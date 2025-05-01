<template>
<!--  <el-main class="goodsManage"></el-main>-->
    <el-tabs  type="border-card" class="goodsTab" v-model="activeName">
      <el-tab-pane :label=num1 name="first">
        <div class="goodsSearch">
          <el-input class="goodsNameInput"><div slot="prepend">商品标题</div></el-input>
          <el-input class="goodsIDInput"><div slot="prepend">商品ID</div></el-input>
          <el-button class="searchIcon" icon="el-icon-search" circle></el-button>
        </div>

        <div class="goodsBrowse">
          <div class="solidTop">
            <div class="solidTopList1">商品名称</div>
            <div class="solidTopList2">价格</div>
            <div class="solidTopList3">库存</div>
            <div class="solidTopList4">销量</div>
            <div class="solidTopList5">状态</div>
            <div class="solidTopList6">操作</div>
          </div>

          <div class="solidDown">
            <div class="infinite-list" v-infinite-scroll="load" style="overflow:auto">
              <div v-for="(k,i) in goods.length" :key="k" class="infinite-list-item">
                <GoodsManageCard :img-src=imgBarsUrl+goods[i].goodsImg1 :order=i+1
                                 :goods-i-d=goods[i].id :name=goods[i].name
                                 :price=goods[i].price :sell-number=goods[i].sellNumber
                                 :stock=goods[i].number></GoodsManageCard>
                <hr style="width: 99%;color: #F0EFF2;margin-left: 0"/>
              </div>
            </div>
          </div>
        </div>
      </el-tab-pane>
<!--      <el-tab-pane label="出售中的商品" name="second"></el-tab-pane>-->
<!--      <el-tab-pane label="未上架的商品" name="third"></el-tab-pane>-->
      <el-tab-pane label="创建新商品" name="forth">
        <div class="goodsCreate">
          <GoodsCreateCard></GoodsCreateCard>
        </div>
      </el-tab-pane>
    </el-tabs>

</template>

<script>
import GoodsManageCard from "@/views/widgetUnit/GoodsManageCard.vue";
import GoodsCreateCard from "@/views/widgetUnit/GoodsCreateCard.vue";
import signUpStatus from "@/utils/status";
export default {
  name: "GoodsManage",
  components:{
    GoodsManageCard,
    GoodsCreateCard,
  },
  data(){
    return{
      imgBarsUrl:"http://localhost:8080/download/",
      activeName:'first',
      num1:'全部商品',
      num2:2,
      count: 5,
      goods:[],
    }
  },
  methods:{
    load () {
      //this.count += 2;
    },

    getAllGoods(){
      this.$request.get('/shop/getShopGoods',{
        params:{
          phone:signUpStatus.getPhone(),
        }
      }).then((res)=>{
        if(res.code === 0){
          this.$message.error('初始化失败'+res.msg);
        }else{
          this.goods = res.data;
        }
      }).catch((err)=>{
        this.$message.error('异常：'+err);
      });
    },
  },
  created() {
    this.getAllGoods();
  }
}
</script>

<style scoped>
.goodsManage{
  width: 85vw;
  height: 86vh;
  margin-top: -15px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12);
}
.goodsTab{
  width: 85vw;
  height: 90vh;
  margin-top: -20px;
  margin-left: 32px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12);
  //border-left: 2px solid deepskyblue;
  //border-right: 2px solid deepskyblue;
}
.goodsCreate{
  display: flex;
  overflow: hidden;
}
.goodsSearch{
  display: flex;
  width: 85vw;
  height: 10vh;
}
.goodsBrowse{
  width: 85vw;
  height: 80vh;
}
.goodsNameInput{
  left: 0;
  width: 250px;
  height: 40px;
  background-color: #F0EFF2;
}
.goodsIDInput{
  left:20px;
  width:250px;
  height: 40px;
}
.searchIcon{
  margin-left: 40px;
  width: 40px;
  height: 40px;
}
.solidTop{
  display: flex;
  width: 97%;
  height: 20px;
  padding: 3px;
  border-top-left-radius: 10px;
  border-top-right-radius: 10px;
  background-color: #F0EFF2;
}
.solidDown{
  display: flex;
  overflow: hidden;
  width: 97.5%;
  height: 80%;
  border-bottom-left-radius: 10px;
  border-bottom-right-radius: 10px;
}
.solidTopList1{
  width: 180px;
}
.solidTopList2{
  width: 80px;
  margin-left: 200px;
}
.solidTopList3{
  width: 80px;
  margin-left: 50px;
}
.solidTopList4{
  width: 80px;
  margin-left: 50px;
}
.solidTopList5{
  width: 80px;
  margin-left: 100px;
}
.solidTopList6{
  width: 100px;
  margin-left: 150px;
}
.infinite-list{
  width: 100%;
  height: 100%;
  margin-top: 0;
}
.infinite-list-item{
  overflow: hidden;
  width: 100%;
  height: 35%;
}
</style>