<template>
  <div class="userPage">
    <div style="margin-left: 30px">
      <el-descriptions class="margin-top" title="用户个人信息" :column="1" size='medium' border>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-picture-outline"></i>
            头像
          </template>
          <div style="cursor: pointer">
            <el-col :span="5">
              <div @click="handlePictureCardPreview">
                <el-avatar class="shopAvtar"
                           :key="avatarKey" size="large"
                           :src=imgBaseUrl+img></el-avatar>
              </div>
            </el-col>
            <template>
              <el-col style="height: 30px;width: 30px;margin-top: 20px">
                <el-upload accept=".jpg,.png" :show-file-list="false"
                           :on-change="handleChangeFile"
                           class="upload-demo" :file-list="imgList"
                           multiple :auto-upload="false" action="http://localhost:8080/image/upload">
                  <el-button size="small" type="primary" plain>修改头像</el-button>
                </el-upload>
                <el-dialog :visible.sync="dialogVisible">
                  <img width="100%" :src="dialogImageUrl" alt="">
                </el-dialog>
              </el-col>

            </template>
            </div>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            用户名
          </template>
          <span v-if="!isInput[0]">{{name}}</span>
          <el-input v-model="newInfo.name" v-if="isInput[0]" placeholder="请输入新的名称" maxlength="40" show-word-limit clearable style="width: fit-content"></el-input>
          <el-button @click="changeBaseInfo(0,!isInput[0],1)" style="margin-left: 10px" type="primary"
                     plain size="small">{{!isInput[0]? '修改名称':'保存修改'}}</el-button>
          <el-button @click="changeBaseInfo(0,false,2)" v-if="isInput[0]" type="success" plain>取消</el-button>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            性别
          </template>
          <el-radio v-if="!isInput[1]" disabled v-model="gender" label="M">男</el-radio>
          <el-radio v-if="!isInput[1]" disabled v-model="gender" label="W">女</el-radio>
          <el-radio v-if="isInput[1]" v-model="newInfo.gender" label="M">男</el-radio>
          <el-radio v-if="isInput[1]" v-model="newInfo.gender" label="W">女</el-radio>
           <el-button @click="changeBaseInfo(1,!isInput[1],1)" style="margin-left: 10px" type="primary"
                     plain size="small">{{!isInput[1]? '修改性别':'保存修改'}}</el-button>
          <el-button @click="changeBaseInfo(1,false,2)" v-if="isInput[1]" type="success" plain>取消</el-button>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            地址
          </template>
          <span v-if="!isInput[2]">{{userAddress}}</span>
          <el-input v-model="newInfo.address" v-if="isInput[2]" placeholder="请输入新的地址" maxlength="40" show-word-limit clearable style="width: fit-content"></el-input>
          <el-button @click="changeBaseInfo(2,!isInput[2],1)" style="margin-left: 10px" type="primary"
                     plain size="small">{{!isInput[2]? '修改地址':'保存修改'}}</el-button>
          <el-button @click="changeBaseInfo(2,false,2)" v-if="isInput[2]" type="success" plain>取消</el-button>
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-user"></i>
            生日
          </template>
          <el-date-picker  v-if="!isInput[3]"
              v-model="birthday"
              type="date" readonly
              placeholder="选择日期">
          </el-date-picker>
          <el-date-picker  v-if="isInput[3]"
              v-model="newInfo.birthday"
              type="date"
              placeholder="选择日期">
          </el-date-picker>
          <el-button @click="changeBaseInfo(3,!isInput[3],1)" style="margin-left: 10px" type="primary"
                     plain size="small">{{!isInput[3]? '修改生日':'保存修改'}}</el-button>
          <el-button @click="changeBaseInfo(3,false,2)" v-if="isInput[3]" type="success" plain>取消</el-button>
        </el-descriptions-item>
      </el-descriptions>
    </div>
  </div>
</template>

<script>
import signUpStatus from "@/utils/status";
import axios from "axios";

export default {
  name: "UserView",
  data(){
    return{
      imgBaseUrl:this.$baseUrl+ '/download/',
      name:'',
      phone:'',
      gender:'',
      img:'3a4187fce7884f0b9748e8b99cfca5c7.jpg',
      userAddress:'',
      birthday:new Date(),
      registerDate:new Date(),

      avatarKey:0,
      dialogImageUrl: '',
      dialogVisible: false,
      imgList:[],

      isInput:[false,false,false,false,false,false],
      newInfo:{
        name:'',
        gender:'',
        birthday:new Date(),
        address:'',
      }
    }
  },
  methods:{
    initUserPage(){
      this.$request.get('/getUserInfo',{
        params:{
          phone:signUpStatus.phone,
        }
      }).then((res)=>{
        if(res.code === 0){
          this.$message.warning('初始化失败');
        }else{
          this.img = res.data.img;
          this.name = res.data.name;
          this.phone = res.data.phone;
          this.birthday = res.data.birthday;
          this.userAddress = res.data.address;
          this.gender = res.data.gender;
          this.avatarKey ++;
        }
      }).catch((err)=>{
        this.$message.error('异常：'+err)
      });
    },

    handlePictureCardPreview() {
      this.dialogImageUrl = this.imgBaseUrl+this.img;
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
        formData.append('phone',signUpStatus.phone);
        formData.append('file',file.raw);
        axios({
          method: 'post',
          url:this.$baseUrl+ '/updateUserAvatar',
          headers: {
            'token':localStorage.getItem('msToken')
          },
          data: formData,
        }).then((res)=>{
          if(res.code === 0){
            this.$message.error('头像修改失败');
          }else{
            this.$message.success('头像修改成功');
            this.img =res.data.data.img;
            this.avatarKey++;
          }
        }).catch((err)=>{
          this.$message.error('异常：'+err);
        });
      }).catch(()=>{
        this.$message.success('已取消');
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

    changeBaseInfo(i,opt,buttonIndex){
      this.$set(this.isInput, i, opt);
      if(buttonIndex === 2 || opt === true){
        this.newInfo.name = this.name;
        this.newInfo.birthday = this.birthday;
        this.newInfo.gender = this.gender;
        this.newInfo.address = this.userAddress;
        return;
      }
      this.$confirm('是否确认修改?', '提示', {
        confirmButtonText: '保存修改',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(()=>{
        let t;
        if(i === 0){//名称
          t = this.name;
          this.name = this.newInfo.name;
        }else if(i === 1){//性别
          t = this.gender;
          this.gender = this.newInfo.gender;
        }else if(i === 2){//地址
          t = this.userAddress;
          this.userAddress = this.newInfo.address;
        }else if(i === 3){
          t = this.birthday;
          this.birthday = this.newInfo.birthday;
        }
        this.$request.post('/updateUserBaseInfo',{
          phone: signUpStatus.getPhone(),
          name: this.name,
          gender: this.gender,
          birthday: this.birthday,
          address: this.userAddress,
        }).then().catch((err)=>{
          this.$message.error(err);
          if(i === 0){//名称
            this.name = t;
          }else if(i === 1){//简介
            this.gender = t;
          }else if(i === 2){//地址
            this.userAddress = t;
          }else if(i === 3){
            this.birthday = t
          }
        });
      }).catch(()=>{
        this.$message.warning('已取消')
      });
    },
  },
  created() {
    this.initUserPage();
  }
}
</script>

<style scoped>
.userPage{
  display: flex;
  height: 100%;
  width: 100%;
  margin-left: 130px;
}
</style>