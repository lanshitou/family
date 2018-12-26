/*
 * @(#) TblPlanTransferTreatmentRecord  2017-08-07 10:44:24
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
 * <p>Title: 执行计划转诊记录</p>
 * <p>Description: TODO 执行计划转诊记录Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-07 10:44:24
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblPlanTransferTreatmentRecord extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 转诊编号
     */
    private String transfernumber;      
    /**
     * 签约协议记录外键
     */
    private String recordidentify;      
    /**
     * 居民签约协议外键
     */
    private String residentcontactidentify;      
    /**
     * 健康档案外键
     */
    private String residenthealtharchivesidentify;      
    /**
     * 执行计划外键
     */
    private String planidentify;      
    /**
     * 转诊对照关系外键
     */
    private String transfercontrastidentify;      
    /**
     * 当前医疗机构外键
     */
    private String currentorgidentity;      
    /**
     * 当前医疗机构名称
     */
    private String currentorgname;      
    /**
     * 对接医疗机构外键
     */
    private String abutmentorgidentity;      
    /**
     * 服务医生外键
     */
    private String doctoridentity;      
    /**
     * 服务医生名称
     */
    private String doctorname;      
    /**
     * 对接医疗机构名称
     */
    private String abutmentorgname;      
    /**
     * 上转/下转:0上转,1下转
     */
    private Boolean transfertreatmentcategory;      
    /**
     * 状态:0取消,1转入成功,2转出成功,3待接收,4未接收
     */
    private Short status;      
    /**
     * 姓名
     */
    private String residentname;      
    /**
     * 年龄
     */
    private Short age;      
    /**
     * 性别:0未知的性别,1男性,2女性,9未说明的性别
     */
    private Short sex;      
    /**
     * 出生日期
     */
    private Date birthdate;      
    /**
     * 身份证编号
     */
    private String identitynumber;      
    /**
     * 联系人电话
     */
    private String linkmanphone;      
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
     * 获取 转诊编号
     */
    public String getTransfernumber()
    {
        return transfernumber;
    } 
        
    /**
     * 设置 转诊编号
     */
    public void setTransfernumber(String transfernumber) 
    {
        this.transfernumber = transfernumber;
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
     * 获取 居民签约协议外键
     */
    public String getResidentcontactidentify()
    {
        return residentcontactidentify;
    } 
        
    /**
     * 设置 居民签约协议外键
     */
    public void setResidentcontactidentify(String residentcontactidentify) 
    {
        this.residentcontactidentify = residentcontactidentify;
    }   
    
    /**
     * 获取 健康档案外键
     */
    public String getResidenthealtharchivesidentify()
    {
        return residenthealtharchivesidentify;
    } 
        
    /**
     * 设置 健康档案外键
     */
    public void setResidenthealtharchivesidentify(String residenthealtharchivesidentify) 
    {
        this.residenthealtharchivesidentify = residenthealtharchivesidentify;
    }   
    
    /**
     * 获取 执行计划外键
     */
    public String getPlanidentify()
    {
        return planidentify;
    } 
        
    /**
     * 设置 执行计划外键
     */
    public void setPlanidentify(String planidentify) 
    {
        this.planidentify = planidentify;
    }   
    
    /**
     * 获取 转诊对照关系外键
     */
    public String getTransfercontrastidentify()
    {
        return transfercontrastidentify;
    } 
        
    /**
     * 设置 转诊对照关系外键
     */
    public void setTransfercontrastidentify(String transfercontrastidentify) 
    {
        this.transfercontrastidentify = transfercontrastidentify;
    }   
    
    /**
     * 获取 当前医疗机构外键
     */
    public String getCurrentorgidentity()
    {
        return currentorgidentity;
    } 
        
    /**
     * 设置 当前医疗机构外键
     */
    public void setCurrentorgidentity(String currentorgidentity) 
    {
        this.currentorgidentity = currentorgidentity;
    }   
    
    /**
     * 获取 当前医疗机构名称
     */
    public String getCurrentorgname()
    {
        return currentorgname;
    } 
        
    /**
     * 设置 当前医疗机构名称
     */
    public void setCurrentorgname(String currentorgname) 
    {
        this.currentorgname = currentorgname;
    }   
    
    /**
     * 获取 对接医疗机构外键
     */
    public String getAbutmentorgidentity()
    {
        return abutmentorgidentity;
    } 
        
    /**
     * 设置 对接医疗机构外键
     */
    public void setAbutmentorgidentity(String abutmentorgidentity) 
    {
        this.abutmentorgidentity = abutmentorgidentity;
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
     * 获取 对接医疗机构名称
     */
    public String getAbutmentorgname()
    {
        return abutmentorgname;
    } 
        
    /**
     * 设置 对接医疗机构名称
     */
    public void setAbutmentorgname(String abutmentorgname) 
    {
        this.abutmentorgname = abutmentorgname;
    }   
    
    /**
     * 获取 上转/下转:0上转,1下转
     */
    public Boolean getTransfertreatmentcategory()
    {
        return transfertreatmentcategory;
    } 
        
    /**
     * 设置 上转/下转:0上转,1下转
     */
    public void setTransfertreatmentcategory(Boolean transfertreatmentcategory) 
    {
        this.transfertreatmentcategory = transfertreatmentcategory;
    }   
    
    /**
     * 获取 状态:0取消,1转入成功,2转出成功,3待接收,4未接收
     */
    public Short getStatus()
    {
        return status;
    } 
        
    /**
     * 设置 状态:0取消,1转入成功,2转出成功,3待接收,4未接收
     */
    public void setStatus(Short status) 
    {
        this.status = status;
    }   
    
    /**
     * 获取 姓名
     */
    public String getResidentname()
    {
        return residentname;
    } 
        
    /**
     * 设置 姓名
     */
    public void setResidentname(String residentname) 
    {
        this.residentname = residentname;
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
     * 获取 联系人电话
     */
    public String getLinkmanphone()
    {
        return linkmanphone;
    } 
        
    /**
     * 设置 联系人电话
     */
    public void setLinkmanphone(String linkmanphone) 
    {
        this.linkmanphone = linkmanphone;
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
    
}