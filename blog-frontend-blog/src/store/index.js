import { create } from 'zustand-vue';

const useArticleStore = create((set) => ({
    articles: [], // 存储文章列表
    currentArticle: null, // 当前选中的文章
    loading: false, // 加载状态

    // 设置文章列表
    setArticles: (newArticles) => set({ articles: newArticles }),
    //拿到当前文章

    // 设置当前文章
    setCurrentArticle: (article) => set({ currentArticle: article }),
    // 设置加载状态
    setLoading: (loading) => set({ loading })
}));

export default useArticleStore;