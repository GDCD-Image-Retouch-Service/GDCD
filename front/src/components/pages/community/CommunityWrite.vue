<template>
  <div class="community-write">
    <!-- 제목 -->
    <div class="wrap">
      <div>제목</div>
      <input type="text" class="write-input" />
    </div>
  </div>
</template>

<script setup></script>

<style>
.wrap {
  display: flex;
  flex-direction: column;
  gap: 20px;
}
.write-input {
  width: 100%;
  height: 37px;
  border: none;
}
</style>
