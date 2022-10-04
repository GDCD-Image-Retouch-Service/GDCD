<template>
  <div class="btn-object blur outer" ref="btnObject">
    {{ props.objectData }}
  </div>
</template>

<script setup>
import { ref, defineProps, onMounted } from 'vue';

// props
const props = defineProps({
  objectData: String,
  naturalHeight: Number,
  naturalWidth: Number,
});

// data
const btnObject = ref(null);

const objectData = props.objectData.split(';');
const objectName = objectData.value[0];
const objectCoord = objectData.value[1].split(',');

console.log('name', objectName);
console.log('x', objectCoord[0]);
console.log('y', objectCoord[1]);

onMounted(() => {
  console.log('props1', props.objectData);

  const fixedHeight = 0;
  const fixedWidth = 0;

  const isHeightBigger = props.naturalHeight > props.naturalWidth;

  if (isHeightBigger.value) {
    fixedHeight.value = (props.naturalHeight * 380) / props.naturalWidth;
    fixedWidth.value = 380;
  } else {
    fixedHeight.value = 380;
    fixedWidth.value = (props.naturalWidth * 380) / props.naturalHeight;
  }

  const objectData = props.objectData.split(';');
  const objectName = objectData[0];
  const objectLU = objectData[1].split(',');
  const objectRD = objectData[2].split(',');

  console.log('name', objectName);
  console.log('L', objectLU[0]);
  console.log('U', objectLU[1]);
  console.log('R', objectRD[0]);
  console.log('D', objectRD[1]);

  console.log('NH', `${props.naturalHeight}px`);
  console.log('NW', `${props.naturalWidth}px`);

  console.log('NH', `${(objectLU[0] * 380) / props.naturalHeight}px`);
  console.log('NW', `${(objectLU[1] * 380) / props.naturalWidth}px`);

  btnObject.value.style.top = `${(objectLU[1] * 380) / props.naturalWidth}px`;
  btnObject.value.style.left = `${(objectLU[0] * 380) / props.naturalHeight}px`;

  btnObject.value.style.width = `${
    ((objectRD[0] - objectLU[0]) * 380) / props.naturalWidth
  }px`;

  btnObject.value.style.height = `${
    ((objectRD[1] - objectLU[1]) * 380) / props.naturalWidth
  }px`;
});
</script>

<style scoped>
.btn-object {
  position: absolute;
  top: 0;
  left: 0;
  width: 0px;
  height: 0px;
  background: red;
}
</style>
