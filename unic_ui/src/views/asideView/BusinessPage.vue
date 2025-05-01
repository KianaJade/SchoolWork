<template>
  <el-main class="businessPage">
    <div style="display: flex">
      <el-aside class="businessAside" style="width: 70px">
        <el-menu class="businessMenu" default-active="1">
          <el-col>
            <div @click="goToManage"><LittleMenu :sel-index=businessPageShow index:1 icon-name="el-icon-s-shop" name="店铺信息"></LittleMenu></div>
<!--            <div @click="goToShopData"><LittleMenu :sel-index=businessPageShow index:2 icon-name="el-icon-s-data" name="店铺数据"></LittleMenu></div>-->
            <div @click="goToGoods"><LittleMenu :sel-index=businessPageShow index:3 icon-name="el-icon-s-goods" name="商品"></LittleMenu></div>
            <div @click="goToOrders"><LittleMenu :sel-index=businessPageShow index:4 icon-name="el-icon-s-order" name="订单"></LittleMenu></div>
          </el-col>
        </el-menu>
      </el-aside>
      <!--      <el-divider  direction="vertical"></el-divider>-->
      <div style="">
        <BusinessManage v-if="businessPageShow === 1"></BusinessManage>
<!--        <BusinessData v-else-if="businessPageShow === 2"></BusinessData>-->
        <GoodsManage v-else-if="businessPageShow === 3"></GoodsManage>
        <OrderManage v-else-if="businessPageShow === 4"></OrderManage>
      </div>
    </div>
  </el-main>
</template>

<script>
import LittleMenu from "@/views/widgetUnit/LittleMenu.vue";
import BusinessManage from "@/views/BusinessMain/BusinessManage.vue";
//import BusinessData from "@/views/BusinessMain/BusinessData.vue";
import GoodsManage from "@/views/BusinessMain/GoodsManage.vue";
import OrderManage from "@/views/BusinessMain/OrderManage.vue";
export default {
  name: "BusinessPage",
  components:{
    LittleMenu,
    BusinessManage,
    //BusinessData,
    GoodsManage,
    OrderManage
  },
  data(){
    return{
      businessPageShow:1,
      selAsideIndex:1,
    }
  },
  methods:{
    goToManage(){
      this.businessPageShow = 1;
    },
    goToShopData(){
      this.businessPageShow = 2;
    },
    goToGoods(){
      this.businessPageShow = 3;
    },
    goToOrders(){
      this.businessPageShow = 4;
    }
  }
}
</script>

<style scoped>
.businessPage{
  //overflow: hidden;
  height: 90vh;
  width: 95vw;
  margin-left: 133px;
  margin-top: -16px;

}
.businessAside{
  position: fixed;
  height: 92vh;
  margin-left: -38px;
  margin-top: -20px;
  z-index: 500;
  background-color: white;
}
.businessMenu{
  width: 70px;
  height: 92vh;
  margin-left: 0;
  //background-color: ;
  z-index: 1100;
}
</style>