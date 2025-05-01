<template>
  <div class="tagPage">
    <div style="width: fit-content;height: fit-content">
      <div class="block">
        <el-tree
            :data="data"
            node-key="id"
            default-expand-all
            :expand-on-click-node="false">
          <span class="custom-tree-node" slot-scope="{ node, data }">
            <span>{{ node.label }}<span v-if="node.level!==1">（{{node.level-1}}）</span></span>
        <span>
          <el-button
              v-if="node.level !== 4"
              type="text"
              size="mini"
              @click="() => append(data)">
            增加
          </el-button>
          <el-button
              v-if="node.level!==1"
              type="text"
              size="mini"
              @click="() => remove(node, data)">
            删除
          </el-button>
            <el-input
                class="input-new-tag"
                v-if="data.inputVisible"
                v-model="inputValue"
                ref="saveTagInput"
                size="mini"
                @keyup.enter.native="handleInputConfirm(node,data)"
                @blur="handleInputConfirm(node,data)">
          </el-input>
            <el-button
                class="button-new-tag"
                v-else-if="node.level!==1"
                type="text"
                size="mini"
                @click="update(node,data)">
            修改
          </el-button>
        </span>
      </span>
        </el-tree>
      </div>
    </div>
  </div>
</template>

<script>
import {initClass} from "@/utils/initUtil";
import {getClassNum} from "@/utils/initUtil";
export default {
  name: "TagManage",
  components: {

  },
  data(){
    const data= [{
          num:0,
          id:1,
          level:1,
          label:'类目树形控件',
          inputVisible:false,
          children:[{
            id:2,
            level:2,
            label:'一级',
            inputVisible:false,
            children:[{
              id:3,
              level:3,
              label:'二级',
              inputVisible:false,
            }]
          }]
        }];
    return{
      inputValue:'',
      data: JSON.parse(JSON.stringify(data)),
      defaultProps: {
        children: 'children',
        label: 'label'
      },
      id:4,
    }
  },
  methods:{
    append(data) {
      this.$request.post('/manage/addClass',{
        className: "新增"+(this.id+1).toString(),
        classLevel: data.level,
        classNameLast: data.label,
      });

      const newChild = { id: this.id++,label: '新增'+this.id.toString(), children: [] ,inputVisible:false};
      if (!data.children) {
        this.$set(data, 'children', []);
      }
      data.children.push(newChild);
    },

    remove(node, data) {
      this.$request.post('/manage/deleteClass',{
        className: data.label,
        classLevel: data.level-1,
      })

      const parent = node.parent;
      const children = parent.data.children || parent.data;
      const index = children.findIndex(d => d.id === data.id);
      children.splice(index, 1);
    },

    update(node,data){
      data.inputVisible = true;
      this.$nextTick(_ => {_;
        this.$refs.saveTagInput.focus();
      });
    },

    handleInputConfirm(node,data) {
      if(this.inputValue !== ''){
        this.$request.post('/manage/updateClass',{
          className: data.label,
          classLevel: data.level-1,
          classNewName: this.inputValue,
        });
      }

      let inputValue = this.inputValue;
      if (inputValue) {
        data.label = inputValue;
      }
      data.inputVisible = false;
      this.inputValue = '';
    },

    async fetchClassNum() {
      this.id = await getClassNum(this);
    },

    init(){
      this.data =  initClass(this);
      this.fetchClassNum();
    },

    saveUpdateClass(){

    },

  },
  created() {
    this.init();

  },
  mounted() {

  }
}
</script>

<style scoped>
.tagPage{
  width: 100%;
  height: 100%;
}
.custom-tree-node {
  flex: 1;
  display: flex;
  align-items: center;
  justify-content: space-between;
  font-size: 14px;
  padding-right: 8px;
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