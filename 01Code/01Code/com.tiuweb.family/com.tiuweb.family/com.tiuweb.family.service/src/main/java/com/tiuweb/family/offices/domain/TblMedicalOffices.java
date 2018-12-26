/*
 * @(#) TblMedicalOffices  2017-07-10 16:56:47
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
package com.tiuweb.family.offices.domain;

import java.io.Serializable;
import java.util.Date;
import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
  
/**
 * 
 * <p>Title: 医疗科室汇总信息</p>
 * <p>Description: TODO 医疗科室汇总信息Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-10 16:56:47
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblMedicalOffices extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 科室名称
     */
    private String officesname;      
    /**
     * 病情关键字
     */
    private String patientskeyword;      
    /**
     * 性别:1男性,2女性,10不限
     */
    private Short sex;      
    /**
     * 年龄条件表达式
     */
    private String ageconditionexpression;      
    /**
     * 年龄条件名称
     */
    private String ageconditionname;      
    /**
     * 
     */
    private String medicalofficesdesc;      
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
     * 获取 科室名称
     */
    public String getOfficesname()
    {
        return officesname;
    } 
        
    /**
     * 设置 科室名称
     */
    public void setOfficesname(String officesname) 
    {
        this.officesname = officesname;
    }   
    
    /**
     * 获取 病情关键字
     */
    public String getPatientskeyword()
    {
        return patientskeyword;
    } 
        
    /**
     * 设置 病情关键字
     */
    public void setPatientskeyword(String patientskeyword) 
    {
        this.patientskeyword = patientskeyword;
    }   
    
    /**
     * 获取 性别:1男性,2女性,10不限
     */
    public Short getSex()
    {
        return sex;
    } 
        
    /**
     * 设置 性别:1男性,2女性,10不限
     */
    public void setSex(Short sex) 
    {
        this.sex = sex;
    }   
    
    /**
     * 获取 年龄条件表达式
     */
    public String getAgeconditionexpression()
    {
        return ageconditionexpression;
    } 
        
    /**
     * 设置 年龄条件表达式
     */
    public void setAgeconditionexpression(String ageconditionexpression) 
    {
        this.ageconditionexpression = ageconditionexpression;
    }   
    
    /**
     * 获取 年龄条件名称
     */
    public String getAgeconditionname()
    {
        return ageconditionname;
    } 
        
    /**
     * 设置 年龄条件名称
     */
    public void setAgeconditionname(String ageconditionname) 
    {
        this.ageconditionname = ageconditionname;
    }   
    
    /**
     * 获取 
     */
    public String getMedicalofficesdesc()
    {
        return medicalofficesdesc;
    } 
        
    /**
     * 设置 
     */
    public void setMedicalofficesdesc(String medicalofficesdesc) 
    {
        this.medicalofficesdesc = medicalofficesdesc;
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