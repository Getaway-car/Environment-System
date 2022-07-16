<template>
  <div>
    <div>
      <el-date-picker
          v-model="datetime"
          type="date"
          placeholder="选择日期"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd">
      </el-date-picker>
      <el-select style="margin-left: 5px" v-model="value" placeholder="请选择">
        <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <el-button class="ml-5" type="primary" @click="dataProcess">搜索</el-button>
    </div>
    <div id="main" style="width: 600px; height: 550px"></div>
  </div>

</template>

<script>
import request from "@/utils/request";
import * as echarts from 'echarts';

export default {
  name: "DataProcess",
  data() {
    return {
      datetime: "",
      options: [{
        value: 'Tem',
        label: '温度'
      }, {
        value: 'Hum',
        label: '湿度'
      }, {
        value: 'Lig',
        label: '光照'
      }],
      value: '',
      result: [1, 3, 2]
    }
  },
  mounted() {
    this.barChart()
  },
  methods:{
    barChart(){
      let option = {
        color: ['#749f83', '#FF6666', '#336699'],
        xAxis: {
          type: 'category',
          data: ['最小值', '最大值', '平均值']
        },
        yAxis: {
          type: 'value'
        },
        series: [
          {
            data: this.result,
            type: 'bar',
            barWidth: 80,
            itemStyle: {
              normal: {
                //这里是重点
                color: function(params) {
                  //注意，如果颜色太少的话，后面颜色不会自动循环，最好多定义几个颜色
                  let colorList = ['#749f83', '#c23531', '#336699'];
                  return colorList[params.dataIndex]
                }
              }
            },
            label: {
              // 柱图头部显示值
              show: true,
              position: "top",
              color: "#333",
              fontSize: "12px",
              formatter: (params) => {
                return params.value[params.encode.x[0]];
              },
            }
          }
        ]
      };
      let chartDom = document.getElementById('main');

      let myChart = echarts.init(chartDom);
      myChart.setOption(option);
    },
    dataProcess(){
      if (this.value == 'Tem'){
        request.get("http://localhost/environments/Tem"+"/"+this.datetime).then(res =>{
          this.result = res.data
          this.barChart()
        }) ;
      }else if(this.value == 'Hum'){
        request.get("http://localhost/environments/Hum"+"/"+this.datetime).then(res =>{
          this.result = res.data
          this.barChart()

        }) ;
      }else if(this.value == 'Lig'){
        request.get("http://localhost/environments/Lig"+"/"+this.datetime).then(res =>{
          this.result = res.data
          this.barChart()
        }) ;
      }
    }
  }
}
</script>

<style scoped>

</style>
