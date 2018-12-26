/*
 * @(#) TblResidentHealthArchives  2017-07-18 11:48:02
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
 * <p>Title: 签约居民个人健康档案</p>
 * <p>Description: TODO 签约居民个人健康档案Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-18 11:48:02
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblResidentHealthArchives extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 档案编号
     */
    private String archivesnumber;      
    /**
     * 身份证编号
     */
    private String identitynumber;      
    /**
     * 现住址
     */
    private String presentaddress;      
    /**
     * 户籍地址
     */
    private String permanentaddress;      
    /**
     * 联系电话
     */
    private String contactphone;      
    /**
     * 乡镇名称
     */
    private String townshipname;      
    /**
     * 村/委员会名称
     */
    private String villagename;      
    /**
     * 建档单位
     */
    private String filingunit;      
    /**
     * 建档人
     */
    private String filingman;      
    /**
     * 责任医生
     */
    private String responsibledoctor;      
    /**
     * 建档日期
     */
    private Date filinhdate;      
    /**
     * 出生日期
     */
    private Date birthdate;      
    /**
     * 姓名
     */
    private String residentname;      
    /**
     * 性别:0未知的性别,1男性,2女性,9未说明的性别
     */
    private Short sex;      
    /**
     * 年龄
     */
    private Short age;      
    /**
     * 职业
     */
    private Short occupation;      
    /**
     * 民族
     */
    private Short nation;      
    /**
     * 工作单位
     */
    private String jobaddress;      
    /**
     * 联系人姓名
     */
    private String linkman;      
    /**
     * 联系人电话
     */
    private String linkmanphone;      
    /**
     * 常住类型
     */
    private Short residenttype;      
    /**
     * 血型
     */
    private Short bloodtype;      
    /**
     * RH阴性
     */
    private Short rhnegative;      
    /**
     * 婚姻状况:0未婚,1已婚
     */
    private Short maritalstatus;      
    /**
     * 文化程度
     */
    private Short education;      
    /**
     * 诊疗关键字
     */
    private String archiveskeywords;      
    /**
     * 数据状态:1-true已删除,0-false未删除
     */
    private Boolean deletesign;      
    /**
     * 
     */
    private String areacode;      
    /**
     * 机构外键
     */
    private String orgid;      
    
    /**
     * 获取 档案编号
     */
    public String getArchivesnumber()
    {
        return archivesnumber;
    } 
        
    /**
     * 设置 档案编号
     */
    public void setArchivesnumber(String archivesnumber) 
    {
        this.archivesnumber = archivesnumber;
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
     * 获取 现住址
     */
    public String getPresentaddress()
    {
        return presentaddress;
    } 
        
    /**
     * 设置 现住址
     */
    public void setPresentaddress(String presentaddress) 
    {
        this.presentaddress = presentaddress;
    }   
    
    /**
     * 获取 户籍地址
     */
    public String getPermanentaddress()
    {
        return permanentaddress;
    } 
        
    /**
     * 设置 户籍地址
     */
    public void setPermanentaddress(String permanentaddress) 
    {
        this.permanentaddress = permanentaddress;
    }   
    
    /**
     * 获取 联系电话
     */
    public String getContactphone()
    {
        return contactphone;
    } 
        
    /**
     * 设置 联系电话
     */
    public void setContactphone(String contactphone) 
    {
        this.contactphone = contactphone;
    }   
    
    /**
     * 获取 乡镇名称
     */
    public String getTownshipname()
    {
        return townshipname;
    } 
        
    /**
     * 设置 乡镇名称
     */
    public void setTownshipname(String townshipname) 
    {
        this.townshipname = townshipname;
    }   
    
    /**
     * 获取 村/委员会名称
     */
    public String getVillagename()
    {
        return villagename;
    } 
        
    /**
     * 设置 村/委员会名称
     */
    public void setVillagename(String villagename) 
    {
        this.villagename = villagename;
    }   
    
    /**
     * 获取 建档单位
     */
    public String getFilingunit()
    {
        return filingunit;
    } 
        
    /**
     * 设置 建档单位
     */
    public void setFilingunit(String filingunit) 
    {
        this.filingunit = filingunit;
    }   
    
    /**
     * 获取 建档人
     */
    public String getFilingman()
    {
        return filingman;
    } 
        
    /**
     * 设置 建档人
     */
    public void setFilingman(String filingman) 
    {
        this.filingman = filingman;
    }   
    
    /**
     * 获取 责任医生
     */
    public String getResponsibledoctor()
    {
        return responsibledoctor;
    } 
        
    /**
     * 设置 责任医生
     */
    public void setResponsibledoctor(String responsibledoctor) 
    {
        this.responsibledoctor = responsibledoctor;
    }   
    
    /**
     * 获取 建档日期
     */
    public Date getFilinhdate()
    {
        return filinhdate;
    } 
        
    /**
     * 设置 建档日期
     */
    public void setFilinhdate(Date filinhdate) 
    {
        this.filinhdate = filinhdate;
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
     * 获取 职业
     */
    public Short getOccupation()
    {
        return occupation;
    } 
        
    /**
     * 设置 职业
     */
    public void setOccupation(Short occupation) 
    {
        this.occupation = occupation;
    }   
    
    /**
     * 获取 民族
     */
    public Short getNation()
    {
        return nation;
    } 
        
    /**
     * 设置 民族
     */
    public void setNation(Short nation) 
    {
        this.nation = nation;
    }   
    
    /**
     * 获取 工作单位
     */
    public String getJobaddress()
    {
        return jobaddress;
    } 
        
    /**
     * 设置 工作单位
     */
    public void setJobaddress(String jobaddress) 
    {
        this.jobaddress = jobaddress;
    }   
    
    /**
     * 获取 联系人姓名
     */
    public String getLinkman()
    {
        return linkman;
    } 
        
    /**
     * 设置 联系人姓名
     */
    public void setLinkman(String linkman) 
    {
        this.linkman = linkman;
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
     * 获取 常住类型
     */
    public Short getResidenttype()
    {
        return residenttype;
    } 
        
    /**
     * 设置 常住类型
     */
    public void setResidenttype(Short residenttype) 
    {
        this.residenttype = residenttype;
    }   
    
    /**
     * 获取 血型
     */
    public Short getBloodtype()
    {
        return bloodtype;
    } 
        
    /**
     * 设置 血型
     */
    public void setBloodtype(Short bloodtype) 
    {
        this.bloodtype = bloodtype;
    }   
    
    /**
     * 获取 RH阴性
     */
    public Short getRhnegative()
    {
        return rhnegative;
    } 
        
    /**
     * 设置 RH阴性
     */
    public void setRhnegative(Short rhnegative) 
    {
        this.rhnegative = rhnegative;
    }   
    
    /**
     * 获取 婚姻状况:0未婚,1已婚
     */
    public Short getMaritalstatus()
    {
        return maritalstatus;
    } 
        
    /**
     * 设置 婚姻状况:0未婚,1已婚
     */
    public void setMaritalstatus(Short maritalstatus) 
    {
        this.maritalstatus = maritalstatus;
    }   
    
    /**
     * 获取 文化程度
     */
    public Short getEducation()
    {
        return education;
    } 
        
    /**
     * 设置 文化程度
     */
    public void setEducation(Short education) 
    {
        this.education = education;
    }   
    
    /**
     * 获取 诊疗关键字
     */
    public String getArchiveskeywords()
    {
        return archiveskeywords;
    } 
        
    /**
     * 设置 诊疗关键字
     */
    public void setArchiveskeywords(String archiveskeywords) 
    {
        this.archiveskeywords = archiveskeywords;
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
     * 获取 
     */
    public String getAreacode()
    {
        return areacode;
    } 
        
    /**
     * 设置 
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