<template>
    <div>
        <div class="step-block">
            <p>当前正在进行第 {{ current + 1 }} 步</p>
            <Steps :current="current">
                <Step title="签约居民信息"></Step>
                <Step title="签约服务明细"></Step>
                <Step title="签约确认生效"></Step>
            </Steps>
        </div>
        <template v-if="current === 0">
            <Form ref="formModel" class="form-block" :model="formModel" :rules="ruleValidate" :label-width="100">
                <br>
                <Row>
                    <Col span="12">
                    <Row>
                        <Col span="11">
                        <Form-item label="申请人姓名" prop="applyName">
                            <Input v-model="formModel.applyName" placeholder="请输入姓名"></Input>
                        </Form-item>
                        </Col>
                        <Col span="11">
                        <Form-item label="性别" prop="sex">
                            <Radio-group v-model="formModel.sex">
                                <Radio label="1">男</Radio>
                                <Radio label="0">女</Radio>
                            </Radio-group>
                        </Form-item>
                        </Col>
                    </Row>
                    </Col>
                    <Col span="12">
                    <Form-item label="身份证号码" prop="identCard">
                        <Input v-model="formModel.identCard" placeholder="请输入身份证号码"></Input>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Row>
                        <Col span="11">
                        <Form-item label="出生日期" prop="birthDate">
                            <Date-picker type="date" placeholder="选择出生日期" v-model="formModel.birthDate"></Date-picker>
                        </Form-item>
                        </Col>
    
                        <Col span="11">
                        <Form-item label="年龄" prop="age">
                            <Input-number placeholder="请输入年龄" v-model="formModel.age"></Input-number>
                        </Form-item>
                        </Col>
                    </Row>
                    </Col>
                    <Col span="12">
                    <Form-item label="联系电话" prop="telephone">
                        <Input v-model="formModel.telephone" placeholder="请输入联系电话"></Input>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Form-item label="家庭地址" prop="address">
                        <Input v-model="formModel.address" type="textarea" :autosize="{minRows: 2,maxRows: 5}" placeholder="请输入家庭地址"></Input>
                    </Form-item>
                </Row>
                <Row>
                    <Form-item v-for="(item, index) in formModel.members.items" :key="item" :label="'家庭成员' + (index + 1)" :rules="{required: true, message: '姓名' + (index + 1) +'不能为空', trigger: 'blur'}">
                        <Row>
                            <Col span="3">
                            <Input v-model="item.memberName" placeholder="请输入姓名"></Input>
                            </Col>
                            <Col span="3">
                            <Input-number :max="100" :min="1" v-model="item.age" placeholder="请输入年龄"></Input-number>
                            </Col>
                            <Col span="3">
                            <Radio-group v-model="item.sex">
                                <Radio label="1">男</Radio>
                                <Radio label="0">女</Radio>
                            </Radio-group>
                            </Col>
                            <Col span="3">
                            <Input v-model="item.relation" placeholder="请输入成员关系"></Input>
                            </Col>
                            <Col span="3">
                            <Input v-model="item.indentNumber" placeholder="请输入健康档案号"></Input>
                            </Col>
                            <Col span="4" offset="1">
                            <Button type="ghost" @click="handleRemove(index)">删除</Button>
                            </Col>
                        </Row>
                    </Form-item>
                    <Form-item>
                        <Row>
                            <Col span="12">
                            <Button type="dashed" long @click="handleAdd" icon="plus-round">新增家庭成员</Button>
                            </Col>
                        </Row>
                    </Form-item>
                </Row>
                <Row>
                    <Form-item>
                        <i-button type="primary" @click="next">下一步</i-button>
    
                        <Button type="ghost" @click="handleReset('formModel')" style="margin-left: 8px">重置</Button>
                    </Form-item>
                </Row>
            </Form>
        </template>
        <template v-else-if="current === 1">
            <Form ref="servicesModel" class="form-block" :model="servicesModel" :rules="servicesValidate" :label-width="100">
                <br>
                <Row>
                    <Col span="12">
                    <Form-item label="医疗机构" prop="applyInstitutions">
                        <Input placeholder="请输入医疗机构" v-model="servicesModel.applyInstitutions"></Input>
                    </Form-item>
                    </Col>
                    <Col span="12">
                    <Form-item label="医生团队" prop="applyDoctorTeam">
                        <Input v-model="servicesModel.applyDoctorTeam" placeholder="请输入医生团队"></Input>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                    <Form-item label="签约类型">
                        <Radio-group v-model="servicesModel.applySignType">
                            <Radio label="1">家庭</Radio>
                            <Radio label="0">个人</Radio>
                        </Radio-group>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Col>
                    <Form-item label="签约服务">
                        <Table border :columns="serviceColumn" :data="serviceItems"></Table>
                    </Form-item>
                    </Col>
                </Row>
                <Row>
                    <Form-item>
                        <i-button type="primary" @click="last">上一步</i-button>
                        <Button type="primary" @click="handleServices('servicesModel')">提交</Button>
                    </Form-item>
                </Row>
            </Form>
        </template>
    </div>
