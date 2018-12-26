/*
 * @(#) TblResidentFamilyApi  2017-07-19 11:41:23
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
import com.tiuweb.family.home.domain.TblResidentFamily;
import com.tiuweb.family.home.service.ITblResidentFamilyService;
import com.tiuweb.family.util.Utility;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 居民家庭信息
 * </p>
 * <p>
 * Description: TODO 居民家庭信息API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-19 11:41:23
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblResidentFamilyApi", description = "居民家庭信息")
@RestController
@RequestMapping(value = "/familys")
public class TblResidentFamilyApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblResidentFamilyService tblResidentFamilyService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部居民家庭信息")
	@ApiOperation(value = "查询全部居民家庭信息", notes = "查询全部居民家庭信息")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(tblResidentFamilyService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询居民家庭信息")
	@ApiOperation(value = "分页查询居民家庭信息", notes = "分页查询居民家庭信息")
	public ResponseEntity<?> findPage(@RequestBody SearchParams params) {
		return new ResponseEntity<>(tblResidentFamilyService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "主键查询居民家庭及其成员信息")
	@ApiOperation(value = "主键查询居民家庭及其成员信息", notes = "主键查询居民家庭及其成员信息")
	public ResponseEntity<?> findByKey(@RequestBody TblResidentFamily tblResidentFamily) {
		TblResidentFamily result = tblResidentFamilyService.findDetailsByCondition(tblResidentFamily);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询居民家庭信息")
	@ApiOperation(value = "条件查询居民家庭信息", notes = "条件查询居民家庭信息")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblResidentFamily tblResidentFamily) {
		List<TblResidentFamily> list = tblResidentFamilyService.findByCondition(tblResidentFamily);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "新增居民家庭信息数据")
	@ApiOperation(value = "新增居民家庭信息", notes = "新增居民家庭信息")
	public ResponseEntity<?> insert(@RequestBody TblResidentFamily tblResidentFamily) {
		MessModel messModel = new MessModel();
		tblResidentFamily.setFamilynumber(Utility.getFamilyNumber());
		tblResidentFamily.setCreateuser(getUserId());
		tblResidentFamily.setCreatedate(new Date());
		tblResidentFamily.setOrgid(getOrgId());
		tblResidentFamily.setDeletesign(false);
		int result = tblResidentFamilyService.insertWithMembers(tblResidentFamily);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("新增成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "更新居民家庭信息数据")
	@ApiOperation(value = "更新居民家庭信息数据", notes = "更新居民家庭信息数据")
	public ResponseEntity<?> update(@RequestBody TblResidentFamily tblResidentFamily) {
		MessModel messModel = new MessModel();
		tblResidentFamily.setUpdatedate(new Date());
		tblResidentFamily.setUpdateuser(getUserId());
		int result = tblResidentFamilyService.updateWithMembers(tblResidentFamily);

		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("更新成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/updateWithInsertMember", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "添加协议时更新(新增)家庭成员信息")
	@ApiOperation(value = "添加协议时更新(新增)家庭成员信息", notes = "添加协议时更新(新增)家庭成员信息")
	public ResponseEntity<?> updateWithInsertMember(@RequestBody TblResidentFamily tblResidentFamily) {
		MessModel messModel = new MessModel();
		tblResidentFamily.setUpdatedate(new Date());
		tblResidentFamily.setUpdateuser(getUserId());
		int result = tblResidentFamilyService.updateWithInsertMember(tblResidentFamily);

		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("更新成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "删除居民家庭信息数据")
	@ApiOperation(value = "删除居民家庭信息数据", notes = "删除居民家庭信息数据")
	public ResponseEntity<?> delete(@RequestBody TblResidentFamily tblResidentFamily) {
		MessModel messModel = new MessModel();
		tblResidentFamily.setUpdatedate(new Date());
		tblResidentFamily.setUpdateuser(getUserId());
		tblResidentFamily.setDeletesign(true);
		// int result = tblResidentFamilyService.delete(tblResidentFamily);
		int result = tblResidentFamilyService.update(tblResidentFamily);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("删除成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

}
