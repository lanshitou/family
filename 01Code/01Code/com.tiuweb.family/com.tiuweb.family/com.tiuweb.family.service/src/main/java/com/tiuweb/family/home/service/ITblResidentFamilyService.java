/*
 * @(#) ITblResidentFamilyService  2017-07-19 11:41:23
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
package com.tiuweb.family.home.service;

import com.tiuweb.climb.framework.commons.service.IBaseService;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.family.home.domain.TblResidentFamily;

/**
 * 
 * <p>
 * Title: 居民家庭信息
 * </p>
 * <p>
 * Description: TODO 居民家庭信息Service层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-19 11:41:23
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface ITblResidentFamilyService extends IBaseService<TblResidentFamily> {

	/**
	 * 更新家庭及其成员信息
	 * 
	 * @param tblResidentFamily
	 * @return
	 * @throws ServiceRunException
	 */
	int updateWithInsertMember(TblResidentFamily tblResidentFamily) throws ServiceRunException;;

	/**
	 * 添加家庭及其成员信息
	 * 
	 * @param tblResidentFamily
	 * @return
	 * @throws ServiceRunException
	 */
	int insertWithMembers(TblResidentFamily tblResidentFamily) throws ServiceRunException;;

	/**
	 * 家庭成员信息更新
	 * 
	 * @param tblResidentFamily
	 * @return
	 * @throws ServiceRunException
	 */
	int updateWithMembers(TblResidentFamily tblResidentFamily) throws ServiceRunException;

	/**
	 * 根据主键查询家庭和成员信息
	 * 
	 * @param tblResidentFamily
	 * @return
	 * @throws ServiceRunException
	 */
	TblResidentFamily findDetailsByCondition(TblResidentFamily tblResidentFamily) throws ServiceRunException;
}
