<template>
  <div class="managePage">
    <ManageHeader></ManageHeader>
    <el-divider style="margin-top: 0"></el-divider>
    <router-view style="margin-top: 0"></router-view>
  </div>
</template>

<script>
import ManageHeader from "@/views/managePage/ManageHeader.vue";
export default {
  name: "ManageView",
  components: {ManageHeader,},
  data(){
    return{
      activeName:'first',
    }
  },
  methods:{

  }
}
</script>

<style scoped>
.managePage{
  overflow: hidden;
  width: 100%;
  height: 100%;
  //margin-top: -10px;
  //margin-left: -10px;
}

</style>