/*
 * @(#) TblNoticeAttachment  2017-07-26 16:11:53
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
import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
  
/**
 * 
 * <p>Title: 通知公告附件</p>
 * <p>Description: TODO 通知公告附件Entity层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-26 16:11:53
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblNoticeAttachment extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 通知公告外键
     */
    private String noticeidentity;      
    /**
     * 附件名称
     */
    private String attachmentname;      
    /**
     * 附件存储路径
     */
    private String storepath;      
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
    
    /**
     * 获取 通知公告外键
     */
    public String getNoticeidentity()
    {
        return noticeidentity;
    } 
        
    /**
     * 设置 通知公告外键
     */
    public void setNoticeidentity(String noticeidentity) 
    {
        this.noticeidentity = noticeidentity;
    }   
    
    /**
     * 获取 附件名称
     */
    public String getAttachmentname()
    {
        return attachmentname;
    } 
        
    /**
     * 设置 附件名称
     */
    public void setAttachmentname(String attachmentname) 
    {
        this.attachmentname = attachmentname;
    }   
    
    /**
     * 获取 附件存储路径
     */
    public String getStorepath()
    {
        return storepath;
    } 
        
    /**
     * 设置 附件存储路径
     */
    public void setStorepath(String storepath) 
    {
        this.storepath = storepath;
    }   
    
    /**
     * 获取 区域编码
     */
    public String getAreacode()
    {
        return areacode;
    } 
        
    /**
     * 设置 区域编码
     */
    public void setAreacode(String areacode) 
    {
        this.areacode = areacode;
    }   
    
    /**
     * 获取 数据状态:1-true已删除,0-false未删除
     */
    public Boolean getDeletesign()
    {
        return deletesign;
    } 
        
    /**
     * 设置 数据状态:1-true已删除,0-false未删除
     */
    public void setDeletesign(Boolean deletesign) 
    {
        this.deletesign = deletesign;
    }   
    
    /**
     * 获取 机构外键
     */
    public String getOrgid()
    {
        return orgid;
    } 
        
    /**
     * 设置 机构外键
     */
    public void setOrgid(String orgid) 
    {
        this.orgid = orgid;
    }   
    
}