import {defaultFailure, get, put} from "./index.js";

function getLimitMessage(data,success,failure = defaultFailure){
    put("api/getLimit/Comments",{
        text:data.text,
        page:data.page,
        limit:10
    },(data)=>{
        success(data)
    })
}

function getCountMessage(success,failure = defaultFailure){
    get("api/getCount/Comments",(data)=>{
        success(data)
    },failure)
}

export {getCountMessage,getLimitMessage}