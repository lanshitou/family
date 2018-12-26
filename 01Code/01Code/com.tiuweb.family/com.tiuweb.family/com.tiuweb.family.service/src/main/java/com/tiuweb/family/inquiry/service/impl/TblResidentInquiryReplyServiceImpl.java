/*
 * @(#) TblResidentInquiryReplyServiceImpl  2017-07-24 10:07:19
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.config.redis.service.IRedisService;
import com.tiuweb.family.inquiry.domain.TblResidentInquiryReply;
import com.tiuweb.family.inquiry.mapper.TblResidentInquiryReplyMapper;
import com.tiuweb.family.inquiry.service.ITblResidentInquiryReplyService;

/**
 * 
 * <p>Title: 居民问诊答复信息</p>
 * <p>Description: TODO 居民问诊答复信息Impl层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-24 10:07:19
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
@Service
@Transactional
public class TblResidentInquiryReplyServiceImpl extends BaseServiceImpl<TblResidentInquiryReply> implements ITblResidentInquiryReplyService {

	@Autowired
	private TblResidentInquiryReplyMapper tblResidentInquiryReplyMapper;
	
	/*需要redis服务请打开*/
	//@Autowired
	//private IRedisService redisService;
	
	@Override
	protected BaseMapper<TblResidentInquiryReply, String> getMapper() {
		return tblResidentInquiryReplyMapper;
	}
}
