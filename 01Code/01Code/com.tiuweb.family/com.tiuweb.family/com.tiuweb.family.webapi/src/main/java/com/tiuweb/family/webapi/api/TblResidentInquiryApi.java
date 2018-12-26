/*
 * @(#) TblResidentInquiryApi  2017-07-24 10:06:25
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
import com.tiuweb.family.inquiry.domain.TblResidentInquiry;
import com.tiuweb.family.inquiry.domain.TblResidentInquiryReply;
import com.tiuweb.family.inquiry.service.ITblResidentInquiryReplyService;
import com.tiuweb.family.inquiry.service.ITblResidentInquiryService;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 居民问诊信息
 * </p>
 * <p>
 * Description: TODO 居民问诊信息API层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-24 10:06:25
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblResidentInquiryApi", description = "居民问诊信息")
@RestController
@RequestMapping(value = "/inquiry")
public class TblResidentInquiryApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblResidentInquiryService tblResidentInquiryService;
	@Autowired
	private ITblResidentInquiryReplyService tblResidentInquiryReplyService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部居民问诊信息")
	@ApiOperation(value = "查询全部居民问诊信息", notes = "查询全部居民问诊信息")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(tblResidentInquiryService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询居民问诊信息")
	@ApiOperation(value = "分页查询居民问诊信息", notes = "分页查询居民问诊信息")
	public ResponseEntity<?> findPage(@RequestBody SearchParams params) {
		return new ResponseEntity<>(tblResidentInquiryService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "主键查询居民问诊信息")
	@ApiOperation(value = "主键查询居民问诊信息", notes = "主键查询居民问诊信息")
	public ResponseEntity<?> findByKey(@RequestBody TblResidentInquiry tblResidentInquiry) {
		// TblResidentInquiry result =
		// tblResidentInquiryService.findByPrimaryKey(tblResidentInquiry.getId());
		TblResidentInquiry result = tblResidentInquiryService.queryInquiryDetails(tblResidentInquiry.getId());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询居民问诊信息")
	@ApiOperation(value = "条件查询居民问诊信息", notes = "条件查询居民问诊信息")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblResidentInquiry tblResidentInquiry) {
		List<TblResidentInquiry> list = tblResidentInquiryService.findByCondition(tblResidentInquiry);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/insertInquiryReply", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "居民问诊回复信息新增")
	@ApiOperation(value = "居民问诊回复信息新增", notes = "居民问诊回复信息新增")
	public ResponseEntity<?> insertInquiryReply(@RequestBody TblResidentInquiryReply tblResidentInquiryReply) {
		MessModel messModel = new MessModel();
		tblResidentInquiryReply.setReplayidentity(getUserId());
		tblResidentInquiryReply.setReplayname(getLoginName());
		tblResidentInquiryReply.setOrgid(getOrgId());
		tblResidentInquiryReply.setCreateuser(getUserId());
		tblResidentInquiryReply.setCreatedate(new Date());
		tblResidentInquiryReply.setDeletesign(false);
		int result = tblResidentInquiryService.saveInquiryInfo(tblResidentInquiryReply);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("新增成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

} /// :~
