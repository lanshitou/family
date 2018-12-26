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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.logs.SystemApiLog;
import com.tiuweb.family.common.service.ICodeInfo;
import com.tiuweb.family.doctors.domain.TblMedicalInstitutionDoctor;
import com.tiuweb.family.doctors.service.ITblMedicalInstitutionDoctorService;
import com.tiuweb.family.util.Constants;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 字典信息
 * </p>
 * <p>
 * Description: TODO 字典信息API层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-17 11:01:14
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "CodeInfo", description = "系统字典信息")
@RestController
@RequestMapping(value = "/code")
public class CodeInfoApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ICodeInfo<?> codeInfo;

	@Autowired
	private ITblMedicalInstitutionDoctorService tblMedicalInstitutionDoctorService;

	@RequestMapping(value = "/findMemberShip", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询居民家庭成员身份字典信息")
	@ApiOperation(value = "查询居民家庭成员身份字典信息", notes = "查询居民家庭成员身份字典信息")
	public ResponseEntity<?> findMemberShip() {
		return new ResponseEntity<>(codeInfo.getCodeInfoList(Constants.MEMBERSIP), HttpStatus.OK);
	}

	@RequestMapping(value = "/findServiceType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询服务类型字典信息")
	@ApiOperation(value = "查询服务类型字典信息", notes = "查询服务类型字典信息")
	public ResponseEntity<?> findServiceType() {
		return new ResponseEntity<>(codeInfo.getCodeInfoList(Constants.SERVICE_TYPE), HttpStatus.OK);
	}

	@RequestMapping(value = "/findCertificateType", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询证件类型字典信息")
	@ApiOperation(value = "查询证件类型字典信息", notes = "查询证件类型字典信息")
	public ResponseEntity<?> findCertificateType() {
		return new ResponseEntity<>(codeInfo.getCodeInfoList(Constants.CERTIFICATE_TYPE), HttpStatus.OK);
	}

	@RequestMapping(value = "/findDoctorLevel", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询医生职级类型字典信息")
	@ApiOperation(value = "查询医生职级类型字典信息", notes = "查询医生职级类型字典信息")
	public ResponseEntity<?> findDoctorLevel() {
		return new ResponseEntity<>(codeInfo.getCodeInfoList(Constants.DOCTOR_LEVEL), HttpStatus.OK);
	}

	@RequestMapping(value = "/findServicePeriod", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询服务计划周期信息")
	@ApiOperation(value = "查询服务计划周期信息", notes = "查询服务计划周期信息")
	public ResponseEntity<?> findServicePeriod() {
		return new ResponseEntity<>(codeInfo.getCodeInfoList(Constants.PLAN_PERIOD_TYPE), HttpStatus.OK);
	}

	@RequestMapping(value = "/findPaymentFrequency", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询费用支付频率信息")
	@ApiOperation(value = "查询费用支付频率信息", notes = "查询费用支付频率信息")
	public ResponseEntity<?> findPaymentFrequency() {
		return new ResponseEntity<>(codeInfo.getCodeInfoList(Constants.PAYMENT_FREQUENCY_TYPE), HttpStatus.OK);
	}

	@RequestMapping(value = "/findEquipment", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询医疗设备信息")
	@ApiOperation(value = "查询医疗设备信息", notes = "查询医疗设备信息")
	public ResponseEntity<?> findEquipment() {
		return new ResponseEntity<>(codeInfo.getCodeInfoList(Constants.EQUIPMENT_TYPE), HttpStatus.OK);
	}

	@RequestMapping(value = "/findCurrentOrg", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询当前登录用户所属机构")
	@ApiOperation(value = "查询当前登录用户所属机构", notes = "查询当前登录用户所属机构")
	public ResponseEntity<?> findCurrentOrg() {
		TblMedicalInstitutionDoctor query = new TblMedicalInstitutionDoctor();
		query.setPuserid(getUserId());
		List<TblMedicalInstitutionDoctor> data = tblMedicalInstitutionDoctorService.findByCondition(query);
		if (null == data || data.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "签约医生中未查询到当前用户所属机构");
		}
		if (data.size() != 1) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "签约医生中查询到当前用户所属机构重复");
		}
		return new ResponseEntity<>(data.get(0), HttpStatus.OK);
	}
}
