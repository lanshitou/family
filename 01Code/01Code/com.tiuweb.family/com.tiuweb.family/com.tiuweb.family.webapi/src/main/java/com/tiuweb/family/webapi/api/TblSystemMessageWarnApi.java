/*
 * @(#) TblSystemMessageWarnApi  2017-08-10 11:35:00
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
import com.tiuweb.climb.framework.commons.util.SearchParams;
import com.tiuweb.climb.framework.config.logs.SystemApiLog;
import com.tiuweb.family.remind.domain.TblSystemMessageWarn;
import com.tiuweb.family.remind.service.ITblSystemMessageWarnService;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 系统消息提醒信息
 * </p>
 * <p>
 * Description: TODO 系统消息提醒信息API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-10 11:35:00
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblSystemMessageWarnApi", description = "系统消息提醒信息")
@RestController
@RequestMapping(value = "/remind")
public class TblSystemMessageWarnApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblSystemMessageWarnService tblSystemMessageWarnService;

	@RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询系统消息提醒信息")
	@ApiOperation(value = "分页查询系统消息提醒信息", notes = "分页查询系统消息提醒信息")
	public ResponseEntity<?> findPage(@RequestBody SearchParams params) {
		// 只查询当前登录用户的提醒
		params.getSearchParams().put("acceptuser", getUserId());
		return new ResponseEntity<>(tblSystemMessageWarnService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询系统消息提醒信息")
	@ApiOperation(value = "条件查询系统消息提醒信息", notes = "条件查询系统消息提醒信息")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblSystemMessageWarn tblSystemMessageWarn) {
		// 只查询当前登录用户的提醒
		tblSystemMessageWarn.setAcceptuser(getUserId());
		List<TblSystemMessageWarn> list = tblSystemMessageWarnService.findByCondition(tblSystemMessageWarn);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "删除系统消息提醒信息数据")
	@ApiOperation(value = "删除系统消息提醒信息数据", notes = "删除系统消息提醒信息数据")
	public ResponseEntity<?> delete(@RequestBody TblSystemMessageWarn tblSystemMessageWarn) {
		MessModel messModel = new MessModel();
		int result = tblSystemMessageWarnService.delete(tblSystemMessageWarn);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("删除成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

}
