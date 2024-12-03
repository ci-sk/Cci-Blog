import {ElMessage} from "element-plus";
import {defaultFailure, del, get, getPromise, put} from "./index.js";

//添加
function insertTag(data,success,failure){
    let val = data.value || data;
    put("api/addTag", {
        tagName:val
    },(data)=>{
        success(data);
    },()=>{
        ElMessage.error("添加失败");
    })
}

//获取标签列表
function getTag(success,failure){
    get('api/getAll/tag',(data)=>{
        success(data);
    }, () => {
        failure("获取用户信息失败", 500, "api/find/Account");
    })
}




//分页
function TagLimit(data,success, failure = defaultFailure) {
    put('api/getLimit/Tag',{
        page:data,
        limit:10
    },(data)=>{
        success(data);
    }, () => {
        // 调用失败回调函数，传递错误信息
        failure("获取失败", 500, "api/getAccountText");
    })
}

//删除
function DelTag(data,success,failure){
    del("/api/delTag",{
        tid:data
    },(data)=>{
        ElMessage.success("删除成功");
        success(data);
    },()=>{
        failure("删除失败", 500, "api/delAccount");
    })
}

//查找
function TagSearch(data, success, failure = defaultFailure) {

    put('/api/getSearch/Tag', {
        text: data.value
    }, (data) => {
        ElMessage.success("获取成功");
        success(data);
    }, () => {
        // 调用失败回调函数，传递错误信息
        failure("获取失败", 500, "api/getAccountText");
    })
}

//获取标签数量
function getTagCount(success,failure = defaultFailure){
    get('api/getCount/Tag',
        (res)=>{
            success(res);
        },(res)=>{
            failure( res,500, "api/getAccount");
        })
}

async function  getAllTag() {
    return  getPromise("api/getAll/tag")
}

export {getTag,insertTag,DelTag,TagLimit,TagSearch,getTagCount,getAllTag}
