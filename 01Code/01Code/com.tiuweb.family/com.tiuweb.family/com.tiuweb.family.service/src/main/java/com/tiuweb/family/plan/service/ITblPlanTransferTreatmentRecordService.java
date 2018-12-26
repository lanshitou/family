/*
 * @(#) ITblPlanTransferTreatmentRecordService  2017-08-07 10:38:53
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
package com.tiuweb.family.plan.service;

import com.tiuweb.climb.framework.commons.service.IBaseService;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.family.plan.domain.TblPlanTransferTreatmentRecord;

/**
 * 
 * <p>
 * Title: 执行计划转诊记录
 * </p>
 * <p>
 * Description: TODO 执行计划转诊记录Service层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-07 10:38:53
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface ITblPlanTransferTreatmentRecordService extends IBaseService<TblPlanTransferTreatmentRecord> {
	/**
	 * 新增转诊记录
	 * 
	 * @param tblPlanTransferTreatmentRecord
	 * @return
	 * @throws ServiceRunException
	 */
	int insertTransfer(TblPlanTransferTreatmentRecord tblPlanTransferTreatmentRecord) throws ServiceRunException;
}
