<script setup>
import {useDark, useToggle} from "@vueuse/core";
import FIleUpdate from "./components/FIleUpdate.vue";

useDark({
  selector: 'html',
  attribute:'class',
  valueDark:"dark",
  valueLight:"light"
})
useDark({
  onChanged(dark)  {useToggle(dark)}
})



</script>

<template>
  <router-view/>
<!--  <FIleUpdate/>-->
</template>

<style>


</style>