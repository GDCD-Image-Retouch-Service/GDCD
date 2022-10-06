<template>
  <div class="community-write">
    <!-- 제목 -->
    <div class="wrap">
      <div class="wrap-title">제목</div>
      <input
        type="text"
        class="write-input"
        v-model="data.title"
        maxlength="30"
      />
    </div>

    <!-- 이미지 -->
    <div class="wrap" v-if="userStore.urlPhotoList.length === 0">
      <div class="wrap-title">이미지</div>
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
            (userStore.urlList = []),
            (communityStore.writeFirstModal = !communityStore.writeFirstModal);
          (firstCheck = false), (secondCheck = false);
        "
      >
        <div class="image-content">이미지를 올려주세요</div>
      </a>
    </div>
    <div class="wrap" v-if="userStore.urlPhotoList.length === 1">
      <div class="wrap-title">이미지</div>
      <a
        data-bs-toggle="modal"
        href="#exampleModalToggle"
        role="button"
        id="image-input"
        @click="
          (firstCheck = false),
            (secondCheck = false),
            (userStore.selectedPhoto = []),
            (userStore.urlList = []),
            (communityStore.writeFirstModal = !communityStore.writeFirstModal)(
              (firstCheck = false),
            ),
            (secondCheck = false)
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
      <div class="wrap-title">이미지</div>
      <a
        id="image-input"
        @click="
          (firstCheck = false),
            (secondCheck = false),
            (userStore.selectedPhoto = []),
            (userStore.urlList = []),
            (communityStore.writeFirstModal = !communityStore.writeFirstModal)(
              (firstCheck = false),
            ),
            (secondCheck = false)
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

    <!-- 대표 이미지 선택
    <div class="wrap">
      <div class="wrap-title">대표이미지를 선택해주세요.</div>
      <div>대표 이미지는 게시글의 표지에 사용됩니다.</div>
    </div> -->
    <!-- 내용 -->
    <div class="wrap">
      <div class="wrap-title">내용</div>
      <textarea
        class="content-area"
        maxlength="100"
        v-model="data.content"
        placeholder="욕설,비방, 광고 등의 내용은 통보없이 삭제될 수 있습니다."
      ></textarea>
    </div>

    <button class="button" @click="createPost(data)">제출</button>

    <!-- 첫 번째 모달 -->
    <template>
      <div>
        <v-dialog v-model="communityStore.writeFirstModal" class="dialog">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-body">
                <div
                  v-for="(values, key) in userStore.myPhoto.item"
                  :key="values"
                >
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
                              id: value.beforeImage?.imageId,
                              url: value.beforeImage?.imageUrl,
                              tag: value.beforeImage?.imageTag,
                            },
                            {
                              id: value.afterImage?.imageId,
                              url: value.afterImage?.imageUrl,
                              tag: value.afterImage?.imageTag,
                            },
                          ]),
                            (userStore.daePyoImage =
                              value.beforeImage.imageUrl);
                          userStore.selectTag = value.beforeImage.imageTag;
                          communityStore.writeSecondModal = true;
                          (firstCheck = false), (secondCheck = false);
                        "
                      >
                        <!-- <img :src="value.beforeImage.imageUrl" alt="" class="photo-image" /> -->
                      </div>
                    </label>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </v-dialog>
      </div></template
    >
    <!-- 두 번째 모달 -->
    <template>
      <div>
        <v-dialog v-model="communityStore.writeSecondModal" class="dialog">
          <div class="modal-dialog">
            <div class="modal-content">
              <div class="modal-body">
                <!-- 메인 이미지 -->
                <img :src="userStore.daePyoImage" alt="" class="main-image" />

                <div style="display: flex; gap: 10px">
                  <div
                    class="sub-wrap"
                    @click="
                      pushSelectedNumber(
                        userStore.photoSelect[0]?.id,
                        userStore.photoSelect[0]?.url,
                        userStore.photoSelect[0]?.tag,
                      ),
                        (firstCheck = !firstCheck);
                      userStore.daePyoImage = userStore.photoSelect[0]?.url;
                    "
                  >
                    <div
                      :style="{
                        backgroundImage:
                          'url(' + userStore.photoSelect[0]?.url + ')',
                      }"
                      class="sub-image"
                      :class="{ firstCheck: firstCheck }"
                    ></div>
                    <span
                      v-if="!firstCheck"
                      class="material-icons-outlined check-icon"
                      style="font-size: 20px; background-color: #ffffff"
                    >
                      check_circle
                    </span>

                    <span
                      v-else
                      class="material-icons check-icon"
                      style="font-size: 20px; background-color: #ffffff"
                      @click="
                        pushSelectedNumber(
                          userStore.photoSelect[0]?.id,
                          userStore.photoSelect[0]?.url,
                          userStore.photoSelect[0]?.tag,
                        ),
                          (firstCheck = !firstCheck)
                      "
                    >
                      check_circle
                    </span>
                  </div>
                  <!--  -->
                  <div
                    class="sub-wrap"
                    v-if="userStore.photoSelect[1]?.url"
                    @click="
                      pushSelectedNumber(
                        userStore.photoSelect[1]?.id,
                        userStore.photoSelect[1]?.url,
                        userStore.photoSelect[1]?.tag,
                      ),
                        (secondCheck = !secondCheck);
                      userStore.daePyoImage = userStore.photoSelect[1]?.url;
                    "
                  >
                    <div
                      :style="{
                        backgroundImage:
                          'url(' + userStore.photoSelect[1]?.url + ')',
                      }"
                      class="sub-image"
                      :class="{ secondCheck: secondCheck }"
                    ></div>

                    <span
                      v-if="!secondCheck"
                      class="material-icons-outlined check-icon"
                      style="font-size: 20px; background-color: #ffffff"
                    >
                      check_circle
                    </span>

                    <span
                      v-else
                      class="material-icons check-icon"
                      style="font-size: 20px; background-color: #ffffff"
                      @click="
                        pushSelectedNumber(
                          userStore.photoSelect[1]?.id,
                          userStore.photoSelect[1]?.url,
                          userStore.photoSelect[1]?.tag,
                        ),
                          (secondCheck = !secondCheck)
                      "
                    >
                      check_circle
                    </span>
                  </div>
                </div>

                <!-- 버튼 -->
                <div style="margin-top: var(--grid-vertical)">
                  <button
                    class="befor-btn"
                    @click="communityStore.writeSecondModal = false"
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
        </v-dialog>
      </div></template
    >
    <!-- 제출 실패 모달 -->

    <v-dialog v-model="dialog">
      <div class="error-alert">
        <div class="create-post-modal">
          <div class="modal-title">제목, 이미지, 내용은 필수 값입니다.</div>
          <div class="modal-close" @click="dialog = false">확인</div>
        </div>
      </div>
    </v-dialog>
  </div>
