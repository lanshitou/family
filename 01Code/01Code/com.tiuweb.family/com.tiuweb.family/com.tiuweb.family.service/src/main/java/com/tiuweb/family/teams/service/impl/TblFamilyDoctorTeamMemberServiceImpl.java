/*
 * @(#) TblFamilyDoctorTeamMemberServiceImpl  2017-07-17 15:20:00
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

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.redis.service.IRedisService;
import com.tiuweb.family.teams.domain.TblFamilyDoctorTeamMember;
import com.tiuweb.family.teams.mapper.TblFamilyDoctorTeamMemberMapper;
import com.tiuweb.family.teams.service.ITblFamilyDoctorTeamMemberService;

/**
 * 
 * <p>
 * Title: 签约家庭医生团队成员信息
 * </p>
 * <p>
 * Description: TODO 签约家庭医生团队成员信息Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 15:20:00
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblFamilyDoctorTeamMemberServiceImpl extends BaseServiceImpl<TblFamilyDoctorTeamMember>
		implements ITblFamilyDoctorTeamMemberService {

	@Autowired
	private TblFamilyDoctorTeamMemberMapper tblFamilyDoctorTeamMemberMapper;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblFamilyDoctorTeamMember, String> getMapper() {
		return tblFamilyDoctorTeamMemberMapper;
	}

	@Override
	public int insertMember(TblFamilyDoctorTeamMember tblFamilyDoctorTeamMember) throws ServiceRunException {
		TblFamilyDoctorTeamMember query = new TblFamilyDoctorTeamMember();
		query.setMedicalteamidentity(tblFamilyDoctorTeamMember.getMedicalteamidentity());
		query.setDoctoridentity(tblFamilyDoctorTeamMember.getDoctoridentity());

		Long init = Long.valueOf("0");
		tblFamilyDoctorTeamMember.setContactcount(init);
		tblFamilyDoctorTeamMember.setScorecount(init);
		tblFamilyDoctorTeamMember.setServicecount(init);
		// 查询要添加的医生是否已经存在当前团队
		List<TblFamilyDoctorTeamMember> existsMember = findByCondition(query);

		// 已经存在则抛出异常
		if (null != existsMember && !existsMember.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.RESP_INSERT_FAILED.getCode(),
					String.format("医生%s不能分配多个角色", new Object[] { tblFamilyDoctorTeamMember.getDoctorname() }));
		}

		return insert(tblFamilyDoctorTeamMember);
	}
}
