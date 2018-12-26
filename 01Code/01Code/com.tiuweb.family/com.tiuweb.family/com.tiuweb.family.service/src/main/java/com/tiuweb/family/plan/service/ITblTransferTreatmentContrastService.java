/*
 * @(#) ITblTransferTreatmentContrastService  2017-08-07 10:53:30
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
import com.tiuweb.family.plan.domain.TblTransferTreatmentContrast;

/**
 * 
 * <p>
 * Title: 转诊服务对照关系信息
 * </p>
 * <p>
 * Description: TODO 转诊服务对照关系信息Service层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-07 10:53:30
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface ITblTransferTreatmentContrastService extends IBaseService<TblTransferTreatmentContrast> {

	/**
	 * 新增转诊对照关系
	 * 
	 * @param tblTransferTreatmentContrast
	 * @return
	 * @throws ServiceRunException
	 */
	int insertContras(TblTransferTreatmentContrast tblTransferTreatmentContrast) throws ServiceRunException;

	/**
	 * 更新转诊对照关系
	 * 
	 * @param tblTransferTreatmentContrast
	 * @return
	 * @throws ServiceRunException
	 */
	int updateContras(TblTransferTreatmentContrast tblTransferTreatmentContrast) throws ServiceRunException;
}
