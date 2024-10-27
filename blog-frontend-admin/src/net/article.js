import {defaultFailure, del, get, put} from "./index.js";

async function insertArticle(data,success,failure = defaultFailure){
    console.log(data)
    await put("api/addArt",{
        aid:data.aid,
        title:data.title,
        content:data.content,
        desc:data.desc,
        img_url:data.img_url,
        tags:data.tags,
        del:data.del
    },(data)=>{
        success(data);
    },()=>{
        failure("添加失败", 500, "api/addArt");
    })
}

function getArticle(success,failure = defaultFailure){
    get("api/find/Art",(data)=>{
        success(data);
    },()=>{
        failure("获取失败", 500, "api/getArt");
    })
}

function DeleteArticle(data,success,failure){
    console.log("@@@",data)
    put("api/delArt",{
        aid:data
    },()=>{
        success(data);
    },()=>{
        failure("删除失败", 500, "api/deleteArt/"+id);
    })
}

function ArticleLimit(data,success,failure){
    console.log("@@@",data)
    put("api/getArticleLimit",{
        text:data.text,
        page:data.page,
        limit:10
    },(data)=>{
        success(data);
    },()=>{
        failure("获取失败", 500, "api/getArt");
    })
}

function getArticleCount(success,failure){
    get("api/getArtCount",(data)=>{
        success(data);
    },()=>{
        failure("获取失败", 500, "api/getArt");
    })
}



export { insertArticle,getArticle,DeleteArticle,ArticleLimit,getArticleCount }