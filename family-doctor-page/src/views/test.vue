<template>
    <component-child v-bind:data-list="dataList">
    </component-child>
    <Input v-model="dataInput" v-on:keyup.13="addDataItem()"></Input>
</template>
<script>
import ComponentChild from './child.vue'
export default {
    data() {
        return {
            dataInput: "",
            dataList: ['hello world!', 'welcome to use vue.js']
        }
    },
    components: { ComponentChild },
    methods: {
        addDataItem() {
            let self = this
            if (!(self.dataInput && self.dataInput.length > 0)) {
                return
            } self.dataList.push(self.dataInput)
            self.dataInput = ""
        }
    }
}
</script>