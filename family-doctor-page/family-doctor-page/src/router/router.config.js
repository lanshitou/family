/**
 * 路由配置表
 * @type {Array}
 */
// import frameRouters from '../framework/example/route.list.js'
const routers = [
  {
    path: '/',
    component: resolve => require(['../pages/home.vue'], resolve),
    children: [
      {
        path: 'register',
        component: resolve => require(['../views/login/registers.vue'], resolve)
      },
      {
        path: 'password',
        component: resolve => require(['../views/login/password.vue'], resolve)
      },
      {
        path: 'home',
        component: resolve => require(['../framework/example/grid.vue'], resolve)
      },
      {
        path: 'uerassigntable',
        component: resolve => require(['../framework/example/excel.vue'], resolve)
      },
      {
        path: 'sysregitable',
        component: resolve => require(['../framework/example/spiner.vue'], resolve)
      },
      {
        path: 'authmanagertable',
        component: resolve => require(['../framework/example/badge.vue'], resolve)
      },
      {
        path: 'inedx',
        component: resolve => require(['../views/index/index.vue'], resolve)
      },
      {
        path: 'moreNotice',
        component: resolve => require(['../views/index/moreNotice.vue'], resolve)
      },
      {
        path: 'plans',
        component: resolve => require(['../views/service/plans/plans.vue'], resolve)
      },
      {
        path: 'conclusionDetails',
        name: 'conclusion/details',
        component: resolve => require(['../views/service/plans/conclusionDetails.vue'], resolve)
      },
      {
        path: 'evaluate',
        name: 'evaluate',
        component: resolve => require(['../views/service/plans/evaluate.vue'], resolve)
      },
      {
        path: 'appointment',
        name: 'appointment',
        component: resolve => require(['../views/service/appointment/appointment.vue'], resolve)
      },
      {
        path: 'appointment_plan',
        name: 'appointment/plan',
        component: resolve => require(['../views/contact/appointment_plan.vue'], resolve)
      },
      {
        path: 'appointment_sign_plans',
        component: resolve => require(['../views/service/appointment/sign_plans.vue'], resolve)
      },
      {
        path: 'appointment_medical_org',
        component: resolve => require(['../views/service/appointment/medical_org.vue'], resolve)
      },
      {
        path: 'appointment_medical_team',
        component: resolve => require(['../views/service/appointment/medical_team.vue'], resolve)
      },
      {
        path: 'referral',
        component: resolve => require(['../views/service/referral/index.vue'], resolve)
      },
      {
        path: 'institution',
        component: resolve => require(['../views/institution/index.vue'], resolve)
      },
      {
        path: 'institution_add',
        component: resolve => require(['../views/institution/add.vue'], resolve)
      },
      {
        path: 'resident',
        component: resolve => require(['../views/institution/resident/resident.vue'], resolve)
      },
      {
        path: 'health',
        component: resolve => require(['../views/institution/resident/health.vue'], resolve)
      },
      {
        path: 'doctors',
        component: resolve => require(['../views/doctor/index.vue'], resolve)
      },
      {
        path: 'doctors_add',
        component: resolve => require(['../views/doctor/add.vue'], resolve)
      },
      {
        path: 'doctors_check:id',
        name: 'doctors/check',
        component: resolve => require(['../views/doctor/check.vue'], resolve)
      },
      {
        path: 'doctors_edit:id',
        name: 'doctors/edit',
        component: resolve => require(['../views/doctor/edit.vue'], resolve)
      },
      {
        path: 'institution_edit',
        name: 'institution/institution_edit',
        component: resolve => require(['../views/institution/edit.vue'], resolve)
      },
      {
        path: 'institution_details',
        name: 'institution/institution_details',
        component: resolve => require(['../views/institution/details.vue'], resolve)
      },
      {
        path: 'teams',
        component: resolve => require(['../views/team/index.vue'], resolve)
      },
      {
        path: 'teams_add',
        component: resolve => require(['../views/team/add.vue'], resolve)
      },
      {
        path: 'teams_edit:id',
        name: 'teams/edit',
        component: resolve => require(['../views/team/edit.vue'], resolve)
      },
      {
        path: 'teams_service:id',
        name: 'teams/service',
        component: resolve => require(['../views/team/team_service.vue'], resolve)
      },
      {
        path: 'referral_add',
        name: 'referral/add',
        component: resolve => require(['../views/service/referral/referral_add.vue'], resolve)
      },
      {
        path: 'referral_nexus',
        component: resolve => require(['../views/service/referral/referral_nexus.vue'], resolve)
      },
      {
        path: 'referral_nexus_add',
        name: 'referral/nexus/add',
        component: resolve => require(['../views/service/referral/referral_nexus_add.vue'], resolve)
      },
      {
        path: 'department',
        component: resolve => require(['../views/service/department/index.vue'], resolve)
      },
      {
        path: 'department_add',
        component: resolve => require(['../views/service/department/department_add.vue'], resolve)
      },
      {
        path: 'department_edit',
        name: 'department/department_edit',
        component: resolve => require(['../views/service/department/department_edit.vue'], resolve)
      },
      {
        path: 'inputConclusion',
        name: 'inputConclusion',
        component: resolve => require(['../views/service/plans/input_conclusion.vue'], resolve)
      },
      {
        path: 'contact',
        component: resolve => require(['../views/contact/index.vue'], resolve)
      },
      {
        path: 'add',
        name: 'contact/add',
        component: resolve => require(['../views/contact/add.vue'], resolve)
      },
      {
        path: 'edit',
        name: 'contact/edit',
        component: resolve => require(['../views/contact/edit.vue'], resolve)
      },
      {
        path: 'familys',
        component: resolve => require(['../views/service/familys/index.vue'], resolve)
      },
      {
        path: 'familys_add',
        name: 'familys/add',
        component: resolve => require(['../views/service/familys/add.vue'], resolve)
      },
      {
        path: 'familys_check',
        name: 'familys/check',
        component: resolve => require(['../views/service/familys/check.vue'], resolve)
      },
      {
        path: 'familys_edit:id',
        name: 'familys/edit',
        component: resolve => require(['../views/service/familys/edit.vue'], resolve)
      },
      {
        path: 'family_add',
        name: 'family/add',
        component: resolve => require(['../views/contact/family_add.vue'], resolve)
      },
      {
        path: 'detail',
        name: 'contact/detail',
        component: resolve => require(['../views/contact/detail.vue'], resolve)
      },
      {
        path: 'personal_record',
        name: 'personal/record',
        component: resolve => require(['../views/contact/personal_record.vue'], resolve)
      },
      {
        path: 'exam',
        name: 'contact/exam',
        component: resolve => require(['../views/contact/exam.vue'], resolve)
      },
      {
        path: 'health_record',
        name: 'health/record',
        component: resolve => require(['../views/contact/health_record.vue'], resolve)
      },
      {
        path: 'items',
        component: resolve => require(['../views/systemSettings/items/index.vue'], resolve)
      },
      {
        path: 'items_add:id',
        name: 'items/add',
        component: resolve => require(['../views/systemSettings/items/add.vue'], resolve)
      },
      {
        path: 'items_edit:id',
        name: 'items/edit',
        component: resolve => require(['../views/systemSettings/items/edit.vue'], resolve)
      },
      {
        path: 'items_detail:id',
        name: 'items/detail',
        component: resolve => require(['../views/systemSettings/items/detail.vue'], resolve)
      },
      {
        path: 'over',
        name: 'contact/over',
        component: resolve => require(['../views/contact/over.vue'], resolve)
      },
      {
        path: 'roles',
        component: resolve => require(['../views/systemSettings/roles/roles.vue'], resolve)
      },
      {
        path: 'role_edit',
        name: 'roles/roles_edit',
        component: resolve => require(['../views/systemSettings/roles/role_edit.vue'], resolve)
      },
      {
        path: 'role_add',
        component: resolve => require(['../views/systemSettings/roles/role_add.vue'], resolve)
      },
      {
        path: 'inquiry',
        component: resolve => require(['../views/inquiry/inquiry.vue'], resolve)
      },
      {
        path: 'inquiry_detail',
        name: 'inquiry/inquiry_detail',
        component: resolve => require(['../views/inquiry/inquiry_detail.vue'], resolve)
      },
      {
        path: 'suggestion',
        component: resolve => require(['../views/suggestion/suggestion.vue'], resolve)
      },
      {
        path: 'suggestion_detail',
        name: 'suggestion/suggestion_detail',
        component: resolve => require(['../views/suggestion/suggestion_detail.vue'], resolve)
      },
      {
        path: 'suggestion_reply',
        name: 'suggestion/suggestion_reply',
        component: resolve => require(['../views/suggestion/suggestion_reply.vue'], resolve)
      },
      {
        path: 'message',
        component: resolve => require(['../views/systemSettings/message/index.vue'], resolve)
      },
      {
        path: 'plan_change',
        name: 'message/plan_change',
        component: resolve => require(['../views/systemSettings/message/plan_change.vue'], resolve)
      },
      {
        path: 'personal',
        component: resolve => require(['../views/systemSettings/personal/index.vue'], resolve)
      },
      {
        path: 'personal_safe',
        component: resolve => require(['../views/systemSettings/personal/safe.vue'], resolve)
      },
      {
        path: 'personal_remind',
        component: resolve => require(['../views/systemSettings/personal/remind.vue'], resolve)
      },
      {
        path: 'notice',
        component: resolve => require(['../views/systemSettings/notice/notice.vue'], resolve)
      },
      {
        path: 'notice_add',
        component: resolve => require(['../views/systemSettings/notice/notice_add.vue'], resolve)
      },
      {
        path: 'notice_detail',
        name: 'notice/notice_detail',
        component: resolve => require(['../views/systemSettings/notice/notice_detail.vue'], resolve)
      },
      {
        path: 'notice_edit',
        name: 'notice/notice_edit',
        component: resolve => require(['../views/systemSettings/notice/notice_edit.vue'], resolve)
      },
      {
        path: 'mechanism',
        component: resolve => require(['../views/supervise/mechanism.vue'], resolve)
      },
      {
        path: 'teamsuper',
        component: resolve => require(['../views/supervise/teamsuper.vue'], resolve)
      },
      {
        path: 'notice_send',
        name: 'notice/notice_send',
        component: resolve => require(['../views/systemSettings/notice/notice_send.vue'], resolve)
      }]
  },
  {
    path: '*',
    name: 'Page404',
    component: resolve => require(['../pages/page404.vue'], resolve)
  },
  {
    path: '/login',
    component: resolve => require(['../views/home/login.vue'], resolve)
  }
]
// routers.push(...frameRouters)
// console.info(routers)
export default routers
