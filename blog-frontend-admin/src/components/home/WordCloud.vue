<script setup>
import { onMounted } from 'vue';
import * as echarts from 'echarts';
import 'echarts-wordcloud';
import { getAllTag } from "../../net/tag.js";

const getTag = async () => {
  const res = await getAllTag();
  return res.map(item => ({
  name: item.tagName,
  value: Math.floor(Math.random() * 5000) + (10 * 1000) // 随机值范围为 10000 到 15000
}));
};

const initChart = async () => {
  const arr = await getTag().then((res) => {
    return res
  })
  console.log(arr)

  const myChart = echarts.init(document.getElementById('mywordcloud'));
  const options = {
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
            (Math.round(Math.random() * 100) + 100) + // R值在100到200之间
            ", " +
            (Math.round(Math.random() * 100) + 100) + // G值在100到200之间
            ", " +
            (Math.round(Math.random() * 100) + 100) + // B值在100到200之间
            ")"
            );
          }
        },
        data: arr,
        emphasis: {
          textStyle: {
            shadowBlur: 10,
            shadowColor: '#333'
          }
        }
      }
    ]
  };
  myChart.setOption(options);
};

onMounted(() => {
  initChart();
});
</script>

<template>
  <div id="mywordcloud" class="wordcloud"></div>
</template>

<style scoped>
.wordcloud {
  width: 400px; /* 调整宽度以使图表更小 */
  height: 300px; /* 调整高度以使图表更小 */
  margin: 0 auto;
}
</style>
