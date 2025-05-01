<template>
  <el-main class="businessManage">
    <el-form class="shopForm" :model="form" label-width="80px">
      <el-form-item style="font-size: 100px" label="店铺头像">
        <div style="margin-top: 20px">
          <el-col :span="5">
            <div @click="handlePictureCardPreview">
              <el-avatar style="margin-left: 0;cursor: pointer" class="shopAvtar"
                         :key="avatarKey"
                         :src="form.avatarSrc"></el-avatar>
            </div>
          </el-col>
          <el-col style="height: 50px;width: 50px;margin-top: 20px">
            <el-upload accept=".jpg,.png" :show-file-list="false"
                       :on-change="handleChangeFile"
                       class="upload-demo" :file-list="imgList"
                       multiple :auto-upload="false" action="http://localhost:8080/image/upload"
                       >
              <el-button size="small" type="primary" plain>修改头像</el-button>
            </el-upload>
            <el-dialog :visible.sync="dialogVisible">
              <img width="100%" :src="dialogImageUrl" alt="">
            </el-dialog>
          </el-col>
        </div>
      </el-form-item>

      <el-form-item label="店铺名称">
        <el-col :span="5">
          <el-input  v-if="isReadOnly[0]" readonly v-model="form.name" style="width: 400px;" placeholder="财源广进（默认名称）"></el-input>
          <el-input  v-else clearable v-model="newForm.name" style="width: 400px;" placeholder="请输入新的店铺名称"></el-input>
        </el-col>
        <el-button  @click="changeForm(0,!isReadOnly[0],1)" type="primary" plain>{{ isReadOnly[0]? '修改名称' : '保存名称' }}<i class="el-icon-edit el-icon--right"></i></el-button>
        <el-button @click="changeForm(0,true,2)"  v-if="!isReadOnly[0]" type="danger" plain>取消</el-button>
      </el-form-item>

      <el-form-item label="注册时间">
        <el-col :span="5">
          <el-date-picker readonly type="date" placeholder="日期" v-model="form.date1" style="width: 100%;"></el-date-picker>
        </el-col>
        <el-col class="line" :span="2">-</el-col>
        <el-col :span="5">
          <el-time-picker readonly placeholder="时间" v-model="form.date2" style="width: 100%;"></el-time-picker>
        </el-col>
      </el-form-item>

      <el-form-item label="店铺地址">
        <el-col :span="5">
          <el-input  v-if="isReadOnly[1]" readonly placeholder="M78星云光之国(默认)" style="width: 400px" maxlength="30" show-word-limit v-model="form.place"></el-input>
          <el-input  v-else clearable v-model="newForm.place" style="width: 400px;" maxlength="30" show-word-limit placeholder="请输入新的店铺地址"></el-input>
        </el-col>
        <el-button  @click="changeForm(1,!isReadOnly[1],1)" type="primary" plain>{{ isReadOnly[1]? '修改地址' : '保存地址' }}<i class="el-icon-edit el-icon--right"></i></el-button>
        <el-button @click="changeForm(1,true,2)"  v-if="!isReadOnly[1]" type="danger" plain>取消</el-button>
      </el-form-item>

      <el-form-item label="简介">
        <el-col :span="5">
          <el-input v-if="isReadOnly[2]"
                   :rows="4" type="textarea"
                    maxlength="40" show-word-limit
                    v-model="form.briefInfo"
                    placeholder="暂无" readonly
                    style="width: 400px;height: 100px"></el-input>
          <el-input  v-else clearable v-model="newForm.briefInfo" :rows="4" type="textarea"
                     style="width: 400px;" maxlength="40" show-word-limit placeholder="请输入新的店铺简介"></el-input>

        </el-col>
        <el-button  @click="changeForm(2,!isReadOnly[2],1)" type="primary" plain>{{ isReadOnly[2]? '修改简介' : '保存简介' }}<i class="el-icon-edit el-icon--right"></i></el-button>
        <el-button @click="changeForm(2,true,2)"  v-if="!isReadOnly[2]" type="danger" plain>取消</el-button>
      </el-form-item>

    </el-form>
  </el-main>
</template>

<script>
import signUpStatus from "@/utils/status";
import axios from "axios";

