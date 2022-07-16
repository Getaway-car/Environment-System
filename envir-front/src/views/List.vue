<template>
  <div>

    <div style="margin: 10px 0">
      <el-date-picker
          v-model="datetime"
          type="date"
          placeholder="选择日期"
          format="yyyy 年 MM 月 dd 日"
          value-format="yyyy-MM-dd">
      </el-date-picker>
      <el-button class="ml-5" type="primary" @click="getByDate">搜索</el-button>
      <el-button type="primary" style="float:right" @click="scan">导入数据 <i class="el-icon-bottom"></i></el-button>
      <el-button type="primary" style="float:right" @click="getLatest">当前环境</el-button>
    </div>

    <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">
      <el-table-column prop="clock" label="时间" width="300">
      </el-table-column>
      <el-table-column prop="temperature" label="温度(℃)" width="120">
      </el-table-column>
      <el-table-column prop="humidity" label="湿度(%)" width="120">
      </el-table-column>
      <el-table-column prop="light" label="光照(px)">
      </el-table-column>
    </el-table>
    <div style="padding: 10px 0">
      <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[5, 10, 20, 30, 40]"
          :page-size="10"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total">
      </el-pagination>
    </div>
  </div>
</template>

<script>
import request from "@/utils/request";

export default {
  name: "List",
  data(){
    return {
      tableData:[],
      total: 0,
      currentPage: 1, // 分页
      pageSize: 10,
      datetime:"",
      headerBg: 'headerBg',
    }
  },
  created() {
    this.getAll();
  },
  methods: {
    getLatest(){
      request.get("http://localhost/environments").then(res => {
        this.tableData = res.data.records
        this.total = res.data.total
      });
    },

    scan(){
      request.get("http://localhost/environments/scan");
      window.location.reload();
      this.getAll();
    },
    getAll(){
      request.get("http://localhost/environments/"+this.currentPage+"/"+this.pageSize).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      });
    },

    getByDate(){
      request.get("http://localhost/environments/"+this.currentPage+"/"+this.pageSize+"/"+this.datetime).then(res => {
        console.log(res)
        this.tableData = res.data.records
        this.total = res.data.total
      });
    },

    handleSizeChange(pageSize) {
      this.pageSize = pageSize
      if (this.datetime!=null) {
        this.getByDate();
      } else {
        this.getAll();
      }
    },
    // //切换页码
    handleCurrentChange(currentPage) {
      this.currentPage = currentPage;
      if (this.datetime!=null) {
        this.getByDate();
      } else {
        this.getAll();
      }

    },
  }

}
</script>

<style>
.headerBg {
  background: #eee!important;
}
</style>
