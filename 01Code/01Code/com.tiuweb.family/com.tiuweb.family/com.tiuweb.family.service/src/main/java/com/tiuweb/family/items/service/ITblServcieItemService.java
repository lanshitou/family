/*
 * @(#) ITblServcieItemService  2017-07-17 11:10:29
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

import java.util.List;
import java.util.Map;

import com.tiuweb.climb.framework.commons.service.IBaseService;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.family.items.domain.TblServcieItem;

/**
 * 
 * <p>
 * Title: 签约服务汇总信息
 * </p>
 * <p>
 * Description: TODO 签约服务汇总信息Service层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 11:10:29
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface ITblServcieItemService extends IBaseService<TblServcieItem> {

	/**
	 * 根据团队主键查询提供的全部签约服务
	 * 
	 * @param params
	 * @return
	 */
	List<TblServcieItem> findByTeamsKey(Map<String, Object> params);

	/**
	 * 添加服务项
	 * 
	 * @param tblServcieItem
	 * @return
	 */
	int inserItemsWithRole(TblServcieItem tblServcieItem) throws ServiceRunException;

	/**
	 * 服務項詳情查询
	 * 
	 * @param tblServcieItem
	 * @return
	 * @throws ServiceRunException
	 */
	TblServcieItem findDetailByPrimaryKey(TblServcieItem tblServcieItem) throws ServiceRunException;

	/**
	 * 更新服务项目
	 * 
	 * @param tblServcieItem
	 * @return
	 * @throws ServiceRunException
	 */
	public int updateItemsWithRole(TblServcieItem tblServcieItem) throws ServiceRunException;
}
