/*
 * @(#) TblTreatmentRecordsServiceImpl  2017-08-04 11:19:14
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
package com.tiuweb.family.plan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.logs.SystemServiceLog;
import com.tiuweb.family.contact.domain.TblResidentContact;
import com.tiuweb.family.contact.domain.TblResidentContactService;
import com.tiuweb.family.contact.domain.TblServicesCustomer;
import com.tiuweb.family.contact.service.ITblResidentContactService;
import com.tiuweb.family.contact.service.ITblResidentContactServiceService;
import com.tiuweb.family.contact.service.ITblServicesCustomerService;
import com.tiuweb.family.items.domain.TblServcieItem;
import com.tiuweb.family.items.mapper.TblServcieItemMapper;
import com.tiuweb.family.plan.domain.TblResidentContactServiceDoctor;
import com.tiuweb.family.plan.domain.TblResidentContactServicePlan;
import com.tiuweb.family.plan.domain.TblTreatmentRecords;
import com.tiuweb.family.plan.mapper.TblTreatmentRecordsMapper;
import com.tiuweb.family.plan.service.ITblResidentContactServicePlanService;
import com.tiuweb.family.plan.service.ITblTreatmentRecordsService;
import com.tiuweb.family.teams.domain.TblFamilyDoctorTeamMember;
import com.tiuweb.family.teams.mapper.TblFamilyDoctorTeamMemberMapper;

/**
 * 
 * <p>
 * Title: 家庭医生服务记录
 * </p>
 * <p>
 * Description: TODO 家庭医生服务记录Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-08-04 11:19:14
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblTreatmentRecordsServiceImpl extends BaseServiceImpl<TblTreatmentRecords>
		implements ITblTreatmentRecordsService {

	@Autowired
	private TblTreatmentRecordsMapper tblTreatmentRecordsMapper;

	@Autowired
	private TblFamilyDoctorTeamMemberMapper tblFamilyDoctorTeamMemberMapper;

	@Autowired
	private ITblResidentContactService tblResidentContactService;

	@Autowired
	private ITblResidentContactServicePlanService tblResidentContactServicePlanService;

	@Autowired
	private ITblResidentContactServiceService tblResidentContactServiceService;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblTreatmentRecords, String> getMapper() {
		return tblTreatmentRecordsMapper;
	}

	@Override
	@SystemServiceLog(description = "新增服务计划执行记录结果")
	public int execute(TblTreatmentRecords record) throws ServiceRunException {
		if (StringUtils.isNullOrEmpty(record.getServicetreatmentdoctoridentity())) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "未知的随访医生");
		}
		TblResidentContactServicePlan plan = tblResidentContactServicePlanService
				.findByPrimaryKey(record.getPlanidentify());
		if (null == plan) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "不识别的服务计划");
		}

		// 协议信息校验
		TblResidentContact contact = tblResidentContactService.findByPrimaryKey(plan.getContactidentify());
		if (null == contact) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "本次服务计划所属签约协议不存在");
		}
		if (contact.getApplystatus() == 2 || contact.getApplystatus() == 3) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "本次服务计划所属签约协议已到期结束或提前解除");
		}
		if (contact.getApplystatus() == 0) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "本次服务计划所属签约协议未生效");
		}

		record.setRecordidentity(plan.getRecordidentify());
		record.setPlanidentify(plan.getId());
		record.setServiceidentify(plan.getServiceidentify());
		record.setServicename(plan.getServicename());
		record.setDetail(plan.getDetail());

		record.setContactidentify(plan.getContactidentify());
		record.setContactnumber(plan.getContactnumber());

		record.setTeamidentity(contact.getTeamidentity());
		record.setTeamname(contact.getTeamname());

		record.setMemberidentity(plan.getMemberidentity());
		record.setMembername(plan.getMembername());

		record.setSex(plan.getSex());
		record.setAge(plan.getAge());
		record.setIdentitynumber(plan.getIdentitynumber());
		record.setResidenttelphone(plan.getResidenttelphone());
		record.setResidentaddress(plan.getResidentaddress());

		// 根据本次签约医疗团队外键查询同一医疗团队成员
		TblFamilyDoctorTeamMember teamMember = new TblFamilyDoctorTeamMember();
		teamMember.setMedicalteamidentity(contact.getTeamidentity());
		List<TblFamilyDoctorTeamMember> doctors = tblFamilyDoctorTeamMemberMapper.findByCondition(teamMember);

		if (null == doctors || doctors.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "团队成员信息不存在");
		}

		// 更新本次所属协议的签约医生
		initSignDoctor(record, contact, doctors);

		// 更新随访医生信息
		TblFamilyDoctorTeamMember tempDoctor = null;
		tempDoctor = initTreatmentDoctor(record, contact, doctors);

		if (null == record.getServicedate()) {
			record.setServicedate(record.getCreatedate());
		}

		// 新增服务记录
		insert(record);

		// 更新计划状态--已完成
		initPlanStatus(record, plan);

		// 更新 随访医生服务次数
		initUpdateDoctorServiceCount(record, tempDoctor);

		// 更新已服务总数信息并且生成下次服务计划
		initContactServiceCountAndNextPlan(contact, plan, record);

		return 1;
	}

	/**
	 * 更新计划状态
	 * 
	 * @param record
	 * @param plan
	 */
	private void initPlanStatus(TblTreatmentRecords record, TblResidentContactServicePlan plan) {
		TblResidentContactServicePlan updatePlan = new TblResidentContactServicePlan();
		updatePlan.setId(plan.getId());
		updatePlan.setStatus(Short.valueOf("3"));

		// 更細时间为随访时间
		updatePlan.setServicedate(record.getServicedate());
		updatePlan.setUpdatedate(record.getCreatedate());
		updatePlan.setUpdateuser(record.getCreateuser());

		// 更新服务单号
		plan.setServicenumber(record.getServicenumber());

		tblResidentContactServicePlanService.update(updatePlan);
	}

	/**
	 * 更新本次签约服务已服务总数
	 * 
	 * @param queryContact
	 * @param plan
	 * @param record
	 */
	private void initContactServiceCountAndNextPlan(TblResidentContact queryContact, TblResidentContactServicePlan plan,
			TblTreatmentRecords record) {
		TblResidentContactService contactService = tblResidentContactServiceService
				.findByPrimaryKey(plan.getServiceidentify());

		if (null == contactService) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "更新已服务总数信息异常:本次计划所属服务信息不存在");
		}
		TblResidentContactService service = new TblResidentContactService();
		service.setId(plan.getServiceidentify());
		service.setServicecount(contactService.getServicecount() + 1);
		tblResidentContactServiceService.update(contactService);

		// 已服务总数＜服务总数,则生成下次服务计划
		if (service.getServicecount() < contactService.getTotalcount()) {
			if (null == record.getNextservicedate()) {
				throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "生成下次服务计划信息异常:请输入下次随访时间");
			}

			plan.setId(null);
			plan.setUpdatedate(null);
			plan.setUpdateuser(null);
			plan.setCreatedate(record.getCreatedate());
			plan.setCreateuser(record.getCreateuser());
			plan.setDeletesign(false);
			plan.setExpectservicedate(record.getNextservicedate());

			plan.setMedicalteamidentity(queryContact.getTeamidentity());
			plan.setMedicalteamname(queryContact.getTeamname());

			// 设置计划状态 -待确认
			plan.setStatus(Short.valueOf("0"));
			tblResidentContactServicePlanService.insert(plan);

			// 更新签约协议已服务总数
			TblResidentContact updateContact = new TblResidentContact();
			updateContact.setId(queryContact.getId());
			updateContact.setServicecount(queryContact.getServicecount() + 1);
			tblResidentContactService.update(updateContact);
		}
	}

	/**
	 * 更新签约医生信息
	 * 
	 * @param record
	 * @param contact
	 * @param doctors
	 */
	private void initSignDoctor(TblTreatmentRecords record, TblResidentContact contact,
			List<TblFamilyDoctorTeamMember> doctors) {
		TblFamilyDoctorTeamMember tempDoctor = null;
		// 超找当时签约医生
		for (TblFamilyDoctorTeamMember doctor : doctors) {
			// 创建人(协议签约医生)信息与医生外键信息一致
			if (contact.getTeamidentity().equals(doctor.getMedicalteamidentity())
					&& doctor.getDoctoridentity().equals(contact.getCreateuser())) {
				tempDoctor = doctor;
			}
		}

		// 医生签约后有可能离开所属团队后被删除
		if (null != tempDoctor) {
			// 更新签约医生和角色信息
			record.setDoctoridentity(tempDoctor.getDoctoridentity());
			record.setDoctorname(tempDoctor.getDoctorname());

			record.setDoctorroleidentity(tempDoctor.getDoctorroleidentity());
			record.setDoctorrolename(tempDoctor.getDoctorrolename());
		}
	}

	/**
	 * 更新随访医生信息
	 * 
	 * @param record
	 * @param contact
	 * @param doctors
	 * @return
	 */
	private TblFamilyDoctorTeamMember initTreatmentDoctor(TblTreatmentRecords record, TblResidentContact contact,
			List<TblFamilyDoctorTeamMember> doctors) {
		TblFamilyDoctorTeamMember tempDoctor = null;
		// 超找本次随访医生,必须和协议所选保持一致
		for (TblFamilyDoctorTeamMember doctor : doctors) {
			// 创建人(协议签约医生)信息与医生外键信息一致
			if (contact.getTeamidentity().equals(doctor.getMedicalteamidentity())
					&& doctor.getDoctoridentity().equals(record.getServicetreatmentdoctoridentity())) {
				tempDoctor = doctor;
			}
		}

		if (null == tempDoctor) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
					String.format("随访医生在签约团队%s中不存在", new Object[] { contact.getTeamname() }));
		}

		record.setServicetreatmentdoctoridentity(tempDoctor.getDoctoridentity());
		record.setServicetreatmentdoctorname(tempDoctor.getDoctorname());

		record.setServicetreatmentdoctorroleidentity(tempDoctor.getDoctorroleidentity());
		record.setServicetreatmentdoctorrolename(tempDoctor.getDoctorrolename());
		return tempDoctor;
	}

	/**
	 * 更新医生服务次数
	 * 
	 * @param record
	 * @param tempDoctor
	 */
	private void initUpdateDoctorServiceCount(TblTreatmentRecords record, TblFamilyDoctorTeamMember tempDoctor) {
		TblFamilyDoctorTeamMember updateDoctor = new TblFamilyDoctorTeamMember();
		updateDoctor.setId(tempDoctor.getId());
		updateDoctor.setServicecount(tempDoctor.getServicecount() + 1);
		updateDoctor.setUpdateuser(record.getCreateuser());
		updateDoctor.setUpdatedate(record.getCreatedate());
		tblFamilyDoctorTeamMemberMapper.update(updateDoctor);
	}
}
