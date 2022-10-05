<template>
  <div class="btn-object outer" ref="btnObject">
    <!-- {{ props.objectData }} -->
  </div>
</template>

<script setup>
import { ref, defineProps, onMounted } from 'vue';

props;
const props = defineProps({
  objectData: String,
  naturalHeight: Number,
  naturalWidth: Number,
});

// data
const btnObject = ref(null);

onMounted(() => {
  if (props.objectData == 'IMAGE NOT SCORED') {
    console.log('이미지 점수 계산이 되지 않았습니다');
    return;
  }

  let fixedHeight = 0;
  let fixedWidth = 0;

  let dy = 0;
  let dx = 0;
  let scale = 1;

  const isHeightBigger = props.naturalHeight > props.naturalWidth;

  if (isHeightBigger) {
    scale = 380 / props.naturalWidth;

    fixedHeight = props.naturalHeight * scale;
    fixedWidth = 380;

    dy = (fixedHeight - 380) / 2;
    dx = 0;
  } else {
    scale = 380 / props.naturalHeight;

    fixedHeight = 380;
    fixedWidth = props.naturalWidth * scale;

    dy = 0;
    dx = (fixedWidth - 380) / 2;
  }

  const objectData = props.objectData.split(';');
  console.log('디텍션:', objectData);
  // const objectName = objectData[0];
  const objectLU = objectData[1].split(',');
  const objectRD = objectData[2].split(',');

  // console.log('name', objectName);
  // console.log('L', objectLU[0]);
  // console.log('U', objectLU[1]);
  // console.log('R', objectRD[0]);
  // console.log('D', objectRD[1]);

  console.log('NH', `${props.naturalHeight}px`);
  console.log('NW', `${props.naturalWidth}px`);

  btnObject.value.style.top = `${objectLU[1] * scale - dy}px`;
  btnObject.value.style.left = `${objectLU[0] * scale - dx}px`;

  btnObject.value.style.width = `${(objectRD[0] - objectLU[0]) * scale}px`;

  console.log(`${(objectRD[0] - objectLU[0]) * scale}px`);

  btnObject.value.style.height = `${(objectRD[1] - objectLU[1]) * scale}px`;

  console.log(`${(objectRD[1] - objectLU[1]) * scale}px`);
});
</script>

<style scoped>
.btn-object {
  position: absolute;
  top: 0;
  left: 0;
  width: 0px;
  height: 0px;
  border: solid 4px lightgray;
  border-radius: var(--size-radius);
}

.blur.btn-object {
  border: solid 4px var(--color-theme);
}
</style>
