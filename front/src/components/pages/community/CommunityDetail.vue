<template>
  <div class="community-detail">
    <community-detailHeader />
  </div>
</template>

<script setup>
import CommunityDetailHeader from '@/components/organisms/CommunityDetailHeader.vue';
import { onMounted } from 'vue';

onMounted(() => {
  window.scrollTo(0, 0);
});
// import Swal from 'sweetalert2';

// Swal.fire({
//   title: 'Error!',
//   text: 'Do you want to continue',
//   icon: 'error',

//   confirmButtonText: 'Cool',
// });
</script>

<style scoped>
.community-detail {
  width: calc(100vw - 40px);
  border-radius: 20px;
  /* border: 1px solid #9A9A9A; */
  margin-top: 30px;
  margin-bottom: 80px;
  box-shadow: 0 4px 4px -4px rgba(0, 0, 0, 0.5);
  border: 1px solid rgba(0, 0, 0, 0.12);
}
</style>
