import { createRouter, createWebHistory } from 'vue-router';

const routes = [
    //首页
    {
        path: '/',
        name: 'Home',
        component: () => import('../views/HomeView.vue'),
    },
    //归档
    {
        path: '/blog/archive',
        name: 'Archive',
        component: () => import('../views/ArchiveView.vue'), // 动态导入
    },
    //随笔
    {
        path: '/personal/essays',
        name: 'Essays',
        component: () => import('../views/EssaysView.vue'),
    },
    //友链
    {
        path: '/social/friends',
        name: 'Friends',
        component: () => import('../views/FriendsView.vue'),
    },
    //关于
    {
        path: '/personal/about',
        name: 'About',
        component: () => import('../views/AboutView.vue'),
    },
    //博客
    {
        path: '/blog/article/:id',
        name: 'Article',
        component: () => import('../views/ArticleView/Articles.vue'),
        props: true,  // 将路由参数作为组件的 props
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;