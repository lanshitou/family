/*
 * @(#) ITblNoticeService  2017-07-26 15:59:14
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
package com.tiuweb.family.notice.service;

import com.tiuweb.climb.framework.commons.service.IBaseService;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.family.notice.domain.TblNotice;

/**
 * 
 * <p>
 * Title: 通知消息
 * </p>
 * <p>
 * Description: TODO 通知消息Service层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-26 15:59:14
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface ITblNoticeService extends IBaseService<TblNotice> {
	/**
	 * 通知公告详情查询
	 * 
	 * @param record
	 * @return
	 * @throws ServiceRunException
	 */
	TblNotice queryNoticeDetails(String id) throws ServiceRunException;

	/**
	 * 新增通知公告
	 * 
	 * @param record
	 * @param loginUser
	 * @return
	 * @throws ServiceRunException
	 */
	int insertNotice(TblNotice record, String loginUser) throws ServiceRunException;

	/**
	 * 通知公告撤回
	 * 
	 * @param record
	 * @return
	 * @throws ServiceRunException
	 */
	int revokeNotice(TblNotice record) throws ServiceRunException;

	/**
	 * 再次发布通知公告
	 * 
	 * @param record
	 * @return
	 * @throws ServiceRunException
	 */
	int resendNotice(TblNotice record, String loginUser) throws ServiceRunException;

	/**
	 * 带条件判断的通知公告修改
	 * 
	 * @param record
	 * @return
	 * @throws ServiceRunException
	 */
	int newUpdate(TblNotice record) throws ServiceRunException;
}
