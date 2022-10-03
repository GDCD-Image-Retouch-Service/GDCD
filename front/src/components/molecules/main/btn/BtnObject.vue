<template>
  <div class="btn-object" ref="btnObject">{{ props.objectData }}</div>
</template>

<script setup>
import { ref, defineProps, onMounted } from 'vue';

// props
const props = defineProps({
  objectData: String,
  natureHeigth: Number,
  natureWidth: Number,
});

// data
const btnObject = ref(null);

// const objectData = props.objectData.split(';');
// const objectName = objectData.value[0];
// const objectCoord = objectData.value[1].split(',');

// console.log('name', objectName);
// console.log('x', objectCoord[0]);
// console.log('y', objectCoord[1]);

onMounted(() => {
  console.log('props1', props.objectData);
  const objectData = props.objectData.split(';');
  const objectName = objectData[0];
  const objectUL = objectData[1].split(',');
  const objectDR = objectData[2].split(',');

  console.log('name', objectName);
  console.log('L', objectUL[0]);
  console.log('U', objectUL[1]);
  console.log('R', objectDR[0]);
  console.log('D', objectDR[1]);
});
</script>

<style scoped>
.btn-object {
  position: absolute;
  top: 0;
  left: 0;
  background: red;
  opacity: 0.5;
}
</style>
