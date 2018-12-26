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
package com.tiuweb.family.common.listener;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.tiuweb.family.common.task.CommonScheduler;

/**
 * 
 * <p>
 * Title: task监听类
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
@Configuration
public class SchedulerListener implements ApplicationListener<ContextRefreshedEvent> {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Autowired
	public CommonScheduler myScheduler;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		try {
			myScheduler.scheduleJobs();
		} catch (SchedulerException e) {
			logger.error("error onApplicationEvent for class of SchedulerListener", e);
		}
	}

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		return schedulerFactoryBean;
	}
}
