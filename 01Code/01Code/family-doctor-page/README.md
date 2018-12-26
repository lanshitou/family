# climb-model-page

> 基于vue init webpack脚手架，集成并应用下面工具：
  css预编译scss，
  加载进度条nprogress，
  字体图标库font-awesome,
  初始化样式nomolize.css,
  以及测试，
  eslint，
  vuex,
  vue-router及懒加载路由,
  vue-resource
  并使用iview-ui作为UI库,
  该版本为基线版本

代码结构： 
```
.
|-- build
|   |-- build.js
|   |-- check-versions.js
|   |-- dev-client.js
|   |-- dev-server.js
|   |-- utils.js
|   |-- vue-loader.config.js
|   |-- vebpack.base.config.js
|   |-- webpack.dev.config.js
|   |-- webpack.prod.config.js
|   |-- webpack.test.config.js
|   |-- vue-loader.config.js
|   |-- vue-loader.config.js
|-- config                           // 项目开发环境配置
|   |-- index.js                     // 项目打包部署配置 
|   |-- dev.env.js
|   |-- prod.env.js
|   |-- test.env.js
|-- dist                              // 项目最终发布版本代码
|   |-- ...
|-- node_modules                      // 依赖包
|   |-- ... 
|-- src                              // 源码目录
|   |-- assets                       // 资源目录 
|   |-- components                   // 公共组件
|       |-- Navigation.vue           // 页面头部公共组件
|       |-- ...
|   |-- fiters                       
|       |-- filters.js
|   |-- config                       // 路由配置和程序的基本信息配置
|       |-- routes.js                // 配置页面路由
|   |-- scss                         // scss文件
|       |-- _clearfix.scss           // 清除浮动
|       |-- ... 
|   |-- pages                        // 页面类
|       |-- Home.vue                 // 首页
|       |-- Login.vue                // 登录
|   |-- vuex                         // vuex的状态管理
|       |-- store.js                 // store存放状态
|       |-- actions.js               // actions显示提交
|       |-- getters.js               // getters获取计算状态
|   |-- app.vue                      // 页面入口文件
|   |-- main.js                      // 程序入口文件，加载各种公共组件
|   |-- static                       // 静态资源目录(本地存放json)
|       |--*.json
|   |-- test                         // 测试目录
|-- .babelrc                         // ES6语法编译配置
|-- .editorconfig
|-- .eslintignore
|-- .eslintrc.js
|-- .gitignore                       // git忽略
|-- index.html                       // 页面入口
|-- README.md                        // 项目说明
|-- package.json                     // 配置项目相关信息，通过执行 npm init 命令创建
.
```
## Build Setup

``` bash
# install dependencies
npm install

# serve with hot reload at localhost:8080
npm run dev

# build for production with minification
npm run build
 
```

