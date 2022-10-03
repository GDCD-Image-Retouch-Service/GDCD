<template>
  <div
    class="btn-scroll-to-top outer d-flex align-items-center justify-content-center"
    @click="scrollToTop()"
  >
    <i class="bi bi-arrow-up"></i>
  </div>
</template>

<script setup>
const scrollToTop = () => {
  document.getElementById('app').scrollTop = 0;
};
</script>

<style scoped>
.btn-scroll-to-top {
  z-index: 1;
  position: fixed;
  left: calc(50vw - 16px);
  bottom: calc(var(--size-h-footer) + 4px);
  width: 32px;
  height: 32px;
  border-radius: 50%;
  background: gray;
  color: white;
  font-size: 12pt;
}
</style>
