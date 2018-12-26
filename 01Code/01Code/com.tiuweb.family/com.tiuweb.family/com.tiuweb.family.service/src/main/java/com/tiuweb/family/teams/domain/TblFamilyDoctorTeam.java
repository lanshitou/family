/*
 * @(#) TblFamilyDoctorTeam  2017-07-20 19:19:39
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
package com.tiuweb.family.teams.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.tiuweb.climb.framework.commons.domain.AbstractEntity;

/**
 * 
 * <p>
 * Title: 家庭医生团队信息
 * </p>
 * <p>
 * Description: TODO 家庭医生团队信息Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-20 19:19:39
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public class TblFamilyDoctorTeam extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 团队编号
	 */
	private String teamnumber;
	/**
	 * 团队名称
	 */
	private String teamname;
	/**
	 * 签约总数
	 */
	private Long contactcount;
	/**
	 * 所属医疗机构外键
	 */
	private String institutionidentity;
	/**
	 * 所属医疗机构名称
	 */
	private String institutionname;
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
	 * 团队成员信息
	 */
	private List<TblFamilyDoctorTeamMember> member = new ArrayList<TblFamilyDoctorTeamMember>();

	public List<TblFamilyDoctorTeamMember> getMember() {
		return member;
	}

	public void setMember(List<TblFamilyDoctorTeamMember> member) {
		this.member = member;
	}

	/**
	 * 获取 团队编号
	 */
	public String getTeamnumber() {
		return teamnumber;
	}

	/**
	 * 设置 团队编号
	 */
	public void setTeamnumber(String teamnumber) {
		this.teamnumber = teamnumber;
	}

	/**
	 * 获取 团队名称
	 */
	public String getTeamname() {
		return teamname;
	}

	/**
	 * 设置 团队名称
	 */
	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	/**
	 * 获取 签约总数
	 */
	public Long getContactcount() {
		return contactcount;
	}

	/**
	 * 设置 签约总数
	 */
	public void setContactcount(Long contactcount) {
		this.contactcount = contactcount;
	}

	/**
	 * 获取 所属医疗机构外键
	 */
	public String getInstitutionidentity() {
		return institutionidentity;
	}

	/**
	 * 设置 所属医疗机构外键
	 */
	public void setInstitutionidentity(String institutionidentity) {
		this.institutionidentity = institutionidentity;
	}

	/**
	 * 获取 所属医疗机构名称
	 */
	public String getInstitutionname() {
		return institutionname;
	}

	/**
	 * 设置 所属医疗机构名称
	 */
	public void setInstitutionname(String institutionname) {
		this.institutionname = institutionname;
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