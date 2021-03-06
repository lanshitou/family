/*
 * @(#) TblServicesCustomerMapper  2017-07-17 18:11:09
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
package com.tiuweb.family.contact.mapper;

import java.util.List;
import java.util.Map;

import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.family.contact.domain.TblServicesCustomer;

/**
 * 
 * <p>
 * Title: 服务对象信息
 * </p>
 * <p>
 * Description: TODO 服务对象信息Mapper层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 18:11:09
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface TblServicesCustomerMapper extends BaseMapper<TblServicesCustomer, String> {
	/**
	 * 根据签约查詢服务对象
	 * 
	 * @param tblServicesCustomer
	 * @return
	 */
	List<TblServicesCustomer> findCustomerByContact(Map<String, Object> tblServicesCustomer);

}