<template>
  <div class="photo-layout">
    <div v-for="photo in myPhoto" :key="photo.date" class="photo-layout-wrap">
      <div>
        {{ photo.date }}
      </div>
      <div class="photo-item-wrap">
        <div v-for="object in photo.object" :key="object" class="photo-item">
          <card-carousel
            :originImage="object.origin"
            :editImage="object.edit"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import CardCarousel from '@/components/molecules/CardCarousel.vue';

const myPhoto = [
  {
    date: '2022-09-15',
    object: [
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
    ],
  },
  {
    date: '2022-09-16',
    object: [
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
    ],
  },
  {
    date: '2022-09-17',
    object: [
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
    ],
  },
  {
    date: '2022-09-18',
    object: [
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
      {
        origin: require('@/assets/sdprofile.png'),
        edit: require('@/assets/sdprofile.png'),
      },
    ],
  },
];
</script>

<style scoped>
.photo-layout-wrap {
  display: flex;
  flex-direction: column;
  gap: 10px;
  margin-bottom: 30px;
}
.photo-item-wrap {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  width: 100%;
}
.photo-item {
  width: 100px;
  overflow: hidden;
}
@media (max-width: 400px) {
  .photo-item {
    width: calc((100% - 20px) / 3);
  }
}
@media (min-width: 400px) and (max-width: 500px) {
  .photo-item {
    width: calc((100% - 30px) / 4);
  }
}
@media (min-width: 500px) and (max-width: 600px) {
  .photo-item {
    width: calc((100% - 40px) / 5);
  }
}
</style>
