/*
 * @(#) TblServcieItem  2017-07-25 19:30:24
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
package com.tiuweb.family.items.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tiuweb.climb.framework.commons.domain.AbstractEntity;

/**
 * 
 * <p>
 * Title: 居民签约服务项目
 * </p>
 * <p>
 * Description: TODO 居民签约服务项目Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-25 19:30:24
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public class TblServcieItem extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 服务类别外键
	 */
	private String servicecategoryid;
	/**
	 * 签约服务项目类别名称
	 */
	private String servicecategoryname;
	/**
	 * 服务名称
	 */
	private String servicename;
	/**
	 * 是否基础服务,基础服务各团队必须提供:0否,1是
	 */
	private Boolean isfoundation;
	/**
	 * 服务方式:0上门服务,1预约就诊
	 */
	private Short isappointment;
	/**
	 * 服务项目审批状态0:待审批,1已生效
	 */
	private Boolean isenable;
	/**
	 * 服务详情
	 */
	private String detail;
	/**
	 * 服务计划计量:1..N次
	 */
	private Short servicemesure;
	/**
	 * 服务计划计量单位
	 */
	private String servicemesureunit;
	/**
	 * 服务计划周期外键:0每年,1每月,2每周
	 */
	private String periodidentity;
	/**
	 * 服务周期名称
	 */
	private String peroidname;
	/**
	 * 预计费用
	 */
	private Long servicecharge;
	/**
	 * 预计费用单位:元
	 */
	private String servicechargeunit;
	/**
	 * 费用支付频率外键
	 */
	private String servicechargefrequencyidentity;
	/**
	 * 计费频率名称
	 */
	private String chargefrequencyname;
	/**
	 * 服务奖励
	 */
	private Long servicereward;
	/**
	 * 所需医疗人员计量
	 */
	private Short doctormesure;
	/**
	 * 所需医疗人员计量单位:人/次
	 */
	private String doctormesureunit;
	/**
	 * 医疗设备外键
	 */
	private String medicalequipmentidentity;
	/**
	 * 医疗设备名称
	 */
	private String medicalequipmentname;
	/**
	 * 审批人
	 */
	private String approvalidentity;
	/**
	 * 审批机构或团队
	 */
	private String auditorgid;
	/**
	 * 申请机构或团队
	 */
	private String applyorgid;
	/**
	 * 数据状态:1-true已删除,0-false未删除
	 */
	private Boolean deletesign;
	/**
	 * 区域编码
	 */
	private String areacode;
	/**
	 * 机构外键
	 */
	private String orgid;

	/**
	 * 服务项所需医疗角色
	 */
	private List<TblServcieItemRole> itemRole = new ArrayList<TblServcieItemRole>();

	/**
	 * 获取 服务类别外键
	 */
	public String getServicecategoryid() {
		return servicecategoryid;
	}

	/**
	 * 设置 服务类别外键
	 */
	public void setServicecategoryid(String servicecategoryid) {
		this.servicecategoryid = servicecategoryid;
	}

	/**
	 * 获取 签约服务项目类别名称
	 */
	public String getServicecategoryname() {
		return servicecategoryname;
	}

	/**
	 * 设置 签约服务项目类别名称
	 */
	public void setServicecategoryname(String servicecategoryname) {
		this.servicecategoryname = servicecategoryname;
	}

	/**
	 * 获取 服务名称
	 */
	public String getServicename() {
		return servicename;
	}

	/**
	 * 设置 服务名称
	 */
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	/**
	 * 获取 是否基础服务,基础服务各团队必须提供:0否,1是
	 */
	public Boolean getIsfoundation() {
		return isfoundation;
	}

	/**
	 * 设置 是否基础服务,基础服务各团队必须提供:0否,1是
	 */
	public void setIsfoundation(Boolean isfoundation) {
		this.isfoundation = isfoundation;
	}

	/**
	 * 获取 服务方式:0上门服务,1预约就诊
	 */
	public Short getIsappointment() {
		return isappointment;
	}

	/**
	 * 设置 服务方式:0上门服务,1预约就诊
	 */
	public void setIsappointment(Short isappointment) {
		this.isappointment = isappointment;
	}

	/**
	 * 获取 服务项目审批状态0:待审批,1已生效
	 */
	public Boolean getIsenable() {
		return isenable;
	}

	/**
	 * 设置 服务项目审批状态0:待审批,1已生效
	 */
	public void setIsenable(Boolean isenable) {
		this.isenable = isenable;
	}

	/**
	 * 获取 服务详情
	 */
	public String getDetail() {
		return detail;
	}

	/**
	 * 设置 服务详情
	 */
	public void setDetail(String detail) {
		this.detail = detail;
	}

	/**
	 * 获取 服务计划计量:1..N次
	 */
	public Short getServicemesure() {
		return servicemesure;
	}

	/**
	 * 设置 服务计划计量:1..N次
	 */
	public void setServicemesure(Short servicemesure) {
		this.servicemesure = servicemesure;
	}

	/**
	 * 获取 服务计划计量单位
	 */
	public String getServicemesureunit() {
		return servicemesureunit;
	}

	/**
	 * 设置 服务计划计量单位
	 */
	public void setServicemesureunit(String servicemesureunit) {
		this.servicemesureunit = servicemesureunit;
	}

	/**
	 * 获取 服务计划周期外键:0每年,1每月,2每周
	 */
	public String getPeriodidentity() {
		return periodidentity;
	}

	/**
	 * 设置 服务计划周期外键:0每年,1每月,2每周
	 */
	public void setPeriodidentity(String periodidentity) {
		this.periodidentity = periodidentity;
	}

	/**
	 * 获取 服务周期名称
	 */
	public String getPeroidname() {
		return peroidname;
	}

	/**
	 * 设置 服务周期名称
	 */
	public void setPeroidname(String peroidname) {
		this.peroidname = peroidname;
	}

	/**
	 * 获取 预计费用
	 */
	public Long getServicecharge() {
		return servicecharge;
	}

	/**
	 * 设置 预计费用
	 */
	public void setServicecharge(Long servicecharge) {
		this.servicecharge = servicecharge;
	}

	/**
	 * 获取 预计费用单位:元
	 */
	public String getServicechargeunit() {
		return servicechargeunit;
	}

	/**
	 * 设置 预计费用单位:元
	 */
	public void setServicechargeunit(String servicechargeunit) {
		this.servicechargeunit = servicechargeunit;
	}

	/**
	 * 获取 费用支付频率外键
	 */
	public String getServicechargefrequencyidentity() {
		return servicechargefrequencyidentity;
	}

	/**
	 * 设置 费用支付频率外键
	 */
	public void setServicechargefrequencyidentity(String servicechargefrequencyidentity) {
		this.servicechargefrequencyidentity = servicechargefrequencyidentity;
	}

	/**
	 * 获取 计费频率名称
	 */
	public String getChargefrequencyname() {
		return chargefrequencyname;
	}

	/**
	 * 设置 计费频率名称
	 */
	public void setChargefrequencyname(String chargefrequencyname) {
		this.chargefrequencyname = chargefrequencyname;
	}

	/**
	 * 获取 服务奖励
	 */
	public Long getServicereward() {
		return servicereward;
	}

	/**
	 * 设置 服务奖励
	 */
	public void setServicereward(Long servicereward) {
		this.servicereward = servicereward;
	}

	/**
	 * 获取 所需医疗人员计量
	 */
	public Short getDoctormesure() {
		return doctormesure;
	}

	/**
	 * 设置 所需医疗人员计量
	 */
	public void setDoctormesure(Short doctormesure) {
		this.doctormesure = doctormesure;
	}

	/**
	 * 获取 所需医疗人员计量单位:人/次
	 */
	public String getDoctormesureunit() {
		return doctormesureunit;
	}

	/**
	 * 设置 所需医疗人员计量单位:人/次
	 */
	public void setDoctormesureunit(String doctormesureunit) {
		this.doctormesureunit = doctormesureunit;
	}

	/**
	 * 获取 医疗设备外键
	 */
	public String getMedicalequipmentidentity() {
		return medicalequipmentidentity;
	}

	/**
	 * 设置 医疗设备外键
	 */
	public void setMedicalequipmentidentity(String medicalequipmentidentity) {
		this.medicalequipmentidentity = medicalequipmentidentity;
	}

	/**
	 * 获取 医疗设备名称
	 */
	public String getMedicalequipmentname() {
		return medicalequipmentname;
	}

	/**
	 * 设置 医疗设备名称
	 */
	public void setMedicalequipmentname(String medicalequipmentname) {
		this.medicalequipmentname = medicalequipmentname;
	}

	/**
	 * 获取 审批人
	 */
	public String getApprovalidentity() {
		return approvalidentity;
	}

	/**
	 * 设置 审批人
	 */
	public void setApprovalidentity(String approvalidentity) {
		this.approvalidentity = approvalidentity;
	}

	/**
	 * 获取 审批机构或团队
	 */
	public String getAuditorgid() {
		return auditorgid;
	}

	/**
	 * 设置 审批机构或团队
	 */
	public void setAuditorgid(String auditorgid) {
		this.auditorgid = auditorgid;
	}

	/**
	 * 获取 申请机构或团队
	 */
	public String getApplyorgid() {
		return applyorgid;
	}

	/**
	 * 设置 申请机构或团队
	 */
	public void setApplyorgid(String applyorgid) {
		this.applyorgid = applyorgid;
	}

	/**
	 * 获取 数据状态:1-true已删除,0-false未删除
	 */
	public Boolean getDeletesign() {
		return deletesign;
	}

	/**
	 * 设置 数据状态:1-true已删除,0-false未删除
	 */
	public void setDeletesign(Boolean deletesign) {
		this.deletesign = deletesign;
	}

	/**
	 * 获取 区域编码
	 */
	public String getAreacode() {
		return areacode;
	}

	/**
	 * 设置 区域编码
	 */
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	/**
	 * 获取 机构外键
	 */
	public String getOrgid() {
		return orgid;
	}

	/**
	 * 设置 机构外键
	 */
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

	public List<TblServcieItemRole> getItemRole() {
		return itemRole;
	}

	public void setItemRole(List<TblServcieItemRole> itemRole) {
		this.itemRole = itemRole;
	}

}