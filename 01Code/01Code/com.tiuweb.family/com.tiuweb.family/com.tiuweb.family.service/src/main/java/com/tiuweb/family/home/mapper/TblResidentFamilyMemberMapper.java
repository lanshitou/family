/*
 * @(#) TblResidentFamilyMemberMapper  2017-07-19 11:43:25
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
package com.tiuweb.family.home.mapper;

import java.util.List;

import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.family.home.domain.TblResidentFamilyMember;

/**
 * 
 * <p>
 * Title: 居民家庭成员信息
 * </p>
 * <p>
 * Description: TODO 居民家庭成员信息Mapper层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-19 11:43:25
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public interface TblResidentFamilyMemberMapper extends BaseMapper<TblResidentFamilyMember, String> {
	
	/**
	 * 根据参数查询全部家庭成员
	 * 
	 * @param record
	 * @return
	 */
	List<TblResidentFamilyMember> findAllMemberByCondition(TblResidentFamilyMember record);
}