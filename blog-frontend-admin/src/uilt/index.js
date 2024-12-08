//时间格式转化
const formatTime = (value) => {
    if (!value) return '';
    
    // 处理形如 "Fri Sep 27 08:35:02 CST 2024" 的时间格式
    if (typeof value === 'string') {
        // 使用正则表达式提取日期部分
        const match = value.match(/(\w+)\s+(\w+)\s+(\d+)\s+(\d+):(\d+):(\d+)\s+CST\s+(\d+)/);
        if (match) {
            const [_, dayOfWeek, month, day, hours, minutes, seconds, year] = match;
            const monthMap = {
                'Jan': '01', 'Feb': '02', 'Mar': '03', 'Apr': '04',
                'May': '05', 'Jun': '06', 'Jul': '07', 'Aug': '08',
                'Sep': '09', 'Oct': '10', 'Nov': '11', 'Dec': '12'
            };
            
            const monthNum = monthMap[month];
            const paddedDay = day.padStart(2, '0');
            
            return `${year}-${monthNum}-${paddedDay} ${hours}:${minutes}:${seconds}`;
        }
    }
    
    // 如果不是特定格式，使用默认处理
    const date = new Date(value);
    if (isNaN(date.getTime())) {
        return '';
    }
    
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0');
    const day = String(date.getDate()).padStart(2, '0');
    const hours = String(date.getHours()).padStart(2, '0');
    const minutes = String(date.getMinutes()).padStart(2, '0');
    const seconds = String(date.getSeconds()).padStart(2, '0');
    
    return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
}


const changeTime = (value)=>{
    value.forEach((item) => {
        item.time = formatTime(item.time);
        if(item.children){
            changeTime(item.children);
        }
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