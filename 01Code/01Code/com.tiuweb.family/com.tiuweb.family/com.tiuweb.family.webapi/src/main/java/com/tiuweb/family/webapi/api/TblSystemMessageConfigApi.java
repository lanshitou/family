/*
 * @(#) TblSystemMessageConfigApi  2017-08-10 11:50:46
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

import com.tiuweb.climb.framework.commons.util.MessModel;
import com.tiuweb.climb.framework.config.logs.SystemApiLog;
import com.tiuweb.family.remind.domain.TblSystemMessageConfig;
import com.tiuweb.family.remind.service.ITblSystemMessageConfigService;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 消息提醒设置
 * </p>
 * <p>
 * Description: TODO 消息提醒设置API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-10 11:50:46
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblSystemMessageConfigApi", description = "消息提醒设置")
@RestController
@RequestMapping(value = "/msgconfig")
public class TblSystemMessageConfigApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblSystemMessageConfigService tblSystemMessageConfigService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部消息提醒设置")
	@ApiOperation(value = "查询全部消息提醒设置", notes = "查询全部消息提醒设置")
	public ResponseEntity<?> findAll() {
		// 查询当前登录用户全部消息提醒设置
		TblSystemMessageConfig query = new TblSystemMessageConfig();
		query.setLoginidentity(getUserId());
		return new ResponseEntity<>(tblSystemMessageConfigService.findByCondition(query), HttpStatus.OK);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "保存(更新或者新增)消息提醒设置")
	@ApiOperation(value = "保存(更新或者新增)消息提醒设置", notes = "保存(更新或者新增)消息提醒设置")
	public ResponseEntity<?> save(@RequestBody List<TblSystemMessageConfig> records) {
		MessModel messModel = new MessModel();
		int result = tblSystemMessageConfigService.save(records, getUserId());
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("新增成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}
}
