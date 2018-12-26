/*
 * @(#) TblServicesCustomer  2017-07-28 12:53:35
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
 * <p>Title: 服务对象信息</p>
 * <p>Description: TODO 服务对象信息Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-28 12:53:35
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblServicesCustomer extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 签约协议外键
     */
    private String contactidentity;      
    /**
     * 服务明细外键
     */
    private String serviceitemidentity;      
    /**
     * 居民健康档案外键
     */
    private String residenthealtharchivesidentity;      
    /**
     * 居民健康档案编号
     */
    private String residenthealtharchivesnumber;      
    /**
     * 签约记录外键
     */
    private String recordidentity;      
    /**
     * 家庭成员外键
     */
    private String memberidentity;      
    /**
     * 家庭编号外键
     */
    private String familyidentity;      
    /**
     * 家庭编号
     */
    private String familynumber;      
    /**
     * 所属服务类别:0全部家庭成员,1仅本人,2其他
     */
    private Short customercategory;      
    /**
     * 家庭成员姓名
     */
    private String membername;      
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
     * 获取 服务明细外键
     */
    public String getServiceitemidentity()
    {
        return serviceitemidentity;
    } 
        
    /**
     * 设置 服务明细外键
     */
    public void setServiceitemidentity(String serviceitemidentity) 
    {
        this.serviceitemidentity = serviceitemidentity;
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
     * 获取 家庭成员外键
     */
    public String getMemberidentity()
    {
        return memberidentity;
    } 
        
    /**
     * 设置 家庭成员外键
     */
    public void setMemberidentity(String memberidentity) 
    {
        this.memberidentity = memberidentity;
    }   
    
    /**
     * 获取 家庭编号外键
     */
    public String getFamilyidentity()
    {
        return familyidentity;
    } 
        
    /**
     * 设置 家庭编号外键
     */
    public void setFamilyidentity(String familyidentity) 
    {
        this.familyidentity = familyidentity;
    }   
    
    /**
     * 获取 家庭编号
     */
    public String getFamilynumber()
    {
        return familynumber;
    } 
        
    /**
     * 设置 家庭编号
     */
    public void setFamilynumber(String familynumber) 
    {
        this.familynumber = familynumber;
    }   
    
    /**
     * 获取 所属服务类别:0全部家庭成员,1仅本人,2其他
     */
    public Short getCustomercategory()
    {
        return customercategory;
    } 
        
    /**
     * 设置 所属服务类别:0全部家庭成员,1仅本人,2其他
     */
    public void setCustomercategory(Short customercategory) 
    {
        this.customercategory = customercategory;
    }   
    
    /**
     * 获取 家庭成员姓名
     */
    public String getMembername()
    {
        return membername;
    } 
        
    /**
     * 设置 家庭成员姓名
     */
    public void setMembername(String membername) 
    {
        this.membername = membername;
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
    
}