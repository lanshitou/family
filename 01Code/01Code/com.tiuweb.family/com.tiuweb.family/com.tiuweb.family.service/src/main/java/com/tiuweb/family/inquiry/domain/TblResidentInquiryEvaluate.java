/*
 * @(#) TblResidentInquiryEvaluate  2017-07-27 11:47:15
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
import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
  
/**
 * 
 * <p>Title: 问诊评价信息</p>
 * <p>Description: TODO 问诊评价信息Entity层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-27 11:47:15
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblResidentInquiryEvaluate extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 问诊信息外键
     */
    private String inquiryidentity;      
    /**
     * 医生外键
     */
    private String doctoridentity;      
    /**
     * 医生名称
     */
    private String doctorname;      
    /**
     * 否是解决
     */
    private Boolean isresolve;      
    /**
     * 处理标志:0居民评价,1系统处理
     */
    private Short isauto;      
    /**
     * 响应满意度
     */
    private Short responserate;      
    /**
     * 处理结果满意度
     */
    private Short resultrate;      
    /**
     * 评价内容
     */
    private String reply;      
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
     * 获取 问诊信息外键
     */
    public String getInquiryidentity()
    {
        return inquiryidentity;
    } 
        
    /**
     * 设置 问诊信息外键
     */
    public void setInquiryidentity(String inquiryidentity) 
    {
        this.inquiryidentity = inquiryidentity;
    }   
    
    /**
     * 获取 医生外键
     */
    public String getDoctoridentity()
    {
        return doctoridentity;
    } 
        
    /**
     * 设置 医生外键
     */
    public void setDoctoridentity(String doctoridentity) 
    {
        this.doctoridentity = doctoridentity;
    }   
    
    /**
     * 获取 医生名称
     */
    public String getDoctorname()
    {
        return doctorname;
    } 
        
    /**
     * 设置 医生名称
     */
    public void setDoctorname(String doctorname) 
    {
        this.doctorname = doctorname;
    }   
    
    /**
     * 获取 否是解决
     */
    public Boolean getIsresolve()
    {
        return isresolve;
    } 
        
    /**
     * 设置 否是解决
     */
    public void setIsresolve(Boolean isresolve) 
    {
        this.isresolve = isresolve;
    }   
    
    /**
     * 获取 处理标志:0居民评价,1系统处理
     */
    public Short getIsauto()
    {
        return isauto;
    } 
        
    /**
     * 设置 处理标志:0居民评价,1系统处理
     */
    public void setIsauto(Short isauto) 
    {
        this.isauto = isauto;
    }   
    
    /**
     * 获取 响应满意度
     */
    public Short getResponserate()
    {
        return responserate;
    } 
        
    /**
     * 设置 响应满意度
     */
    public void setResponserate(Short responserate) 
    {
        this.responserate = responserate;
    }   
    
    /**
     * 获取 处理结果满意度
     */
    public Short getResultrate()
    {
        return resultrate;
    } 
        
    /**
     * 设置 处理结果满意度
     */
    public void setResultrate(Short resultrate) 
    {
        this.resultrate = resultrate;
    }   
    
    /**
     * 获取 评价内容
     */
    public String getReply()
    {
        return reply;
    } 
        
    /**
     * 设置 评价内容
     */
    public void setReply(String reply) 
    {
        this.reply = reply;
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