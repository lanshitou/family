/*
 * @(#) TblResidentContact  2017-08-7 9:27:03
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
package com.tiuweb.family.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * <p>
 * Title: climburl配置
 * </p>
 * <p>
 * Description: climburl配置
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-7 9:27:03
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Configuration
public class ApiUrl {
	/**
	 * 查询全部机构信息
	 */
	private final static String ORIGIN_ORG = "/porg/findall";

	/**
	 * 更新密码
	 */
	private static final String URL_UPDATE_PWD = "/puser/update/pw";

	/**
	 * 用户登出
	 */
	private static final String URL_LOGOUT = "/loginmagner/logout";

	/**
	 * 系统用户新增
	 */
	private static final String URL_ADD_USER = "/syspuser/addpuser";

	/**
	 * 系统授权
	 */
	private static final String URL_GRANT_SYSTEM = "/syspusersys/addpusersys";

	/**
	 * 角色授权
	 */
	private static final String URL_GRANT_ROLE = "/sysrole/addpuserrole";

	@Value("${climb.logasyncurl}")
	private String climbUrl;

	@Value("${climb.appno}")
	private String appno;

	@Value("${roles.ordinaryDoctorRole}")
	private String ordinaryDoctorRole;

	public String getClimbUrl() {
		return climbUrl;
	}

	public String getAppno() {
		return appno;
	}

	public void setAppno(String appno) {
		this.appno = appno;
	}

	public String getOrdinaryDoctorRole() {
		return ordinaryDoctorRole;
	}

	public void setOrdinaryDoctorRole(String ordinaryDoctorRole) {
		this.ordinaryDoctorRole = ordinaryDoctorRole;
	}

	public void setClimbUrl(String climbUrl) {
		this.climbUrl = climbUrl;
	}

	public String getOriginOrgUrl() {
		return climbUrl.concat(ORIGIN_ORG);
	}

	public String getUpdatePwdUrl() {
		return climbUrl.concat(URL_UPDATE_PWD);
	}

	public String getLogoutUrl() {
		return climbUrl.concat(URL_LOGOUT);
	}

	public String getAddUserUrl() {
		return climbUrl.concat(URL_ADD_USER);
	}

	public String getGrantSystemUrl() {
		return climbUrl.concat(URL_GRANT_SYSTEM);
	}

	public String getGrantRoleUrl() {
		return climbUrl.concat(URL_GRANT_ROLE);
	}
}