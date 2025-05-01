<template>
  <el-main style="height: fit-content;width: 90vw;margin-left: 115px;margin-top: -35px">
    <el-tabs type="border-card">
      <el-tab-pane label="所有订单"><OrdersTable :order-status=99></OrdersTable></el-tab-pane>
      <el-tab-pane label="待付款"><OrdersTable :order-status=1></OrdersTable></el-tab-pane>
      <el-tab-pane label="待发货"><OrdersTable :order-status=2></OrdersTable></el-tab-pane>
      <el-tab-pane label="待签收"><OrdersTable :order-status=3></OrdersTable></el-tab-pane>
    </el-tabs>
  </el-main>
</template>

<script>
import OrdersTable from "@/views/widgetUnit/OrdersTable.vue";
export default {
  name: "GoodsOrder",
  components:{
    OrdersTable,
  },
  data(){
    return{

    }
  },
  methods:{

  },
}
</script>

<style scoped>

</style>