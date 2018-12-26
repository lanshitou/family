/*
 * @(#) TblFamilyDoctorTeamMember  2017-08-04 18:02:22
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
package com.tiuweb.family.teams.domain;

import java.io.Serializable;
import java.util.Date;
import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
  
/**
 * 
 * <p>Title: 签约家庭医生团队成员信息</p>
 * <p>Description: TODO 签约家庭医生团队成员信息Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-04 18:02:22
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblFamilyDoctorTeamMember extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 所属团队外键
     */
    private String medicalteamidentity;      
    /**
     * 所属团队名称
     */
    private String medicalteamname;      
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
     * 责任医生服务优先级
     */
    private Short teamdoctorresponsibilityorder;      
    /**
     * 提供服务总数
     */
    private Long servicecount;      
    /**
     * 接受签约总数
     */
    private Long contactcount;      
    /**
     * 累计评分总数
     */
    private Long scorecount;      
    /**
     * 是否队长
     */
    private Boolean iscaptain;      
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
     * 获取 所属团队外键
     */
    public String getMedicalteamidentity()
    {
        return medicalteamidentity;
    } 
        
    /**
     * 设置 所属团队外键
     */
    public void setMedicalteamidentity(String medicalteamidentity) 
    {
        this.medicalteamidentity = medicalteamidentity;
    }   
    
    /**
     * 获取 所属团队名称
     */
    public String getMedicalteamname()
    {
        return medicalteamname;
    } 
        
    /**
     * 设置 所属团队名称
     */
    public void setMedicalteamname(String medicalteamname) 
    {
        this.medicalteamname = medicalteamname;
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
     * 获取 责任医生服务优先级
     */
    public Short getTeamdoctorresponsibilityorder()
    {
        return teamdoctorresponsibilityorder;
    } 
        
    /**
     * 设置 责任医生服务优先级
     */
    public void setTeamdoctorresponsibilityorder(Short teamdoctorresponsibilityorder) 
    {
        this.teamdoctorresponsibilityorder = teamdoctorresponsibilityorder;
    }   
    
    /**
     * 获取 提供服务总数
     */
    public Long getServicecount()
    {
        return servicecount;
    } 
        
    /**
     * 设置 提供服务总数
     */
    public void setServicecount(Long servicecount) 
    {
        this.servicecount = servicecount;
    }   
    
    /**
     * 获取 接受签约总数
     */
    public Long getContactcount()
    {
        return contactcount;
    } 
        
    /**
     * 设置 接受签约总数
     */
    public void setContactcount(Long contactcount) 
    {
        this.contactcount = contactcount;
    }   
    
    /**
     * 获取 累计评分总数
     */
    public Long getScorecount()
    {
        return scorecount;
    } 
        
    /**
     * 设置 累计评分总数
     */
    public void setScorecount(Long scorecount) 
    {
        this.scorecount = scorecount;
    }   
    
    /**
     * 获取 是否队长
     */
    public Boolean getIscaptain()
    {
        return iscaptain;
    } 
        
    /**
     * 设置 是否队长
     */
    public void setIscaptain(Boolean iscaptain) 
    {
        this.iscaptain = iscaptain;
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