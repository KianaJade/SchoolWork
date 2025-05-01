<template>
  <div class="goodsManageCard">
    <div class="gMCName">
      <div class="gMCNameOrder" style="">{{order}}</div>
      <div class="gMCGoodsImg"><el-image :src=imgSrc fit="fit"></el-image></div>
      <div class="gMCNameDes">
        <div class="gMCND-word">{{name}}</div>
        <div class="gMCND-ID">ID:{{goodsID}}</div>
      </div>
    </div>
    <div class="gMCPrice">
      <div class="gMCPrice-sign">¥</div>
      <div class="gMCPrice-number">{{price}}</div>
    </div>
    <div class="gMCStock">
      <div class="gMCStock-number">{{stock}}</div>
    </div>
    <div class="gMCSaleNum">
      {{sellNumber}}
    </div>
    <div class="gMCCreateTime">
      <div class="gMCCreateTime-status">
        出售中
      </div>
    </div>
    <div class="gMCOperation">
      <div class="gMCOperation-1">编辑</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "GoodsManageCard",
  props:{
    imgSrc:{
      type:String,
      required:true,
      default:'',
    },
    order:{
      type:Number,
      default:0,
    },
    name:{
      type:String,
      required: true,
      default:'商品名称',
    },
    goodsID:{
      type:Number,
      required:true,
      default:-99999,
    },
    price:{
      type:Number,
      required:true,
      default:-99999
    },
    stock:{
      type:Number,
      required:true,
      default:-99999
    },
    sellNumber:{
      type:Number,
      required:true,
      default:0
    },
    createTime:{
      type:String,
      default: '1999-12-31',
    },

  },
  data(){
    return{

    }
  },
  methods:{
    initGoodsManageCard(){

    },
  },
  created() {
    this.initGoodsManageCard();
  },
}
</script>

<style scoped>
.goodsManageCard{
  display: flex;
  width: 100%;
  height: 92%;
}
.gMCName{
  display: flex;
  width: 370px;
}
.gMCNameOrder{
  width: 40px;
  height: 40px;
  margin-top: 40px;
}
.gMCGoodsImg{
  width: 80px;
  height: 80px;
  overflow: hidden;
  border-radius: 20px;
  margin-left: 10px;
  margin-top: 20px;
}
.gMCNameDes{
  width: 220px;
  height: 100%;
  margin-left: 10px;
}
.gMCND-word{
  width: fit-content;
  height: 40px;
  margin-top: 20px;
  float: left;
}
.gMCND-ID{
  width: fit-content;
  height: 40px;
  color: #cccccc;
  font-size: 15px;
}
.gMCPrice{
  display: flex;
  width: 100px;
  margin-left: 20px;
}
.gMCPrice-sign{
  width: fit-content;
  margin-top: 30px;
}
.gMCPrice-number{
  width: fit-content;
  margin-top: 30px;
  margin-left: 10px;
}
.gMCStock{
  width: 80px;
  margin-left: 50px;
}
.gMCStock-number{
  width: fit-content;
  margin-top: 30px;
}
.gMCSaleNum{
  width: fit-content;
  margin-top: 30px;
  margin-left: 55px;
}
.gMCCreateTime{
  width: 180px;
  margin-left: 150px;
}
.gMCCreateTime-time{
  width: fit-content;
  margin-top: 30px;
}
.gMCCreateTime-status{
  width: fit-content;
  background-color: #03FA71;
}
.gMCOperation{
  width: 130px;
  margin-left: 70px;
}
.gMCOperation-1{
  width: fit-content;
  margin-top: 30px;
  color: deepskyblue;
  cursor: pointer;
}
.gMCOperation-1:hover{
  border-bottom: 2px solid #cccccc;
}
</style>