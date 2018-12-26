/*
 * @(#) TblResidentContactRecordServiceImpl  2017-07-13 15:03:51
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
package com.tiuweb.family.common.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.mysql.jdbc.StringUtils;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.commons.util.UserToken;
import com.tiuweb.climb.framework.config.logs.SystemServiceLog;
import com.tiuweb.family.common.ApiUrl;
import com.tiuweb.family.common.service.ISystemInfoService;
import com.tiuweb.family.util.Utility;

/**
 * 
 * <p>
 * Title: 系統服务
 * </p>
 * <p>
 * Description: TODO 签约协议历史记录Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-13 15:03:51
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class SystemInfoService<T> implements ISystemInfoService<T> {
	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiUrl apiUrl;

	@Override
	@SystemServiceLog(description = "用户密码修改")
	public int updatePwd(Map<String, Object> pwdInfo, UserToken token) throws ServiceRunException {
		if (null == pwdInfo || pwdInfo.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "参数无效");
		}
		if (!pwdInfo.containsKey("newpassword") || !pwdInfo.containsKey("password")) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "原始密码或者新密码无效");
		}

		if (null == token || StringUtils.isNullOrEmpty(token.getUserid())
				|| StringUtils.isNullOrEmpty(token.getUsertoken())) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "当前用户token无效,请重新登录尝试");
		}

		pwdInfo.put("id", token.getUserid());
		// 请求状态获取
		try {
			ResponseEntity<String> rss = Utility.request(restTemplate, token, apiUrl.getUpdatePwdUrl(), HttpMethod.POST,
					pwdInfo);

			if (rss.getStatusCode() == HttpStatus.OK) {
				rss = Utility.request(restTemplate, token, apiUrl.getLogoutUrl(), HttpMethod.POST,
						new HashMap<String, Object>());
			}
		} catch (RestClientException e) {
			throw new ServiceRunException(RunExceptionEnum.RESP_UPDATE_FAILED.getCode(), "原始密码错误或者系统异常");
		}
		return 0;
	}

	@Override
	@Transactional(readOnly = true)
	@SystemServiceLog(description = "已登录用户退出系统")
	public int logout(UserToken token) throws ServiceRunException {
		try {
			if (null == token || StringUtils.isNullOrEmpty(token.getUserid())
					|| StringUtils.isNullOrEmpty(token.getUsertoken())) {
				throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "当前用户token无效,请重新登录尝试");
			}

			ResponseEntity<String> rss = Utility.request(restTemplate, token, apiUrl.getLogoutUrl(), HttpMethod.POST,
					new HashMap<String, Object>());

			if (rss.getStatusCode() == HttpStatus.OK) {
				return 1;
			}
		} catch (RestClientException e) {
			throw new ServiceRunException(RunExceptionEnum.RESP_UPDATE_FAILED.getCode(), "用户未登录");
		}
		return 0;
	}
}
