/*
 * @(#) TblServcieItemServiceImpl  2017-07-17 11:10:29
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
package com.tiuweb.family.items.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.logs.SystemServiceLog;
import com.tiuweb.family.common.service.ICodeInfo;
import com.tiuweb.family.items.domain.TblServcieItem;
import com.tiuweb.family.items.domain.TblServcieItemRole;
import com.tiuweb.family.items.mapper.TblServcieItemMapper;
import com.tiuweb.family.items.service.ITblServcieItemRoleService;
import com.tiuweb.family.items.service.ITblServcieItemService;
import com.tiuweb.family.roles.domain.TblMedicalTeamRole;
import com.tiuweb.family.roles.service.ITblMedicalTeamRoleService;
import com.tiuweb.family.util.Constants;

/**
 * 
 * <p>
 * Title: 签约服务汇总信息
 * </p>
 * <p>
 * Description: TODO 签约服务汇总信息Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 11:10:29
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblServcieItemServiceImpl extends BaseServiceImpl<TblServcieItem> implements ITblServcieItemService {

	@Autowired
	private TblServcieItemMapper tblServcieItemMapper;

	@Autowired
	private ITblServcieItemRoleService tblServcieItemRoleService;

	@Autowired
	private ITblMedicalTeamRoleService tblMedicalTeamRoleService;

	@Autowired
	private ICodeInfo<?> codeInfo;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblServcieItem, String> getMapper() {
		return tblServcieItemMapper;
	}

	@Transactional(readOnly = true)
	@Override
	public List<TblServcieItem> findByTeamsKey(Map<String, Object> params) {
		List<TblServcieItem> items = tblServcieItemMapper.findByTeamsKey(params);
		for (TblServcieItem item : items) {
			TblServcieItemRole role = new TblServcieItemRole();
			role.setServcieitemidentity(item.getId());
			item.setItemRole(tblServcieItemRoleService.findByCondition(role));
		}
		return items;
	}

	@Override
	@SystemServiceLog(description = "更新服务项目")
	public int updateItemsWithRole(TblServcieItem tblServcieItem) {
		// 初始化服务项基础数据
		initItemData(tblServcieItem);

		List<TblServcieItemRole> roles = tblServcieItem.getItemRole();

		// 服务项所需医疗角色判断
		if (null == roles || roles.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
					String.format("服务项%s所需医疗人员角色无效", new Object[] { tblServcieItem.getServicename() }));
		}

		this.update(tblServcieItem);

		TblServcieItemRole deleteRoles = new TblServcieItemRole();
		deleteRoles.setServcieitemidentity(tblServcieItem.getId());

		// 更新时先全部删除该服务项所属全部医疗角色,在插入最新的服务项所需医生角色
		tblServcieItemRoleService.delete(deleteRoles);

		return initItemRolesData(tblServcieItem, roles);
	}

	@Override
	@SystemServiceLog(description = "添加服务项目")
	public int inserItemsWithRole(TblServcieItem tblServcieItem) {
		// 初始化服务项基础数据
		initItemData(tblServcieItem);

		List<TblServcieItemRole> roles = tblServcieItem.getItemRole();

		// 服务项所需医疗角色判断
		if (null == roles || roles.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
					String.format("服务项%s所需医疗人员角色无效", new Object[] { tblServcieItem.getServicename() }));
		}

		this.insert(tblServcieItem);

		return initItemRolesData(tblServcieItem, roles);
	}

	@Override
	@Transactional(readOnly = true)
	@SystemServiceLog(description = "查询服务项目详情")
	public TblServcieItem findDetailByPrimaryKey(TblServcieItem tblServcieItem) throws ServiceRunException {
		TblServcieItem detail = this.findByPrimaryKey(tblServcieItem.getId());
		if (null == detail) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "服务项不存在");
		}

		TblServcieItemRole record = new TblServcieItemRole();
		record.setServcieitemidentity(tblServcieItem.getId());
		detail.setItemRole(tblServcieItemRoleService.findByCondition(record));
		return detail;
	}

	/**
	 * 初始化服务项所需医疗角色信息
	 * 
	 * @param tblServcieItem
	 * @param roles
	 * @return
	 */
	private int initItemRolesData(TblServcieItem tblServcieItem, List<TblServcieItemRole> roles) {
		// 查询医生角色
		List<TblMedicalTeamRole> teamRoleList = tblMedicalTeamRoleService.findAll();

		if (null == teamRoleList || teamRoleList.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "医疗劫色基础数据不存在");
		}

		// 设置服务项主键
		for (TblServcieItemRole role : roles) {

			role.setServcieitemidentity(tblServcieItem.getId());

			for (TblMedicalTeamRole teamRole : teamRoleList) {
				if (role.getMedicalteamroleidentity().equalsIgnoreCase(teamRole.getId())) {
					// 设置角色名称
					role.setRolename(teamRole.getRolename());
					break;
				}
			}
		}

		return tblServcieItemRoleService.insertBatch(roles);
	}

	/**
	 * 初始化服务项基础数据
	 * 
	 * @param tblServcieItem
	 */
	private void initItemData(TblServcieItem tblServcieItem) {
		List<?> categorys = codeInfo.getCodeInfoList(Constants.SERVICE_TYPE);

		// 服务项目分类
		if (StringUtils.isNullOrEmpty(tblServcieItem.getServicecategoryid()) || null == categorys
				|| categorys.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "服务分类基础数据不存在");
		}

		for (Object category : categorys) {
			if (getValueByKey(category, "codeinfovalue").equals(tblServcieItem.getServicecategoryid())) {
				tblServcieItem.setServicecategoryname(getValueByKey(category, "codeinfoname"));
				break;
			}
		}

		// 医疗设备类型
		List<?> equipmentType = codeInfo.getCodeInfoList(Constants.EQUIPMENT_TYPE);
		// 服务项所需医疗角色判断
		if (null == equipmentType || equipmentType.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "医疗设备基础数据不存在");
		}

		for (Object equipment : equipmentType) {
			if (getValueByKey(equipment, "codeinfovalue").equals(tblServcieItem.getMedicalequipmentidentity())) {
				tblServcieItem.setMedicalequipmentname(getValueByKey(equipment, "codeinfoname"));
				break;
			}
		}

		// 服务计划周期
		List<?> planPeriodType = codeInfo.getCodeInfoList(Constants.PLAN_PERIOD_TYPE);
		// 服务项所需医疗角色判断
		if (null == equipmentType || equipmentType.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "服务计划周期基础数据不存在");
		}

		for (Object period : planPeriodType) {
			if (getValueByKey(period, "codeinfovalue").equals(tblServcieItem.getPeriodidentity())) {
				tblServcieItem.setPeroidname(getValueByKey(period, "codeinfoname"));
				break;
			}
		}

		// 计费频率
		List<?> serviceCharge = codeInfo.getCodeInfoList(Constants.PAYMENT_FREQUENCY_TYPE);
		// 服务项所需医疗角色判断
		if (null == serviceCharge || serviceCharge.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "服务计基础数据不存在");
		}

		for (Object charge : serviceCharge) {
			if (getValueByKey(charge, "codeinfovalue").equals(tblServcieItem.getServicechargefrequencyidentity())) {
				tblServcieItem.setChargefrequencyname(getValueByKey(charge, "codeinfoname"));
				break;
			}
		}
	}

	private String getValueByKey(Object json, String key) {
		return JSON.parseObject(json.toString()).getString(key);
	}
}
