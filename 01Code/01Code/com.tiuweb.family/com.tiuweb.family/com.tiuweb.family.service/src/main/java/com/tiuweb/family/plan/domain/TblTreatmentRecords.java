/*
 * @(#) TblTreatmentRecords  2017-08-09 09:45:44
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
 * <p>Title: 家庭医生服务记录</p>
 * <p>Description: TODO 家庭医生服务记录Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-09 09:45:44
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblTreatmentRecords extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 服务单号
     */
    private String servicenumber;      
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
     * 服务内容详情
     */
    private String detail;      
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
     * 服务方式:0上门服务,1预约就诊
     */
    private Short isappointment;      
    /**
     * 随访日期
     */
    private Date servicedate;      
    /**
     * 下次随访日期
     */
    private Date nextservicedate;      
    /**
     * 随访医生外键
     */
    private String servicetreatmentdoctoridentity;      
    /**
     * 随访医生名称
     */
    private String servicetreatmentdoctorname;      
    /**
     * 医生角色外键
     */
    private String servicetreatmentdoctorroleidentity;      
    /**
     * 医生角色名称
     */
    private String servicetreatmentdoctorrolename;      
    /**
     * 是否家庭医生签约服务:0否,1是
     */
    private Boolean iscontactservice;      
    /**
     * 诊断结论
     */
    private String conclusion;      
    /**
     * 医嘱
     */
    private String advice;      
    /**
     * 诊疗关键字
     */
    private String treatmentkeywords;      
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
     * 响应满意度
     */
    private Short doctorrate;      
    /**
     * 处理结果满意度
     */
    private Short servicerate;      
    /**
     * 居民家庭地址
     */
    private String residentaddress;      
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
     * 获取 随访日期
     */
    public Date getServicedate()
    {
        return servicedate;
    } 
        
    /**
     * 设置 随访日期
     */
    public void setServicedate(Date servicedate) 
    {
        this.servicedate = servicedate;
    }   
    
    /**
     * 获取 下次随访日期
     */
    public Date getNextservicedate()
    {
        return nextservicedate;
    } 
        
    /**
     * 设置 下次随访日期
     */
    public void setNextservicedate(Date nextservicedate) 
    {
        this.nextservicedate = nextservicedate;
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
     * 获取 医生角色外键
     */
    public String getServicetreatmentdoctorroleidentity()
    {
        return servicetreatmentdoctorroleidentity;
    } 
        
    /**
     * 设置 医生角色外键
     */
    public void setServicetreatmentdoctorroleidentity(String servicetreatmentdoctorroleidentity) 
    {
        this.servicetreatmentdoctorroleidentity = servicetreatmentdoctorroleidentity;
    }   
    
    /**
     * 获取 医生角色名称
     */
    public String getServicetreatmentdoctorrolename()
    {
        return servicetreatmentdoctorrolename;
    } 
        
    /**
     * 设置 医生角色名称
     */
    public void setServicetreatmentdoctorrolename(String servicetreatmentdoctorrolename) 
    {
        this.servicetreatmentdoctorrolename = servicetreatmentdoctorrolename;
    }   
    
    /**
     * 获取 是否家庭医生签约服务:0否,1是
     */
    public Boolean getIscontactservice()
    {
        return iscontactservice;
    } 
        
    /**
     * 设置 是否家庭医生签约服务:0否,1是
     */
    public void setIscontactservice(Boolean iscontactservice) 
    {
        this.iscontactservice = iscontactservice;
    }   
    
    /**
     * 获取 诊断结论
     */
    public String getConclusion()
    {
        return conclusion;
    } 
        
    /**
     * 设置 诊断结论
     */
    public void setConclusion(String conclusion) 
    {
        this.conclusion = conclusion;
    }   
    
    /**
     * 获取 医嘱
     */
    public String getAdvice()
    {
        return advice;
    } 
        
    /**
     * 设置 医嘱
     */
    public void setAdvice(String advice) 
    {
        this.advice = advice;
    }   
    
    /**
     * 获取 诊疗关键字
     */
    public String getTreatmentkeywords()
    {
        return treatmentkeywords;
    } 
        
    /**
     * 设置 诊疗关键字
     */
    public void setTreatmentkeywords(String treatmentkeywords) 
    {
        this.treatmentkeywords = treatmentkeywords;
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
     * 获取 响应满意度
     */
    public Short getDoctorrate()
    {
        return doctorrate;
    } 
        
    /**
     * 设置 响应满意度
     */
    public void setDoctorrate(Short doctorrate) 
    {
        this.doctorrate = doctorrate;
    }   
    
    /**
     * 获取 处理结果满意度
     */
    public Short getServicerate()
    {
        return servicerate;
    } 
        
    /**
     * 设置 处理结果满意度
     */
    public void setServicerate(Short servicerate) 
    {
        this.servicerate = servicerate;
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