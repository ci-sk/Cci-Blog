import { marked } from 'marked';
import { ref, shallowRef, computed, watch, onMounted } from 'vue';
import Prism from 'prismjs';

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
export function randomColor  (index) {
    const colors = ['#7480ff', '#84cc16', '#f97316',
        '#f472b6', '#16a34a', '#1d4ed8', '#f59e0b', '#db2777', '#14b8a6', '#8b5cf6'
    ];
    return colors[index % colors.length];
}

// 计算#号数量
export  const countHashes = (rawText) => {
    // 使用正则表达式匹配开头的#号
    const match = rawText.match(/^#+/);
    // 如果匹配成功，返回#号的数量，否则返回0
    return match ? match[0].length : 0;
};

// 新增通用ID生成函数
export function  generateId (text){
    const textStr = typeof text === 'object' ? text.text || '' : String(text);
    return textStr.toLowerCase().replace(/[^\w]+/g, '-');
};

// 渲染Markdown
export function renderMarkdown (markdownText) {
    console.log(markdownText)
    // 配置marked
    const render = new marked.Renderer();
    // 修改heading渲染
    render.heading = function(text) {
        const level = countHashes(text.raw);
        const id = generateId(text);
        return `<h${level} id="${id}" class="markdown-body">${text.text}</h${level}>`;
    };
    marked.setOptions({
        renderer: render,
        gfm: true,
        pedantic: false,
        sanitize: false,
    });
    // 渲染Markdown为HTML
    return marked(markdownText);
}

export function generateColorFromText(text) {
    // 简单哈希算法生成颜色
    let hash = 0;
    for (let i = 0; i < text.length; i++) {
        hash = text.charCodeAt(i) + ((hash << 5) - hash);
    }
    // 生成HSL颜色
    const h = Math.abs(hash) % 180;
    return `hsl(${h},76%, 72%)`;
}