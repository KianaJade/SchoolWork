<template>
  <div class="managePage-header">
    <el-breadcrumb separator="/" style="font-size: 20px;color: #cccccc;font-weight: 500">
      <el-breadcrumb-item><a href="/manage">首页</a></el-breadcrumb-item>
      <el-breadcrumb-item><a href="/manage/tag">类目管理</a></el-breadcrumb-item>
      <el-breadcrumb-item>活动详情</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script>
export default {
  name: "ManageHeader"
}
</script>

<style scoped>
.managePage-header{
  display: flex;
  width: 100%;
  height: fit-content;
  align-items: center;
  //box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);;
}
</style>