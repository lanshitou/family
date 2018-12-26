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

import java.util.Map;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

import com.mysql.jdbc.StringUtils;
import com.tiuweb.family.util.Utility;

/**
 * 
 * <p>
 * Title: 通用task调度
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
@Component
@Configuration
public class CommonScheduler {
	protected Logger logger = LoggerFactory.getLogger(this.getClass().getName());

	@Value("${task.orgRefreshTrigger}")
	private String orgRefreshTrigger;

	@Value("${task.planOverDueTrigger}")
	private String planOverDueTrigger;

	@Autowired
	private SchedulerFactoryBean schedulerFactoryBean;

	public void scheduleJobs() throws SchedulerException {
		Scheduler scheduler = schedulerFactoryBean.getScheduler();
		startOrgRefreshTriggerJob(scheduler);
		startPlanOverDueTriggerJob(scheduler);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void scheduleJob(Map<String, Object> params, Trigger trigger, Class clazzJob) throws SchedulerException {
		JobDetail jobDetail = JobBuilder.newJob(clazzJob).withIdentity(Utility.get32UUID(), "group1").build();
		jobDetail.getJobDataMap().putAll(params);
		schedulerFactoryBean.getScheduler().scheduleJob(jobDetail, trigger);
	}

	private void startOrgRefreshTriggerJob(Scheduler scheduler) throws SchedulerException {
		if (StringUtils.isNullOrEmpty(orgRefreshTrigger)) {
			logger.error("config of task.orgRefreshTrigger invalid");
		} else {
			JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class).withIdentity("job1", "group1").build();
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(orgRefreshTrigger);
			CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
					.withSchedule(scheduleBuilder).build();
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}
	}

	private void startPlanOverDueTriggerJob(Scheduler scheduler) throws SchedulerException {
		if (StringUtils.isNullOrEmpty(planOverDueTrigger)) {
			logger.error("config of task.planOverDueTrigger invalid");
		} else {
			JobDetail jobDetail = JobBuilder.newJob(ScheduledJob.class).withIdentity("job2", "group1").build();
			CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(planOverDueTrigger);
			CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity("trigger2", "group1")
					.withSchedule(scheduleBuilder).build();
			scheduler.scheduleJob(jobDetail, cronTrigger);
		}
	}
}