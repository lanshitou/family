/*
 * @(#) TblFamilyDoctorTeamMemberApi  2017-07-17 15:20:00
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
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.SearchParams;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.logs.SystemApiLog;
import com.tiuweb.family.teams.domain.TblFamilyDoctorTeamMember;
import com.tiuweb.family.teams.service.ITblFamilyDoctorTeamMemberService;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 签约家庭医生团队成员信息
 * </p>
 * <p>
 * Description: TODO 签约家庭医生团队成员信息API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 15:20:00
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblFamilyDoctorTeamMemberApi", description = "签约家庭医生团队成员信息")
@RestController
@RequestMapping(value = "/member")
public class TblFamilyDoctorTeamMemberApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblFamilyDoctorTeamMemberService tblFamilyDoctorTeamMemberService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部签约家庭医生团队成员信息")
	@ApiOperation(value = "查询全部签约家庭医生团队成员信息", notes = "查询全部签约家庭医生团队成员信息")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(tblFamilyDoctorTeamMemberService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询签约家庭医生团队成员信息")
	@ApiOperation(value = "分页查询签约家庭医生团队成员信息", notes = "分页查询签约家庭医生团队成员信息")
	public ResponseEntity<?> findPage(@RequestBody SearchParams params) {
		return new ResponseEntity<>(tblFamilyDoctorTeamMemberService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "主键查询签约家庭医生团队成员信息")
	@ApiOperation(value = "主键查询签约家庭医生团队成员信息", notes = "主键查询签约家庭医生团队成员信息")
	public ResponseEntity<?> findByKey(@RequestBody TblFamilyDoctorTeamMember tblFamilyDoctorTeamMember) {
		TblFamilyDoctorTeamMember result = tblFamilyDoctorTeamMemberService
				.findByPrimaryKey(tblFamilyDoctorTeamMember.getId());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询签约家庭医生团队成员信息")
	@ApiOperation(value = "条件查询签约家庭医生团队成员信息", notes = "条件查询签约家庭医生团队成员信息")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblFamilyDoctorTeamMember tblFamilyDoctorTeamMember) {
		List<TblFamilyDoctorTeamMember> list = tblFamilyDoctorTeamMemberService
				.findByCondition(tblFamilyDoctorTeamMember);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "新增签约家庭医生团队成员信息数据")
	@ApiOperation(value = "新增签约家庭医生团队成员信息", notes = "新增签约家庭医生团队成员信息")
	public ResponseEntity<?> insert(@RequestBody TblFamilyDoctorTeamMember tblFamilyDoctorTeamMember) {
		MessModel messModel = new MessModel();
		tblFamilyDoctorTeamMember.setCreateuser(getUserId());
		tblFamilyDoctorTeamMember.setCreatedate(new Date());
		tblFamilyDoctorTeamMember.setOrgid(getOrgId());
		tblFamilyDoctorTeamMember.setDeletesign(false);

		int result = tblFamilyDoctorTeamMemberService.insertMember(tblFamilyDoctorTeamMember);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("新增成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "更新签约家庭医生团队成员信息数据")
	@ApiOperation(value = "更新签约家庭医生团队成员信息数据", notes = "更新签约家庭医生团队成员信息数据")
	public ResponseEntity<?> update(@RequestBody TblFamilyDoctorTeamMember tblFamilyDoctorTeamMember) {
		MessModel messModel = new MessModel();
		tblFamilyDoctorTeamMember.setUpdatedate(new Date());
		tblFamilyDoctorTeamMember.setUpdateuser(getUserId());
		int result = tblFamilyDoctorTeamMemberService.update(tblFamilyDoctorTeamMember);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("更新成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "删除签约家庭医生团队成员信息数据")
	@ApiOperation(value = "删除签约家庭医生团队成员信息数据", notes = "删除签约家庭医生团队成员信息数据")
	public ResponseEntity<?> delete(@RequestBody TblFamilyDoctorTeamMember tblFamilyDoctorTeamMember) {
		MessModel messModel = new MessModel();
		// int result =
		// tblFamilyDoctorTeamMemberService.delete(tblFamilyDoctorTeamMember);

		tblFamilyDoctorTeamMember.setUpdatedate(new Date());
		tblFamilyDoctorTeamMember.setUpdateuser(getUserId());
		tblFamilyDoctorTeamMember.setDeletesign(true);
		int result = tblFamilyDoctorTeamMemberService.update(tblFamilyDoctorTeamMember);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("删除成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

}
