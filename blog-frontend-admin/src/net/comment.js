import {defaultFailure, get} from "./index.js";

function getComment(success,failure = defaultFailure){
    get("api/find/Comments",(data)=>{
        console.log("@@")
        success(data)
    },failure)
}

export {getComment}