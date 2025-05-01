<template>
  <el-menu-item class="littleMenuItem" index="1"
                :class="{'littleMenuItem-sel':selCss}" @mouseenter="asdf">
      <el-row style="height: 20px;width: 70px">
        <i style="margin-left: -30px" :class="iconName"></i>
      </el-row>
      <el-row style="height: 50px;width: 70px">
        <span style="margin-left: -35px">{{name}}</span>
      </el-row>
  </el-menu-item>
</template>

<script>
export default {
  name: "LittleMenu",
  props:{
    name:{
      type:String,
      required:true,
    },
    iconName:{
      type:String,
      required: true,
    },
    index:{
      type:Number,
      default:0,
    },
    selIndex:{
      type:Number,
      default: 1,
    },
  },
  data(){
    return{
      selCss:false,
    }
  },
  methods:{
    asdf(){
      this.selCss = this.index === this.selIndex;
    }
  }
}
</script>

<style scoped>
.littleMenuItem{
  height: 70px;
  width: 70px;
  color:black;
}
.littleMenuItem:hover{
  background-color: #00BFFF;
}
.littleMenuItem-sel{
  background-color:deepskyblue;
}
</style>