/**
 * 系统所有的配置项
 */

// 子系统后台服务地址baseURL(仅读取菜单用 部署权限子系统\SSO的后台服务IP)
// global.apiSSOBaseConfigURL = 'http://192.168.25.102'
global.apiSSOBaseConfigURL = 'http://192.168.25.143:8021'

// 子系统后台服务地址baseURL(业务调用 其他子系统更新自己的后台服务IP)
global.apiBaseConfigURL = 'http://192.168.25.143'

// 单点登录系统登录URL(前端登录服务IP)
global.loginHost = 'http://192.168.25.143/auth'
