/*
 * @(#) TblSystemMessageConfigServiceImpl  2017-08-10 11:50:46
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
package com.tiuweb.family.remind.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.commons.utilabstract.BasePubFun;
import com.tiuweb.climb.framework.config.logs.SystemServiceLog;
import com.tiuweb.family.remind.domain.TblSystemMessageConfig;
import com.tiuweb.family.remind.mapper.TblSystemMessageConfigMapper;
import com.tiuweb.family.remind.service.ITblSystemMessageConfigService;

/**
 * 
 * <p>
 * Title: 消息提醒设置
 * </p>
 * <p>
 * Description: TODO 消息提醒设置Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-10 11:50:46
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblSystemMessageConfigServiceImpl extends BaseServiceImpl<TblSystemMessageConfig>
		implements ITblSystemMessageConfigService {

	@Autowired
	private TblSystemMessageConfigMapper tblSystemMessageConfigMapper;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblSystemMessageConfig, String> getMapper() {
		return tblSystemMessageConfigMapper;
	}

	@Override
	@SystemServiceLog(description = "保存(更新或者新增)消息提醒设置")
	public int save(List<TblSystemMessageConfig> records, String userId) throws ServiceRunException {
		if (records == null || records.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
					RunExceptionEnum.REQ_NOT_PARA.getDescription());
		}

		String id = null;
		for (TblSystemMessageConfig record : records) {
			id = record.getId();
			record.setLoginidentity(userId);
			record.setUpdatedate(new Date());

			// 新增 == null ? BasePubFun.getUUID() : record.getId().trim()
			if (StringUtils.isNullOrEmpty(id)) {
				record.setId(BasePubFun.getUUID());
				insert(record);
			}
			// 更新
			else {
				update(record);
			}
		}
		return 1;
	}
}
