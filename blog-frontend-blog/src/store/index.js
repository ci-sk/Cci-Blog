import { create } from 'zustand-vue';

const useArticleStore = create((set) => ({
    articles: [], // 存储文章列表
    currentArticle: null, // 当前选中的文章
    loading: false, // 加载状态

    // 设置文章列表
    setArticles: (articles) => set({ articles }),

    // 设置当前文章
    setCurrentArticle: (article) => set({ currentArticle: article }),

    // 添加新文章
    addArticle: (article) => set((state) => ({
        articles: [article, ...state.articles]
    })),

    // 更新文章
    updateArticle: (updatedArticle) => set((state) => ({
        articles: state.articles.map(article =>
            article.id === updatedArticle.id ? updatedArticle : article
        )
    })),

    // 删除文章
    deleteArticle: (id) => set((state) => ({
        articles: state.articles.filter(article => article.id !== id)
    })),

    // 设置加载状态
    setLoading: (loading) => set({ loading })
}));

export default useArticleStore;