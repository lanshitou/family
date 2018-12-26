/*
 * @(#) ITblSystemMessageConfigService  2017-08-10 11:50:46
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
package com.tiuweb.family.remind.service;

import java.util.List;

import com.tiuweb.climb.framework.commons.service.IBaseService;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.family.remind.domain.TblSystemMessageConfig;

/**
 * 
 * <p>
 * Title: 消息提醒设置
 * </p>
 * <p>
 * Description: TODO 消息提醒设置Service层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-10 11:50:46
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface ITblSystemMessageConfigService extends IBaseService<TblSystemMessageConfig> {

	/**
	 * 保存消息提醒设置
	 * 
	 * @param record
	 * @return
	 * @throws ServiceRunException
	 */
	int save(List<TblSystemMessageConfig> records,String userId) throws ServiceRunException;
}
