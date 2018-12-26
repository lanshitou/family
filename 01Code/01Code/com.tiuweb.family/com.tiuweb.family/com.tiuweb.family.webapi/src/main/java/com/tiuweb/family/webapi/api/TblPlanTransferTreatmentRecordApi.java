/*
 * @(#) TblPlanTransferTreatmentRecordApi  2017-08-07 10:38:53
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
import com.tiuweb.family.plan.domain.TblPlanTransferTreatmentRecord;
import com.tiuweb.family.plan.service.ITblPlanTransferTreatmentRecordService;
import com.tiuweb.family.util.Utility;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 执行计划转诊记录
 * </p>
 * <p>
 * Description: TODO 执行计划转诊记录API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-07 10:38:53
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblPlanTransferTreatmentRecordApi", description = "执行计划转诊记录")
@RestController
@RequestMapping(value = "/transfer")
public class TblPlanTransferTreatmentRecordApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblPlanTransferTreatmentRecordService tblPlanTransferTreatmentRecordService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部执行计划转诊记录")
	@ApiOperation(value = "查询全部执行计划转诊记录", notes = "查询全部执行计划转诊记录")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(tblPlanTransferTreatmentRecordService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询执行计划转诊记录")
	@ApiOperation(value = "分页查询执行计划转诊记录", notes = "分页查询执行计划转诊记录")
	public ResponseEntity<?> findPage(@RequestBody SearchParams params) {
		return new ResponseEntity<>(tblPlanTransferTreatmentRecordService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "主键查询执行计划转诊记录")
	@ApiOperation(value = "主键查询执行计划转诊记录", notes = "主键查询执行计划转诊记录")
	public ResponseEntity<?> findByKey(@RequestBody TblPlanTransferTreatmentRecord tblPlanTransferTreatmentRecord) {
		TblPlanTransferTreatmentRecord result = tblPlanTransferTreatmentRecordService
				.findByPrimaryKey(tblPlanTransferTreatmentRecord.getId());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询执行计划转诊记录")
	@ApiOperation(value = "条件查询执行计划转诊记录", notes = "条件查询执行计划转诊记录")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblPlanTransferTreatmentRecord tblPlanTransferTreatmentRecord) {
		List<TblPlanTransferTreatmentRecord> list = tblPlanTransferTreatmentRecordService
				.findByCondition(tblPlanTransferTreatmentRecord);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "新增执行计划转诊记录数据")
	@ApiOperation(value = "新增执行计划转诊记录", notes = "新增执行计划转诊记录")
	public ResponseEntity<?> insert(@RequestBody TblPlanTransferTreatmentRecord tblPlanTransferTreatmentRecord) {
		MessModel messModel = new MessModel();

		// 创建属性信息初始化
		tblPlanTransferTreatmentRecord.setTransfernumber(Utility.getRecordNumber());
		tblPlanTransferTreatmentRecord.setCreateuser(getUserId());
		tblPlanTransferTreatmentRecord.setCreatedate(new Date());
		tblPlanTransferTreatmentRecord.setOrgid(getOrgId());
		tblPlanTransferTreatmentRecord.setDeletesign(false);
		tblPlanTransferTreatmentRecord.setStatus((short) 1);

		int result = tblPlanTransferTreatmentRecordService.insertTransfer(tblPlanTransferTreatmentRecord);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("新增成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "更新执行计划转诊记录数据")
	@ApiOperation(value = "更新执行计划转诊记录数据", notes = "更新执行计划转诊记录数据")
	public ResponseEntity<?> update(@RequestBody TblPlanTransferTreatmentRecord tblPlanTransferTreatmentRecord) {
		MessModel messModel = new MessModel();
		int result = tblPlanTransferTreatmentRecordService.update(tblPlanTransferTreatmentRecord);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("更新成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "删除执行计划转诊记录数据")
	@ApiOperation(value = "删除执行计划转诊记录数据", notes = "删除执行计划转诊记录数据")
	public ResponseEntity<?> delete(@RequestBody TblPlanTransferTreatmentRecord tblPlanTransferTreatmentRecord) {
		MessModel messModel = new MessModel();
		int result = tblPlanTransferTreatmentRecordService.delete(tblPlanTransferTreatmentRecord);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("删除成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

}
