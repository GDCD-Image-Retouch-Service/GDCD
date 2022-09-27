<template>
  <div class="loading-dots d-flex align-items-center justify-content-center">
    <div class="flex-grow-1"></div>
    <div class="loading-dot"></div>

    <div class="flex-grow-1"></div>
    <div class="loading-dot"></div>

    <div class="flex-grow-1"></div>
    <div class="loading-dot"></div>

    <div class="flex-grow-1"></div>
    <div class="loading-dot"></div>

    <div class="flex-grow-1"></div>
    <div class="loading-dot"></div>

    <div class="flex-grow-1"></div>
  </div>
</template>

<script setup></script>

<style scoped>
.loading-dots {
  height: 100px;
  width: 100px;
}

.loading-dot {
  width: 10px;
  height: 10px;

  background: #999;
  animation: preload 2s infinite;
  border-radius: 100%;
}

.loading-dot:nth-child(4) {
  animation-delay: 0.2s;
}

.loading-dot:nth-child(6) {
  animation-delay: 0.4s;
}

.loading-dot:nth-child(8) {
  animation-delay: 0.6s;
}

.loading-dot:nth-child(10) {
  animation-delay: 0.8s;
}

@keyframes preload {
  0% {
    opacity: 1;
  }
  50% {
    opacity: 0.2;
  }
  100% {
    opacity: 1;
  }
}
</style>
