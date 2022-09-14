<template>
  <div class="main-service">
    디자인 공사중 입니다
    <ToggleButton class="width:100%" />
    공사중 이ㅣㅂ니다
    <ImageInput />
    <ScoreReceiveButton />
    <HowToUse />
  </div>
</template>

<script setup>
import ToggleButton from '@/components/molecules/ToggleButton.vue';
import ImageInput from '@/components/organisms/ImageInput.vue';
import ScoreReceiveButton from '@/components/atoms/button/ScoreReceiveButton.vue';
import HowToUse from '@/components/molecules/HowToUse.vue';
</script>

<style>
.main-service {
  width: 100%;
}
</style>
