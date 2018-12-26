/*
 * @(#) ITblFamilyDoctorTeamMemberService  2017-07-17 15:20:00
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
package com.tiuweb.family.teams.service;

import com.tiuweb.climb.framework.commons.service.IBaseService;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.family.teams.domain.TblFamilyDoctorTeamMember;

/**
 * 
 * <p>
 * Title: 签约家庭医生团队成员信息
 * </p>
 * <p>
 * Description: TODO 签约家庭医生团队成员信息Service层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 15:20:00
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface ITblFamilyDoctorTeamMemberService extends IBaseService<TblFamilyDoctorTeamMember> {
	/**
	 * 新增团队成员
	 * 
	 * @param tblFamilyDoctorTeamMember
	 * @return
	 * @throws ServiceRunException
	 */
	int insertMember(TblFamilyDoctorTeamMember tblFamilyDoctorTeamMember) throws ServiceRunException;
}
