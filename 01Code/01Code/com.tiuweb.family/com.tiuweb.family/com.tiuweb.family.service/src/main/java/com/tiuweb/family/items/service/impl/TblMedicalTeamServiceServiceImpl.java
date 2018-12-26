/*
 * @(#) TblMedicalTeamServiceServiceImpl  2017-07-17 12:52:58
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
package com.tiuweb.family.items.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.logs.SystemServiceLog;
import com.tiuweb.family.items.domain.TblMedicalTeamService;
import com.tiuweb.family.items.mapper.TblMedicalTeamServiceMapper;
import com.tiuweb.family.items.service.ITblMedicalTeamServiceService;
import com.tiuweb.family.teams.domain.TblFamilyDoctorTeam;
import com.tiuweb.family.teams.mapper.TblFamilyDoctorTeamMapper;

/**
 * 
 * <p>
 * Title: 团队提供的签约服务汇总信息
 * </p>
 * <p>
 * Description: TODO 团队提供的签约服务汇总信息Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 12:52:58
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblMedicalTeamServiceServiceImpl extends BaseServiceImpl<TblMedicalTeamService>
		implements ITblMedicalTeamServiceService {

	@Autowired
	private TblMedicalTeamServiceMapper tblMedicalTeamServiceMapper;

	@Autowired
	private TblFamilyDoctorTeamMapper tblFamilyDoctorTeamMapper;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblMedicalTeamService, String> getMapper() {
		return tblMedicalTeamServiceMapper;
	}

	@Override
	@SystemServiceLog(description = "新增医疗团队服务信息")
	public int insertItemByTeam(TblMedicalTeamService teamItem) throws ServiceRunException {
		TblFamilyDoctorTeam team = tblFamilyDoctorTeamMapper.findByPrimaryKey(teamItem.getTeamidentify());
		if (null == team) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "团队不存在");
		}
		teamItem.setTeamname(team.getTeamname());
		teamItem.setDeletesign(false);
		return this.insert(teamItem);
	}

	@Override
	@SystemServiceLog(description = "根据团队外键和服务项外键删除医疗团队服务信息")
	public int deleteItem(TblMedicalTeamService teamItem) throws ServiceRunException {
		TblMedicalTeamService queryTeamItem = new TblMedicalTeamService();
		queryTeamItem.setServcieitemidentify(teamItem.getServcieitemidentify());
		queryTeamItem.setTeamidentify(teamItem.getTeamidentify());

		List<TblMedicalTeamService> existsItems = this.findByCondition(queryTeamItem);
		if (null == existsItems || existsItems.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "服務不存在");
		}

		// 清空查询项
		teamItem.setServcieitemidentify(null);
		teamItem.setTeamidentify(null);

		// 根据主键删除
		for (TblMedicalTeamService item : existsItems) {
			teamItem.setId(item.getId());
			this.update(teamItem);
		}

		return 1;
	}
}
