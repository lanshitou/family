/*
 * @(#) TblResidentContactRecord  2017-07-13 16:20:07
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
package com.tiuweb.family.contact.domain;

import java.io.Serializable;
import java.util.Date;
import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
  
/**
 * 
 * <p>Title: 签约家庭医生团队成员信息</p>
 * <p>Description: TODO 签约家庭医生团队成员信息Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-13 16:20:07
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblResidentContactRecord extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 是否居民申请(签约协议)来源:0否-医护端添加,1是-居民申请
     */
    private Boolean isresidentapply;      
    /**
     * 是否续签:0-false否.1-true是
     */
    private Boolean isrenew;      
    /**
     * 申请人外键
     */
    private String applyidentity;      
    /**
     * 审核人外键
     */
    private String authidentity;      
    /**
     * 申请日期
     */
    private Date applydate;      
    /**
     * 生效日期
     */
    private Date enabledate;      
    /**
     * 截止日期
     */
    private Date enddate;      
    
    /**
     * 获取 是否居民申请(签约协议)来源:0否-医护端添加,1是-居民申请
     */
    public Boolean getIsresidentapply()
    {
        return isresidentapply;
    } 
        
    /**
     * 设置 是否居民申请(签约协议)来源:0否-医护端添加,1是-居民申请
     */
    public void setIsresidentapply(Boolean isresidentapply) 
    {
        this.isresidentapply = isresidentapply;
    }   
    
    /**
     * 获取 是否续签:0-false否.1-true是
     */
    public Boolean getIsrenew()
    {
        return isrenew;
    } 
        
    /**
     * 设置 是否续签:0-false否.1-true是
     */
    public void setIsrenew(Boolean isrenew) 
    {
        this.isrenew = isrenew;
    }   
    
    /**
     * 获取 申请人外键
     */
    public String getApplyidentity()
    {
        return applyidentity;
    } 
        
    /**
     * 设置 申请人外键
     */
    public void setApplyidentity(String applyidentity) 
    {
        this.applyidentity = applyidentity;
    }   
    
    /**
     * 获取 审核人外键
     */
    public String getAuthidentity()
    {
        return authidentity;
    } 
        
    /**
     * 设置 审核人外键
     */
    public void setAuthidentity(String authidentity) 
    {
        this.authidentity = authidentity;
    }   
    
    /**
     * 获取 申请日期
     */
    public Date getApplydate()
    {
        return applydate;
    } 
        
    /**
     * 设置 申请日期
     */
    public void setApplydate(Date applydate) 
    {
        this.applydate = applydate;
    }   
    
    /**
     * 获取 生效日期
     */
    public Date getEnabledate()
    {
        return enabledate;
    } 
        
    /**
     * 设置 生效日期
     */
    public void setEnabledate(Date enabledate) 
    {
        this.enabledate = enabledate;
    }   
    
    /**
     * 获取 截止日期
     */
    public Date getEnddate()
    {
        return enddate;
    } 
        
    /**
     * 设置 截止日期
     */
    public void setEnddate(Date enddate) 
    {
        this.enddate = enddate;
    }   
    
}