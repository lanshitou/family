/*
 * @(#) TblMedicalInstitutionDoctorApi  2017-07-17 17:28:17
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
import com.tiuweb.family.doctors.domain.TblMedicalInstitutionDoctor;
import com.tiuweb.family.doctors.service.ITblMedicalInstitutionDoctorService;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 签约医疗机构医生信息
 * </p>
 * <p>
 * Description: TODO 签约医疗机构医生信息API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 17:28:17
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblMedicalInstitutionDoctorApi", description = "签约医疗机构医生信息")
@RestController
@RequestMapping(value = "/doctor")
public class TblMedicalInstitutionDoctorApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblMedicalInstitutionDoctorService tblMedicalInstitutionDoctorService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部签约医疗机构医生信息")
	@ApiOperation(value = "查询全部签约医疗机构医生信息", notes = "查询全部签约医疗机构医生信息")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(tblMedicalInstitutionDoctorService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询签约医疗机构医生信息")
	@ApiOperation(value = "分页查询签约医疗机构医生信息", notes = "分页查询签约医疗机构医生信息")
	public ResponseEntity<?> findPage(@RequestBody SearchParams params) {
		return new ResponseEntity<>(tblMedicalInstitutionDoctorService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "主键查询签约医疗机构医生信息")
	@ApiOperation(value = "主键查询签约医疗机构医生信息", notes = "主键查询签约医疗机构医生信息")
	public ResponseEntity<?> findByKey(@RequestBody TblMedicalInstitutionDoctor tblMedicalInstitutionDoctor) {
		TblMedicalInstitutionDoctor result = tblMedicalInstitutionDoctorService
				.findByPrimaryKey(tblMedicalInstitutionDoctor.getId());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询签约医疗机构医生信息")
	@ApiOperation(value = "条件查询签约医疗机构医生信息", notes = "条件查询签约医疗机构医生信息")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblMedicalInstitutionDoctor tblMedicalInstitutionDoctor) {
		List<TblMedicalInstitutionDoctor> list = tblMedicalInstitutionDoctorService
				.findByCondition(tblMedicalInstitutionDoctor);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "新增签约医疗机构医生信息数据")
	@ApiOperation(value = "新增签约医疗机构医生信息", notes = "新增签约医疗机构医生信息")
	public ResponseEntity<?> insert(@RequestBody TblMedicalInstitutionDoctor tblMedicalInstitutionDoctor) {
		MessModel messModel = new MessModel();
		tblMedicalInstitutionDoctor.setCreateuser(getUserId());
		tblMedicalInstitutionDoctor.setCreatedate(new Date());
		tblMedicalInstitutionDoctor.setOrgid(getOrgId());
		int result = tblMedicalInstitutionDoctorService.insertDoctor(tblMedicalInstitutionDoctor, getToken());
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("新增成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "更新签约医疗机构医生信息数据")
	@ApiOperation(value = "更新签约医疗机构医生信息数据", notes = "更新签约医疗机构医生信息数据")
	public ResponseEntity<?> update(@RequestBody TblMedicalInstitutionDoctor tblMedicalInstitutionDoctor) {
		MessModel messModel = new MessModel();
		tblMedicalInstitutionDoctor.setUpdatedate(new Date());
		tblMedicalInstitutionDoctor.setUpdateuser(getUserId());
		int result = tblMedicalInstitutionDoctorService.update(tblMedicalInstitutionDoctor);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("更新成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "删除签约医疗机构医生信息数据")
	@ApiOperation(value = "删除签约医疗机构医生信息数据", notes = "删除签约医疗机构医生信息数据")
	public ResponseEntity<?> delete(@RequestBody TblMedicalInstitutionDoctor tblMedicalInstitutionDoctor) {
		MessModel messModel = new MessModel();
		tblMedicalInstitutionDoctor.setUpdatedate(new Date());
		tblMedicalInstitutionDoctor.setUpdateuser(getUserId());
		tblMedicalInstitutionDoctor.setDeletesign(true);
		// int result =
		// tblMedicalInstitutionDoctorService.delete(tblMedicalInstitutionDoctor);
		int result = tblMedicalInstitutionDoctorService.update(tblMedicalInstitutionDoctor);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("删除成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

}
