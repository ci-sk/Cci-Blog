import {defaultFailure, get, put} from "./index.js";

function getComment(success,failure = defaultFailure){
    get("api/getAll/Comments",(data)=>{
        console.log("@@")
        success(data)
    },failure)
}

function getLimitComment(data,success,failure = defaultFailure){
    put("api/getLimit/Comments",{
        text:data.text,
        page:(data.page-1) * 10,
        limit:10*data.page
    },(data)=>{
        success(data)
    })
}

function getCountComment(success,failure = defaultFailure){
    get("api/getCount/Comments",(data)=>{
        success(data)
    },failure)
}

export {getComment,getCountComment,getLimitComment}