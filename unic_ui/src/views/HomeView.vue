<template>
  <div class="home">
    <el-container>
      <el-header class="pageHeader">
        <el-row style="width: 100vw">
          <el-col style="width: 10vw">
            <el-image
                style="width: 100px; height: 50px;margin-top: 5px"
                src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg"
                fit="scale-down"></el-image>
          </el-col>
          <el-col style="width: 30vw">
            <el-menu class="el-menu-demo" mode="horizontal">
<!--              <el-menu-item index="1">处理中心</el-menu-item>-->
              <el-menu-item>
                <el-dropdown v-if="false">
                  <el-button>
                    热门分类<i class="el-icon-arrow-down el-icon--right"></i>
                  </el-button>
                  <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item
                        class="el-dropdown-item"
                        style="width: 10vw;height: 5vh; display: flex; justify-content: space-between; align-items: center;"
                      v-for="(k,i) in Math.ceil(goodsClass.length/3)"
                      :key="k">
                      <span>{{goodsClass[i*3].label}}</span>
                      <span v-if="i*3+1<goodsClass.length">/{{goodsClass[i*3+1].label}}</span>
                      <span v-if="i*3+2<goodsClass.length">/{{goodsClass[i*3+2].label}}</span>
                      <i class="el-icon-arrow-right"></i>
                      <div class="sub-menu">
                        <el-col>
                          <el-row v-for="(sa,j) in (goodsClass[i*3].children.length)" :key="sa">
                            <el-col :span="4">
                              <div style="float: right">
                                <span>{{goodsClass[i*3].children[j].label}}</span>
                                <i class="el-icon-arrow-right"></i>
                              </div>
                            </el-col>
                            <el-col style="" :span="20">
                              <el-col style="margin-top: 0" v-for="(ty,ki) in goodsClass[i*3].children[j].children.length" :key="ty" :span="2">
                                  <span>{{goodsClass[i*3].children[j].children[ki].label}}</span>
                              </el-col>
                            </el-col>
                          </el-row>
                          <el-row v-for="(sa,j) in (i*3+1<goodsClass.length?(goodsClass[i*3+1].children.length):0)" :key="sa">
                            <el-col :span="4">
                              <div style="float: right">
                                <span>{{goodsClass[i*3+1].children[j].label}}</span>
                                <i class="el-icon-arrow-right"></i>
                              </div>
                            </el-col>
                            <el-col style="" :span="20">
                              <el-col style="margin-top: 0" v-for="(ty,ki) in goodsClass[i*3+1].children[j].children.length" :key="ty" :span="2">
                                <span>{{goodsClass[i*3+1].children[j].children[ki].label}}</span>
                              </el-col>
                            </el-col>
                          </el-row>
                          <el-row v-for="(sa,j) in (i*3+2<goodsClass.length?(goodsClass[i*3+2].children.length):0)" :key="sa">
                            <el-col :span="4">
                              <div style="float: right">
                                <span>{{goodsClass[i*3+2].children[j].label}}</span>
                                <i class="el-icon-arrow-right"></i>
                              </div>
                            </el-col>
                            <el-col style="" :span="20">
                              <el-col style="margin-top: 0" v-for="(ty,ki) in goodsClass[i*3+2].children[j].children.length" :key="ty" :span="2">
                                <span>{{goodsClass[i*3+2].children[j].children[ki].label}}</span>
                              </el-col>
                            </el-col>
                          </el-row>
                        </el-col>
                      </div>
                    </el-dropdown-item>
                  </el-dropdown-menu>
                </el-dropdown>
              </el-menu-item>
            </el-menu>
          </el-col>
          <el-col style="width: 40vw">
            <el-input style="width: 10vw" placeholder="这是一处空旷的荒原"></el-input>
          </el-col>
          <div style="margin-left: 200px;width: fit-content;display: flex">
            <div style="width: fit-content;margin-right: 30px">
              <el-avatar style="margin-left:5vw" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"></el-avatar>
            </div>
            <div style="width: 10vw;"  v-if="!signUpStatus.status">
              <div style="margin-left: 0;margin-top: 10px;width: 5vw">
                <el-link style="margin-right: 3px" :underline="false" href="/login">登录</el-link>
                <span style="margin-top: 2px">/</span>
                <el-link style="margin-left: 3px" :underline="false" href='/signIn'>注册</el-link>
              </div>
            </div>
            <div v-else>
              <el-button type="text" @click="logOut">退出登录</el-button>
            </div>
          </div>
        </el-row>
      </el-header>
      <div style="height: 10vh"></div>
      <el-container>
        <el-aside class="pageAside">
            <el-row style="margin-top: 0">
              <el-menu
                  default-active="1"
                  class="asideMenu">
                <el-menu-item index="1" @click="goToMainHome">
                  <i class="el-icon-house"></i>
                  <span >集优品</span>
                </el-menu-item>
