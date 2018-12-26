/*
 * @(#) TblPlanTransferTreatmentRecordServiceImpl  2017-08-07 10:38:53
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

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.redis.service.IRedisService;
import com.tiuweb.family.plan.domain.TblPlanTransferTreatmentRecord;
import com.tiuweb.family.plan.mapper.TblPlanTransferTreatmentRecordMapper;
import com.tiuweb.family.plan.service.ITblPlanTransferTreatmentRecordService;

/**
 * 
 * <p>
 * Title: 执行计划转诊记录
 * </p>
 * <p>
 * Description: TODO 执行计划转诊记录Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-07 10:38:53
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblPlanTransferTreatmentRecordServiceImpl extends BaseServiceImpl<TblPlanTransferTreatmentRecord>
		implements ITblPlanTransferTreatmentRecordService {

	@Autowired
	private TblPlanTransferTreatmentRecordMapper tblPlanTransferTreatmentRecordMapper;

	/* 需要redis服务请打开 */
	@Autowired
	private IRedisService redisService;

	@Override
	protected BaseMapper<TblPlanTransferTreatmentRecord, String> getMapper() {
		return tblPlanTransferTreatmentRecordMapper;
	}

	@Override
	public int insertTransfer(TblPlanTransferTreatmentRecord tblPlanTransferTreatmentRecord)
			throws ServiceRunException {

		// 根据当前登录用户id查询详细信息
		String jsonRedis = redisService.get(tblPlanTransferTreatmentRecord.getCreateuser());
		if (StringUtils.isNullOrEmpty(jsonRedis)) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "当前登录用户信息异常");
		}

		Map target = JSON.parseObject(jsonRedis, Map.class);
		Map user = (Map) target.get("user");
		if (null == user) {
			user = new HashMap<String, String>();
		}
		String orgName = String.valueOf(user.get("orgname"));
		String orgId = String.valueOf(user.get("orgid"));
		tblPlanTransferTreatmentRecord.setCurrentorgidentity(orgId);
		tblPlanTransferTreatmentRecord.setCurrentorgname(orgName);
		return insert(tblPlanTransferTreatmentRecord);
	}
}
