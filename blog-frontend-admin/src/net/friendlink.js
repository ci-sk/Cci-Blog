import { get, post, put, del, defaultFailure } from './index'

/**
 * 获取所有友链
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
function getFriendLinks(success, failure = defaultFailure) {
    get('api/getAll/FriendLink', (data) => {
        success(data)
    }, failure)
}

/**
 * 获取友链列表（分页）
 * @param {Object} data 查询参数 {page: number, size: number, text: string}
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
function getLimitFriendLinks(data, success, failure = defaultFailure) {
    put('api/getLimit/FriendLink?' + new URLSearchParams({
        text: data.text || '',
        page: data.page || 0,
        size: data.size || 10
    }).toString(), null, (data) => {
        success(data)
    }, failure)
}

/**
 * 获取友链总数
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
function getFriendLinkCount(success, failure = defaultFailure) {
    get('api/getCount/FriendLink', (data) => {
        success(data)
    }, failure)
}

/**
 * 搜索友链
 * @param {string} keyword 搜索关键词
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
function searchFriendLinks(keyword, success, failure = defaultFailure) {
    get('api/getSearch/FriendLink', {
        keyword: keyword
    }, (data) => {
        success(data)
    }, failure)
}

/**
 * 添加或更新友链
 * @param {Object} data 友链信息 {fid?: number, fname: string, describes: string, url: string, icon: string}
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
function saveFriendLink(data, success, failure = defaultFailure) {
    put('api/saveFriendLink', data, (data) => {
        success(data)
    }, failure)
}

/**
 * 删除友链
 * @param {number} id 友链ID
 * @param {Function} success 成功回调
 * @param {Function} failure 失败回调
 */
function deleteFriendLink(id, success, failure = defaultFailure) {
    del('api/delFriendLink', { id }, (data) => {
        success(data)
    }, failure)
}

export {
    getFriendLinks,
    getLimitFriendLinks,
    getFriendLinkCount,
    searchFriendLinks,
    saveFriendLink,
    deleteFriendLink
}
