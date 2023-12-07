import { createRouter, createWebHistory } from 'vue-router'
import NotFound from '../views/error/NotFound'
import twitterMessage from '../views/twitter/twitterMessage'
import telegramMessage from '../views/telegram/telegramMessage'
import UserAccountLoginView from '../views/user/account/UserAccountLoginView'
import UserAccountRegisterView from '../views/user/account/UserAccountRegisterView'
import store from '../store/index'
import twitterUser from '../views/twitter-user/twitterUser'

const routes = [
  {
    path: "/",
    name: "home",
    redirect: "/twitter",
    meta: {
      requestAuth: true,
    }
  },
  {
    path: "/twitter/",
    name: "twitter",
    component: twitterMessage,
    meta: {
      requestAuth: true,
    }

  },
  {
    path: "/twitterUser/",
    name: "twitterUser",
    component: twitterUser,
    meta: {
      requestAuth: true,
    }

  },
  {
    path: "/telegram/",
    name: "telegram",
    component: telegramMessage,
    meta: {
      requestAuth: true,
    }

  },
  {
    path: "/user/account/login/",
    name: "user_account_login",
    component: UserAccountLoginView,
    meta: {
      requestAuth: false,
    }

  },
  {
    path: "/user/account/register/",
    name: "user_account_register",
    component: UserAccountRegisterView,
    meta: {
      requestAuth: false,
    }

  },

  {
    path: "/404/",
    name: "404",
    component: NotFound,
    meta: {
      requestAuth: false,
    }

  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/"
  }
]


const router = createRouter({
  history: createWebHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.meta.requestAuth && !store.state.user.is_login) {
    next({name: "user_account_login"});
  } else {
    next();
  }
})



export default router
