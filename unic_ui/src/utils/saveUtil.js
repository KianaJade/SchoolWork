export function saveClass(vm,data){
    let ret=[];
    let len1 = data.length;
    let a=-1,b=0,c=1;
    for(let i=0;i<len1;i++){
        ret.push(data[i].label);
        ret.push(data[i].label);

        let gc = data[i];
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
    vm.$request.post('/updateGoodsClass').then((res)=>{
        if(res.code === 1){

        }
    }).catch((err)=>{
        vm.$message.error(err);
    });
}