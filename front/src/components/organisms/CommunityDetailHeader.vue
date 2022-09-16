<template>
  <div class="community-detail-header">
    <!-- 프로필 -->
    <div class="user-profile">
      <img 
        :src="card.profileImage" 
        alt="" 
        class="profile-image">
      {{ card.username }}
    </div>

    <!-- 태그들 -->
    <div class="tag-wrap">
      <div 
        v-for="tag in card.tag" 
        :key="tag" 
        class="tag"
      >
        {{ tag }}
      </div>
    </div>

    <!-- 이미지 -->
    <card-carousel 
    :orginImage = "card.cardImage.origin"
    :editImage = "card.cardImage.edit"
    />

    <div class="card-content">
      {{ card.content }}
    </div>

    <!-- 좋아요 북마크 -->
    <div
      class="like-bookmark"
    >
      <i class="bi bi-heart"></i>
      <div>
        0
      </div>
      <i 
        class="bi bi-bookmark" 
        style="margin-right: 20px;"
      ></i>
    </div>
  </div>
</template>

<script setup>
import CardCarousel from '@/components/molecules/CardCarousel.vue'

const card = {
    profileImage: 'data:image/jpeg;base64,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',
    username: '성덕',
    tag: [
        '개',
        '고양이',
        '가나다라마바사'
    ],
    cardImage: { 
        origin: 'https://picsum.photos/200/300',
        edit: 'https://picsum.photos/200/301',
    },
    content: '일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십'
}
</script>

<style scoped>
.community-detail-header {
  display: flex;
  align-items: flex-start;
  gap: 30px;
  flex-direction: column;
  margin-top: 30px;
  margin-bottom: 30px;
}
.profile-image {
  width: 50px;
  height: 50px;
  object-fit: cover;
  border-radius: 50px;
  margin-left: 20px;
}

/* 태그 */
.tag-wrap {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  padding: 0 20px;
}
.tag {
  padding: 0 20px;
  padding-top: 3px;
  height: 30px;
  border: 1px solid black;

  background-color: var(--black);
  color: var(--light-main-color);
  border-radius: 30px;
}
.card-image {
  width: 100%;
  object-fit: cover;
}
.card-content {
  padding: 0 20px;
}
.like-bookmark {
  width: 100%;
  display: flex;
  justify-content: flex-end;
  gap: 5px;
}
</style>