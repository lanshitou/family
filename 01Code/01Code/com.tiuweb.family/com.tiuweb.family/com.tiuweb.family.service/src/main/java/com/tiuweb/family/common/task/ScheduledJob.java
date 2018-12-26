/*
 * @(#) TblResidentContact  2017-08-7 9:27:03
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
package com.tiuweb.family.common.task;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * <p>
 * Title: task类
 * </p>
 * <p>
 * Description: TODO 完成定时任务的启动
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-7 9:27:03
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public class ScheduledJob implements Job {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	private SimpleDateFormat dateFormat() {
		return new SimpleDateFormat("HH:mm:ss");
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		System.out.println("AAAA: The time is now " + dateFormat().format(new Date()));
	}
}
