/*
 * @(#) TblResidentFamilyServiceImpl  2017-07-19 11:41:23
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

import java.util.ArrayList;
import java.util.Date;
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
import com.tiuweb.family.contact.domain.TblResidentHealthArchives;
import com.tiuweb.family.contact.service.ITblResidentHealthArchivesService;
import com.tiuweb.family.home.domain.TblResidentFamily;
import com.tiuweb.family.home.domain.TblResidentFamilyMember;
import com.tiuweb.family.home.mapper.TblResidentFamilyMapper;
import com.tiuweb.family.home.service.ITblResidentFamilyMemberService;
import com.tiuweb.family.home.service.ITblResidentFamilyService;

/**
 * 
 * <p>
 * Title: 居民家庭信息
 * </p>
 * <p>
 * Description: TODO 居民家庭信息Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-19 11:41:23
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblResidentFamilyServiceImpl extends BaseServiceImpl<TblResidentFamily>
		implements ITblResidentFamilyService {

	@Autowired
	private TblResidentFamilyMapper tblResidentFamilyMapper;

	@Autowired
	private ITblResidentFamilyMemberService tblResidentFamilyMemberService;

	@Autowired
	private ITblResidentHealthArchivesService tblResidentHealthArchivesService;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblResidentFamily, String> getMapper() {
		return tblResidentFamilyMapper;
	}

	@Override
	@SystemServiceLog(description = "添加协议时更新(新增)家庭成员信息")
	public int updateWithInsertMember(TblResidentFamily tblResidentFamily) {
		List<TblResidentFamilyMember> members = tblResidentFamily.getMembers();
		if (null == members || members.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
					RunExceptionEnum.RESP_RESULT_EMPTY.getDescription());
		}

		// 根据家庭信息主键获取家庭信息
		TblResidentFamily tempFamily = tblResidentFamilyMapper.findByPrimaryKey(tblResidentFamily.getId());

		if (null == tempFamily) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "家庭信息不存在");
		}

		List<TblResidentHealthArchives> currentArchives = null;
		TblResidentHealthArchives current = new TblResidentHealthArchives();

		int isMaster = 0;
		int allMember = tempFamily.getMembrcount();

		// 遍历居民信息
		for (TblResidentFamilyMember member : members) {
			current.setIdentitynumber(member.getIdentitynumber());

			// 每次查询 保证信息的完整性
			currentArchives = tblResidentHealthArchivesService.findByCondition(current);

			if (null == currentArchives || currentArchives.isEmpty()) {
				throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
						String.format("%s居民健康档案不存在", new Object[] { member.getIdentitynumber() }));
			}

			if (currentArchives.size() > 1) {
				throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
						String.format("%s居民健康档案重复", new Object[] { member.getIdentitynumber() }));
			}

			if (null != member.getIsmaster() && member.getIsmaster()) {
				isMaster = isMaster + 1;
			}

			// 重新查询后给成员设值
			initFamilyMembers(tempFamily, currentArchives, member);

			if (StringUtils.isNullOrEmpty(member.getId())) {
				tblResidentFamilyMemberService.insert(member);
			} else {
				tblResidentFamilyMemberService.update(member);
			}
			allMember++;
		}
		if (0 == isMaster) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "缺少户主信息");
		}
		if (1 != isMaster) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "只能有一个户主信息");
		}
		tempFamily.setMembrcount(allMember);
		// 更新家庭成员总数
		return tblResidentFamilyMapper.update(tempFamily);
	}

	/**
	 * 初始化家庭成员信息
	 * 
	 * @param tempFamily
	 * @param currentArchives
	 * @param member
	 */
	private void initFamilyMembers(TblResidentFamily tempFamily, List<TblResidentHealthArchives> currentArchives,
			TblResidentFamilyMember member) {
		TblResidentHealthArchives archives = currentArchives.get(0);

		member.setFamilyidentity(tempFamily.getId());
		member.setFamilynumber(tempFamily.getFamilynumber());

		member.setIsserved(false);

		member.setAddress(archives.getPresentaddress());
		member.setAge(archives.getAge());
		member.setSex(archives.getSex());
		member.setBirthdate(archives.getBirthdate());
		member.setTelephone(archives.getContactphone());
		member.setIdentitynumber(archives.getIdentitynumber());
		member.setHealthidentity(archives.getId());
		member.setArchivesnumber(archives.getArchivesnumber());
		member.setMembername(archives.getResidentname());

		member.setCreatedate(tempFamily.getCreatedate());
		member.setCreateuser(tempFamily.getCreateuser());
		member.setOrgid(tempFamily.getOrgid());
		member.setAreacode(tempFamily.getAreacode());
		member.setDeletesign(false);
	}

	@Override
	@SystemServiceLog(description = "新增居民家庭和成员信息")
	public int insertWithMembers(TblResidentFamily tblResidentFamily) {
		List<TblResidentFamilyMember> members = tblResidentFamily.getMembers();
		if (null == members || members.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "家庭成员信息无效");
		}

		// 更新家庭成员人数
		tblResidentFamily.setMembrcount(members.size());
		this.insert(tblResidentFamily);
		TblResidentHealthArchives currentQuery = new TblResidentHealthArchives();
		List<TblResidentHealthArchives> currentArchives = null;
		TblResidentHealthArchives signalArchive = null;
		int isMaster = 0;
		// 遍历居民信息
		for (TblResidentFamilyMember member : members) {
			currentQuery.setIdentitynumber(member.getIdentitynumber());
			currentArchives = tblResidentHealthArchivesService.findByCondition(currentQuery);

			if (null == currentArchives || currentArchives.isEmpty()) {
				throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
						String.format("%s居民健康档案不存在", new Object[] { member.getIdentitynumber() }));
			}

			if (currentArchives.size() > 1) {
				throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
						String.format("%s居民健康档案重复", new Object[] { member.getIdentitynumber() }));
			}

			if (null != member.getIsmaster() && member.getIsmaster()) {
				isMaster = isMaster + 1;
			}
			signalArchive = currentArchives.get(0);
			member.setDeletesign(false);
			member.setTelephone(signalArchive.getContactphone());
			member.setHealthidentity(currentArchives.get(0).getId());
			member.setArchivesnumber(currentArchives.get(0).getArchivesnumber());
			member.setBirthdate(currentArchives.get(0).getBirthdate());
			member.setFamilyidentity(tblResidentFamily.getId());
			member.setFamilynumber(tblResidentFamily.getFamilynumber());

			member.setCreatedate(tblResidentFamily.getCreatedate());
			member.setCreateuser(tblResidentFamily.getCreateuser());
			member.setOrgid(tblResidentFamily.getOrgid());
			member.setAreacode(tblResidentFamily.getAreacode());
		}
		if (0 == isMaster) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "缺少户主信息");
		}
		if (1 != isMaster) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "只能有一个户主信息");
		}
		return tblResidentFamilyMemberService.insertBatch(members);
	}

	@Override
	@SystemServiceLog(description = "更新居民家庭和成员信息")
	public int updateWithMembers(TblResidentFamily tblResidentFamily) {
		List<TblResidentFamilyMember> members = tblResidentFamily.getMembers();
		if (null == members || members.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "家庭成员信息无效");
		}

		// 更新家庭成员人数
		tblResidentFamily.setMembrcount(members.size());
		this.update(tblResidentFamily);
		TblResidentHealthArchives currentQuery = new TblResidentHealthArchives();
		List<TblResidentHealthArchives> currentArchives = null;

		List<TblResidentFamilyMember> insertMemer = new ArrayList<TblResidentFamilyMember>();

		TblResidentFamily currentFamily = this.findByPrimaryKey(tblResidentFamily.getId());

		int isMaster = 0;

		// 遍历居民信息
		for (TblResidentFamilyMember member : members) {
			currentQuery.setIdentitynumber(member.getIdentitynumber());
			currentArchives = tblResidentHealthArchivesService.findByCondition(currentQuery);

			if (null == currentArchives || currentArchives.isEmpty()) {
				throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
						String.format("%s居民健康档案不存在", new Object[] { member.getIdentitynumber() }));
			}

			if (currentArchives.size() > 1) {
				throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
						String.format("%s居民健康档案重复", new Object[] { member.getIdentitynumber() }));
			}

			if (null != member.getIsmaster() && member.getIsmaster()) {
				isMaster = isMaster + 1;
			}

			member.setHealthidentity(currentArchives.get(0).getId());
			member.setArchivesnumber(currentArchives.get(0).getArchivesnumber());
			member.setBirthdate(currentArchives.get(0).getBirthdate());
			member.setFamilyidentity(tblResidentFamily.getId());
			member.setFamilynumber(tblResidentFamily.getFamilynumber());

			// 新增
			if (StringUtils.isNullOrEmpty(member.getId())) {
				member.setAddress(currentArchives.get(0).getPresentaddress());

				member.setDeletesign(false);
				member.setCreatedate(tblResidentFamily.getUpdatedate());
				member.setCreateuser(tblResidentFamily.getUpdateuser());
				member.setOrgid(currentFamily.getOrgid());
				member.setAreacode(currentFamily.getAreacode());

				insertMemer.add(member);
			}
			// 更新
			else {
				member.setUpdatedate(tblResidentFamily.getUpdatedate());
				member.setUpdateuser(tblResidentFamily.getUpdateuser());
				tblResidentFamilyMemberService.update(member);
			}
		}

		if (0 == isMaster) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "缺少户主信息");
		}
		if (1 != isMaster) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "只能有一个户主信息");
		}

		// 新增的家庭成员保存
		if (!insertMemer.isEmpty()) {
			tblResidentFamilyMemberService.insertBatch(insertMemer);
		}

		return 1;
	}

	@Override
	@Transactional(readOnly = true)
	@SystemServiceLog(description = "条件查询家庭成员信息")
	public TblResidentFamily findDetailsByCondition(TblResidentFamily tblResidentFamily) throws ServiceRunException {
		List<TblResidentFamily> familys = this.findByCondition(tblResidentFamily);
		if (null == familys || familys.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "家庭信息无效");
		}
		if (familys.size() > 1) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "家庭信息重复");
		}

		TblResidentFamily currentFaimly = familys.get(0);
		TblResidentFamilyMember record = new TblResidentFamilyMember();
		record.setFamilyidentity(currentFaimly.getId());
		currentFaimly.setMembers(tblResidentFamilyMemberService.findByCondition(record));
		return currentFaimly;
	}
}
