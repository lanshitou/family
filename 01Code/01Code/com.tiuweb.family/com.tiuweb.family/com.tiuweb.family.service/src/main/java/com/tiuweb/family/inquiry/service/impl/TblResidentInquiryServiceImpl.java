/*
 * @(#) TblResidentInquiryServiceImpl  2017-07-24 10:06:25
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
package com.tiuweb.family.inquiry.service.impl;

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
import com.tiuweb.family.inquiry.domain.TblResidentInquiry;
import com.tiuweb.family.inquiry.domain.TblResidentInquiryEvaluate;
import com.tiuweb.family.inquiry.domain.TblResidentInquiryReply;
import com.tiuweb.family.inquiry.mapper.TblResidentInquiryMapper;
import com.tiuweb.family.inquiry.service.ITblResidentInquiryEvaluateService;
import com.tiuweb.family.inquiry.service.ITblResidentInquiryReplyService;
import com.tiuweb.family.inquiry.service.ITblResidentInquiryService;

/**
 * 
 * <p>
 * Title: 居民问诊信息
 * </p>
 * <p>
 * Description: TODO 居民问诊信息Impl层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-24 10:06:25
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblResidentInquiryServiceImpl extends BaseServiceImpl<TblResidentInquiry>
		implements ITblResidentInquiryService {

	@Autowired
	private TblResidentInquiryMapper tblResidentInquiryMapper;
	@Autowired
	private ITblResidentInquiryService tblResidentInquiryService;
	@Autowired
	private ITblResidentInquiryReplyService tblResidentInquiryReplyService;
	@Autowired
	private ITblResidentInquiryEvaluateService tblResidentInquiryEvaluateService;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblResidentInquiry, String> getMapper() {
		return tblResidentInquiryMapper;
	}

	@Override
	@Transactional(readOnly = true)
	@SystemServiceLog(description = "问诊详情查询")
	public TblResidentInquiry queryInquiryDetails(String id) throws ServiceRunException {

		if (StringUtils.isNullOrEmpty(id)) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "问诊记录主键不能为空");
		}

		TblResidentInquiry record = tblResidentInquiryService.findByPrimaryKey(id);

		// 问诊回复
		TblResidentInquiryReply tblResidentInquiryReply = new TblResidentInquiryReply();
		tblResidentInquiryReply.setResidentinquiryidentity(record.getId());
		tblResidentInquiryReply.setDeletesign(false);
		List<TblResidentInquiryReply> replys = tblResidentInquiryReplyService.findByCondition(tblResidentInquiryReply);
		record.setReplys(replys);

		// 问诊评价
		TblResidentInquiryEvaluate tblResidentInquiryEvaluate = new TblResidentInquiryEvaluate();
		tblResidentInquiryEvaluate.setInquiryidentity(record.getId());
		tblResidentInquiryEvaluate.setDeletesign(false);
		List<TblResidentInquiryEvaluate> evaluates = tblResidentInquiryEvaluateService
				.findByCondition(tblResidentInquiryEvaluate);
		record.setEvaluates(evaluates);

		return record;
	}

	@Override
	@SystemServiceLog(description = "问诊回复入库")
	public int saveInquiryInfo(TblResidentInquiryReply record) throws ServiceRunException {

		if (StringUtils.isNullOrEmpty(record.getResidentinquiryidentity())) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "请指定问诊内容");
		}

		// 回复内容入库
		tblResidentInquiryReplyService.insert(record);

		// 更改居民问诊信息中的问诊状态
		TblResidentInquiry tblResidentInquiry = 
		      tblResidentInquiryService.findByPrimaryKey(record.getResidentinquiryidentity());

		if (record.getComein() == true)
		{
		   tblResidentInquiry.setUpdateuser(record.getCreateuser());
		   tblResidentInquiry.setUpdatedate(record.getCreatedate());
		   // 问诊状态:0未回复,1回复中,2已解决,3已评价 || 此处是医护端,解决与否应该由居民在评价的时候决定,详见表
	      // tbl_resident_inquiry_evaluate=>is_resolve
	      tblResidentInquiry.setInquirystatus((short) 1);
		}
		
		return tblResidentInquiryService.update(tblResidentInquiry);
	}

} /// :~
