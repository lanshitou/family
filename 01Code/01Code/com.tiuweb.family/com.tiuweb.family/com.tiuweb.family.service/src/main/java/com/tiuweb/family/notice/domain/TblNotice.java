/*
 * @(#) TblNotice  2017-07-26 15:59:14
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
package com.tiuweb.family.notice.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tiuweb.climb.framework.commons.domain.AbstractEntity;

/**
 * 
 * <p>
 * Title: 通知消息
 * </p>
 * <p>
 * Description: TODO 通知消息Entity层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-26 15:59:14
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public class TblNotice extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 标题
	 */
	private String title;
	/**
	 * 通知内容
	 */
	private String content;
	/**
	 * 发布状态:0待发布,1已发布,2已撤回
	 */
	private Short publishstatus;
	/**
	 * 发布范围类别:1所有人可见(默认),2所有医护端账号可见,3本机构内部可见,4所有签约居民和家庭可见
	 */
	private Short publishcategory;
	/**
	 * 发布方式:0实时发布,1定时发布
	 */
	private Short publishmode;
	/**
	 * 定时发布时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	private Date publishtime;
	/**
	 * 发布机构名称
	 */
	private String publishorgname;
	/**
	 * 区域编码
	 */
	private String areacode;
	/**
	 * 数据状态:1-true已删除,0-false未删除
	 */
	private Boolean deletesign;
	/**
	 * 机构外键
	 */
	private String orgid;

	// 通知公告附件
	private List<TblNoticeAttachment> attachments;

	public List<TblNoticeAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<TblNoticeAttachment> attachments) {
		this.attachments = attachments;
	}

	/**
	 * 获取 标题
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 设置 标题
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 获取 通知内容
	 */
	public String getContent() {
		return content;
	}

	/**
	 * 设置 通知内容
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 获取 发布状态:0待发布,1已发布,2已撤回
	 */
	public Short getPublishstatus() {
		return publishstatus;
	}

	/**
	 * 设置 发布状态:0待发布,1已发布,2已撤回
	 */
	public void setPublishstatus(Short publishstatus) {
		this.publishstatus = publishstatus;
	}

	/**
	 * 获取 发布范围类别:1所有人可见(默认),2所有医护端账号可见,3本机构内部可见,4所有签约居民和家庭可见
	 */
	public Short getPublishcategory() {
		return publishcategory;
	}

	/**
	 * 设置 发布范围类别:1所有人可见(默认),2所有医护端账号可见,3本机构内部可见,4所有签约居民和家庭可见
	 */
	public void setPublishcategory(Short publishcategory) {
		this.publishcategory = publishcategory;
	}

	/**
	 * 获取 发布方式:0实时发布,1定时发布
	 */
	public Short getPublishmode() {
		return publishmode;
	}

	/**
	 * 设置 发布方式:0实时发布,1定时发布
	 */
	public void setPublishmode(Short publishmode) {
		this.publishmode = publishmode;
	}

	/**
	 * 获取 定时发布时间
	 */
	public Date getPublishtime() {
		return publishtime;
	}

	/**
	 * 设置 定时发布时间
	 */
	public void setPublishtime(Date publishtime) {
		this.publishtime = publishtime;
	}

	/**
	 * 获取 发布机构名称
	 */
	public String getPublishorgname() {
		return publishorgname;
	}

	/**
	 * 设置 发布机构名称
	 */
	public void setPublishorgname(String publishorgname) {
		this.publishorgname = publishorgname;
	}

	/**
	 * 获取 区域编码
	 */
	public String getAreacode() {
		return areacode;
	}

	/**
	 * 设置 区域编码
	 */
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	/**
	 * 获取 数据状态:1-true已删除,0-false未删除
	 */
	public Boolean getDeletesign() {
		return deletesign;
	}

	/**
	 * 设置 数据状态:1-true已删除,0-false未删除
	 */
	public void setDeletesign(Boolean deletesign) {
		this.deletesign = deletesign;
	}

	/**
	 * 获取 机构外键
	 */
	public String getOrgid() {
		return orgid;
	}

	/**
	 * 设置 机构外键
	 */
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

}