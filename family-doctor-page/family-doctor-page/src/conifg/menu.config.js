// import api from '../../api'
/**
 * [vuelists 中pageconfig字段为页面配置]
 * [vuelists 中local字段为是否本地配置, true为本地配置,false为远程请求]
 * @type {Object}
 * 下面是两种示例
 */

// 这是本地配置栗子
// var vuelists = {
//   local: false,
//   pageconfig: [
//     {
//       'only': false,
//       'name': 'home',
//       'title': '首页',
//       'iconCls': 'fa fa-home',
//       'menuurl': '/home'
//     },
//     {
//       'name': '540d33da0e1511e7858b60a44ce84a90',
//       'title': '组件',
//       'iconCls': 'fa-hospital-o',
//       'only': false,
//       'menuurl': '/uerassigntable'
//     },
//     {
//       'name': '54c12bcb0e1511e7858b60a44ce84a90',
//       'title': '菜单大A',
//       'iconCls': 'fa-users',
//       'only': true,
//       'children': [
//         {
//           'name': '54d5e8740e1511e7858b60a44ce84a90',
//           'title': '菜单A子级1',
//           'iconCls': 'fa-user-circle-o',
//           'only': false,
//           'menuurl': '/sysregitable'
//         },
//         {
//           'name': '54e725150e1511e7858b60a44ce84a90',
//           'title': '菜单A子级2',
//           'iconCls': 'fa-user-o',
//           'only': false,
//           'menuurl': '/authmanagertable'
//         }
//       ]
//     },
//     {
//       'name': '540d33da0e1511e7858b60a44ce84a90',
//       'title': '签约协议',
//       'iconCls': 'fa-users',
//       'only': false,
//       'menuurl': '/contact'
//     }
//   ]
// }

// 此处正式开发环境不需要
// import { setStorage } from '../framework/common/storage'
// setStorage('session', 'user', {
//   id: '859D20A91F1947FA883A9E2AAA5C6A5C',
//   usertoken: 'ADDBAB2181F444FA8DC691544E2EBD86',
//   appNo: 'ff8d8aaf0d5211e7858b60a44ce84a90'
// })

// 这是远程请求栗子 //todo 远程index为必配项
var vuelists = {
  local: true,
  pageconfig: {
    // index: index
  }
}
export default vuelists
