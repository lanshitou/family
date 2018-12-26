<template>
    <div style="background:#eee;padding: 0px">
            <div class='rowBox filterBar'>
                <div class='columnBox'>
                    <div class='columnBox menu'>
                        <div class='filterTitle'>时间范围:</div>
                        <Date-picker type='daterange' placement='bottom-start' placeholder='选择日期' style='width: 200px'></Date-picker>
                    </div>
                    <div class='columnBox menu'>
                        <div class='filterTitle'>最&#8195;&#8195;近:</div>
                        <Radio-group type='button' v-model='dataBtnValue' size='large'>
                            <Radio label='一个月'></Radio>
                            <Radio label='三个月'></Radio>
                            <Radio label='半年'></Radio>
                            <Radio label='一年'></Radio>
                        </Radio-group>
                    </div>
                    <div class='columnBox menu searchInput'>
                        <Input v-model="searchParams.like" icon='ios-search-strong' placeholder='请输入关键字搜索' style='width: 200px'></Input>
                    </div>
                </div>
                <div class='columnBox marginTop20'>
                    <div class='columnBox menu'>
                        <div class='filterTitle'>状&#8195;&#8195;态:</div>
                        <Select v-model="searchParams.status" style='width:200px' placeholder='全部'>
                            <Option v-for='item in stateType' :value='item.value' :key='item'>{{ item.label }}</Option>
                        </Select>
                    </div>
                    <div class='columnBox menu'>
                        <div class='filterTitle'>签约类型:</div>
                        <Select v-model="searchParams.applySignType" style='width:200px' placeholder='全部'>
                            <Option v-for='item in signType' :value='item.value' :key='item'>{{ item.label }}</Option>
                        </Select>
                    </div>
                    <div class='columnBox menu'>
                        <div class='filterTitle'>排&#8195;&#8195;序:</div>
                        <Select v-model="searchParams.sort" style='width:200px' placeholder='排序(默认为时间降序)'>
                            <Option  v-for='item in sortType' :value='item.value' :key='item'>{{ item.label }}</Option>
                        </Select>
                    </div>
                    <div class='columnBox menu searchInput'>
                        <Button class='marginLeft12' type='primary' :loading='loading2' icon='search' @click='search'>
                            <span v-if='!loading2'>查询</span>
                            <span v-else>Loading...</span>
                        </Button>
                    </div>
                </div>
            </div>
            <slot></slot>
            <!--
            <transition name="move" mode="out-in"><router-view keep-alive></router-view></transition>  
            -->
            <v-table :searchInfo="searchInfo"></v-table>   
    </div>
</template>
<script>
    import vTable from './table.vue';
    export default {
        components: {
            'v-table': vTable
        },       
        data () {
            return {
                searchInfo: [],
                searchParams: {
                    applySignType: '',
                    status: '',
                    order: 'enable_date',
                    like: '',
                    sort: 'desc'                    
                },
                loading: false,
                loading2: false,
                dataBtnValue: '一个月',
                stateType: [
                    {
                        value: '',
                        label: '全部'
                    },
                    {
                        value: '0',
                        label: '待审批'
                    },
                    {
                        value: '1',
                        label: '已生效'
                    },
                    {
                        value: '2',
                        label: '已结束'
                    }
                ],
                signType: [
                    {
                        value: '',
                        label: '全部'
                    },
                    {
                        value: '0',
                        label: '个人'
                    },
                    {
                        value: '1',
                        label: '家庭'
                    }
                ],
                sortType: [
                    {
                        value: 'desc',
                        label: '生效日期降序'
                    },
                    {
                        value: 'asc',
                        label: '生效日期升序'
                    }
                ],                
            }
        },
        methods: {
            toLoading() {
                this.loading = true;
            },
            search() {
                this.searchInfo = [this.searchParams];
            }
        }
    }
</script>
<style scoped>
.rowBox {
    display: -webkit-flex;
    /* Safari */
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    padding: 12px;
}

.columnBox {
    display: -webkit-flex;
    /* Safari */
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
}

.marginTop20 {
    margin-top: 8px;
}

.filterBar {
    background: #f8f8f9;
    border: 1px #dddee1 solid;
    border-radius: 4px;
}
.menu {
    flex-direction: row;
    align-items: center;
}

.filterTitle {
    font-size: 12px;
    color: #1c2438;
    padding: 10px 12px;
}
.searchInput {
    flex: 1;
    justify-content: flex-end;
    margin-right: 12px;
}
.marginLeft12 {
    margin-left: 12px;
}
</style>
