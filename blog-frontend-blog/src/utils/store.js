// 获取文章状态和操作
import axios from "../net/index"
import {useArticleStore, useTagStore } from "../store/index";

const { setArticles, setLoading } = useArticleStore();

const {setTags,setTagLoading} = useTagStore();
// 获取文章列表
export const fetchArticles = async () => {
    setLoading(true);
    try {
        const response = await axios.get("/getAll/Article");
        setArticles(response.data);
    } catch (error) {
        console.error("Error fetching articles:", error);
    } finally {
        setLoading(false);
    }
};

export const fetchTags = async () => {
    try {
        const response = await axios.get("/getAll/tag");
        setTags(response.data);
    } catch (error) {
        console.error("Error fetching tags:", error);
    } finally {
        setLoading(false);
    }
};

export const fetchFriends = async () => {
    try {
        const response = await axios.get("/getAll/FriendLink");
        return response.data;
    }catch (error) {
        console.error("Error fetching friends:", error);
    }
}

export const ArticlesCont = async (aid) => {
    try {
        const response = await axios.get(`/getById/article/${aid}`);
        return response.data;
    } catch (error) {
        console.error("Error fetching tags:", error);
    }
};
export const getComments = async (aid) => {
    try {
        const response = await axios.get(`/getById/aCom/${aid}`);
        return response.data;
    } catch (error) {
        console.error("Error fetching tags:", error);
    }
};

export const getIsQQ = async(text)=>{
    try {
        const response = await axios.get(`/getById/Account`,{
            params: {
                text: text,
            },
        });
        return response.data;
    }catch (error) {
        console.error("Error fetching tags:", error);
    }
}

export const createAccount = async (data) => {
    console.log(data)
    try {
        const response = await axios.post(`/auth/addAccount`, data,{
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
        return response.data;
    }catch (error) {
        console.error("Error fetching tags:", error);
    }
}

export const submComment = async (data) => {
    try {
        const response = await axios.post(`/auth/addComment`, data,{
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            }
        });
        return response.data;
    }
    catch (error) {
        console.error("Error fetching tags:", error);
    }
}