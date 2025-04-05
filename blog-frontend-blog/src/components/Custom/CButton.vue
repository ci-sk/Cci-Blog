<script setup>
import {computed} from "vue";

const props = defineProps({
  primaryColor: {
    type: String,
    default: 'hsl(var(--p))'
  },
  size: {
    type: String,
    default: 'sm',
    validator: (value) => ['sm', 'md', 'lg'].includes(value)
  },
  width: {
    type: [String, Number],
    default: null
  },
  height: {
    type: [String, Number],
    default: null
  }
});

const sizeClass = computed(() => {
  return {
    'sm': 'btn-sm',
    'md': 'btn-md',
    'lg': 'btn-lg'
  }[props.size];
});

const buttonStyle = computed(() => {
  const style = {
    '--btn-color': props.primaryColor,
    'border-color': `color-mix(in srgb, ${props.primaryColor} 20%, transparent)`,
    'background-color': `color-mix(in srgb, ${props.primaryColor} 10%, transparent)`,
    color: props.primaryColor
  };

  if (props.width) {
    style.width = typeof props.width === 'number' ? `${props.width}px` : props.width;
  }
  if (props.height) {
    style.height = typeof props.height === 'number' ? `${props.height}px` : props.height;
  }

  return style;
});
</script>

<template>
  <button
      class="btn transition-all"
      :class="sizeClass"
      :style="buttonStyle"
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
