<template>
  <div class="photo-view">
    <router-view />
  </div>
</template>

<script setup></script>

<style scoped>
.photo-view {
  margin-top: var(--size-h-header);
  margin-bottom: var(--size-h-footer);
  overflow: scroll;
}
</style>
