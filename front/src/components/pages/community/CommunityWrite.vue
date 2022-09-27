<template>
  <div class="community-write">
    <!-- 제목 -->
    <div class="wrap">
      <div>제목</div>
      <input type="text" class="write-input" />
    </div>

    <!-- 내용 -->
    <div class="wrap">
      <div>내용</div>
      <textarea class="content-area" maxlength="100"></textarea>
    </div>

    <button class="button">제출</button>
  </div>
</template>

<script setup></script>

<style scoped>
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
</style>
