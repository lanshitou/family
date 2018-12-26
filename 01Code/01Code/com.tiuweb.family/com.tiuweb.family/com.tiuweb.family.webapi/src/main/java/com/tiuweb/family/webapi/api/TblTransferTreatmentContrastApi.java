/*
 * @(#) TblTransferTreatmentContrastApi  2017-08-07 10:53:30
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.tiuweb.family.plan.domain.TblTransferTreatmentContrast;
import com.tiuweb.family.plan.service.ITblTransferTreatmentContrastService;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 转诊服务对照关系信息
 * </p>
 * <p>
 * Description: TODO 转诊服务对照关系信息API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-07 10:53:30
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblTransferTreatmentContrastApi", description = "转诊服务对照关系信息")
@RestController
@RequestMapping(value = "/contrast")
public class TblTransferTreatmentContrastApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblTransferTreatmentContrastService tblTransferTreatmentContrastService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部转诊服务对照关系信息")
	@ApiOperation(value = "查询全部转诊服务对照关系信息", notes = "查询全部转诊服务对照关系信息")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(tblTransferTreatmentContrastService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询转诊服务对照关系信息")
	@ApiOperation(value = "分页查询转诊服务对照关系信息", notes = "分页查询转诊服务对照关系信息")
	public ResponseEntity<?> findPage(@RequestBody SearchParams params) {
		return new ResponseEntity<>(tblTransferTreatmentContrastService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "主键查询转诊服务对照关系信息")
	@ApiOperation(value = "主键查询转诊服务对照关系信息", notes = "主键查询转诊服务对照关系信息")
	public ResponseEntity<?> findByKey(@RequestBody TblTransferTreatmentContrast tblTransferTreatmentContrast) {
		TblTransferTreatmentContrast result = tblTransferTreatmentContrastService
				.findByPrimaryKey(tblTransferTreatmentContrast.getId());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/findCurrentOrgContrast", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询当前机构转诊服务对照关系信息")
	@ApiOperation(value = "查询当前机构转诊服务对照关系信息", notes = "查询当前机构转诊服务对照关系信息")
	public ResponseEntity<?> findCruuentOrgContrast() {
		TblTransferTreatmentContrast query = new TblTransferTreatmentContrast();
		query.setCurrentorgidentity(getOrgId());
		return new ResponseEntity<>(tblTransferTreatmentContrastService.findByCondition(query), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询转诊服务对照关系信息")
	@ApiOperation(value = "条件查询转诊服务对照关系信息", notes = "条件查询转诊服务对照关系信息")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblTransferTreatmentContrast tblTransferTreatmentContrast) {
		List<TblTransferTreatmentContrast> list = tblTransferTreatmentContrastService
				.findByCondition(tblTransferTreatmentContrast);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "新增转诊服务对照关系信息数据")
	@ApiOperation(value = "新增转诊服务对照关系信息", notes = "新增转诊服务对照关系信息")
	public ResponseEntity<?> insert(@RequestBody TblTransferTreatmentContrast tblTransferTreatmentContrast) {
		MessModel messModel = new MessModel();

		tblTransferTreatmentContrast.setCreateuser(getUserId());
		tblTransferTreatmentContrast.setCreatedate(new Date());
		tblTransferTreatmentContrast.setOrgid(getOrgId());
		tblTransferTreatmentContrast.setDeletesign(false);

		int result = tblTransferTreatmentContrastService.insertContras(tblTransferTreatmentContrast);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("新增成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "更新转诊服务对照关系信息数据")
	@ApiOperation(value = "更新转诊服务对照关系信息数据", notes = "更新转诊服务对照关系信息数据")
	public ResponseEntity<?> update(@RequestBody TblTransferTreatmentContrast tblTransferTreatmentContrast) {
		MessModel messModel = new MessModel();
		tblTransferTreatmentContrast.setUpdatedate(new Date());
		tblTransferTreatmentContrast.setCreateuser(getUserId());
		int result = tblTransferTreatmentContrastService.updateContras(tblTransferTreatmentContrast);

		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("更新成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "删除转诊服务对照关系信息数据")
	@ApiOperation(value = "删除转诊服务对照关系信息数据", notes = "删除转诊服务对照关系信息数据")
	public ResponseEntity<?> delete(@RequestBody TblTransferTreatmentContrast tblTransferTreatmentContrast) {
		MessModel messModel = new MessModel();
		int result = tblTransferTreatmentContrastService.delete(tblTransferTreatmentContrast);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("删除成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}
}
