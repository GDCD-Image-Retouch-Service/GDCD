<template>
  <div>
    <nav class="footer-nav">
      <div class="footer-nav-wrap main outer d-flex justify-content-around">
        <!--  홈 -->
        <router-link
          to="/main"
          class="list d-flex flex-column align-items-center"
        >
          <div
            class="icon-circle d-flex justify-content-center align-items-center"
          >
            <span class="icon"><i class="bi bi-house-door"></i></span>
          </div>
        </router-link>

        <!-- 커뮤니티 -->
        <router-link to="/community">
          <div
            class="icon-circle d-flex justify-content-center align-items-center"
          >
            <span class="icon"><i class="bi bi-people"></i></span>
          </div>
        </router-link>

        <!-- 프로필 -->
        <div
          @click="
            router.push({
              name: 'ProfilePost',
              params: { userId: userStore.currentUser.item?.user?.userId },
            })
          "
          class="d-flex justify-content-center align-items-center"
        >
          <div
            class="icon-circle d-flex justify-content-center align-items-center"
          >
            <span class="icon"><i class="bi bi-person"></i></span>
          </div>
        </div>

        <router-link to="/photo" class="list d-flex flex-column">
          <div
            class="icon-circle d-flex justify-content-center align-items-center"
          >
            <span class="icon"><i class="bi bi-image"></i> </span>
          </div>
        </router-link>
      </div>
    </nav>
  </div>
</template>

<script setup>
import router from '@/router/index.js';
import { useUserStore } from '@/stores/user';

const userStore = useUserStore();
</script>

<style scoped>
.footer-nav {
  position: fixed;
  bottom: 0px;
  z-index: 50;
  width: 100vw;
  height: var(--size-h-footer);
  min-height: var(--size-h-footer);
  display: flex;
  justify-content: center;
}

.footer-nav-wrap {
  height: var(--size-h-footer);
  width: 100%;
  justify-content: space-around;
}
.footer-nav-wrap a {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  color: var(--color-reverse);
}

.icon-circle {
  height: calc(var(--size-h-footer) - 8px);
  width: calc(var(--size-h-footer) - 8px);
  border-radius: 50%;
  overflow: hidden;
  font-size: 16pt;
  /* border: solid 4px var(--color-theme); */
}
</style>
