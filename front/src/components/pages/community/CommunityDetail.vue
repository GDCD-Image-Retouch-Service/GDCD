<template>
  <div class="community-detail">
    <community-detail-card />
    <community-comment />
  </div>
</template>

<script setup>
import CommunityDetailCard from '@/components/organisms/community/CommunityDetailCard.vue';
import CommunityComment from '@/components/organisms/community/CommunityComment.vue';

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
  width: calc(100% - 2 * var(--grid-side));
  margin: var(--grid-vertical) var(--grid-side) 0 var(--grid-side);

  overflow: hidden;
}
</style>
