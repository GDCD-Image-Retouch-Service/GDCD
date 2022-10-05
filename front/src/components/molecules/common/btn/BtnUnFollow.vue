<template>
  <div class="add-friend" @click="userStore.follow(userId)">팔로잉</div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { defineProps, toRefs } from 'vue';

const props = defineProps({
  userId: Number,
});

const { userId } = toRefs(props);
const userStore = useUserStore();
</script>

<style scoped>
.add-friend {
  width: 65px;
  height: 27px;
  line-height: 27px;
  text-align: center;
  background-color: var(--theme-color);
  color: var(--light-main-color);
  border-radius: 5px;
}
</style>
