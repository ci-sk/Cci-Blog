import axios from 'axios';

const instance = axios.create({
    baseURL: 'http://localhost:8080/api', // 你的 API 基础 URL
    timeout: 10000, // 请求超时时间
    headers: {
        'Content-Type': 'application/json',
    }
});

// 响应拦截器
instance.interceptors.response.use(
    response => {
        // 对响应数据做点什么
        return response.data;
    },
    error => {
        // 对响应错误做点什么
        return Promise.reject(error);
    }
);

export default instance;