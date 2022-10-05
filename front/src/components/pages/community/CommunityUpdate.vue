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
        @click="
          (firstCheck = false),
            (secondCheck = false),
            (userStore.selectedPhoto = []),
            (userStore.urlList = [])
        "
      >
      </a>
    </div>
    <div class="wrap" v-if="userStore.urlPhotoList.length === 1">
      <div>이미지</div>
      <a
        data-bs-toggle="modal"
        href="#exampleModalToggle"
        role="button"
        id="image-input"
        @click="
          (firstCheck = false),
            (secondCheck = false),
            (userStore.selectedPhoto = []),
            (userStore.urlList = [])
        "
        style="padding: 0; text-decoration: none; color: var(--black)"
      >
        <img
          :src="userStore.urlPhotoList?.[0]"
          alt=""
          class="one-image common-image"
        />
      </a>
      <div class="tag-wrap">
        <div
          v-for="(tag, index) in selectTags[0]"
          :key="index"
          class="tag common-image"
        >
          {{ tag }}
        </div>
      </div>
    </div>
    <div class="wrap" v-if="userStore.urlPhotoList.length === 2">
      <div>이미지</div>
      <a
        data-bs-toggle="modal"
        href="#exampleModalToggle"
        role="button"
        id="image-input"
        @click="
          (firstCheck = false),
            (secondCheck = false),
            (userStore.selectedPhoto = []),
            (userStore.urlList = [])
        "
        style="padding: 0; text-decoration: none; color: var(--black)"
      >
        <div style="display: flex; width: 100%; gap: 10px">
          <img
            :src="userStore.urlPhotoList?.[0]"
            alt=""
            class="two-image common-image"
          />
          <img
            :src="userStore.urlPhotoList?.[1]"
            alt=""
            class="two-image common-image"
          />
        </div>
      </a>
      <div class="tag-wrap">
        <div
          v-for="(tag, index) in selectTags[0]"
          :key="index"
          class="tag common-image"
        >
          {{ tag }}
        </div>
      </div>
    </div>

    <!-- 내용 -->
    <div class="wrap">
      <div>내용</div>
      <textarea
        class="content-area"
        maxlength="100"
        v-model="data.content"
      ></textarea>
    </div>

    <button class="button" @click="updatePost(data)">수정</button>

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
                          tag: value.beforeImage.imageTag,
                        },
                        {
                          id: value.afterImage.imageId,
                          url: value.afterImage.imageUrl,
                          tag: value.afterImage.imageTag,
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
            style="display: none"
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
              <div class="sub-wrap">
                <div
                  :style="{
                    backgroundImage:
                      'url(' + userStore.photoSelect[0]?.url + ')',
                  }"
                  class="sub-image"
                  :class="{ firstCheck: firstCheck }"
                  @click="userStore.daePyoImage = userStore.photoSelect[0]?.url"
                ></div>

                <i
                  class="bi bi-check-circle-fill check-icon"
                  style="font-size: 20px; background-color: #ffffff"
                  @click="
                    pushSelectedNumber(
                      userStore.photoSelect[0]?.id,
                      userStore.photoSelect[0]?.url,
                      userStore.photoSelect[0]?.tag,
                    ),
                      (firstCheck = !firstCheck)
                  "
                ></i>
              </div>
              <div class="sub-wrap">
                <div
                  :style="{
                    backgroundImage:
                      'url(' + userStore.photoSelect[1]?.url + ')',
                  }"
                  class="sub-image"
                  :class="{ secondCheck: secondCheck }"
                  @click="userStore.daePyoImage = userStore.photoSelect[1]?.url"
                ></div>

                <i
                  class="bi bi-check-circle-fill check-icon"
                  style="font-size: 20px; background-color: #ffffff"
                  @click="
                    pushSelectedNumber(
                      userStore.photoSelect[1]?.id,
                      userStore.photoSelect[1]?.url,
                      userStore.photoSelect[1]?.tag,
                    ),
                      (secondCheck = !secondCheck)
                  "
                ></i>
              </div>
            </div>

            <!-- 버튼 -->
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
                data-bs-dismiss="modal"
                aria-label="Close"
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
import DateFormat from '@/components/molecules/common/DateFormat.vue';
import { useCommunityStore } from '@/stores/community.js';
import { useUserStore } from '@/stores/user.js';
import { ref } from 'vue';
// import router from '@/router';
import { useRoute, useRouter } from 'vue-router';

