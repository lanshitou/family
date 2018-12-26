/*
 * @(#) TblSystemMessageCategoryApi  2017-08-10 11:32:20
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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiuweb.climb.framework.commons.api.BaseTokenApi;
import com.tiuweb.climb.framework.config.logs.SystemApiLog;
import com.tiuweb.family.remind.service.ITblSystemMessageCategoryService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
 
/**
 * 
 * <p>Title: 系统消息提醒分类</p>
 * <p>Description: TODO 系统消息提醒分类API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-10 11:32:20
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */   
@Api(value = "TblSystemMessageCategoryApi", description="系统消息提醒分类")
@RestController
@RequestMapping(value = "/msgcategory")
public class TblSystemMessageCategoryApi extends BaseTokenApi
{    
    protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    
    @Autowired
    private ITblSystemMessageCategoryService tblSystemMessageCategoryService;
    
    @RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @SystemApiLog(description = "查询全部系统消息提醒分类")
	@ApiOperation(value="查询全部系统消息提醒分类", notes="查询全部系统消息提醒分类")
    public ResponseEntity<?> findAll()
    {	
    	 return new ResponseEntity<>(tblSystemMessageCategoryService.findAll(), HttpStatus.OK);    
    }
	    
}
