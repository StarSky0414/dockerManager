<template>
  <v-chart theme="ovilia-green" v-bind:options="option"/>
</template>

<script>
import ECharts from 'vue-echarts'

export default {
  name: "EchartsGauge",
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
    }
  },
  watch: {

    date(v) {

      this.option.series[0].data[0].value=v;
      this.option.series[0].detail= {formatter: v + '%'};


    }
  },
  data() {
    return {
      option: {
        tooltip: {
          formatter: '{a} <br/>{b} : {c}%'
        },
        toolbox: {
          feature: {
            restore: {},
            saveAsImage: {}
          }
        },
        series: [
          {
            name: this.title,
            type: 'gauge',
            detail: {formatter: this.date + '%'},
            data: [{value: this.date, name: this.title}]
          }
        ]
      }
    }
  }
}
</script>

<style scoped>

</style>