/*
 * @(#) ITblMedicalTeamServiceService  2017-07-17 12:52:58
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
package com.tiuweb.family.items.service;

import com.tiuweb.climb.framework.commons.service.IBaseService;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.family.items.domain.TblMedicalTeamService;

/**
 * 
 * <p>
 * Title: 团队提供的签约服务汇总信息
 * </p>
 * <p>
 * Description: TODO 团队提供的签约服务汇总信息Service层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 12:52:58
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface ITblMedicalTeamServiceService extends IBaseService<TblMedicalTeamService> {

	/**
	 * 根据团队插入团队服务
	 * 
	 * @param teamItem
	 * @return
	 * @throws ServiceRunException
	 */
	int insertItemByTeam(TblMedicalTeamService teamItem) throws ServiceRunException;

	/**
	 * 删除医疗团队服务项
	 * 
	 * @param teamItem
	 * @return
	 * @throws ServiceRunException
	 */
	int deleteItem(TblMedicalTeamService teamItem) throws ServiceRunException;
}
