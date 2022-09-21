<template>
  <div class="toggle-switch">
    <div class="toggle-button"></div>
  </div>
</template>

<script setup>
// import { ref } from 'vue';
</script>

<style scoped>
.toggle-switch {
  width: 3rem;
  height: 1.5rem;
  display: block;
  position: relative;
  border-radius: 2rem;
  border: 1px solid #cacafc;
  cursor: pointer;
}
.toggle-button {
  width: 1rem;
  height: 1rem;
  position: absolute;
  top: 50%;
  left: 0.2rem;
  transform: translateY(-50%);
  border-radius: 50%;
  background: #cacafc;
}
.toggle {
  background-color: brown;
}
</style>
