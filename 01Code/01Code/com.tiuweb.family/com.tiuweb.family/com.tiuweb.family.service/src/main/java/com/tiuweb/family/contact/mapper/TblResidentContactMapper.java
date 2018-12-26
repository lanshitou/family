/*
 * @(#) TblResidentContactMapper  2017-07-13 14:20:20
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

import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.family.contact.domain.TblResidentContact;
import com.tiuweb.family.contact.domain.TblServicesCustomer;

/**
 * 
 * <p>
 * Title: 居民签约协议
 * </p>
 * <p>
 * Description: TODO 居民签约协议Mapper层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-13 14:20:20
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface TblResidentContactMapper extends BaseMapper<TblResidentContact, String> {
	/**
	 * 根据服務对象身份证号查询签约协议
	 * 
	 * @param tblServicesCustomer
	 * @return
	 */
	List<TblResidentContact> findContactByCustomer(TblServicesCustomer tblServicesCustomer);

	/**
	 * 解除签约协议
	 * 
	 * @param tblResidentContactService
	 * @return
	 */
	int relieve(TblResidentContact tblResidentContact);
}