<template>
  <div class="goodsView">
    <div style="height: 15%">

    </div>
    <div style="height: 15%;background-color: #2c3e50">

    </div>
    <div class="goodsView-3">
      <div class="leftImg">
        <div class="leftImg-div1" v-for="(k,i) in imgNum" :key="k">
          <div  class="miniImg" :class="{'miniImg-hover':imgSel === i}" @mouseenter="onMouseEnter(i)">
            <el-image
                style="width: 50px;height: 50px;border-radius: 10px;margin-top: 5px"
                :src="miniImgSrc[i]"
                fit="fit"></el-image>
          </div>
        </div>
      </div>
      <div class="goodsImgShow">
        <el-image
            style="border-radius: 10px;width: 100%;height: 100%"
            :src="imgSrc"></el-image>
      </div>
      <div class="goodsChoose">
        <div class="goodsName">
          {{goodsName}}
        </div>
        <div style="display: flex;width: fit-content;margin-top: 10px">
          <span style="font-size: 15px;color: #e5423c;">¥</span>
          <span style="font-size: 20px;color: #e5423c;">{{price}}</span>
          <span style="font-size: 17px;color: #cccccc;margin-left: 2px">销量</span>
          <span style="font-size: 17px;color: #cccccc">{{sellNum}}</span>
        </div>
        <div style="display: flex;width: fit-content;margin-top: 10px">
          <span>销量</span>
          <span style="margin-left: 20px">{{sellNum}}</span>
        </div>
        <div v-for="(k,i) in collapse.num" :key="k" style="display: flex;margin-top: 20px">
          <span style="width: fit-content">{{collapse.title[i]}}</span>
          <div style="margin-left: 20px;display: flex">
            <div v-for="(k2,i2) in collapse.tag[i].dynamicTags" :key="k2" style="margin-left: 10px">
              <div class="typeTag" :class="{'typeTag-sel':collapse.activeNames[i] === i2}" @click="changeTypeTag(i,i2)">
                {{k2}}
              </div>
            </div>
          </div>
        </div>
        <div style="display: flex;margin-top: 20px">
          <span>数量</span>
          <el-input-number v-model="buyNum"
                           style="margin-left: 20px;"
                           size="mini"
                           :precision="0"
                           :min="1" :max="goodsNum" label="描述文字"></el-input-number>
        </div>
        <div style="margin-top: 20px">
          <div class="buyButton">
            <button @click="addBag" class="buyButton-1">加入购物车</button>
            <button @click="addOrder" class="buyButton-2">立即购买</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import signUpStatus from "@/utils/status";

