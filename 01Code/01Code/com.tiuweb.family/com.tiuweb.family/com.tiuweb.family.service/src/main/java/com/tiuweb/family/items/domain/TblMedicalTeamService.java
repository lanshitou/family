/*
 * @(#) TblMedicalTeamService  2017-07-17 12:52:58
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
package com.tiuweb.family.items.domain;

import java.io.Serializable;

import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
  
/**
 * 
 * <p>Title: 团队提供的签约服务汇总信息</p>
 * <p>Description: TODO 团队提供的签约服务汇总信息Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-17 12:52:58
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblMedicalTeamService extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 签约服务项目外键
     */
    private String servcieitemidentify;      
    /**
     * 签约服务项目名称
     */
    private String servcieitemname;      
    /**
     * 
     */
    private String teamidentify;      
    /**
     * 
     */
    private String teamname;      
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
     * 获取 签约服务项目外键
     */
    public String getServcieitemidentify()
    {
        return servcieitemidentify;
    } 
        
    /**
     * 设置 签约服务项目外键
     */
    public void setServcieitemidentify(String servcieitemidentify) 
    {
        this.servcieitemidentify = servcieitemidentify;
    }   
    
    /**
     * 获取 签约服务项目名称
     */
    public String getServcieitemname()
    {
        return servcieitemname;
    } 
        
    /**
     * 设置 签约服务项目名称
     */
    public void setServcieitemname(String servcieitemname) 
    {
        this.servcieitemname = servcieitemname;
    }   
    
    /**
     * 获取 
     */
    public String getTeamidentify()
    {
        return teamidentify;
    } 
        
    /**
     * 设置 
     */
    public void setTeamidentify(String teamidentify) 
    {
        this.teamidentify = teamidentify;
    }   
    
    /**
     * 获取 
     */
    public String getTeamname()
    {
        return teamname;
    } 
        
    /**
     * 设置 
     */
    public void setTeamname(String teamname) 
    {
        this.teamname = teamname;
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