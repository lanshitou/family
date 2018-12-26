/*
 * @(#) TblResidentFeedbackServiceImpl  2017-07-27 16:51:06
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
package com.tiuweb.family.feedback.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.logs.SystemServiceLog;
import com.tiuweb.family.feedback.domain.TblResidentFeedback;
import com.tiuweb.family.feedback.domain.TblResidentFeedbackEvaluate;
import com.tiuweb.family.feedback.mapper.TblResidentFeedbackMapper;
import com.tiuweb.family.feedback.service.ITblResidentFeedbackEvaluateService;
import com.tiuweb.family.feedback.service.ITblResidentFeedbackService;

/**
 * 
 * <p>Title: 投诉建议信息</p>
 * <p>Description: TODO 投诉建议信息Impl层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-27 16:51:06
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
@Service
@Transactional
public class TblResidentFeedbackServiceImpl extends BaseServiceImpl<TblResidentFeedback> implements ITblResidentFeedbackService {

	@Autowired
	private TblResidentFeedbackMapper tblResidentFeedbackMapper;
	@Autowired
	private ITblResidentFeedbackService tblResidentFeedbackService;
	@Autowired
	private ITblResidentFeedbackEvaluateService tblResidentFeedbackEvaluateService;
	
	/*需要redis服务请打开*/
	//@Autowired
	//private IRedisService redisService;
	
	@Override
	protected BaseMapper<TblResidentFeedback, String> getMapper() {
		return tblResidentFeedbackMapper;
	}
	
	@Override
   @Transactional(readOnly = true)
   @SystemServiceLog(description = "投诉建议详情查询")
	public TblResidentFeedback queryFeedbackDetails(String id) throws ServiceRunException
	{
	   if (StringUtils.isNullOrEmpty(id)) {
         throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "投诉建议主键不能为空");
      }
	   
	   TblResidentFeedback record = tblResidentFeedbackService.findByPrimaryKey(id);
	   if (record == null) {
         throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "查询无记录");
      }
	   // 评价信息
	   TblResidentFeedbackEvaluate tblResidentFeedbackEvaluate = new TblResidentFeedbackEvaluate();
	   tblResidentFeedbackEvaluate.setFeedbackidentity(record.getId());
	   tblResidentFeedbackEvaluate.setDeletesign(false);
	   List<TblResidentFeedbackEvaluate> evaluates = tblResidentFeedbackEvaluateService.findByCondition(tblResidentFeedbackEvaluate);
	   record.setEvaluates(evaluates);
	   
	   return record;
	}
	
	@Override
	@SystemServiceLog(description = "投诉建议评价")
	public int evaluateToFeedback(TblResidentFeedback record) throws ServiceRunException
	{
	   if (record == null) {
         throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "投诉建议信息不存在");
      }
	   
	   List<TblResidentFeedbackEvaluate> evaluates = record.getEvaluates();
	   if (null == evaluates || evaluates.isEmpty()) 
	   {
         throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "评价信息无效");
      }
	   
	   TblResidentFeedback tblResidentFeedback = tblResidentFeedbackService.findByPrimaryKey(record.getId());
	   for (TblResidentFeedbackEvaluate evaluate: evaluates)
	   {
	      // 诉投建议信息外键
	      evaluate.setFeedbackidentity(record.getId());
	      // 居民账户外键?
	      // 居民账号
	      evaluate.setLoginname(tblResidentFeedback.getCreateuser());
	      // 删除标记
	      evaluate.setDeletesign(false);
	      // 更新日期
	      evaluate.setCreatedate(record.getUpdatedate());
	      
	      // 居民评价
	      if (evaluate.getIsauto() == 0 || evaluate.getIsauto() == null)
	      {
	         evaluate.setOrgid(tblResidentFeedback.getOrgid());
	         evaluate.setCreateuser(tblResidentFeedback.getCreateuser());
	      }
	      // 系统自动处理
	      else if (evaluate.getIsauto() == 1)
	      {
	         evaluate.setOrgid("00000001");
	         evaluate.setCreateuser("XXXXXXXX");
	         record.setUpdateuser("XXXXXXXX");
	         // 居民账户外键
	         evaluate.setResdientidentity("xxxxxx");
	         // 是否解决
	         evaluate.setIsresolve(true);
	         // 评价内容
	         evaluate.setReply("非常好的一次反馈！");
	         // 响应满意度
	         evaluate.setResponserate((short)10);
	         // 处理结果满意度
	         evaluate.setResultrate((short)10);
	      }
	   }
	   
	   // 评价信息入库
	   tblResidentFeedbackEvaluateService.insertBatch(evaluates);
	   // 投诉建议信息status修改
	   record.setStatus((short)3);
	   return tblResidentFeedbackService.update(record);
	}
}
