<template>
  <div style="width: 60px">{{ ans }}</div>
</template>

<script setup>
import { defineProps, toRefs } from 'vue';

const props = defineProps({
  updateInfo: String,
});

// 이 줄 추가해야 안정적임
const { updateInfo } = toRefs(props);
var today = new Date();

// 날짜 계산
var year = today.getFullYear();
var month = ('0' + (today.getMonth() + 1)).slice(-2);
var day = ('0' + today.getDate()).slice(-2);

var dateString = year + '-' + month + '-' + day;

// 시간 계산
var hours = ('0' + today.getHours()).slice(-2);
var minutes = ('0' + today.getMinutes()).slice(-2);
var seconds = ('0' + today.getSeconds()).slice(-2);

var timeString = hours + ':' + minutes + ':' + seconds;

// 업데이트 시간

var updateTime = new Date(updateInfo.value);

var year2 = updateTime.getFullYear();
var month2 = ('0' + (updateTime.getMonth() + 1)).slice(-2);
var day2 = ('0' + updateTime.getDate()).slice(-2);

var dateString2 = year2 + '-' + month2 + '-' + day2;

// 시간 계산
var hours2 = ('0' + updateTime.getHours()).slice(-2);
var minutes2 = ('0' + updateTime.getMinutes()).slice(-2);
var seconds2 = ('0' + updateTime.getSeconds()).slice(-2);

var timeString2 = hours2 + ':' + minutes2 + ':' + seconds2;

var ans = '';
const calcDate = () => {
  const yearGap = dateString.substr(0, 4) - dateString2.substr(0, 4);
  const monthGap = dateString.substr(5, 2) - dateString2.substr(5, 2);
  const dateGap = dateString.substr(8, 2) - dateString2.substr(8, 2);
  const hourGap = timeString.substr(0, 2) - timeString2.substr(0, 2);
  const minuteGap = timeString.substr(3, 2) - timeString2.substr(3, 2);
  const secondGap = timeString.substr(6, 2) - timeString2.substr(6, 2);

  if (yearGap) {
    ans = yearGap + '년전';
  } else if (monthGap) {
    ans = monthGap + '달전';
  } else if (dateGap) {
    ans = dateGap + '일전';
  } else if (hourGap) {
    ans = hourGap + '시간전';
  } else if (minuteGap) {
    ans = minuteGap + '분전';
  } else if (secondGap) {
    ans = secondGap + '초전';
  }
};

calcDate();
</script>

<style scoped></style>
