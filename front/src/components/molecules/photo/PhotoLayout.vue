<template>
  <div class="photo-layout">
    <date-format :updateInfo="myPhotos.item[0][0].updateTime" />

    <div class="photo-item-wrap">
      <div v-for="myPhoto in myPhotos.item" :key="myPhoto">
        <img :src="myPhoto[0].imageUrl" class="photo-item" />
      </div>
    </div>
  </div>
</template>

<script setup>
import DateFormat from '../common/DateFormat.vue';
import { useUserStore } from '@/stores/user.js';
const userStore = useUserStore();

userStore.getMyPhoto();

const myPhotos = {
  msg: 'SUCCESS',
  item: [
    [
      {
        updateTime: '2022-09-23T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=3',
        imageId: 3,
        imageTag: ['고구마', '고양이', '춘식'],
        imageRank: 1,
      },
      {
        updateTime: '2022-09-20T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=4',
        imageId: 4,
        imageTag: ['엄지', '사자', '성'],
        imageRank: 9,
      },
    ],
    [
      {
        updateTime: '2022-09-23T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=3',
        imageId: 3,
        imageTag: ['고구마', '고양이', '춘식'],
        imageRank: 1,
      },
      {
        updateTime: '2022-09-20T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=4',
        imageId: 4,
        imageTag: ['엄지', '사자', '성'],
        imageRank: 9,
      },
    ],
    [
      {
        updateTime: '2022-09-21T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=3',
        imageId: 3,
        imageTag: ['고구마', '고양이', '춘식'],
        imageRank: 1,
      },
      {
        updateTime: '2022-09-23T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=4',
        imageId: 4,
        imageTag: ['엄지', '사자', '성'],
        imageRank: 9,
      },
    ],
    [
      {
        updateTime: '2022-09-23T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=3',
        imageId: 3,
        imageTag: ['고구마', '고양이', '춘식'],
        imageRank: 1,
      },
      {
        updateTime: '2022-09-20T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=4',
        imageId: 4,
        imageTag: ['엄지', '사자', '성'],
        imageRank: 9,
      },
    ],
    [
      {
        updateTime: '2022-09-23T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=3',
        imageId: 3,
        imageTag: ['고구마', '고양이', '춘식'],
        imageRank: 1,
      },
      {
        updateTime: '2022-09-20T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=4',
        imageId: 4,
        imageTag: ['엄지', '사자', '성'],
        imageRank: 9,
      },
    ],
    [
      {
        updateTime: '2022-09-23T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=3',
        imageId: 3,
        imageTag: ['고구마', '고양이', '춘식'],
        imageRank: 1,
      },
      {
        updateTime: '2022-09-20T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=4',
        imageId: 4,
        imageTag: ['엄지', '사자', '성'],
        imageRank: 9,
      },
    ],
    [
      {
        updateTime: '2022-09-23T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=3',
        imageId: 3,
        imageTag: ['고구마', '고양이', '춘식'],
        imageRank: 1,
      },
      {
        updateTime: '2022-09-20T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=4',
        imageId: 4,
        imageTag: ['엄지', '사자', '성'],
        imageRank: 9,
      },
    ],
    [
      {
        updateTime: '2022-09-23T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=3',
        imageId: 3,
        imageTag: ['고구마', '고양이', '춘식'],
        imageRank: 1,
      },
      {
        updateTime: '2022-09-20T05:11:02.465',
        imageUrl: 'https://j7b301.p.ssafy.io/api/image?imageId=4',
        imageId: 4,
        imageTag: ['엄지', '사자', '성'],
        imageRank: 9,
      },
    ],
  ],
};
</script>

<style scoped>
.photo-layout-wrap {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-bottom: 30px;
}
.photo-item-wrap {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  width: 100%;
  /* justify-content: space-between; */
}
.photo-item {
  width: 100px;
  height: 100px;
  object-fit: cover;
}
</style>
