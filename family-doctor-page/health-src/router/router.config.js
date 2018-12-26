/**
 * 路由配置表
 * @type {Array}
 */
// import frameRouters from '../framework/example/route.list.js'
const routers = [
  {
    path: '/login',
    component: resolve => require(['../index/login.vue'], resolve)
  },
  {
    path: '/home',
    component: resolve => require(['../index/index.vue'], resolve)
  },
  {
    path: '/platform',
    component: resolve => require(['../index/platform.vue'], resolve)
  },
  {
    path: '/',
    component: resolve => require(['../components/health/home.vue'], resolve),
    children: [
      {
        path: 'organization',
        name: 'organization',
        component: resolve => require(['../views/health/organization/index.vue'], resolve)
      },
      {
        path: 'organization_add',
        name: 'organization/add',
        component: resolve => require(['../views/health/organization/add.vue'], resolve)
      },
      {
        path: '/organization_edit',
        name: 'organization/edit',
        component: resolve => require(['../views/health/organization/edit.vue'], resolve)
      },
      {
        path: 'object',
        name: 'object',
        component: resolve => require(['../views/health/object/index.vue'], resolve)
      },
      {
        path: 'object_add',
        name: 'object/add',
        component: resolve => require(['../views/health/object/add.vue'], resolve)
      },
      {
        path: '/object_edit',
        name: 'object/edit',
        component: resolve => require(['../views/health/object/edit.vue'], resolve)
      },
      {
        path: 'person',
        name: 'person',
        component: resolve => require(['../views/health/person/index.vue'], resolve)
      },
      {
        path: 'person_add',
        name: 'person/add',
        component: resolve => require(['../views/health/person/add.vue'], resolve)
      },
      {
        path: 'person_edit',
        name: 'person/edit',
        component: resolve => require(['../views/health/person/edit.vue'], resolve)
      },
      {
        path: 'task',
        name: 'task',
        component: resolve => require(['../views/health/task/index.vue'], resolve)
      },
      {
        path: 'task_add',
        name: 'task/add',
        component: resolve => require(['../views/health/task/add.vue'], resolve)
      },
      {
        path: 'task_detail',
        name: 'task/detail',
        component: resolve => require(['../views/health/task/detail.vue'], resolve)
      },
      {
        path: 'dictionaries',
        name: 'dictionaries',
        component: resolve => require(['../views/health/dictionaries/index.vue'], resolve)
      },
      {
        path: 'roles',
        name: 'roles',
        component: resolve => require(['../views/health/roles/index.vue'], resolve)
      },
      {
        path: 'roles_add',
        name: 'roles/add',
        component: resolve => require(['../views/health/roles/add.vue'], resolve)
      },
      {
        path: 'users',
        name: 'users',
        component: resolve => require(['../views/health/users/index.vue'], resolve)
      },
      {
        path: 'users_add',
        name: 'users/add',
        component: resolve => require(['../views/health/users/add.vue'], resolve)
      },
      {
        path: 'users_edit',
        name: 'users/edit',
        component: resolve => require(['../views/health/users/edit.vue'], resolve)
      },
      {
        path: '/personal',
        name: 'personal/edit',
        component: resolve => require(['../views/health/personal/index.vue'], resolve)
      },
      {
        path: '/personal_editpwd',
        name: 'personal/editpwd',
        component: resolve => require(['../views/health/personal/editpwd.vue'], resolve)
      },
      {
        path: '/personal_edittel',
        name: 'personal/edittel',
        component: resolve => require(['../views/health/personal/edittel.vue'], resolve)
      }
    ]
  },
  {
    path: '/decision',
    component: resolve => require(['../components/decision/home.vue'], resolve),
    children: [
      {
        path: '/healthAssess',
        name: 'healthAssess',
        component: resolve => require(['../views/decision/healthAssess/index.vue'], resolve)
      },
      {
        path: '/plan',
        name: 'plan',
        component: resolve => require(['../views/decision/plan/index.vue'], resolve)
      },
      {
        path: '/survey',
        name: 'survey',
        component: resolve => require(['../views/decision/survey/index.vue'], resolve)
      },
      {
        path: '/survey_add',
        name: 'survey/add',
        component: resolve => require(['../views/decision/survey/add.vue'], resolve)
      },
      {
        path: '/survey_edit',
        name: 'survey/edit',
        component: resolve => require(['../views/decision/survey/edit.vue'], resolve)
      },
      {
        path: '/perAssess',
        name: 'perAssess',
        component: resolve => require(['../views/decision/perAssess/index.vue'], resolve)
      },
      {
        path: '/perAssess_add',
        name: 'perAssess/add',
        component: resolve => require(['../views/decision/perAssess/add.vue'], resolve)
      },
      {
        path: '/perAssess_detail',
        name: 'perAssess/detail',
        component: resolve => require(['../views/decision/perAssess/detail.vue'], resolve)
      }
    ]
  },
  {
    path: '/shortMessage',
    component: resolve => require(['../components/shortMessage/home.vue'], resolve),
    children: [
      {
        path: '/db',
        name: 'db',
        component: resolve => require(['../views/shortMessage/db/index.vue'], resolve)
      },
      {
        path: '/db_add',
        name: 'db/add',
        component: resolve => require(['../views/shortMessage/db/add.vue'], resolve)
      },
      {
        path: '/db_edit',
        name: 'db/edit',
        component: resolve => require(['../views/shortMessage/db/edit.vue'], resolve)
      },
      {
        path: '/gb',
        name: 'gb',
        component: resolve => require(['../views/shortMessage/gb/index.vue'], resolve)
      },
      {
        path: '/gb_add',
        name: 'gb/add',
        component: resolve => require(['../views/shortMessage/gb/add.vue'], resolve)
      },
      {
        path: '/gb_edit',
        name: 'gb/edit',
        component: resolve => require(['../views/shortMessage/gb/edit.vue'], resolve)
      },
      {
        path: '/ds',
        name: 'ds',
        component: resolve => require(['../views/shortMessage/ds/index.vue'], resolve)
      },
      {
        path: '/ds_add',
        name: 'ds/add',
        component: resolve => require(['../views/shortMessage/ds/add.vue'], resolve)
      },
      {
        path: 'ds_edit',
        name: 'ds/edit',
        component: resolve => require(['../views/shortMessage/ds/edit.vue'], resolve)
      },
      {
        path: '/sf',
        name: 'sf',
        component: resolve => require(['../views/shortMessage/sf/index.vue'], resolve)
      },
      {
        path: '/sf_add',
        name: 'sf/add',
        component: resolve => require(['../views/shortMessage/sf/add.vue'], resolve)
      },
      {
        path: 'sf_detail',
        name: 'sf/detail',
        component: resolve => require(['../views/shortMessage/sf/detail.vue'], resolve)
      },
      {
        path: '/mb',
        name: 'mb',
        component: resolve => require(['../views/shortMessage/mb/index.vue'], resolve)
      },
      {
        path: '/mb_add',
        name: 'mb/add',
        component: resolve => require(['../views/shortMessage/mb/add.vue'], resolve)
      },
      {
        path: '/mb_edit',
        name: 'mb/edit',
        component: resolve => require(['../views/shortMessage/mb/edit.vue'], resolve)
      },
      {
        path: '/dy',
        name: 'dy',
        component: resolve => require(['../views/shortMessage/dy/index.vue'], resolve)
      },
      {
        path: '/dy_add',
        name: 'dy/add',
        component: resolve => require(['../views/shortMessage/dy/add.vue'], resolve)
      },
      {
        path: '/dy_edit',
        name: 'dy/edit',
        component: resolve => require(['../views/shortMessage/dy/edit.vue'], resolve)
      }
    ]
  }
]
// routers.push(...frameRouters)
// console.info(routers)
export default routers
