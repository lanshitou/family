/*
 * @(#) TblServcieItemRoleServiceImpl  2017-07-17 11:02:23
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
package com.tiuweb.family.items.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.config.redis.service.IRedisService;
import com.tiuweb.family.items.domain.TblServcieItemRole;
import com.tiuweb.family.items.mapper.TblServcieItemRoleMapper;
import com.tiuweb.family.items.service.ITblServcieItemRoleService;

/**
 * 
 * <p>Title: 签约服务项目所需角色</p>
 * <p>Description: TODO 签约服务项目所需角色Impl层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-17 11:02:23
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
@Service
@Transactional
public class TblServcieItemRoleServiceImpl extends BaseServiceImpl<TblServcieItemRole> implements ITblServcieItemRoleService {

	@Autowired
	private TblServcieItemRoleMapper tblServcieItemRoleMapper;
	
	/*需要redis服务请打开*/
	//@Autowired
	//private IRedisService redisService;
	
	@Override
	protected BaseMapper<TblServcieItemRole, String> getMapper() {
		return tblServcieItemRoleMapper;
	}
}
