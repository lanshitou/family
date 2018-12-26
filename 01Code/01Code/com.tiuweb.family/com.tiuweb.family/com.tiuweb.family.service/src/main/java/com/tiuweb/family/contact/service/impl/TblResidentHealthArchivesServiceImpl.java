/*
 * @(#) TblResidentHealthArchivesServiceImpl  2017-07-17 09:42:37
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
package com.tiuweb.family.contact.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.logs.SystemServiceLog;
import com.tiuweb.family.contact.domain.TblResidentContact;
import com.tiuweb.family.contact.domain.TblResidentHealthArchives;
import com.tiuweb.family.contact.mapper.TblResidentHealthArchivesMapper;
import com.tiuweb.family.contact.service.ITblResidentContactService;
import com.tiuweb.family.contact.service.ITblResidentHealthArchivesService;
import com.tiuweb.family.home.domain.TblResidentFamilyMember;
import com.tiuweb.family.home.mapper.TblResidentFamilyMemberMapper;
import com.tiuweb.family.util.Utility;

/**
 * 
 * <p>
 * Title: 居民个人健康档案
 * </p>
 * <p>
 * Description: TODO 居民个人健康档案Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 09:42:37
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblResidentHealthArchivesServiceImpl extends BaseServiceImpl<TblResidentHealthArchives>
		implements ITblResidentHealthArchivesService {

	@Autowired
	private TblResidentHealthArchivesMapper tblResidentHealthArchivesMapper;

	@Autowired
	private TblResidentFamilyMemberMapper tblResidentFamilyMemberMapper;

	@Autowired
	private ITblResidentContactService tblResidentContactService;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblResidentHealthArchives, String> getMapper() {
		return tblResidentHealthArchivesMapper;
	}

	@Override
	@Transactional(readOnly = true)
	@SystemServiceLog(description = "根据身份证号码查询居民健康档案且查询是否存在于其他家庭")
	public List<TblResidentHealthArchives> findAllMemberWithFamilyByCondition(TblResidentHealthArchives archives) {

		if (StringUtils.isNullOrEmpty(archives.getIdentitynumber())) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "请输入身份证号");
		}

		TblResidentFamilyMember record = new TblResidentFamilyMember();
		record.setIdentitynumber(archives.getIdentitynumber());
		List<TblResidentFamilyMember> list = tblResidentFamilyMemberMapper.findAllMemberByCondition(record);

		// 已经存在于其他家庭
		if (list != null && !list.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
					String.format("%s已经存在于其他家庭", new Object[] { archives.getIdentitynumber() }));
		}

		List<TblResidentHealthArchives> self = tblResidentHealthArchivesMapper.findByCondition(archives);

		// 个人健康档案不存在
		if (self == null || self.size() == 0) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
					String.format("%s健康档案不存在", new Object[] { archives.getIdentitynumber() }));
		}
		return self;
	}

	@Override
	@Transactional(readOnly = true)
	@SystemServiceLog(description = "根据身份证号码查询居民健康档案且查询是否已经签订协议")
	public List<TblResidentHealthArchives> findMemberWithContactByCondition(
			TblResidentHealthArchives tblResidentHealthArchives) {

		if (StringUtils.isNullOrEmpty(tblResidentHealthArchives.getIdentitynumber())) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "请输入身份证号");
		}

		TblResidentContact contact = new TblResidentContact();
		contact.setIdentitynumber(tblResidentHealthArchives.getIdentitynumber());

		List<TblResidentContact> existsContact = tblResidentContactService.findByCondition(contact);
		// 查询是否已经签署有效的协议
		if (existsContact != null && !existsContact.isEmpty()) {
			for (TblResidentContact tblResidentContact : existsContact) {
				if (tblResidentContact.getApplystatus() == 0 || tblResidentContact.getApplystatus() == 1) {
					throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
							String.format("已经签订协议%s", new Object[] { tblResidentContact.getContactnumber() }));
				}
			}
		}

		List<TblResidentHealthArchives> self = this.findByCondition(tblResidentHealthArchives);
		// 个人健康档案不存在 为了前台弹框提示
		// if (self == null || self.size() == 0) {
		// throw new
		// ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
		// String.format("%s健康档案不存在", new Object[] {
		// tblResidentHealthArchives.getIdentitynumber() }));
		// }
		return self;
	}

	@Override
	public int insertArchive(TblResidentHealthArchives record) throws ServiceRunException {
		TblResidentHealthArchives queryArchives = new TblResidentHealthArchives();
		queryArchives.setIdentitynumber(record.getIdentitynumber());

		List<TblResidentHealthArchives> existsArchives = findByCondition(queryArchives);
		if (null != existsArchives && !existsArchives.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
					String.format("已经存在身份证号为%s的居民健康档案", new Object[] { record.getIdentitynumber() }));
		}
		record.setArchivesnumber(Utility.getHealthNumber());
		return insert(record);
	}
}
