/*
 * @(#) TblMedicalInstitutionApi  2017-07-10 15:00:42
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
package com.tiuweb.family.webapi.api;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiuweb.climb.framework.commons.util.MessModel;
import com.tiuweb.climb.framework.commons.util.SearchParams;
import com.tiuweb.climb.framework.config.logs.SystemApiLog;
import com.tiuweb.family.institution.domain.TblDoctorProfessionalLevel;
import com.tiuweb.family.institution.domain.TblMedicalInstitution;
import com.tiuweb.family.institution.domain.TblMedicalInstitutionServiceArea;
import com.tiuweb.family.institution.service.ITblDoctorProfessionalLevelService;
import com.tiuweb.family.institution.service.ITblMedicalInstitutionService;
import com.tiuweb.family.institution.service.ITblMedicalInstitutionServiceAreaService;
import com.tiuweb.family.offices.domain.TblMedicalInstitutionOffices;
import com.tiuweb.family.offices.service.ITblMedicalInstitutionOfficesService;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 签约医疗机构
 * </p>
 * <p>
 * Description: TODO 签约医疗机构API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-10 15:00:42
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblMedicalInstitutionApi", description = "签约医疗机构")
@RestController
@RequestMapping(value = "/institution")
public class TblMedicalInstitutionApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblMedicalInstitutionService tblMedicalInstitutionService;

	@Autowired
	private ITblMedicalInstitutionOfficesService tblMedicalInstitutionOfficesService;

	@Autowired
	private ITblDoctorProfessionalLevelService tblDoctorProfessionalLevelService;

	@Autowired
	private ITblMedicalInstitutionServiceAreaService tblMedicalInstitutionServiceAreaService;

	@RequestMapping(value = "/findOriginAll", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "从权限系统查询全部组织机构")
	@ApiOperation(value = "从权限系统查询全部组织机构", notes = "从权限系统查询全部组织机构")
	public ResponseEntity<?> findOriginAll() {
		return new ResponseEntity<>(tblMedicalInstitutionService.findOriginAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部签约医疗机构")
	@ApiOperation(value = "查询全部签约医疗机构", notes = "查询全部签约医疗机构")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(tblMedicalInstitutionService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findAllByUserOrgId", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询当前用户所属医疗机构及其下属的全部医疗机构")
	@ApiOperation(value = "查询当前用户所属医疗机构及其下属的全部医疗机构", notes = "查询当前用户所属医疗机构及其下属的全部医疗机构")
	public ResponseEntity<?> findByOrgId() {
		return new ResponseEntity<>(tblMedicalInstitutionService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询签约医疗机构")
	@ApiOperation(value = "分页查询签约医疗机构", notes = "分页查询签约医疗机构")
	public ResponseEntity<?> findPage(@RequestBody SearchParams params) {
		return new ResponseEntity<>(tblMedicalInstitutionService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "主键查询签约医疗机构")
	@ApiOperation(value = "主键查询签约医疗机构", notes = "主键查询签约医疗机构")
	public ResponseEntity<?> findByKey(@RequestBody TblMedicalInstitution tblMedicalInstitution) {
		TblMedicalInstitution result = tblMedicalInstitutionService.findByPrimaryKey(tblMedicalInstitution.getId());
		TblMedicalInstitutionOffices office = new TblMedicalInstitutionOffices();
		office.setMedicalinstitutionidentity(tblMedicalInstitution.getId());
		office.setDeletesign(false);
		// 签约医疗机构下属科室信息
		result.setOffices(tblMedicalInstitutionOfficesService.findByCondition(office));

		TblDoctorProfessionalLevel level = new TblDoctorProfessionalLevel();
		level.setMedicalinstitutionidentity(tblMedicalInstitution.getId());
		level.setDeletesign(false);

		// 签约医疗机构下属医生职级
		result.setLevels(tblDoctorProfessionalLevelService.findByCondition(level));

		TblMedicalInstitutionServiceArea area = new TblMedicalInstitutionServiceArea();
		area.setMedicalinstitutionidentity(tblMedicalInstitution.getId());
		area.setDeletesign(false);

		List<TblMedicalInstitutionServiceArea> tt = tblMedicalInstitutionServiceAreaService.findByCondition(area);

		// 签约医疗机构服务区域
		result.setAreas(tt);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询签约医疗机构")
	@ApiOperation(value = "条件查询签约医疗机构", notes = "条件查询签约医疗机构")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblMedicalInstitution tblMedicalInstitution) {
		List<TblMedicalInstitution> list = tblMedicalInstitutionService.findByCondition(tblMedicalInstitution);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "新增签约医疗机构数据")
	@ApiOperation(value = "新增签约医疗机构", notes = "新增签约医疗机构")
	public ResponseEntity<?> insert(@RequestBody TblMedicalInstitution tblMedicalInstitution) {
		MessModel messModel = new MessModel();
		tblMedicalInstitution.setCreateuser(getUserId());
		tblMedicalInstitution.setCreatedate(new Date());
		tblMedicalInstitution.setOrgid(getOrgId());
		tblMedicalInstitution.setDeletesign(false);

		int result = tblMedicalInstitutionService.insertWithTrans(tblMedicalInstitution);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("新增成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "更新签约医疗机构数据")
	@ApiOperation(value = "更新签约医疗机构数据", notes = "更新签约医疗机构数据")
	public ResponseEntity<?> update(@RequestBody TblMedicalInstitution tblMedicalInstitution) {
		MessModel messModel = new MessModel();
		tblMedicalInstitution.setUpdatedate(new Date());
		tblMedicalInstitution.setUpdateuser(getUserId());
		int result = tblMedicalInstitutionService.update(tblMedicalInstitution);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("更新成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "删除签约医疗机构数据")
	@ApiOperation(value = "删除签约医疗机构数据", notes = "删除签约医疗机构数据")
	public ResponseEntity<?> delete(@RequestBody TblMedicalInstitution tblMedicalInstitution) {
		MessModel messModel = new MessModel();
		tblMedicalInstitution.setUpdatedate(new Date());
		tblMedicalInstitution.setUpdateuser(getUserId());
		tblMedicalInstitution.setDeletesign(true);
		// int result =
		// tblMedicalInstitutionService.delete(tblMedicalInstitution);
		int result = tblMedicalInstitutionService.update(tblMedicalInstitution);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("删除成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

}
