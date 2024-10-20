import {defaultFailure, get, put} from "./index.js";

function insertArticle(data,success,failure = defaultFailure){
    console.log(data)
    put("api/addArt",{
        title:data.title,
        content:data.content,
        desc:data.desc,
        img_url:data.img_url,
        tags:data.tags,
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


export { insertArticle,getArticle }