<template>
  <div
    v-for="following in userStore.following.item?.followings"
    :key="following.userId"
    class="follow-wrap"
  >
    <div class="user-wrap">
      <img :src="following.profile" alt="" class="profile-image" />
      {{ following.nickname }}
    </div>
    <delete-button
      @click="
        userStore.follow(following.userId),
          router.push({ name: 'ProfileFriend' })
      "
    />
  </div>
</template>

<script setup>
import DeleteButton from '@/components/molecules/common/btn/BtnDelete.vue';
import { useUserStore } from '@/stores/user';
import { useRoute, useRouter } from 'vue-router';

const router = useRouter();
const route = useRoute();
console.log(router);
const userStore = useUserStore();

userStore.getMyFollowing(route.params.userId);
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
