<script setup>
/**
 * @说明: 热力图
 * @作者: Aoki
 * @时间: 2023/02/17 17:22:22
 */
import {getCurrentInstance, onMounted, ref} from 'vue'

import {getArticle} from "../../net/article.js";

const { proxy } = getCurrentInstance()
let heatMap = ref(null)
let name = ref('文章贡献度')
const timeArr = ref([])

function formatTimestamp(timestamp) {
  const date = new Date(timestamp);
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0'); // 月份从0开始，所以要加1
  const day = String(date.getDate()).padStart(2, '0');
  return `${year}-${month}-${day}`;
}

const getArtInfo = async () => {
  let dateCounts = [];

  const res =  await getArticle();

 await res.forEach((item) => {
    timeArr.value.push(item.time.substr(0, 10));
    dateCounts = timeArr.value.reduce((counts, date) => {
      if (!counts[date]) {
        counts[date] = 1;
      } else {
        counts[date]++;
      }
      return counts;
    }, {});
  });

 return Object.entries(dateCounts).map(([date, count]) => [date, count]);

};

onMounted( async () => {

  const time  =  new Date().getTime();
  const arr  =  await getArtInfo().then((res)=>{
    return res
  })

  const myChart = proxy.$echarts.init(heatMap.value)
  myChart.setOption({
    tooltip: {
      formatter: function (params) {
        return params.value[0] + ' : ' + params.value[1]
      }
    },
    visualMap: [  {
      show: true,
      type: 'piecewise',
      orient: 'horizontal',
      right: 0,
      min: 0,
      max: 5,
      text: ['多', '少'],
      textStyle: {
        color: '#ccc',
        fontSize: 12,
      },
      showLabel: false,
      itemWidth: 16,
      itemHeight: 16,
      itemGap: 4,
      selectedMode: false, //禁止选择
      inRange: {
        color: [
          'rgba(233,233,233)',
          '#95de64',
          '#52c41a',
          '#237804',
          '#092b00',
        ],
      },
      //图例分段
      pieces: [
        { min: 5 },
        { min: 4, max: 5 },
        { min: 3, max: 4 },
        { min: 2, max: 3 },
        { min: 1, max: 2 },
        { value: 0, color: 'rgba(233,233,233)' },
      ],
    },
  ],

    calendar: {
      itemStyle: {
        //解决格子间距
        color: '#ccc',
        borderWidth: 3,
        borderColor: '#fff'
      },
      cellSize: [18, 18],  //格子尺寸
      range: [formatTimestamp(time-(3600 * 24 * 30 * 12 * 1000)) ,formatTimestamp(time) ],
      splitLine: true,
      dayLabel: {
        firstDay: 7,
        nameMap: 'ZH'
      },
      monthLabel: {
        nameMap: ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'
        ]
      },
      yearLabel: {
        show: false
      },
      silent: {
        show: false
      }
    },
    series: {
      type: 'heatmap',
      coordinateSystem: 'calendar',
      data:arr
    },
  })
})
</script>

<template>
  <div class="box">
    <h2>{{ name }}</h2>
    <p>一年内博客提交数量</p>
    <div ref="heatMap" class="about"></div>
  </div>
</template>


<style lang="less" scoped>
.box {
  width: 100%;
  height: 11.5625rem;
  h2{
    text-align: center;
    margin:0;
    font-size: 18px;
  }
  p {
    width: 281px;
    height: 19px;
    font-size: 14px;
    text-align: center;
    line-height: 20px;
    color: #777777;
    margin:0 auto;
  }
}
.about {
  width: 64.8895rem;
  height: 13.56865rem;
  margin: 0 auto;
}
</style>
