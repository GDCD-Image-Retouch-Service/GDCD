<template>
  <div class="profile-following">
    <div
      v-for="following in userStore.following.item?.followings"
      :key="following.userId"
      class="follow-wrap"
    >
      <div
        class="user-wrap"
        @click="
          router.push({
            name: 'ProfilePost',
            params: { userId: following.userId },
          })
        "
      >
        <img :src="following.profile" alt="" class="profile-image" />
        {{ following.nickname }}
      </div>
      <delete-button @click="userStore.follow(following.userId)" />
    </div>
  </div>
</template>

<script setup>
import DeleteButton from '@/components/molecules/common/btn/BtnDelete.vue';
import { useUserStore } from '@/stores/user';
import { useRoute, useRouter } from 'vue-router';

const route = useRoute();
const router = useRouter();
const userStore = useUserStore();

userStore.getMyFollowing(route.params.userId);
</script>
<style scoped>
@media (min-width: 1024px) {
  .profile-following {
    min-width: 400px;
    max-width: 400px;
  }
  .scrap-wrap {
    max-width: calc(25% - 21px);
  }
}
.profile-following {
  width: 100%;
  margin: 0 auto;
}
.follow-wrap {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: var(--grid-side);
}
.user-wrap {
  display: flex;
  gap: 20px;
  align-items: center;
  font-size: 15px;
  font-weight: 700;
}
.profile-image {
  width: 50px;
  height: 50px;
  border-radius: 50px;
  object-fit: cover;
}
</style>
