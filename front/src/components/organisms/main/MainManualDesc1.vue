<template>
  <div class="main-manual-desc main">
    <p>
      1. 최상단의 토글버튼으로 앨범과 카메라를 선택하고 이미지를 업로드 합니다.
    </p>
    <p>2. 점수 받기 버튼을 클릭해 AI가 측정한 점수를 받습니다.</p>
    <p>
      3. AI가 측정한 점수가 마음에 들지 않을 경우 이미지 개선하기 버튼을 클릭해
      이미지를 개선합니다.
    </p>
    <p>
      4. 이미지 개선 시에 AI가 판별한 피사체 중 원하는 피사체를 선택합니다.
      선택된 피사체는 이미지 개선에서 누락되지 않습니다.
    </p>
    <p>5. AI는 개선된 5장의 이미지를 점수와 함께 보여줍니다.</p>
    <p>
      6. 마지막으로 데이터 수집에 동의하여 커뮤니티에 올릴 데이터를 저장할 수
      있습니다.
    </p>
  </div>
</template>

<script setup></script>

<style scoped>
.main-manual-desc {
  width: 100%;
  padding: 0 var(--grid-side);
  display: flex;
  flex-direction: column;
  gap: 10px;
  padding-top: var(--grid-vertical);
}
</style>
