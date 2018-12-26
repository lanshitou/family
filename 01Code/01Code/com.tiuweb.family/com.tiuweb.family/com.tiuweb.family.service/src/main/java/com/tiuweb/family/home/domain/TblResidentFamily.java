/*
 * @(#) TblResidentFamily  2017-07-24 12:55:48
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
package com.tiuweb.family.home.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tiuweb.climb.framework.commons.domain.AbstractEntity;

/**
 * 
 * <p>
 * Title: 居民家庭信息
 * </p>
 * <p>
 * Description: TODO 居民家庭信息Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-24 12:55:48
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public class TblResidentFamily extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 家庭编号
	 */
	private String familynumber;
	/**
	 * 居民健康档案外键
	 */
	private String healthidentity;
	/**
	 * 姓名
	 */
	private String membername;
	/**
	 * 身份证编号
	 */
	private String identitynumber;
	/**
	 * 联系电话
	 */
	private String telephone;
	/**
	 * 家庭地址
	 */
	private String address;
	/**
	 * 家庭成员人数
	 */
	private Integer membrcount;
	/**
	 * 签约人数
	 */
	private Integer contactcount;
	/**
	 * 区域编码
	 */
	private String areacode;
	/**
	 * 数据状态:1-true已删除,0-false未删除
	 */
	private Boolean deletesign;
	/**
	 * 机构外键
	 */
	private String orgid;

	/**
	 * 家庭成员信息
	 */
	private List<TblResidentFamilyMember> members = new ArrayList<TblResidentFamilyMember>();

	public List<TblResidentFamilyMember> getMembers() {
		return members;
	}

	public void setMembers(List<TblResidentFamilyMember> members) {
		this.members = members;
	}

	/**
	 * 获取 家庭编号
	 */
	public String getFamilynumber() {
		return familynumber;
	}

	/**
	 * 设置 家庭编号
	 */
	public void setFamilynumber(String familynumber) {
		this.familynumber = familynumber;
	}

	/**
	 * 获取 居民健康档案外键
	 */
	public String getHealthidentity() {
		return healthidentity;
	}

	/**
	 * 设置 居民健康档案外键
	 */
	public void setHealthidentity(String healthidentity) {
		this.healthidentity = healthidentity;
	}

	/**
	 * 获取 姓名
	 */
	public String getMembername() {
		return membername;
	}

	/**
	 * 设置 姓名
	 */
	public void setMembername(String membername) {
		this.membername = membername;
	}

	/**
	 * 获取 身份证编号
	 */
	public String getIdentitynumber() {
		return identitynumber;
	}

	/**
	 * 设置 身份证编号
	 */
	public void setIdentitynumber(String identitynumber) {
		this.identitynumber = identitynumber;
	}

	/**
	 * 获取 联系电话
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * 设置 联系电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * 获取 家庭地址
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * 设置 家庭地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * 获取 家庭成员人数
	 */
	public Integer getMembrcount() {
		return membrcount;
	}

	/**
	 * 设置 家庭成员人数
	 */
	public void setMembrcount(Integer membrcount) {
		this.membrcount = membrcount;
	}

	/**
	 * 获取 签约人数
	 */
	public Integer getContactcount() {
		return contactcount;
	}

	/**
	 * 设置 签约人数
	 */
	public void setContactcount(Integer contactcount) {
		this.contactcount = contactcount;
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