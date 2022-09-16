<template>
  <div>
    <div v-for="ch in chat" :key="ch.id" class="chatting-wrap">
      <!-- 유저 정보 -->
      <div class="user-info">
        <img :src="ch.profileImage" class="profile-image" />
        <div>
          {{ ch.username }}
        </div>
      </div>

      <!-- 메시지 -->
      <div>
        {{ ch.message }}
      </div>
    </div>
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
.chatting-wrap {
  align-items: center;
  display: flex;
  /* justify-content: space-between; */
  gap: 10px;
  margin-bottom: 20px;
  width: 100%;
}
.user-info {
  display: flex;
  align-items: center;
}
.profile-image {
  width: 50px;
  height: 50px;
  object-fit: cover;
  border-radius: 50px;
}
</style>
