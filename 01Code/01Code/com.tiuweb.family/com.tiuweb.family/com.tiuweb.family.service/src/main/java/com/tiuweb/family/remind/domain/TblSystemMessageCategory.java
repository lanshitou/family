/*
 * @(#) TblSystemMessageCategory  2017-08-10 10:22:33
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
 * <p>Title: 系统消息提醒分类</p>
 * <p>Description: TODO 系统消息提醒分类Entity层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-08-10 10:22:33
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblSystemMessageCategory extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 分类名称
     */
    private String category;      
    /**
     * 状态:0停用,1启用
     */
    private Boolean status;      
    
    /**
     * 获取 分类名称
     */
    public String getCategory()
    {
        return category;
    } 
        
    /**
     * 设置 分类名称
     */
    public void setCategory(String category) 
    {
        this.category = category;
    }   
    
    /**
     * 获取 状态:0停用,1启用
     */
    public Boolean getStatus()
    {
        return status;
    } 
        
    /**
     * 设置 状态:0停用,1启用
     */
    public void setStatus(Boolean status) 
    {
        this.status = status;
    }   
    
}