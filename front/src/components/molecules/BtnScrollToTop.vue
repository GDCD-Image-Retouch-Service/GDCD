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
  window.scrollTo({ top: 100000, left: 0, behavior: 'smooth' });
  console.log('눌림');
};
</script>

<style>
.btn-scroll-to-top {
  position: fixed;
  right: 10px;
  bottom: 48px;
  width: 50px;
  height: 50px;
  border-radius: 50%;
  background: #e62739;
  color: white;
  font-size: 12pt;
}
</style>
