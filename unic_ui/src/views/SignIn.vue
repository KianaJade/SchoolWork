<template>
  <div class="signInPage">
    <div class="signInPage-2">
      <el-form class="signInForm">
        <el-col style="width: 20vw;height: 50vh">
          <el-image
              style="width: 15vw; height: 45vh;margin-top: 18px"
              src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
              fit="fill"></el-image>
        </el-col>
        <el-col style="width: 30vw;height: 50vh;">
          <el-row style="height: 15vh">
            <el-form-item>
              <el-input v-model="account" @input="accountInput" minlength="11" maxlength="11" class="accountInput" suffix-icon="el-icon-user" placeholder="手机号"></el-input>
            </el-form-item>
          </el-row>
          <el-row style="height: 8vh">
            <el-form-item>
              <el-input v-model="password" @input="passwordInput" show-password maxlength="40" class="passwordInput" placeholder="密码"></el-input>
            </el-form-item>
          </el-row>
          <el-row style="height: 8vh">
            <el-form-item>
              <el-input v-model="rePassWord" @input="rePasswordInput" show-password maxlength="40" class="passwordInput" placeholder="确认密码"></el-input>
            </el-form-item>
          </el-row>
          <el-row>
            <el-button class="signInButton" @click="signIn">注册</el-button>
          </el-row>
        </el-col>
      </el-form>
    </div>
  </div>
</template>

<script>
//import axios from "axios";
import {accountInputCheck} from "@/utils/inputCheck";
import {passwordInputCheck} from "@/utils/inputCheck";
import signUpStatus, {frontPageUrl} from "@/utils/status";
export default {
  name: "SignIn",
  data(){
    return{
      account:'',
      password:'',
      rePassWord:'',
    }
  },
  methods:{
    formatCheck(){
      if(this.account === '' || this.password === '' || this.rePassWord === '')return false;
      if(this.password !== this.rePassWord){
        this.$message.error('两次密码不一致，请检查！');
        return false;
      }
      if(this.account.length !== 11){
        this.$message.error('手机号码格式错误');
        return false;
      }
      return true;
    },

    signIn(){
      if(!this.formatCheck())return;
      this.$request.post('/signIn',{
        phone: this.account,
        password: this.password,
      }).then((res)=>{
        if(res.code === 0){
          this.$message.error(res.msg);
        }else{
          sessionStorage.setItem('msToken',res.data.token)
          this.$message.success('注册成功!');
          signUpStatus.signUpStatusTrue();
          signUpStatus.setPhone(this.account);
          window.location.replace(frontPageUrl);
        }
      }).catch((err)=>{
        this.$message.error('注册请求失败:'+err);
      });
    },

    accountInput(){
      this.account = accountInputCheck(this.account);
    },

    passwordInput(){
      this.password = passwordInputCheck(this.password);
    },

    rePasswordInput(){
      this.rePassWord = passwordInputCheck(this.rePassWord);
    },

  },
  created() {

  }
}
</script>

<style scoped>
.signInPage{
  width: 100%;
  height: 686px;
  background-color: #F0EFF2;
  overflow: hidden;
}
.signInPage-2{
  margin-top: 100px;
}
.signInForm{
  border-radius: 30px;
  background-color: white ;
  width: 50vw;
  height: 50vh;
  margin-left: 400px;
  margin-top: 130px;
}
.accountInput{
  background-color: #F0EFF2;
  width: 20vw;
  margin-top: 7vh
}
.passwordInput{
  width: 20vw;
  margin-top: 1vh;
}
.signInButton{
  background-color: #F74E00;
  width: 20vw;
  height: 7vh;
  margin-top: 4vh;
  color: #F0EFF2;
  font-weight: 1000;
  font-size: 19px;
}
</style>