/*
 * @(#) TblMedicalInstitution  2017-07-26 14:44:58
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tiuweb.climb.framework.commons.domain.AbstractEntity;
import com.tiuweb.family.offices.domain.TblMedicalInstitutionOffices;

/**
 * 
 * <p>
 * Title: 签约医疗机构信息
 * </p>
 * <p>
 * Description: TODO 签约医疗机构信息Entity层
 *
 * @author Z.J.
 * @version 1.00.00 创建时间：2017-07-26 14:44:58
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public class TblMedicalInstitution extends AbstractEntity
      implements Serializable
{
   private static final long serialVersionUID = 1L;

   /**
    * 医疗机构字典外键-p_org-climbdb
    */
   private String porgidentity;
   /**
    * 医疗机构名称
    */
   private String orgname;
   /**
    * 机构代码
    */
   private String orgcode;
   /**
    * 机构配图
    */
   private String previewimage;
   /**
    * 联系电话
    */
   private String telephone;
   /**
    * 机构地址
    */
   private String address;
   /**
    * 机构简介
    */
   private String summary;
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
    * 机构下属科室
    */
   private List<TblMedicalInstitutionOffices> offices = new ArrayList<TblMedicalInstitutionOffices>();

   /**
    * 下属医生职级
    */
   private List<TblDoctorProfessionalLevel> levels = new ArrayList<TblDoctorProfessionalLevel>();

   /**
    * 医疗机构服务区域
    */
   private List<TblMedicalInstitutionServiceArea> areas = new ArrayList<TblMedicalInstitutionServiceArea>();

   /**
    * 获取 医疗机构字典外键-p_org-climbdb
    */
   public String getPorgidentity()
   {
      return porgidentity;
   }

   /**
    * 设置 医疗机构字典外键-p_org-climbdb
    */
   public void setPorgidentity(String porgidentity)
   {
      this.porgidentity = porgidentity;
   }

   /**
    * 获取 医疗机构名称
    */
   public String getOrgname()
   {
      return orgname;
   }

   /**
    * 设置 医疗机构名称
    */
   public void setOrgname(String orgname)
   {
      this.orgname = orgname;
   }

   /**
    * 获取 机构代码
    */
   public String getOrgcode()
   {
      return orgcode;
   }

   /**
    * 设置 机构代码
    */
   public void setOrgcode(String orgcode)
   {
      this.orgcode = orgcode;
   }

   /**
    * 获取 机构配图
    */
   public String getPreviewimage()
   {
      return previewimage;
   }

   /**
    * 设置 机构配图
    */
   public void setPreviewimage(String previewimage)
   {
      this.previewimage = previewimage;
   }

   /**
    * 获取 联系电话
    */
   public String getTelephone()
   {
      return telephone;
   }

   /**
    * 设置 联系电话
    */
   public void setTelephone(String telephone)
   {
      this.telephone = telephone;
   }

   /**
    * 获取 机构地址
    */
   public String getAddress()
   {
      return address;
   }

   /**
    * 设置 机构地址
    */
   public void setAddress(String address)
   {
      this.address = address;
   }

   /**
    * 获取 机构简介
    */
   public String getSummary()
   {
      return summary;
   }

   /**
    * 设置 机构简介
    */
   public void setSummary(String summary)
   {
      this.summary = summary;
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

   public List<TblMedicalInstitutionOffices> getOffices()
   {
      return offices;
   }

   public void setOffices(List<TblMedicalInstitutionOffices> offices)
   {
      this.offices = offices;
   }

   public List<TblDoctorProfessionalLevel> getLevels()
   {
      return levels;
   }

   public void setLevels(List<TblDoctorProfessionalLevel> levels)
   {
      this.levels = levels;
   }

   public List<TblMedicalInstitutionServiceArea> getAreas()
   {
      return areas;
   }

   public void setAreas(List<TblMedicalInstitutionServiceArea> areas)
   {
      this.areas = areas;
   }

}