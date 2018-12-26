/*
 * @(#) TblFamilyDoctorTeamApi  2017-07-17 15:07:46
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
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.SearchParams;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.logs.SystemApiLog;
import com.tiuweb.family.items.domain.TblMedicalTeamService;
import com.tiuweb.family.items.service.ITblMedicalTeamServiceService;
import com.tiuweb.family.items.service.ITblServcieItemService;
import com.tiuweb.family.teams.domain.TblFamilyDoctorTeam;
import com.tiuweb.family.teams.service.ITblFamilyDoctorTeamService;
import com.tiuweb.family.util.Utility;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 签约家庭医生团队信息
 * </p>
 * <p>
 * Description: TODO 签约家庭医生团队信息API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 15:07:46
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblFamilyDoctorTeamApi", description = "签约家庭医生团队信息")
@RestController
@RequestMapping(value = "/team")
public class TblFamilyDoctorTeamApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblServcieItemService tblServcieItemService;

	@Autowired
	private ITblFamilyDoctorTeamService tblFamilyDoctorTeamService;

	@Autowired
	private ITblMedicalTeamServiceService tblMedicalTeamServiceService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部签约家庭医生团队信息")
	@ApiOperation(value = "查询全部签约家庭医生团队信息", notes = "查询全部签约家庭医生团队信息")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(tblFamilyDoctorTeamService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findByTeamsKey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "根据团队主键查询团队提供的有效服务")
	@ApiOperation(value = "根据团队主键查询团队提供的有效服务", notes = "根据团队主键查询团队提供的有效服务")
	public ResponseEntity<?> findByTeamsKey(@RequestBody Map<String, Object> params) {
		return new ResponseEntity<>(tblServcieItemService.findByTeamsKey(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询签约家庭医生团队信息")
	@ApiOperation(value = "分页查询签约家庭医生团队信息", notes = "分页查询签约家庭医生团队信息")
	public ResponseEntity<?> findPage(@RequestBody SearchParams params) {
		return new ResponseEntity<>(tblFamilyDoctorTeamService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "主键查询签约家庭医生团队信息")
	@ApiOperation(value = "主键查询签约家庭医生团队信息", notes = "主键查询签约家庭医生团队信息")
	public ResponseEntity<?> findByKey(@RequestBody TblFamilyDoctorTeam tblFamilyDoctorTeam) {
		TblFamilyDoctorTeam result = tblFamilyDoctorTeamService.findByPrimaryKey(tblFamilyDoctorTeam.getId());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询签约家庭医生团队信息")
	@ApiOperation(value = "条件查询签约家庭医生团队信息", notes = "条件查询签约家庭医生团队信息")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblFamilyDoctorTeam tblFamilyDoctorTeam) {
		List<TblFamilyDoctorTeam> list = tblFamilyDoctorTeamService.findByCondition(tblFamilyDoctorTeam);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/insertItem", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "新增签约家庭医生服务项目")
	@ApiOperation(value = "新增签约家庭医生服务项目", notes = "新增签约家庭医生服务项目")
	public ResponseEntity<?> insertItem(@RequestBody TblMedicalTeamService tblMedicalTeamService) {
		tblMedicalTeamService.setCreateuser(getUserId());
		tblMedicalTeamService.setCreatedate(new Date());
		tblMedicalTeamService.setOrgid(getOrgId());
		tblMedicalTeamService.setDeletesign(false);
		int result = tblMedicalTeamServiceService.insertItemByTeam(tblMedicalTeamService);
		// 判断返回结果
		if (result <= 0) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "插入");
		}
		return null;
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "新增签约家庭医生团队信息数据")
	@ApiOperation(value = "新增签约家庭医生团队信息", notes = "新增签约家庭医生团队信息")
	public ResponseEntity<?> insert(@RequestBody TblFamilyDoctorTeam tblFamilyDoctorTeam) {
		MessModel messModel = new MessModel();
		tblFamilyDoctorTeam.setTeamnumber(Utility.getTeamNumber());
		tblFamilyDoctorTeam.setCreateuser(getUserId());
		tblFamilyDoctorTeam.setCreatedate(new Date());
		tblFamilyDoctorTeam.setOrgid(getOrgId());
		tblFamilyDoctorTeam.setDeletesign(false);
		int result = tblFamilyDoctorTeamService.insertTeamWithMember(tblFamilyDoctorTeam);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("新增成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "更新签约家庭医生团队信息数据")
	@ApiOperation(value = "更新签约家庭医生团队信息数据", notes = "更新签约家庭医生团队信息数据")
	public ResponseEntity<?> update(@RequestBody TblFamilyDoctorTeam tblFamilyDoctorTeam) {
		MessModel messModel = new MessModel();
		tblFamilyDoctorTeam.setUpdatedate(new Date());
		tblFamilyDoctorTeam.setUpdateuser(getUserId());
		int result = tblFamilyDoctorTeamService.update(tblFamilyDoctorTeam);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("更新成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "删除签约家庭医生团队信息数据")
	@ApiOperation(value = "删除签约家庭医生团队信息数据", notes = "删除签约家庭医生团队信息数据")
	public ResponseEntity<?> delete(@RequestBody TblFamilyDoctorTeam tblFamilyDoctorTeam) {
		MessModel messModel = new MessModel();
		tblFamilyDoctorTeam.setUpdatedate(new Date());
		tblFamilyDoctorTeam.setUpdateuser(getUserId());
		tblFamilyDoctorTeam.setDeletesign(true);
		int result = tblFamilyDoctorTeamService.update(tblFamilyDoctorTeam);
		// int result = tblFamilyDoctorTeamService.delete(tblFamilyDoctorTeam);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("删除成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/deleteItem", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "删除签约家庭医生团队服务信息")
	@ApiOperation(value = "删除签约家庭医生团队服务信息", notes = "删除签约家庭医生团队服务信息")
	public ResponseEntity<?> delete(@RequestBody TblMedicalTeamService tblMedicalTeamService) {
		MessModel messModel = new MessModel();
		tblMedicalTeamService.setUpdatedate(new Date());
		tblMedicalTeamService.setUpdateuser(getUserId());
		tblMedicalTeamService.setDeletesign(true);
		int result = tblMedicalTeamServiceService.deleteItem(tblMedicalTeamService);
		// int result = tblFamilyDoctorTeamService.delete(tblFamilyDoctorTeam);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("删除成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}
}
