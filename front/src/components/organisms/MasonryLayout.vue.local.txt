<template>
  <div class="masonry-layout d-flex flex-column align-items-center main">
    <div
      class="masonry-container"
      style="width: 100vw; display: grid; column-gap: 1px; grid-auto-rows: 1px"
    >
      <div
        v-for="(widgetNum, index) in widgetList"
        :id="`dragItem-${index}-widget-${widgetNum}`"
        :key="index"
        class="masonry-brick"
      >
        <div
          class="masonry-item"
          :style="{ height: `${widgetNum}px`, background: 'blue' }"
        >
          내용물
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { ref, nextTick, onMounted } from 'vue';

export default {
  setup() {
    const widgetList = ref([
      50, 100, 400, 300, 500, 200, 50, 100, 400, 300, 500, 200, 100, 400, 300,
      500, 200, 100, 400, 300, 500, 200,
    ]);

    const componentList = [];

    async function init() {
      nextTick(() => {
        masonryLayoutSetting();
        window.addEventListener('resize', masonryLayoutSetting);
      });
    }

    function masonryLayoutSetting() {
      const masonryContainer = document.querySelector('.masonry-container');
      if (!masonryContainer) {
        return;
      }

      const masonryContainerStyle = getComputedStyle(masonryContainer);

      const containerWidth = parseInt(
        masonryContainerStyle.getPropertyValue('width')
      );

      if (containerWidth > 1440) {
        masonryContainer.style.gridTemplateColumns = `repeat(5, calc(${containerWidth}px / 5))`;
      } else if (containerWidth > 960) {
        masonryContainer.style.gridTemplateColumns = `repeat(4, calc(${containerWidth}px / 4))`;
      } else if (containerWidth > 560) {
        masonryContainer.style.gridTemplateColumns = `repeat(3, calc(${containerWidth}px / 3))`;
      } else {
        masonryContainer.style.gridTemplateColumns = `repeat(2, calc(${containerWidth}px / 2))`;
      }

      const columnGap = parseInt(
        masonryContainerStyle.getPropertyValue('column-gap')
      );
      const autoRows = parseInt(
        masonryContainerStyle.getPropertyValue('grid-auto-rows')
      );

      document.querySelectorAll('.masonry-brick').forEach((el) => {
        el.style.gridRowEnd = `span ${Math.ceil(
          el.querySelector('.masonry-item').scrollHeight / autoRows +
            columnGap / autoRows
        )}`;
      });
    }

    onMounted(() => {
      init();
    });

    return {
      componentList,
      widgetList,
      masonryLayoutSetting,
    };
  },
};
</script>

<style scoped>
.drag-container {
  -webkit-user-select: none;
  -moz-user-select: none;
  user-select: none;
  transition-duration: 0.2s;
}

.blank {
  border: 1px dashed #000;
  border-radius: 16px;
}

/* .widget {
  z-index: 1;
  position: relative;
  border-radius: 16px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;

  transition-duration: 0.2s;
}

.widget-component {
  width: 100%;
  padding: 16px;
  display: block;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.widget-component * {
  display: block;
  color: #000000;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
} */
</style>
