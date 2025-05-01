<template>
  <div class="loginPage">
    <el-container style="overflow: hidden">
      <el-header style="height: 20vh;">
      </el-header>

      <el-main style="height: 80vh;overflow: hidden">
        <el-form class="loginForm">
          <el-col style="width: 20vw;height: 50vh">
            <el-image
                style="width: 15vw; height: 45vh;margin-top: 18px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="fill"></el-image>
          </el-col>
          <el-col style="width: 30vw;height: 50vh;">
            <el-row style="height: 15vh">
              <el-form-item>
                <el-input @input="accountInput" minlength="11" maxlength="11" v-model="account" class="accountInput" suffix-icon="el-icon-user" placeholder="手机号"></el-input>
              </el-form-item>
            </el-row>
            <el-row style="height: 15vh">
              <el-form-item>
                <el-input @input="passwordInput" minlength="1" maxlength="40" v-model="password" show-password class="passwordInput" placeholder="密码"></el-input>
              </el-form-item>
            </el-row>
            <el-row>
              <el-button @click="login" class="loginButton">登录</el-button>
            </el-row>
          </el-col>
        </el-form>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import {accountInputCheck} from "@/utils/inputCheck";
import {passwordInputCheck} from "@/utils/inputCheck";
import signUpStatus, {frontPageUrl} from "@/utils/status";

export default {
  name: "LoginView",
  data(){
    return{
      account:'',
      password:''
    }
  },
  methods:{
    login(){
      this.$request.post('/signUp',
          {
            phone:this.account,
            password: this.password
          }
      ).then((res)=>{
        if(res.code === 0){
          this.$message.error(res.msg);
        }else{
          localStorage.setItem('msToken',res.data.token)
          this.$message.success('登录成功!');
          signUpStatus.signUpStatusTrue();
          signUpStatus.setPhone(this.account);
          window.location.replace(frontPageUrl);
        }
      }).catch((err)=>{
        this.$message.error('错误:'+err);
      });
    },

    //格式检查
    accountInput(){
      this.account = accountInputCheck(this.account);
    },
    passwordInput(){
      this.password = passwordInputCheck(this.password);
    }
  }
}
</script>

<style scoped>
.loginPage{
  background-color: #F0EFF2;
  overflow: hidden;
}
.loginForm{
  border-radius: 30px;
  background-color: white ;
  width: 50vw;
  height: 50vh;
  margin-left: 22vw;
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
.loginButton{
  background-color: pink;
  width: 20vw;
  height: 7vh;
  margin-top: 2vh;
}
</style>