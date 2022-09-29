<template>
  <div class="community-write">
    <!-- 제목 -->
    <div class="wrap">
      <div>제목</div>
      <input type="text" class="write-input" v-model="data.title" />
    </div>
    <!-- 이미지 -->
    <div class="wrap" v-if="userStore.urlPhotoList.length === 0">
      <div>이미지</div>
      <a
        class="btn image-input"
        data-bs-toggle="modal"
        href="#exampleModalToggle"
        role="button"
        id="image-input"
      >
      </a>
    </div>

    <div v-else>
      <a
        data-bs-toggle="modal"
        href="#exampleModalToggle"
        role="button"
        id="image-input"
        style="padding: 0; text-decoration: none; color: var(--black)"
        ><div>이미지</div>
      </a>
      <card-carousel
        :firstImage="userStore.urlPhotoList?.[0]"
        :secondImage="userStore.urlPhotoList?.[1]"
        :firstTags="userStore.selectTag"
        :secondTags="userStore.selectTag"
      />
    </div>
    {{ userStore.myPhoto.item.beforeImage?.imageTag }}
    <!-- 내용 -->
    <div class="wrap">
      <div>내용</div>
      <textarea
        class="content-area"
        maxlength="100"
        v-model="data.content"
      ></textarea>
    </div>

    <button class="button" @click="createPost(data)">제출</button>

    <div
      class="modal fade"
      id="exampleModalToggle"
      aria-hidden="true"
      aria-labelledby="exampleModalToggleLabel"
      tabindex="-1"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <div v-for="(values, key) in userStore.myPhoto.item" :key="values">
              <date-format
                :updateInfo="key"
                style="margin-top: 30px; margin-bottom: 10px"
              />
              <div class="image-wrap">
                <label for="select-image">
                  <div
                    v-for="value in values"
                    :key="value"
                    :style="{
                      backgroundImage:
                        'url(' + value.beforeImage.imageUrl + ')',
                    }"
                    class="photo-image"
                    @click="
                      (userStore.photoSelect = [
                        {
                          id: value.beforeImage.imageId,
                          url: value.beforeImage.imageUrl,
                        },
                        {
                          id: value.afterImage.imageId,
                          url: value.afterImage.imageUrl,
                        },
                      ]),
                        (userStore.daePyoImage = value.beforeImage.imageUrl);
                      userStore.selectTag = value.beforeImage.imageTag;
                    "
                  >
                    <!-- <img :src="value.beforeImage.imageUrl" alt="" class="photo-image" /> -->
                  </div>
                </label>
              </div>
            </div>
          </div>
          <button
            class="btn"
            data-bs-target="#exampleModalToggle2"
            data-bs-toggle="modal"
            data-bs-dismiss="modal"
            id="select-image"
            style="height: 50px"
          ></button>
        </div>
      </div>
    </div>
    <!-- 두 번째 모달 -->
    <div
      class="modal fade"
      id="exampleModalToggle2"
      aria-hidden="true"
      aria-labelledby="exampleModalToggleLabel2"
      tabindex="-1"
    >
      <div class="modal-dialog">
        <div class="modal-content">
          <div class="modal-body">
            <!-- 메인 이미지 -->
            <img :src="userStore.daePyoImage" alt="" class="main-image" />

            <div style="display: flex; gap: 10px">
              <div
                :style="{
                  backgroundImage: 'url(' + userStore.photoSelect[0]?.url + ')',
                }"
                class="sub-image"
                @click="
                  (userStore.daePyoImage = userStore.photoSelect[0]?.url),
                    pushSelectedNumber(
                      userStore.photoSelect[0]?.id,
                      userStore.photoSelect[0]?.url,
                    )
                "
              ></div>
              <div
                :style="{
                  backgroundImage: 'url(' + userStore.photoSelect[1]?.url + ')',
                }"
                class="sub-image"
                @click="
                  (userStore.daePyoImage = userStore.photoSelect[1]?.url),
                    pushSelectedNumber(
                      userStore.photoSelect[1]?.id,
                      userStore.photoSelect[1]?.url,
                    )
                "
              ></div>
            </div>
            <div style="margin-top: var(--grid-vertical)">
              <button
                class="befor-btn"
                data-bs-target="#exampleModalToggle"
                data-bs-toggle="modal"
                data-bs-dismiss="modal"
              >
                돌아가기
              </button>
              <button
                class="select-btn"
                data-bs-target="#exampleModalToggle"
                data-bs-toggle="modal"
                data-bs-dismiss="modal"
                @click="
                  (data.images = [
                    userStore.photoSelect[0]?.id,
                    userStore.photoSelect[1]?.id,
                  ]),
                    selectPhoto()
                "
              >
                선택완료
              </button>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import CardCarousel from '@/components/molecules/CardCarousel.vue';
