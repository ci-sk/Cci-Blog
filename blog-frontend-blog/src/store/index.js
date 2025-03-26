import { create } from 'zustand-vue';

export const useArticleStore = create((set) => ({
    articles: [], // 存储文章列表
    currentArticle: null, // 当前选中的文章
    loading: false, // 加载状态
    // 设置文章列表
    setArticles: (newArticles) => set({ articles: newArticles }),
    setCurrentArticle: (article) => set({ currentArticle: article }),
    // 设置加载状态
    setLoading: (loading) => set({ loading })
}));

export const useTagStore = create((set) => ({
    tags: [], // 存储标签列表
    loading: false, // 加载状态
    // 设置标签列表
    setTags: (newTags) => set({ tags: newTags }),
    // 设置加载状态
    setLoading: (loading) => set({ loading })
}))
