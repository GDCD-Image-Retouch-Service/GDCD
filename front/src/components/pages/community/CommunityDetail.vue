<template>
 <div class="community-detail outer">
    <community-detailHeader />
 </div>
</template>

<script setup>
import CommunityDetailHeader from "@/components/organisms/CommunityDetailHeader.vue";
</script>

<style scoped>
.community-detail {
    width: calc(100vw - 40px);
    border-radius: 20px;
    /* border: 1px solid #9A9A9A; */
    margin-top: 30px;
    margin-bottom: 80px;
}
</style>