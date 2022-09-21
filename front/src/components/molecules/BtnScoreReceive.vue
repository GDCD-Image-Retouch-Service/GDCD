<template>
  <div class="btn-score-receive">
    <div>점수 받기</div>
  </div>
</template>

<script setup></script>

<style scoped>
.btn-score-receive {
  height: var(--size-h-header);
  width: 90%;
  max-width: 380px;
  border-radius: var(--size-radius);
  background-color: var(--theme-color);
  box-shadow: var(--default-shadow);
}
.btn-score-receive div {
  margin: 13px 0;
  color: var(--white);
  font-weight: 700;
  font-size: 16px;
  text-align: center;
}
</style>
