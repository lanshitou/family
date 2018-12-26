<template>
    <div class="index">
        <div style="background:#eee;padding: 1px">
            <Button-group>
                <Button type="ghost" shape="circle" icon="ios-plus" @click.native="add">添加</Button>
                <Button type="ghost" icon="ios-edit" @click.native="edit">编辑</Button>
                <Button type="ghost" shape="circle" icon="ios-search" @click.native="del">删除</Button>
                <Button type="ghost" shape="circle">圆角按钮</Button>
            </Button-group>
        </div>
        <Row type="flex" justify="center">
            <Table :context="self" :data="listData" @on-selection-change="select" :columns="tableColumns" stripe border></Table>
            <div class="pages-block">
                <div class="pages-right">
                    <Page :total="total" :page-size="pageSize" @on-page-size-change="doPageSizeChange" @on-change="change" show-sizer show-total :current="page" show-elevator></Page>
                </div>
            </div>
        </Row>
        <!--信息编辑摸态框-->
        <Modal v-model="updateModel" title="信息编辑" class="update-model">
            <Row justify="center" class="row">
                <Col span="6" class="lable-title">姓名</Col>
                <Col span="14">
                <input v-model="updateData.name" placeholder="请输入姓名">
                </Input>
                </Col>
            </Row>
            <Row justify="center" class="row">
                <Col span="6" class="lable-title">年龄</Col>
                <Col span="14">
                <input v-model="updateData.age" placeholder="请输入年龄">
                </Input>
                </Col>
            </Row>
            <Row justify="center" class="row">
                <Col span="6" class="lable-title">地址</Col>
                <Col span="14">
                <input v-model="updateData.address" placeholder="请输入地址">
                </Input>
                </Col>
            </Row>
        </Modal>
    </div>
</template>
<script>
const initPage = {
    page: 1,
    pageSize: 1
};

