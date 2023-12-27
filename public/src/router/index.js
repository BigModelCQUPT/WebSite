import { createRouter, createWebHashHistory } from 'vue-router'
import RegisterView from "@/views/RegisterView";
import ForgetAccountView from "@/views/ForgetAccountView";
import TestView from "@/views/TestView";
import MyView from "@/views/MyView";
import BookMeetingView from "../views/meeting/BookMeetingView";
import RecordMeetingView from "../views/meeting/RecordMeetingView";
import HistoryMeetingView from "../views/meeting/HistoryMeetingView";
import AddActivityView from "@/views/activity/AddActivityView";
import LookActivityView from "@/views/activity/LookActivityView";
import MainView from "@/views/MainView";
import LoginView from "@/views/LoginView";
import UpdateInformationView from "@/views/UpdateInformationView";
import MeetingMessageView from "@/views/message/MeetingMessageView";
import MeetingDetailView from "@/views/meeting/MeetingDetailView";
import CommunityIntroductionView from "@/views/community/CommunityIntroductionView";
import CommunityStructView from "@/views/community/CommunityStructView";
import FindPersonView from "@/views/community/FindPersonView";
import ReportDailyView from "@/views/yiqing/ReportDailyView";
import SalaryAccountView from "@/views/finance/SalaryAccountView";
import GetResidentInformation from "@/views/resident/GetResidentInformation";
import EmployeeView from "@/views/community/EmployeeView";
import EmployeeDetailView from "@/views/community/EmployeeDetailView";
import ReportWarmView from "@/views/yiqing/ReportWarmView";
import AddCommunityConditionView from "@/views/service/AddCommunityConditionView";
import CommunityConditionView from "@/views/service/CommunityConditionView";
import CommunityFinanceView from "@/views/finance/CommunityFinanceView";
import FinanceStatisticView from "@/views/finance/FinanceStatisticView";
import InputAnalyseView from "@/views/finance/InputAnalyseView";
import OutputAnalyseView from "@/views/finance/OutputAnalyseView";
import InputFinanceView from "@/views/finance/InputFinanceView";
import OutputFinanceView from "@/views/finance/OutputFinanceView";
import ActivityDetailView from "@/views/activity/ActivityDetailView";
import CommunityResidentDataView from "@/views/resident/CommunityResidentDataView";
import GetTenantInformation from "@/views/resident/GetTenantInformation";
import BookHistoryView from "@/views/meeting/BookHistoryView";
import UpdatePasswordView from "@/views/UpdatePasswordView";
import AdminMainView from "@/views/admin/AdminMainView";
import DataStatisticView from "@/views/yiqing/DataStatisticView";
import DailyAdmin from "@/views/admin/DailyAdmin";
import EditIntroductionView from "@/views/admin/EditIntroductionView";
import EditStructView from "@/views/admin/EditStructView";
import EditEmployeeView from "@/views/admin/EditEmployeeView";
import EditLeaderView from "@/views/admin/EditLeaderView";
import overView from "@/views/overView"
<<<<<<< HEAD
import TokenList from "@/views/TokenList"
import GetTelegramInformation from "@/views/resident/GetTelegramInformation"
// import axios from 'axios';
=======
import GetYoutubeInformation from "@/views/resident/GetYoutubeInformation"
import keywordView from "@/views/resident/keywordView"
import KeyInformation from "@/views/resident/KeyInformation"
import axios from 'axios';
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f

