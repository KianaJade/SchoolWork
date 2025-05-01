<template>
  <div class="orderManage">
    <el-table
        :data="tableData"
        border
        height="560"
        style="width: 100%;">
      <el-table-column
          prop="id"
          label="订单编号"
          width="80">
      </el-table-column>
      <el-table-column
          label="商品"
          width="80">
        <template slot-scope="scope">
          <el-image
              style="width: 50px;height: 50px;border-radius: 10px;margin-top: 5px"
              :src=imgBarsUrl+scope.row.img1
              fit="fit"></el-image>
        </template>
      </el-table-column>
      <el-table-column
          prop="goodsName"
          label="商品名称"
          width="180">
      </el-table-column>
      <el-table-column
          label="种类"
          width="180">
        <template  slot-scope="scope">
          <div v-for="item in scope.row.tagName" :key="item">{{item}}</div>
        </template>
      </el-table-column>
      <el-table-column
          prop="singlePrice"
          label="单价"
          width="80">
      </el-table-column>
      <el-table-column
          prop="number"
          label="数量"
          width="80">
      </el-table-column>
      <el-table-column
          prop="totalPrice"
          label="总价"
          width="80">
      </el-table-column>
      <el-table-column
          label="状态"
          width="80">
        <template  slot-scope="scope">
          <div v-if="scope.row.status === 1">未付款</div>
          <div v-if="scope.row.status === 2">已付款，未发货</div>
          <div v-if="scope.row.status === 3">已发货</div>
          <div v-if="scope.row.status === 4">已签收</div>
        </template>
      </el-table-column>
      <el-table-column
          prop="orderDate"
          label="下单时间"
          width="180">
      </el-table-column>
      <el-table-column
          label="管理"
          width="180">
        <template slot-scope="scope">
          <el-button
              size="mini"
              type="success"
              v-if="scope.row.status===2"
              @click="handleEdit(scope.row)">发货</el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import signUpStatus from "@/utils/status";

export default {
  name: "OrderManage",
  data() {
    return {
      imgBarsUrl:"http://localhost:8080/download/",
      tableData: [],
    }
  },
  methods:{
    initShopOrders(){
      this.$request.get('/shop/getShopOrders',{
        params:{
          phone:signUpStatus.getPhone(),
        }
      }).then((res)=>{
        if(res.code === 0){
          this.$message.error('加载失败');
        }else{
          this.tableData=res.data
        }
      }).catch();
    },

    //发货
    handleEdit(row){
      this.$request.post('/shop/deliverGoods',{
        id:row.id,
      }).then((res)=>{
        if(res.code === 0){
          this.$message.error('发货失败：'+res.msg)
        }else{
          this.$message.success('发货成功');
          this.initShopOrders();
        }
      }).catch((err)=>{
        this.$message.error('异常：'+err);
      });
    },
  },
  created() {
    this.initShopOrders();
  }
}
</script>

<style scoped>
.orderManage{
  height: fit-content;
  width: fit-content;
  margin-left: 100px;
  background-color: #F0EFF2;
}
</style>