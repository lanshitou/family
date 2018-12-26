/*
 * @(#) TblTransferTreatmentContrastServiceImpl  2017-08-07 10:53:30
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
package com.tiuweb.family.plan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.family.plan.domain.TblTransferTreatmentContrast;
import com.tiuweb.family.plan.mapper.TblTransferTreatmentContrastMapper;
import com.tiuweb.family.plan.service.ITblTransferTreatmentContrastService;

/**
 * 
 * <p>
 * Title: 转诊服务对照关系信息
 * </p>
 * <p>
 * Description: TODO 转诊服务对照关系信息Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-07 10:53:30
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblTransferTreatmentContrastServiceImpl extends BaseServiceImpl<TblTransferTreatmentContrast>
		implements ITblTransferTreatmentContrastService {

	@Autowired
	private TblTransferTreatmentContrastMapper tblTransferTreatmentContrastMapper;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblTransferTreatmentContrast, String> getMapper() {
		return tblTransferTreatmentContrastMapper;
	}

	@Override
	public int insertContras(TblTransferTreatmentContrast tblTransferTreatmentContrast) throws ServiceRunException {
		initValidation(tblTransferTreatmentContrast);

		return insert(tblTransferTreatmentContrast);
	}

	@Override
	public int updateContras(TblTransferTreatmentContrast tblTransferTreatmentContrast) throws ServiceRunException {
		if (StringUtils.isNullOrEmpty(tblTransferTreatmentContrast.getId())) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "不识别的对照关系");
		}
		if (StringUtils.isNullOrEmpty(tblTransferTreatmentContrast.getCurrentorgidentity())
				|| StringUtils.isNullOrEmpty(tblTransferTreatmentContrast.getCountyorgname())) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "请指定当前医疗机构");
		}

		if (StringUtils.isNullOrEmpty(tblTransferTreatmentContrast.getCountyorgidentity())
				|| StringUtils.isNullOrEmpty(tblTransferTreatmentContrast.getCountyorgname())) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "请指定县级对接医院");
		}

		if (StringUtils.isNullOrEmpty(tblTransferTreatmentContrast.getCityorgidentity())
				|| StringUtils.isNullOrEmpty(tblTransferTreatmentContrast.getCityorgname())) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "请指定市级对接医院");
		}
		return update(tblTransferTreatmentContrast);
	}

	/**
	 * 对照关系有效性校验
	 * 
	 * @param tblTransferTreatmentContrast
	 */
	private void initValidation(TblTransferTreatmentContrast tblTransferTreatmentContrast) {
		if (StringUtils.isNullOrEmpty(tblTransferTreatmentContrast.getCurrentorgidentity())
				|| StringUtils.isNullOrEmpty(tblTransferTreatmentContrast.getCountyorgname())) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "请指定当前医疗机构");
		}

		if (StringUtils.isNullOrEmpty(tblTransferTreatmentContrast.getCountyorgidentity())
				|| StringUtils.isNullOrEmpty(tblTransferTreatmentContrast.getCountyorgname())) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "请指定县级对接医院");
		}

		if (StringUtils.isNullOrEmpty(tblTransferTreatmentContrast.getCityorgidentity())
				|| StringUtils.isNullOrEmpty(tblTransferTreatmentContrast.getCityorgname())) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "请指定市级对接医院");
		}

		TblTransferTreatmentContrast query = new TblTransferTreatmentContrast();
		query.setCurrentorgidentity(tblTransferTreatmentContrast.getCurrentorgidentity());
		List<TblTransferTreatmentContrast> exists = tblTransferTreatmentContrastMapper.findByCondition(query);

		if (null != exists && !exists.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
					String.format("%s 已经存在转诊对照关系", new Object[] { tblTransferTreatmentContrast.getCountyorgname() }));
		}
	}
}
