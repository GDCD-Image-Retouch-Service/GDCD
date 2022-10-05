<template>
  <div class="profile-follow">
    <div
      v-for="follower in userStore.follower.item?.followers"
      :key="follower.userId"
      class="follow-wrap"
    >
      <div
        class="user-wrap"
        @click="
          router.push({
            name: 'ProfilePost',
            params: { userId: follower.userId },
          })
        "
      >
        <img :src="follower.profile" alt="" class="profile-image" />
        {{ follower.nickname }}
      </div>
      <delete-button @click="deleteFollow" />
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

userStore.getMyFollower(route.params.userId);

// 내 팔로워 목록에 있는 사람 삭제
const deleteFollow = () => {
  console.log('팔로워 삭제');
};
</script>

<style scoped>
@media (min-width: 1024px) {
  .profile-follow {
    min-width: 400px;
    max-width: 400px;
  }
  .scrap-wrap {
    max-width: calc(25% - 21px);
  }
}
.profile-follow {
  width: 100%;
  margin: 0 auto;
}
.follow-wrap {
  display: flex;
  justify-content: space-between;
  align-items: center;
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
