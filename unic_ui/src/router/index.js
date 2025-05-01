import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'home',
    component: () => import(/* webpackChunkName: "about" */ '../views/HomeView.vue'),
    meta:{
      title:'优集社'
    }
  },
  {
    path: '/home',
    redirect:'/'
  },
  {
    path: '/login',
    name: 'login',
    component: () => import(/* webpackChunkName: "about" */ '../views/LoginView.vue'),
    meta: {
      title: '登录'
    },
  },
  {
    path: '/signIn',
    name: 'signIn',
    component: () => import(/* webpackChunkName: "about" */ '../views/SignIn.vue'),
    meta: {
      title: '注册'
    },
  },
  {
    path: '/shopping',
    name: 'shop',
    component: () => import(/* webpackChunkName: "about" */ '../views/GoodsView.vue'),
    meta: {
      title: '商品浏览'
    },
  },
  {
    path: '/manage',
    name:'manage',
    component: () => import(/* webpackChunkName: "about" */ '../views/ManageView.vue'),
    meta: {
      title: '管理'
    },
    children:[
      {
        path: 'tag',
        name:'tag',
        component: () => import(/* webpackChunkName: "about" */ '../views/managePage/TagManage.vue'),
        meta: {
          title: '管理类目'
        },
      }
    ]
  }
]

const router = new VueRouter({
  mode:'history',
  routes
})

router.beforeEach((to, from, next) => {
  if (to.fullPath === from.fullPath) {
    // 如果目标路径和当前路径相同，阻止导航
    return next(false)
  }else{
    return next();
  }
});

export default router
