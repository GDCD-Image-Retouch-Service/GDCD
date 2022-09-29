<template>
  <div style="width: 100%; display: flex; flex-direction: row-reverse">
    <div
      class="toggle-switch"
      @click="isToggleButton = !isToggleButton"
      :class="{ 'toggled-switch': isToggleButton }"
    >
      <div
        class="toggle-button"
        :class="{ 'toggled-button': isToggleButton }"
      ></div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

let isToggleButton = ref(false);

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
  margin-bottom: 10px;
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
.toggled-switch {
  width: 3rem;
  height: 1.5rem;
  display: block;
  position: relative;
  border-radius: 2rem;
  border: 1px solid #cacafc;
  cursor: pointer;
  transition: all 0.2s ease-in;
  background: #cacafc;
}
.toggled-button {
  left: calc(100% - 1.1rem);
  background: #ffffff;
  transform: translateY(-50%);
  border-radius: 50%;
  width: 1rem;
  height: 1rem;
  position: absolute;
}
</style>
