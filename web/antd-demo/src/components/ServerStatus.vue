<template>
  <!--  <echarts-gauge v-bind:date="result.cpuPers" title="CPU使用率"></echarts-gauge>-->
  <!--  <echarts-category v-bind:date="result.cpuPers" title="CPU使用率"  v-bind:y-max="100"> </echarts-category>-->
  <!--  <echarts-category v-bind:date="result.mUsed" title="内存使用" v-bind:y-max="result.mAll"> </echarts-category>-->
  <div>
    <a-descriptions bordered title="系统资源概述" :size="size">
      <a-descriptions-item label="操作系统">
        {{ result.systemName }}
      </a-descriptions-item>
      <a-descriptions-item label="CPU型号">
        {{ result.cpuinfo }}
      </a-descriptions-item>
      <a-descriptions-item label="内存容量">
        {{ result.mAll }}G
      </a-descriptions-item>
      <a-descriptions-item label="CPU核数">
        {{ result.totalCPUs }}个
      </a-descriptions-item>
      <a-descriptions-item label="CPU单核速度">
        {{ result.referenceSpeeds }}
      </a-descriptions-item>

      <!--        <a-descriptions-item label="Config Info">-->
      <!--          Data disk type: MongoDB-->
      <!--          <br />-->
      <!--          Database version: 3.4-->
      <!--          <br />-->
      <!--          Package: dds.mongo.mid-->
      <!--          <br />-->
      <!--          Storage space: 10 GB-->
      <!--          <br />-->
      <!--          Replication factor: 3-->
      <!--          <br />-->
      <!--          Region: East China 1<br />-->
      <!--        </a-descriptions-item>-->
    </a-descriptions>

    <div id="usedInfo">
      <h3><b>系统使用情况</b></h3>
      <a-row type="flex" justify="space-around">

        <a-col :span="4">
          <div align="middle">
            <a-progress
                type="dashboard"
                :stroke-color="{'0%': '#e91010','100%': '#07c2f1'}"
                :percent=result.cpuPers
            >
              <template #format="percent">
                <span style="color: #07c2f1">{{ percent }}%</span>
              </template>
            </a-progress>
          </div>
          <div align="middle">CPU使用率</div>
        </a-col>


        <a-col :span="4">
          <div align="middle">
            <a-progress
                type="dashboard"
                :stroke-color="{'0%': '#e91010','100%': '#07c2f1'}"
                :percent=result.mUsedProportion*100
            >
              <template #format="percent">
                <span style="color: #07c2f1">{{ percent }}%</span>
              </template>
            </a-progress>
          </div>
          <div align="middle">内存使用</div>
        </a-col>

        <a-col :span="8">
          <a-card title="磁盘情况" size="small">

            <a-list item-layout="horizontal" :data-source="result.fileSysInfos">
              <template v-slot:renderItem="{item, index}">
                <a-list-item>
                  <a-list-item-meta>
                    <template v-slot:description>
                      <a @click="checkitem(index)">

                        <a-row type="flex" justify="space-around" align="middle">
                          <a-col align="middle" :span="5">{{ item.dirName }}</a-col>
                          <a-col :span="19">
                            <a-progress :percent="item.usePercent" size="small"/>
                          </a-col>
                          <div :span="8" v-show="this.fileSysShow === index">
                            <p>格式：{{ item.sysTypeName }}</p>
                          </div>
                          <div :span="8" v-show="this.fileSysShow === index">
                            <p>格式：{{ item.sysTypeName }}</p>
                          </div>
                        </a-row>
                      </a>
                    </template>
                  </a-list-item-meta>
                </a-list-item>
              </template>
            </a-list>
          </a-card>

        </a-col>
        <!--      <a-col :span="4" style="background-color: maroon ; height:150px"></a-col>-->
      </a-row>
    </div>
  </div>

</template>

<script>

// import EchartsGauge from "@/components/stateComponents/EchartsGauge";
// import EchartsCategory from "@/components/stateComponents/EchartsCategory";
import axios from 'axios'

export default {
  name: "ServerStatus",
  components: {
    // EchartsCategory
    // EchartsGauge
  },

  methods: {
    requestTest() {

      axios.get("http://localhost:8084/TestController/testCpu", null).then(res => {
        this.result = res.data;
        console.log(this.result);

      })
    },
    checkitem(i) {
      this.fileSysShow = this.fileSysShow === i ? null : i;
    }
  },
  created() {
    this.requestTest();
  },
  data() {
    return {
      result: 0,
      fileSystemArray: 0,
      fileSysShow: null,
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
#usedInfo {
  margin-top: 30px;
}
</style>