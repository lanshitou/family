/*
 * @(#) TblResidentContactServiceDoctor  2017-08-02 15:40:51
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
 * <p>Title: 签约服务项目医生信息</p>
 * <p>Description: TODO 签约服务项目医生信息Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-02 15:40:51
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblResidentContactServiceDoctor extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 签约协议外键
     */
    private String contactidentity;      
    /**
     * 签约记录外键
     */
    private String recordidentity;      
    /**
     * 签约服务项目外键
     */
    private String servcieitemdetailidentity;      
    /**
     * 所属签约团队成员外键
     */
    private String teamdoctoridentity;      
    /**
     * 团队签约医生外键
     */
    private String doctoridentity;      
    /**
     * 签约医生名称
     */
    private String doctorname;      
    /**
     * 医生角色外键
     */
    private String doctorroleidentity;      
    /**
     * 医生角色名称
     */
    private String doctorrolename;      
    /**
     * 数据状态:1-true已删除,0-false未删除
     */
    private Boolean deletesign;      
    /**
     * 区域编码
     */
    private String areacode;      
    /**
     * 机构外键
     */
    private String orgid;      
    
    /**
     * 获取 签约协议外键
     */
    public String getContactidentity()
    {
        return contactidentity;
    } 
        
    /**
     * 设置 签约协议外键
     */
    public void setContactidentity(String contactidentity) 
    {
        this.contactidentity = contactidentity;
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
     * 获取 签约服务项目外键
     */
    public String getServcieitemdetailidentity()
    {
        return servcieitemdetailidentity;
    } 
        
    /**
     * 设置 签约服务项目外键
     */
    public void setServcieitemdetailidentity(String servcieitemdetailidentity) 
    {
        this.servcieitemdetailidentity = servcieitemdetailidentity;
    }   
    
    /**
     * 获取 所属签约团队成员外键
     */
    public String getTeamdoctoridentity()
    {
        return teamdoctoridentity;
    } 
        
    /**
     * 设置 所属签约团队成员外键
     */
    public void setTeamdoctoridentity(String teamdoctoridentity) 
    {
        this.teamdoctoridentity = teamdoctoridentity;
    }   
    
    /**
     * 获取 团队签约医生外键
     */
    public String getDoctoridentity()
    {
        return doctoridentity;
    } 
        
    /**
     * 设置 团队签约医生外键
     */
    public void setDoctoridentity(String doctoridentity) 
    {
        this.doctoridentity = doctoridentity;
    }   
    
    /**
     * 获取 签约医生名称
     */
    public String getDoctorname()
    {
        return doctorname;
    } 
        
    /**
     * 设置 签约医生名称
     */
    public void setDoctorname(String doctorname) 
    {
        this.doctorname = doctorname;
    }   
    
    /**
     * 获取 医生角色外键
     */
    public String getDoctorroleidentity()
    {
        return doctorroleidentity;
    } 
        
    /**
     * 设置 医生角色外键
     */
    public void setDoctorroleidentity(String doctorroleidentity) 
    {
        this.doctorroleidentity = doctorroleidentity;
    }   
    
    /**
     * 获取 医生角色名称
     */
    public String getDoctorrolename()
    {
        return doctorrolename;
    } 
        
    /**
     * 设置 医生角色名称
     */
    public void setDoctorrolename(String doctorrolename) 
    {
        this.doctorrolename = doctorrolename;
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