<script setup>
import router from "../router/index.js";
import {useCounterStore} from "../store/index.js";
import {useRoute} from "vue-router";

const Store = useCounterStore()

function changeMenu(item){
  router.push(item.path)
}

const route = useRoute()

const tags = Store.tabList;

function handleClose(item, index) {
  // 删除面包屑数据
  Store.delTab(Store,item)
  // 如果删除的刚好是自己
  if (item.path === route.path) {
    const length = tags.length
    // 如果删除的是最后一个:跳到前一个
    if (length === index) {
      router.push(this.tags[index - 1].path)
    }
    // 不是最后一个:往后一个
    else {
      router.push(this.tags[index].path)
    }
  }
}


</script>

<template>
  <div class="flex gap-2 tabs">
    <el-tag v-for="(item,index) in tags" :key="item.path" :closable="item.name !== 'home'"
    :effect="item.name!== 'home'? 'dark' : 'plain'"
     @click="changeMenu(item)"
     @close="handleClose(item,index)"
    >
      {{ item.label }}
    </el-tag>
  </div>
</template>

<style lang="less" scoped>
.tabs{
  padding: 10px 20px 0 ;

  .el-tag{
    width: 80px;
    height: 30px;
    margin-right: 15px;
    cursor: pointer;
  }
}

</style>