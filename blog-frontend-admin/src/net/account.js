import {ElMessage} from "element-plus";
import {defaultFailure, del, get, put} from "./index.js";

function getUserInfo(success, failure = defaultFailure) {
    // 调用 GET 请求函数，获取用户信息
    get('api/find/Account', (data)=>{
        // 调用成功回调函数，传递用户信息数据
        success(data);
    }, () => {
        failure("获取用户信息失败", 500, "api/find/Account");
    }, failure)
}

function getAccountText(data, success, failure = defaultFailure) {

    put('/api/getAccountByText',{
        text:data
    },(data)=>{
        ElMessage.success("获取成功");
        success(data);
    }, () => {
        // 调用失败回调函数，传递错误信息
        failure("获取失败", 500, "api/getAccountText");
    })
}

function AccountLimit(data,success, failure = defaultFailure) {
    put('api/getAccountLimit',{
        page:data,
        limit:10
    },(data)=>{
        success(data);
    }, () => {
        // 调用失败回调函数，传递错误信息
        failure("获取失败", 500, "api/getAccountText");
    })
}

function DelAccount(data,success,failure = defaultFailure) {
    del('api/delAccount', {
        uid:data
    },()=>{
        ElMessage.success("删除成功");
        success();
    }, () => {
        // 调用失败回调函数，传递错误信息
        failure("删除失败", 500, "api/delAccount");
    })
}

function getAccountCount(success,failure = defaultFailure){
    get('api/getAccountCount',
        (res)=>{
            success(res);
        },(res)=>{
            failure( res,500, "api/getAccount");
        })
}

export {getAccountCount,getUserInfo,DelAccount,getAccountText,AccountLimit}
