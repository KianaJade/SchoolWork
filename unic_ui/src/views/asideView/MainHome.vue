<template>
  <el-container>
    <el-main style="height: fit-content;width: 90vw;margin-left: 10vw">
      <el-col class="col-container">
        <el-row v-infinite-scroll="loadGoods" infinite-scroll-disabled="loading">
          <el-col class="colGoods" :span="3" v-for="(item) in goods" :key="item.id">
            <GoodsCard
                :id="item.id"
                :img-url="item.goodsImg1"
                :name="item.name"
                :price="item.price"
                :sell-num="item.sellNumber"
            />
          </el-col>
        </el-row>
      </el-col>
    </el-main>
<!--    <el-footer class="mainHomeFooter">Footer</el-footer>-->
  </el-container>
</template>

<script>
import GoodsCard from "@/views/widgetUnit/GoodsCard.vue";

export default {
  name: "MainHome",
  components:{
    GoodsCard
  },
  data(){
    return{
      myBaseUrl:'http://localhost:7000',
      rowNum:0,
      cardNum:0,
      goods:[],
    }
  },
  methods:{
    loadGoods(){
      this.getGoods();
    },

    getGoods(){
      this.$request.get('/getGoods',{
        params:{
          start:this.cardNum,
          limit:20,
        }
      }).then((res)=>{
        if(res.code === 0){
          this.$message.error('获取失败:'+res.msg);
        }else{
          this.cardNum+=res.data.length;
          this.rowNum+=Math.ceil(res.data.length / 5);
          this.goods = [...this.goods, ...res.data];
        }
      }).catch((err)=>{
        this.$message.error('异常：'+err);
      });
    },

    initPage(){
      this.getGoods();
    },

  },
  created() {
    this.initPage();
  }
}
</script>

<style scoped>
.colGoods{
  height: 45vh;
  width: 14vw;
  margin-top: 5vh;
  margin-left: 2vw;
  border-radius: 10px;
}
.mainHomeFooter{
  height: 20vh;
  width: 90vw;
  background-color: cornflowerblue;
  margin-left: 10vw;
}
</style>