import { createRouter, createWebHistory } from 'vue-router';

const routes = [
    {
        path: '/',
        name: 'Home',
        component: () => import('../views/HomeView.vue'), // 动态导入
    },
    {
        path: '/blog',
        name: 'Blog',
        component: () => import('../views/BlogView.vue'), // 动态导入
    },
    {
        path: '/blog/category',
        name: 'Category',
        component: () => import('../views/CategoryView.vue'), // 动态导入
    },
    {
        path: '/blog/archive',
        name: 'Archive',
        component: () => import('../views/ArchiveView.vue'), // 动态导入
    },
    {
        path: '/blog/tag',
        name: 'Tag',
        component: () => import('../views/TagView.vue'), // 动态导入
    },
    {
        path: '/social/friends',
        name: 'Friends',
        component: () => import('../views/FriendsView.vue'), // 动态导入
    },
    {
        path: '/social/guestbook',
        name: 'Guestbook',
        component: () => import('../views/GuestbookView.vue'), // 动态导入
    },
    {
        path: '/personal/about',
        name: 'About',
        component: () => import('../views/AboutView.vue'), // 动态导入
    },
    {
        path: '/personal/essays',
        name: 'Essays',
        component: () => import('../views/EssaysView.vue'), // 动态导入
    },
    {
        path: '/personal/tools',
        name: 'Tools',
        component: () => import('../views/ToolsView.vue'), // 动态导入
    },
    {
        path: '/blog/article',
        name: 'Article',
        component: () => import('../views/ArticleView/Articles.vue'), // 动态导入
    }
];

const router = createRouter({
    history: createWebHistory(),
    routes,
});

export default router;