<!--                <el-menu-item index="2">-->
<!--                  <i class="el-icon-chat-dot-square"></i>-->
<!--                  <span slot="title">消息</span>-->
<!--                </el-menu-item>-->
                <el-menu-item index="3" @click="goToShopBag">
                  <i class="el-icon-shopping-cart-full"></i>
                  <span slot="title">购物车</span>
                </el-menu-item>
                <el-menu-item index="4" @click="goToOrder">
                  <i class="el-icon-goods"></i>
                  <span slot="title">我的订单</span>
                </el-menu-item>
                <el-menu-item index="5" @click="goToBusinessPage">
                  <i class="el-icon-s-shop"></i>
                  <span slot="title">商家服务</span>
                </el-menu-item>
                <el-menu-item index="6" @click="goToUserPage">
                  <i class="el-icon-user"></i>
                  <span slot="title">个人主页</span>
                </el-menu-item>
              </el-menu>
            </el-row>
        </el-aside>
        <div class="pageMain">
          <MainHome v-if="this.mainPageVisible === 1"></MainHome>
          <ShopBag v-else-if="mainPageVisible === 3"></ShopBag>
          <GoodsOrder v-else-if="mainPageVisible === 4"></GoodsOrder>
          <BusinessPage v-else-if="this.mainPageVisible === 5"></BusinessPage>
          <UserView v-else-if="this.mainPageVisible === 6"></UserView>
        </div>
      </el-container>
    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import MainHome from "@/views/asideView/MainHome.vue";
import BusinessPage from "@/views/asideView/BusinessPage.vue";
import ShopBag from "@/views/asideView/ShopBag.vue";
import GoodsOrder from "@/views/asideView/GoodsOrder.vue";
import signUpStatus from "@/utils/status";
import UserView from "@/views/UserPage/UserView.vue";
import {getClass} from "@/utils/initUtil";
export default {
  name: 'HomeView',
  components: {
    MainHome,
    BusinessPage,
    ShopBag,
    GoodsOrder,
    UserView,
  },
  data(){
    return{
      myBaseUrl:'http://localhost:7000',
      mainPageVisible:1,
      signUpStatus,
      goodsClass:[],
    }
  },
  methods:{
    refreshPage() {
      this.$router.go(0);
    },

    goToLogin(){
      this.$router.push('/login');
    },

    goToMainHome(){
      this.mainPageVisible = 1;
    },

    goToShopBag(){
      signUpStatus.checkSignUp(this);
      if(signUpStatus.status)this.mainPageVisible = 3;
    },
    goToUserPage(){
      this.mainPageVisible = 6;
    },

    goToOrder(){
      signUpStatus.checkSignUp(this);
      if(signUpStatus.status)this.mainPageVisible = 4;
    },

    async goToBusinessPage(){
      signUpStatus.checkSignUp(this);
      if(signUpStatus.status){
        await signUpStatus.getShopInfo(this);
        if(signUpStatus.shop.isHave === false){
          await this.createShop();
        }else {
          this.mainPageVisible = 5;
        }
      }
    },

    createShop(){
      this.$confirm('您当前没有商铺，是否创建?', '提示', {
        confirmButtonText: '创建商铺',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$request.post('/shop/createShop',{
          phone: signUpStatus.phone,
        }).catch((err)=>{
          this.$message.error(err);
        });
        signUpStatus.getShopInfo(this);
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消'
        });
      });
    },

    logOut(){
      signUpStatus.initStatus(this)
    },

  },
  created() {
    signUpStatus.getStatus();
    this.goodsClass = getClass(this);
  }
}
</script>

<style scoped>
.home{
  overflow: hidden;
  //width: 100vw;
}
.pageHeader{
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
  top:0;
  width:100vw;
  height: 13vh;
  position: fixed;
  margin-left: -10px;
  z-index: 1000;
  background-color: white;
}
.pageAside{
  top: 61px;
  left: 0;
  position: fixed;
  height: 92vh;
  width: 9%;
}
.pageMain{

}
.asideMenu{
  height: 92vh;
  width: 9vw;
  background-color:  #F0EFF2;
  margin-top: 0;
  box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
}
.el-dropdown-item {
  position: relative;
}
.sub-menu {
  display: none;
  position: fixed;
  left: 21vw;
  top: 10vh;
  width: 50vw;
  height: fit-content;
  background-color: white;
  border: 1px solid #ccc;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.12);
  min-width: 120px;
}
.el-dropdown-item:hover .sub-menu {
  display: block;
}
</style>