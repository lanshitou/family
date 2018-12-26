/*
 * @(#) TblMedicalInstitutionDoctor  2017-07-18 10:15:05
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
package com.tiuweb.family.doctors.domain;

import java.io.Serializable;
import java.util.Date;
import com.tiuweb.climb.framework.commons.domain.AbstractEntity;

/**
 * 
 * <p>
 * Title: 签约医疗机构医生信息
 * </p>
 * <p>
 * Description: TODO 签约医疗机构医生信息Entity层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-18 10:15:05
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
public class TblMedicalInstitutionDoctor extends AbstractEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 所属签约医疗机构键外
	 */
	private String institutionidentity;
	/**
	 * 所属签约医疗机构名称
	 */
	private String institutionname;
	/**
	 * 医生所属机构地址
	 */
	private String medicalinstitutionaddress;
	/**
	 * 医生登录账户外键
	 */
	private String puserid;
	/**
	 * 医生登录账户
	 */
	private String paccount;
	/**
	 * 医生名称
	 */
	private String doctorname;
	/**
	 * 医生头像
	 */
	private String previewimage;
	/**
	 * 年龄
	 */
	private Short age;
	/**
	 * 性别:0未知的性别,1男性,2女性,9未说明的性别
	 */
	private Short sex;
	/**
	 * 出生日期
	 */
	private Date birthdate;
	/**
	 * 执业医师证编号
	 */
	private String doctorscertificate;
	/**
	 * 执业医师证扫描件
	 */
	private String doctorscertificateimage;
	/**
	 * 医生简介
	 */
	private String doctordesc;
	/**
	 * 证件类型外键
	 */
	private String certificateidentity;
	/**
	 * 证件类型名称
	 */
	private String certificatename;
	/**
	 * varchar(100)
	 */
	private String certificatenumber;
	/**
	 * 联系电话
	 */
	private String telephone;
	/**
	 * 电子邮箱
	 */
	private String email;
	/**
	 * 医生职级类别
	 */
	private Long professionallevelidentity;
	/**
	 * 医生职级类别名称
	 */
	private String professionallevelname;
	/**
	 * 医生所在科室
	 */
	private String officesidentity;
	/**
	 * 所属签约医疗机构科室名称
	 */
	private String officesname;
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
	 * 获取 所属签约医疗机构键外
	 */
	public String getInstitutionidentity() {
		return institutionidentity;
	}

	/**
	 * 设置 所属签约医疗机构键外
	 */
	public void setInstitutionidentity(String institutionidentity) {
		this.institutionidentity = institutionidentity;
	}

	/**
	 * 获取 所属签约医疗机构名称
	 */
	public String getInstitutionname() {
		return institutionname;
	}

	/**
	 * 设置 所属签约医疗机构名称
	 */
	public void setInstitutionname(String institutionname) {
		this.institutionname = institutionname;
	}

	/**
	 * 获取 医生所属机构地址
	 */
	public String getMedicalinstitutionaddress() {
		return medicalinstitutionaddress;
	}

	/**
	 * 设置 医生所属机构地址
	 */
	public void setMedicalinstitutionaddress(String medicalinstitutionaddress) {
		this.medicalinstitutionaddress = medicalinstitutionaddress;
	}

	/**
	 * 获取 医生登录账户外键
	 */
	public String getPuserid() {
		return puserid;
	}

	/**
	 * 设置 医生登录账户外键
	 */
	public void setPuserid(String puserid) {
		this.puserid = puserid;
	}

	/**
	 * 获取 医生登录账户
	 */
	public String getPaccount() {
		return paccount;
	}

	/**
	 * 设置 医生登录账户
	 */
	public void setPaccount(String paccount) {
		this.paccount = paccount;
	}

	/**
	 * 获取 医生名称
	 */
	public String getDoctorname() {
		return doctorname;
	}

	/**
	 * 设置 医生名称
	 */
	public void setDoctorname(String doctorname) {
		this.doctorname = doctorname;
	}

	/**
	 * 获取 医生头像
	 */
	public String getPreviewimage() {
		return previewimage;
	}

	/**
	 * 设置 医生头像
	 */
	public void setPreviewimage(String previewimage) {
		this.previewimage = previewimage;
	}

	/**
	 * 获取 年龄
	 */
	public Short getAge() {
		return age;
	}

	/**
	 * 设置 年龄
	 */
	public void setAge(Short age) {
		this.age = age;
	}

	/**
	 * 获取 性别:0未知的性别,1男性,2女性,9未说明的性别
	 */
	public Short getSex() {
		return sex;
	}

	/**
	 * 设置 性别:0未知的性别,1男性,2女性,9未说明的性别
	 */
	public void setSex(Short sex) {
		this.sex = sex;
	}

	/**
	 * 获取 出生日期
	 */
	public Date getBirthdate() {
		return birthdate;
	}

	/**
	 * 设置 出生日期
	 */
	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	/**
	 * 获取 执业医师证编号
	 */
	public String getDoctorscertificate() {
		return doctorscertificate;
	}

	/**
	 * 设置 执业医师证编号
	 */
	public void setDoctorscertificate(String doctorscertificate) {
		this.doctorscertificate = doctorscertificate;
	}

	/**
	 * 获取 执业医师证扫描件
	 */
	public String getDoctorscertificateimage() {
		return doctorscertificateimage;
	}

	/**
	 * 设置 执业医师证扫描件
	 */
	public void setDoctorscertificateimage(String doctorscertificateimage) {
		this.doctorscertificateimage = doctorscertificateimage;
	}

	/**
	 * 获取 医生简介
	 */
	public String getDoctordesc() {
		return doctordesc;
	}

	/**
	 * 设置 医生简介
	 */
	public void setDoctordesc(String doctordesc) {
		this.doctordesc = doctordesc;
	}

	/**
	 * 获取 证件类型外键
	 */
	public String getCertificateidentity() {
		return certificateidentity;
	}

	/**
	 * 设置 证件类型外键
	 */
	public void setCertificateidentity(String certificateidentity) {
		this.certificateidentity = certificateidentity;
	}

	/**
	 * 获取 证件类型名称
	 */
	public String getCertificatename() {
		return certificatename;
	}

	/**
	 * 设置 证件类型名称
	 */
	public void setCertificatename(String certificatename) {
		this.certificatename = certificatename;
	}

	/**
	 * 获取 varchar(100)
	 */
	public String getCertificatenumber() {
		return certificatenumber;
	}

	/**
	 * 设置 varchar(100)
	 */
	public void setCertificatenumber(String certificatenumber) {
		this.certificatenumber = certificatenumber;
	}

	/**
	 * 获取 联系电话
	 */
	public String getTelephone() {
		return telephone;
	}

	/**
	 * 设置 联系电话
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	/**
	 * 获取 电子邮箱
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * 设置 电子邮箱
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * 获取 医生职级类别
	 */
	public Long getProfessionallevelidentity() {
		return professionallevelidentity;
	}

	/**
	 * 设置 医生职级类别
	 */
	public void setProfessionallevelidentity(Long professionallevelidentity) {
		this.professionallevelidentity = professionallevelidentity;
	}

	/**
	 * 获取 医生职级类别名称
	 */
	public String getProfessionallevelname() {
		return professionallevelname;
	}

	/**
	 * 设置 医生职级类别名称
	 */
	public void setProfessionallevelname(String professionallevelname) {
		this.professionallevelname = professionallevelname;
	}

	/**
	 * 获取 医生所在科室
	 */
	public String getOfficesidentity() {
		return officesidentity;
	}

	/**
	 * 设置 医生所在科室
	 */
	public void setOfficesidentity(String officesidentity) {
		this.officesidentity = officesidentity;
	}

	/**
	 * 获取 所属签约医疗机构科室名称
	 */
	public String getOfficesname() {
		return officesname;
	}

	/**
	 * 设置 所属签约医疗机构科室名称
	 */
	public void setOfficesname(String officesname) {
		this.officesname = officesname;
	}

	/**
	 * 获取 数据状态:1-true已删除,0-false未删除
	 */
	public Boolean getDeletesign() {
		return deletesign;
	}

	/**
	 * 设置 数据状态:1-true已删除,0-false未删除
	 */
	public void setDeletesign(Boolean deletesign) {
		this.deletesign = deletesign;
	}

	/**
	 * 获取 区域编码
	 */
	public String getAreacode() {
		return areacode;
	}

	/**
	 * 设置 区域编码
	 */
	public void setAreacode(String areacode) {
		this.areacode = areacode;
	}

	/**
	 * 获取 机构外键
	 */
	public String getOrgid() {
		return orgid;
	}

	/**
	 * 设置 机构外键
	 */
	public void setOrgid(String orgid) {
		this.orgid = orgid;
	}

}