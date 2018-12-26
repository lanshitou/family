/*
 * @(#) ITblResidentHealthArchivesService  2017-07-17 09:42:37
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
import com.tiuweb.family.contact.domain.TblResidentHealthArchives;

/**
 * 
 * <p>
 * Title: 居民个人健康档案
 * </p>
 * <p>
 * Description: TODO 居民个人健康档案Service层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 09:42:37
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface ITblResidentHealthArchivesService extends IBaseService<TblResidentHealthArchives> {
	/**
	 * 帶家庭查询
	 * 
	 * @param tblResidentHealthArchives
	 * @return
	 */
	List<TblResidentHealthArchives> findAllMemberWithFamilyByCondition(
			TblResidentHealthArchives tblResidentHealthArchives);

	/**
	 * 帶协议查询
	 * 
	 * @param tblResidentHealthArchives
	 * @return
	 */
	List<TblResidentHealthArchives> findMemberWithContactByCondition(
			TblResidentHealthArchives tblResidentHealthArchives);

	/**
	 * 新增居民健康档案
	 * 
	 * @param record
	 * @return
	 * @throws ServiceRunException
	 */
	int insertArchive(TblResidentHealthArchives record) throws ServiceRunException;
}
