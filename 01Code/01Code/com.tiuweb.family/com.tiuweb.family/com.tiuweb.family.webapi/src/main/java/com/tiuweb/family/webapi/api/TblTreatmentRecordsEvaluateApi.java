/*
 * @(#) TblTreatmentRecordsEvaluateApi  2017-08-09 10:36:26
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

import com.tiuweb.climb.framework.commons.api.BaseTokenApi;
import com.tiuweb.climb.framework.config.logs.SystemApiLog;
import com.tiuweb.family.plan.domain.TblTreatmentRecordsEvaluate;
import com.tiuweb.family.plan.service.ITblTreatmentRecordsEvaluateService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
/**
 * 
 * <p>Title: 医疗服务计划服务记录评价</p>
 * <p>Description: TODO 医疗服务计划服务记录评价API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-09 10:36:26
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */   
@Api(value = "TblTreatmentRecordsEvaluateApi", description="医疗服务计划服务记录评价")
@RestController
@RequestMapping(value = "/evaluate")
public class TblTreatmentRecordsEvaluateApi extends BaseTokenApi
{    
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @Autowired
    private ITblTreatmentRecordsEvaluateService tblTreatmentRecordsEvaluateService;
    
    @RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @SystemApiLog(description = "主键查询医疗服务计划服务记录评价")
	@ApiOperation(value="主键查询医疗服务计划服务记录评价", notes="主键查询医疗服务计划服务记录评价")
    public ResponseEntity<?> findByKey(@RequestBody TblTreatmentRecordsEvaluate tblTreatmentRecordsEvaluate)
    {	    	    
    	TblTreatmentRecordsEvaluate result = tblTreatmentRecordsEvaluateService.findByPrimaryKey(tblTreatmentRecordsEvaluate.getId());
    	return new ResponseEntity<>(result, HttpStatus.OK);	        	
    }
    
    @RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @SystemApiLog(description = "条件查询医疗服务计划服务记录评价")
	@ApiOperation(value="条件查询医疗服务计划服务记录评价", notes="条件查询医疗服务计划服务记录评价")
    public ResponseEntity<?> findInfoByCon(@RequestBody TblTreatmentRecordsEvaluate tblTreatmentRecordsEvaluate)
    {	    		    
    	List<TblTreatmentRecordsEvaluate> list = tblTreatmentRecordsEvaluateService.findByCondition(tblTreatmentRecordsEvaluate);
    	return new ResponseEntity<>(list, HttpStatus.OK);	  
    }
    
}
