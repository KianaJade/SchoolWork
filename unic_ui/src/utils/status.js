let signUpStatus = {
    phone:'',
    status: false,
    shop:{
      id:0,
      name:'',
      img:'',
      des:'',
      address:'',
      time:'',
      isHave:false,
    },
    initStatus(vm){
        this.phone = '';
        this.status = false;
        this.shop.isHave = false;
        this.saveStatus();
        vm.$router.go(0);
    },

    saveStatus(){
        localStorage.setItem('signUpStatus',JSON.stringify(this));

    },

    getStatus(){
        const storedData = localStorage.getItem('signUpStatus');
        if (storedData) {
            const parsedData = JSON.parse(storedData);
            Object.assign(this, parsedData);
        }
    },

    setPhone:function (phone){
      this.getStatus();
      this.phone = phone;
      this.saveStatus();
    },
    getPhone:function (){
        this.getStatus();
        return this.phone
    },

    signUpStatusTrue: function () {
        this.getStatus();
        this.status = true;
        this.saveStatus();
    },

    checkSignUp:function (vm){
        this.getStatus();
      if(this.status === false){
          vm.$router.push('/login');
      }
      this.saveStatus();
    },

    getShopInfo: async function (vm){
        this.getStatus();
        await vm.$request.get('/shop/getShopInfo',{
            params:{
                phone: this.phone,
            }
        }).then((res)=>{
            if(res.code === 1){
                if(res.data !== null){
                    this.shop.id = res.data.id;
                    this.shop.name = res.data.name;
                    this.shop.img = res.data.img;
                    this.shop.des = res.data.des;
                    this.shop.address = res.data.address;
                    this.shop.time = res.data.createTime;
                    this.shop.isHave = true;
                }else{
                    this.shop.isHave = false;
                }
            }else{
                vm.$message.error(res.msg);
            }
        }).catch((err)=>{
            vm.$message.error(err);
        });
        this.saveStatus();
    },

};

export default signUpStatus;

export const frontPageUrl = 'http://localhost:7000/#/';