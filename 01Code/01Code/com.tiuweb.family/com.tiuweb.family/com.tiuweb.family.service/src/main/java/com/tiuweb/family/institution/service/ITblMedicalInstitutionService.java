/*
 * @(#) ITblMedicalInstitutionService  2017-07-10 15:00:42
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
package com.tiuweb.family.institution.service;

import java.util.List;

import com.tiuweb.climb.framework.commons.service.IBaseService;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.family.institution.domain.TblMedicalInstitution;

/**
 * 
 * <p>
 * Title: 签约医疗机构
 * </p>
 * <p>
 * Description: TODO 签约医疗机构Service层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-10 15:00:42
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface ITblMedicalInstitutionService extends IBaseService<TblMedicalInstitution> {
	/**
	 * 医疗机构及其科室、医生职级、服务区域信息添加
	 * 
	 * @param record
	 * @return
	 * @throws ServiceRunException
	 */
	int insertWithTrans(TblMedicalInstitution record) throws ServiceRunException;

	/**
	 * 查询框架全部组织机构
	 * 
	 * @return
	 * @throws ServiceRunException
	 */
	List<?> findOriginAll() throws ServiceRunException;
}
