import { createRouter, createWebHashHistory } from 'vue-router'
import RegisterView from "@/views/RegisterView";
import ForgetAccountView from "@/views/ForgetAccountView";

import MainView from "@/views/MainView";
import LoginView from "@/views/LoginView";
import GetTwiUserInformation from "@/views/allinformation/GetTwiUserInformation";
import GetTubeUserInformation from "@/views/allinformation/GetTubeUserInformation";
import GetTwitterInformation from "@/views/allinformation/GetTwitterInformation";
import UpdatePasswordView from "@/views/UpdatePasswordView";

import overView from "@/views/overView"
import GetYoutubeInformation from "@/views/allinformation/GetYoutubeInformation"
import keywordView from "@/views/allinformation/keywordView"
import KeyInformation from "@/views/allinformation/KeyInformation"
import TokenList from "@/views/TokenList"
import GetTelegramInformation from "@/views/allinformation/GetTelegramInformation"
import axios from 'axios';

const routes = [

  {
    path: '/main',
    name: 'main',
    meta: {
      title: '首页'
    },
    component: MainView,
    children: [
      {
        path: '/getTwiUserInformation',
        name: '推特用户信息',
        component: GetTwiUserInformation
      },
      {
        path: '/getTubeUserInformation',
        name: '油管用户信息',
        component: GetTubeUserInformation
      },
      {
        path: '/getTwitterInformation',
        name: '推特信息',
        component: GetTwitterInformation
      },
      {
        path: '/updatePassword',
        name: '密码修改',
        component: UpdatePasswordView
      },
      {
        path: '/overView',
        name: '总览',
        component: overView
      },
      {
        path: '/keyword',
        name: '关键词',
        component: keywordView
      },
      {
        path: '/getYoutubeInformation',
        name: '油管',
        component: GetYoutubeInformation
      },
      {
        path: '/keyInformation',
        name: '关键信息',
        component: KeyInformation
      },
      {
        path: '/TokenList',
        name: 'token',
        component: TokenList
      },
      {
        path: '/GetTelegramInformation',
        name: 'getTelegramInformation',
        component: GetTelegramInformation
      },
    ]
  },


  {
    path: '/',
    name: 'login',
    component: LoginView
  },
  {
    path: '/register',
    component: RegisterView
  },
  {
    path: '/forgetAccount',
    component: ForgetAccountView
  },

]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})


// router.beforeEach((to, from, next) => {
//   // to and from are both route objects. must call `next`.
//   if (to.path === '/register' || to.path === '/login' || to.path === '/main') {
//     next();//直接放行
//   } else {
//     const token = localStorage.getItem('jwt_token');
//     if (token === null || token === '') {
//       next('/')
//     } else {
//       next()
//     }
//   }
// })

//路由守卫
router.beforeEach((to, from, next) => {
  if (to.path === '/' || to.path === '/register') { // 如果跳转登录页面,则移除token
    localStorage.removeItem('jwt_token')
    next()
  } else {
    let token = localStorage.getItem('jwt_token');
    if (token === null || token === '') { //token不存在页跳转到登录页面
      router.replace({ path: '/' })
    } else {
      // 检验token是否正确
      axios({
        url: 'http://10.16.104.183:8181/user/account/info', //在controller中写一个接口用来token校验
        method: 'get',
        //将token信息保存在header里
        headers: {
          Authorization: "Bearer " + token,
        }
      }).then((resp) => {
        if (resp.data.error_message != "success") {
          console.log('检验失败')
          router.replace({ path: '/' }) // 如果token失效,返回到登录页面
        }
      })
      next();
    }
  }
})


export default router
