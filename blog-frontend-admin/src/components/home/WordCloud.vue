<script setup>
import { onMounted } from 'vue';
import * as echarts from 'echarts';
import 'echarts-wordcloud';
import { getAllTag } from "../../net/tag.js";

const getTag = async () => {

  const res = await getAllTag();

  return res.map(item => ({
    name: item.tagName,
    value: (10) * 1000
  }));

};

const initChart = async () => {
  const arr = await getTag().then((res) => {
    return res
  })
  console.log(arr)


  const myChart = echarts.init(document.getElementById('mywordcloud'));
  const options = {
    // 核心配置
    series: [
      {
        type: 'wordCloud',
        shape: 'circle',
        sizeRange: [8, 46],
        rotationRange: [0, 0],
        gridSize: 0,
        layoutAnimation: true,
        textStyle: {
          color: function () {
            return (
              "rgb(" +
              Math.round(Math.random() * 255) +
              ", " +
              Math.round(Math.random() * 255) +
              ", " +
              Math.round(Math.random() * 255) +
              ")"
            );
          }
        },
        data: arr, // 这里随便改成数组包含数据就好
        emphasis: {
          textStyle: {
            fontWeight: 'bold',
            color: '#000'
          }
        }
      }
    ]
  }

  myChart.setOption(options);
}


onMounted(() => {
  initChart();
});
</script>

<template>
  <div id="mywordcloud" :style="{ width: '50%', height: '300px' }"></div>
</template>

<style scoped lang="less"></style>
