
import {apiBaseConfig as axios} from './index'
let base = 'http://192.168.25.143:9527'

export const requestLogin = params => { return axios.post(`${base}/login`, params).then(res => res.data) }

export const getUserList = params => { return axios.get(`${base}/sign/list/${params.page}/${params.pageSize}`, { params: params }) }

export const getUserListPage = params => { return axios.get(`${base}/sign/list/${params.page}/${params.pageSize}`, { params: params }) }
export const removeContact = params => { return axios.post(`${base}/sign/remove`, { ids: params }) }

export const getContact = params => { return axios.get(`${base}/sign/view/${params.id}`, params) }

// 关系字典GET /code/findMemberShip
export const findMemberShip = params => { return axios.get(`${base}/code/findMemberShip`, params) }
// 添加签约协议 POST /contact/insert
export const addContact = params => { return axios.post(`${base}/contact/insert`, params) }
// 修改签约协议POST /contact/update{ return axios.post(`${base}/sign/update`, params) }
// 根据主键查询签约计划
export const findplanbykey = params => { return axios.post(`${base}/plan/findbykey`, params) }
// 分页查询居民签约服务执行计划
export const getPlanListPage = params => { return axios.post(`${base}/plan/findpage`, params) }
// 查询全部居民签约服务执行计划
export const getAllPlanPage = params => { return axios.post(`${base}/plan/findpage`, params) }
// 分页查询签约协议
export const getContactFindByCon = params => { return axios.post(`${base}/contact/findbycon`, params) }
// 条件查询签约协议
export const getContactPage = params => { return axios.post(`${base}/contact/findpage`, params) }
// 修改居民签约服务执行计划
export const updatePlanPage = params => { return axios.post(`${base}/plan/update`, params) }
// 查询医生
export const doctorPage = params => { return axios.post(`${base}/service/doctor/findbycon`, params) }
// 更新居民签约服务项目医生信息数据
export const doctorUpadtePage = params => { return axios.post(`${base}/service/doctor/update`, params) }
// 更新居民签约服务执行计划数据
export const upadtePlan = params => { return axios.post(`${base}/plan/update`, params) }
// 根据签约协议ID查询信息
export const findContactByKey = params => { return axios.post(`${base}/contact/findbykey`, params) }
// 根据ID修改签约协议
export const updateContactByKey = params => { return axios.post(`${base}/contact/update`, params) }
// 新增签约家庭医生团队信息
export const insertTeam = params => { return axios.post(`${base}/team/insert`, params) }
// 分页查询签约家庭医生团队信息
export const getTeamsPage = params => { return axios.post(`${base}/team/findpage`, params) }
// 条件查询签约家庭医生团队信息
export const getTeamsByCon = params => { return axios.post(`${base}/team/findbycon`, params) }
// 主键查询签约家庭医生团队信息
export const findTeamsByKey = params => { return axios.post(`${base}/team/findbykey`, params) }
// 删除签约家庭医生团队信息数据
export const delTeams = params => { return axios.post(`${base}/team/delete`, params) }
// 删除签约家庭医生团队服务信息
export const delItem = params => { return axios.post(`${base}/team/deleteItem`, params) }
// 更新签约家庭医生团队信息数据
export const updateTeams = params => { return axios.post(`${base}/team/update`, params) }
// 新增签约家庭医生服务项目
export const insertTeamItem = params => { return axios.post(`${base}/team/insertItem`, params) }
// 新增签约家庭医生团队成员信息
export const insertTeamsMember = params => { return axios.post(`${base}/member/insert`, params) }
// 条件查询签约家庭医生团队成员信息
export const getTeamsMember = params => { return axios.post(`${base}/member/findbycon`, params) }
// 分页查询签约家庭医生团队成员信息
export const getTeamsMemberPage = params => { return axios.post(`${base}/member/findpage`, params) }
// 更新签约家庭医生团队成员信息数据
export const updateTeamsMember = params => { return axios.post(`${base}/member/update`, params) }
// 删除签约家庭医生团队成员信息数据
export const delTeamsMember = params => { return axios.post(`${base}/member/delete`, params) }
// 根据身份证号查询个人信息
export const getContentByIdentitynumber = params => { return axios.post(`${base}/archives/findbycon`, params) }
// 保存个人健康档案
export const addArchives = params => { return axios.post(`${base}/archives/insert`, params) }
// 根据主键查询个人健康档案 POST /archives/findbykey
export const findarchivesbykey = params => { return axios.post(`${base}/archives/findbykey`, params) }
// 新增签约医疗机构医生信息
export const insertDoctor = params => { return axios.post(`${base}/doctor/insert`, params) }
// 更新签约医疗机构医生信息数据
export const updateDoctor = params => { return axios.post(`${base}/doctor/update`, params) }
// 分页查询签约医疗机构医生信息
export const getDocotrPage = params => { return axios.post(`${base}/doctor/findpage`, params) }
// 主键查询签约医疗机构医生信息
export const findDocotrByKey = params => { return axios.post(`${base}/doctor/findbykey`, params) }
// 删除签约医疗机构医生信息数据
export const delDocotr = params => { return axios.post(`${base}/doctor/delete`, params) }
// 根据条件查询全部签约医疗机构医生信息
export const getDocotrByTeam = params => { return axios.post(`${base}/doctor/findbycon`, params) }
// 分页查询签约医疗机构
export const getInstitutionPage = params => { return axios.post(`${base}/institution/findpage`, params) }
// 根据签约医疗机构ID查询信息
export const findInstitutionContactByKey = params => { return axios.post(`${base}/institution/findbykey`, params) }
// 根据ID修改签约医疗机构
export const updateInstitutionContactByKey = params => { return axios.post(`${base}/institution/update`, params) }
// 删除签约医疗机构
export const delInstitution = params => { return axios.post(`${base}/institution/delete`, params) }
// 新增签约医疗机构
export const insertInstitution = params => { return axios.post(`${base}/institution/insert`, params) }
// 字典所有医生级别
export const getDoctorLevel = params => { return axios.get(`${base}/code/findDoctorLevel`) }
// 字典里区域信息
export const getAreaAll = params => { return axios.post(`${base}/code/findAreasByCon`, params) }
// 分页查询医疗科室汇总信息
export const getOfficesPage = params => { return axios.post(`${base}/offices/findpage`, params) }
// 更新医疗科室信息
export const updateOffices = params => { return axios.post(`${base}/offices/update`, params) }
// 新增医疗科室信息
export const insertOffices = params => { return axios.post(`${base}/offices/insert`, params) }
// ID查询医疗科室信息
export const getContactOfficesById = params => { return axios.post(`${base}/offices/findbykey`, params) }
// 删除医疗科室信息
export const delOffices = params => { return axios.post(`${base}/offices/delete`, params) }
// 查询全部医疗团队角色信息
export const getAllRoles = params => { return axios.get(`${base}/roles/findall`) }
// 分页查询全部医疗团队角色信息
export const getAllRolesPage = params => { return axios.post(`${base}/roles/findpage`, params) }
// 删除医疗团队角色信息
export const delRoles = params => { return axios.post(`${base}/roles/delete`, params) }
// 更新医疗团队角色信息
export const updateRole = params => { return axios.post(`${base}/roles/update`, params) }
// 新增医疗团队角色信息
export const insertRole = params => { return axios.post(`${base}/roles/insert`, params) }
// ID 查询医疗团队角色信息
export const getRolesByKey = params => { return axios.post(`${base}/roles/findbykey`, params) }
// 查询权限系统当前登录用户所属机构
export const getInstitutionByUser = params => { return axios.get(`${base}/code/findOriginCurrentOrg`) }
// 查询全部科室信息
export const getOffice = params => { return axios.get(`${base}/offices/findall`) }
// 根据团队ID查询团队服务信息
export const getFuWuByTeamsKey = params => { return axios.post(`${base}/team/findByTeamsKey`, params) }
// 分页查询居民家庭信息
export const getFamilysPage = params => { return axios.post(`${base}/familys/findpage`, params) }
// 新增居民家庭信息
export const insertFamilys = params => { return axios.post(`${base}/familys/insert`, params) }
// 主键查询居民家庭信息
export const findFamilyByKey = params => { return axios.post(`${base}/familys/findbykey`, params) }
// 更新居民家庭信息数据
export const updateFamily = params => { return axios.post(`${base}/familys/update`, params) }
// 新增居民家庭成员信息
export const insertRelative = params => { return axios.post(`${base}/relative/insert`, params) }
// 根据身份证号码查询居民全部家庭成员信息
export const findMemeberByCon = params => { return axios.post(`${base}/relative/findMemeberByCon`, params) }
// 签约协议中新增家庭成员的提交按钮
export const updateWithInsertMember = params => { return axios.post(`${base}/familys/updateWithInsertMember`, params) }
// 新增签约协议中新增家庭成员查询个人档案
export const findAllMemberWithFamilyByCondition = params => { return axios.post(`${base}/archives/findAllMemberWithFamilyByCondition`, params) }
// 根据身份证号码查询居民健康档案且查询是否已经签订协议POST /archives/findMemberWithContactByCondition
export const findMemberWithContactByCondition = params => { return axios.post(`${base}/archives/findMemberWithContactByCondition`, params) }
// 条件查询居民个人健康档案(查询户主)
export const findMasterWithContactByCon = params => { return axios.post(`${base}/archives/findbycon`, params) }
// 根据身份证号码查询居民全部家庭成员信息且不存在则新增家庭及成员信息
export const findMemeberWithInsertByCon = params => { return axios.post(`${base}/relative/findMemeberWithInsertByCon`, params) }
// 分页查询问诊信息
export const findInquirytPage = params => { return axios.post(`${base}/inquiry/findpage`, params) }
// 主键查询问诊详情
export const findInquirytByKey = params => { return axios.post(`${base}/inquiry/findbykey`, params) }
// 新增问诊详情回复信息
export const insertNotice = params => { return axios.post(`${base}/inquiry/insertInquiryReply`, params) }
// 查询服务类型字典信息
export const findServiceType = params => { return axios.get(`${base}/code/findServiceType`, params) }
// 查询服务计划周期信息
export const findServicePeriod = params => { return axios.get(`${base}/code/findServicePeriod`, params) }
// 查询费用支付频率信息
export const findPaymentFrequency = params => { return axios.get(`${base}/code/findPaymentFrequency`, params) }
// 查询医疗设备信息
export const findEquipment = params => { return axios.get(`${base}/code/findEquipment`, params) }
// 查询医生职级类型字典信息
export const findDoctorLevel = params => { return axios.get(`${base}/code/findDoctorLevel`, params) }
// 查询证件类型字典信息
export const findCertificateType = params => { return axios.get(`${base}/code/findCertificateType`, params) }
// 分页查询签约服务项目
export const findItemPage = params => { return axios.post(`${base}/items/findpage`, params) }
// 主键查询签约服务项目
export const findItemByKey = params => { return axios.post(`${base}/items/findbykey`, params) }
// 删除签约服务项目数据
export const deleteItem = params => { return axios.post(`${base}/items/delete`, params) }
// 分页查询通知公告
export const findNoticetPage = params => { return axios.post(`${base}/notice/findpage`, params) }
// ID查询通知公告
export const findNoticetById = params => { return axios.post(`${base}/notice/findbykey`, params) }
// 新增通知公告
export const insertNotices = params => { return axios.post(`${base}/notice/insert`, params) }
// 更新通知公告
export const updateNotices = params => { return axios.post(`${base}/notice/update`, params) }
// 撤回通知公告
export const revokeNotices = params => { return axios.post(`${base}/notice/revoke`, params) }
// 再次发布通知公告
export const sendNotices = params => { return axios.post(`${base}/notice/release`, params) }
// 条件查询签约服务项目
export const findItemByCon = params => { return axios.post(`${base}/items/findbycon`, params) }
// 新增签约服务项目
export const insertItem = params => { return axios.post(`${base}/items/insert`, params) }
// 更新签约服务项目数据
export const updateItem = params => { return axios.post(`${base}/items/update`, params) }
// 根据身份证号查询预约服务信息
export const findAppointmentByCon = params => { return axios.post(`${base}/contact/findAppointmentByCon`, params) }
// 分页查询投诉建议
export const findSuggestionPage = params => { return axios.post(`${base}/feedback/findpage`, params) }
// ID查询投诉建议
export const findSuggestionById = params => { return axios.post(`${base}/feedback/findbykey`, params) }
// 更新投诉建议
export const updateSuggestion = params => { return axios.post(`${base}/feedback/update`, params) }
// 签约协议解除POST /contact/relieve
export const relieve = params => { return axios.post(`${base}/contact/relieve/${params.id}`) }
// 审批居民签约协议数据(更改协议和服务状态并且生成服务计划
export const approval = params => { return axios.post(`${base}/contact/approval/${params.id}`) }
// 续签居民签约协议
export const renew = params => { return axios.post(`${base}/contact/renew/${params.id}`) }
// 转诊对照关系分页查询
export const findcontrastpage = params => { return axios.post(`${base}/contrast/findpage`, params) }
// 转诊记录分页
export const findtransferpage = params => { return axios.post(`${base}/transfer/findpage`, params) }
// 通过ID修改转诊记录信息
export const updatatransferbykey = params => { return axios.post(`${base}/transfer/update`, params) }
// 录入结论
export const executeinsert = params => { return axios.post(`${base}/plan/execute`, params) }
// 根据主键查询查询服务记录评价
export const findevaluatebykey = params => { return axios.post(`${base}/evaluate/findbykey`, params) }
// 条件查询服务记录评价
export const findevaluatebycon = params => { return axios.post(`${base}/evaluate/findbycon`, params) }
// 主键查询计划转诊记录
export const findtransferbykey = params => { return axios.post(`${base}/transfer/findbykey`, params) }
// 转诊记录新增
export const inserttransfer = params => { return axios.post(`${base}/transfer/insert`, params) }
// 添加转诊对照关系表
export const insertcontrast = params => { return axios.post(`${base}/contrast/insert`, params) }
// 条件查询服务执行计划
export const findplanbycon = params => { return axios.post(`${base}/plan/findbycon`, params) }
// 条件查询医生服务记录
export const findtreatmentbycon = params => { return axios.post(`${base}/treatment/findbycon`, params) }
// 查询所有的医疗机构
export const findinstitutionall = params => { return axios.get(`${base}/institution/findall`, params) }
// 主键查询家庭医生服务记录
export const findtreatmentbykey = params => { return axios.post(`${base}/treatment/findbykey`, params) }
// 系统消息提醒分页查询
export const findremindpage = params => { return axios.post(`${base}/remind/findpage`, params) }
// 系统消息提醒所有消息类别查询
export const findremindAll = params => { return axios.get(`${base}/msgcategory/findall`) }
// 分页查询签约居民信息
export const findContactResidentPage = params => { return axios.post(`${base}/contact/findContactResidentPage`, params) }
//  查询当前机构转诊服务对照关系信息
export const findCruuentOrgContrast = params => { return axios.get(`${base}/contrast/findCurrentOrgContrast`) }
// 查询全部系统消息提醒分类
export const findallMsgcategory = params => { return axios.get(`${base}/msgcategory/findall`, params) }
// 查询全部消息提醒设置
export const findallMsgconfig = params => { return axios.get(`${base}/msgconfig/findall`, params) }
// 保存(更新或者新增)消息提醒设置
export const saveMsgcategory = params => { return axios.post(`${base}/msgconfig/save`, params) }
// 主键查询对照关系表
export const findcontrastbykey = params => { return axios.post(`${base}/contrast/findbykey`, params) }
// 根据主键修改关系表
export const updatecontrast = params => { return axios.post(`${base}/contrast/update`, params) }
// 删除对照关系表数据
export const deletecontrast = params => { return axios.post(`${base}/contrast/delete`, params) }
// 查询当前登录用户所属机构
export const findCurrentOrg = params => { return axios.get(`${base}/code/findCurrentOrg`, params) }
// 修改密码
export const updatePwd = params => { return axios.post(`${base}/system/update/pwd`, params) }
// 已登录用户退出
export const logout = params => { return axios.post(`${base}/system/logout`, params) }
// 签约服务预约
export const subscribecontact = params => { return axios.post(`${base}/contact/subscribe`, params) }
// 可更换的医生
export const findOptionalDoctor = params => { return axios.post(`${base}/plan/findOptionalDoctor`, params) }
// 上传头像
export const pictureUpload = params => { return axios.post(`${base}/pictureUpload/upload`, params) }