const routes = [
  {
    path: '/test',
    component: TestView
  },
  {
    path: '/main',
<<<<<<< HEAD
    name: 'main',
    meta: {
      title: '首页'
    },
    component: MainView,
    children: [

      {
        path: "/bookMeeting",
        name: '预约会议',
=======
    name:'main',
    meta:{
      title:'首页'
    },
    component: MainView,
    children:[
      {
        path:"/bookMeeting",
        name:'预约会议',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: BookMeetingView
      },
      {
        path: "/recordMeeting",
<<<<<<< HEAD
        name: '会议记录',
=======
        name:'会议记录',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: RecordMeetingView
      },
      {
        path: "/bookHistory",
<<<<<<< HEAD
        name: '预约记录',
=======
        name:'预约记录',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: BookHistoryView
      },
      {
        path: "/historyMeeting",
<<<<<<< HEAD
        name: '查看历史会议',
        component: HistoryMeetingView
      },
      {
        path: "/addActivity",
        name: '添加活动',
        component: AddActivityView
      },
      {
        path: "/activityDetail",
        name: '活动详情',
=======
        name:'查看历史会议',
        component:HistoryMeetingView
      },
      {
        path: "/addActivity",
        name:'添加活动',
        component:AddActivityView
      },
      {
        path: "/activityDetail",
        name:'活动详情',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: ActivityDetailView
      },
      {
        path: "/lookActivity",
<<<<<<< HEAD
        name: '查看历史活动台账',
        component: LookActivityView
      },
      {
        path: '/my',
        name: '个人信息',
=======
        name:'查看历史活动台账',
        component:LookActivityView
      },
      {
        path: '/my',
        name:'个人信息',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: MyView
      },
      {
        path: '/updateInformation',
<<<<<<< HEAD
        name: '编辑资料',
=======
        name :'编辑资料',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: UpdateInformationView
      },
      {
        path: '/meetingMessage',
        component: MeetingMessageView
      },
      {
        path: '/meetingDetail',
<<<<<<< HEAD
        name: '会议详情',
        component: MeetingDetailView
      },
      {
        path: '/communityIntroduction',
        name: '社区简介',
        component: CommunityIntroductionView
      },
      {
        path: '/communityStruct',
        name: '党群结构',
=======
        name:'会议详情',
        component: MeetingDetailView
      },
      {
        path:'/communityIntroduction',
        name:'社区简介',
        component: CommunityIntroductionView
      },
      {
        path:'/communityStruct',
        name:'党群结构',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: CommunityStructView
      },
      {
        path: '/findPerson',
<<<<<<< HEAD
        name: '一键查找',
=======
        name:'一键查找',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: FindPersonView
      },
      {
        path: '/reportDaily',
<<<<<<< HEAD
        name: '每日一报',
=======
        name:'每日一报',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: ReportDailyView
      },
      {
        path: '/getResidentInformation',
<<<<<<< HEAD
        name: '居民信息',
=======
        name:'居民信息',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: GetResidentInformation
      },
      {
        path: '/getTenantInformation',
<<<<<<< HEAD
        name: '查看租户信息',
=======
        name :'查看租户信息',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: GetTenantInformation
      },
      {
        path: '/employee',
<<<<<<< HEAD
        name: '领导集体',
=======
        name:'领导集体',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: EmployeeView
      },
      {
        path: '/employeeDetail',
<<<<<<< HEAD
        name: '领导详细信息',
=======
        name:'领导详细信息',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: EmployeeDetailView
      },
      {
        path: '/reportWarm',
<<<<<<< HEAD
        name: '暖心台账登记',
=======
        name:'暖心台账登记',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: ReportWarmView
      },
      {
        path: '/addCommunityCondition',
<<<<<<< HEAD
        name: '干部走访民情登记',
=======
        name:'干部走访民情登记',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: AddCommunityConditionView
      },
      {
        path: '/communityCondition',
<<<<<<< HEAD
        name: '走访民情记录查看',
=======
        name:'走访民情记录查看',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: CommunityConditionView
      },
      {
        path: '/inputFinance',
<<<<<<< HEAD
        name: '收入登记',
=======
        name :'收入登记',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: InputFinanceView
      },
      {
        path: '/outputFinance',
<<<<<<< HEAD
        name: '支出登记',
=======
        name:'支出登记',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: OutputFinanceView
      },
      {
        path: '/communityFinance',
<<<<<<< HEAD
        name: '社区财务',
        component: CommunityFinanceView
      },
      {
        path: '/financeStatistic',
        name: '年度财务统计',
        component: FinanceStatisticView
      },
      {
        path: '/inputAnalyse',
        name: '收入分析',
        component: InputAnalyseView
      },
      {
        path: '/outputAnalyse',
        name: '支出分析',
        component: OutputAnalyseView
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
        path: '/TokenList',
        name: 'token',
        component: TokenList
      }, {
        path: '/GetTelegramInformation',
        name: 'getTelegramInformation',
        component: GetTelegramInformation
=======
        name:'社区财务',
        component: CommunityFinanceView
      },
      {
        path:'/financeStatistic',
        name:'年度财务统计',
        component: FinanceStatisticView
      },
      {
        path:'/inputAnalyse',
        name:'收入分析',
        component: InputAnalyseView
      },
      {
        path:'/outputAnalyse',
        name:'支出分析',
        component: OutputAnalyseView
      },
      {
        path:'/updatePassword',
        name:'密码修改',
        component: UpdatePasswordView
      },
      {
        path:'/overView',
        name:'总览',
        component: overView
      },
      {
        path: '/keyword',
        name:'关键词',
        component: keywordView
      },
      {
        path:'/getYoutubeInformation',
        name:'油管',
        component: GetYoutubeInformation
      },
      {
        path:'/keyInformation',
        name:'关键信息',
        component: KeyInformation
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
      },
    ]
  },
  {
    path: '/communityResidentData',
    name: '社区居民信息',
    component: CommunityResidentDataView
  },
  {
<<<<<<< HEAD
    path: '/dataStatistic',
=======
    path:'/dataStatistic',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
    name: '防疫信息统计',
    component: DataStatisticView
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
  {
    path: '/adminmain',
<<<<<<< HEAD
    name: '管理首页',
    component: AdminMainView,
    children: [
      {
        path: '/salaryAccount',
        name: '工资账套',
=======
    name:'管理首页',
    component: AdminMainView,
    children: [
      {
        path:'/salaryAccount',
        name:'工资账套',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: SalaryAccountView
      },
      {
        path: '/dailyAdmin',
<<<<<<< HEAD
        name: '查看每日一报',
=======
        name:'查看每日一报',
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f
        component: DailyAdmin
      },
      {
        path: '/editIntroduction',
        name: '社区信息编辑',
        component: EditIntroductionView
      },
      {
        path: '/editStruct',
        name: '党群结构编辑',
        component: EditStructView
      },
      {
        path: '/editEmployee',
        name: '领导信息编辑',
        component: EditEmployeeView
      },
      {
        path: '/editLeader',
        name: '领导信息修改',
        component: EditLeaderView
      }

    ]
  }
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
<<<<<<< HEAD
// router.beforeEach((to, from, next) => {
//   if (to.path === '/' || to.path === '/register') { // 如果跳转登录页面,则移除token
//     localStorage.removeItem('jwt_token')
//     next()
//   } else {
//     let token = localStorage.getItem('jwt_token');
//     if (token === null || token === '') { //token不存在页跳转到登录页面
//       router.replace({path:'/'})
//     } else {
//       // 检验token是否正确
//       axios({
//         url: 'http://10.16.104.183:8181/user/account/info', //在controller中写一个接口用来token校验
//         method: 'get',
//         //将token信息保存在header里
//         headers: {
//           Authorization: "Bearer " + token,
//         }
//       }).then((resp) => {
//         if (resp.data.error_message != "success") {
//           console.log('检验失败')
//           router.replace({path:'/'}) // 如果token失效,返回到登录页面
//         }
//       })
//       next();
//     }
//   }
// })
=======
router.beforeEach((to, from, next) => {
  if (to.path === '/' || to.path === '/register') { // 如果跳转登录页面,则移除token
    localStorage.removeItem('jwt_token')
    next()
  } else {
    let token = localStorage.getItem('jwt_token');
    if (token === null || token === '') { //token不存在页跳转到登录页面
      router.replace({path:'/'})
    } else {
      // 检验token是否正确
      axios({
        url: 'http://localhost:8181/user/account/info', //在controller中写一个接口用来token校验
        method: 'get',
        //将token信息保存在header里
        headers: {
          Authorization: "Bearer " + token,
        }
      }).then((resp) => {
        if (resp.data.error_message != "success") {
          console.log('检验失败')
          router.replace({path:'/'}) // 如果token失效,返回到登录页面
        }
      })
      next();
    }
  }
})
>>>>>>> ffc14b02a948452209c8504655d41786bdd0c07f


export default router
