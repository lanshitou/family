/*
 * @(#) ITblResidentContactServicePlanService  2017-07-12 16:23:05
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
import com.tiuweb.family.plan.domain.TblResidentContactServicePlan;

/**
 * 
 * <p>
 * Title: 居民签约服务执行计划
 * </p>
 * <p>
 * Description: TODO 居民签约服务执行计划Service层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-12 16:23:05
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface ITblResidentContactServicePlanService extends IBaseService<TblResidentContactServicePlan> {

	/**
	 * 解除签约协议服务计划
	 * 
	 * @param tblResidentContactServicePlan
	 * @return
	 */
	int relieve(TblResidentContactServicePlan tblResidentContactServicePlan) throws ServiceRunException;;
}
