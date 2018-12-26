/*
 * @(#) TblServcieItemRole  2017-07-26 15:33:25
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
 * <p>Title: 签约服务项目所需角色</p>
 * <p>Description: TODO 签约服务项目所需角色Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-26 15:33:25
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblServcieItemRole extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 签约服务项目外键
     */
    private String servcieitemidentity;      
    /**
     * 医疗团队角色外键
     */
    private String medicalteamroleidentity;      
    /**
     * 角色名称
     */
    private String rolename;      
    
    /**
     * 获取 签约服务项目外键
     */
    public String getServcieitemidentity()
    {
        return servcieitemidentity;
    } 
        
    /**
     * 设置 签约服务项目外键
     */
    public void setServcieitemidentity(String servcieitemidentity) 
    {
        this.servcieitemidentity = servcieitemidentity;
    }   
    
    /**
     * 获取 医疗团队角色外键
     */
    public String getMedicalteamroleidentity()
    {
        return medicalteamroleidentity;
    } 
        
    /**
     * 设置 医疗团队角色外键
     */
    public void setMedicalteamroleidentity(String medicalteamroleidentity) 
    {
        this.medicalteamroleidentity = medicalteamroleidentity;
    }   
    
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
    
}