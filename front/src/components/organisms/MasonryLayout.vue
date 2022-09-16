<template>
  <div class="masonry-layout d-flex flex-column align-items-center main">
    <div
      class="masonry-container"
      style="width: 100vw; display: grid; column-gap: 0px; grid-auto-rows: 1px"
    >
      <div
        v-for="(widgetNum, index) in widgetList"
        :id="`dragItem-${index}-widget-${widgetNum}`"
        :key="index"
        class="masonry-brick"
      >
        <div class="masonry-item" :style="{ height: `${widgetNum}px` }">
          <post-card />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PostCard from '@/components/molecules/PostCard.vue';
import { ref, nextTick, onMounted } from 'vue';

export default {
  components: {
    PostCard,
  },
  setup() {
    const widgetList = ref([
      80, 100, 400, 300, 500, 200, 80, 100, 400, 300, 500, 200, 100, 400, 300,
      500, 200, 100, 400, 300, 500, 200, 100, 400, 300, 500, 200, 100, 400, 300,
      500, 200,
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

<style scoped></style>
