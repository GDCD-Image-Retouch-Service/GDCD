<template>
  <div class="post-card outer">
    <div>게시글 디자인</div>
    <div>포토카드 같이</div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.post-card {
  z-index: 1;
  display: block;
  position: relative;
  top: 10px;
  left: 10px;
  width: calc(100% - 20px);
  height: calc(100% - 20px);
  /* background: var(--theme-color); */
  border-radius: 20px;

  transition-duration: 0.1s;
}

.post-card:hover {
  z-index: 20;
  transform: scale(1.05); /*  default */
  -webkit-transform: scale(1.05); /*  크롬 */
  -moz-transform: scale(1.05); /* FireFox */
  -o-transform: scale(1.05); /* Opera */
}
</style>
