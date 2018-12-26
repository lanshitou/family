/*
 * @(#) TblResidentInquiry  2017-07-27 11:46:12
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
package com.tiuweb.family.inquiry.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
  
/**
 * 
 * <p>Title: 居民问诊信息</p>
 * <p>Description: TODO 居民问诊信息Entity层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-27 11:46:12
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblResidentInquiry extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 是否指定问诊:0否,1是
     */
    private Boolean isappoint;      
    /**
     * 病情类别外键
     */
    private String patientscategoryidentity;      
    /**
     * 病情类别名称
     */
    private String patientscategoryname;      
    /**
     * 问诊内容
     */
    private String content;      
    /**
     * 问诊状态:0未回复,1回复中,2已解决,3已评价
     */
    private Short inquirystatus;      
    /**
     * 问诊居民外键
     */
    private String inquiryidentity;      
    /**
     * 问诊居民账户
     */
    private String loginname;      
    /**
     * 问诊居民昵称
     */
    private String inquirynick;      
    /**
     * 是否签约:0否,1是
     */
    private Boolean iscontact;      
    /**
     * 专业匹配度
     */
    private Long matched;      
    /**
     * 过期时间:问诊状态=2已解决,开始计算过期时间=当前时间+7天,评价后清空
     */
    private Date deadlinedate;      
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
    
    // 问诊回复
    private List<TblResidentInquiryReply> replys;
    // 问诊评价
    private List<TblResidentInquiryEvaluate> evaluates;
    
    public List<TblResidentInquiryReply> getReplys()
   {
      return replys;
   }

   public void setReplys(List<TblResidentInquiryReply> replys)
   {
      this.replys = replys;
   }

   public List<TblResidentInquiryEvaluate> getEvaluates()
   {
      return evaluates;
   }

   public void setEvaluates(List<TblResidentInquiryEvaluate> evaluates)
   {
      this.evaluates = evaluates;
   }

   /**
     * 获取 是否指定问诊:0否,1是
     */
    public Boolean getIsappoint()
    {
        return isappoint;
    } 
        
    /**
     * 设置 是否指定问诊:0否,1是
     */
    public void setIsappoint(Boolean isappoint) 
    {
        this.isappoint = isappoint;
    }   
    
    /**
     * 获取 病情类别外键
     */
    public String getPatientscategoryidentity()
    {
        return patientscategoryidentity;
    } 
        
    /**
     * 设置 病情类别外键
     */
    public void setPatientscategoryidentity(String patientscategoryidentity) 
    {
        this.patientscategoryidentity = patientscategoryidentity;
    }   
    
    /**
     * 获取 病情类别名称
     */
    public String getPatientscategoryname()
    {
        return patientscategoryname;
    } 
        
    /**
     * 设置 病情类别名称
     */
    public void setPatientscategoryname(String patientscategoryname) 
    {
        this.patientscategoryname = patientscategoryname;
    }   
    
    /**
     * 获取 问诊内容
     */
    public String getContent()
    {
        return content;
    } 
        
    /**
     * 设置 问诊内容
     */
    public void setContent(String content) 
    {
        this.content = content;
    }   
    
    /**
     * 获取 问诊状态:0未回复,1回复中,2已解决,3已评价
     */
    public Short getInquirystatus()
    {
        return inquirystatus;
    } 
        
    /**
     * 设置 问诊状态:0未回复,1回复中,2已解决,3已评价
     */
    public void setInquirystatus(Short inquirystatus) 
    {
        this.inquirystatus = inquirystatus;
    }   
    
    /**
     * 获取 问诊居民外键
     */
    public String getInquiryidentity()
    {
        return inquiryidentity;
    } 
        
    /**
     * 设置 问诊居民外键
     */
    public void setInquiryidentity(String inquiryidentity) 
    {
        this.inquiryidentity = inquiryidentity;
    }   
    
    /**
     * 获取 问诊居民账户
     */
    public String getLoginname()
    {
        return loginname;
    } 
        
    /**
     * 设置 问诊居民账户
     */
    public void setLoginname(String loginname) 
    {
        this.loginname = loginname;
    }   
    
    /**
     * 获取 问诊居民昵称
     */
    public String getInquirynick()
    {
        return inquirynick;
    } 
        
    /**
     * 设置 问诊居民昵称
     */
    public void setInquirynick(String inquirynick) 
    {
        this.inquirynick = inquirynick;
    }   
    
    /**
     * 获取 是否签约:0否,1是
     */
    public Boolean getIscontact()
    {
        return iscontact;
    } 
        
    /**
     * 设置 是否签约:0否,1是
     */
    public void setIscontact(Boolean iscontact) 
    {
        this.iscontact = iscontact;
    }   
    
    /**
     * 获取 专业匹配度
     */
    public Long getMatched()
    {
        return matched;
    } 
        
    /**
     * 设置 专业匹配度
     */
    public void setMatched(Long matched) 
    {
        this.matched = matched;
    }   
    
    /**
     * 获取 过期时间:问诊状态=2已解决,开始计算过期时间=当前时间+7天,评价后清空
     */
    public Date getDeadlinedate()
    {
        return deadlinedate;
    } 
        
    /**
     * 设置 过期时间:问诊状态=2已解决,开始计算过期时间=当前时间+7天,评价后清空
     */
    public void setDeadlinedate(Date deadlinedate) 
    {
        this.deadlinedate = deadlinedate;
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