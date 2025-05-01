<template>
  <el-form class="form-createGoods" :model="form" label-width="80px">
    <el-form-item label="商品图像">
      <div class="imgUpload">
        <el-upload
            accept=".jpg,.png"
            list-type="picture-card"
            :on-change="handleChangeFile"
            :on-exceed="handleExceed"
            :on-remove="handleRemoveImg"
            :limit="5"
            :file-list="uploadInfo.fileList"
            :auto-upload="false" action="http://localhost:8080/image/upload" multiple>
          <i slot="default" class="el-icon-plus"></i>
          <div slot="file" slot-scope="{file}">
            <img class="el-upload-list__item-thumbnail" :src="file.url" alt="">
            <span class="el-upload-list__item-actions">
            <span class="el-upload-list__item-preview" @click="handlePictureCardPreview(file)">
              <i class="el-icon-zoom-in"></i>
            </span>
            <span v-if="!disabled" class="el-upload-list__item-delete" @click="handleRemove(file)">
              <i class="el-icon-delete"></i>
            </span>
          </span>
          </div>
        </el-upload>
        <el-dialog :visible.sync="dialogVisible">
          <img width="100%" :src="dialogImageUrl" alt="">
        </el-dialog>
      </div>
    </el-form-item>
    <div style="display: flex">
      <div class="form-createGoods-left">
        <el-form-item label="商品标题">
          <div class="nameInput">
            <el-input v-model="form.name" minlength="1" maxlength="40" placeholder="请输入商品的标题" style="width: 400px"></el-input>
          </div>
        </el-form-item>
        <div style="display: flex">
          <el-form-item label="商品单价">
            <div class="goodsPrice">
              <el-input @input="priceFormatCheck" v-model="form.price" placeholder="请输入价格" style="width: 160px"></el-input>
            </div>
          </el-form-item>
          <el-form-item label="库存量">
            <div class="goodsStore">
              <el-input @input="storeInputCheck" v-model="form.num" placeholder="请选择库存量"  style="width: 160px"></el-input>
            </div>
          </el-form-item>
        </div>
        <el-form-item label="一级类目">
          <div style="width: fit-content">
            <el-select @change="getClass1Pos" v-model="form.class1Sel" placeholder="请选择一级类目">
              <el-option v-for="item in form.goodsClass"
                         :key="item.label" :label="item.label" :value="item.label"></el-option>
            </el-select>
          </div>
        </el-form-item>
        <el-form-item label="二级类目">
          <div style="width: fit-content">
            <el-select @change="getClass2Pos" v-model="form.class2Sel" placeholder="请选择二级类目">
              <el-option v-for="item in (form.goodsClass[form.pos1]? form.goodsClass[form.pos1].children : [])"
                         :key="item.label" :label="item.label"  :value="item.label"></el-option>
            </el-select>
          </div>
        </el-form-item>
        <el-form-item label="三级类目">
          <div style="width: fit-content">
            <el-select v-model="form.class3Sel" placeholder="请选择三级类目">
              <el-option v-for="item in (form.goodsClass[form.pos1] && form.goodsClass[form.pos1].children[form.pos2]? form.goodsClass[form.pos1].children[form.pos2].children : [])"
                         :key="item.label" :label="item.label" :value="item.label"></el-option>
            </el-select>
          </div>
        </el-form-item>

        <el-button @click="uploadNewGoods" type="success">立即创建</el-button>

      </div>
      <div class="form-createGoods-right">
        <div style="display: flex;">
          <el-input v-model="collapse.newType" maxlength="40" placeholder="请输入新增的属性名" style="width: 200px"></el-input>
          <el-button style="margin-left: 20px" type="primary" plain @click="addGoodsType">新增属性</el-button>
        </div>
        <div style="display: flex;margin-top: 10px">
          <el-select v-model="collapse.updateTitle" placeholder="请选择需要修改的属性名">
            <el-option
                v-for="item in collapse.title"
                :key="item"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
          <el-input v-model="collapse.newTitle" maxlength="40" placeholder="请输入修改后的名称" style="margin-left: 10px;width: 200px"></el-input>
          <el-button @click="updateTitle" style="margin-left: 20px" type="primary" plain>确认修改</el-button>
        </div>
        <div style="display: flex;margin-top: 10px">
          <el-select v-model="collapse.deleteTitle" placeholder="请选择需要删除的属性名">
            <el-option
                v-for="item in collapse.title"
                :key="item"
                :label="item"
                :value="item">
            </el-option>
          </el-select>
          <el-button @click="deleteTitle" style="margin-left: 20px" type="primary" plain>确认删除</el-button>
        </div>
        <el-collapse v-model="collapse.activeNames" style="margin-top: 20px">
            <div v-for="(k,i) in collapse.num" :key="k">
              <el-collapse-item :title="collapse.title[i]" :name="i">
                <el-tag
                    :key="`tag-${i}-${index}`"
                    v-for="(tag,index) in collapse.tag[i].dynamicTags"
                    closable
                    :disable-transitions="false"
                    @close="handleClose(i,tag)">
                  {{tag}}
                </el-tag>
                <el-input
                    maxlength="40"
                    class="input-new-tag"
                    v-if="collapse.tag[i].inputVisible"
                    v-model="collapse.tag[i].inputValue"
                    :ref="`saveTagInput-${i}`"
                    size="small"
                    @keyup.enter.native="handleInputConfirm(i)"
                    @blur="handleInputConfirm(i)">
                </el-input>
                <el-button v-else class="button-new-tag" size="small" @click="showInput(i)">+ New Tag</el-button>
              </el-collapse-item>
            </div>
        </el-collapse>
      </div>
    </div>
  </el-form>
