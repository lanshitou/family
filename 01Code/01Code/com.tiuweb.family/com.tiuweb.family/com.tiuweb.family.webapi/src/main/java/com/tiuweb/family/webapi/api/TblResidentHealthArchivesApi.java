/*
 * @(#) TblResidentHealthArchivesApi  2017-07-17 09:42:37
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
import com.tiuweb.family.contact.domain.TblResidentHealthArchives;
import com.tiuweb.family.contact.service.ITblResidentHealthArchivesService;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 居民个人健康档案
 * </p>
 * <p>
 * Description: TODO 居民个人健康档案API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 09:42:37
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblResidentHealthArchivesApi", description = "居民个人健康档案")
@RestController
@RequestMapping(value = "/archives")
public class TblResidentHealthArchivesApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblResidentHealthArchivesService tblResidentHealthArchivesService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部居民个人健康档案")
	@ApiOperation(value = "查询全部居民个人健康档案", notes = "查询全部居民个人健康档案")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(tblResidentHealthArchivesService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询居民个人健康档案")
	@ApiOperation(value = "分页查询居民个人健康档案", notes = "分页查询居民个人健康档案")
	public ResponseEntity<?> findPage(@RequestBody SearchParams params) {
		return new ResponseEntity<>(tblResidentHealthArchivesService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "主键查询居民个人健康档案")
	@ApiOperation(value = "主键查询居民个人健康档案", notes = "主键查询居民个人健康档案")
	public ResponseEntity<?> findByKey(@RequestBody TblResidentHealthArchives tblResidentHealthArchives) {
		TblResidentHealthArchives result = tblResidentHealthArchivesService
				.findByPrimaryKey(tblResidentHealthArchives.getId());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/findAllMemberWithFamilyByCondition", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "根据身份证号码查询居民健康档案且查询是否存在于其他家庭")
	@ApiOperation(value = "根据身份证号码查询居民健康档案且查询是否存在于其他家庭", notes = "根据身份证号码查询居民健康档案且查询是否存在于其他家庭")
	public ResponseEntity<?> findAllMemberWithFamilyByCondition(
			@RequestBody TblResidentHealthArchives tblResidentHealthArchives) {
		List<TblResidentHealthArchives> list = tblResidentHealthArchivesService
				.findAllMemberWithFamilyByCondition(tblResidentHealthArchives);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/findMemberWithContactByCondition", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "根据身份证号码查询居民健康档案且查询是否已经签订协议")
	@ApiOperation(value = "根据身份证号码查询居民健康档案且查询是否已经签订协议", notes = "根据身份证号码查询居民健康档案且查询是否已经签订协议")
	public ResponseEntity<?> findMemberWithContactByCondition(
			@RequestBody TblResidentHealthArchives tblResidentHealthArchives) {
		List<TblResidentHealthArchives> list = tblResidentHealthArchivesService
				.findMemberWithContactByCondition(tblResidentHealthArchives);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询居民个人健康档案")
	@ApiOperation(value = "条件查询居民个人健康档案", notes = "条件查询居民个人健康档案")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblResidentHealthArchives tblResidentHealthArchives) {
		List<TblResidentHealthArchives> list = tblResidentHealthArchivesService
				.findByCondition(tblResidentHealthArchives);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "新增居民个人健康档案数据")
	@ApiOperation(value = "新增居民个人健康档案", notes = "新增居民个人健康档案")
	public ResponseEntity<?> insert(@RequestBody TblResidentHealthArchives tblResidentHealthArchives) {
		MessModel messModel = new MessModel();
		Date now = new Date();
		tblResidentHealthArchives.setDeletesign(false);
		tblResidentHealthArchives.setCreateuser(getUserId());
		tblResidentHealthArchives.setCreatedate(now);
		tblResidentHealthArchives.setOrgid(getOrgId());
		tblResidentHealthArchives.setFilinhdate(now);
		int result = tblResidentHealthArchivesService.insertArchive(tblResidentHealthArchives);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("新增成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "更新居民个人健康档案数据")
	@ApiOperation(value = "更新居民个人健康档案数据", notes = "更新居民个人健康档案数据")
	public ResponseEntity<?> update(@RequestBody TblResidentHealthArchives tblResidentHealthArchives) {
		MessModel messModel = new MessModel();
		tblResidentHealthArchives.setUpdatedate(new Date());
		tblResidentHealthArchives.setUpdateuser(getUserId());
		int result = tblResidentHealthArchivesService.update(tblResidentHealthArchives);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("更新成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	// @RequestMapping(value = "/delete", method = RequestMethod.POST, produces
	// = MediaType.APPLICATION_JSON_VALUE)
	// @SystemApiLog(description = "删除居民个人健康档案数据")
	// @ApiOperation(value = "删除居民个人健康档案数据", notes = "删除居民个人健康档案数据")
	// public ResponseEntity<?> delete(@RequestBody TblResidentHealthArchives
	// tblResidentHealthArchives) {
	// MessModel messModel = new MessModel();
	// int result =
	// tblResidentHealthArchivesService.delete(tblResidentHealthArchives);
	// // 判断返回结果
	// if (result > 0) {
	// messModel.setMessage("删除成功");
	// return new ResponseEntity<>(messModel, HttpStatus.OK);
	// }
	// return null;
	// }

}
