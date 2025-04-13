import {ElMessage} from "element-plus";
import {defaultFailure, del, get, put} from "./index.js";

function getUserInfo(success, failure = defaultFailure) {
    // 调用 GET 请求函数，获取用户信息
    get('api/getAll/Account', (data)=>{
        // 调用成功回调函数，传递用户信息数据
        success(data);
    }, () => {
        failure("获取用户信息失败", 500, "api/find/Account");
    }, failure)
}

function AccountLimit(data,success, failure = defaultFailure) {
    let params = {
        page: (data.page - 1) * 10,
        limit: 10 * data.page
    };
    if (data.text !== '') {
        params.text = data.text;
    }

    put('api/getLimit/Account',params,(data)=>{
        success(data);
    }, () => {
        // 调用失败回调函数，传递错误信息
        failure("获取失败", 500, "api/getAccountText");
    })
}

function DelAccount(data,success,failure = defaultFailure) {
    del('api/delAccount', {
        uid: parseInt(data)
    },()=>{
        ElMessage.success("删除成功");
        success();
    }, () => {
        // 调用失败回调函数，传递错误信息
        failure("删除失败", 500, "api/delAccount");
    })
}

function getAccountCount(success,failure = defaultFailure){
    get('api/getCount/Account',
        (res)=>{
            success(res);
        },(res)=>{
            failure( res,500, "api/getAccount");
        })
}

export {getAccountCount,getUserInfo,DelAccount,AccountLimit}
