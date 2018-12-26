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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.family.contact.domain.VContactResident;
import com.tiuweb.family.contact.mapper.VContactResidentMapper;
import com.tiuweb.family.contact.service.IVContactResidentService;

/**
 * 
 * <p>
 * Title: 签约居民信息
 * </p>
 * <p>
 * Description: TODO 签约居民信息Impl层
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
public class VContactResidentServiceImpl extends BaseServiceImpl<VContactResident> implements IVContactResidentService {

	@Autowired
	private VContactResidentMapper contactResidentMapper;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<VContactResident, String> getMapper() {
		return contactResidentMapper;
	}
}