For detailed explanation on how things work, checkout the [guide](http://vuejs-templates.github.io/webpack/) and [docs for vue-loader](http://vuejs.github.io/vue-loader).
 
## 前言
```
  安装node------------>安装webpack-------------->安装vue-cli
```
快速带过官方脚手架初始化新建项目：
``` bash
# vue init
  vue init webpack my-project 
``` 
项目名、vue-router、eslint、test等按需要选择


#### 推荐使用cnpm(淘宝镜像)
```
  npm install -g cnpm --registry=https://registry.npm.taobao.org
```
注： 如果使用cnpm，dos窗口出现header{}，不能下载，设置proxy即可解决

## 使用

下载项目，运行步骤见Build Setup


## 开发阶段

### 端口号设定：
```js
// conifg/index.js
  ...
    dev: {
      ...
      port: 8089 //默认为8080，改之即可
      ...
    }
  ...
```

## vue

### 1.组件
```js
// f.vue文件
  <template>
    <div p='prop1' o='prop2'></div>
  </template>
  <script>  
  exprot default {
    name: 'component-name',
    props: [
      'prop1','prop2'
    ]
  }
</script>

// l.vue
  <template>
    <f :prop1='hello' :prop2='vueJs'></f>  
  </template>
  <script>
    import f from 'f.vue'

    export default {
      components: [
        f
      ]
    }
  </script>
```

### 2.懒加载路由及子路由
```js
  routes:[
    {
      path: '/',
      component: resolve => require(['pages/Home'],resolve)
    }, {
      path: '/tables',
      component: resolve => require(['pages/Tables'],resolve),
      children: [
        {
          path: 'tablechild',
          component: resolve => require(['components/Tableexpand'],resolve)
        }
      ] 
    }
  ]
// 路径：.../tables/tablechild
// 跳路由Tableexpand.vue
```

### 3.vue-resouce
```js
  vm.$http.get(url).then(function(data){},function(res){})
  vm.$http.post(url,{}).then(function(data){},function(res){})
  vm.$http.jsonp(url).then(function(data){},function(res){})

  //可使用其他如jQuery.ajax(),axios等，使用之前确保install
  //在vue2.0更新后，vue-resource不再对其更新，作者推荐使用axios
```
### 4.vuex
```js
// vuex/store.js
  import Vue from 'vue'
  import Vuex from 'vuex'
  import * as actions from './actions.js'
  import * as getters from './getters.js'

  Vue.use(Vuex)
  // 应用初始状态
  const state = {
    count: 10
  }

  // 定义所需的mutations
  const mutations = {
    INCRE (state) {
      state.count++
    },
    DECRE (state) {
      state.count--
    }
  }

  // 创建store实例
  export default new Vuex.Store({
    actions,
    getters,
    state,
    mutations
  })

// vuex/getter.js 状态获取
  export const getCount = state => {
    return state.count
  }

// vuex/actions.js 显式提交
  export const increment = ({commit}) => {
    commit('INCRE')
  }
  export const decrement = ({commit}) => {
    commit('DECRE')
  }

// pages/vuextest.vue 使用
  <template>
    <p>{{getCount}}</p>
    <button @click="increment">+</button>
    <button @click="decrement">-</button>
  </template>

  <script>
    import {mapGetters, mapActions} from 'vuex' // 引入
    export default {
      computed: {
        // getters混入computed对象中
        ...mapGetters([
          'getCount'
        ])
      },
      methods: {
        ...mapActions([
          'increment',
          'decrement'
        ])
      }
    }
  </script>
```


## CSS说明

### 1.初始化样式引入
使用normalize.css进行初始化：
```js
// package.json

  "dependencies": {
    ...
    "normalize.css": "^5.0.0"
    ...
  }

// App.vue
  <style lang="scss">
    @import "../node_modules/normalize.css"; 

  </style>
```

### 2.sass(scss)使用
需在style中使用lang="scss"，如：
``` html 
  <style lang="scss"></style>
``` 

在scss目录下新建a.scss
```html 
<!-- f.vue中引用 -->
  <style lang="scss">
    @import "scss/_container";

    ...
  </style>
```


## js说明

### 1.引用其他库：
```bash
# 方法一：在package.json中dependencies栏添加相应的包，执行
        npm install
# 方法二：使用
        npm install [name] --save
```
### 2.本地json请求
本地json文件需放置在static目录下，并通过
``` js 
  url:"../static/name.json"
``` 
调用

### 3.data数据中引入本地图片路径

使用require引入图片
```html
<!-- templete中： -->
  <div v-for="item in banners">
      <img :src="item.url" :alt="item.title"/>
  </div>
```
```js
// script中：
  data() {
        return {
          listDatas: [], 
          banners: [{
            title: "banner1",
            url: require("src/assets/banner/b_1.jpg") 
          },{
            title: "banner2",
            url: require("src/assets/banner/b_2.jpg") 
          },{
            title: " banner3",
            url: require("src/assets/banner/b_3.jpg")  
          },{
            title: "banner4",
            url: require("src/assets/banner/b_4.jpg") 
          },{
            title: "banner5",
            url: require("src/assets/banner/b_5.jpg")  
          },{
            title: "banner6",
            url: require("src/assets/banner/b_6.jpg")  
          }]
        }
      }
```

### 4.filters使用
```js
// filters/filters.js
  /**
   * 过滤器表
   * Created by ChaoLeer on 17/1/25.
   */
  // 千位分隔符，默认使用逗号','分隔
  import numspa from './numspa.js'
  // 去除首尾空格
  import trim from './trim.js'

  export {
    numspa,
    trim
  }

// filters/numspa.js
  /**
   * 千位分隔符，默认使用逗号','分隔
   * @param  {[type]} value  [传入参数]
   * @param  {[type]} splite [分隔符号，可选，默认为逗号',']
   * @return {[type]}        [返回处理后数据]
   */
  const numspa = (value, div) => {
    div = div || ','
    return value.toString().replace(/\B(?=(\d{3})+$)/g, div)
  }

  export default numspa

// filters/trim.js
  /**
   * 去除首尾空格
   * @param  {[string]} string [字符串]
   * @return {[string]}        [返回处理后数据]
   */
  const trim = (string) => {
    return string.toString().replace()
  }

  export default trim

// 在*.vue里使用
  import {trim, numspa} from 'filters'

  trim(...)
```

```html
  <templete>
    <div>
      <p>当前在线用户：<code>{{cout}}</code></p> 
    </div>
  </templete>
  <script>
    import {numspa} from 'filters'

    export default {
      data () {
        return {
          cout: unumspa(10000782937897, ' ')
        }
      }
    }
    //当前在线用户：10 000 782 937 897
  </script>

```

### 5.utils工具
代码说明：
```js
// utils/index.js
  // 统一异常处理
  import Exception from './exception.js'

// utils/exception.js
  funtion CommonException(){
    ...
  }
  export default CommonException

// Utilstest.vue
  import {Format} from 'utils'
  Format(new Date(), 'yyyy-MM-dd hh:mm:ss') // 格式化日期
```
#### 字符串格式化工具
```js
  StringFormat()
```
#### 日期格式化工具
```js
  Format(new Date(), 'yyyy-MM-dd')
```
#### 日志工具
```js
  Log("...")
```
#### 统一异常处理
```js
  Exception('系统异常', '10001', vm)
```

### 6.ESlint
注：若不以该规范进行，则出现阻断性提示，希望大家能够养成习惯，这样就可以更容易适应
以下为默认规则(未进行修改)：

```bash
# 行结尾不能有空格

# tab设置为转空格

# 语句不需要使用;结尾
  let a = 12
# 函数必须用空格隔开参数
  function () {
    ...
  }
# 注释//后必须有空格间隔
  let a = 12 // a为12
# 循环语句需为以下格式：
  for (let a = 0; a < 12; a++) {

  }
# 条件语句需为：
  if () {

  } else {

  }
# 参数,后需空格
  function (c1, c2, c3) {
    ...
  }
# 字符串必须为单引号' '
  let a = '你好！'

```

## 打包

### 打包部署在服务器指定目录下：

#### 第一步：
```js
// config/index.js
  ...  
    build: {
      ...
      //设置为部署目录名
      assetsPublicPath: '../boot_project_v1/'
      ...
    }
  ...
```

```bash
# 在命令窗口中使用
  npm run build
```
#### 第二步：
打包完成后，将
```bash
  |--dist
  |  |--index.html
  |  |--static
  |     |--...

```
目录下的所有文件直接丢在服务器指定目录下(第一步设定的目录下即可)

### 打包部署服务器根目录下：
使用默认的即可
```js

// config/index.js
  ...  
    build: {
      ...
      assetsPublicPath: '/'
      ...
    }
  ...
```
