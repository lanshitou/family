/*
 * @(#) ITblResidentFeedbackService  2017-07-27 16:51:06
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
package com.tiuweb.family.feedback.service;

import com.tiuweb.climb.framework.commons.service.IBaseService;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.family.feedback.domain.TblResidentFeedback;
import com.tiuweb.family.feedback.domain.TblResidentFeedbackEvaluate;
  
/**
 * 
 * <p>Title: 投诉建议信息</p>
 * <p>Description: TODO 投诉建议信息Service层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-27 16:51:06
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public interface ITblResidentFeedbackService extends IBaseService<TblResidentFeedback> 
{
	/**
	 * 投诉建议详情查询
	 * param id
	 * return TblResidentFeedback
	 * @throws ServiceRunException
	 */
   TblResidentFeedback queryFeedbackDetails(String id) throws ServiceRunException;
   
   /**
    * 投诉建议评价
    * param record
    * return 
    * @throws ServiceRunException
    */
   int evaluateToFeedback(TblResidentFeedback record) throws ServiceRunException;
}
