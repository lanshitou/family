import home from './views/home.vue'
import contactTable from './views/contact/table.vue'
import contactIndex from './views/contact/index.vue'

const routers = [{
    path: '/',
    meta: {
        title: '首页'
    },
    children: [{
        path: '/',
        meta: {
            title: '签约协议-首页'
        },
        component: contactIndex,
        children: [
            {
                path: '/',
                meta: {
                    title: '签约协议-表格'
                },
                component: contactTable
            }
        ]
    },{
        path: '/contact',
        meta: {
            title: '签约协议'
        },
        component: contactIndex,
        children: [
            {
                path: '/',
                meta: {
                    title: '签约协议-表格'
                },
                component: contactTable
            }
        ]
    },{
        path: '/contact/add',
        meta: {
            title: '签约协议添加'
        },
        component: (resolve) => require(['./views/contact/add.vue'], resolve)
    },{
        path: '/contact/edit/:id',
        name: '/contact/edit',
        meta: {
            title: '签约协议编辑'
        },
        component: (resolve) => require(['./views/contact/add.vue'], resolve)
    }],
    component: home
}];
export default routers;