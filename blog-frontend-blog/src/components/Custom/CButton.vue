<script setup>
import {computed} from "vue";

const props = defineProps({
  primaryColor: {
    type: String,
    default: 'hsl(var(--p))' // 使用DaisyUI默认主色
  }
});
</script>

<template>
  <button
      class="btn btn-sm transition-all"
      :style="{
      '--btn-color': primaryColor,
      'border-color': `color-mix(in srgb, ${primaryColor} 20%, transparent)`,
      'background-color': `color-mix(in srgb, ${primaryColor} 10%, transparent)`,
      color: primaryColor
    }"
  >
    <slot></slot>
  </button>
</template>

<style scoped>
.btn:hover {
  background-color: color-mix(in srgb, var(--btn-color) 20%, transparent);
  border-color: color-mix(in srgb, var(--btn-color) 30%, transparent);
}
</style>
