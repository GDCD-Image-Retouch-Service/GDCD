<template>
  <div>{{ ans }}</div>
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

const calcDate = () => {
  const date1 =
    parseInt(timeString.substr(6, 2)) +
    parseInt(timeString.substr(3, 2)) * 60 +
    parseInt(timeString.substr(0, 2)) * 60 * 60 +
    parseInt(dateString.substr(8, 2)) * 60 * 60 * 24 +
    parseInt(dateString.substr(5, 2)) * 60 * 60 * 24 * 365 +
    parseInt(dateString.substr(2, 2)) * 60 * 60 * 24 * 365 * 12;

  const date2 =
    parseInt(timeString2.substr(6, 2)) +
    parseInt(timeString2.substr(3, 2)) * 60 +
    parseInt(timeString2.substr(0, 2)) * 60 * 60 +
    parseInt(dateString2.substr(8, 2)) * 60 * 60 * 24 +
    parseInt(dateString2.substr(5, 2)) * 60 * 60 * 24 * 365 +
    parseInt(dateString2.substr(2, 2)) * 60 * 60 * 24 * 365 * 12;

  let ret = date1 - date2 + 1;
  if (ret < 60) {
    return '오늘';
  }
  ret = Math.floor(ret / 60);
  if (ret < 60) {
    return `오늘`;
  }
  ret = Math.floor(ret / 60);
  if (ret < 24) {
    if (ret + 9 < 24) {
      return `오늘`;
    } else {
      return '1일 전';
    }
  }
  ret = Math.floor(ret / 24);
  if (ret < 7) {
    return `${ret}일 전`;
  }
  ret = Math.floor(ret / 7);
  if (ret < 4) {
    return `${ret}주 전`;
  }
  ret = Math.floor(ret / 4);
  if (ret < 12) {
    return `${ret}달 전`;
  }
  ret = Math.floor(ret / 12);
  return `${ret}년 전`;
};

const ans = calcDate();
</script>

<style scoped></style>
