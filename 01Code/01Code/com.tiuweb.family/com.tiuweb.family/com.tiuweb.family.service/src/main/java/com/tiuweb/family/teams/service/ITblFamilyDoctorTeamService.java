/*
 * @(#) ITblFamilyDoctorTeamService  2017-07-17 15:07:46
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
import com.tiuweb.family.teams.domain.TblFamilyDoctorTeam;

/**
 * 
 * <p>
 * Title: 签约家庭医生团队信息
 * </p>
 * <p>
 * Description: TODO 签约家庭医生团队信息Service层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 15:07:46
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface ITblFamilyDoctorTeamService extends IBaseService<TblFamilyDoctorTeam> {

	/**
	 * 保存团队及其成员信息
	 * 
	 * @param tblFamilyDoctorTeam
	 * @return
	 * @throws ServiceRunException
	 */
	int insertTeamWithMember(TblFamilyDoctorTeam tblFamilyDoctorTeam) throws ServiceRunException;
}