</template>

<script setup>
import DateFormat from '@/components/molecules/common/DateFormat.vue';
import { useCommunityStore } from '@/stores/community.js';
import { useUserStore } from '@/stores/user.js';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter();
const communityStore = useCommunityStore();
const userStore = useUserStore();
let dialog = ref(false);

const data = ref({
  title: '',
  content: '',
  privacyBound: 1,
  images: [],
  representative: 0,
});

const firstCheck = ref(false);
const secondCheck = ref(false);
const selectTags = ref([]);

async function createPost(data) {
  const context = {
    title: data.title,
    content: data.content,
    privacyBound: 1,
    images: userStore.selectedPhotoList,
    representative: 0,
  };
  if (
    (context.title != '') &
    (context.images.length != 0) &
    (context.content != '')
  ) {
    await communityStore.createPost(context);
    router.push({
      name: 'CommunityList',
    });
  } else {
    dialog.value = true;
    setTimeout(function () {
      dialog.value = false;
    }, 1000);
  }
}

const selectPhoto = () => {
  userStore.urlPhotoList = userStore.urlList;
  userStore.urlList = [];
  userStore.selectedPhotoList = userStore.selectedPhoto;
  communityStore.writeFirstModal = false;
  communityStore.writeSecondModal = false;
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

userStore.photoSelect = [];
userStore.selectedPhoto = [];
userStore.urlList = [];
userStore.urlPhotoList = [];
userStore.selectTag = [];
communityStore.writeFirstModal = false;
communityStore.writeSecondModal = false;
</script>

<style scoped>
@keyframes openDialog {
  0% {
    bottom: -100vh;
  }
  100% {
    bottom: calc(-50vh);
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
  margin: var(--grid-vertical) auto;
  overflow: scroll;
  display: flex;
  flex-direction: column;
  gap: var(--grid-vertical);
}
.wrap {
  display: flex;
  flex-direction: column;
  gap: 10px;
}
.wrap-title {
  height: 24px;
  line-height: 24px;
}
.write-input {
  width: 100%;
  height: 45px;
  border: 1px solid var(--instagram-grey);
  border-radius: 5px;
  background-color: var(--main-color);
  margin: 0 auto;
  padding: 10px;
  background-color: var(--color-main);
  font-size: 16px;
}
.content-area {
  padding: 10px;
  width: 100%;
  border: 1px solid var(--instagram-grey);
  resize: none;
  line-height: 22px;
  border-radius: 5px;
  height: 150px;
  background-color: var(--color-main);
  font-size: 16px;
}
.image-input {
  width: 100%;
  padding: 5px 5px 100% 5px;
  border: 1px solid var(--instagram-grey);
  border-radius: 5px;
  background-color: var(--color-main);
}

.modal-dialog {
  position: absolute;
  width: 100vw;
  min-width: 100%;
  max-width: 400px;
  bottom: calc(-50vh);
  animation-name: openDialog;
  animation-duration: 0.3s;
  margin: 0;
  border-radius: 10px 10px 0 0;
  height: 700px;
  background-color: #e8eaed;
  overflow: hidden;
  transform: translateX(-24px);
  padding: 10px;
}
.modal-content {
  max-height: 700px;
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
  height: 700px;
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
  background: none;
}
.tag-wrap {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
  margin-top: 20px;
}
.tag {
  background-color: var(--theme-color);
  padding: 8px 20px;
  border-radius: 20px;
  line-height: 20px;
  font-size: 16px;
}
.image-content {
  position: absolute;
  top: calc(50% - 12px);
  left: calc(50% - 75px);
}
.write-first-modal {
  width: 100%;
  max-width: 400px;
  height: 170px;
  background-color: var(--color-main);
  border-radius: 5px;
  text-align: center;
  position: relative;
  margin: 0 auto;
}
.error-alert {
  width: 100%;
  position: fixed;
  left: 0;
  bottom: 0;
  background-color: #ffffff;
  border: 1px solid var(--instagram-grey);
  border-radius: 5px;
  text-align: center;
}
.create-post-modal {
  width: 100%;
  max-width: 400px;
  height: 170px;
  background-color: var(--color-main);
  border-radius: 5px;
  text-align: center;
  position: relative;
  margin: 0 auto;
}
.modal-title {
  width: 100%;
  position: absolute;
  top: calc(40% - 12px);
  color: var(--color-reverse);
  font-family: 'Pretendard-Regular';
}
.modal-close {
  background-color: var(--theme-color);
  border-radius: 5px;
  border: none;
  color: var(--light-main-color);
  font-weight: 500;
  width: 259px;
  height: 38px;
  line-height: 38px;
  position: absolute;
  top: calc(75% - 12px);
  left: calc(50% - 130px);
}
</style>