export default {
  name: "BusinessManage",
  data(){
    return {
      avatarKey:0,
      dialogImageUrl: '',
      dialogVisible: false,
      imgList:[],
      isReadOnly:[true,true,true],
      form:{
        name:'',
        avatarSrc:'/goodsImages/testGoodsImage.png',
        date1:'9999-12-12',
        date2:new Date(2021, 1, 2, 12, 12, 12),
        place:'',
        briefInfo:'我们是致力于学习用品的专卖店！\n三十年时间，打磨最好品质！！\n让我们成为你的避风港！！！',
      },
      newForm:{
        name:'',
        place:'',
        briefInfo:'',
      },
    }
  },
  methods:{
    initBusinessManage(){
      signUpStatus.getShopInfo(this);
      this.form.name = signUpStatus.shop.name;
      if(signUpStatus.shop.img)this.form.avatarSrc =this.$baseUrl+ '/download/' + signUpStatus.shop.img;
      this.form.briefInfo = signUpStatus.shop.des;
      this.form.date1 = signUpStatus.shop.time.substring(0,signUpStatus.shop.time.indexOf('T'));
      this.form.date2 = signUpStatus.shop.time;
      this.form.place = signUpStatus.shop.address;
    },

    handlePictureCardPreview() {
      this.dialogImageUrl = this.form.avatarSrc;
      this.dialogVisible = true;
    },
    handleChangeFile (file) {
        this.$confirm('是否确认修改头像', '提示', {
          confirmButtonText: '确认',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(()=>{
          let formData = new FormData();
          signUpStatus.getShopInfo(this);
          formData.append('id',signUpStatus.shop.id);
          formData.append('imgFile',file.raw);
          axios({
            method: 'post',
            url:this.$baseUrl+ '/shop/updateShopInfo/avatar',
            headers: {
              //'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryVCFSAonTuDbVCoAN',
              'token':localStorage.getItem('msToken')
            },
            data: formData,
          }).then((res)=>{
            if(res.code === 0){
              this.$message.error('头像修改失败');
            }else{
              this.$message.success('头像修改成功');
              this.form.avatarSrc =this.$baseUrl+ '/download/' + res.data.data.img;
              this.avatarKey++;
            }
          }).catch((err)=>{
            this.$message.error('异常：'+err)
          });
        }).catch(()=>{

        });

      const isLt2M =( file.size / 1024 / 1024 )< 5
      if (!isLt2M) {
        this.$refs.upload.clearFiles()
        this.$message({
          message: '上传文件大小不能超过 2MB!',
          type: 'warning'
        })
        return false
      }
    },

    changeForm(i,b,buttonIndex){
      this.$set(this.isReadOnly, i, b);
      if(b === false || buttonIndex === 2){
        this.newForm.name = this.form.name;
        this.newForm.place = this.form.place;
        this.newForm.briefInfo = this.form.briefInfo;
        return;
      }//之后为保存操作
      this.$confirm('是否确认修改?', '提示', {
        confirmButtonText: '保存修改',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(()=>{
        let t = '';
        if(i === 0){//名称
          t = this.form.name;
          this.form.name = this.newForm.name;
        }else if(i === 1){//地址
          t = this.form.place;
          this.form.place = this.newForm.place;
        }else if(i === 2){//简介
          t = this.form.briefInfo;
          this.form.briefInfo = this.newForm.briefInfo;
        }
        signUpStatus.getShopInfo(this);
        this.$request.post('/shop/updateShopInfo/baseInfo',{
          id:signUpStatus.shop.id,
          name:this.form.name,
          des:this.form.briefInfo,
          address:this.form.place,
        }).then((res)=>{
          if(res.code === 0){
            this.$message.error('修改失败：'+res.msg);
            if(i === 0){//名称
              this.form.name = t;
            }else if(i === 1){//地址
              this.form.place = t;
            }else if(i === 2){//简介
              this.form.briefInfo = t;
            }
          }else{
            this.$message.success('修改成功');
          }
        }).catch((err)=>{
          this.$message.error('异常：'+err);
        });
      }).catch(()=>{
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },

  },
  created() {
    this.initBusinessManage();
  }
}
</script>

<style scoped>
.businessManage{
  width: 85vw;
  height: 86vh;
  margin-top: -15px;
  //background-color: #F0EFF2;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12);
}
.shopForm{
  width: 65vw;
  height: 80vh;
  margin-top: -15px;
  margin-left: 10vw;
  background-color: #F0EFF2;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12);
}
.shopAvtar{
  width: 80px;
  height: 80px;
}
</style>