<template>
  <div class="masonry-layout sub d-flex flex-column align-items-center main">
    <div
      class="masonry-container"
      style="
        width: 100%;
        padding: 20px;
        display: grid;
        column-gap: 10px;
        grid-auto-rows: 1px;
      "
    >
      <div
        v-for="(post, index) in communityStore.postsAll.item"
        :key="index"
        class="masonry-content"
      >
        <div class="masonry-item">
          <post-card :post="post" />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PostCard from '@/components/molecules/PostCard.vue';
import { ref, nextTick, onMounted, onUpdated, onBeforeMount } from 'vue';
import { useCommunityStore } from '@/stores/community';

export default {
  components: {
    PostCard,
  },
  setup() {
    const widgetList = ref();

    const restList = ref([
      'https://picsum.photos/200/300',
      'https://picsum.photos/200/200',
      'https://picsum.photos/200/400',
      'https://picsum.photos/200/310',
      'https://picsum.photos/200/210',
      'https://picsum.photos/200/410',
      'https://picsum.photos/200/320',
      'https://picsum.photos/200/220',
      'https://picsum.photos/200/420',
      'https://picsum.photos/200/330',
      'https://picsum.photos/200/230',
      'https://picsum.photos/200/430',
    ]);

    const componentList = [];

    async function init() {
      rest_test();
      nextTick(() => {
        masonryLayoutSetting();
        window.addEventListener('resize', masonryLayoutSetting);
      });
    }

    function rest_test() {
      widgetList.value = restList.value;
    }

    function masonryLayoutSetting() {
      const masonryContainer = document.querySelector('.masonry-container');
      if (!masonryContainer) {
        return;
      }

      const masonryContainerStyle = getComputedStyle(masonryContainer);

      const containerWidth = parseInt(
        masonryContainerStyle.getPropertyValue('width'),
      );

      if (containerWidth > 1440) {
        masonryContainer.style.gridTemplateColumns = `repeat(5, calc((${containerWidth}px - 60px) / 5))`;
      } else if (containerWidth > 960) {
        masonryContainer.style.gridTemplateColumns = `repeat(4, calc((${containerWidth}px - 50px) / 4))`;
      } else if (containerWidth > 560) {
        masonryContainer.style.gridTemplateColumns = `repeat(3, calc((${containerWidth}px - 40px) / 3))`;
      } else {
        masonryContainer.style.gridTemplateColumns = `repeat(2, calc((${containerWidth}px - 30px) / 2))`;
      }
      // } else if (containerWidth > 380) {
      //   masonryContainer.style.gridTemplateColumns = `repeat(2, calc((${containerWidth}px - 20px) / 2))`;
      // } else {
      //   masonryContainer.style.gridTemplateColumns = `calc(${containerWidth}px - 20px)`;
      // }

      const columnGap = parseInt(
        masonryContainerStyle.getPropertyValue('column-gap'),
      );
      const autoRows = parseInt(
        masonryContainerStyle.getPropertyValue('grid-auto-rows'),
      );

      document.querySelectorAll('.masonry-content').forEach((el) => {
        el.style.gridRowEnd = `span ${Math.ceil(
          el.querySelector('.masonry-item').scrollHeight / autoRows +
            columnGap / autoRows,
        )}`;
      });
    }

    onBeforeMount(() => {});

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
  data() {
    return {
      communityStore: useCommunityStore(),
    };
  },
};
</script>

<style scoped>
.masonry-layout {
  width: calc(100% - 20px);
}
</style>
