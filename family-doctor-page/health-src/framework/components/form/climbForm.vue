<template>
  <Form ref="otFormValidateDom" :model="otFormValidate" :rules="otRuleValidate" :label-width="80">
    <template v-for="otItem in otTableDates">
      <Form-item v-if="!otItem.flag" :label="otItem.label" :prop="otItem.prop">
        <Input v-if="otItem.type==='text' || otItem.type==='password' || otItem.type==='textarea'" :type="otItem.type" v-model="otFormValidate[otItem.prop]"
          :autosize="{minRows: 2,maxRows: 5}" :placeholder="otItem.placeholder"></Input>
        <Select v-if="otItem.type === 'select'" v-model="otFormValidate[otItem.prop]" :placeholder="otItem.placeholder">
          <Option v-for="sItem in otItem.options" :value="sItem.value" :key="sItem">{{ sItem.label }}</Option>
        </Select>
        <Radio-group v-if="otItem.type === 'radio'" v-model="otFormValidate[otItem.prop]">
          <Radio v-for="rItem in otItem.options" :label="rItem.label">{{rItem.value}}</Radio>
        </Radio-group>
        <Checkbox-group v-if="otItem.type === 'checkbox'" v-model="otFormValidate[otItem.prop]">
          <Checkbox v-for="cItem in otItem.options" :label="cItem.label"></Checkbox>
        </Checkbox-group>
        <Date-picker :type="otItem.type" v-if="otItem.type === 'date' || otItem.type === 'daterange' || otItem.type === 'datetime' || otItem.type === 'datetimerange' || otItem.type === 'year' || otItem.type === 'month'"
          v-model="otFormValidate[otItem.prop]" :placeholder="otItem.placeholder"></Date-picker>
      </Form-item>
      <Form-item v-else :label="otItem.label">
        <Row v-if="otItem.type === 'dategroup'">
          <Col span="11">
          <Form-item :prop="otItem.prop.pre.prop">
            <Date-picker :type="otItem.prop.pre.type" :placeholder="otItem.prop.pre.placeholder" v-model="otFormValidate[otItem.prop.pre.prop]"></Date-picker>
          </Form-item>
          </Col>
          <Col span="2" style="text-align: center">-</Col>
          <Col span="11">
          <Form-item :prop="otItem.prop.after.prop">
            <Date-picker :type="otItem.prop.after.type" :placeholder="otItem.prop.after.placeholder" v-model="otFormValidate[otItem.prop.after.prop]"></Date-picker>
          </Form-item>
          </Col>
        </Row>
      </Form-item>
    </template>
    <Form-item>
      <Button type="primary" @click="handleSubmit('otFormValidateDom')">提交</Button>
      <Button type="ghost" @click="handleReset('otFormValidateDom')" style="margin-left: 8px">重置</Button>
    </Form-item>
  </Form>
</template>

<script>
  export default {
    name: 'climbForm',
    data () { },
    props: [
      'otTableDates'
    ]
  }
</script>