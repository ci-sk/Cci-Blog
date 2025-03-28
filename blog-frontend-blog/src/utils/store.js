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