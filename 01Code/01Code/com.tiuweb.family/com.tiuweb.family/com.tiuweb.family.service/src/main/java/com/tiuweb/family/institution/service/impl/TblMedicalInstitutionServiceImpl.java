/*
 * @(#) TblMedicalInstitutionServiceImpl  2017-07-10 15:00:42
 *
 * Copyright 2003 by TiuWeb Corporation.
 * 51 zhangba six Road, xian, PRC 710065 // Replace with xian’s address
 * 
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * TiuWeb Corporation.  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with TiuWeb.
 */
package com.tiuweb.family.institution.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.logs.SystemServiceLog;
import com.tiuweb.family.common.ApiUrl;
import com.tiuweb.family.institution.domain.TblDoctorProfessionalLevel;
import com.tiuweb.family.institution.domain.TblMedicalInstitution;
import com.tiuweb.family.institution.domain.TblMedicalInstitutionServiceArea;
import com.tiuweb.family.institution.mapper.TblMedicalInstitutionMapper;
import com.tiuweb.family.institution.service.ITblDoctorProfessionalLevelService;
import com.tiuweb.family.institution.service.ITblMedicalInstitutionService;
import com.tiuweb.family.institution.service.ITblMedicalInstitutionServiceAreaService;
import com.tiuweb.family.offices.domain.TblMedicalInstitutionOffices;
import com.tiuweb.family.offices.service.ITblMedicalInstitutionOfficesService;

/**
 * 
 * <p>
 * Title: 签约医疗机构
 * </p>
 * <p>
 * Description: TODO 签约医疗机构Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-10 15:00:42
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblMedicalInstitutionServiceImpl extends BaseServiceImpl<TblMedicalInstitution>
		implements ITblMedicalInstitutionService {

	@Autowired
	private TblMedicalInstitutionMapper tblMedicalInstitutionMapper;

	@Autowired
	private ITblMedicalInstitutionOfficesService tblMedicalInstitutionOfficesService;

	@Autowired
	private ITblDoctorProfessionalLevelService tblDoctorProfessionalLevelService;

	@Autowired
	private ITblMedicalInstitutionServiceAreaService tblMedicalInstitutionServiceAreaService;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiUrl apiUrl;

	@Override
	protected BaseMapper<TblMedicalInstitution, String> getMapper() {
		return tblMedicalInstitutionMapper;
	}

	@Override
	@SystemServiceLog(description = "医疗机构添加")
	public int insertWithTrans(TblMedicalInstitution record) throws ServiceRunException {
		TblMedicalInstitution exists = findByPrimaryKey(record.getPorgidentity());
		if (null != exists) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
					String.format("%s已经存在", record.getOrgname()));
		}
		// 签约机构下属科室
		List<TblMedicalInstitutionOffices> offices = record.getOffices();
		if (null == offices || offices.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "科室信息无效");
		}

		// 签约机构下属医生职级
		List<TblDoctorProfessionalLevel> levels = record.getLevels();
		if (null == levels || levels.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "职级信息无效");
		}

		// 设置主键,和权限系统机构主键保持一致
		// record.setId(record.getPorgidentity());

		// 机构信息入库
		this.insert(record);

		initOthersInfo(record, offices, levels);
		return 1;
	}

	/**
	 * 科室、医生职级、服务区域信息添加
	 * 
	 * @param record
	 * @param offices
	 * @param levels
	 */
	private void initOthersInfo(TblMedicalInstitution record, List<TblMedicalInstitutionOffices> offices,
			List<TblDoctorProfessionalLevel> levels) {
		for (TblMedicalInstitutionOffices office : offices) {
			office.setCreateuser(record.getCreateuser());
			office.setCreatedate(record.getCreatedate());
			office.setOrgid(record.getOrgid());
			office.setDeletesign(false);

			office.setMedicalinstitutionidentity(record.getId());
		}

		// 医疗机构科室信息添加
		tblMedicalInstitutionOfficesService.insertBatch(offices);

		// 签约机构下属医生职级
		for (TblDoctorProfessionalLevel level : levels) {
			level.setCreateuser(record.getCreateuser());
			level.setCreatedate(record.getCreatedate());
			level.setOrgid(record.getOrgid());
			level.setDeletesign(false);

			// 签约医疗机构外键
			level.setMedicalinstitutionidentity(record.getId());
			// 所属医疗机构名称
			level.setMedicalinstitutionname(record.getOrgname());
		}

		// 医生职级信息添加
		tblDoctorProfessionalLevelService.insertBatch(levels);

		// 签约机构下属服务区域
		List<TblMedicalInstitutionServiceArea> areas = record.getAreas();

		if (null != areas && !areas.isEmpty()) {
			for (TblMedicalInstitutionServiceArea area : areas) {
				area.setCreateuser(record.getCreateuser());
				area.setCreatedate(record.getCreatedate());
				area.setOrgid(record.getOrgid());
				area.setDeletesign(false);

				// 所属医疗机构外键
				area.setMedicalinstitutionidentity(record.getId());
				// 所属医疗机构名称
				area.setMedicalinstitutionname(record.getOrgname());
				// 删除标记
				area.setDeletesign(false);
			}

			tblMedicalInstitutionServiceAreaService.insertBatch(areas);
		}
	}

	@Override
	public List<?> findOriginAll() throws ServiceRunException {
		return restTemplate.getForObject(apiUrl.getOriginOrgUrl(), List.class);
	}
}
