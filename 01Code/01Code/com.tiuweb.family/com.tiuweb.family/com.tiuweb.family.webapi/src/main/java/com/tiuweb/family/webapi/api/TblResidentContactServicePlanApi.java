/*
 * @(#) TblResidentContactServicePlanApi  2017-07-12 16:23:05
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
import com.tiuweb.family.plan.domain.TblResidentContactServicePlan;
import com.tiuweb.family.plan.domain.TblTreatmentRecords;
import com.tiuweb.family.plan.service.ITblResidentContactServicePlanService;
import com.tiuweb.family.plan.service.ITblTreatmentRecordsService;
import com.tiuweb.family.util.Utility;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 居民签约服务执行计划
 * </p>
 * <p>
 * Description: TODO 居民签约服务执行计划API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-12 16:23:05
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblResidentContactServicePlanApi", description = "居民签约服务执行计划")
@RestController
@RequestMapping(value = "/plan")
public class TblResidentContactServicePlanApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblResidentContactServicePlanService tblResidentContactServicePlanService;

	@Autowired
	private ITblTreatmentRecordsService tblTreatmentRecordsService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部居民签约服务执行计划")
	@ApiOperation(value = "查询全部居民签约服务执行计划", notes = "查询全部居民签约服务执行计划")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(tblResidentContactServicePlanService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询居民签约服务执行计划")
	@ApiOperation(value = "分页查询居民签约服务执行计划", notes = "分页查询居民签约服务执行计划")
	public ResponseEntity<?> findPage(@RequestBody SearchParams params) {
		return new ResponseEntity<>(tblResidentContactServicePlanService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "主键查询居民签约服务执行计划")
	@ApiOperation(value = "主键查询居民签约服务执行计划", notes = "主键查询居民签约服务执行计划")
	public ResponseEntity<?> findByKey(@RequestBody TblResidentContactServicePlan tblResidentContactServicePlan) {
		TblResidentContactServicePlan result = tblResidentContactServicePlanService
				.findByPrimaryKey(tblResidentContactServicePlan.getId());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询居民签约服务执行计划")
	@ApiOperation(value = "条件查询居民签约服务执行计划", notes = "条件查询居民签约服务执行计划")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblResidentContactServicePlan tblResidentContactServicePlan) {
		List<TblResidentContactServicePlan> list = tblResidentContactServicePlanService
				.findByCondition(tblResidentContactServicePlan);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "更新居民签约服务执行计划数据")
	@ApiOperation(value = "更新居民签约服务执行计划数据", notes = "更新居民签约服务执行计划数据")
	public ResponseEntity<?> update(@RequestBody TblResidentContactServicePlan tblResidentContactServicePlan) {
		MessModel messModel = new MessModel();
		int result = tblResidentContactServicePlanService.update(tblResidentContactServicePlan);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("更新成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/execute", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "居民签约服务计划待执行计划录入结论")
	@ApiOperation(value = "居民签约服务计划待执行计划录入结论", notes = "居民签约服务计划待执行计划录入结论")
	public ResponseEntity<?> execute(@RequestBody TblTreatmentRecords tblTreatmentRecords) {
		MessModel messModel = new MessModel();

		// 创建属性信息初始化
		tblTreatmentRecords.setServicenumber(Utility.getServiceNumber());
		tblTreatmentRecords.setCreateuser(getUserId());
		tblTreatmentRecords.setCreatedate(new Date());
		tblTreatmentRecords.setOrgid(getOrgId());
		tblTreatmentRecords.setDeletesign(false);

		int result = tblTreatmentRecordsService.execute(tblTreatmentRecords);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("录入成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}
}
