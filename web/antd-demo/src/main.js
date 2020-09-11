import { createApp } from 'vue';
import Antd from 'ant-design-vue';
import ECharts from 'vue-echarts/components/ECharts.vue'
import App from './App';
// 引入 SVG 渲染器模块
import 'zrender/lib/svg/svg'
import 'ant-design-vue/dist/antd.css';
import 'echarts/lib/echarts'
import 'echarts/lib/chart/line'
import 'echarts/lib/chart/bar'
import 'echarts/lib/chart/gauge'
import 'echarts/lib/chart/pie'
import 'echarts/lib/chart/radar'
import 'echarts/lib/component/tooltip'
import 'echarts/lib/component/title'
import 'echarts/lib/component/legend'
import 'echarts/lib/component/legendScroll'
const app = createApp(App);
// 注册组件后即可使用
app.component('v-chart', ECharts)
app.config.productionTip = false;
app.use(Antd).mount('#app');