import {createRouter, createWebHashHistory} from 'vue-router'

import {unauthorized} from "../net/index.js";
import {ElMessage} from "element-plus";
import NProgress from 'nprogress'   // 导入 nprogress
import 'nprogress/nprogress.css'
import LoadingPage from "../components/LoadingPage.vue";   // 导入样式，否则看不到效果

// NProgress.configure({ showSpinner: true })   // 显示右上角螺旋加载提示


const router = createRouter({
     history:createWebHashHistory(import.meta.env.BASE_URL),
    // history:createWebHistory(),
     routes:[
        {
            path:'/',
            name:'welcome',
            component:()=>import('../views/WelcomeView.vue'),
            children:[
                {
                    path:'/',
                    name:'welcome-login',
                    component:()=>import('../views/welcome/WelcomePage.vue')
                }
            ],
            
        },
         {
            path:'/index',
             component:() =>import("../views/IndexView.vue"),
             children:[
                 {
                     path:"/index",
                     name:"首页",
                     component:() => import("../views/index/IndexPage.vue"),
                 },
                 {
                    path: "/article",
                     name:"文章管理",
                     children:[
                         {
                             path:"/article/index",
                             name:"博客管理",
                             component: () => import("../views/index/ArticlePage.vue"),
                         },
                         {
                             path: "/article/write",
                             name: "撰写",
                             component: () => import("../views/index/WritePage.vue"),
                         },
                         {
                             path:"/article/write/:item",
                             name: "修改",
                             component: () => import("../views/index/WritePage.vue"),
                         },
                         {
                             path: "/article/tags",
                             name: "标签",
                             component: () => import("../views/index/Tag.vue"),
                         },
                         {
                             path: "/article/category",
                             name: "分类",
                             component: () => import("../views/index/CategoryPage.vue"),
                         },

                     ]
                 },
                 {
                     path:"/webSite",
                     name:"网站",
                     children:[
                         {
                             path: "/webSite/index",
                             name: "系统",
                             component: () => import("../views/index/SystemPage.vue"),
                         },
                         {
                             path: "/webSite/friend",
                             name: "友链",
                             component: () => import("../views/index/FriendLinkPage.vue"),
                         },
                         {
                             path: "/webSite/log",
                             name: "日志",
                             component: () => import("../views/index/LogPage.vue"),
                         }
                     ]
                 },
                 {
                   path:"/message",
                   name:"消息管理",
                   children:[
                       {
                         path: "/message/index",
                         name: "消息",
                         component: () => import("../views/index/MessagePage.vue"),
                       },
                       {
                         path: "/message/comments",
                         name: "评论",
                         component: () => import("../views/index/CommentsPage.vue"),
                       },
                   ]
                 },
                 {
                     path:"/root",
                     name:"权限",
                     children:[
                         {
                             path: "/root/user",
                             name: "用户",
                             component: () => import("../views/index/UserPage.vue"),
                         },
                     ]
                 },

             ],
         },
         {
             path: '/loading',
             name: 'LoadingPage',
             component:() => import("../components/Loading.vue"), // 加载页面组件
         },
     ]
})

router.beforeEach((to,from,next) =>{
    NProgress.start()   // 开始加载
    const isUnauthorized = unauthorized()
    if(to.name.startsWith("welcome-")&&!isUnauthorized){
        next("/index")
    }else if((to.fullPath.startsWith("/index") ||to.fullPath.startsWith("/article") ||
        to.fullPath.startsWith("/webSite") || to.fullPath.startsWith("/root")|| to.fullPath.startsWith("/message"))  &&isUnauthorized){
        ElMessage.warning("登录失效，请重新登录")
        next("/")
    }else {
        next()
    }
    NProgress.done()
})

export default router