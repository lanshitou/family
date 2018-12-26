/*
 * @(#) TblResidentContact  2017-08-08 17:20:02
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
import com.tiuweb.family.teams.domain.TblFamilyDoctorTeamMember;
  
/**
 * 
 * <p>Title: 居民签约协议</p>
 * <p>Description: TODO 居民签约协议Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-08 17:20:02
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblResidentContact extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 签约单号
     */
    private String contactnumber;      
    /**
     * 签署协议居民家庭成员外键
     */
    private String contactmemberidentity;      
    /**
     * 居民家庭外键
     */
    private String residentfamilyidentity;      
    /**
     * 居民家庭编号
     */
    private String residentfamilynumber;      
    /**
     * 签约记录外键
     */
    private String recordidentity;      
    /**
     * 居民健康档案外键
     */
    private String residenthealtharchivesidentity;      
    /**
     * 居民健康档案编号
     */
    private String residenthealtharchivesnumber;      
    /**
     * 所属医疗机构外键
     */
    private String institutionidentity;      
    /**
     * 所属医疗机构名称
     */
    private String institutionname;      
    /**
     * 所属家庭医生团队外键
     */
    private String teamidentity;      
    /**
     * 所属家庭医生团队名称
     */
    private String teamname;      
    /**
     * 所属家庭医生团队签约医生外键
     */
    private String teamdoctoridentity;      
    /**
     * 所属家庭医生团队签约医生名称
     */
    private String teamdoctorname;      
    /**
     * 签约类型:0个人,1家庭
     */
    private Short signtype;      
    /**
     * 签约状态:0待审批,1已生效,2:到期结束,3提前解除
     */
    private Short applystatus;      
    /**
     * 服务计划总数
     */
    private Integer totalcount;      
    /**
     * 已提供服务次数
     */
    private Integer servicecount;      
    /**
     * 协议生效日期
     */
    private Date enabledate;      
    /**
     * 申请人姓名
     */
    private String applyname;      
    /**
     * 身份证编号
     */
    private String identitynumber;      
    /**
     * 出生日期
     */
    private Date birthdate;      
    /**
     * 年龄
     */
    private Short age;      
    /**
     * 性别:0未知的性别,1男性,2女性,9未说明的性别
     */
    private Short sex;      
    /**
     * 联系电话
     */
    private String telephone;      
    /**
     * 家庭地址
     */
    private String address;      
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
	 * 当前签约协议记录
	 */
	private TblResidentContactRecord tblResidentContactRecord;

	/**
	 * 本次签约所属医疗品团队成员
	 */
	private List<TblFamilyDoctorTeamMember> tblFamilyDoctorTeamRoles = new ArrayList<TblFamilyDoctorTeamMember>();

	/**
	 * 本次签约全部服务
	 */
	private List<TblResidentContactService> services = new ArrayList<TblResidentContactService>();

	/**
	 * 协议预约人的基本信息:姓名、性别、年龄、联系电话、家庭住址
	 */
	private Map<String, Object> appoinement = new HashMap<String, Object>();

	public List<TblResidentContactService> getServices() {
		return services;
	}

	public void setServices(List<TblResidentContactService> services) {
		this.services = services;
	}

	public List<TblFamilyDoctorTeamMember> getTblFamilyDoctorTeamRoles() {
		return tblFamilyDoctorTeamRoles;
	}

	public void setTblFamilyDoctorTeamRoles(List<TblFamilyDoctorTeamMember> tblFamilyDoctorTeamRoles) {
		this.tblFamilyDoctorTeamRoles = tblFamilyDoctorTeamRoles;
	}

	public TblResidentContactRecord getTblResidentContactRecord() {
		return tblResidentContactRecord;
	}

	public void setTblResidentContactRecord(TblResidentContactRecord tblResidentContactRecord) {
		this.tblResidentContactRecord = tblResidentContactRecord;
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
     * 获取 签署协议居民家庭成员外键
     */
    public String getContactmemberidentity()
    {
        return contactmemberidentity;
    } 
        
    /**
     * 设置 签署协议居民家庭成员外键
     */
    public void setContactmemberidentity(String contactmemberidentity) 
    {
        this.contactmemberidentity = contactmemberidentity;
    }   
    
    /**
     * 获取 居民家庭外键
     */
    public String getResidentfamilyidentity()
    {
        return residentfamilyidentity;
    } 
        
    /**
     * 设置 居民家庭外键
     */
    public void setResidentfamilyidentity(String residentfamilyidentity) 
    {
        this.residentfamilyidentity = residentfamilyidentity;
    }   
    
    /**
     * 获取 居民家庭编号
     */
    public String getResidentfamilynumber()
    {
        return residentfamilynumber;
    } 
        
    /**
     * 设置 居民家庭编号
     */
    public void setResidentfamilynumber(String residentfamilynumber) 
    {
        this.residentfamilynumber = residentfamilynumber;
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
     * 获取 居民健康档案外键
     */
    public String getResidenthealtharchivesidentity()
    {
        return residenthealtharchivesidentity;
    } 
        
    /**
     * 设置 居民健康档案外键
     */
    public void setResidenthealtharchivesidentity(String residenthealtharchivesidentity) 
    {
        this.residenthealtharchivesidentity = residenthealtharchivesidentity;
    }   
    
    /**
     * 获取 居民健康档案编号
     */
    public String getResidenthealtharchivesnumber()
    {
        return residenthealtharchivesnumber;
    } 
        
    /**
     * 设置 居民健康档案编号
     */
    public void setResidenthealtharchivesnumber(String residenthealtharchivesnumber) 
    {
        this.residenthealtharchivesnumber = residenthealtharchivesnumber;
    }   
    
    /**
     * 获取 所属医疗机构外键
     */
    public String getInstitutionidentity()
    {
        return institutionidentity;
    } 
        
    /**
     * 设置 所属医疗机构外键
     */
    public void setInstitutionidentity(String institutionidentity) 
    {
        this.institutionidentity = institutionidentity;
    }   
    
    /**
     * 获取 所属医疗机构名称
     */
    public String getInstitutionname()
    {
        return institutionname;
    } 
        
    /**
     * 设置 所属医疗机构名称
     */
    public void setInstitutionname(String institutionname) 
    {
        this.institutionname = institutionname;
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
     * 获取 所属家庭医生团队签约医生外键
     */
    public String getTeamdoctoridentity()
    {
        return teamdoctoridentity;
    } 
        
    /**
     * 设置 所属家庭医生团队签约医生外键
     */
    public void setTeamdoctoridentity(String teamdoctoridentity) 
    {
        this.teamdoctoridentity = teamdoctoridentity;
    }   
    
    /**
     * 获取 所属家庭医生团队签约医生名称
     */
    public String getTeamdoctorname()
    {
        return teamdoctorname;
    } 
        
    /**
     * 设置 所属家庭医生团队签约医生名称
     */
    public void setTeamdoctorname(String teamdoctorname) 
    {
        this.teamdoctorname = teamdoctorname;
    }   
    
    /**
     * 获取 签约类型:0个人,1家庭
     */
    public Short getSigntype()
    {
        return signtype;
    } 
        
    /**
     * 设置 签约类型:0个人,1家庭
     */
    public void setSigntype(Short signtype) 
    {
        this.signtype = signtype;
    }   
    
    /**
     * 获取 签约状态:0待审批,1已生效,2:到期结束,3提前解除
     */
    public Short getApplystatus()
    {
        return applystatus;
    } 
        
    /**
     * 设置 签约状态:0待审批,1已生效,2:到期结束,3提前解除
     */
    public void setApplystatus(Short applystatus) 
    {
        this.applystatus = applystatus;
    }   
    
    /**
     * 获取 服务计划总数
     */
    public Integer getTotalcount()
    {
        return totalcount;
    } 
        
    /**
     * 设置 服务计划总数
     */
    public void setTotalcount(Integer totalcount) 
    {
        this.totalcount = totalcount;
    }   
    
    /**
     * 获取 已提供服务次数
     */
    public Integer getServicecount()
    {
        return servicecount;
    } 
        
    /**
     * 设置 已提供服务次数
     */
    public void setServicecount(Integer servicecount) 
    {
        this.servicecount = servicecount;
    }   
    
    /**
     * 获取 协议生效日期
     */
    public Date getEnabledate()
    {
        return enabledate;
    } 
        
    /**
     * 设置 协议生效日期
     */
    public void setEnabledate(Date enabledate) 
    {
        this.enabledate = enabledate;
    }   
    
    /**
     * 获取 申请人姓名
     */
    public String getApplyname()
    {
        return applyname;
    } 
        
    /**
     * 设置 申请人姓名
     */
    public void setApplyname(String applyname) 
    {
        this.applyname = applyname;
    }   
    
    /**
     * 获取 身份证编号
     */
    public String getIdentitynumber()
    {
        return identitynumber;
    } 
        
    /**
     * 设置 身份证编号
     */
    public void setIdentitynumber(String identitynumber) 
    {
        this.identitynumber = identitynumber;
    }   
    
    /**
     * 获取 出生日期
     */
    public Date getBirthdate()
    {
        return birthdate;
    } 
        
    /**
     * 设置 出生日期
     */
    public void setBirthdate(Date birthdate) 
    {
        this.birthdate = birthdate;
    }   
    
    /**
     * 获取 年龄
     */
    public Short getAge()
    {
        return age;
    } 
        
    /**
     * 设置 年龄
     */
    public void setAge(Short age) 
    {
        this.age = age;
    }   
    
    /**
     * 获取 性别:0未知的性别,1男性,2女性,9未说明的性别
     */
    public Short getSex()
    {
        return sex;
    } 
        
    /**
     * 设置 性别:0未知的性别,1男性,2女性,9未说明的性别
     */
    public void setSex(Short sex) 
    {
        this.sex = sex;
    }   
    
    /**
     * 获取 联系电话
     */
    public String getTelephone()
    {
        return telephone;
    } 
        
    /**
     * 设置 联系电话
     */
    public void setTelephone(String telephone) 
    {
        this.telephone = telephone;
    }   
    
    /**
     * 获取 家庭地址
     */
    public String getAddress()
    {
        return address;
    } 
        
    /**
     * 设置 家庭地址
     */
    public void setAddress(String address) 
    {
        this.address = address;
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
    
    	public Map<String, Object> getAppoinement() {
		return appoinement;
	}

	public void setAppoinement(Map<String, Object> appoinement) {
		this.appoinement = appoinement;
	}
}