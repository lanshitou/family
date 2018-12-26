/*
 * @(#) TblMedicalTeamRole  2017-07-17 18:34:30
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
package com.tiuweb.family.roles.domain;

import java.io.Serializable;
import java.util.Date;
import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
  
/**
 * 
 * <p>Title: 医疗团队角色信息</p>
 * <p>Description: TODO 医疗团队角色信息Entity层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-17 18:34:30
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblMedicalTeamRole extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 角色名称
     */
    private String rolename;      
    /**
     * 职责说明
     */
    private String jobdescription;      
    /**
     * 任职资格/标准
     */
    private String jobqualification;      
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
     * 获取 角色名称
     */
    public String getRolename()
    {
        return rolename;
    } 
        
    /**
     * 设置 角色名称
     */
    public void setRolename(String rolename) 
    {
        this.rolename = rolename;
    }   
    
    /**
     * 获取 职责说明
     */
    public String getJobdescription()
    {
        return jobdescription;
    } 
        
    /**
     * 设置 职责说明
     */
    public void setJobdescription(String jobdescription) 
    {
        this.jobdescription = jobdescription;
    }   
    
    /**
     * 获取 任职资格/标准
     */
    public String getJobqualification()
    {
        return jobqualification;
    } 
        
    /**
     * 设置 任职资格/标准
     */
    public void setJobqualification(String jobqualification) 
    {
        this.jobqualification = jobqualification;
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
    
} ///:~