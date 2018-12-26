/*
 * @(#) TblDoctorProfessionalLevel  2017-07-26 14:24:24
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
package com.tiuweb.family.institution.domain;

import java.io.Serializable;
import java.util.Date;
import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
  
/**
 * 
 * <p>Title: 签约医疗机构医生职级</p>
 * <p>Description: TODO 签约医疗机构医生职级Entity层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-26 14:24:24
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblDoctorProfessionalLevel extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 签约医疗机构外键
     */
    private String medicalinstitutionidentity;      
    /**
     * 所属医疗机构名称
     */
    private String medicalinstitutionname;      
    /**
     * 医生职级外键
     */
    private String professionallevelidentity;      
    /**
     * 医生职级名称
     */
    private String professionallevelname;      
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
     * 获取 签约医疗机构外键
     */
    public String getMedicalinstitutionidentity()
    {
        return medicalinstitutionidentity;
    } 
        
    /**
     * 设置 签约医疗机构外键
     */
    public void setMedicalinstitutionidentity(String medicalinstitutionidentity) 
    {
        this.medicalinstitutionidentity = medicalinstitutionidentity;
    }   
    
    /**
     * 获取 所属医疗机构名称
     */
    public String getMedicalinstitutionname()
    {
        return medicalinstitutionname;
    } 
        
    /**
     * 设置 所属医疗机构名称
     */
    public void setMedicalinstitutionname(String medicalinstitutionname) 
    {
        this.medicalinstitutionname = medicalinstitutionname;
    }   
    
    /**
     * 获取 医生职级外键
     */
    public String getProfessionallevelidentity()
    {
        return professionallevelidentity;
    } 
        
    /**
     * 设置 医生职级外键
     */
    public void setProfessionallevelidentity(String professionallevelidentity) 
    {
        this.professionallevelidentity = professionallevelidentity;
    }   
    
    /**
     * 获取 医生职级名称
     */
    public String getProfessionallevelname()
    {
        return professionallevelname;
    } 
        
    /**
     * 设置 医生职级名称
     */
    public void setProfessionallevelname(String professionallevelname) 
    {
        this.professionallevelname = professionallevelname;
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