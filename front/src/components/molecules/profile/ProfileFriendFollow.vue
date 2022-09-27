<template>
  <div
    v-for="follower in userStore.follower.item?.followers"
    :key="follower.userId"
    class="follow-wrap"
  >
    <div class="user-wrap">
      <img :src="follower.image" alt="" class="profile-image" />
      {{ follower.nickname }}
    </div>
    <delete-button />
  </div>
</template>

<script setup>
import DeleteButton from '@/components/molecules/common/btn/BtnDelete.vue';
import { useUserStore } from '@/stores/user';
import { useRoute } from 'vue-router';

const route = useRoute();
const userStore = useUserStore();

userStore.getMyFollower(route.params.userId);
</script>

<style scoped>
.follow-wrap {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.user-wrap {
  display: flex;
  gap: 20px;
  align-items: center;
}
.profile-image {
  width: 50px;
  height: 50px;
  border-radius: 50px;
  object-fit: cover;
}
</style>
