<template>
  <div>
    <router-view />
  </div>
</template>

<script setup>
import { useBasicStore } from '@/stores/basic.js';

const basicStore = useBasicStore();
basicStore.isActiveFooter = 2;
</script>

<style scoped></style>