</template>
<script>
import {
    addContact
} from '../../api/api';
export default {
    data() {
        return {
            serviceColumn: [
                {
                    type: 'selection',
                    width: 60,
                    align: 'center'
                },
                {
                    title: '签约服务类别',
                    key: 'type'
                },
                {
                    title: '签约服务项目',
                    key: 'item'
                },
                {
                    title: '服务计划',
                    key: 'plan'
                },
                {
                    title: '所需医疗人员',
                    key: 'doctors'
                },
                {
                    title: '医疗设备',
                    key: 'advice'
                },
                {
                    title: '预计收费',
                    key: 'fee'
                },
                {
                    title: '服务对象',
                    key: 'target'
                }
            ],
            serviceItems: [
                {
                    type: '王小明',
                    item: 18,
                    plan: '北京市朝阳区芍药居',
                    doctors: '',
                    advice: '',
                    fee: '',
                    target: ''
                }
            ],
            current: 0,
            servicesModel: {
                applyInstitutions: '',
                applyDoctorTeam: '',
                applySignType: '1'
            },
            servicesValidate: {
                applyInstitutions: [
                    { required: true, max: 500, message: '医疗机构不能为空', trigger: 'blur' }
                ],
                applyDoctorTeam: [
                    { required: true, max: 500, message: '医生团队不能为空', trigger: 'blur' }
                ],
                applySignType: [
                    { required: true, message: '请选择签约类型', trigger: 'change' }
                ]
            },
            formModel: {
                applyName: '',
                identCard: '',
                birthDate: '',
                sex: '',
                address: '',
                age: 1,
                telephone: '',
                members: {
                    items: [
                        {
                            memberName: '',
                            age: 1,
                            sex: '',
                            relation: '',
                            indentNumber: ''
                        }
                    ]
                }
            },
            ruleValidate: {
                applyName: [
                    { required: true, max: 50, message: '姓名不能为空', trigger: 'blur' }
                ],
                identCard: [
                    { required: true, max: 20, message: '身份证号不能为空', trigger: 'blur' }
                ],
                sex: [
                    { required: true, message: '请选择性别', trigger: 'change' }
                ],
                birthDate: [
                    { required: true, type: 'date', message: '请选择出生日期', trigger: 'change' }
                ],
                telephone: [
                    { required: true, max: 14, message: '请输入手机号码', trigger: 'change' }
                ],
                address: [
                    { required: true, message: '请输入家庭地址', trigger: 'blur' },
                    { type: 'string', max: 500, message: '最多输入500字', trigger: 'blur' }
                ]
            }
        }
    },
    methods: {
        last() {
            if (this.current > 0) {
                this.current -= 1;
            }
        },
        next() {
            this.$refs['formModel'].validate((valid) => {
                if (valid) {
                    this.$Message.success('提交成功!' + JSON.stringify(this.formModel));
                    this.handleStatus();
                } else {
                    this.$Message.error('居民签约信息验证失败!');
                }
            })
        },
        confirm() {
            var that=this;
            this.$Modal.confirm({
                title: '操作确认',
                content: '<p>签约成功，是否继续新增?</p>',
                loading: true,
                onOk: () => {
                    this.$Message.info('点击了确定');                   
                    this.$Modal.remove();
                    that.handleStatus();
                },
                onCancel: () => {
                    this.$Message.info('点击了取消');
                    that.$router.push({path: '/contact'})
                }
            });
        },
        handleStatus() {
            if (this.current == 2) {
                this.current = 0;
            } else {
                this.current += 1;
            }
            if(this.current == 2){
               this.confirm();
            }
        },
        handleServices(name) {
            this.$refs[name].validate((valid) => {
                if (valid) {
                    this.$Loading.start();
                    var addParams = this.formModel;
                    addParams.members=this.formModel.members.items;
                    addContact(addParams).then((res) => {
                        this.$Loading.finish();
                        this.total = res.data.total;
                        this.page = res.data.pageNum;
                        this.listData = res.data.list;

                    });                   
                    this.handleStatus();
                    this.formModel.service=this.servicesModel;
                    this.$Message.success('提交成功!' + JSON.stringify(this.formModel));
                } else {
                    this.$Message.error('居民签约信息验证失败!');
                }
            })
        },
        handleReset(name,that) {
            that.$refs[name].resetFields();
        },
        handleAdd() {
            this.formModel.members.items.push({
                memberName: '',
                age: 1,
                sex: '',
                relation: '',
                indentNumber: ''
            });
        },
        handleRemove(index) {
            this.formModel.members.items.splice(index, 1);
        }
    }
}
</script>
<style scoped lang="less">
.form-block {
    margin: 10px;
    overflow: hidden;
    background: #f8f8f9;
    margin-top: 10px;
    margin-bottom: 10px;
}

.step-block {
    margin: 10px;
    height: 50px;
    overflow: hidden;
    background: #f8f8f9;
    margin-top: 10px;
    margin-bottom: 10px;
}
</style>
