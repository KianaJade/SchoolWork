<template>
  <el-table
      :data="tableData"
      border
      height="560"
      style="width: 100%;">
    <el-table-column
        prop="id"
        label="订单编号"
        width="60">
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
        width="160">
    </el-table-column>
    <el-table-column
        label="种类"
        width="80">
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
        label="管理"
        width="145">
      <template slot-scope="scope">
        <el-button
            size="mini"
            type="success"
            v-if="scope.row.status === 1"
            @click="payOff(scope.row)">付款</el-button>
        <el-button
            size="mini"
            type="success"
            v-if="scope.row.status === 3"
            @click="signInOrder(scope.row)">签收</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script>
import signUpStatus from "@/utils/status";

export default {
  name: "OrdersTable",
  props:{
    orderStatus:{
      type:Number,
      required:true,
    },
  },
  data(){
    return{
      imgBarsUrl:"http://localhost:8080/download/",
      tableData: [],
      payOffID:[],
      payOffImg:[],
      payOffSum:0,
    }
  },
  methods:{
    initShopOrders(){
      this.tableData = [];
      this.payOffID = [];
      this.payOffImg = [];
      this.payOffSum = 0;
      this.$request.get('/getUserOrders',{
        params:{
          phone:signUpStatus.getPhone(),
        }
      }).then((res)=>{
        if(res.code === 0){
          this.$message.error('加载失败');
        }else{
          if(this.orderStatus === 99){
            this.tableData = res.data;
          }else{
            let len = res.data.length;
            for(let i=0;i<len;i++)
            {
              if(res.data[i].status === this.orderStatus)this.tableData.push(res.data[i])
            }
          }
        }
      }).catch();
    },

    payOff(row){
      let id = [];
      id.push(row.id)
      this.$request.post('/updateOrder/payOff',id,).then((res)=>{
        if(res.code === 0){
          this.$message.error('付款失败：'+res.msg);
        }else{
          this.$message.success('付款成功');
          this.initShopOrders();
        }
      }).catch((err)=>{
        this.$message.error('异常'+err);
      });
    },
    signInOrder(row){
      this.$request.post('/updateOrder/signIn',row.id,).then((res)=>{
        if(res.code === 0){
          this.$message.error('签收失败：'+res.msg);
        }else{
          this.$message.success('签收成功');
          this.initShopOrders();
        }
      }).catch((err)=>{
        this.$message.error('异常'+err);
      });
    }

  },
  created() {
    this.initShopOrders();
  }
}
</script>

<style scoped>

</style>