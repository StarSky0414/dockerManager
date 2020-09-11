<template>
<!--  <echarts-gauge v-bind:date="result.cpuPers" title="CPU使用率"></echarts-gauge>-->
  <echarts-category v-bind:date="result.cpuPers" title="CPU使用率"  v-bind:y-max="100"> </echarts-category>
  <echarts-category v-bind:date="result.mUsed" title="内存使用" v-bind:y-max="result.mAll"> </echarts-category>
  <div></div>
</template>

<script>

// import EchartsGauge from "@/components/stateComponents/EchartsGauge";
import axios from 'axios'
import EchartsCategory from "@/components/stateComponents/EchartsCategory";

export default {
  name: "ServerStatus",
  components: {
    EchartsCategory
    // EchartsGauge
  },

  methods: {
    requestTest() {

      axios.get("http://localhost:8084/TestController/testCpu", null).then(res => {
        this.result = res.data;
        console.log(this.result);

      })
    }
  },
  created() {
    this.requestTest();
  },
  data() {
    return {
      result: 0,
    }
  },
  mounted() {
    this.timer = setInterval(this.requestTest, 800);
  },
  beforeUnmount() {
    clearInterval(this.timer);
  }
}
</script>

<style scoped>

</style>