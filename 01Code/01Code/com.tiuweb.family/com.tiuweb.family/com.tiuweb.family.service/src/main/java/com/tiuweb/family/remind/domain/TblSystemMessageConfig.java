/*
 * @(#) TblSystemMessageConfig  2017-08-10 15:30:46
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

import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
  
/**
 * 
 * <p>Title: 消息提醒设置</p>
 * <p>Description: TODO 消息提醒设置Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-10 15:30:46
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblSystemMessageConfig extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 用户外键
     */
    private String loginidentity;      
    /**
     * 提醒消息分类外键
     */
    private String messagecategoryidentity;      
    /**
     * 提醒消息分类名称
     */
    private String messagecategoryname;      
    /**
     * 提醒设置状态:0关闭,1开启
     */
    private Boolean configstatus;      
    /**
     * 短信通知状态:0关闭,1开启
     */
    private Boolean messagestatus;      
    /**
     * 邮件通知状态:0关闭,1开启
     */
    private Boolean mailstatus;      
    
    /**
     * 获取 用户外键
     */
    public String getLoginidentity()
    {
        return loginidentity;
    } 
        
    /**
     * 设置 用户外键
     */
    public void setLoginidentity(String loginidentity) 
    {
        this.loginidentity = loginidentity;
    }   
    
    /**
     * 获取 提醒消息分类外键
     */
    public String getMessagecategoryidentity()
    {
        return messagecategoryidentity;
    } 
        
    /**
     * 设置 提醒消息分类外键
     */
    public void setMessagecategoryidentity(String messagecategoryidentity) 
    {
        this.messagecategoryidentity = messagecategoryidentity;
    }   
    
    /**
     * 获取 提醒消息分类名称
     */
    public String getMessagecategoryname()
    {
        return messagecategoryname;
    } 
        
    /**
     * 设置 提醒消息分类名称
     */
    public void setMessagecategoryname(String messagecategoryname) 
    {
        this.messagecategoryname = messagecategoryname;
    }   
    
    /**
     * 获取 提醒设置状态:0关闭,1开启
     */
    public Boolean getConfigstatus()
    {
        return configstatus;
    } 
        
    /**
     * 设置 提醒设置状态:0关闭,1开启
     */
    public void setConfigstatus(Boolean configstatus) 
    {
        this.configstatus = configstatus;
    }   
    
    /**
     * 获取 短信通知状态:0关闭,1开启
     */
    public Boolean getMessagestatus()
    {
        return messagestatus;
    } 
        
    /**
     * 设置 短信通知状态:0关闭,1开启
     */
    public void setMessagestatus(Boolean messagestatus) 
    {
        this.messagestatus = messagestatus;
    }   
    
    /**
     * 获取 邮件通知状态:0关闭,1开启
     */
    public Boolean getMailstatus()
    {
        return mailstatus;
    } 
        
    /**
     * 设置 邮件通知状态:0关闭,1开启
     */
    public void setMailstatus(Boolean mailstatus) 
    {
        this.mailstatus = mailstatus;
    }   
    
}