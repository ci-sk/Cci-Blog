import {defaultFailure, get, put} from "./index.js";

function getLimitMessage(data,success,failure = defaultFailure){
    console.log(data)
    let params = {
        page: (data.page - 1) * 10,
        limit: 10 * data.page
    };
    if (data.text !== '') {
        params.text = data.text;
    }
    put("api/getLimit/Message", params, (data) => {
        success(data);
    });
}

function getCountMessage(success,failure = defaultFailure){
    get("api/getCount/Message",(data)=>{
        success(data)
    },failure)
}

function DeleteMessage(data,success,failure = defaultFailure){
    put("api/delMessage",{
        id:data.id
    },(data)=>{
        success(data)
    },failure)
}

export {getCountMessage,getLimitMessage,DeleteMessage}