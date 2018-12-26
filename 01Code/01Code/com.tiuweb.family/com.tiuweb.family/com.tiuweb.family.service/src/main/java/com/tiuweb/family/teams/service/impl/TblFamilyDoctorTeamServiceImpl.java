/*
 * @(#) TblFamilyDoctorTeamServiceImpl  2017-07-17 15:07:46
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
package com.tiuweb.family.teams.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.logs.SystemServiceLog;
import com.tiuweb.family.teams.domain.TblFamilyDoctorTeam;
import com.tiuweb.family.teams.domain.TblFamilyDoctorTeamMember;
import com.tiuweb.family.teams.mapper.TblFamilyDoctorTeamMapper;
import com.tiuweb.family.teams.service.ITblFamilyDoctorTeamMemberService;
import com.tiuweb.family.teams.service.ITblFamilyDoctorTeamService;

/**
 * 
 * <p>
 * Title: 签约家庭医生团队信息
 * </p>
 * <p>
 * Description: TODO 签约家庭医生团队信息Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 15:07:46
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblFamilyDoctorTeamServiceImpl extends BaseServiceImpl<TblFamilyDoctorTeam>
		implements ITblFamilyDoctorTeamService {

	@Autowired
	private TblFamilyDoctorTeamMapper tblFamilyDoctorTeamMapper;

	@Autowired
	private ITblFamilyDoctorTeamMemberService tblFamilyDoctorTeamMemberService;
	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblFamilyDoctorTeam, String> getMapper() {
		return tblFamilyDoctorTeamMapper;
	}

	@Override
	@SystemServiceLog(description = "团队及其成员信息添加")
	public int insertTeamWithMember(TblFamilyDoctorTeam tblFamilyDoctorTeam) throws ServiceRunException {
		// 保存团队信息
		this.insert(tblFamilyDoctorTeam);
		List<TblFamilyDoctorTeamMember> memebers = tblFamilyDoctorTeam.getMember();

		List<String> exists = new ArrayList<String>();

		// 遍历成员信息并设置医疗机构外键和名称以及创建属性信息
		for (TblFamilyDoctorTeamMember member : memebers) {
			if (exists.contains(member.getDoctoridentity())) {
				throw new ServiceRunException(RunExceptionEnum.RESP_INSERT_FAILED.getCode(),
						String.format("医生%s不能分配多个角色", new Object[] { member.getDoctorname() }));
			}
			exists.add(member.getDoctoridentity());

			Long init = Long.valueOf("0");
			member.setScorecount(init);
			member.setServicecount(init);
			member.setContactcount(init);

			member.setMedicalteamidentity(tblFamilyDoctorTeam.getId());
			member.setMedicalteamname(tblFamilyDoctorTeam.getTeamname());
			member.setCreatedate(tblFamilyDoctorTeam.getCreatedate());
			member.setCreateuser(tblFamilyDoctorTeam.getCreateuser());
			member.setAreacode(tblFamilyDoctorTeam.getAreacode());
			member.setOrgid(tblFamilyDoctorTeam.getOrgid());
			member.setDeletesign(false);
		}
		return tblFamilyDoctorTeamMemberService.insertBatch(memebers);
	}
}
