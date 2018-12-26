/*
 * @(#) TblMedicalInstitutionServiceArea  2017-07-26 14:31:47
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
 * <p>Title: 医疗机构服务区域</p>
 * <p>Description: TODO 医疗机构服务区域Entity层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-26 14:31:47
 * <pre>
 * 修改记录:
 * 版本号    修改人        修改日期       修改内容
 * 
 */ 
public class TblMedicalInstitutionServiceArea extends AbstractEntity implements Serializable 
{	
    private static final long serialVersionUID = 1L;
	
    /**
     * 医疗机构外键
     */
    private String medicalinstitutionidentity;      
    /**
     * 医疗机构名称
     */
    private String medicalinstitutionname;      
    /**
     * 省级编码
     */
    private String provincecode;      
    /**
     * 省级名称
     */
    private String provincename;      
    /**
     * 市级编码
     */
    private String citycode;      
    /**
     * 市级名称
     */
    private String cityname;      
    /**
     * 区域编码
     */
    private String areacode;      
    /**
     * 区级名称
     */
    private String areaname;      
    /**
     * 县级编码
     */
    private String countycode;      
    /**
     * 县级名称
     */
    private String countyname;      
    /**
     * 乡镇级编码
     */
    private String townshipcode;      
    /**
     * 乡镇名称
     */
    private String townshipname;      
    /**
     * 村级编码
     */
    private String villagecode;      
    /**
     * 村名称
     */
    private String villagename;      
    /**
     * 区域经度坐标
     */
    private String longitude;      
    /**
     * 区域维度坐标
     */
    private String latitude;      
    /**
     * 数据状态:1-true已删除,0-false未删除
     */
    private Boolean deletesign;      
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
     * 获取 医疗机构名称
     */
    public String getMedicalinstitutionname()
    {
        return medicalinstitutionname;
    } 
        
    /**
     * 设置 医疗机构名称
     */
    public void setMedicalinstitutionname(String medicalinstitutionname) 
    {
        this.medicalinstitutionname = medicalinstitutionname;
    }   
    
    /**
     * 获取 省级编码
     */
    public String getProvincecode()
    {
        return provincecode;
    } 
        
    /**
     * 设置 省级编码
     */
    public void setProvincecode(String provincecode) 
    {
        this.provincecode = provincecode;
    }   
    
    /**
     * 获取 省级名称
     */
    public String getProvincename()
    {
        return provincename;
    } 
        
    /**
     * 设置 省级名称
     */
    public void setProvincename(String provincename) 
    {
        this.provincename = provincename;
    }   
    
    /**
     * 获取 市级编码
     */
    public String getCitycode()
    {
        return citycode;
    } 
        
    /**
     * 设置 市级编码
     */
    public void setCitycode(String citycode) 
    {
        this.citycode = citycode;
    }   
    
    /**
     * 获取 市级名称
     */
    public String getCityname()
    {
        return cityname;
    } 
        
    /**
     * 设置 市级名称
     */
    public void setCityname(String cityname) 
    {
        this.cityname = cityname;
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
     * 获取 区级名称
     */
    public String getAreaname()
    {
        return areaname;
    } 
        
    /**
     * 设置 区级名称
     */
    public void setAreaname(String areaname) 
    {
        this.areaname = areaname;
    }   
    
    /**
     * 获取 县级编码
     */
    public String getCountycode()
    {
        return countycode;
    } 
        
    /**
     * 设置 县级编码
     */
    public void setCountycode(String countycode) 
    {
        this.countycode = countycode;
    }   
    
    /**
     * 获取 县级名称
     */
    public String getCountyname()
    {
        return countyname;
    } 
        
    /**
     * 设置 县级名称
     */
    public void setCountyname(String countyname) 
    {
        this.countyname = countyname;
    }   
    
    /**
     * 获取 乡镇级编码
     */
    public String getTownshipcode()
    {
        return townshipcode;
    } 
        
    /**
     * 设置 乡镇级编码
     */
    public void setTownshipcode(String townshipcode) 
    {
        this.townshipcode = townshipcode;
    }   
    
    /**
     * 获取 乡镇名称
     */
    public String getTownshipname()
    {
        return townshipname;
    } 
        
    /**
     * 设置 乡镇名称
     */
    public void setTownshipname(String townshipname) 
    {
        this.townshipname = townshipname;
    }   
    
    /**
     * 获取 村级编码
     */
    public String getVillagecode()
    {
        return villagecode;
    } 
        
    /**
     * 设置 村级编码
     */
    public void setVillagecode(String villagecode) 
    {
        this.villagecode = villagecode;
    }   
    
    /**
     * 获取 村名称
     */
    public String getVillagename()
    {
        return villagename;
    } 
        
    /**
     * 设置 村名称
     */
    public void setVillagename(String villagename) 
    {
        this.villagename = villagename;
    }   
    
    /**
     * 获取 区域经度坐标
     */
    public String getLongitude()
    {
        return longitude;
    } 
        
    /**
     * 设置 区域经度坐标
     */
    public void setLongitude(String longitude) 
    {
        this.longitude = longitude;
    }   
    
    /**
     * 获取 区域维度坐标
     */
    public String getLatitude()
    {
        return latitude;
    } 
        
    /**
     * 设置 区域维度坐标
     */
    public void setLatitude(String latitude) 
    {
        this.latitude = latitude;
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