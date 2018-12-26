package com.tiuweb.family.common.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.mysql.jdbc.StringUtils;
import com.tiuweb.climb.framework.commons.util.CodeInfoUtil;
import com.tiuweb.climb.framework.config.logs.SystemServiceLog;
import com.tiuweb.family.common.service.ICodeInfo;

@Service
@Transactional
public class CodeInfoService implements ICodeInfo {

	@Override
	@Transactional(readOnly = true)
	@SystemServiceLog(description = "根据字典类型获取字典信息列表")
	public List<?> getCodeInfoList(String codeType) {
		if (StringUtils.isNullOrEmpty(codeType)) {
			return new ArrayList();
		}
		return JSON.parseArray(CodeInfoUtil.getDictionaryByDistType(codeType));
	}

	@Override
	@Transactional(readOnly = true)
	@SystemServiceLog(description = "根据字典类型和字典值获取字典信息列表")
	public List<?> getCodeInfo(String codeType, String codeValue) {
		if (StringUtils.isNullOrEmpty(codeType) || StringUtils.isNullOrEmpty(codeValue)) {
			return new ArrayList();
		}
		return JSON.parseArray(CodeInfoUtil.getDictionaryByValue(codeType, codeValue));
	}

}
