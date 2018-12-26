/*
 * @(#) ITblResidentContactService  2017-07-13 14:20:20
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
package com.tiuweb.family.contact.service;

import java.util.List;

import com.tiuweb.climb.framework.commons.service.IBaseService;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.family.contact.domain.TblResidentContact;
import com.tiuweb.family.contact.domain.TblResidentContactService;
import com.tiuweb.family.contact.domain.TblServicesCustomer;
import com.tiuweb.family.plan.domain.TblResidentContactServicePlan;

/**
 * 
 * <p>
 * Title: 居民签约协议
 * </p>
 * <p>
 * Description: TODO 居民签约协议Service层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-13 14:20:20
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface ITblResidentContactService extends IBaseService<TblResidentContact> {
	/**
	 * 根据服務对象身份证号查询签约协议
	 * 
	 * @param tblServicesCustomer
	 * @return
	 */
	List<TblResidentContact> findContactByCustomer(TblServicesCustomer tblServicesCustomer);

	/**
	 * 在一个事务里完成,居民签约协议添加
	 * 
	 * @param record
	 * @param loginName
	 * @return
	 * @throws ServiceRunException
	 */
	public int insertContact(TblResidentContact record, String loginName) throws ServiceRunException;

	/**
	 * 获取去签约服务详情
	 * 
	 * @param record
	 * @return
	 * @throws ServiceRunException
	 */
	TblResidentContact findDetailByKey(TblResidentContact record) throws ServiceRunException;

	/**
	 * 根据服务对象身份证号查询居民预约服务信息
	 * 
	 * @param tblServicesCustomer
	 * @return
	 * @throws ServiceRunException
	 */
	TblResidentContact findAppointmentByCon(TblServicesCustomer tblServicesCustomer) throws ServiceRunException;

	/**
	 * 解除居民签约协议
	 * 
	 * @param tblResidentContactServicePlan
	 * @return
	 */
	int relieve(TblResidentContact tblResidentContact) throws ServiceRunException;

	/**
	 * 审批居民签约协议数据
	 * 
	 * @param tblResidentContact
	 * @param loginName
	 * @return
	 * @throws ServiceRunException
	 */
	int approval(TblResidentContact tblResidentContact, String loginName) throws ServiceRunException;

	/**
	 * 居民签约协议续签
	 * 
	 * @param tblResidentContact
	 * @param loginName
	 * @return
	 * @throws ServiceRunException
	 */
	int renew(TblResidentContact tblResidentContact, String loginName) throws ServiceRunException;

	/**
	 * 居民签约服务预约
	 * 
	 * @param plan
	 * @param loginName
	 * @return
	 * @throws ServiceRunException
	 */
	int subscribe(TblResidentContactServicePlan plan) throws ServiceRunException;
}
