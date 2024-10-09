import {createRouter, createWebHashHistory, createWebHistory, useRoute} from 'vue-router'

import {unauthorized} from "../net/index.js";

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
                 },{
                     path: "/index/user",
                     name: "index-user",
                     component: () => import("../views/index/UserPage.vue"),
                 },{
                     path: "/index/article",
                     name: "index-article",
                     component: () => import("../views/index/ArticlePage.vue"),
                 },{
                     path: "/index/write",
                     name: "index-write",
                     component: () => import("../views/index/WritePage.vue"),
                 },{
                     path: "/index/categories",
                     name: "index-categories",
                     component: () => import("../views/index/CategoriesPage.vue"),
                 },{
                     path: "/index/message",
                     name: "index-message",
                     component: () => import("../views/index/MessagePage.vue"),
                 },{
                     path: "/index/comments",
                     name: "index-comments",
                     component: () => import("../views/index/CommentsPage.vue"),
                 },{
                     path: "/index/picture",
                     name: "index-picture",
                     component: () => import("../views/index/PicturePage.vue"),
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