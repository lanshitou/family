/*
 * @(#) TblResidentFamilyMemberServiceImpl  2017-07-19 11:43:25
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
package com.tiuweb.family.home.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.CodeInfoUtil;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.logs.SystemServiceLog;
import com.tiuweb.family.contact.domain.TblResidentContact;
import com.tiuweb.family.contact.domain.TblResidentHealthArchives;
import com.tiuweb.family.contact.service.ITblResidentContactService;
import com.tiuweb.family.contact.service.ITblResidentHealthArchivesService;
import com.tiuweb.family.home.domain.TblResidentFamily;
import com.tiuweb.family.home.domain.TblResidentFamilyMember;
import com.tiuweb.family.home.mapper.TblResidentFamilyMemberMapper;
import com.tiuweb.family.home.service.ITblResidentFamilyMemberService;
import com.tiuweb.family.home.service.ITblResidentFamilyService;
import com.tiuweb.family.util.Constants;

/**
 * 
 * <p>
 * Title: 居民家庭成员信息
 * </p>
 * <p>
 * Description: TODO 居民家庭成员信息Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-19 11:43:25
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblResidentFamilyMemberServiceImpl extends BaseServiceImpl<TblResidentFamilyMember>
		implements ITblResidentFamilyMemberService {

	@Autowired
	private TblResidentFamilyMemberMapper tblResidentFamilyMemberMapper;

	@Autowired
	private ITblResidentHealthArchivesService tblResidentHealthArchivesService;

	@Autowired
	private ITblResidentFamilyService tblResidentFamilyService;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblResidentFamilyMember, String> getMapper() {
		return tblResidentFamilyMemberMapper;
	}

	@Override
	@SystemServiceLog(description = "根据身份证号码查询居民全部家庭成员信息且不存在则新增家庭及成员信息")
	public List<TblResidentFamilyMember> findAllMemberWithAddByCondition(TblResidentFamilyMember record,
			TblResidentFamily family) {

		List<TblResidentFamilyMember> list = tblResidentFamilyMemberMapper.findAllMemberByCondition(record);

		// 不存在任何家庭则新建
		if (list == null || list.isEmpty()) {
			TblResidentHealthArchives archives = new TblResidentHealthArchives();
			archives.setIdentitynumber(record.getIdentitynumber());
			List<TblResidentHealthArchives> self = tblResidentHealthArchivesService.findByCondition(archives);

			if (null == self || self.isEmpty()) {
				throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
						String.format("%s健康档案不存在", new Object[] { record.getIdentitynumber() }));
			}

			else if (self.size() > 1) {
				throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
						String.format("%s健康档案重复", new Object[] { record.getIdentitynumber() }));
			}
			// 没有家庭则新增
			else {
				// 家庭新增
				family.setMembrcount(1);
				tblResidentFamilyService.insert(family);

				// 家庭成员信息构造
				TblResidentFamilyMember memeber = new TblResidentFamilyMember();

				archives = self.get(0);
				// 默认创建的额是本人的
				memeber.setMembership(Short.valueOf("1"));
				memeber.setArchivesnumber(archives.getArchivesnumber());
				memeber.setFamilyidentity(family.getId());
				memeber.setFamilynumber(family.getFamilynumber());
				memeber.setAddress(archives.getPresentaddress());
				memeber.setAge(archives.getAge());
				memeber.setSex(archives.getSex());
				memeber.setBirthdate(archives.getBirthdate());
				memeber.setTelephone(archives.getContactphone());
				memeber.setIdentitynumber(archives.getIdentitynumber());
				memeber.setHealthidentity(archives.getId());
				memeber.setMembername(archives.getResidentname());

				// 创建属性信息
				memeber.setCreatedate(family.getCreatedate());
				memeber.setCreateuser(family.getCreateuser());
				memeber.setOrgid(family.getOrgid());
				memeber.setAreacode(family.getAreacode());

				// 新增家庭成员
				this.insert(memeber);

				list = tblResidentFamilyMemberMapper.findAllMemberByCondition(record);
			}
		}
		return list;
	}

	@Override
	public List<TblResidentFamilyMember> findAllMemberByCondition(TblResidentFamilyMember record) {
		if (StringUtils.isNullOrEmpty(record.getIdentitynumber())) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "请输入身份证号");
		}

		List<TblResidentFamilyMember> list = tblResidentFamilyMemberMapper.findAllMemberByCondition(record);

		// 不提示,方便前台做判断
		// if (null == list || list.isEmpty()) {
		// throw new
		// ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
		// "居民家庭成员信息不存在");
		// }
		return list;
	}
}
