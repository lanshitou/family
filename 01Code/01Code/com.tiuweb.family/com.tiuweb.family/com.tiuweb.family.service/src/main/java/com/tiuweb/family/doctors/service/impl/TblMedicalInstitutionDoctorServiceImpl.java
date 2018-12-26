/*
 * @(#) TblMedicalInstitutionDoctorServiceImpl  2017-07-17 16:35:03
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
package com.tiuweb.family.doctors.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.commons.util.UserToken;
import com.tiuweb.climb.framework.config.logs.SystemServiceLog;
import com.tiuweb.family.common.ApiUrl;
import com.tiuweb.family.doctors.domain.TblMedicalInstitutionDoctor;
import com.tiuweb.family.doctors.mapper.TblMedicalInstitutionDoctorMapper;
import com.tiuweb.family.doctors.service.ITblMedicalInstitutionDoctorService;
import com.tiuweb.family.util.Utility;

/**
 * 
 * <p>
 * Title: 签约家庭医生信息
 * </p>
 * <p>
 * Description: TODO 签约家庭医生信息Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 16:35:03
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblMedicalInstitutionDoctorServiceImpl extends BaseServiceImpl<TblMedicalInstitutionDoctor>
		implements ITblMedicalInstitutionDoctorService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiUrl apiUrl;

	@Autowired
	private TblMedicalInstitutionDoctorMapper tblMedicalInstitutionDoctorMapper;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblMedicalInstitutionDoctor, String> getMapper() {
		return tblMedicalInstitutionDoctorMapper;
	}

	@Override
	@SystemServiceLog(description = "新增签约医生并授权")
	public int insertDoctor(TblMedicalInstitutionDoctor record, UserToken token) throws ServiceRunException {
		if (null == token || StringUtils.isNullOrEmpty(token.getUserid())
				|| StringUtils.isNullOrEmpty(token.getUsertoken())) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "当前用户token无效,请重新登录尝试");
		}

		TblMedicalInstitutionDoctor query = new TblMedicalInstitutionDoctor();
		query.setPaccount(record.getPaccount());
		List<TblMedicalInstitutionDoctor> exists = findByCondition(query);
		if (null != exists && !exists.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
					String.format("%s已存在", new Object[] { record.getPaccount() }));
		}

		try {
			Map<String, Object> user = new HashMap<String, Object>();
			user.put("username", record.getDoctorname());
			user.put("usersex", "SEX_".concat(String.valueOf(record.getSex())));
			user.put("userphone", record.getPaccount());
			user.put("useremail", record.getEmail());
			user.put("loginname", record.getPaccount());
			user.put("usercrednum", record.getCertificatenumber());
			// user.put("createuser", token.getUserid());
			user.put("orgid", record.getInstitutionidentity());

			// 用户新增
			ResponseEntity<String> rss = Utility.request(restTemplate, token, apiUrl.getAddUserUrl(), HttpMethod.POST,
					user);

			if (rss.getStatusCode() == HttpStatus.OK) {
				// 设置主键
				String userID = JSON.parseObject(rss.getBody()).getString("message");
				record.setId(userID);
				record.setPuserid(userID);

				Map<String, Object> system = new HashMap<String, Object>();
				system.put("appno", apiUrl.getAppno());
				system.put("userid", userID);
				system.put("sysid", apiUrl.getAppno());
				system.put("createuser", token.getUserid());

				// 系统授权
				rss = Utility.request(restTemplate, token, apiUrl.getGrantSystemUrl(), HttpMethod.POST, system);

				Map<String, Object> role = new HashMap<String, Object>();
				role.put("appno", apiUrl.getAppno());
				role.put("userid", record.getId());
				role.put("roleid", apiUrl.getOrdinaryDoctorRole());
				role.put("createuser", token.getUserid());

				// 角色授权
				rss = Utility.request(restTemplate, token, apiUrl.getGrantRoleUrl(), HttpMethod.POST, role);
				insert(record);
			}

		} catch (RestClientException e) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
					String.format("%s已存在", new Object[] { record.getPaccount() }));
		}
		return 1;
	}
}
