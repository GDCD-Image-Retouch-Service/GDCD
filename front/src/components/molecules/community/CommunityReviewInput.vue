<template>
  <div class="chatting-input-wrap">
    <!-- 유저 프로필 -->
    <img :src="chat.profileImage" alt="" class="profile-image" />

    <!-- 인풋 -->
    <input type="text" class="chatting-input" placeholder="댓글 달기..." />

    <!-- 버튼 -->
    <div class="chatting-button">게시</div>
  </div>
</template>

<script setup>
import { defineProps, toRefs } from 'vue';

const props = defineProps({
  chat: Object,
});

const { chat } = toRefs(props);
</script>

<style scoped>
.chatting-input-wrap {
  position: fixed;
  bottom: var(--size-h-footer);
  width: 100%;
  left: 0;
  height: 50px;

  display: flex;
  gap: 10px;
  align-items: center;
  background-color: var(--light-sub-color);
}
.profile-image {
  width: 30px;
  height: 30px;
  object-fit: cover;
  border-radius: 50px;
  margin-left: 20px;
}
.chatting-input {
  width: calc(100% - 120px);
  height: 35px;
  border: none;
  padding: 0 7px;
  border-radius: 10px;
  font-size: 12px;

  line-height: 35px;
}
.chatting-button {
  width: 30px;
  color: var(--instagram-dark-grey);
  font-size: 12px;
}
</style>
