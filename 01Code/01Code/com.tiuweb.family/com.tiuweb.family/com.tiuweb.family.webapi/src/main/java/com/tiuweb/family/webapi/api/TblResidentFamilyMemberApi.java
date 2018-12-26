/*
 * @(#) TblResidentFamilyMemberApi  2017-07-19 11:43:25
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
import com.tiuweb.climb.framework.config.logs.SystemApiLog;
import com.tiuweb.family.home.domain.TblResidentFamily;
import com.tiuweb.family.home.domain.TblResidentFamilyMember;
import com.tiuweb.family.home.service.ITblResidentFamilyMemberService;
import com.tiuweb.family.util.Utility;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 居民家庭成员信息
 * </p>
 * <p>
 * Description: TODO 居民家庭成员信息API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-19 11:43:25
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblResidentFamilyMemberApi", description = "居民家庭成员信息")
@RestController
@RequestMapping(value = "/relative")
public class TblResidentFamilyMemberApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblResidentFamilyMemberService tblResidentFamilyMemberService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部居民家庭成员信息")
	@ApiOperation(value = "查询全部居民家庭成员信息", notes = "查询全部居民家庭成员信息")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(tblResidentFamilyMemberService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "主键查询居民家庭成员信息")
	@ApiOperation(value = "主键查询居民家庭成员信息", notes = "主键查询居民家庭成员信息")
	public ResponseEntity<?> findByKey(@RequestBody TblResidentFamilyMember tblResidentFamilyMember) {
		TblResidentFamilyMember result = tblResidentFamilyMemberService
				.findByPrimaryKey(tblResidentFamilyMember.getId());
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询居民家庭成员信息")
	@ApiOperation(value = "条件查询居民家庭成员信息", notes = "条件查询居民家庭成员信息")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblResidentFamilyMember tblResidentFamilyMember) {
		List<TblResidentFamilyMember> list = tblResidentFamilyMemberService.findByCondition(tblResidentFamilyMember);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/findMemeberByCon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "根据身份证号码查询居民全部家庭成员信息")
	@ApiOperation(value = "根据身份证号码查询居民全部家庭成员信息", notes = "根据身份证号码查询居民全部家庭成员信息")
	public ResponseEntity<?> findMemeberByCon(@RequestBody TblResidentFamilyMember tblResidentFamilyMember) {
		List<TblResidentFamilyMember> list = tblResidentFamilyMemberService
				.findAllMemberByCondition(tblResidentFamilyMember);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/findMemeberWithInsertByCon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "根据身份证号码查询居民全部家庭成员信息且不存在则新增家庭及成员信息")
	@ApiOperation(value = "根据身份证号码查询居民全部家庭成员信息且不存在则新增家庭及成员信息", notes = "根据身份证号码查询居民全部家庭成员信息且不存在则新增家庭及成员信息")
	public ResponseEntity<?> findMemeberWithInsertByCon(@RequestBody TblResidentFamilyMember tblResidentFamilyMember) {
		TblResidentFamily family = new TblResidentFamily();
		family.setFamilynumber(Utility.getFamilyNumber());
		family.setCreateuser(getUserId());
		family.setCreatedate(new Date());
		family.setOrgid(getOrgId());
		List<TblResidentFamilyMember> list = tblResidentFamilyMemberService
				.findAllMemberWithAddByCondition(tblResidentFamilyMember, family);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "新增居民家庭成员信息数据")
	@ApiOperation(value = "新增居民家庭成员信息", notes = "新增居民家庭成员信息")
	public ResponseEntity<?> insert(@RequestBody TblResidentFamilyMember tblResidentFamilyMember) {
		MessModel messModel = new MessModel();
		tblResidentFamilyMember.setCreateuser(getUserId());
		tblResidentFamilyMember.setCreatedate(new Date());
		tblResidentFamilyMember.setOrgid(getOrgId());
		int result = tblResidentFamilyMemberService.insert(tblResidentFamilyMember);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("新增成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "删除居民家庭成员信息数据")
	@ApiOperation(value = "删除居民家庭成员信息数据", notes = "删除居民家庭成员信息数据")
	public ResponseEntity<?> delete(@RequestBody TblResidentFamilyMember tblResidentFamilyMember) {
		MessModel messModel = new MessModel();
		tblResidentFamilyMember.setUpdatedate(new Date());
		tblResidentFamilyMember.setUpdateuser(getUserId());
		tblResidentFamilyMember.setDeletesign(true);
		int result = tblResidentFamilyMemberService.update(tblResidentFamilyMember);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("删除成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

}
