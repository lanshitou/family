/*
 * @(#) TblResidentFeedbackApi  2017-07-27 16:51:06
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
package com.tiuweb.family.webapi.api;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiuweb.climb.framework.commons.util.MessModel;
import com.tiuweb.climb.framework.commons.util.SearchParams;
import com.tiuweb.climb.framework.config.logs.SystemApiLog;
import com.tiuweb.family.feedback.domain.TblResidentFeedback;
import com.tiuweb.family.feedback.service.ITblResidentFeedbackService;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
/**
 * 
 * <p>Title: 投诉建议信息</p>
 * <p>Description: TODO 投诉建议信息API层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-27 16:51:06
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */   
@Api(value = "TblResidentFeedbackApi", description="投诉建议信息")
@RestController
@RequestMapping(value = "/feedback")
public class TblResidentFeedbackApi extends BaseApi
{    
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @Autowired
    private ITblResidentFeedbackService tblResidentFeedbackService;
    
    @RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @SystemApiLog(description = "查询全部投诉建议信息")
	@ApiOperation(value="查询全部投诉建议信息", notes="查询全部投诉建议信息")
    public ResponseEntity<?> findAll()
    {	
    	 return new ResponseEntity<>(tblResidentFeedbackService.findAll(), HttpStatus.OK);    
    }
    
    @RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @SystemApiLog(description = "分页查询投诉建议信息")
	@ApiOperation(value="分页查询投诉建议信息", notes="分页查询投诉建议信息")
    public ResponseEntity<?> findPage(@RequestBody SearchParams params)
    {	    	  	    
    	return new ResponseEntity<>(tblResidentFeedbackService.findPageByCondition(params), HttpStatus.OK);    	    
    }
    
    @RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @SystemApiLog(description = "主键查询投诉建议信息")
	@ApiOperation(value="主键查询投诉建议信息", notes="主键查询投诉建议信息")
    public ResponseEntity<?> findByKey(@RequestBody TblResidentFeedback tblResidentFeedback)
    {	    	    
//    	TblResidentFeedback result = tblResidentFeedbackService.findByPrimaryKey(tblResidentFeedback.getId());
       TblResidentFeedback result = tblResidentFeedbackService.queryFeedbackDetails(tblResidentFeedback.getId());
    	return new ResponseEntity<>(result, HttpStatus.OK);	        	
    }
    
    @RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @SystemApiLog(description = "条件查询投诉建议信息")
	@ApiOperation(value="条件查询投诉建议信息", notes="条件查询投诉建议信息")
    public ResponseEntity<?> findInfoByCon(@RequestBody TblResidentFeedback tblResidentFeedback)
    {	    		    
    	List<TblResidentFeedback> list = tblResidentFeedbackService.findByCondition(tblResidentFeedback);
    	return new ResponseEntity<>(list, HttpStatus.OK);	  
    }
    
    @RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @SystemApiLog(description = "新增投诉建议信息数据")
	@ApiOperation(value="新增投诉建议信息", notes="新增投诉建议信息")
    public ResponseEntity<?> insert(@RequestBody TblResidentFeedback tblResidentFeedback)
    {	
    	MessModel messModel = new MessModel();    
    	tblResidentFeedback.setDeletesign(false);
    	tblResidentFeedback.setStatus((short)0);
    	tblResidentFeedback.setOrgid(getOrgId());
    	// 公众端接入用户名
    	tblResidentFeedback.setCreateuser(getLoginName());
    	tblResidentFeedback.setCreatedate(new Date());
	    int result = tblResidentFeedbackService.insert(tblResidentFeedback);
	    // 判断返回结果
	    if (result > 0)
	    {
		    messModel.setMessage("新增成功");
		    return new ResponseEntity<>(messModel, HttpStatus.OK);
	    }	       	
    	return null;
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @SystemApiLog(description = "更新投诉建议信息数据")
	@ApiOperation(value="更新投诉建议信息数据", notes="更新投诉建议信息数据")
    public ResponseEntity<?> update(@RequestBody TblResidentFeedback tblResidentFeedback)
    {	
    	MessModel messModel = new MessModel();    
    	tblResidentFeedback.setUpdateuser(getUserId());
    	tblResidentFeedback.setUpdatedate(new Date());
    	tblResidentFeedback.setReplyidentify(getUserId());
    	tblResidentFeedback.setReplydate(tblResidentFeedback.getUpdatedate());
    	// 状态变成1回复中
    	tblResidentFeedback.setStatus((short)1);
	    int result = tblResidentFeedbackService.update(tblResidentFeedback);	    
	    // 判断返回结果
	    if (result > 0)
	    {
		    messModel.setMessage("更新成功");
		    return new ResponseEntity<>(messModel, HttpStatus.OK);
	    }
    	return null;
    }
    
    @RequestMapping(value = "/evaluate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @SystemApiLog(description = "投诉建议评价")
	 @ApiOperation(value="投诉建议评价", notes="投诉建议评价")
    public ResponseEntity<?> evaluate(@RequestBody TblResidentFeedback tblResidentFeedback)
    {	
    	MessModel messModel = new MessModel();   
    	tblResidentFeedback.setUpdateuser(getLoginName());
    	tblResidentFeedback.setUpdatedate(new Date());
//	    int result = tblResidentFeedbackService.delete(tblResidentFeedback);    
    	int result = tblResidentFeedbackService.evaluateToFeedback(tblResidentFeedback);
	    // 判断返回结果
	    if (result > 0)
	    {
		    messModel.setMessage("评价成功");
		    return new ResponseEntity<>(messModel, HttpStatus.OK);
	    }	    
    	return null;
    }
    
}
