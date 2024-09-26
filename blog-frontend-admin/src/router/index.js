import {createRouter, createWebHashHistory, useRoute} from 'vue-router'
import {ChatDotSquare, ChatSquare, Document, EditPen, ElementPlus, House, Picture, User} from "@element-plus/icons-vue";

import {unauthorized} from "../net/index.js";

const router = createRouter({
     history:createWebHashHistory(import.meta.env.BASE_URL),
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
            ]
        },{
            path:'/index',
             name:'index',
             component:() =>import("../views/IndexView.vue"),
             children:[
                 {
                     path:"/index",
                     name:"index-home",
                     component:() => import("../views/index/IndexPage.vue"),
                     "meat":{
                         "title":"首页",
                     }
                 },{
                     path: "/index/user",
                     name: "index-user",
                     component: () => import("../views/index/UserPage.vue"),
                     "meat":{
                         "title":"用户管理",
                     }
                 },{
                     path: "/index/article",
                     name: "index-article",
                     component: () => import("../views/index/ArticlePage.vue"),
                     "meat":{
                         "title":"博客管理",
                     }
                 },{
                     path: "/index/write",
                     name: "index-write",
                     component: () => import("../views/index/WritePage.vue"),
                     "meat":{
                         "title":"撰写博客",
                     }
                 },{
                     path: "/index/categories",
                     name: "index-categories",
                     component: () => import("../views/index/CategoriesPage.vue"),
                     "meat":{
                         "title":"分类管理",
                     }
                 },{
                     path: "/index/message",
                     name: "index-message",
                     component: () => import("../views/index/MessagePage.vue"),
                     "meat":{
                         "title":"留言管理",
                     }
                 },{
                     path: "/index/comments",
                     name: "index-comments",
                     component: () => import("../views/index/CommentsPage.vue"),
                     "meat":{
                         "title":"评论管理",
                     }
                 },{
                     path: "/index/picture",
                     name: "index-picture",
                     component: () => import("../views/index/PicturePage.vue"),
                     "meat":{
                         "title":"图片管理",
                     }
                 },

             ]
         }
     ]
})

router.beforeEach((to,from,next) =>{
    const isUnauthorized = unauthorized()
    if(to.name.startsWith("welcome-")&&!isUnauthorized){
        next("/index")
    }else if(to.fullPath.startsWith("/index")&&isUnauthorized){
        next("/")
    }else {
        next()
    }
})

export default router