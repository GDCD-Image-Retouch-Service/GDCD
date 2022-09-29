<template>
  <div class="scrap">
    <div class="wrapper">
      <label for="file" class="upload-btn">
        <img
          :src="userStore.profile?.item?.user.profile"
          class="image-box"
          ref="imgBox"
        />
      </label>
      <input
        id="file"
        type="file"
        accept="image/*"
        @change="testfunc"
        ref="fileInput"
        style="display: none"
      />
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';

import { ref } from 'vue';

const userStore = useUserStore();

const fileInput = ref(null);
let imgBox = ref(null);

const testfunc = () => {
  const reader = new FileReader();
  reader.onload = ({ target }) => {
    imgBox.value.src = target.result;
  };
  console.dir(fileInput.value.files[0]);
  reader.readAsDataURL(fileInput.value.files[0]);
};
</script>

<style scoped>
.scrap {
  width: calc(100% - 2 * var(--grid-side));
  margin-left: var(--grid-side);
}
.wrapper {
  width: 100%;
  display: flex;
  justify-content: center;
  margin-top: var(--grid-vertical);
}
.image-box {
  width: 130px;
  height: 130px;
  border-radius: 130px;
  border: 1px solid var(--instagram-grey);
}
</style>
