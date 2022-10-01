<template>
  <div class="anim-chaltteogi">
    <div class="floor"></div>
  </div>
</template>

<script setup></script>

<style scoped>
.anim-chaltteogi {
  position: relative;
  height: 240px;
  width: 240px;
  background: blue;
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
</style>
