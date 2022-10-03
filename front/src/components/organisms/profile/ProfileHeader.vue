<template>
  <div class="profile-header">
    <div class="profile-header-wrap">
      <img
        :src="userStore.profile?.item?.user?.profile"
        alt=""
        class="profile-image"
      />
      <profile-header-info />
    </div>
  </div>
</template>

<script setup>
import ProfileHeaderInfo from '@/components/molecules/profile/ProfileHeaderInfo';
import { useUserStore } from '@/stores/user';
import { onBeforeMount } from 'vue';
import { useRoute } from 'vue-router';

const route = useRoute();

const userStore = useUserStore();
onBeforeMount(() => {
  userStore.getOtherinfo(route.params.userId);
});
</script>

<style scoped>
.profile-header {
  width: calc(100% - 2 * var(--grid-side));
  display: flex;
  justify-content: center;
  padding-bottom: var(--grid-vertical);
  border-bottom: 1px solid var(--instagram-dark-grey);
  margin-left: var(--grid-side);
  margin-top: var(--grid-vertical);
}
.profile-header-wrap {
  width: 100%;
  max-width: 400px;
  display: flex;
  justify-content: start;
  gap: 20px;
}
.profile-image {
  width: 80px;
  height: 80px;
  object-fit: cover;
  border-radius: 80px;
  border: 1px solid var(--instagram-grey);
}
</style>