import {
    getUserListPage, removeContact
} from '../../api/api';
export default {
    props: [
        'searchInfo'
    ],
    data() {
        return {
            self: this,
            selections: [],
            total: 0,
            page: 1,
            pageSize: 1,
            listData: [],
            updateModel: false,
            updateData: {
                applyName: "",
                indentCard: 0,
                applySignType: ""
            },
            updateIndex: 0,
            tableColumns: [
                {
                    type: 'selection',
                    width: 60,
                    key: 'id',
                    align: 'center'
                },
                {
                    title: '签约居民',
                    align: 'center',
                    key: 'applyName'
                },
                {
                    title: '身份证号',
                    align: 'center',
                    key: 'indentCard'
                },
                {
                    title: '签约类型',
                    align: 'center',
                    key: 'applySignType'
                },
                {
                    title: '申请日期',
                    key: 'applyDate',
                    align: 'center'

                },
                {
                    title: '拟申请机构',
                    key: 'applyInstitutions',
                    ellipsis: true,
                    align: 'center'

                },
                {
                    title: '拟申请团队',
                    key: 'applyDoctorTeam',
                    align: 'center'

                },
                {
                    title: '状态',
                    key: 'status',
                    align: 'center'

                },
                {
                    title: '操作', key: 'action', align: 'center',
                    render: (h, params) => {
                        return h('div', [
                            h('Button', {
                                props: { type: 'primary', size: 'small' },
                                style: { marginRight: '5px' },
                                on: {
                                    click: () => {
                                        this.doShowMsg(params.index)
                                    }
                                }
                            }, '查看')
                        ]);
                    }
                }
            ]
        }
    },
    watch: {
        searchInfo: function () {
            console.info('==='+JSON.stringify(this.searchInfo[0]));
            let para = {
                page: 1,
                pageSize: 1,
                queryParams: this.searchInfo[0]
            };
            this.$Loading.start();
            getUserListPage(para).then((res) => {
                this.$Loading.finish();
                this.total = res.data.total;
                this.page = res.data.pageNum;
                this.listData = res.data.list;
            });           
        }
    },
    created: function () {
        let para = {
            page: 1,
            pageSize: 1
        };
        this.$Loading.start();
        getUserListPage(initPage).then((res) => {
            this.$Loading.finish();
            this.total = res.data.total;
            this.page = res.data.pageNum;
            this.listData = res.data.list;
        });
    },
    methods: {
        search() {
            getUserListPage(initPage).then((res) => {
                this.$Loading.finish();
                this.total = res.data.total;
                this.page = res.data.pageNum;
                this.listData = res.data.list;
            });
        },
        add() {
            this.$router.push({ path: '/contact/add' });
        },
        edit() {
            if (this.selections.length != 1) {
                this.$Message.error('只能选择一条记录！');
            } else if (this.selections.length == 0) {
                this.$Message.error('请选择一条记录！');
            } else {
                this.$router.push({ name: '/contact/edit',params:{id:this.selections[0].id}});
            }
        },
        del() {
            if (this.selections.length == 0) {
                this.$Message.error('至少选择一条记录！');
            } else {
                var ids = [];
                this.selections.forEach(function (element) {
                    ids.push(element.id);
                }, this);
                this.$Loading.start();
                removeContact(ids.join(',')).then((res) => {
                    if (res.status == 200) {
                        let queryParas = {
                            page: this.page,
                            pageSize: this.pageSize
                        };
                        getUserListPage(queryParas).then((response) => {
                            this.$Loading.finish();
                            this.total = response.data.total;
                            this.page = response.data.pageNum;
                            this.listData = response.data.list;
                        });
                    } else {
                        this.$Message.error('删除失败！');
                    }
                });
            }
        },
        select(selection) {
            this.selections = selection;
        },
        //查看单个信息
        doShowMsg(index) {
            this.$Modal.info({
                title: '用户信息',
                content: `姓名：${this.listData[index].applyName}<br>年龄：${this.listData[index].indentCard}<br>地址：${this.listData[index].applySignType}`
            })
        },
        mockTableData() {
            let para = {
                page: 1,
                pageSize: 1
            };
            this.$Loading.start();
            getUserListPage(para).then((res) => {
                this.$Loading.finish();
                this.total = res.data.total;
                this.page = res.data.pageNum;
                this.listData = res.data.list;

            });
        },
        show(index) {
            this.$Modal.info({
                title: '用户信息',
                content: `姓名：${this.listData[index].name}<br>年龄：${this.listData[index].age}<br>地址：${this.listData[index].addr}`
            })
        },
        remove(index) {
            let self = this;
            this.$Modal.confirm({
                title: '用户信息',
                content: `是否删除此记录`,
                onOk: function () {
                    this.$Loading.start();
                    let para = { id: index }
                    removeUser(para).then((res) => {
                        self.mockTableData();
                    });
                }
            })
        },
        change(row) {
            // 这里直接更改了模拟的数据，真实使用场景应该从服务端获取数据
            console.log(row);
            let para = {
                page: row,
                pageSize: this.pageSize
            };
            this.$Loading.start();
            getUserListPage(para).then((res) => {
                this.$Loading.finish();
                this.total = res.data.total;
                this.page = res.data.pageNum;
                this.listData = res.data.list;

            });
        },
        //改变当前显示的行数
        doPageSizeChange: function (size) {
            this.pageSize = size;
            let para = {
                page: this.page,
                pageSize: size
            };
            this.$Loading.start();
            getUserListPage(para).then((res) => {
                this.$Loading.finish();
                this.total = res.data.total;
                this.page = res.data.pageNum;
                this.listData = res.data.list;

            });
        }
    },
    mounted() {
        this.mockTableData();
    }
}
</script>
<style scoped lang="less">
.index {
    .lable-title {
        text-align: center;
    }
    .pages-block {
        margin: 0px;
        overflow: hidden;
        margin-top: 10px;
        margin-bottom: 10px;
        .pages-right {
            float: right;
        }
    }
    .update-model .row {
        display: flex;
        align-items: center;
        margin-bottom: 10px;
    }
}
</style>