/*
 * @(#) TblTreatmentRecordsEvaluateServiceImpl  2017-08-09 10:36:26
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
package com.tiuweb.family.plan.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.config.redis.service.IRedisService;
import com.tiuweb.family.plan.domain.TblTreatmentRecordsEvaluate;
import com.tiuweb.family.plan.mapper.TblTreatmentRecordsEvaluateMapper;
import com.tiuweb.family.plan.service.ITblTreatmentRecordsEvaluateService;

/**
 * 
 * <p>Title: 医疗服务计划服务记录评价</p>
 * <p>Description: TODO 医疗服务计划服务记录评价Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-09 10:36:26
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
@Service
@Transactional
public class TblTreatmentRecordsEvaluateServiceImpl extends BaseServiceImpl<TblTreatmentRecordsEvaluate> implements ITblTreatmentRecordsEvaluateService {

	@Autowired
	private TblTreatmentRecordsEvaluateMapper tblTreatmentRecordsEvaluateMapper;
	
	/*需要redis服务请打开*/
	//@Autowired
	//private IRedisService redisService;
	
	@Override
	protected BaseMapper<TblTreatmentRecordsEvaluate, String> getMapper() {
		return tblTreatmentRecordsEvaluateMapper;
	}
}
