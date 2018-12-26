package com.tiuweb.family.common.task;

import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.tiuweb.family.common.SpringContextHolder;
import com.tiuweb.family.notice.domain.TblNotice;
import com.tiuweb.family.notice.service.ITblNoticeService;
import com.tiuweb.family.remind.domain.TblSystemMessageWarn;
import com.tiuweb.family.remind.service.ITblSystemMessageWarnService;

public class NoticePublishJob implements Job {

	@SuppressWarnings("unchecked")
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		List<TblSystemMessageWarn> target = (List<TblSystemMessageWarn>) context.getJobDetail().getJobDataMap()
				.get("target");
		ITblSystemMessageWarnService warnService = SpringContextHolder.getBean(ITblSystemMessageWarnService.class);
		warnService.insertBatch(target);

		// 更新通知状态
		TblNotice notice = (TblNotice) context.getJobDetail().getJobDataMap().get("notice");
		notice.setPublishstatus(Short.valueOf("1"));
		ITblNoticeService ITblNoticeService = SpringContextHolder.getBean(ITblNoticeService.class);
		ITblNoticeService.update(notice);
	}

}
