package com.tiuweb.family.common.service;

import java.util.Map;

import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.commons.util.UserToken;

public interface ISystemInfoService<T> {

	/**
	 * 更新用户密码
	 * 
	 * @param pwdInfo
	 * @param token
	 * @return
	 * @throws ServiceRunException
	 */
	int updatePwd(Map<String, Object> pwdInfo, UserToken token) throws ServiceRunException;

	/**
	 * 已登录用户退出
	 * 
	 * @param token
	 * @return
	 * @throws ServiceRunException
	 */
	int logout(UserToken token) throws ServiceRunException;
}
