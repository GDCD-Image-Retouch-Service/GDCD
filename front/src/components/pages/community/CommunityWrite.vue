<template>
  <div class="community-write">
    <!-- 제목 -->
    <div class="wrap">
      <div>제목</div>
      <input type="text" class="write-input" v-model="data.title" />
    </div>

    <!-- 이미지 -->
    <div class="wrap">
      <div>이미지</div>

      <a
        class="btn image-input"
        data-bs-toggle="modal"
        href="#exampleModalToggle"
        role="button"
        id="image-input"
      >
        <img
          :src="userStore.selectedPhoto?.[0]"
          alt=""
          class="selected-image" />
        <img :src="userStore.selectedPhoto?.[1]" alt="" class="selected-image"
      /></a>
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
              <date-format :updateInfo="key" />
              <div class="image-wrap">
                <div v-for="value in values" :key="value">
                  <label for="select-image">
                    <img
                      :src="value.beforeImage.imageUrl"
                      alt=""
                      class="photo-image"
                      @click="
                        userStore.photoSelect = [
                          {
                            id: value.beforeImage.imageId,
                            url: value.beforeImage.imageUrl,
                          },
                          {
                            id: value.afterImage.imageId,
                            url: value.afterImage.imageUrl,
                          },
                        ]
                      "
                    />
                  </label>
                </div>
              </div>
            </div>
          </div>
          <button
            class="btn"
            data-bs-target="#exampleModalToggle2"
            data-bs-toggle="modal"
            data-bs-dismiss="modal"
            id="select-image"
          >
            Open second modal
          </button>
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
            <img src="" alt="" />
            <img
              :src="userStore.photoSelect[0]?.url"
              alt=""
              class="main-image"
            />

            <div style="display: flex; gap: 10px">
              <img
                :src="userStore.photoSelect[0]?.url"
                alt=""
                class="sub-image"
              />
              <img
                :src="userStore.photoSelect[1]?.url"
                alt=""
                class="sub-image"
              />
            </div>
          </div>
          <div>
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
</template>

<script setup>
import DateFormat from '@/components/molecules/common/DateFormat.vue';
import { useCommunityStore } from '@/stores/community.js';
import { useUserStore } from '@/stores/user.js';
import { ref } from 'vue';

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
    images: [userStore.photoSelect[0].id, userStore.photoSelect[1].id],
    representative: 0,
  };
  console.log(context, userStore.photoSelect);
  communityStore.createPost(context);
};

const selectPhoto = () => {
  userStore.selectedPhoto = [
    userStore.photoSelect[0]?.url,
    userStore.photoSelect[0]?.url,
  ];
  console.log(userStore.selectedPhoto[0]);
};

userStore.getMyPhoto();
</script>

<style scoped>
@keyframes openDialog {
  0% {
    bottom: -500px;
  }
  100% {
    bottom: -160px;
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
  bottom: -160px;
  animation-name: openDialog;
  animation-duration: 1s;
  margin: 0;
  border-radius: 130px 130px 0 0;
  height: 700px;
}
.image-wrap {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}
.photo-image {
  width: 100px;
  height: 100px;
  object-fit: cover;
  margin-bottom: 30px;
  margin-top: 10px;
}
.modal-body {
  height: 500px;
  overflow: scroll;
}

.main-image {
  width: 100%;
}

.sub-image {
  width: 50%;
}
.befor-btn {
  background-color: var(--delete-btn);
  width: 50%;
  color: white;
  height: 38px;
  border: none;
}
.select-btn {
  background-color: var(--theme-color);
  width: 50%;
  color: white;
  border: none;
  height: 38px;
}
.selected-image {
  width: 100%;
}
</style>
