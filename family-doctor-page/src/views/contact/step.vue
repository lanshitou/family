<template>
<div>
    <p>当前正在进行第 {{ current + 1 }} 步</p>
    <Steps :current="current">
        <Step title="居民签约信息"></Step>
        <Step title="签约服务明细"></Step>
        <Step title="签约确认"></Step>
        <Step title="步骤4"></Step>
    </Steps>
    </div>
    <div>
    <Button type="primary" @click="next>提交</Button>
    </div>
</template>
<script>
    export default {
        data () {
            return {
                current: 0
            }
        },
        methods: {
            next () {
                if (this.current == 3) {
                    this.current = 0;
                } else {
                    this.current += 1;
                }
            }
        }
    }
</script>
