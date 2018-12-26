<template>
  <div class="container">
    <climb-panel title="数字健康中使用的医院标签">
      <climb-badge :circle="false" :width="182" :height="143">
        <img :src="imgUrl" />
        <template slot="bottom">
          <div class="bottom-tip">三级甲等</div>
        </template>
      </climb-badge>
    </climb-panel>

    <climb-panel title="右上角示例">
      <div class="flex flex-row">
        <div>
          <climb-badge :circle="false">
            <img :src="imgUrl" />
            <template slot="right">
              <div class="hot-tip">hot</div>
            </template>
          </climb-badge>
        </div>

        <div class="ml10">
          <climb-badge :circle="false">
            <img :src="imgUrl" />
            <template slot="right">
              <div class="hot-tip">荐</div>
            </template>
          </climb-badge>
        </div>

        <div class="ml10">
          <climb-badge :circle="false">
            <img :src="imgUrl" />
            <template slot="right">
              <div class="hot-tip">new</div>
            </template>
          </climb-badge>
        </div>

        <div class="ml10">
          <climb-badge :circle="false">
            <img :src="imgUrl" />
            <template slot="right">
              <div class="hot-tip">新!</div>
            </template>
          </climb-badge>
        </div>

        <div class="ml10">
          <climb-badge :circle="false">
            <img :src="imgUrl" />
            <template slot="right">
              <div class="hot-tip">
                <Icon type="fireball" size="14"></Icon>
              </div>
            </template>
          </climb-badge>
        </div>
      </div>
    </climb-panel>
  </div>
</template>

<script>
  export default {
    data () {
      return {
        imgUrl: 'http://tiuweb-dhp.oss-cn-beijing.aliyuncs.com/tiuweb-dhp/hospital/36d088ab5133452583feac593327590c.jpg?x-oss-process=image/resize,w_134,h_104/auto-orient,1/quality,q_90/format,jpg'
      }
    }
  }

</script>

<style lang="scss">
  .bottom-tip {
    position: absolute;
    bottom: 0;
    height: 22px;
    width: 60px;
    line-height: 22px;
    text-align: center;
    color: #ff7403;
    border-top-right-radius: 10px;
    background: #fff;
  }

  .hot-tip {
    position: absolute;
    right: 0;
    height: 22px;
    line-height: 22px;
    text-align: center;
    padding: 0 3px;
    color: #fff;
    background-color: #ff7403;
    border-bottom-left-radius: 2px;
  }
</style>
