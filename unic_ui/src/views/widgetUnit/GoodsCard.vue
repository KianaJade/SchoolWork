<template>
  <div @click="openItemPage" class="goodsCard">
    <div class="goodsImage">
      <el-image
          :src="myBaseUrl+imgUrl"
          fit="fit"></el-image>
    </div>
    <div class="goodsDescription">{{name}}</div>
    <div style="height: 5vh;width: 13vw;">
      <div style="float: left;margin-left: 2px">
        <span style="font-size: 15px;color: #e5423c;">¥</span>
        <span style="font-size: 20px;color: #e5423c;">{{price}}</span>
        <span style="font-size: 17px;color: #cccccc;margin-left: 2px">销量</span>
        <span style="font-size: 17px;color: #cccccc">{{sellNum}}</span>
      </div>
    </div>
  </div>
</template>

<script>
import signUpStatus from "@/utils/status";

export default {
  name: "GoodsCard",
  props:{
    id:{
      type:Number,
      required:true,
    },
    imgUrl:{
      type:String,
      required: true,
    },
    name:{
      type:String,
      default:'获取失败',
    },
    price:{
      type:Number,
      default: 99999,
    },
    sellNum:{
      type:Number,
      default:0,
    }
  },
  data(){
      return{
        myBaseUrl:'http://localhost:8080/download/',
      }
  },
  methods:{
    openItemPage(){
      if(signUpStatus.status === false){
        this.$message.error('请登录后再进行操作')
        return
      }
      localStorage.setItem('goodsID',this.id.toString());
      window.open('http://localhost:7000'+'/shopping','_blank');
    },
  }
}
</script>

<style scoped>
.goodsCard{
  border-radius: 10px;
  width: 13vw;
}
.goodsImage{
  overflow: hidden;
  border-radius: 10px;
  height: 30vh;
  width: 13vw;
  background-color: white;
}
.goodsImage el-image{
  width: 100%;
  height: 100%;
  object-fit: cover;
}
.goodsCard:hover{
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
  border: 2px solid red;
  cursor: pointer;
}
.goodsDescription{
  height: 10vh;
  color: #1A1A1A;
  font-weight: 500;
  line-height: 24px;
}
</style>