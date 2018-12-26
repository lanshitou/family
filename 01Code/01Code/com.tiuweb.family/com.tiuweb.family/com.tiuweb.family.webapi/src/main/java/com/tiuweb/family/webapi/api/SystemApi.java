/*
 * @(#) TblServcieItemApi  2017-07-17 11:01:14
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiuweb.climb.framework.commons.util.MessModel;
import com.tiuweb.climb.framework.commons.util.SearchParams;
import com.tiuweb.climb.framework.config.logs.SystemApiLog;
import com.tiuweb.family.common.service.ISystemInfoService;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 字典信息
 * </p>
 * <p>
 * Description: TODO 字典信息API层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-17 11:01:14
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "SystemInfo", description = "系统操作相关信息")
@RestController
@RequestMapping(value = "/system")
public class SystemApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ISystemInfoService<?> systemInfo;

	@RequestMapping(value = "/update/pwd", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "修改密码")
	@ApiOperation(value = "修改密码", notes = "修改密码")
	public ResponseEntity<?> updatePassword(@RequestBody SearchParams updateInfo) {
		MessModel messModel = new MessModel();

		int result = systemInfo.updatePwd(updateInfo.getSearchParams(), getToken());

		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("更新密码成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/logout", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "已登录用户退出")
	@ApiOperation(value = "已登录用户退出", notes = "已登录用户退出")
	public ResponseEntity<?> logout() {
		MessModel messModel = new MessModel();

		int result = systemInfo.logout(getToken());

		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("退出成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}
}
