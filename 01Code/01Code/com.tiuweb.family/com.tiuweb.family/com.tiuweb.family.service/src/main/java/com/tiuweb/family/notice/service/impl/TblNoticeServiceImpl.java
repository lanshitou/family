/*
 * @(#) TblNoticeServiceImpl  2017-07-26 15:59:14
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
package com.tiuweb.family.notice.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.logs.SystemServiceLog;
import com.tiuweb.family.common.task.CommonScheduler;
import com.tiuweb.family.common.task.NoticePublishJob;
import com.tiuweb.family.doctors.domain.TblMedicalInstitutionDoctor;
import com.tiuweb.family.doctors.service.ITblMedicalInstitutionDoctorService;
import com.tiuweb.family.notice.domain.TblNotice;
import com.tiuweb.family.notice.domain.TblNoticeAttachment;
import com.tiuweb.family.notice.mapper.TblNoticeMapper;
import com.tiuweb.family.notice.service.ITblNoticeAttachmentService;
import com.tiuweb.family.notice.service.ITblNoticeService;
import com.tiuweb.family.remind.domain.TblSystemMessageWarn;
import com.tiuweb.family.remind.service.ITblSystemMessageWarnService;
import com.tiuweb.family.util.Constants;
import com.tiuweb.family.util.Utility;

/**
 * 
 * <p>
 * Title: 通知消息
 * </p>
 * <p>
 * Description: TODO 通知消息Impl层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-26 15:59:14
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblNoticeServiceImpl extends BaseServiceImpl<TblNotice> implements ITblNoticeService {

	@Autowired
	private TblNoticeMapper tblNoticeMapper;

	@Autowired
	private ITblNoticeAttachmentService tblNoticeAttachmentService;

	@Autowired
	private ITblMedicalInstitutionDoctorService tblMedicalInstitutionDoctorService;

	@Autowired
	private CommonScheduler commonScheduler;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblNotice, String> getMapper() {
		return tblNoticeMapper;
	}

	@Override
	@Transactional(readOnly = true)
	@SystemServiceLog(description = "通知公告详情查询")
	public TblNotice queryNoticeDetails(String id) throws ServiceRunException {
		if (StringUtils.isNullOrEmpty(id)) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "通知公告主键不能为空！");
		}

		TblNotice record = findByPrimaryKey(id);

		TblNoticeAttachment tblNoticeAttachment = new TblNoticeAttachment();
		tblNoticeAttachment.setNoticeidentity(record.getId());
		tblNoticeAttachment.setDeletesign(false);
		List<TblNoticeAttachment> attachments = tblNoticeAttachmentService.findByCondition(tblNoticeAttachment);
		record.setAttachments(attachments);

		return record;
	}

	@Override
	@SystemServiceLog(description = "新增通知公告")
	public int insertNotice(TblNotice record, String loginUser) throws ServiceRunException {
		// 通知公告入库
		if (record.getPublishmode() == 0) {
			// 自动设置发布时间为当前时间
			record.setPublishtime(new Date());
			record.setPublishstatus((short) 1);
		} else if (record.getPublishmode() == 1) {
			initTimerPublish(record);
		}
		this.insert(record);

		// 通知公告附件
		List<TblNoticeAttachment> attachments = record.getAttachments();
		if (null != attachments && !attachments.isEmpty()) {
			for (TblNoticeAttachment attachment : attachments) {
				attachment.setNoticeidentity(record.getId());
				attachment.setDeletesign(false);
				attachment.setCreateuser(record.getCreateuser());
				attachment.setCreatedate(record.getCreatedate());
				attachment.setOrgid(record.getOrgid());
			}

			tblNoticeAttachmentService.insertBatch(attachments);
		}

		return initAcceptorsAndWarning(record, loginUser);
	}

	private int initAcceptorsAndWarning(TblNotice record, String loginUser) {
		TblSystemMessageWarn message = new TblSystemMessageWarn();
		message.setCategory(Constants.TIP_NOTIFY);
		message.setStatus(false);
		message.setMsgidentity(record.getId());
		message.setCreatedate(new Date());
		message.setSenduser(record.getCreateuser());
		message.setContent(record.getTitle());
		message.setSendloginname(loginUser);
		TblMedicalInstitutionDoctor selfInstitutionDoctorQuery = new TblMedicalInstitutionDoctor();
		List<TblSystemMessageWarn> warns = new ArrayList<TblSystemMessageWarn>();
		// 发布范围类别:1所有人可见(默认),2所有医护端账号可见,3本机构内部可见,4所有签约居民和家庭可见',
		switch (record.getPublishcategory()) {
		case 2:
			initMessageWarn(message, warns, selfInstitutionDoctorQuery);
			break;
		case 3:
			selfInstitutionDoctorQuery.setInstitutionidentity(record.getOrgid());
			initMessageWarn(message, warns, selfInstitutionDoctorQuery);
			break;
		}
		if (!warns.isEmpty()) {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("target", warns);
			params.put("notice", record);
			Trigger Trigger = null;
			// 实时发布
			if (record.getPublishmode() == 0) {
				Trigger = TriggerBuilder.newTrigger().startNow().withIdentity(Utility.get32UUID()).build();
			}
			// 定时发布
			else {
				Trigger = TriggerBuilder.newTrigger().startAt(record.getPublishtime()).withIdentity(Utility.get32UUID())
						.build();
			}
			try {
				commonScheduler.scheduleJob(params, Trigger, NoticePublishJob.class);
			} catch (SchedulerException e) {
				throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "定时任务调度失败");
			}
		}
		return 1;
	}

	private void initMessageWarn(TblSystemMessageWarn message, List<TblSystemMessageWarn> warns,
			TblMedicalInstitutionDoctor selfInstitutionDoctorQuery) {
		List<TblMedicalInstitutionDoctor> selfInstitutionDoctors = tblMedicalInstitutionDoctorService
				.findByCondition(selfInstitutionDoctorQuery);
		if (null != selfInstitutionDoctors) {
			for (TblMedicalInstitutionDoctor acceptor : selfInstitutionDoctors) {
				TblSystemMessageWarn tempWarn = message.clone();
				tempWarn.setAcceptuser(acceptor.getPuserid());
				tempWarn.setAcceptloginname(acceptor.getPaccount());
				warns.add(tempWarn);
			}
		}
	}

	/**
	 * 发布时间处理
	 * 
	 * @param record
	 */
	private void initTimerPublish(TblNotice record) {
		// 判断发布日期是否是过去时间&null
		if (record.getPublishtime() == null) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "请设置发布时间");
		} else {
			record.setPublishstatus((short) 0);
		}
	}

	@Override
	@SystemServiceLog(description = "通知公告撤回")
	public int revokeNotice(TblNotice record) throws ServiceRunException {
		if (StringUtils.isNullOrEmpty(record.getId())) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "通知公告主键不能为空！");
		}

		TblNotice tblNotice = findByPrimaryKey(record.getId());
		if (tblNotice.getPublishstatus() == null) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "通知公告发布状态未知");
		}
		short status = tblNotice.getPublishstatus();
		if (status == 2) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "已撤回通知公告不能反复撤回");
		}
		if (status == 0 || status == 1) {
			tblNotice.setPublishstatus((short) 2);
			tblNotice.setUpdateuser(record.getUpdateuser());
			tblNotice.setUpdatedate(record.getUpdatedate());
		}

		return update(tblNotice);
	}

	@Override
	@SystemServiceLog(description = "重新发布通知公告")
	public int resendNotice(TblNotice record, String loginUser) throws ServiceRunException {
		TblNotice tblNotice = findByPrimaryKey(record.getId());
		if (tblNotice == null) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "通知公告记录为空");
		}
		return initAcceptorsAndWarning(tblNotice, loginUser);
	}

	@Override
	@SystemServiceLog(description = "带条件判断的通知公告修改")
	public int newUpdate(TblNotice record) throws ServiceRunException {
		// 判断待修改通知的主键
		if (StringUtils.isNullOrEmpty(record.getId())) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "通知公告主键不能为空！");
		}

		// 判断待修改记录的发布状态
		TblNotice tblNotice = findByPrimaryKey(record.getId());
		if (tblNotice.getPublishstatus() == null) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "通知公告发布状态未知");
		}
		if (tblNotice.getPublishstatus() != 2) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "非已撤回状态不能修改");
		}

		if (record.getPublishmode() != null) {
			if (record.getPublishmode() == 0) {
				record.setPublishstatus((short) 1);
				// 再发布时自动设置发布时间为当前
				record.setPublishtime(new Date());
			} else if (record.getPublishmode() == 1) {
				initTimerPublish(record);
			}
		}
		return update(record);
	}
}
