import {defaultFailure, del, get, getPromise, put, takeAccessToken} from "./index.js";
import axios from "axios";

async function insertArticle(data,success,failure = defaultFailure){
    await put("api/addArticle",{
        aid:data.aid,
        title:data.title,
        content:data.content,
        desc:data.desc,
        img_url:data.img_url,
        tags:data.tags,
        del:data.del,
        categoryId:data.categoryId
    },(data)=>{
        success(data);
    },()=>{
        failure("添加失败", 500, "api/addArt");
    })
}

async function  getArticle() {
    return  getPromise("api/getAll/Article")

}


function DeleteArticle(data,success,failure){
    put("api/delArticle",{
        aid:data
    },()=>{
        success(data);
    },()=>{
        failure("删除失败", 500, "api/deleteArt/"+id);
    })
}

function ArticleLimit(data,success,failure){
    let params = {
        page: (data.page - 1) * 10,
        limit: 10 * data.page
    };
    if (data.text !== '') {
        params.text = data.text;
    }


    put("api/getLimit/Article",params,(data)=>{
        success(data);
    },()=>{
        failure("获取失败", 500, "api/getArt");
    })
}

function getArticleCount(success,failure){
    get("api/getCount/Article",(data)=>{
        success(data);
    },()=>{
        failure("获取失败", 500, "api/getArt");
    })
}

function getArticleByAid(aid,success,failure){
    get(`api/get/article/${aid}`,(data)=>{
        success(data);
    },()=>{
        failure("获取失败", 500, "api/getArt");
    })
}

export { insertArticle,getArticle,DeleteArticle,ArticleLimit,getArticleCount,getArticleByAid }