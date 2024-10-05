import {defineStore} from 'pinia';

export const useCounterStore
    = defineStore("Collapse",{
    state:()=>({
        isCollapse:false,  //导航栏是否折叠
        // 面包屑导航
        tabList:[
            {
                path:'/index',
                name:"home",
                label:"首页",
                icon:"s-home",
                url:"/index"
            }
        ]
    }),
    actions:{
        //修改导航栏展开和收起的方法
        CollapseMenu(state){
            state.isCollapse =!state.isCollapse
        },
        //更新面包屑数据的方法
        SelectMenu(state,item){
            const index = state.tabList.findIndex((item1)=>item1.name === item.name)
            if(index === -1){
                state.tabList.push(item)
            }
        },
        delTab(state,item){
            const index = state.tabList.findIndex((item1)=>item1.name === item.name)
            state.tabList.splice(index,1)
        }
    }
})