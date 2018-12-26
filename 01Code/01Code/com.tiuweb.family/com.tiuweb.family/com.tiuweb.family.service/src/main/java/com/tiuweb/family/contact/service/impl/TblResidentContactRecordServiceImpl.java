/*
 * @(#) TblResidentContactRecordServiceImpl  2017-07-13 15:03:51
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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.family.contact.domain.TblResidentContactRecord;
import com.tiuweb.family.contact.mapper.TblResidentContactRecordMapper;
import com.tiuweb.family.contact.service.ITblResidentContactRecordService;

/**
 * 
 * <p>
 * Title: 签约协议历史记录
 * </p>
 * <p>
 * Description: TODO 签约协议历史记录Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-13 15:03:51
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblResidentContactRecordServiceImpl extends BaseServiceImpl<TblResidentContactRecord>
		implements ITblResidentContactRecordService {

	@Autowired
	private TblResidentContactRecordMapper tblResidentContactRecordMapper;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblResidentContactRecord, String> getMapper() {
		return tblResidentContactRecordMapper;
	}
}
