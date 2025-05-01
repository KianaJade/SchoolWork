export function initClass(vm){
    let ret= [{
        num:0,
        id:1,
        level:1,
        label:'类目树形控件',
        inputVisible:false,
        children:[],
    }];
    vm.$request.get('/getGoodsClass').then((res)=>{
        if(res.code === 1){
            let len = res.data.length;
            let a=-1,b=0,c=1;
            for(let i=0;i<len;i++){
                let gc = res.data[i];
                if(gc === '1'){
                    a++;
                    b=-1;
                    ret[0].children.push({id:++c,level:2,label:res.data[i+1],inputVisible:false,children:[]});
                }else if(gc === '2'){
                    b++;
                    ret[0].children[a].children.push({id:++c,level:3,label:res.data[i+1],inputVisible:false,children:[]});
                }else if(gc === '3'){
                    ret[0].children[a].children[b].children.push({id:++c,level:4,label:res.data[i+1],inputVisible:false});
                }
            }
            ret[0].num = ++c;
        }
        return ret;
    }).catch((err)=>{
        vm.$message.error(err);
        return ret;
    });
    return ret;
}

export function getClassNum(vm){
    return  vm.$request.get('/manage/getClassNum').then((res)=>{
        let num = 0;
        if(res.code === 1){
            num = res.data + 2;
        }
        return num;
    }).catch((err)=>{
        vm.$message.error(err);
        return 0;
    })
}

//GoodsCreateCard
export function getClass(vm){
    let ret=[];

    vm.$request.get('/getGoodsClass').then((res)=>{
        if(res.code === 1){
            let len = res.data.length;
            let a=-1,b=0;
            for(let i=0;i<len;i++){
                let gc = res.data[i];
                if(gc === '1'){
                    a++;
                    b=-1;
                    ret.push({label:res.data[i+1],children:[]});
                }else if(gc === '2'){
                    b++;
                    ret[a].children.push({label:res.data[i+1],children:[]});
                }else if(gc === '3'){
                    ret[a].children[b].children.push({label:res.data[i+1]});
                }
            }
        }
        return ret;
    }).catch((err)=>{
        vm.$message.error(err);
        return ret;
    });
    return ret;
}