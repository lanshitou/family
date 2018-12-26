/*
 * @(#) TblResidentContactServiceImpl  2017-07-13 14:20:20
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
package com.tiuweb.family.contact.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.StringUtils;
import com.tiuweb.climb.framework.commons.dao.BaseMapper;
import com.tiuweb.climb.framework.commons.service.impl.BaseServiceImpl;
import com.tiuweb.climb.framework.commons.util.Pagination;
import com.tiuweb.climb.framework.commons.util.RunExceptionEnum;
import com.tiuweb.climb.framework.commons.util.SearchParams;
import com.tiuweb.climb.framework.commons.util.ServiceRunException;
import com.tiuweb.climb.framework.config.logs.SystemServiceLog;
import com.tiuweb.family.contact.domain.TblResidentContact;
import com.tiuweb.family.contact.domain.TblResidentContactRecord;
import com.tiuweb.family.contact.domain.TblResidentContactService;
import com.tiuweb.family.contact.domain.TblResidentHealthArchives;
import com.tiuweb.family.contact.domain.TblServicesCustomer;
import com.tiuweb.family.contact.mapper.TblResidentContactMapper;
import com.tiuweb.family.contact.mapper.TblResidentContactServiceMapper;
import com.tiuweb.family.contact.service.ITblResidentContactRecordService;
import com.tiuweb.family.contact.service.ITblResidentContactService;
import com.tiuweb.family.contact.service.ITblResidentContactServiceService;
import com.tiuweb.family.contact.service.ITblResidentHealthArchivesService;
import com.tiuweb.family.contact.service.ITblServicesCustomerService;
import com.tiuweb.family.home.domain.TblResidentFamilyMember;
import com.tiuweb.family.home.service.ITblResidentFamilyMemberService;
import com.tiuweb.family.items.domain.TblServcieItem;
import com.tiuweb.family.items.domain.TblServcieItemRole;
import com.tiuweb.family.items.mapper.TblServcieItemMapper;
import com.tiuweb.family.items.mapper.TblServcieItemRoleMapper;
import com.tiuweb.family.plan.domain.TblResidentContactServiceDoctor;
import com.tiuweb.family.plan.domain.TblResidentContactServicePlan;
import com.tiuweb.family.plan.service.ITblResidentContactServiceDoctorService;
import com.tiuweb.family.plan.service.ITblResidentContactServicePlanService;
import com.tiuweb.family.teams.domain.TblFamilyDoctorTeam;
import com.tiuweb.family.teams.domain.TblFamilyDoctorTeamMember;
import com.tiuweb.family.teams.mapper.TblFamilyDoctorTeamMemberMapper;
import com.tiuweb.family.teams.service.ITblFamilyDoctorTeamMemberService;
import com.tiuweb.family.teams.service.ITblFamilyDoctorTeamService;
import com.tiuweb.family.util.Utility;

/**
 * 
 * <p>
 * Title: 居民签约协议
 * </p>
 * <p>
 * Description: TODO 居民签约协议Impl层
 *
 * @author tanggeliang
 * @version 1.00.00 创建时间：2017-07-13 14:20:20
 * 
 *          <pre>
 *          修改记录: 版本号 修改人 修改日期 修改内容
 * 
 */
