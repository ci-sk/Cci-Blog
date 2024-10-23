//时间格式转化
const formatTime = (value) => {
    if (!value) return '';
    const date = new Date(value);
    const year = date.getFullYear();
    const month = date.getMonth() + 1;
    const day = date.getDate();
    const hours = date.getHours();
    const minutes = date.getMinutes();
    const seconds = date.getSeconds();
    return `${year}年${month}月${day}日 ${hours}:${minutes}:${seconds}`;
}


const changeTime = (value)=>{
    value.forEach((item) => {
        item.time = formatTime(item.time);
    })
    return value;
}

const getTags = (value)=>{
    value.forEach((item) => {
        item.tags = item.tags.split(',');
    })
    return value
}

export {formatTime,changeTime,getTags}