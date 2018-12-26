package com.tiuweb.family.webapi.base;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;
import com.tiuweb.climb.framework.commons.api.BaseTokenApi;
import com.tiuweb.climb.framework.config.redis.service.IRedisService;

public abstract class BaseApi extends BaseTokenApi {
	@Autowired
	private IRedisService redisService;

	@SuppressWarnings("unchecked")
	private Map<String, String> getUserInfo() {
		String keyUserId = this.getToken().getUserid();
		if (StringUtils.isNullOrEmpty(keyUserId)) {
			return new HashMap<String, String>();
		}

		String jsonRedis = redisService.get(keyUserId);
		if (StringUtils.isNullOrEmpty(jsonRedis)) {
			return new HashMap<String, String>();
		} else {
			Map target = JSON.parseObject(jsonRedis, Map.class);
			Map user = (Map) target.get("user");
			if (null == user) {
				user = new HashMap<String, String>();
			}
			return user;
		}
	}

	public String getOrgId() {
		String orgId = getUserInfo().get("orgid");
		return StringUtils.isNullOrEmpty(orgId) ? "68F8E766466C4D8787E53B487CB2BB19" : orgId;
	}

	public String getOrgName() {
		String orgName = getUserInfo().get("orgname");
		return StringUtils.isNullOrEmpty(orgName) ? "天网软件" : orgName;
	}

	public String getUserId() {
		String userId = getUserInfo().get("id");
		if (StringUtils.isNullOrEmpty(userId)) {
			userId = getToken().userid;
		}
		if (StringUtils.isNullOrEmpty(userId)) {
			userId = "944553881DC2461D8CD4730F3AFEC440";
		}
		return userId;
	}

	public String getLoginName() {
		String loginName = getUserInfo().get("loginname");
		if (StringUtils.isNullOrEmpty(loginName)) {
			loginName = "admin";
		}
		return loginName;
	}

	public String getUserName() {
		String userName = getUserInfo().get("username");
		if (StringUtils.isNullOrEmpty(userName)) {
			userName = "管理员";
		}
		return userName;
	}
}
