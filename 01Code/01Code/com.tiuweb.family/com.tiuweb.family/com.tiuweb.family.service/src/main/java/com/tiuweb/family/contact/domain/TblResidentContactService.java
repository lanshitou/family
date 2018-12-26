/*
 * @(#) TblResidentContactService  2017-07-28 11:00:27
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
package com.tiuweb.family.contact.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
import com.tiuweb.family.plan.domain.TblResidentContactServiceDoctor;

/**
 * 
 * <p>
 * Title: 居民签约服务项目
 * </p>
 * <p>
 * Description: TODO 居民签约服务项目Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-28 11:00:27
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public class TblResidentContactService extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 签约协议外键
	 */
	private String contactidentity;
	/**
	 * 签约记录外键
	 */
	private String recordidentity;
	/**
	 * 所属服务外键
	 */
	private String serviceitemidentity;
	/**
	 * 服务类别外键
	 */
	private String servicecategoryid;
	/**
	 * 签约服务项目类别名称
	 */
	private String servicecategoryname;
	/**
	 * 签约状态:0待审批,1已生效,2:到期结束,3提前解除
	 */
	private Short applystatus;
	/**
	 * 签约单号
	 */
	private String contactnumber;
	/**
	 * 服务计划总数
	 */
	private Integer totalcount;
	/**
	 * 已提供服务次数
	 */
	private Integer servicecount;
	/**
	 * 服务名称
	 */
	private String servicename;
	/**
	 * 首次服务时间
	 */
	private Date firstservicedate;
	/**
	 * 是否基础服务,基础服务各团队必须提供:0否,1是
	 */
	private Boolean isfoundation;
	/**
	 * 服务对象类别:0全部家庭成员,1仅本人,2其他
	 */
	private Short customercategory;
	/**
	 * 服务方式:0上门服务,1预约就诊
	 */
	private Short isappointment;
	/**
	 * 服务项目审批状态0:待审批,1已生效
	 */
	private Boolean isenable;
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
	private String serviceperiodidentity;
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
	 * 费用支付频率名称
	 */
	private String servicechargefrequencyname;
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
	 * 服务对象
	 */
	private List<TblServicesCustomer> customer = new ArrayList<TblServicesCustomer>();

	/**
	 * 服务项所属医生及其角色
	 */
	private List<TblResidentContactServiceDoctor> doctors = new ArrayList<TblResidentContactServiceDoctor>();

	public List<TblResidentContactServiceDoctor> getDoctors() {
		return doctors;
	}

	public void setDoctors(List<TblResidentContactServiceDoctor> doctors) {
		this.doctors = doctors;
	}

	public List<TblServicesCustomer> getCustomer() {
		return customer;
	}

	public void setCustomer(List<TblServicesCustomer> customer) {
		this.customer = customer;
	}

	/**
	 * 获取 签约协议外键
	 */
	public String getContactidentity() {
		return contactidentity;
	}

	/**
	 * 设置 签约协议外键
	 */
	public void setContactidentity(String contactidentity) {
		this.contactidentity = contactidentity;
	}

	/**
	 * 获取 签约记录外键
	 */
	public String getRecordidentity() {
		return recordidentity;
	}

	/**
	 * 设置 签约记录外键
	 */
	public void setRecordidentity(String recordidentity) {
		this.recordidentity = recordidentity;
	}

	/**
	 * 获取 所属服务外键
	 */
	public String getServiceitemidentity() {
		return serviceitemidentity;
	}

	/**
	 * 设置 所属服务外键
	 */
	public void setServiceitemidentity(String serviceitemidentity) {
		this.serviceitemidentity = serviceitemidentity;
	}

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
	 * 获取 签约状态:0待审批,1已生效,2:到期结束,3提前解除
	 */
	public Short getApplystatus() {
		return applystatus;
	}

	/**
	 * 设置 签约状态:0待审批,1已生效,2:到期结束,3提前解除
	 */
	public void setApplystatus(Short applystatus) {
		this.applystatus = applystatus;
	}

	/**
	 * 获取 签约单号
	 */
	public String getContactnumber() {
		return contactnumber;
	}

	/**
	 * 设置 签约单号
	 */
	public void setContactnumber(String contactnumber) {
		this.contactnumber = contactnumber;
	}

	/**
	 * 获取 服务计划总数
	 */
	public Integer getTotalcount() {
		return totalcount;
	}

	/**
	 * 设置 服务计划总数
	 */
	public void setTotalcount(Integer totalcount) {
		this.totalcount = totalcount;
	}

	/**
	 * 获取 已提供服务次数
	 */
	public Integer getServicecount() {
		return servicecount;
	}

	/**
	 * 设置 已提供服务次数
	 */
	public void setServicecount(Integer servicecount) {
		this.servicecount = servicecount;
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
	 * 获取 首次服务时间
	 */
	public Date getFirstservicedate() {
		return firstservicedate;
	}

	/**
	 * 设置 首次服务时间
	 */
	public void setFirstservicedate(Date firstservicedate) {
		this.firstservicedate = firstservicedate;
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
	 * 获取 服务对象类别:0全部家庭成员,1仅本人,2其他
	 */
	public Short getCustomercategory() {
		return customercategory;
	}

	/**
	 * 设置 服务对象类别:0全部家庭成员,1仅本人,2其他
	 */
	public void setCustomercategory(Short customercategory) {
		this.customercategory = customercategory;
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
	public String getServiceperiodidentity() {
		return serviceperiodidentity;
	}

	/**
	 * 设置 服务计划周期外键:0每年,1每月,2每周
	 */
	public void setServiceperiodidentity(String serviceperiodidentity) {
		this.serviceperiodidentity = serviceperiodidentity;
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
	 * 获取 费用支付频率名称
	 */
	public String getServicechargefrequencyname() {
		return servicechargefrequencyname;
	}

	/**
	 * 设置 费用支付频率名称
	 */
	public void setServicechargefrequencyname(String servicechargefrequencyname) {
		this.servicechargefrequencyname = servicechargefrequencyname;
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

}