</template>

<script>
import {getClass} from "@/utils/initUtil";
import {priceInputCheck,accountInputCheck} from "@/utils/inputCheck";
import signUpStatus from "@/utils/status";
import axios from "axios";
export default {
  name: "GoodsCreateCard",
  data(){
    return{
      form:{
        pos1:0,
        pos2:0,
        class1Sel:'',
        class2Sel:'',
        class3Sel:'',
        goodsClass:[{label:'类目一',children:[{
          label:'类目二',
            children:[{
            label:'类目三'
            }]
          }]}],
        name:'',
        price:0,
        num:0,
      },
      dialogImageUrl: '',
      dialogVisible: false,
      disabled: false,
      collapse:{
        newType:'',//新添加的类型名
        num : 1, //类型数量
        updateTitle:'',//待修改的类型
        newTitle:'',//修改后的类型名
        deleteTitle:'',
        activeNames: ['0'],
        title:['颜色（例）'],
        tag:[{dynamicTags: ['标签一', '标签二', '标签三'],
          inputVisible: false,
          inputValue: ''}],
      },
      uploadInfo:{
        imgNum:0,
        imgList:[],
        fileList:[]
      },
    }
  },
  methods:{
    //上传
    uploadCheck(){
      if(this.form.name === '')return false;
      if(this.form.name === null)return false;
      if(this.form.price === null)return false;
      if(this.form.num === null)return false;
      if(this.form.class1Sel === '')return false;
      if(this.form.class2Sel === '')return false;
      if(this.form.class3Sel === '')return false;
      return this.uploadInfo.imgList.length !== 0;
    },
    uploadNewGoods(){
      if(this.uploadCheck() === false){
        this.$message.error('商品信息尚未补充完整')
        return;
      }
      let formData = new FormData();
      formData.append('phone',signUpStatus.phone);
      formData.append('name',this.form.name);
      formData.append('price',this.form.price);
      formData.append('number',this.form.num);
      formData.append('class1',this.form.class1Sel);
      formData.append('class2',this.form.class2Sel);
      formData.append('class3',this.form.class3Sel);

      let goodsType=[];
      for(let i=0;i<this.collapse.num;i++)
      {
          goodsType[i] = [];
          goodsType[i].push(this.collapse.title[i]);
          for(let j=0;j<this.collapse.tag[i].dynamicTags.length;j++)
          {
            goodsType[i].push(this.collapse.tag[i].dynamicTags[j]);
          }
      }
      formData.append('type',JSON.stringify(goodsType));

      for (let i = 0; i < this.uploadInfo.imgList.length; i++) {
        formData.append('files', this.uploadInfo.imgList[i]);
      }
      formData.append('imgNumber',this.uploadInfo.imgList.length);

      axios({
        method: 'post',
        url: this.$baseUrl+'/shop/createGoods',
        headers: {
          //'Content-Type': 'multipart/form-data; boundary=----WebKitFormBoundaryVCFSAonTuDbVCoAN',
          'token':localStorage.getItem('msToken')
        },
        data: formData,
      }).then((res) => {
        if(res.data.code !== 1){
          this.$message.error("上传失败:"+res.data.msg)
        }else{
          this.$message.success("上传成功");
        }
      }).catch((error) => {
        console.error('请求出错:', error);
        this.$message.error('网络错误，请稍后重试');
      });
    },

    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },

    //tag
    handleClose(i,tag) {
      this.collapse.tag[i].dynamicTags.splice(this.collapse.tag[i].dynamicTags.indexOf(tag), 1);
    },
    handleInputConfirm(i) {
      let inputValue = this.collapse.tag[i].inputValue;
      if (inputValue) {
        this.collapse.tag[i].dynamicTags.push(inputValue);
      }
      this.collapse.tag[i].inputVisible = false;
      this.collapse.tag[i].inputValue = '';
    },
    showInput(i) {
      this.collapse.tag[i].inputVisible = true;
      this.$nextTick(() => {
        const inputRef = this.$refs[`saveTagInput-${i}`];
        if (inputRef) {
          inputRef.focus;
        }
      });
    },

    //商品type的操作
    addGoodsType(){
      if(this.collapse.newType === '')return;
      this.collapse.num ++ ;
      this.collapse.title.push(this.collapse.newType);
      this.collapse.tag.push({dynamicTags: ['标签一', '标签二', '标签三'],
        inputVisible: false,
        inputValue: ''});
      this.collapse.newType = '';
    },
    updateTitle(){
      let idx = this.collapse.title.indexOf(this.collapse.updateTitle);
      if(idx === -1)return;
      this.collapse.title.splice(idx,1,this.collapse.newTitle);
      this.collapse.updateTitle = this.collapse.newTitle;
    },
    deleteTitle(){
      let idx = this.collapse.title.indexOf(this.collapse.deleteTitle);
      if(idx === -1)return;
      this.collapse.title.splice(idx,1);
      this.collapse.deleteTitle = '';
      this.collapse.num -- ;
    },

    //商品图片
    handleChangeFile (file,fileList) {
      if (!file) return

      let idx = fileList.indexOf(file);
      if (idx !== -1) {
        this.uploadInfo.imgList.push(file.raw);
        this.uploadInfo.fileList.push(file)
      }

      const isLt2M =( file.size / 1024 / 1024 )< 2
      if (!isLt2M) {
        this.$refs.upload.clearFiles()
        this.$message({
          message: '上传文件大小不能超过 2MB!',
          type: 'warning'
        })
        return false
      }
    },
    handleExceed(){
      this.$message.warning('图片数量限制为5');
    },
    handleRemoveImg(file,fileList){
      let idx = fileList.indexOf(file);
      if (idx!== -1) {
        fileList.splice(idx, 1);
        this.uploadInfo.imgList.splice(idx,1);
      }
    },
    handleRemove(file) {
      let idx = this.uploadInfo.imgList.indexOf(file.raw);
      if (idx!== -1) {
        this.uploadInfo.fileList.splice(idx, 1);
        this.uploadInfo.imgList.splice(idx,1);
      }
    },

    getClass1Pos(){
      let t =0;
      for(let i = 0; i<this.form.goodsClass.length;i++)
      {
        if(this.form.class1Sel === this.form.goodsClass[i].label){
          t = i;
          break;
        }
      }
      this.form.pos1 = t;
      this.form.pos2 = 0;
      this.form.class2Sel = '';
      this.form.class3Sel = '';
    },
    getClass2Pos(){
      let t =0;
      for(let i = 0; i<this.form.goodsClass[this.form.pos1].children.length;i++)
      {
        if(this.form.class2Sel === this.form.goodsClass[this.form.pos1].children[i].label){
          t = i;
          break;
        }
      }
      this.form.pos2 = t;
      this.form.class3Sel = '';
    },

    //格式检查
    priceFormatCheck(){
      this.form.price = priceInputCheck(this.form.price);
    },
    storeInputCheck(){
      this.form.num = accountInputCheck(this.form.num);
    },

  },
  created() {
    this.form.goodsClass = getClass(this);
  }
}
</script>

<style scoped>
.form-createGoods{
  width: 1200px;
  height: fit-content;
}
.form-createGoods-left{
  width: 500px;
  height: 100%;
}
.form-createGoods-right{
  margin-top: 10px;
  width: 600px;
  height: fit-content;
}
.imgUpload{
  width: fit-content;
}
.nameInput{
  width: fit-content;
}
.goodsPrice{
  width: fit-content;
}
.el-tag + .el-tag {
  margin-left: 10px;
}
.button-new-tag {
  margin-left: 10px;
  height: 32px;
  line-height: 30px;
  padding-top: 0;
  padding-bottom: 0;
}
.input-new-tag {
  width: 90px;
  margin-left: 10px;
  vertical-align: bottom;
}
</style>