@Service
@Transactional
public class TblResidentContactServiceImpl extends BaseServiceImpl<TblResidentContact>
		implements ITblResidentContactService {

	@Autowired
	private TblResidentContactMapper tblResidentContactMapper;

	@Autowired
	private ITblResidentContactRecordService tblResidentContactRecordService;

	@Autowired
	private ITblResidentContactServiceService tblResidentContactServiceService;

	@Autowired
	private ITblResidentFamilyMemberService tblResidentFamilyMemberService;

	@Autowired
	private ITblServicesCustomerService tblServicesCustomerService;

	@Autowired
	private TblFamilyDoctorTeamMemberMapper tblFamilyDoctorTeamMemberMapper;

	@Autowired
	private TblServcieItemRoleMapper tblServcieItemRoleMapper;

	@Autowired
	private TblServcieItemMapper tblServcieItemMapper;

	@Autowired
	private ITblResidentContactServiceDoctorService tblResidentContactServiceDoctorService;

	@Autowired
	private ITblFamilyDoctorTeamMemberService tblFamilyDoctorTeamMemberService;

	@Autowired
	private ITblResidentContactService tblResidentContactService;

	@Autowired
	private ITblResidentHealthArchivesService tblResidentHealthArchivesService;

	@Autowired
	private ITblResidentContactServicePlanService tblResidentContactServicePlanService;

	@Autowired
	private TblResidentContactServiceMapper tblResidentContactServiceMapper;

	@Autowired
	private ITblFamilyDoctorTeamService tblFamilyDoctorTeamService;

	/* 需要redis服务请打开 */
	// @Autowired
	// private IRedisService redisService;

	@Override
	protected BaseMapper<TblResidentContact, String> getMapper() {
		return tblResidentContactMapper;
	}

	@Transactional(readOnly = true)
	@SystemServiceLog(description = "根据服务对象身份证号码查询当前服务对象的全部预约服务")
	@Override
	public List<TblResidentContact> findContactByCustomer(TblServicesCustomer tblServicesCustomer) {
		List<TblResidentContact> list = tblResidentContactMapper.findContactByCustomer(tblServicesCustomer);
		// 判断返回结果
		if (null == list || list.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
					String.format("%s签约协议不存在", new Object[] { tblServicesCustomer.getIdentitynumber() }));
		}

		if (list.size() > 1) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
					String.format("%s存在于多份签约协议,预约服务信息异常", new Object[] { tblServicesCustomer.getIdentitynumber() }));
		}

		List<TblResidentContact> enableContact = new ArrayList<TblResidentContact>();
		for (TblResidentContact contact : list) {
			if (contact.getApplystatus() == 0 || contact.getApplystatus() == 1) {
				enableContact.add(contact);
			}
		}

		if (enableContact.isEmpty()) {
			String identityNumber = tblServicesCustomer.getIdentitynumber();
			if (StringUtils.isNullOrEmpty(identityNumber)) {
				identityNumber = "";
			}
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
					String.format("%s不存在待审批或已生效的签约协议", new Object[] { identityNumber }));
		}
		return list;
	}

	@Override
	@SystemServiceLog(description = "签约协议添加")
	public int insertContact(TblResidentContact contact, String loginName) throws ServiceRunException {

		// 查询是否存在签约协议:待审批||已生效
		SearchParams params = new SearchParams();
		Map<String, Object> searchParams = new HashMap<String, Object>();
		searchParams.put("identitynumber", contact.getIdentitynumber());
		searchParams.put("applystatus", "0,1");

		params.setSearchParams(searchParams);
		Pagination<TblResidentContact> existsContact = tblResidentContactService.findPageByCondition(params);

		// 签约服务信息
		if (existsContact.getRows().size() > 0) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(),
					String.format("%s存在签约协议", new Object[] { contact.getIdentitynumber() }));
		}

		List<TblResidentContactService> serviceItmes = contact.getServices();

		// 签约服务信息
		if (null == serviceItmes || serviceItmes.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约服务无效");
		} else {
			// 签约人部分信息
			TblResidentFamilyMember contactMember = getContactMemberByIdentityNumber(contact);
			contact.setTelephone(contactMember.getTelephone());
			contact.setContactmemberidentity(contactMember.getId());
			contact.setApplyname(contactMember.getMembername());
			// 居民健康档案外键
			contact.setResidenthealtharchivesidentity(contactMember.getHealthidentity());
			// 居民健康档案编号
			contact.setResidenthealtharchivesnumber(contactMember.getArchivesnumber());
			// 签约人家庭外键
			contact.setResidentfamilyidentity(contactMember.getFamilyidentity());
			// 签约人家庭编号
			contact.setResidentfamilynumber(contactMember.getFamilynumber());

			List<TblResidentFamilyMember> familys = initFamilysMemberBySignType(contact);

			// 签约记录初始化
			TblResidentContactRecord tblResidentContactRecord = new TblResidentContactRecord();
			tblResidentContactRecord.setApplydate(contact.getCreatedate());
			tblResidentContactRecord.setApplyidentity(contact.getCreateuser());
			tblResidentContactRecord.setAuthidentity(contact.getCreateuser());
			tblResidentContactRecord.setEnabledate(contact.getCreatedate());
			tblResidentContactRecord.setIsrenew(false);
			tblResidentContactRecord.setIsresidentapply(false);
			tblResidentContactRecord.setEnddate(Utility.getYearAddedOne(contact.getCreatedate()));

			// 签约记录添加
			tblResidentContactRecordService.insert(tblResidentContactRecord);

			// 设置记录主键
			contact.setId(Utility.get32UUID());
			// 添加签约记录外键
			contact.setRecordidentity(tblResidentContactRecord.getId());

			// 根据本次签约医疗团队外键查询同一医疗团队成员
			TblFamilyDoctorTeamMember teamMember = new TblFamilyDoctorTeamMember();
			teamMember.setMedicalteamidentity(contact.getTeamidentity());
			List<TblFamilyDoctorTeamMember> doctors = tblFamilyDoctorTeamMemberMapper.findByCondition(teamMember);

			TblServcieItem serviceItem = null;
			int countIndex = 0;
			int allCount = 0;

			for (TblResidentContactService item : serviceItmes) {

				serviceItem = tblServcieItemMapper.findByPrimaryKey(item.getServiceitemidentity());

				if (null == serviceItem) {
					throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
							String.format("服务%s不存在", new Object[] { item.getServiceitemidentity() }));
				}

				item = initContactServiceItem(serviceItem, item, contact, tblResidentContactRecord);
				item.setId(Utility.get32UUID());

				// 服务对象添加
				List<TblServicesCustomer> servicesTarget = initServiceTraget(contact, item, contactMember, familys);
				// 设置服务对象信息
				item.setCustomer(servicesTarget);

				countIndex = getAllServiceCount(contact, serviceItem, item, tblResidentContactRecord);

				// 服务计划总数
				item.setTotalcount(countIndex);
				allCount = allCount + countIndex;

				item.setServicecount(0);

				tblResidentContactServiceService.insert(item);

				tblServicesCustomerService.insertBatch(servicesTarget);

				// 当前服务所需角色
				List<TblResidentContactServiceDoctor> needDoctors = initNeedDoctors(contact, doctors, item);

				// 生成第一条计划
				initFirstServicePlan(contact, item, serviceItem, servicesTarget, needDoctors);
			}

			// 设置服务计划总数
			contact.setTotalcount(allCount);

			TblFamilyDoctorTeam team = tblFamilyDoctorTeamService.findByPrimaryKey(contact.getTeamidentity());

			// 更新团队签约总数
			initTeamContactCount(team, contact);

			// 更新本次签约医生接受签约协议总数
			initAcceptDoctorContactCount(contact, loginName, doctors, team, true);
		}
		return 1;
	}

	/**
	 * 更新本次签约医生接受签约协议总数
	 * 
	 * @param contact
	 * @param loginName
	 * @param doctors
	 * @param team
	 */
	private void initAcceptDoctorContactCount(TblResidentContact contact, String loginName,
			List<TblFamilyDoctorTeamMember> doctors, TblFamilyDoctorTeam team, boolean isInsert) {
		List<TblFamilyDoctorTeamMember> acceptContactDoctor = new ArrayList<TblFamilyDoctorTeamMember>();
		for (TblFamilyDoctorTeamMember doctor : doctors) {
			// 当前登录账户存在于当前签约协议所属医疗团队
			if (doctor.getMedicalteamidentity().equals(contact.getTeamidentity())
					&& contact.getCreateuser().equals(doctor.getDoctoridentity())) {
				acceptContactDoctor.add(doctor);
			}
		}

		if (acceptContactDoctor.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
					String.format("更新本次签约医生数据异常:当前登录用户%s在团队%s中不存在", new Object[] { loginName, team.getTeamname() }));
		}

		// 参考原型【个人资料】=>[签约信息]一个医生可存在于多个团队,在团队中角色必须唯一
		if (acceptContactDoctor.size() > 1) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
					String.format("更新本次签约医生数据异常:当前登录用户%s在团队%s中角色不唯一", new Object[] { loginName, team.getTeamname() }));
		}

		TblFamilyDoctorTeamMember acceptDoctor = acceptContactDoctor.get(0);
		TblFamilyDoctorTeamMember updateDoctor = new TblFamilyDoctorTeamMember();
		updateDoctor.setId(acceptDoctor.getId());
		updateDoctor.setContactcount(acceptDoctor.getContactcount() + 1);
		updateDoctor.setUpdateuser(contact.getUpdateuser());
		updateDoctor.setUpdatedate(contact.getUpdatedate());
		tblFamilyDoctorTeamMemberService.update(updateDoctor);

		// 更细签约医生信息
		contact.setTeamdoctoridentity(acceptDoctor.getId());
		contact.setTeamdoctorname(acceptDoctor.getDoctorname());

		// 签约协议添加
		if (isInsert) {
			insert(contact);
		}
		// 签约协议更新
		else {
			update(contact);
		}
	}

	/**
	 * 更新医疗团队签约总数
	 * 
	 * @param contact
	 */
	private void initTeamContactCount(TblFamilyDoctorTeam team, TblResidentContact contact) {
		Long allCount = team.getContactcount() + 1;
		TblFamilyDoctorTeam updateTeam = new TblFamilyDoctorTeam();
		updateTeam.setId(contact.getTeamidentity());
		updateTeam.setContactcount(allCount);
		team.setUpdateuser(contact.getUpdateuser());
		team.setUpdatedate(contact.getUpdatedate());
		tblFamilyDoctorTeamService.update(updateTeam);
	}

	/**
	 * 生成非预约服务项目的第一条服务计划
	 * 
	 * @param contact
	 * @param item
	 * @param serviceItem
	 * @param servicesCustomer
	 * @param needDoctors
	 */
	private void initFirstServicePlan(TblResidentContact contact, TblResidentContactService item,
			TblServcieItem serviceItem, List<TblServicesCustomer> servicesCustomer,
			List<TblResidentContactServiceDoctor> needDoctors) {
		// 非预约服务
		if (item.getIsappointment() == 0) {
			if (null == item.getFirstservicedate()) {
				throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
						String.format("服务项%s首次服务时间不存在", new Object[] { item.getServicename() }));
			}

			TblResidentContactServiceDoctor doctor = needDoctors.get(0);

			// 给每一个服务对象生成第一次服务计划
			for (TblServicesCustomer customer : servicesCustomer) {
				TblResidentContactServicePlan servicePlan = new TblResidentContactServicePlan();
				// 创建属性信息
				servicePlan.setDeletesign(false);
				servicePlan.setAreacode(contact.getAreacode());
				servicePlan.setCreateuser(contact.getCreateuser());
				servicePlan.setCreatedate(contact.getCreatedate());
				// 所属医疗机构
				servicePlan.setOrgid(contact.getOrgid());

				// 服务项目分类信息
				servicePlan.setServicecategoryid(item.getServicecategoryid());
				servicePlan.setServicecategoryname(item.getServicecategoryname());

				servicePlan.setServiceidentify(item.getId());
				servicePlan.setServicename(item.getServicename());
				servicePlan.setDetail(serviceItem.getDetail());

				servicePlan.setRecordidentify(contact.getRecordidentity());
				servicePlan.setContactidentify(contact.getId());
				servicePlan.setContactnumber(contact.getContactnumber());

				servicePlan.setExpectservicedate(item.getFirstservicedate());

				servicePlan.setMemberidentity(customer.getMemberidentity());
				servicePlan.setMembername(customer.getMembername());
				servicePlan.setAge(customer.getAge());
				servicePlan.setSex(customer.getSex());
				servicePlan.setIdentitynumber(customer.getIdentitynumber());
				servicePlan.setResidenttelphone(customer.getTelephone());
				servicePlan.setResidentaddress(customer.getAddress());

				servicePlan.setMedicalteamidentity(contact.getTeamidentity());
				servicePlan.setMedicalteamname(contact.getTeamname());
				servicePlan.setDoctoridentity(doctor.getDoctoridentity());
				servicePlan.setDoctorname(doctor.getDoctorname());
				servicePlan.setDoctorroleidentity(doctor.getDoctorroleidentity());
				servicePlan.setDoctorrolename(doctor.getDoctorrolename());

				servicePlan.setIsappointment(item.getIsappointment());
				servicePlan.setIstransfer(false);

				servicePlan.setStatus((short) 0);

				tblResidentContactServicePlanService.insert(servicePlan);
			}
		}
	}

	/**
	 * 根据签约类型决定是否查询家庭信息
	 * 
	 * @param contact
	 * @param contactMember
	 * @return
	 */
	private List<TblResidentFamilyMember> initFamilysMemberBySignType(TblResidentContact contact) {
		List<TblResidentFamilyMember> familys = null;
		if (contact.getSigntype() == 1) {
			// 根据居民家庭外键查询全部家庭成员信息
			TblResidentFamilyMember member = new TblResidentFamilyMember();
			member.setIdentitynumber(contact.getIdentitynumber());
			familys = tblResidentFamilyMemberService.findAllMemberByCondition(member);

			// 家庭信息不存在
			if (null == familys || familys.isEmpty()) {
				throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
						String.format("%s家庭信息不存在", new Object[] { contact.getApplyname() }));
			}
		}
		return familys;
	}

	/**
	 * 根据服务周期分类计算服务计划总数
	 * 
	 * @param contact
	 * @param serviceItem
	 * @param item
	 * @return
	 */
	private int getAllServiceCount(TblResidentContact contact, TblServcieItem serviceItem,
			TblResidentContactService item, TblResidentContactRecord tblResidentContactRecord) {
		// 服务计划计量:1..N次 * item.getCustomer().size()
		//
		int baseCount = item.getCustomer().size() * serviceItem.getServicemesure();
		switch (Integer.parseInt(serviceItem.getPeriodidentity())) {

		// 年
		case 0:
			return baseCount;
		// 月
		case 1:
			return baseCount * Utility.getMonthBetween(tblResidentContactRecord.getCreatedate(),
					tblResidentContactRecord.getEnddate());
		// 周
		case 2:
			return baseCount * Utility.getWeeksBeforeEndDate(tblResidentContactRecord.getCreatedate(),
					tblResidentContactRecord.getEnddate());
		default:
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
					String.format("不识别的服务周期分类", new Object[] { serviceItem.getPeriodidentity() }));
		}
	}

	/**
	 * 初始化签约的服务项目信息
	 * 
	 * @param item
	 * @param contact
	 * @return
	 */
	private TblResidentContactService initContactServiceItem(TblServcieItem serviceItem, TblResidentContactService item,
			TblResidentContact contact, TblResidentContactRecord tblResidentContactRecord) {
		// 创建属性信息
		item.setDeletesign(false);
		item.setAreacode(contact.getAreacode());
		item.setCreateuser(contact.getCreateuser());
		item.setCreatedate(contact.getCreatedate());
		// 所属医疗机构
		item.setOrgid(contact.getOrgid());

		item.setContactidentity(contact.getId());
		item.setRecordidentity(contact.getRecordidentity());
		item.setServiceitemidentity(serviceItem.getId());

		item.setServicecategoryid(serviceItem.getServicecategoryid());
		item.setServicecategoryname(serviceItem.getServicecategoryname());

		item.setApplystatus((short) 1);
		item.setContactnumber(contact.getContactnumber());

		// 已服务总数默认为0
		item.setServicecount(0);

		// 服务名称
		item.setServicename(serviceItem.getServicename());
		// 已传递参数
		// item.setFirstservicedate(new Date());

		// 是否基础服务
		item.setIsfoundation(serviceItem.getIsfoundation());
		// 已传递参数
		// item.setCustomercategory(customercategory);
		item.setIsappointment(serviceItem.getIsappointment());
		// 服务项目审批状态
		item.setIsenable(true);

		// 服务计划计量
		item.setServicemesure(serviceItem.getServicemesure());
		// 服务计划计量单位
		item.setServicemesureunit(serviceItem.getServicemesureunit());

		// 服务计划周期外键
		item.setServiceperiodidentity(serviceItem.getPeriodidentity());

		// 预计费用
		item.setServicecharge(serviceItem.getServicecharge());
		// 费用支付频率外键
		item.setServicechargefrequencyidentity(serviceItem.getServicechargefrequencyidentity());
		// 费用支付频率名称
		item.setServicechargefrequencyname(serviceItem.getChargefrequencyname());
		// 预计费用单位
		item.setServicechargeunit(serviceItem.getServicechargeunit());

		item.setServicereward(serviceItem.getServicereward());

		item.setDoctormesure(serviceItem.getDoctormesure());
		item.setDoctormesureunit(serviceItem.getDoctormesureunit());

		item.setMedicalequipmentidentity(serviceItem.getMedicalequipmentidentity());
		item.setMedicalequipmentname(serviceItem.getMedicalequipmentname());

		return item;
	}

	/**
	 * 初始化签约服务所需医生及其角色信息
	 * 
	 * @param record
	 * @param doctors
	 * @param item
	 * @returnD82D7C4AC72E4160B311E19DDD7E92FD
	 */
	private List<TblResidentContactServiceDoctor> initNeedDoctors(TblResidentContact record,
			List<TblFamilyDoctorTeamMember> doctors, TblResidentContactService item) {
		TblServcieItemRole teamRole = new TblServcieItemRole();
		teamRole.setServcieitemidentity(item.getServiceitemidentity());

		// 根据服务项外键查询当前服务项医生角色
		List<TblServcieItemRole> allRoles = tblServcieItemRoleMapper.findByCondition(teamRole);

		if (null == allRoles || allRoles.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
					String.format("本次签约医疗服务%s所需医生不存在", new Object[] { item.getServicename() }));
		}

		List<TblResidentContactServiceDoctor> needDoctors = new ArrayList<TblResidentContactServiceDoctor>();
		for (TblServcieItemRole role : allRoles) {
			for (TblFamilyDoctorTeamMember doctor : doctors) {
				// 医生角色符合服务项要求角色
				if (role.getMedicalteamroleidentity().equals(doctor.getDoctorroleidentity())) {
					TblResidentContactServiceDoctor itemDoctor = new TblResidentContactServiceDoctor();
					// 签约协议外键
					itemDoctor.setContactidentity(record.getId());
					// 签约协议记录外键
					itemDoctor.setRecordidentity(record.getRecordidentity());
					// 服务项外键
					itemDoctor.setServcieitemdetailidentity(item.getId());
					// 所在团队成员信息表主键
					itemDoctor.setTeamdoctoridentity(doctor.getId());
					// 医生外键
					itemDoctor.setDoctoridentity(doctor.getDoctoridentity());
					// 医生名称
					itemDoctor.setDoctorname(doctor.getDoctorname());
					// 医生角色外键
					itemDoctor.setDoctorroleidentity(doctor.getDoctorroleidentity());
					// 医生角色名称
					itemDoctor.setDoctorrolename(doctor.getDoctorrolename());
					itemDoctor.setDeletesign(false);
					itemDoctor.setCreatedate(record.getCreatedate());
					itemDoctor.setCreateuser(record.getCreateuser());
					itemDoctor.setOrgid(record.getOrgid());
					needDoctors.add(itemDoctor);
				}
			}

			// 当前服务所需角色在签约团队中没有找到
			if (needDoctors.isEmpty()) {
				throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), String
						.format("在团队%s中没有找到角色为%s的医生", new Object[] { record.getTeamname(), role.getRolename(), }));
			}
		}
		tblResidentContactServiceDoctorService.insertBatch(needDoctors);
		return needDoctors;
	}

	/**
	 * 初始化服务对象信息
	 * 
	 * @param record
	 * @param item
	 * @param contactMember
	 * @return
	 */
	private List<TblServicesCustomer> initServiceTraget(TblResidentContact record, TblResidentContactService item,
			TblResidentFamilyMember contactMember, List<TblResidentFamilyMember> familys) {
		Short category = item.getCustomercategory();
		List<TblServicesCustomer> servicesTarget = new ArrayList<TblServicesCustomer>();
		// 全部家庭成员
		if (0 == category) {
			// // 根据居民家庭外键查询全部家庭成员信息
			// TblResidentFamilyMember member = new TblResidentFamilyMember();
			// member.setFamilyidentity(record.getResidentfamilyidentity());
			// List<TblResidentFamilyMember> familys =
			// tblResidentFamilyMemberService.findByCondition(member);
			//
			// // 家庭信息不存在
			// if (null == familys || familys.isEmpty()) {
			// throw new
			// ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
			// String.format("%s家庭信息不存在", new Object[] { record.getApplyname()
			// }));
			// }

			for (TblResidentFamilyMember fmember : familys) {
				TblServicesCustomer customer = getTblServicesCustomer(fmember, record, item);
				servicesTarget.add(customer);
			}
		}
		// 仅本人
		else if (1 == category) {
			servicesTarget.add(getTblServicesCustomer(contactMember, record, item));
		}
		// 其他
		else if (2 == category) {
			// 已选择的服务对象信息-根据主键重新查询,补全信息
			List<TblServicesCustomer> customers = item.getCustomer();
			for (TblServicesCustomer cust : customers) {
				for (TblResidentFamilyMember fmember : familys) {
					if (cust.getId().equals(fmember.getId())) {
						servicesTarget.add(getTblServicesCustomer(fmember, record, item));
					}
				}
			}
		}
		return servicesTarget;
	}

	/**
	 * 根据身份证号查询签约人信息
	 * 
	 * @param record
	 * @param item
	 * @return
	 */
	private TblResidentFamilyMember getContactMemberByIdentityNumber(TblResidentContact record) {
		TblResidentFamilyMember member = new TblResidentFamilyMember();
		member.setIdentitynumber(record.getIdentitynumber());

		// 家庭签约
		if (record.getSigntype() == 1) {
			// 根据家庭成员身份证号查询个人信息
			List<TblResidentFamilyMember> self = tblResidentFamilyMemberService.findByCondition(member);

			// 服务项目签约人判断
			if (null == self || self.isEmpty()) {
				throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
						String.format("签约协议申请人%s不存在", new Object[] { record.getApplyname() }));
			}
			if (self.size() > 1) {
				throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
						String.format("签约协议申请人%s重复", new Object[] { record.getApplyname() }));
			}
			return self.get(0);
		}
		// 个人签约
		else {
			TblResidentHealthArchives archives = new TblResidentHealthArchives();
			archives.setIdentitynumber(record.getIdentitynumber());
			List<TblResidentHealthArchives> archivesList = tblResidentHealthArchivesService.findByCondition(archives);
			// 服务项目签约人判断
			if (null == archivesList || archivesList.isEmpty()) {
				throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
						String.format("签约协议申请人%s不存在", new Object[] { record.getApplyname() }));
			}
			if (archivesList.size() > 1) {
				throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
						String.format("签约协议申请人%s重复", new Object[] { record.getApplyname() }));
			}

			TblResidentHealthArchives temp = archivesList.get(0);
			member.setHealthidentity(temp.getId());
			member.setArchivesnumber(temp.getArchivesnumber());

			member.setAge(temp.getAge());
			member.setSex(temp.getSex());
			member.setMembername(temp.getResidentname());
			member.setBirthdate(temp.getBirthdate());
			member.setAddress(temp.getPresentaddress());
			member.setTelephone(temp.getLinkmanphone());
			return member;
		}

	}

	/**
	 * 构造服务对象信息
	 * 
	 * @param fmember
	 * @param record
	 * @param item
	 * @return
	 */
	private TblServicesCustomer getTblServicesCustomer(TblResidentFamilyMember fmember, TblResidentContact record,
			TblResidentContactService item) {
		TblServicesCustomer customer = new TblServicesCustomer();
		customer.setContactidentity(record.getId());
		customer.setServiceitemidentity(item.getId());
		customer.setResidenthealtharchivesidentity(fmember.getHealthidentity());
		customer.setResidenthealtharchivesnumber(fmember.getArchivesnumber());
		customer.setRecordidentity(record.getRecordidentity());
		// 家庭成员外键
		customer.setMemberidentity(fmember.getId());
		// 居民家庭外键
		customer.setFamilyidentity(fmember.getFamilyidentity());
		// 家庭编号
		customer.setFamilynumber(fmember.getFamilynumber());
		// 所属服务类别
		customer.setCustomercategory(item.getCustomercategory());
		customer.setMembername(fmember.getMembername());
		customer.setIdentitynumber(fmember.getIdentitynumber());
		customer.setBirthdate(fmember.getBirthdate());
		customer.setAge(fmember.getAge());
		customer.setSex(fmember.getSex());
		customer.setTelephone(fmember.getTelephone());
		customer.setAddress(fmember.getAddress());

		// 本次签约服务服务对象归属信息
		customer.setCreateuser(record.getCreateuser());
		customer.setCreatedate(record.getCreatedate());
		customer.setOrgid(record.getOrgid());
		customer.setDeletesign(false);

		return customer;
	}

	@Override
	@Transactional(readOnly = true)
	@SystemServiceLog(description = "根据签约协议主键查询签约协议详情")
	public TblResidentContact findDetailByKey(TblResidentContact record) throws ServiceRunException {
		TblResidentContact result = tblResidentContactService.findByPrimaryKey(record.getId());

		// 查询签约协议记录信息:包含申请和审以及状态信息批信息
		result.setTblResidentContactRecord(
				tblResidentContactRecordService.findByPrimaryKey(result.getRecordidentity()));
		// 构造医疗团队查询对象
		TblFamilyDoctorTeamMember doctor = new TblFamilyDoctorTeamMember();
		doctor.setMedicalteamidentity(result.getTeamidentity());

		// 查询签约医疗团队成员
		result.setTblFamilyDoctorTeamRoles(tblFamilyDoctorTeamMemberService.findByCondition(doctor));

		// 查询本签约全部签约服务
		TblResidentContactService service = new TblResidentContactService();
		service.setContactidentity(result.getId());
		service.setRecordidentity(result.getRecordidentity());

		List<TblResidentContactService> allServices = tblResidentContactServiceService.findByCondition(service);

		// 根据签约协议查询本次签约服务对应的全部服务对象D82D7C4AC72E4160B311E13DDD7E92FD
		Map<String, Object> tblServicesCustomer = new HashMap<String, Object>();
		tblServicesCustomer.put("recordidentity", result.getRecordidentity());
		List<TblServicesCustomer> allCustomers = tblServicesCustomerService.findCustomerByContact(tblServicesCustomer);

		if (null != allServices) {
			for (TblResidentContactService contactservice : allServices) {
				List<TblServicesCustomer> tempCustomer = new ArrayList<TblServicesCustomer>();
				for (TblServicesCustomer custoemr : allCustomers) {
					if (record.getId().equalsIgnoreCase(custoemr.getContactidentity())
							&& contactservice.getId().equalsIgnoreCase(custoemr.getServiceitemidentity())) {
						tempCustomer.add(custoemr);
					}
				}
				contactservice.setCustomer(tempCustomer);
			}
		}

		result.setServices(allServices);

		return result;
	}

	@Override
	@Transactional(readOnly = true)
	@SystemServiceLog(description = "根据身份证号查询居民预约服务信息")
	public TblResidentContact findAppointmentByCon(TblServicesCustomer tblServicesCustomer) throws ServiceRunException {
		List<TblResidentContact> list = findContactByCustomer(tblServicesCustomer);

		TblResidentContact tblResidentContact = list.get(0);

		// 查询预约服务信息,查找同一批次预约服务信息
		TblResidentContactService tblResidentContactService = new TblResidentContactService();
		tblResidentContactService.setRecordidentity(tblResidentContact.getRecordidentity());
		tblResidentContactService.setIsappointment((short) 1);
		List<TblResidentContactService> allAppointmentServices = tblResidentContactServiceService
				.findByCondition(tblResidentContactService);
		if (null == allAppointmentServices || allAppointmentServices.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.RESP_RESULT_EMPTY.getCode(), "预约服务不存在");
		}

		Map<String, Object> appoinement = new HashMap<String, Object>();
		// 根据服务项主键和身份证号查询预约服务所属服务对象
		TblServicesCustomer queryCustomer = new TblServicesCustomer();
		queryCustomer.setRecordidentity(tblResidentContact.getRecordidentity());
		queryCustomer.setIdentitynumber(tblServicesCustomer.getIdentitynumber());
		// queryCustomer.setServiceitemidentity(contactservice.getId());
		List<TblServicesCustomer> allCustomers = tblServicesCustomerService.findByCondition(queryCustomer);

		// 获取预约服务对象基本信息
		for (TblServicesCustomer customer : allCustomers) {
			if (customer.getIdentitynumber().equals(tblServicesCustomer.getIdentitynumber())) {
				if (appoinement.isEmpty()) {
					TblServicesCustomer tempCustomer = allCustomers.get(0);
					appoinement.put("applyname", tempCustomer.getMembername());
					appoinement.put("age", tempCustomer.getAge());
					appoinement.put("sex", tempCustomer.getSex());
					appoinement.put("telephone", tempCustomer.getTelephone());
					appoinement.put("address", tempCustomer.getAddress());
					break;
				}
			}
		}

		List<TblResidentContactService> existsAppointmentServices = new ArrayList<TblResidentContactService>();
		for (TblResidentContactService contactservice : allAppointmentServices) {
			for (TblServicesCustomer customer : allCustomers) {
				// 该服务对象渔有本次预约服务项目
				if (customer.getIdentitynumber().equals(tblServicesCustomer.getIdentitynumber())
						&& contactservice.getId().equals(customer.getServiceitemidentity())) {
					existsAppointmentServices.add(contactservice);
				}
			}
		}

		tblResidentContact.setAppoinement(appoinement);
		tblResidentContact.setServices(existsAppointmentServices);
		return tblResidentContact;
	}

	@Override
	@SystemServiceLog(description = "解除居民签约协议和签约对应服务以及对应服务计划")
	public int relieve(TblResidentContact record) throws ServiceRunException {
		if (StringUtils.isNullOrEmpty(record.getId())) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "不识别的签约协议");
		}

		TblResidentContact queryContact = findByPrimaryKey(record.getId());
		if (null == queryContact) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "要解除的签约协议不存在");
		}

		if (queryContact.getApplystatus() == 3) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "不能重复解除");
		}

		if (queryContact.getApplystatus() == 0) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "不能解除未生效的协议");
		}

		// 解除居民签约协议 =>状态3
		record.setRecordidentity(queryContact.getRecordidentity());
		tblResidentContactMapper.relieve(record);
		// 解除签约服务
		TblResidentContactService tblResidentContactService = new TblResidentContactService();
		tblResidentContactService.setRecordidentity(record.getRecordidentity());
		tblResidentContactService.setApplystatus(record.getApplystatus());

		tblResidentContactService.setUpdatedate(record.getUpdatedate());
		tblResidentContactService.setUpdateuser(record.getUpdateuser());

		tblResidentContactServiceMapper.relieve(tblResidentContactService);
		// 解除服务计划
		TblResidentContactServicePlan tblResidentContactServicePlan = new TblResidentContactServicePlan();
		tblResidentContactServicePlan.setRecordidentify(record.getRecordidentity());
		tblResidentContactServicePlan.setStatus(record.getApplystatus());

		tblResidentContactServicePlan.setUpdatedate(record.getUpdatedate());
		tblResidentContactServicePlan.setUpdateuser(record.getUpdateuser());
		tblResidentContactServicePlanService.relieve(tblResidentContactServicePlan);
		return 1;
	}

	@Override
	@SystemServiceLog(description = "审批居民签约协议和签约对应服务以及生成服务计划")
	public int approval(TblResidentContact tblResidentContact, String loginName) throws ServiceRunException {
		if (StringUtils.isNullOrEmpty(tblResidentContact.getId())) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "不识别的签约协议");
		}

		TblResidentContact queryContact = findByPrimaryKey(tblResidentContact.getId());
		if (null == queryContact) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约协议不存在");
		}

		if (queryContact.getApplystatus() == 1) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "不能重复审批");
		}

		if (queryContact.getApplystatus() == 2 || queryContact.getApplystatus() == 3) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "不能审批到期结束或已解除的协议");
		}

		// 签约记录查询
		TblResidentContactRecord tblResidentContactRecord = tblResidentContactRecordService
				.findByPrimaryKey(queryContact.getRecordidentity());

		if (null == tblResidentContactRecord) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约协议数据异常:没有签约记录信息");
		}

		initUpdateContactAndItems(tblResidentContact, queryContact);

		initServicePlans(queryContact);

		// 根据本次签约医疗团队外键查询同一医疗团队成员
		TblFamilyDoctorTeamMember teamMember = new TblFamilyDoctorTeamMember();
		teamMember.setMedicalteamidentity(queryContact.getTeamidentity());
		List<TblFamilyDoctorTeamMember> doctors = tblFamilyDoctorTeamMemberMapper.findByCondition(teamMember);
		TblFamilyDoctorTeam team = tblFamilyDoctorTeamService.findByPrimaryKey(queryContact.getTeamidentity());

		// 更新团队签约总数
		initTeamContactCount(team, queryContact);

		// 更新本次签约医生接受签约协议总数
		initAcceptDoctorContactCount(queryContact, loginName, doctors, team, true);
		return 1;
	}

	/**
	 * 初始化签约协议和服务想信息
	 * 
	 * @param tblResidentContact
	 * @param queryContact
	 */
	private void initUpdateContactAndItems(TblResidentContact tblResidentContact, TblResidentContact queryContact) {
		TblResidentContactRecord updateContactRecord = new TblResidentContactRecord();
		// 审核信息
		updateContactRecord.setId(queryContact.getRecordidentity());
		updateContactRecord.setAuthidentity(tblResidentContact.getUpdateuser());
		updateContactRecord.setEnabledate(tblResidentContact.getUpdatedate());
		updateContactRecord.setIsrenew(false);
		updateContactRecord.setEnddate(Utility.getYearAddedOne(tblResidentContact.getUpdatedate()));

		// 审核信息更新
		tblResidentContactRecordService.update(updateContactRecord);

		// 签约协议生效日期
		tblResidentContact.setEnabledate(tblResidentContact.getUpdatedate());
		update(tblResidentContact);

		// 审批签约服务=>使用解除的方法:更新recordidentity相同的签约服务
		TblResidentContactService tblResidentContactService = new TblResidentContactService();
		tblResidentContactService.setRecordidentity(queryContact.getRecordidentity());
		tblResidentContactService.setApplystatus(tblResidentContact.getApplystatus());

		tblResidentContactService.setUpdatedate(tblResidentContact.getUpdatedate());
		tblResidentContactService.setUpdateuser(tblResidentContact.getUpdateuser());

		tblResidentContactServiceMapper.relieve(tblResidentContactService);
	}

	/**
	 * 初始化服务计划信息
	 * 
	 * @param queryContact
	 */
	private void initServicePlans(TblResidentContact queryContact) {
		// 根据recordidentity查询本次签约全部签约服务以及服务对象和医生信息
		TblResidentContactService queryContactService = new TblResidentContactService();
		queryContactService.setRecordidentity(queryContact.getRecordidentity());
		List<TblResidentContactService> allContactItems = tblResidentContactServiceService
				.findByCondition(queryContactService);

		if (null == allContactItems || allContactItems.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约协议数据异常:没有签约服务信息");
		}
		TblResidentContactServiceDoctor queryContactServiceDoctor = new TblResidentContactServiceDoctor();
		queryContactServiceDoctor.setRecordidentity(queryContact.getRecordidentity());
		List<TblResidentContactServiceDoctor> allContactDoctors = tblResidentContactServiceDoctorService
				.findByCondition(queryContactServiceDoctor);
		if (null == allContactDoctors || allContactDoctors.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约协议数据异常:没有签约服务医生信息");
		}

		// 根据本次签约医疗团队外键查询同一医疗团队成员
		TblFamilyDoctorTeamMember teamMember = new TblFamilyDoctorTeamMember();
		teamMember.setMedicalteamidentity(queryContact.getTeamidentity());

		// 签约人部分信息
		TblResidentFamilyMember contactMember = getContactMemberByIdentityNumber(queryContact);
		List<TblResidentFamilyMember> familys = initFamilysMemberBySignType(queryContact);

		TblServcieItem serviceItem = null;
		for (TblResidentContactService item : allContactItems) {
			serviceItem = tblServcieItemMapper.findByPrimaryKey(item.getServiceitemidentity());
			List<TblResidentContactServiceDoctor> needDoctors = new ArrayList<TblResidentContactServiceDoctor>();

			for (TblResidentContactServiceDoctor doctor : allContactDoctors) {
				if (item.getId().equals(doctor.getServcieitemdetailidentity())) {
					needDoctors.add(doctor);
				}
			}
			List<TblServicesCustomer> servicesTarget = initServiceTraget(queryContact, item, contactMember, familys);

			// 生成第一条计划
			initFirstServicePlan(queryContact, item, serviceItem, servicesTarget, needDoctors);
		}
	}

	@Override
	@SystemServiceLog(description = "居民签约协议续签")
	public int renew(TblResidentContact tblResidentContact, String loginName) throws ServiceRunException {
		TblResidentContact queryContact = tblResidentContactMapper.findByPrimaryKey(tblResidentContact.getId());
		if (null == queryContact) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约协议不存在");
		}

		if (queryContact.getApplystatus() == 0) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "不能续签待审批的签约协议");
		}

		if (queryContact.getApplystatus() == 1) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "不能续签未结束或未到期的签约协议");
		}

		// 生成签约记录信息
		TblResidentContactRecord tblResidentContactRecord = new TblResidentContactRecord();
		tblResidentContactRecord.setApplydate(tblResidentContact.getUpdatedate());
		tblResidentContactRecord.setApplyidentity(tblResidentContact.getUpdateuser());
		tblResidentContactRecord.setAuthidentity(tblResidentContact.getUpdateuser());
		tblResidentContactRecord.setEnabledate(tblResidentContact.getUpdatedate());
		tblResidentContactRecord.setIsrenew(true);
		tblResidentContactRecord.setIsresidentapply(false);
		tblResidentContactRecord.setEnddate(Utility.getYearAddedOne(tblResidentContact.getUpdatedate()));
		tblResidentContactRecordService.insert(tblResidentContactRecord);

		// 临时数据替换:适配计划生成的创创建信息
		// queryContact.setOrgid(tblResidentContact.getOrgid());
		// queryContact.setCreateuser(tblResidentContact.getUpdateuser());
		// queryContact.setCreatedate(tblResidentContact.getUpdatedate());
		queryContact.setUpdatedate(tblResidentContact.getUpdatedate());
		queryContact.setUpdateuser(tblResidentContact.getUpdateuser());

		// 获取额签约后的服务
		List<TblResidentContactService> usedItems = getAfterRenewItems(queryContact, tblResidentContactRecord);

		TblServcieItem serviceItem = null;
		queryContact.setRecordidentity(tblResidentContactRecord.getId());

		for (TblResidentContactService item : usedItems) {
			serviceItem = tblServcieItemMapper.findByPrimaryKey(item.getServiceitemidentity());
			// 生成第一条计划
			initFirstServicePlan(queryContact, item, serviceItem, item.getCustomer(), item.getDoctors());
		}

		// 更新签约协议信息
		queryContact.setEnabledate(tblResidentContact.getUpdatedate());
		queryContact.setRecordidentity(tblResidentContactRecord.getId());
		queryContact.setApplystatus(Short.valueOf("1"));

		// 根据本次签约医疗团队外键查询同一医疗团队成员
		TblFamilyDoctorTeamMember teamMember = new TblFamilyDoctorTeamMember();
		teamMember.setMedicalteamidentity(queryContact.getTeamidentity());
		List<TblFamilyDoctorTeamMember> doctors = tblFamilyDoctorTeamMemberMapper.findByCondition(teamMember);
		TblFamilyDoctorTeam team = tblFamilyDoctorTeamService.findByPrimaryKey(queryContact.getTeamidentity());

		// 更新团队签约总数
		initTeamContactCount(team, queryContact);

		// 更新本次签约医生接受签约协议总数
		initAcceptDoctorContactCount(queryContact, loginName, doctors, team, false);

		return 1;
	}

	/**
	 * 获取签约后的服务项目(包含服务项对应服务医生和服务对象)
	 * 
	 * @param queryContact
	 * @param tblResidentContactRecord
	 * @return
	 */
	private List<TblResidentContactService> getAfterRenewItems(TblResidentContact queryContact,
			TblResidentContactRecord tblResidentContactRecord) {
		TblResidentContactService usedQueryItems = new TblResidentContactService();
		usedQueryItems.setRecordidentity(queryContact.getRecordidentity());
		// 续签之前的额签约服务
		List<TblResidentContactService> usedItems = tblResidentContactServiceService.findByCondition(usedQueryItems);

		if (null == usedItems || usedItems.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约协议数据异常:没有历史签约服务项目信息");
		}

		for (TblResidentContactService item : usedItems) {

			// 续签之前的服务对象
			TblServicesCustomer usedQueryCustomer = new TblServicesCustomer();
			usedQueryCustomer.setRecordidentity(queryContact.getRecordidentity());
			usedQueryCustomer.setServiceitemidentity(item.getId());

			List<TblServicesCustomer> usedCustomers = tblServicesCustomerService.findByCondition(usedQueryCustomer);
			if (null == usedCustomers || usedCustomers.isEmpty()) {
				throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
						String.format("签约协议数据异常:服务项%s没有历史签约服务对象信息", new Object[] { item.getServicename() }));
			}

			// 续签之前的服务医生
			TblResidentContactServiceDoctor usedQueryDoctor = new TblResidentContactServiceDoctor();
			usedQueryDoctor.setRecordidentity(queryContact.getRecordidentity());
			usedQueryDoctor.setServcieitemdetailidentity(item.getId());
			List<TblResidentContactServiceDoctor> usedDoctors = tblResidentContactServiceDoctorService
					.findByCondition(usedQueryDoctor);
			if (null == usedDoctors || usedDoctors.isEmpty()) {
				throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
						String.format("签约协议数据异常:服务项%s没有历史签约服务医生信息", new Object[] { item.getServicename() }));
			}

			item.setId(null);
			item.setIsenable(true);
			// 指定新的签约记录信息
			item.setRecordidentity(tblResidentContactRecord.getId());
			item.setApplystatus(Short.valueOf("1"));
			item.setCreateuser(queryContact.getUpdateuser());
			item.setCreatedate(queryContact.getUpdatedate());
			item.setUpdatedate(queryContact.getUpdatedate());
			item.setUpdateuser(queryContact.getUpdateuser());

			tblResidentContactServiceService.insert(item);

			initRenewServiceCustomer(queryContact, tblResidentContactRecord, item, usedCustomers);

			initRenewServiceDoctor(queryContact, tblResidentContactRecord, item, usedDoctors);
		}

		return usedItems;
	}

	/**
	 * 更新签约服务医生信息
	 * 
	 * @param queryContact
	 * @param tblResidentContactRecord
	 * @param item
	 * @param usedDoctors
	 */
	private void initRenewServiceDoctor(TblResidentContact queryContact,
			TblResidentContactRecord tblResidentContactRecord, TblResidentContactService item,
			List<TblResidentContactServiceDoctor> usedDoctors) {
		for (TblResidentContactServiceDoctor doctor : usedDoctors) {
			doctor.setId(null);
			doctor.setDeletesign(false);
			doctor.setOrgid(queryContact.getOrgid());
			doctor.setServcieitemdetailidentity(item.getId());
			doctor.setRecordidentity(tblResidentContactRecord.getId());
			doctor.setCreateuser(queryContact.getUpdateuser());
			doctor.setCreatedate(queryContact.getUpdatedate());
			doctor.setUpdatedate(queryContact.getUpdatedate());
			doctor.setUpdateuser(queryContact.getUpdateuser());
		}
		tblResidentContactServiceDoctorService.insertBatch(usedDoctors);
		item.setDoctors(usedDoctors);
	}

	/**
	 * 更新签约服务对象信息
	 * 
	 * @param queryContact
	 * @param tblResidentContactRecord
	 * @param item
	 * @param usedCustomers
	 */
	private void initRenewServiceCustomer(TblResidentContact queryContact,
			TblResidentContactRecord tblResidentContactRecord, TblResidentContactService item,
			List<TblServicesCustomer> usedCustomers) {
		for (TblServicesCustomer customer : usedCustomers) {
			customer.setId(null);
			customer.setDeletesign(false);
			customer.setOrgid(queryContact.getOrgid());
			customer.setServiceitemidentity(item.getId());
			customer.setRecordidentity(tblResidentContactRecord.getId());
			customer.setCreateuser(queryContact.getUpdateuser());
			customer.setCreatedate(queryContact.getUpdatedate());
			customer.setUpdatedate(queryContact.getUpdatedate());
			customer.setUpdateuser(queryContact.getUpdateuser());
		}
		tblServicesCustomerService.insertBatch(usedCustomers);
		item.setCustomer(usedCustomers);
	}

	@Override
	@SystemServiceLog(description = "签约服务预约")
	public int subscribe(TblResidentContactServicePlan plan) throws ServiceRunException {

		TblResidentContactService queryContactService = new TblResidentContactService();
		queryContactService.setId(plan.getServiceidentify());

		TblResidentContactService contactService = initServiceValid(queryContactService);

		TblResidentContact queryContact = initValidContact(contactService);

		// 本次预约服务
		// List<TblResidentContactService> usedItems = new
		// ArrayList<TblResidentContactService>();
		// usedItems.add(contactService);

		// 本次预约签约服务的服务对象
		TblServicesCustomer queryServicesCustomer = new TblServicesCustomer();
		queryServicesCustomer.setRecordidentity(contactService.getRecordidentity());
		queryServicesCustomer.setIdentitynumber(plan.getIdentitynumber());
		queryServicesCustomer.setServiceitemidentity(plan.getServiceidentify());

		List<TblServicesCustomer> usedCustomers = tblServicesCustomerService.findByCondition(queryServicesCustomer);

		if (null == usedCustomers || usedCustomers.isEmpty()) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
					String.format("本次预约服务对象%s不存在", new Object[] { queryServicesCustomer.getIdentitynumber() }));
		}

		if (usedCustomers.size() != 1) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(),
					String.format("本次签约服务的服务对象%s重复", new Object[] { queryServicesCustomer.getIdentitynumber() }));
		}

		TblServcieItem serviceItem = null;
		// 根据本次签约医疗团队外键查询同一医疗团队成员
		TblFamilyDoctorTeamMember teamMember = new TblFamilyDoctorTeamMember();
		teamMember.setMedicalteamidentity(queryContact.getTeamidentity());
		List<TblFamilyDoctorTeamMember> doctors = tblFamilyDoctorTeamMemberMapper.findByCondition(teamMember);

		// for (TblResidentContactService item : usedItems) {
		serviceItem = tblServcieItemMapper.findByPrimaryKey(contactService.getServiceitemidentity());

		// 预约服务预约时间
		contactService.setFirstservicedate(plan.getExpectservicedate());
		// 生成一条计划
		initFirstServicePlan(queryContact, contactService, serviceItem, usedCustomers,
				initNeedDoctors(queryContact, doctors, contactService));
		// }
		return 1;
	}

	private TblResidentContactService initServiceValid(TblResidentContactService tblResidentContactService) {
		TblResidentContactService contactService = tblResidentContactServiceService
				.findByPrimaryKey(tblResidentContactService.getId());

		if (null == contactService) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约服务不存在");
		}

		if (contactService.getApplystatus() == 0) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约服务未生效");
		}

		if (contactService.getApplystatus() == 2) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约服务已结束");
		}

		if (contactService.getApplystatus() == 3) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约服务已解除");
		}
		return contactService;
	}

	private TblResidentContact initValidContact(TblResidentContactService contactService) {
		TblResidentContact queryContact = tblResidentContactMapper
				.findByPrimaryKey(contactService.getContactidentity());
		if (null == queryContact) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约协议不存在");
		}

		if (queryContact.getApplystatus() == 0) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约协议未生效");
		}

		if (queryContact.getApplystatus() == 2) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约协议已结束");
		}

		if (queryContact.getApplystatus() == 3) {
			throw new ServiceRunException(RunExceptionEnum.REQ_NOT_PARA.getCode(), "签约协议已解除");
		}
		return queryContact;
	}
}