import DateFormat from '@/components/molecules/common/DateFormat.vue';
import { useCommunityStore } from '@/stores/community.js';
import { useUserStore } from '@/stores/user.js';
import { ref } from 'vue';
import router from '@/router';

const communityStore = useCommunityStore();
const userStore = useUserStore();

const data = ref({
  title: '',
  content: '',
  privacyBound: 1,
  images: [],
  representative: 0,
});

const createPost = (data) => {
  console.log(userStore.photoSelect);

  const context = {
    title: data.title,
    content: data.content,
    privacyBound: 1,
    images: userStore.selectedPhoto,
    representative: 0,
  };
  console.log(context, userStore.photoSelect);
  communityStore.createPost(context);

  router.push({ name: 'community' });
};

const selectPhoto = () => {
  userStore.urlPhotoList = userStore.urlList;
  userStore.urlList = [];
  console.log(userStore.selectedPhoto);
};

const pushSelectedNumber = (num, url) => {
  let checked = true;
  userStore.selectedPhoto.filter((e) => {
    if (e === num) {
      checked = false;
      const index = userStore.selectedPhoto.indexOf(e);
      userStore.selectedPhoto.splice(index, 1);
    }
  });
  if (checked) {
    userStore.selectedPhoto.push(num);
  }

  checked = true;
  userStore.urlList.filter((e) => {
    if (e === url) {
      checked = false;
      const index = userStore.urlList.indexOf(e);
      userStore.urlList.splice(index, 1);
    }
  });
  if (checked) {
    userStore.urlList.push(url);
  }

  console.log(userStore.selectedPhoto);
  console.log(userStore.urlList);
};

userStore.getMyPhoto();

userStore.photoSelect = [];
userStore.selectedPhoto = [];
userStore.urlList = [];
userStore.urlPhotoList = [];
userStore.selectTag = [];
</script>

<style scoped>
@keyframes openDialog {
  0% {
    bottom: -600px;
  }
  100% {
    bottom: -48px;
  }
}

.community-write {
  width: calc(100% - 2 * var(--grid-side));
  margin: var(--grid-vertical) var(--grid-side);

  display: flex;
  flex-direction: column;
  gap: var(--grid-vertical);
}
.wrap {
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.write-input {
  width: 100%;
  height: 37px;
  border: 1px solid var(--instagram-grey);
  border-radius: 5px;
  background-color: var(--light-main-color);
  margin: 0 auto;
  padding: 5px;
}
.content-area {
  padding: 5px;
  width: 100%;
  border: 1px solid var(--instagram-grey);
  resize: none;
  line-height: 22px;
  border-radius: 5px;
  height: 150px;
}
.image-input {
  width: 100%;
  padding: 5px 5px 100% 5px;
  border: 1px solid var(--instagram-grey);
  border-radius: 5px;
  background-color: var(--light-main-color);
}
.modal-dialog {
  position: fixed;
  width: 100%;
  min-width: 100%;
  bottom: -48px;
  animation-name: openDialog;
  animation-duration: 0.1s;
  margin: 0;
  border-radius: 130px 130px 0 0;
  height: 700px;
}
.image-wrap > label {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}
.photo-image {
  width: calc((100% - 20px) / 3);
  padding-bottom: calc((100% - 20px) / 3);
  background-position: center;
  background-size: cover;
  border-radius: 5px;
}
.modal-body {
  width: 100%;
  height: 600px;
  overflow: scroll;
}
.main-image {
  width: 100%;
  margin-bottom: var(--grid-vertical);
  border-radius: 5px;
}
.sub-image {
  width: 50%;
  padding-bottom: 50%;
  background-position: center;
  background-size: cover;
  border-radius: 5px;
}
.befor-btn {
  background-color: var(--delete-btn);
  width: calc(50% - 5px);
  color: white;
  height: 50px;
  border: none;
  margin-right: 10px;
  border-radius: 5px;
}
.select-btn {
  background-color: var(--theme-color);
  width: calc(50% - 5px);
  color: white;
  border: none;
  height: 50px;
  border-radius: 5px;
}
.selected-image {
  width: 100%;
}
</style>
