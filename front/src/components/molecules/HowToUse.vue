<template>
  <div>
    <div id="how-to-use-header" @click="clickHowToUse">
      <div>개떡찰떡 사용법</div>
      <div>
        <v-icon> mdi-chevron-down </v-icon>
      </div>
    </div>

    <!-- <div id="how-to-use-content" :style="{ display: mainStore.toggleHowToUse }"> -->
    <div id="how-to-use-content">
      <div>
        <span style="color: var(--main-color)">1. </span>최상단의 토글버튼으로
        앨범과 카메라를 선택하고 이미지를 업로드 합니다.
      </div>

      <div>
        <span style="color: var(--main-color)">2. </span>점수 받기 버튼을 클릭해
        AI가 측정한 점수를 받습니다.
      </div>

      <div>
        <span style="color: var(--main-color)">3. </span>AI가 측정한 점수가
        마음에 들지 않을 경우 이미지 개선하기 버튼을 클릭해 이미지를 개선합니다.
      </div>

      <div>
        <span style="color: var(--main-color)">4. </span>이미지 개선 시에 AI가
        판별한 오브젝트 중 원하는 오브젝트를 선택합니다. 선택된 오브젝트는
        이미지 개선에서 누락되지 않습니다.
      </div>

      <div>
        <span style="color: var(--main-color)">5. </span>AI는 개선된 5장의
        이미지를 점수와 함께 보여줍니다.
      </div>

      <div>
        <span style="color: var(--main-color)">6. </span>마지막으로 데이터
        수집에 동의하여 커뮤니티에 올릴 데이터를 저장할 수 있습니다.
      </div>
    </div>
  </div>
</template>

<script setup>
// import { useMainStore } from '@/store/index.js'

// const mainStore = useMainStore();

// async function clickHowToUse() {

//   let promise = new Promise((resolve) => {
//     setTimeout(() => resolve("완료!"), 0)
//       if ( mainStore.toggleHowToUse === 'none' ) {
//       mainStore.toggleHowToUse = 'block'
//       }
//       else {
//         mainStore.toggleHowToUse = 'none'
//       }
//   });

//   let result = await promise; // 프라미스가 이행될 때까지 기다림 (*)

//   result;
//   window.scrollTo(0,document.body.scrollHeight);

// }
</script>

<style scoped>
#how-to-use-header {
  background-color: var(--white);
  height: 50px;
  display: flex;
  padding: 0 var(--half-grid);
  justify-content: space-between;
}
#how-to-use-header div {
  margin-top: calc(25px - 12px);
}
#how-to-use-header div:first-child {
  width: calc(100% - var(--grid) - 24px);
}
#how-to-use-content {
  width: calc(100% - var(--grid));
  margin: 0 auto;
  display: flex;
  flex-direction: column;
  margin-top: 10px;
  gap: var(--mini-gap);
  word-wrap: break-word;
}
</style>
