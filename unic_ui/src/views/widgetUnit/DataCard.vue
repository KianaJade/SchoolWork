<template>
  <div class="dataCard">
    <el-row class="dataCardLine1">
      <span style="float: left;">{{ name1 }}</span>
    </el-row>
    <el-row class="dataCardLine2">
      <span style="float: left;">{{number1}}</span>
    </el-row>
    <el-row class="dataCardLine3">
      <span style="float: left;">{{name2}}</span>
      <span class="dataCardLine3Number">{{number2}}</span>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "DataCard",
  props:{
    name1:{
      type:String,
      required:true,
    },
    number1:{
      type:Number,
      default: 0,
    },
    name2:{
      type:String,
      default:'昨日',
    },
    number2:{
      type:Number,
      default:0,
    }
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
.dataCard{
  background-color: white;
  height: 15vh;
  border-radius: 20px;
  overflow: hidden;
}
.dataCardLine1{
  width: 210px;
  height: 4vh;
  margin-left: 10px;
}
.dataCardLine2{
  width: 210px;
  height: 7vh;
  margin-left: 10px;
}
.dataCardLine3{
  width: 210px;
  height: 4vh;
  margin-left: 10px;
}
.dataCardLine3Number{
  float: left;
  margin-left: 5px;
  margin-top: 10px;
  font-size: 10px;
}
</style>