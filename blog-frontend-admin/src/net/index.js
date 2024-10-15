// 导入 axios 库用于发送 HTTP 请求
import axios from "axios";
// 导入 Element Plus 的 Message 组件用于显示消息
import { ElMessage } from "element-plus";
import data from "bootstrap/js/src/dom/data.js";

// 定义存储认证令牌的名称
const authItemName = "access_token";

// 默认的请求失败处理函数
const defaultFailure = (message, code, url) => {
    // 在控制台中输出错误信息
    console.warn(`请求地址:${url},状态码:${code},错误信息:${message}`);
    // 使用 Element Plus 的 Message 组件显示警告消息
    ElMessage.warning(message);
};

// 默认的请求错误处理函数
const defaultError = (err) => {
    // 在控制台中输出错误信息
    console.warn(err);
    // 使用 Element Plus 的 Message 组件显示警告消息
    ElMessage.warning("出错啦~~~ 请联系管理员");
};

// 从本地存储中获取访问令牌
function takeAccessToken() {
    // 尝试从 localStorage 中获取令牌，如果没有则从 sessionStorage 中获取
    const str = localStorage.getItem(authItemName) || sessionStorage.getItem(authItemName);
    if (!str) return null;
    // 将获取到的字符串解析为 JSON 对象
    const authObj = JSON.parse(str);
    // 如果令牌已过期，则删除令牌并提示用户重新登录
    if (authObj.expire <= new Date()) {
        deleteAccessToken();
        ElMessage.warning("登录状态已过期,请重新登录");
        return null;
    }
    // 返回令牌
    return authObj.token;
}

// 将访问令牌存储到本地存储中
function storeAccessToken(token, remember, expire) {
    // 创建一个包含令牌和过期时间的对象
    const authObj = { token: token, expire: expire };
    // 将对象转换为字符串
    const str = JSON.stringify(authObj);
    // 根据 remember 参数决定将令牌存储到 localStorage 还是 sessionStorage
    if (remember) {
        localStorage.setItem(authItemName, str);
    } else {
        sessionStorage.setItem(authItemName, str);
    }
}

// 从本地存储中删除访问令牌
function deleteAccessToken() {
    // 从 localStorage 和 sessionStorage 中删除令牌
    localStorage.removeItem(authItemName);
    sessionStorage.removeItem(authItemName);
}

// 获取请求头中的 Authorization 字段
function accessHeader() {
    // 获取令牌，如果没有则返回空对象
    const token = takeAccessToken();
    return token ? { 'Authorization': `Bearer ${takeAccessToken()}`,
        'Content-Type': 'application/x-www-form-urlencoded'
    } : {};
}


// 发送 GET 请求的内部函数
function internalGet(url, header, success, failure, error = defaultError) {
    axios.get(url, { headers: header }).then(({ data }) => {
        if (data.code === 200) {
            success(data.data);
        } else {
            failure(data.message, data.code, url);
        }
    }).catch(err => error(err));
}

// 发送 PUT 请求的内部函数
function internalPut(url, data, header, success, failure, error = defaultError) {
    axios.put(url, data,{
        headers: header
    })
      .then(({ data }) => {
        if (data.code === 200) {
            success(data.data);
        } else {
            failure(data.message, data.code, url);
        }
      }).catch(err => error(err))
}

// 发送 POST 请求的内部函数
function internalPost(url, data, header, success, failure, error = defaultError) {
    axios.post(url, data,{
        headers: header
    }).then(({ data }) => {
        if (data.code === 200) {
            success(data.data);
        } else {
            failure(data.message, data.code, url);
        }
    }).catch(err => error(err));
}

// 发送 DELETE 请求的内部函数
function internalDel(url, data,header, success, failure, error = defaultError) {
    axios.delete(url,{
        headers: header,
        data:data
    })
        .then(({data}) => {
           success(data)
        })
        .catch(error => {
            failure(error);
        });

}

// 发送 GET 请求的封装函数
function get(url, success, failure = defaultFailure) {
    internalGet(url, accessHeader(), success, failure);
}

// 发送 POST 请求的封装函数
function post(url, data, success, failure = defaultFailure) {
    internalPost(url, data, accessHeader(), success, failure);
}

// 发送 PUT 请求的封装函数
function put(url, data, success, failure = defaultFailure) {
    internalPut(url, data, accessHeader(), success, failure);
}

// 发送 DELETE 请求的封装函数
function del(url,data,success, failure = defaultFailure) {
    internalDel(url, data,accessHeader(), success, failure);
}

// 登录函数
function login(username, password, remember, success, failure = defaultFailure) {
    // 调用内部 POST 请求函数，发送登录请求
    internalPost('api/auth/login', {
        username: username,
        password: password,
    }, {
        'Content-Type': 'application/x-www-form-urlencoded'
    }, (data) => {
        // 登录成功后，存储令牌
        storeAccessToken(data.token, remember, data.expire);
        // 使用 Element Plus 的 Message 组件显示成功消息
        ElMessage.success(`登录成功,欢迎${data.username}来到我们的系统`);
        // 调用成功回调函数
        success(data);
    }, failure);
}

// 登出函数
function logout(success, failure = defaultFailure) {
    // 调用 GET 请求函数，发送登出请求
    get('api/auth/logout', {}, () => {
        // 登出成功后，删除令牌
        deleteAccessToken();
        // 使用 Element Plus 的 Message 组件显示成功消息
        ElMessage.success("退出登录成功,欢迎您再次使用");
        // 调用成功回调函数
        success();
    }, failure);
}

//文件上传
function uploadFile(data,success,failure){
    internalPost('api/file/upload',{
        multipartFile: data
    },{
        'Authorization': `Bearer ${takeAccessToken()}`,
        "Content-Type": "multipart/form-data"
    },(res)=>{
        ElMessage.success("上传成功");
        success(res);
    },(res)=>{
        failure( res,500, "api/file/upload");
    })
}

// 判断用户是否未登录
function unauthorized() {
    // 返回是否存在访问令牌
    return!takeAccessToken();
}

// 导出函数
export { login, logout, get, post,put,del,defaultFailure, unauthorized,uploadFile};

