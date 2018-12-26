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
import com.tiuweb.family.items.domain.TblServcieItem;
import com.tiuweb.family.items.service.ITblServcieItemRoleService;
import com.tiuweb.family.items.service.ITblServcieItemService;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 签约服务项目
 * </p>
 * <p>
 * Description: TODO 签约服务项目API层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-17 11:01:14
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblServcieItemApi", description = "签约服务项目")
@RestController
@RequestMapping(value = "/items")
public class TblServcieItemApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblServcieItemService tblServcieItemService;

	@Autowired
	private ITblServcieItemRoleService tblServcieItemRoleService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部签约服务项目")
	@ApiOperation(value = "查询全部签约服务项目", notes = "查询全部签约服务项目")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(tblServcieItemService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询签约服务项目")
	@ApiOperation(value = "分页查询签约服务项目", notes = "分页查询签约服务项目")
	public ResponseEntity<?> findPage(@RequestBody SearchParams params) {
		return new ResponseEntity<>(tblServcieItemService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "主键查询签约服务项目")
	@ApiOperation(value = "主键查询签约服务项目", notes = "主键查询签约服务项目")
	public ResponseEntity<?> findByKey(@RequestBody TblServcieItem tblServcieItem) {
		TblServcieItem result = tblServcieItemService.findDetailByPrimaryKey(tblServcieItem);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询签约服务项目")
	@ApiOperation(value = "条件查询签约服务项目", notes = "条件查询签约服务项目")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblServcieItem tblServcieItem) {
		List<TblServcieItem> list = tblServcieItemService.findByCondition(tblServcieItem);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "新增签约服务项目数据")
	@ApiOperation(value = "新增签约服务项目", notes = "新增签约服务项目")
	public ResponseEntity<?> insert(@RequestBody TblServcieItem tblServcieItem) {
		MessModel messModel = new MessModel();
		tblServcieItem.setCreateuser(getUserId());
		tblServcieItem.setCreatedate(new Date());
		tblServcieItem.setOrgid(getOrgId());
		int result = tblServcieItemService.inserItemsWithRole(tblServcieItem);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("新增成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "更新签约服务项目数据")
	@ApiOperation(value = "更新签约服务项目数据", notes = "更新签约服务项目数据")
	public ResponseEntity<?> update(@RequestBody TblServcieItem tblServcieItem) {
		MessModel messModel = new MessModel();
		tblServcieItem.setUpdatedate(new Date());
		tblServcieItem.setUpdateuser(getUserId());
		int result = tblServcieItemService.updateItemsWithRole(tblServcieItem);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("更新成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "删除签约服务项目数据")
	@ApiOperation(value = "删除签约服务项目数据", notes = "删除签约服务项目数据")
	public ResponseEntity<?> delete(@RequestBody TblServcieItem tblServcieItem) {
		MessModel messModel = new MessModel();
		int result = tblServcieItemService.delete(tblServcieItem);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("删除成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/findRole", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询所需角色")
	@ApiOperation(value = "查询所需角色", notes = "查询所需角色")
	public ResponseEntity<?> findRole() {
		return new ResponseEntity<>(tblServcieItemRoleService.findAll(), HttpStatus.OK);
	}
} /// :~
