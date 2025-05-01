<template>
  <el-main style="height: fit-content;width: 90vw;margin-left: 10vw">
    <div class="shopBag">
      <div class="shopBag-left">
        <span style="float: left;font-size: 16px;color: #1F1F1F;font-weight: 700;">全部商品</span>
        <el-table
            :data="tableData"
            border
            height="560"
            style="width: 100%;">
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
              width="245">
            <template slot-scope="scope">
              <el-button
                  size="mini"
                  type="success"
                  v-if="payOffID.indexOf(scope.row.id) === -1"
                  @click="addPayOff(scope.row)">加入结算</el-button>
              <el-button
                  size="mini"
                  type="danger"
                  v-if="payOffID.indexOf(scope.row.id) !== -1"
                  @click="deletePayOff(scope.row)">取消</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="shopBag-right">
        <div class="shopBag-clear">
          <span style="float: left;margin-left: 10px;font-weight: 1000">结算明细</span>
          <div class="shopBag-clearImg">
            <el-image
                v-for="item in payOffImg" :key="item"
                style="width: 50px;height: 50px;border-radius: 10px;margin-top: 5px"
                :src=imgBarsUrl+item
                fit="fit"></el-image>
          </div>
          <div style="display: flex;">
            <span style="font-weight: 1000;font-size: 20px;
                  margin-left: 10px">合计:</span>
            <span style="font-weight: 600;float: right;
                  width: fit-content;margin-right: 10px;margin-left: 200px;
                  font-size: 20px;color: #FF5300">¥ {{payOffSum.toFixed(2)}}</span>
          </div>
          <el-button style="background-color: #FF5300;color: #F0EFF2;
                      width: 80%;margin-top: 20px;border-radius: 10px;
                      font-weight: 1000;font-size: 15px"
                      @click="payOff">结算</el-button>
        </div>
      </div>
    </div>
  </el-main>
</template>

<script>
import signUpStatus from "@/utils/status";

export default {
  name: "ShopBag",
  props:{

  },
  data(){
    return{
      checkAll:false,
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
          let len = res.data.length;
          for(let i=0;i<len;i++)
          {
            if(res.data[i].status === 0)this.tableData.push(res.data[i])
          }
        }
      }).catch();
    },

    addPayOff(row){
      this.payOffID.push(row.id);
      this.payOffImg.push(row.img1);
      this.payOffSum+=row.totalPrice;
    },
    deletePayOff(row){
      let idx = this.payOffID.indexOf(row.id);
      this.payOffID.splice(idx,1);
      this.payOffImg.splice(idx,1);
      this.payOffSum-=row.totalPrice;
    },

    payOff(){
      if(this.payOffID.length === 0)return;
      this.$request.post('/updateOrder/payOff',this.payOffID,).then((res)=>{
        if(res.code === 0){
          this.$message.error('结算失败：'+res.msg);
        }else{
          this.$message.success('结算成功');
          this.initShopOrders();
        }
      }).catch((err)=>{
        this.$message.error('异常'+err);
      });
    },
  },
  created() {
    this.initShopOrders();
  }
}
</script>

<style scoped>
.shopBag{
  display: flex;
  width: 100%;
  height: 100%;
}
.shopBag-left{
  width: 60%;
}
.shopBag-right{
  width: 40%;
}
.shopBag-clear{
  overflow: hidden;
  width: 400px;
  height: 350px;
  border-radius: 8px;
  border: 1px solid #F0EFF2;
}
.shopBag-clearImg{
  width: 100%;
  height: 230px;
  margin-top: 20px;
}
</style>