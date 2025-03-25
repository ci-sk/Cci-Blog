//计算过去多长时间
export function timeAgo(dateString) {
    const date = new Date(dateString);
    const now = new Date();
    const diff = now - date;

    // 转换成秒数
    const seconds = Math.floor(diff / 1000);

    // 不同时间段的换算
    const intervals = {
        年: Math.floor(seconds / 31536000),
        月: Math.floor(seconds / 2592000),
        周: Math.floor(seconds / 604800),
        天: Math.floor(seconds / 86400),
        小时: Math.floor(seconds / 3600),
        分钟: Math.floor(seconds / 60),
        秒: seconds
    };

    // 判断使用哪个时间单位
    for (const [unit, value] of Object.entries(intervals)) {
        if (value > 0) {
            return `${value}${unit}前`;
        }
    }

    return '刚刚';
}


// 随机取色
export function randomColor() {
    return '#' + Math.floor(Math.random() * 0xFFFFFF).toString(16);
}