import {
    ChatSquare,
    Document,
    EditPen,
    ElementPlus,
    House,
    Message,
    Picture, Setting,
    User,
    Location, Key, MilkTea, Menu
} from "@element-plus/icons-vue";


export default[
    {
        path:"/index",
        name:"home",
        label:"首页",
        icon:House,

    },
    {
        label:"文章管理",
        icon:Document,
        name:"article",
        children:[
            {
                path:"/article",
                name:"article",
                label:"博客管理",
                icon:Document,
            },
            {
                path:"/article/write",
                name:"write",
                label:"撰写博客",
                icon:EditPen,
            },
            {
                path:"/article/tags",
                name:"tags",
                label:"标签管理",
                icon:ElementPlus,
            },
            {
                path:"/article/category",
                name:"category",
                label:"分类管理",
                icon:Location,
            },
            {
                path:"/article/picture",
                name:"picture",
                label:"图片管理",
                icon:Picture,
            },
        ],
    },
    {
        label:"网站设置",
        name:"setting",
        icon:Key,
        children:[
            {
                path: "/webSite",
                name: "webSite",
                label: "网站设置",
                icon: Key,
                url: "/webSite"
            },
            {
                path: "/webSite/friend",
                name: "friend",
                label: "友链管理",
                icon: MilkTea,
                url: "/friend"
            },
        ],
    },
    {
        label:"消息管理",
        name:"message",
        icon:Message,
        url:"/message",
        children: [
            {
                path:"/message",
                name:"message",
                label:"留言管理",
                icon:Message,
                url:"/message"
            },
            {
                path:"/message/comments",
                name:"comments",
                label:"评论管理",
                icon:ChatSquare,
                url:"/comments"
            },
        ]
    },
    {
        label: '权限管理',
        icon: Setting,
        children: [
            {
                path:"/root/user",
                name:"user",
                label:"用户管理",
                icon:User,
                url:"/user"
            },
            {
                path: '/root/menu',
                name: 'menu',
                label: '菜单管理',
                icon: Menu,
                url: 'Power/Menu'
            },
        ]
    },
]