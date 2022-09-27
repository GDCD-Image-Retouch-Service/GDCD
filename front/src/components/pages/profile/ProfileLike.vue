<template>
  <div class="like">
    <div class="like-items">
      <div v-for="post in oddLike" :key="post">
        <img :src="post.image" alt="" class="card-image common-image" />
      </div>
    </div>
    <div class="like-items">
      <div v-for="post in evenLike" :key="post">
        <img :src="post.image" alt="" class="card-image common-image" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user.txt';
import { ref } from 'vue';

const userStore = useUserStore();

let oddLike = ref([]);
let evenLike = ref([]);

const devideLike = () => {
  userStore.like.item.posts.forEach((e, index) => {
    if (index % 2 === 0) {
      evenLike.value.push(e);
    } else {
      oddLike.value.push(e);
    }
  });
};

devideLike();
userStore.isItemActive = 2;
</script>

<style scoped>
.like {
  margin-top: var(--grid-vertical);

  display: flex;
  gap: 10px;
}
.like-items {
  display: flex;
  flex-direction: column;
  gap: 10px;
  width: 50%;
}
.card-image {
  width: 100%;
}
</style>
