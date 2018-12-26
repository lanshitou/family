/*
 * @(#) TblResidentContactApi  2017-07-13 14:20:20
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tiuweb.climb.framework.commons.util.MessModel;
import com.tiuweb.climb.framework.commons.util.SearchParams;
import com.tiuweb.climb.framework.config.logs.SystemApiLog;
import com.tiuweb.family.contact.domain.TblResidentContact;
import com.tiuweb.family.contact.domain.TblResidentContactService;
import com.tiuweb.family.contact.domain.TblServicesCustomer;
import com.tiuweb.family.contact.service.ITblResidentContactService;
import com.tiuweb.family.contact.service.IVContactResidentService;
import com.tiuweb.family.plan.domain.TblResidentContactServicePlan;
import com.tiuweb.family.util.Utility;
import com.tiuweb.family.webapi.base.BaseApi;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * <p>
 * Title: 居民签约协议
 * </p>
 * <p>
 * Description: TODO 居民签约协议API层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-13 14:20:20
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Api(value = "TblResidentContactApi", description = "居民签约协议")
@RestController
@RequestMapping(value = "/contact")
public class TblResidentContactApi extends BaseApi {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	private ITblResidentContactService tblResidentContactService;

	@Autowired
	private IVContactResidentService vcontactResidentService;

	@RequestMapping(value = "/findall", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "查询全部居民签约协议")
	@ApiOperation(value = "查询全部居民签约协议", notes = "查询全部居民签约协议")
	public ResponseEntity<?> findAll() {
		return new ResponseEntity<>(tblResidentContactService.findAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/findpage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询居民签约协议")
	@ApiOperation(value = "分页查询居民签约协议", notes = "分页查询居民签约协议")
	public ResponseEntity<?> findPage(@RequestBody SearchParams params) {
		// 仅查看自己所在医疗机构
		params.getSearchParams().put("institutionidentity", getOrgId());
		return new ResponseEntity<>(tblResidentContactService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findContactResidentPage", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "分页查询签约居民信息")
	@ApiOperation(value = "分页查询签约居民信息", notes = "分页查询签约居民信息")
	public ResponseEntity<?> findContactResidentPage(@RequestBody SearchParams params) {
		return new ResponseEntity<>(vcontactResidentService.findPageByCondition(params), HttpStatus.OK);
	}

	@RequestMapping(value = "/findbykey", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "主键查询居民签约协议详情")
	@ApiOperation(value = "主键查询居民签约协议详情", notes = "主键查询居民签约协议详情")
	public ResponseEntity<?> findByKey(@RequestBody TblResidentContact tblResidentContact) {
		return new ResponseEntity<>(tblResidentContactService.findDetailByKey(tblResidentContact), HttpStatus.OK);
	}

	@RequestMapping(value = "/findAppointmentByCon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "根据身份证号查询居民预约服务信息")
	@ApiOperation(value = "根据身份证号查询居民预约服务信息", notes = "根据身份证号查询居民预约服务信息")
	public ResponseEntity<?> findAppointmentByCon(@RequestBody TblServicesCustomer tblServicesCustomer) {
		TblResidentContact contact = tblResidentContactService.findAppointmentByCon(tblServicesCustomer);
		return new ResponseEntity<>(contact, HttpStatus.OK);
	}

	@RequestMapping(value = "/findbycon", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "条件查询居民签约协议")
	@ApiOperation(value = "条件查询居民签约协议", notes = "条件查询居民签约协议")
	public ResponseEntity<?> findInfoByCon(@RequestBody TblResidentContact tblResidentContact) {
		List<TblResidentContact> list = tblResidentContactService.findByCondition(tblResidentContact);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "新增居民签约协议数据")
	@ApiOperation(value = "新增居民签约协议", notes = "新增居民签约协议")
	public ResponseEntity<?> insert(@RequestBody TblResidentContact tblResidentContact) {
		MessModel messModel = new MessModel();

		// 协议生效日期
		Date enableDate = new Date();
		tblResidentContact.setEnabledate(enableDate);

		// 创建属性信息初始化
		tblResidentContact.setContactnumber(Utility.getContactNumber());
		tblResidentContact.setCreateuser(getUserId());
		tblResidentContact.setCreatedate(enableDate);
		tblResidentContact.setOrgid(getOrgId());
		tblResidentContact.setDeletesign(false);
		tblResidentContact.setApplystatus((short) 1);

		int result = tblResidentContactService.insertContact(tblResidentContact, getLoginName());
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("新增成功");
		} else {
			messModel.setMessage("新增失败");
		}
		return new ResponseEntity<>(messModel, HttpStatus.OK);
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "更新居民签约协议数据")
	@ApiOperation(value = "更新居民签约协议数据", notes = "更新居民签约协议数据")
	public ResponseEntity<?> update(@RequestBody TblResidentContact tblResidentContact) {
		MessModel messModel = new MessModel();
		tblResidentContact.setUpdateuser(getUserId());
		tblResidentContact.setUpdatedate(new Date());
		int result = tblResidentContactService.update(tblResidentContact);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("更新成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/renew/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "续签居民签约协议")
	@ApiOperation(value = "续签居民签约协议", notes = "续签居民签约协议")
	public ResponseEntity<?> renew(@PathVariable String id) {
		MessModel messModel = new MessModel();
		TblResidentContact tblResidentContact = new TblResidentContact();
		tblResidentContact.setId(id);
		tblResidentContact.setOrgid(getOrgId());
		tblResidentContact.setUpdateuser(getUserId());
		tblResidentContact.setUpdatedate(new Date());
		tblResidentContact.setApplystatus(Short.valueOf("1"));

		int result = tblResidentContactService.renew(tblResidentContact, getLoginName());
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("续签成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/relieve/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "解除居民签约协议数据(更改协议和服务以及服务计划状态)")
	@ApiOperation(value = "解除居民签约协议数据(更改协议和服务以及服务计划状态)", notes = "解除居民签约协议数据(更改协议和服务以及服务计划状态)")
	public ResponseEntity<?> relieve(@PathVariable String id) {
		MessModel messModel = new MessModel();
		TblResidentContact tblResidentContact = new TblResidentContact();
		tblResidentContact.setId(id);
		tblResidentContact.setUpdateuser(getUserId());
		tblResidentContact.setUpdatedate(new Date());
		tblResidentContact.setApplystatus(Short.valueOf("3"));
		int result = tblResidentContactService.relieve(tblResidentContact);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("解除成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/approval/{id}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "审批居民签约协议数据(更改协议和服务状态并且生成服务计划)")
	@ApiOperation(value = "审批居民签约协议数据(更改协议和服务状态并且生成服务计划)", notes = "审批居民签约协议数据(更改协议和服务状态并且生成服务计划)")
	public ResponseEntity<?> approval(@PathVariable String id) {
		MessModel messModel = new MessModel();
		TblResidentContact tblResidentContact = new TblResidentContact();
		tblResidentContact.setId(id);
		tblResidentContact.setUpdateuser(getUserId());
		tblResidentContact.setUpdatedate(new Date());
		tblResidentContact.setApplystatus(Short.valueOf("1"));
		int result = tblResidentContactService.approval(tblResidentContact, getLoginName());
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("审批成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "签约服务预约(生成预约服务的服务计划)")
	@ApiOperation(value = "签约服务预约(生成预约服务的服务计划)", notes = "签约服务预约(生成预约服务的服务计划)")
	public ResponseEntity<?> subscribe(@RequestBody TblResidentContactServicePlan plan) {
		MessModel messModel = new MessModel();
		int result = tblResidentContactService.subscribe(plan);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("预约成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@SystemApiLog(description = "删除居民签约协议数据")
	@ApiOperation(value = "删除居民签约协议数据", notes = "删除居民签约协议数据")
	public ResponseEntity<?> delete(@RequestBody TblResidentContact tblResidentContact) {
		MessModel messModel = new MessModel();
		tblResidentContact.setUpdateuser(getUserId());
		tblResidentContact.setUpdatedate(new Date());
		int result = tblResidentContactService.delete(tblResidentContact);
		// 判断返回结果
		if (result > 0) {
			messModel.setMessage("删除成功");
			return new ResponseEntity<>(messModel, HttpStatus.OK);
		}
		return null;
	}

}
