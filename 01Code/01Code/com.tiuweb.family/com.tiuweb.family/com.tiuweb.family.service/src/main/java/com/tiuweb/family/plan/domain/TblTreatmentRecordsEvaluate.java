/*
 * @(#) TblTreatmentRecordsEvaluate  2017-08-09 10:36:25
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
package com.tiuweb.family.plan.domain;

import java.io.Serializable;
import java.util.Date;
import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
  
/**
 * 
 * <p>Title: 医疗服务计划服务记录评价</p>
 * <p>Description: TODO 医疗服务计划服务记录评价Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-09 10:36:25
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblTreatmentRecordsEvaluate extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 服务记录外键
     */
    private String servicerecordidentify;      
    /**
     * 签约记录外键
     */
    private String recordidentity;      
    /**
     * 服务计划外键
     */
    private String planidentify;      
    /**
     * 已签约服务外键
     */
    private String serviceidentify;      
    /**
     * 已签约服务名称
     */
    private String servicename;      
    /**
     * 签约协议外键
     */
    private String contactidentify;      
    /**
     * 签约单号
     */
    private String contactnumber;      
    /**
     * 所属家庭医生团队外键
     */
    private String teamidentity;      
    /**
     * 所属家庭医生团队名称
     */
    private String teamname;      
    /**
     * 随访医生外键
     */
    private String servicetreatmentdoctoridentity;      
    /**
     * 随访医生名称
     */
    private String servicetreatmentdoctorname;      
    /**
     * 评价内容
     */
    private String comment;      
    /**
     * 居民账号
     */
    private String loginname;      
    
    /**
     * 获取 服务记录外键
     */
    public String getServicerecordidentify()
    {
        return servicerecordidentify;
    } 
        
    /**
     * 设置 服务记录外键
     */
    public void setServicerecordidentify(String servicerecordidentify) 
    {
        this.servicerecordidentify = servicerecordidentify;
    }   
    
    /**
     * 获取 签约记录外键
     */
    public String getRecordidentity()
    {
        return recordidentity;
    } 
        
    /**
     * 设置 签约记录外键
     */
    public void setRecordidentity(String recordidentity) 
    {
        this.recordidentity = recordidentity;
    }   
    
    /**
     * 获取 服务计划外键
     */
    public String getPlanidentify()
    {
        return planidentify;
    } 
        
    /**
     * 设置 服务计划外键
     */
    public void setPlanidentify(String planidentify) 
    {
        this.planidentify = planidentify;
    }   
    
    /**
     * 获取 已签约服务外键
     */
    public String getServiceidentify()
    {
        return serviceidentify;
    } 
        
    /**
     * 设置 已签约服务外键
     */
    public void setServiceidentify(String serviceidentify) 
    {
        this.serviceidentify = serviceidentify;
    }   
    
    /**
     * 获取 已签约服务名称
     */
    public String getServicename()
    {
        return servicename;
    } 
        
    /**
     * 设置 已签约服务名称
     */
    public void setServicename(String servicename) 
    {
        this.servicename = servicename;
    }   
    
    /**
     * 获取 签约协议外键
     */
    public String getContactidentify()
    {
        return contactidentify;
    } 
        
    /**
     * 设置 签约协议外键
     */
    public void setContactidentify(String contactidentify) 
    {
        this.contactidentify = contactidentify;
    }   
    
    /**
     * 获取 签约单号
     */
    public String getContactnumber()
    {
        return contactnumber;
    } 
        
    /**
     * 设置 签约单号
     */
    public void setContactnumber(String contactnumber) 
    {
        this.contactnumber = contactnumber;
    }   
    
    /**
     * 获取 所属家庭医生团队外键
     */
    public String getTeamidentity()
    {
        return teamidentity;
    } 
        
    /**
     * 设置 所属家庭医生团队外键
     */
    public void setTeamidentity(String teamidentity) 
    {
        this.teamidentity = teamidentity;
    }   
    
    /**
     * 获取 所属家庭医生团队名称
     */
    public String getTeamname()
    {
        return teamname;
    } 
        
    /**
     * 设置 所属家庭医生团队名称
     */
    public void setTeamname(String teamname) 
    {
        this.teamname = teamname;
    }   
    
    /**
     * 获取 随访医生外键
     */
    public String getServicetreatmentdoctoridentity()
    {
        return servicetreatmentdoctoridentity;
    } 
        
    /**
     * 设置 随访医生外键
     */
    public void setServicetreatmentdoctoridentity(String servicetreatmentdoctoridentity) 
    {
        this.servicetreatmentdoctoridentity = servicetreatmentdoctoridentity;
    }   
    
    /**
     * 获取 随访医生名称
     */
    public String getServicetreatmentdoctorname()
    {
        return servicetreatmentdoctorname;
    } 
        
    /**
     * 设置 随访医生名称
     */
    public void setServicetreatmentdoctorname(String servicetreatmentdoctorname) 
    {
        this.servicetreatmentdoctorname = servicetreatmentdoctorname;
    }   
    
    /**
     * 获取 评价内容
     */
    public String getComment()
    {
        return comment;
    } 
        
    /**
     * 设置 评价内容
     */
    public void setComment(String comment) 
    {
        this.comment = comment;
    }   
    
    /**
     * 获取 居民账号
     */
    public String getLoginname()
    {
        return loginname;
    } 
        
    /**
     * 设置 居民账号
     */
    public void setLoginname(String loginname) 
    {
        this.loginname = loginname;
    }   
    
}