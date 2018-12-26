export default [
  {
    path: '/example',
    component: resolve => require(['./index.vue'], resolve),
    children: [
      {
        path: 'grid',
        component: resolve => require(['./grid.vue'], resolve)
      },
      {
        path: 'spiner',
        component: resolve => require(['./spiner.vue'], resolve)
      },
      {
        path: 'excel',
        component: resolve => require(['./excel.vue'], resolve)
      },
      {
        path: 'badge',
        component: resolve => require(['./badge.vue'], resolve)
      },
      {
        path: 'schedule',
        component: resolve => require(['./schedule.vue'], resolve)
      },
      {
        path: '/example',
        redirect: '/example/badge'
      }
    ]
  }
]
