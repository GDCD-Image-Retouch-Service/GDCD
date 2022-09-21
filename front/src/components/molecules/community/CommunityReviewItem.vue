<template>
  <div style="margin: var(--grid-vertical) var(--grid-side)">
    <div v-for="ch in chat" :key="ch.id" class="chatting-wrap">
      <div class="chattting-header">
        <!-- 유저 정보 -->
        <div class="user-info">
          <img :src="ch.profileImage" class="profile-image" />
          <div>
            {{ ch.username }}
          </div>
        </div>

        <!-- 수정 삭제 -->
        <div class="edit-delete-wrap">
          <div>수정</div>
          <div>삭제</div>
        </div>
      </div>

      <!-- 메시지 -->
      <div class="chatting-message">
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
  flex-direction: column;
  width: 100%;
  align-items: start;
}
.chattting-header {
  width: 100%;
  display: flex;
  justify-content: space-between;
}
.edit-delete-wrap {
  display: flex;
  gap: 5px;
  font-size: 12px;
  line-height: 30px;
}
.user-info {
  display: flex;
  align-items: center;
  gap: 5px;
  font-weight: 700;
}
.profile-image {
  width: 30px;
  height: 30px;
  object-fit: cover;
  border-radius: 50px;
}
.chatting-message {
  word-break: break-all;
  text-align: start;
}
</style>
