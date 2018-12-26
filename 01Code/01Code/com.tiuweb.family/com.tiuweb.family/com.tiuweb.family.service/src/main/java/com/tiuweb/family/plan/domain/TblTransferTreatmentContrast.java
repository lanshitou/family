/*
 * @(#) TblTransferTreatmentContrast  2017-08-10 10:28:29
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
 * <p>Title: 转诊服务对照关系信息</p>
 * <p>Description: TODO 转诊服务对照关系信息Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-10 10:28:29
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblTransferTreatmentContrast extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 当前医疗机构外键
     */
    private String currentorgidentity;      
    /**
     * 当前医疗机构名称
     */
    private String currentorgname;      
    /**
     * 县级医疗机构外键
     */
    private String countyorgidentity;      
    /**
     * 县级医疗机构名称
     */
    private String countyorgname;      
    /**
     * 市级医疗机构外键
     */
    private String cityorgidentity;      
    /**
     * 市级医疗机构名称
     */
    private String cityorgname;      
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
     * 获取 县级医疗机构外键
     */
    public String getCountyorgidentity()
    {
        return countyorgidentity;
    } 
        
    /**
     * 设置 县级医疗机构外键
     */
    public void setCountyorgidentity(String countyorgidentity) 
    {
        this.countyorgidentity = countyorgidentity;
    }   
    
    /**
     * 获取 县级医疗机构名称
     */
    public String getCountyorgname()
    {
        return countyorgname;
    } 
        
    /**
     * 设置 县级医疗机构名称
     */
    public void setCountyorgname(String countyorgname) 
    {
        this.countyorgname = countyorgname;
    }   
    
    /**
     * 获取 市级医疗机构外键
     */
    public String getCityorgidentity()
    {
        return cityorgidentity;
    } 
        
    /**
     * 设置 市级医疗机构外键
     */
    public void setCityorgidentity(String cityorgidentity) 
    {
        this.cityorgidentity = cityorgidentity;
    }   
    
    /**
     * 获取 市级医疗机构名称
     */
    public String getCityorgname()
    {
        return cityorgname;
    } 
        
    /**
     * 设置 市级医疗机构名称
     */
    public void setCityorgname(String cityorgname) 
    {
        this.cityorgname = cityorgname;
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