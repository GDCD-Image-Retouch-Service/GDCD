<template>
  <div
    class="error-view sub d-flex flex-column align-items-center justify-content-center"
  >
    <img :src="`@/assets/sprite/ChaltteogiSad.png`" ref="chalSad" />
    <icon-rank :rank="1" />
    <icon-rank :rank="2" />
    <icon-rank :rank="3" />
    <icon-rank :rank="4" />
    <icon-rank :rank="5" />
    <icon-rank :rank="6" />
    <icon-rank :rank="7" />
    <icon-rank :rank="8" />
    <icon-rank :rank="9" />
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.js';
import { useMainStore } from '@/stores';
import { ref, onMounted } from 'vue';
import IconRank from '@/components/atoms/IconRank';
import swal from 'sweetalert2';

const chalSad = ref(null);
const userStore = useUserStore();
const mainStore = useMainStore();

userStore.headerSetDropdown = false;
userStore.loginModal = false;
userStore.logoutModal = false;

// > Life Cycle
onMounted(() => {
  // chalSad.value.src = require('@/assets/sprite/ChaltteogiSad.png');
});

swal.fire({
  icon: 'success',
  background: mainStore.getIsDark ? '#3c3c3a' : '#ffffff',
});
</script>

<style scoped>
.error-view {
  padding-top: 70px;
  padding-bottom: 60px;
  height: 100vh;
  min-height: 100vh;
  background: red;
  width: 100vw;
}
</style>
