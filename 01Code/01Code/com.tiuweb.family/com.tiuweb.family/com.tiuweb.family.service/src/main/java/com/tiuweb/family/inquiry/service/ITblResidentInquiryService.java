/*
 * @(#) ITblResidentInquiryService  2017-07-24 10:06:25
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
package com.tiuweb.family.inquiry.service;

import com.tiuweb.climb.framework.commons.service.IBaseService;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.family.inquiry.domain.TblResidentInquiry;
import com.tiuweb.family.inquiry.domain.TblResidentInquiryReply;
  
/**
 * 
 * <p>Title: 居民问诊信息</p>
 * <p>Description: TODO 居民问诊信息Service层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-24 10:06:25
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public interface ITblResidentInquiryService extends IBaseService<TblResidentInquiry> 
{
   /**
    * 问诊详情查询
    * 
    * @param record
    * @return
    * @throws ServiceRunException
    */
   TblResidentInquiry queryInquiryDetails(String id) throws ServiceRunException;
   
   /**
    * 问诊答复信息入库
    * @param record
    * @throws ServiceRunException
    */
   int saveInquiryInfo(TblResidentInquiryReply record) throws ServiceRunException;
}
