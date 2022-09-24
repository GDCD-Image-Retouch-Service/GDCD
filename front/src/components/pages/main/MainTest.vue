// 이미지 서버 업로드 테스트 코드
<template>
  <div
    class="main-upload d-flex flex-column align-items-center justify-content-center"
  >
    <img :src="imageUrl" alt="이미지" />

    <button @click="testConnection">사진 전송 버튼</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { image } from '@/api/rest';

const imageUrl = ref(null);

const testConnection = async () => {
  const data = await image.testConnection(3);

  imageUrl.value = data.item.imageUrl;
  console.log(data);
};
</script>

<style>
.main-upload {
  min-height: calc(100vh - var(--size-h-header));
  width: 100vw;
}

.main-upload-form {
  border-radius: 20px;
  padding: 8px;
  width: 300px;
}

/* box */
.box-upload-image {
  margin: 0;
  width: 100%;
  height: 400px;
  border: 10px #333 solid;
}

#videoElement {
  width: 100%;
  height: 370px;
  background: #666;
}
/*  */
</style>
