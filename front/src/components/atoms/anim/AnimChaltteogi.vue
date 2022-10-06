<template>
  <div class="anim-chaltteogi">
    <img
      :src="require('@/assets/sprite/Chaltteogi.png')"
      id="animate"
      style="width: 200px; height: 144px"
    />
    <div class="floor"></div>
  </div>
</template>

<script setup></script>

<style scoped>
.anim-chaltteogi {
  position: relative;
  height: 240px;
  width: 240px;
}

.floor {
  height: 40px;
  width: 200px;
  background: var(--color-theme);
  bottom: 0%;
  left: 20px;
  position: absolute;
  border-radius: 50%;
}

#animate {
  z-index: 9000;
  position: absolute;
  top: 80px;
  left: 20px;
  /* transform: translate(-50%, -50%); */
  animation: move 2s ease-in-out infinite;
}

@keyframes move {
  0% {
    height: 144px;
    top: 80px;
  }
  10% {
    height: 120px;
    top: 104px;
  }
  20% {
    top: 80px;
    height: 144px;
    transform: rotate(0deg);
  }
  /* 25% {
    top: 60px;
    height: 144px;
    transform: rotate(0deg);
  } */
  30% {
    top: -50px;
    height: 144px;
    transform: rotate(-180deg);
  }
  40% {
    top: 80px;
    height: 144px;
    transform: rotate(-360deg);
  }
  100% {
    top: 80px;
    height: 144px;
    transform: rotate(-360deg);
  }
}
</style>
