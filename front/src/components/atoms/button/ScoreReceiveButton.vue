<template>
  <div id="score-receive-button">
    <div>점수 받기</div>
  </div>
</template>

<script setup></script>

<style scoped>
#score-receive-button {
  width: calc(100% - var(--grid));
  height: 50px;
  margin: 0 auto;
  border-radius: 5px;
  background-color: var(--main-color);
  box-shadow: var(--default-shadow);
}
#score-receive-button div {
  margin: 13px 0;
  color: var(--white);
  font-weight: 700;
  font-size: 16px;
  text-align: center;
}
</style>
