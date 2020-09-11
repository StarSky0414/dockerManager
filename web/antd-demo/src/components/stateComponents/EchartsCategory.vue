<template>
  <v-chart theme="ovilia-green" v-bind:options="option"/>
</template>

<script>
import ECharts from 'vue-echarts'

export default {
  name: "EchartsCategory",
  components: {
    'v-chart': ECharts
  },
  methods: {},
  props: {
    date: {
      type: String
    },
    title: {
      type: String
    },
    yMax: {
      type: String
    }
  },
  watch: {

    date(v) {
      if (this.option.series[0].data.length > 20) {
        this.option.series[0].data.shift();
        this.option.xAxis.data.shift();
      }
      console.log(this.option.series[0].data)

      console.log(this.option.series[0].data)
      this.option.series[0].data.push(v);
      let date = new Date();
      this.option.xAxis.data.push(date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds());


    },
    yMax(){
      this.option.yAxis.max = this.yMax;
    }
  },
  data() {
    return {
      option: {
        title: {
          left: 'center',
          text: this.title,
        },

        xAxis: {
          type: 'category',
          boundaryGap: false,
          data: []
        },
        yAxis: {
          max: this.yMax,
          min: 0,
          type: 'value'
        },
        series: [{
          data: [],
          type: 'line',
          smooth: true,

          areaStyle: {}
        }]
      },
    }
  }
}
</script>

<style scoped>

</style>