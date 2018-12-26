/*
 * @(#) TblMedicalInstitutionOffices  2017-07-10 15:18:26
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
 * <p>Title: 医疗科室信息</p>
 * <p>Description: TODO 医疗科室信息Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-10 15:18:26
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblMedicalInstitutionOffices extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 医疗机构外键
     */
    private String medicalinstitutionidentity;      
    /**
     * 医疗机构下属科室外键
     */
    // modified by Z.J. 20170720
    //private Long officesidentity;
    private String officesidentity;
    
    /**
     * 
     */
    private String officesname;      
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
     * 获取 医疗机构外键
     */
    public String getMedicalinstitutionidentity()
    {
        return medicalinstitutionidentity;
    } 
        
    /**
     * 设置 医疗机构外键
     */
    public void setMedicalinstitutionidentity(String medicalinstitutionidentity) 
    {
        this.medicalinstitutionidentity = medicalinstitutionidentity;
    }   
    
    /**
     * 获取 医疗机构下属科室外键
     */
    // modified by Z.J. 20170720
//    public Long getOfficesidentity()
    public String getOfficesidentity()
    {
        return officesidentity;
    } 
        
    /**
     * 设置 医疗机构下属科室外键
     */
    // modified by Z.J. 20170720
//    public void setOfficesidentity(Long officesidentity) 
    public void setOfficesidentity(String officesidentity)
    {
        this.officesidentity = officesidentity;
    }   
    
    /**
     * 获取 
     */
    public String getOfficesname()
    {
        return officesname;
    } 
        
    /**
     * 设置 
     */
    public void setOfficesname(String officesname) 
    {
        this.officesname = officesname;
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