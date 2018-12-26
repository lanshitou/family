/*
 * @(#) TblSystemMessageWarn  2017-08-10 11:35:00
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
package com.tiuweb.family.remind.domain;

import java.io.Serializable;
import java.util.Date;
import com.tiuweb.climb.framework.commons.domain.AbstractEntity;

/**
 * 
 * <p>Title: 系统消息提醒信息</p>
 * <p>Description: TODO 系统消息提醒信息Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-10 11:35:00
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblSystemMessageWarn extends AbstractEntity implements Cloneable , Serializable  
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 消息类别:1居民问诊,2问诊评论,3投诉建议,4通知公告,5签约申请,6计划更改,7服务执行
     */
    private Short category;      
    /**
     * 消息状态:0未读，1已读
     */
    private Boolean status;      
    /**
     * 消息来源外键标识
     */
    private String msgidentity;      
    /**
     * 消息内容
     */
    private String content;      
    /**
     * 接收人外键
     */
    private String acceptuser;      
    /**
     * 接收人账号
     */
    private String acceptloginname;      
    /**
     * 读取时间
     */
    private Date readdate;      
    /**
     * 发送人外键
     */
    private String senduser;      
    /**
     * 发送人账号
     */
    private String sendloginname;      
    
    /**
     * 获取 消息类别:1居民问诊,2问诊评论,3投诉建议,4通知公告,5签约申请,6计划更改,7服务执行
     */
    public Short getCategory()
    {
        return category;
    } 
        
    /**
     * 设置 消息类别:1居民问诊,2问诊评论,3投诉建议,4通知公告,5签约申请,6计划更改,7服务执行
     */
    public void setCategory(Short category) 
    {
        this.category = category;
    }   
    
    /**
     * 获取 消息状态:0未读，1已读
     */
    public Boolean getStatus()
    {
        return status;
    } 
        
    /**
     * 设置 消息状态:0未读，1已读
     */
    public void setStatus(Boolean status) 
    {
        this.status = status;
    }   
    
    /**
     * 获取 消息来源外键标识
     */
    public String getMsgidentity()
    {
        return msgidentity;
    } 
        
    /**
     * 设置 消息来源外键标识
     */
    public void setMsgidentity(String msgidentity) 
    {
        this.msgidentity = msgidentity;
    }   
    
    /**
     * 获取 消息内容
     */
    public String getContent()
    {
        return content;
    } 
        
    /**
     * 设置 消息内容
     */
    public void setContent(String content) 
    {
        this.content = content;
    }   
    
    /**
     * 获取 接收人外键
     */
    public String getAcceptuser()
    {
        return acceptuser;
    } 
        
    /**
     * 设置 接收人外键
     */
    public void setAcceptuser(String acceptuser) 
    {
        this.acceptuser = acceptuser;
    }   
    
    /**
     * 获取 接收人账号
     */
    public String getAcceptloginname()
    {
        return acceptloginname;
    } 
        
    /**
     * 设置 接收人账号
     */
    public void setAcceptloginname(String acceptloginname) 
    {
        this.acceptloginname = acceptloginname;
    }   
    
    /**
     * 获取 读取时间
     */
    public Date getReaddate()
    {
        return readdate;
    } 
        
    /**
     * 设置 读取时间
     */
    public void setReaddate(Date readdate) 
    {
        this.readdate = readdate;
    }   
    
    /**
     * 获取 发送人外键
     */
    public String getSenduser()
    {
        return senduser;
    } 
        
    /**
     * 设置 发送人外键
     */
    public void setSenduser(String senduser) 
    {
        this.senduser = senduser;
    }   
    
    /**
     * 获取 发送人账号
     */
    public String getSendloginname()
    {
        return sendloginname;
    } 
        
    /**
     * 设置 发送人账号
     */
    public void setSendloginname(String sendloginname) 
    {
        this.sendloginname = sendloginname;
    }   
    
    @Override  
    public TblSystemMessageWarn clone() {  
    	TblSystemMessageWarn address = null;  
        try {  
            address = (TblSystemMessageWarn) super.clone();  
        } catch (CloneNotSupportedException e) {  
            e.printStackTrace();  
        }  
        return address;  
    } 
}