/*
 * @(#) TblResidentContactServicePlan  2017-08-15 16:51:12
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
 * @version 1.00.00 创建时间：2017-08-09 14:06:57
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblResidentContactServicePlan extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 签约服务项目类别外键
     */
    private String servicecategoryid;      
    /**
     * 签约服务项目类别名称
     */
    private String servicecategoryname;      
    /**
     * 已签约服务外键
     */
    private String serviceidentify;      
    /**
     * 已签约服务名称
     */
    private String servicename;      
    /**
     * 服务内容详情
     */
    private String detail;      
    /**
     * 签约协议记录外键
     */
    private String recordidentify;      
    /**
     * 签约协议外键
     */
    private String contactidentify;      
    /**
     * 服务单号
     */
    private String servicenumber;      
    /**
     * 签约单号
     */
    private String contactnumber;      
    /**
     * 计划服务时间
     */
    private Date expectservicedate;      
    /**
     * 实际服务时间
     */
    private Date servicedate;      
    /**
     * 服务家庭成员外键
     */
    private String memberidentity;      
    /**
     * 服务家庭成员姓名
     */
    private String membername;      
    /**
     * 性别:0未知的性别,1男性,2女性,9未说明的性别
     */
    private Short sex;      
    /**
     * 年龄
     */
    private Short age;      
    /**
     * 身份证编号
     */
    private String identitynumber;      
    /**
     * 居民联系电话
     */
    private String residenttelphone;      
    /**
     * 居民家庭地址
     */
    private String residentaddress;      
    /**
     * 所属团队外键
     */
    private String medicalteamidentity;      
    /**
     * 所属团队名称
     */
    private String medicalteamname;      
    /**
     * 服务医生外键
     */
    private String doctoridentity;      
    /**
     * 服务医生名称
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
     * 服务方式:0上门服务,1预约就诊
     */
    private Short isappointment;      
    /**
     * 医疗服务满意度
     */
    private Short servicerate;      
    /**
     * 是否转诊:0否,1是
     */
    private Boolean istransfer;      
    /**
     * 计划当前状态:0待确认,1待执行,2已过期,3已完成
     */
    private Short status;      
    /**
     * 计划开始日期
     */
    private Date startdate;      
    /**
     * 计划截止日期
     */
    private Date enddate;      
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
     * 获取 签约服务项目类别外键
     */
    public String getServicecategoryid()
    {
        return servicecategoryid;
    } 
        
    /**
     * 设置 签约服务项目类别外键
     */
    public void setServicecategoryid(String servicecategoryid) 
    {
        this.servicecategoryid = servicecategoryid;
    }   
    
    /**
     * 获取 签约服务项目类别名称
     */
    public String getServicecategoryname()
    {
        return servicecategoryname;
    } 
        
    /**
     * 设置 签约服务项目类别名称
     */
    public void setServicecategoryname(String servicecategoryname) 
    {
        this.servicecategoryname = servicecategoryname;
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
     * 获取 服务内容详情
     */
    public String getDetail()
    {
        return detail;
    } 
        
    /**
     * 设置 服务内容详情
     */
    public void setDetail(String detail) 
    {
        this.detail = detail;
    }   
    
    /**
     * 获取 签约协议记录外键
     */
    public String getRecordidentify()
    {
        return recordidentify;
    } 
        
    /**
     * 设置 签约协议记录外键
     */
    public void setRecordidentify(String recordidentify) 
    {
        this.recordidentify = recordidentify;
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
     * 获取 服务单号
     */
    public String getServicenumber()
    {
        return servicenumber;
    } 
        
    /**
     * 设置 服务单号
     */
    public void setServicenumber(String servicenumber) 
    {
        this.servicenumber = servicenumber;
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
     * 获取 计划服务时间
     */
    public Date getExpectservicedate()
    {
        return expectservicedate;
    } 
        
    /**
     * 设置 计划服务时间
     */
    public void setExpectservicedate(Date expectservicedate) 
    {
        this.expectservicedate = expectservicedate;
    }   
    
    /**
     * 获取 实际服务时间
     */
    public Date getServicedate()
    {
        return servicedate;
    } 
        
    /**
     * 设置 实际服务时间
     */
    public void setServicedate(Date servicedate) 
    {
        this.servicedate = servicedate;
    }   
    
    /**
     * 获取 服务家庭成员外键
     */
    public String getMemberidentity()
    {
        return memberidentity;
    } 
        
    /**
     * 设置 服务家庭成员外键
     */
    public void setMemberidentity(String memberidentity) 
    {
        this.memberidentity = memberidentity;
    }   
    
    /**
     * 获取 服务家庭成员姓名
     */
    public String getMembername()
    {
        return membername;
    } 
        
    /**
     * 设置 服务家庭成员姓名
     */
    public void setMembername(String membername) 
    {
        this.membername = membername;
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
     * 获取 居民联系电话
     */
    public String getResidenttelphone()
    {
        return residenttelphone;
    } 
        
    /**
     * 设置 居民联系电话
     */
    public void setResidenttelphone(String residenttelphone) 
    {
        this.residenttelphone = residenttelphone;
    }   
    
    /**
     * 获取 居民家庭地址
     */
    public String getResidentaddress()
    {
        return residentaddress;
    } 
        
    /**
     * 设置 居民家庭地址
     */
    public void setResidentaddress(String residentaddress) 
    {
        this.residentaddress = residentaddress;
    }   
    
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
     * 获取 服务医生外键
     */
    public String getDoctoridentity()
    {
        return doctoridentity;
    } 
        
    /**
     * 设置 服务医生外键
     */
    public void setDoctoridentity(String doctoridentity) 
    {
        this.doctoridentity = doctoridentity;
    }   
    
    /**
     * 获取 服务医生名称
     */
    public String getDoctorname()
    {
        return doctorname;
    } 
        
    /**
     * 设置 服务医生名称
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
     * 获取 服务方式:0上门服务,1预约就诊
     */
    public Short getIsappointment()
    {
        return isappointment;
    } 
        
    /**
     * 设置 服务方式:0上门服务,1预约就诊
     */
    public void setIsappointment(Short isappointment) 
    {
        this.isappointment = isappointment;
    }   
    
    /**
     * 获取 医疗服务满意度
     */
    public Short getServicerate()
    {
        return servicerate;
    } 
        
    /**
     * 设置 医疗服务满意度
     */
    public void setServicerate(Short servicerate) 
    {
        this.servicerate = servicerate;
    }   
    
    /**
     * 获取 是否转诊:0否,1是
     */
    public Boolean getIstransfer()
    {
        return istransfer;
    } 
        
    /**
     * 设置 是否转诊:0否,1是
     */
    public void setIstransfer(Boolean istransfer) 
    {
        this.istransfer = istransfer;
    }   
    
    /**
     * 获取 计划当前状态:0待确认,1待执行,2已过期,3已完成
     */
    public Short getStatus()
    {
        return status;
    } 
        
    /**
     * 设置 计划当前状态:0待确认,1待执行,2已过期,3已完成
     */
    public void setStatus(Short status) 
    {
        this.status = status;
    }   
    
    /**
     * 获取 计划开始日期
     */
    public Date getStartdate()
    {
        return startdate;
    } 
        
    /**
     * 设置 计划开始日期
     */
    public void setStartdate(Date startdate) 
    {
        this.startdate = startdate;
    }   
    
    /**
     * 获取 计划截止日期
     */
    public Date getEnddate()
    {
        return enddate;
    } 
        
    /**
     * 设置 计划截止日期
     */
    public void setEnddate(Date enddate) 
    {
        this.enddate = enddate;
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