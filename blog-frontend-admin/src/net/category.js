import {get, post, put, del, defaultFailure, getPromise} from './index'

/**
 * 获取分类列表
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
function getCategory(success, failure = defaultFailure) {
    get('/api/getAll/Category', (data) => {
        success(data)
    }, failure)
}

/**
 * 获取分类列表（分页）
 * @param data 查询参数 {text: string, page: number}
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
function getLimitCategory(data, success, failure = defaultFailure) {
    let params = {
        page: (data.page - 1) * 10,
        limit: 10 * data.page
    };
    if (data.text !== '') {
        params.text = data.text;
    }
    put('api/getLimit/Category', params, (data) => {
        success(data)
    }, failure)
}

/**
 * 获取分类总数
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
function getCountCategory(success, failure = defaultFailure) {
    get('/api/getCount/Category', (data) => {
        success(data)
    }, failure)
}

/**
 * 添加分类
 * @param {string} name 分类名称
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
function addCategory(name, success, failure = defaultFailure) {
    put('/api/addCategory', {
        name: name
    }, (data) => {
        success(data)
    }, failure)
}

/**
 * 更新分类
 * @param {number} id 分类ID
 * @param {Object} data 分类信息 {name: string}
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
function updateCategory(id, data, success, failure = defaultFailure) {
    put('/api/updateCategory', {
        id: id,
        name: data.name
    }, (data) => {
        success(data)
    }, failure)
}

/**
 * 删除分类
 * @param {number} id 分类ID
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
function deleteCategory(id, success, failure = defaultFailure) {
    del('/api/delCategory', { id }, (data) => {
        success(data)
    }, failure)
}

/**
 * 获取分类统计信息
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
// function getCategoryStats(success, failure = defaultFailure) {
//     get('/api/category/stats', (data) => {
//         console.log("返回",data)
//         success(data)
//     }, failure)
// }

async function getCategoryStats(){
    return getPromise("api/category/stats")
}

export {
    getCategory,
    getLimitCategory,
    getCountCategory,
    addCategory,
    updateCategory,
    deleteCategory,
    getCategoryStats
}
