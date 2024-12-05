import { get, post, put, del, defaultFailure } from './index'

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
    put('api/getLimit/Category', {
        text: data.text || '',
        page: data.page + 1,  // 后端会自动减1
        size: 10
    }, (data) => {
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
 * @param {Object} data 分类信息 {name: string, description: string}
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
function addCategory(data, success, failure = defaultFailure) {
    put('/api/addCategory', {
        name: data.name,
        description: data.description
    }, (data) => {
        success(data)
    }, failure)
}

/**
 * 更新分类
 * @param {Object} data 分类信息 {id: number, name: string, description: string}
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
function updateCategory(data, success, failure = defaultFailure) {
    put('/api/updateCategory', {
        id: data.id,
        name: data.name,
        description: data.description
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

export {
    getCategory,
    getLimitCategory,
    getCountCategory,
    addCategory,
    updateCategory,
    deleteCategory
}
