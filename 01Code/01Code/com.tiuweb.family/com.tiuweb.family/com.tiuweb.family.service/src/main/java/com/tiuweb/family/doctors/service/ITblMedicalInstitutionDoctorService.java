/*
 * @(#) ITblMedicalInstitutionDoctorService  2017-07-17 16:35:03
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
package com.tiuweb.family.doctors.service;

import com.tiuweb.climb.framework.commons.service.IBaseService;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.commons.util.UserToken;
import com.tiuweb.family.doctors.domain.TblMedicalInstitutionDoctor;

/**
 * 
 * <p>
 * Title: 签约家庭医生信息
 * </p>
 * <p>
 * Description: TODO 签约家庭医生信息Service层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 16:35:03
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface ITblMedicalInstitutionDoctorService extends IBaseService<TblMedicalInstitutionDoctor> {
	/**
	 * 新增用户并授权
	 * 
	 * @param record
	 * @param token
	 * @return
	 * @throws ServiceRunException
	 */
	int insertDoctor(TblMedicalInstitutionDoctor record, UserToken token) throws ServiceRunException;
}
