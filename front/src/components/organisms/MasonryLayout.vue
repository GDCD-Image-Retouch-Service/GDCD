<template>
  <div class="masonry-layout d-flex flex-column align-items-center main">
    <div
      class="masonry-container"
      style="
        width: 100vw;
        padding: 10px;
        display: grid;
        column-gap: 0px;
        grid-auto-rows: 1px;
      "
    >
      <div
        v-for="(widgetNum, index) in widgetList"
        :id="`dragItem-${index}-widget-${widgetNum}`"
        :key="index"
        class="masonry-brick"
      >
        <div class="masonry-item">
          <post-card :url="widgetNum" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PostCard from '@/components/molecules/PostCard.vue';
import { ref, nextTick, onMounted, onUpdated } from 'vue';

export default {
  components: {
    PostCard,
  },
  setup() {
    const widgetList = ref([
      'https://picsum.photos/200/200',
      'https://picsum.photos/200/220',
      'https://picsum.photos/200/240',
      'https://picsum.photos/200/260',
      'https://picsum.photos/200/280',
      'https://picsum.photos/200/300',
      'https://picsum.photos/200/320',
      'https://picsum.photos/200/340',
      'https://picsum.photos/200/360',
      'https://picsum.photos/200/380',
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
        masonryContainer.style.gridTemplateColumns = `repeat(5, calc((${containerWidth}px - 20px) / 5))`;
      } else if (containerWidth > 960) {
        masonryContainer.style.gridTemplateColumns = `repeat(4, calc((${containerWidth}px - 20px) / 4))`;
      } else if (containerWidth > 560) {
        masonryContainer.style.gridTemplateColumns = `repeat(3, calc((${containerWidth}px - 20px) / 3))`;
      } else {
        masonryContainer.style.gridTemplateColumns = `repeat(2, calc((${containerWidth}px - 20px) / 2))`;
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

    onUpdated(() => {
      masonryLayoutSetting();
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
.masonry-item {
  padding-bottom: 10px;
}
</style>
