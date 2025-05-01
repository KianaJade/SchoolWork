<template>
  <el-main class="businessData">
    <el-row class="dataUp">
      <div class="lineData">
        <el-col :span="4">
          <el-row style="height: 7vh;">
            <div class="dataNumber">99</div></el-row>
          <el-row class="dataWord">待付款</el-row>
        </el-col>

        <el-col :span="4">
          <el-row style="height: 7vh;">
            <div class="dataNumber">99</div></el-row>
          <el-row class="dataWord">待付款</el-row>
        </el-col>

        <el-col :span="4">
          <el-row style="height: 7vh;">
            <div class="dataNumber">99</div></el-row>
          <el-row class="dataWord">待付款</el-row>
        </el-col>

        <el-col :span="4">
          <el-row style="height: 7vh;">
            <div class="dataNumber">99</div></el-row>
          <el-row class="dataWord">待付款</el-row>
        </el-col>

        <el-col :span="4">
          <el-row style="height: 7vh;">
            <div class="dataNumber">99</div></el-row>
          <el-row class="dataWord">待付款</el-row>
        </el-col>

        <el-col :span="4">
          <el-row style="height: 7vh;">
            <div class="dataNumber">99</div></el-row>
          <el-row class="dataWord">待付款</el-row>
        </el-col>
      </div>
    </el-row>

    <el-row class="dataDown">
      <el-col :span="4" :offset="1"><DataCard name1="支付金额"></DataCard></el-col>
      <el-col :span="4" :offset="1"><DataCard name1="支付金额"></DataCard></el-col>
      <el-col :span="4" :offset="1"><DataCard name1="支付金额"></DataCard></el-col>
      <el-col :span="4" :offset="1"><DataCard name1="支付金额"></DataCard></el-col>
    </el-row>

    <el-row class="dataDown">
      <el-col :span="4" :offset="1"><DataCard name1="支付金额"></DataCard></el-col>
      <el-col :span="4" :offset="1"><DataCard name1="支付金额"></DataCard></el-col>
      <el-col :span="4" :offset="1"><DataCard name1="支付金额"></DataCard></el-col>
      <el-col :span="4" :offset="1"><DataCard name1="支付金额"></DataCard></el-col>
    </el-row>
  </el-main>
</template>

<script>
import DataCard from "@/views/widgetUnit/DataCard.vue";
export default {
  name: "BusinessData",
  components:{
    DataCard,
  },
  data(){
    return{

    }
  },
  methods:{

  }
}
</script>

<style scoped>
.businessData{
  overflow: hidden;
  width: 85vw;
  height: 87vh;
  margin-top: -37px;
  background-color: #F0EFF2;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12);
}
.dataUp{
  height: 20vh;
  width: 85vw;
}
.dataDown{
  width: 85vw;
  height: 15vh;
  margin-top: 5vh;
}
.lineData{
  width: 80vw;
  height: 15vh;
  margin-left: 2vw;
  background-color: deepskyblue;
  border-radius: 30px;
}
.dataNumber{
  margin-top: 25px;
  color: white;
}
.dataWord{
  height: 8vh;
  color: white;
}
</style>