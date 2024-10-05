import {
    ChatSquare,
    Document,
    EditPen,
    ElementPlus,
    House,
    Message,
    Picture, Setting,
    User,
    Location
} from "@element-plus/icons-vue";


export default[
    {
        path:"/index",
        name:"home",
        label:"首页",
        icon:House,
        url:"/index"
    },
    {
        path:"/index/user",
        name:"user",
        label:"用户管理",
        icon:User,
        url:"/index/user"
    },
    {
        path:"/index/article",
        name:"article",
        label:"博客管理",
        icon:Document,
        url:"/index/article"
    },
    {
        path:"/index/write",
        name:"write",
        label:"撰写博客",
        icon:EditPen,
        url:"/index/write"
    },
    {
        path:"/index/categories",
        name:"categories",
        label:"分类管理",
        icon:ElementPlus,
        url:"/index/categories"
    },
    {
        path:"/index/message",
        name:"message",
        label:"留言管理",
        icon:Message,
        url:"/index/message"
    },
    {
        path:"/index/comments",
        name:"comments",
        label:"评论管理",
        icon:ChatSquare,
        url:"/index/comments"
    },
    {
        path:"/index/picture",
        name:"picture",
        label:"图片管理",
        icon:Picture,
        url:"/index/picture"
    },
    {
        label: '其他',
        icon: Location,
        children: [
            {
                path: '/page1',
                name: 'page1',
                label: '页面1',
                icon: Setting,
                url: 'Other/PageOne'
            },
            {
                path: '/page2',
                name: 'page2',
                label: '页面2',
                icon: Setting,
                url: 'Other/PageTwo'
            }
        ]
    }
]