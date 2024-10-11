<script setup>
import { ref } from 'vue';
import { MdEditor } from 'md-editor-v3';

import 'md-editor-v3/lib/style.css';

import {useDark} from "@vueuse/core";

const text = ref('# Hello Editor');

const isDark = useDark();



</script>

<template>
  <MdEditor :theme="isDark?'dark':'light'" v-model="text" />
</template>

<style scoped>
MdEditor{
  //background: var(--el-bg-color);

}
</style>