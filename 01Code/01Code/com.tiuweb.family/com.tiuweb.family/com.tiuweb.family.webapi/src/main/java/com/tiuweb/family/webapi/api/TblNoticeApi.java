/*
 * @(#) TblNoticeApi  2017-07-26 15:59:14
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
import com.tiuweb.family.notice.domain.TblNotice;
import com.tiuweb.family.notice.service.ITblNoticeService;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 通知消息
 * </p>
 * <p>
 * Description: TODO 通知消息API层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-26 15:59:14
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblNoticeApi", description = "通知公告")
@RestController
@RequestMapping(value = "/notice")
public class TblNoticeApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblNoticeService tblNoticeService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部通知公告")
	@ApiOperation(value = "查询全部通知公告", notes = "查询全部通知公告")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(tblNoticeService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询通知公告")
	@ApiOperation(value = "分页查询通知公告", notes = "分页查询通知公告")
	public ResponseEntity<?> findPage(@RequestBody SearchParams params) {
		return new ResponseEntity<>(tblNoticeService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "主键查询通知公告")
	@ApiOperation(value = "主键查询通知公告", notes = "主键查询通知公告")
	public ResponseEntity<?> findByKey(@RequestBody TblNotice tblNotice) {
		// TblNotice result =
		// tblNoticeService.findByPrimaryKey(tblNotice.getId());
		TblNotice result = tblNoticeService.queryNoticeDetails(tblNotice.getId());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询通知公告")
	@ApiOperation(value = "条件查询通知公告", notes = "条件查询通知公告")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblNotice tblNotice) {
		List<TblNotice> list = tblNoticeService.findByCondition(tblNotice);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "新增通知公告")
	@ApiOperation(value = "新增通知公告", notes = "新增通知公告")
	public ResponseEntity<?> addNotice(@RequestBody TblNotice tblNotice) {
		MessModel messModel = new MessModel();
		tblNotice.setOrgid(getOrgId());
		tblNotice.setCreatedate(new Date());
		tblNotice.setCreateuser(getUserId());
		tblNotice.setDeletesign(false);
		tblNotice.setPublishorgname(getOrgName());

		// int result = tblNoticeService.insert(tblNotice);
		int result = tblNoticeService.insertNotice(tblNotice, getLoginName());
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("新增成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "更新通知公告数据")
	@ApiOperation(value = "更新通知公告数据", notes = "更新通知公告数据")
	public ResponseEntity<?> newUpdate(@RequestBody TblNotice tblNotice) {
		MessModel messModel = new MessModel();
		tblNotice.setUpdateuser(getUserId());
		tblNotice.setUpdatedate(new Date());
		// int result = tblNoticeService.update(tblNotice);
		int result = tblNoticeService.newUpdate(tblNotice);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("更新成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/revoke", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "撤回通知公告")
	@ApiOperation(value = "通知公告撤回", notes = "通知公告撤回")
	public ResponseEntity<?> updateNotice(@RequestBody TblNotice tblNotice) {
		MessModel messModel = new MessModel();
		tblNotice.setUpdateuser(getUserId());
		tblNotice.setUpdatedate(new Date());
		int result = tblNoticeService.revokeNotice(tblNotice);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("撤回成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/release", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "发布通知公告")
	@ApiOperation(value = "再次发布通知公告", notes = "再次发布通知公告")
	public ResponseEntity<?> resend(@RequestBody TblNotice tblNotice) {
		MessModel messModel = new MessModel();
		tblNotice.setUpdateuser(getUserId());
		tblNotice.setUpdatedate(new Date());
		int result = tblNoticeService.resendNotice(tblNotice, getLoginName());
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("再次发布通知公告成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}
}
