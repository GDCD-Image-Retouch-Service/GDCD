<template>
  <div class="btn-slider outer"></div>
</template>

<script setup></script>

<style scoped>
.btn-slider {
  z-index: 2;
  position: absolute;
  left: calc(0% + 4px);
  /* left: calc(50% + 4px); */
  height: calc(var(--size-h-header) - 8px);
  line-height: var(--size-h-header);
  border-radius: var(--size-radius);
  font-size: 20pt;
  width: calc(50% - 8px);
  background: white;
}
</style>