export default {
  name: "GoodsView",
  data(){
    return {
      id:0,
      goodsName:'',
      imgSrc:'',
      imgNum:0,
      imgSel:0,
      buyNum:1,
      miniImgSrc:['/goodsImages/testGoodsImage.png','','','',''],
      miniImg_hover:[true,false,false,false,false],
      price:0,
      sellNum:0,
      goodsNum:0,
      baseUrl:'http://localhost:8080/download/',
      collapse:{
        num : 0,
        activeNames: [],
        title:[],
        changeTitle:'',
        tag:[{dynamicTags: [],
          TagsID:[],
          inputVisible: false,
          inputValue: ''},],
      }
    }
  },
  methods:{
    onMouseEnter(i){
      this.imgSrc = this.miniImgSrc[i];
      this.imgSel = i;
      this.miniImg_hover[i] = true;
    },
    changeTypeTag(i,i2){
      this.collapse.activeNames.splice(i,1,i2);
    },

    initGoodsView(){
      if(sessionStorage.getItem('goodsID') === null){
        this.id = parseInt(localStorage.getItem('goodsID'));
        sessionStorage.setItem('goodsID',this.id);
      }else{
        this.id = parseInt(sessionStorage.getItem('goodsID'));
      }

      if (isNaN(this.id) || this.id <= 0) {
        this.$message.error('商品 ID 无效');
        return;
      }
      this.$request.get('/shopping',{
        params:{
          goodsId:this.id,
        }
      }).then((res)=>{
        if(res.code === 0){
          this.$message.error('初始化失败:'+res.msg);
        }else{
          this.goodsName = res.data.name;
          this.imgNum = res.data.imgNumber;
          this.miniImgSrc[0] = this.baseUrl + res.data.goodsImg1;
          this.miniImgSrc[1] = this.baseUrl + res.data.goodsImg2;
          this.miniImgSrc[2] = this.baseUrl + res.data.goodsImg3;
          this.miniImgSrc[3] = this.baseUrl + res.data.goodsImg4;
          this.miniImgSrc[4] = this.baseUrl + res.data.goodsImg5;
          this.imgSrc = this.miniImgSrc[0];
          this.price = res.data.price;
          this.sellNum = res.data.sellNumber;
          this.goodsNum = res.data.number;

          this.collapse.title = res.data.type;
          this.collapse.num = res.data.type.length;
          let tags = res.data.tags;
          for(let i=0;i<tags.length;i++)
          {
            let tags1 = tags[i];
            this.collapse.activeNames.push(0);
            this.collapse.tag[i] = {dynamicTags: [],
              TagsID:[],
              inputVisible: false,
              inputValue: ''};
            for(let j=0;j<tags1.length;j++)
            {
              this.collapse.tag[i].dynamicTags.push(tags1[j].tagName);
              this.collapse.tag[i].TagsID.push(tags1[j].tagID);
            }
          }
        }
      }).catch((err)=>{
        this.$message.error(err);
      });
    },

    addBag(){
      this.postOrder(0);
    },
    addOrder(){
      this.$confirm('是否立即付款？',{
        confirmButtonText: '立即付款',
        cancelButtonText: '暂不付款',
        type: 'warning'
      }).then(()=>{
        this.postOrder(2);
      }).catch(()=>{
        this.postOrder(1);
      });

    },
    postOrder(status){
      let tagsID = [];
      for(let i=0;i<this.collapse.activeNames.length;i++)
      {
        tagsID.push(this.collapse.tag[i].TagsID[this.collapse.activeNames[i]]);
      }
      this.$request.post('/shopping/addOrder',{
        phone: signUpStatus.getPhone(),
        goodsId:this.id,
        number: this.buyNum,
        singlePrice:this.price,
        totalPrice: this.price*this.buyNum,
        status:status,
        tagId:tagsID,
      }).then((res)=>{
        if(res.code === 0){
          this.$message.error('下单失败：'+res.msg);
        }else{
          this.$message.success('下单成功');
        }
      }).catch((err)=>{
        this.$message.error('异常'+err);
      });
    },
  },
  created() {
    this.initGoodsView();
  }
}
</script>

<style scoped>
.goodsView{
  overflow: hidden;
  width: 100%;
  height: 100%;
}
.goodsView-3{
  display: flex;
}
.goodsView-3 .goodsImgShow{
  width: 500px;
  height: 500px;
}
.leftImg{
  width: 80px;
  margin-left: 200px;
}
.leftImg-div1{
  overflow: hidden;
  width: 63px;
  height: 63px;
}
.miniImg{
  overflow: hidden;
  width: 60px;
  height: 60px;
  border-radius: 10px;
}
.miniImg:hover{
  cursor: pointer;
}
.miniImg-hover{
  border: 1px red solid;
}
.goodsChoose{
  margin-left: 20px;
  width: 600px;
  height: 600px;
}
.goodsChoose .goodsName{
  //width: 100%;
  height: fit-content;
  font: 700 16px Arial,"microsoft yahei";
  color: #666;
  padding-top: 10px;
  margin-bottom: 5px;
}
.typeTag{
  border-radius: 4px;
  border:1px solid #7a7a7a;
  cursor: pointer;
}
.typeTag:hover{
  border:1px solid red;
  color: #e5423c;
}
.typeTag-sel{
  border:1px solid red;
  color: #e5423c;
}
.buyButton{
  overflow: hidden;
  display: flex;
  width: 400px;
  height: 50px;
  border-radius: 8px;
}
.buyButton-1{
  width: 100px;
  height: 100%;
  border-top-left-radius: 8px;
  border-bottom-left-radius: 8px;
  background-color: #FFA202;
}
.buyButton-2{
  width: 300px;
  height: 100%;
  border-top-right-radius: 8px;
  border-bottom-right-radius: 8px;
  background-color: #FF5300;
}
</style>