const myRouter = useRouter();
const route = useRoute();
const communityStore = useCommunityStore();
const userStore = useUserStore();

const data = ref({
  title: communityStore.post.item?.title,
  content: communityStore.post.item?.content,
  privacyBound: 1,
  images: [],
  representative: 0,
});

const firstCheck = ref(false);
const secondCheck = ref(false);
const selectTags = ref([communityStore.post.item?.images[0]?.imageTag]);

console.log(route.params.postId);

async function updatePost(data) {
  const context = {
    postId: route.params.postId,
    title: data.title,
    content: data.content,
    privacyBound: 1,
    images: userStore.selectedPhotoList,
    representative: 0,
  };

  await communityStore.updatePost(context);

  myRouter.push({ name: 'CommunityList' });
}

const selectPhoto = () => {
  userStore.urlPhotoList = userStore.urlList;
  userStore.urlList = [];
  userStore.selectedPhotoList = userStore.selectedPhoto;
};

const pushSelectedNumber = (num, url, tag) => {
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

  checked = true;
  selectTags.value.filter((e) => {
    if (e === tag) {
      checked = false;
      const index = selectTags.value.indexOf(e);
      selectTags.value.splice(index, 1);
    }
  });
  if (checked) {
    selectTags.value.push(tag);
  }
};

userStore.getMyPhoto();

userStore.urlList = [];
if (communityStore.post.item?.images[1]) {
  userStore.urlPhotoList = [
    communityStore.post.item?.images[0]?.imageUrl,
    communityStore.post.item?.images[1]?.imageUrl,
  ];
  userStore.photoSelect = [
    communityStore.post.item?.images[0]?.imageId,
    communityStore.post.item?.images[1]?.imageId,
  ];
  userStore.selectedPhoto = [
    communityStore.post.item?.images[0]?.imageId,
    communityStore.post.item?.images[1]?.imageId,
  ];
} else {
  userStore.urlPhotoList = [communityStore.post.item?.images[0]?.imageUrl];
  userStore.photoSelect = [communityStore.post.item?.images[0]?.imageId];
  userStore.selectedPhoto = [communityStore.post.item?.images[0]?.imageId];
}
userStore.selectTag = [];

userStore.selectedPhotoList = userStore.selectedPhoto;
</script>

<style scoped>
@keyframes openDialog {
  0% {
    bottom: -600px;
  }
  100% {
    bottom: -98px;
  }
}
@media (min-width: 1024px) {
  .community-write {
    min-width: 935px;
    max-width: 935px;
  }
}
.firstCheck,
.secondCheck {
  opacity: 0.5;
}

.community-write {
  width: calc(100% - 2 * var(--grid-side));
  margin: 0 auto;
  margin-top: var(--grid-vertical);
  margin-bottom: var(--grid-vertical);

  display: flex;
  flex-direction: column;
  gap: var(--grid-vertical);
  overflow: scroll;
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
  bottom: -98px;
  animation-name: openDialog;
  animation-duration: 0.3s;
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
.sub-wrap {
  width: 100%;
  position: relative;
}
.sub-image {
  width: 100%;
  padding-bottom: 100%;
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
.one-image {
  width: 100%;
}
.two-image {
  width: calc(50% - 5px);
}
.check-icon {
  position: absolute;
  top: 0;
  left: 0;
}
.tag-wrap {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: var(--grid-vertical);
}
.tag {
  padding: 3px 10px;
}
</style>
