<template>
  <div class="main-upload d-flex align-items-center justify-content-center">
    <div
      class="main-upload-form outer d-flex flex-column justify-content-center"
    >
      <div class="btn-toggle-mode">토글버튼</div>
      <div class="btn-mode-change">영상공간</div>
      <div class="btn-upload-image">점수버튼</div>
    </div>
  </div>
</template>

<script setup></script>

<style>
.main-upload {
  min-height: calc(100vh - var(--size-h-header));
  width: 100vw;
}

.main-upload-form {
  border-radius: 20px;
  padding: 8px;
  width: 300px;
}
</style>
