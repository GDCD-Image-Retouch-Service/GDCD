<template>
  <nav class="footer-nav">
    <div class="footer-nav-wrap">
      <!--  홈 -->
      <router-link
        to="/main"
        class="list d-flex flex-column align-items-center a-link"
      >
        <div
          class="icon-circle d-flex justify-content-center align-items-center"
        >
          <span class="material-icons-outlined"> home </span>
        </div>
      </router-link>

      <!-- 커뮤니티 -->
      <router-link to="/community" class="a-link">
        <div
          class="icon-circle d-flex justify-content-center align-items-center"
        >
          <span class="material-icons-outlined icons"> groups </span>
        </div>
      </router-link>

      <!-- 프로필 -->
      <div
        @click="
          router.push({
            name: 'ProfilePost',
            params: { userId: userStore.currentUser.item?.user?.userId },
            props: { id: 0 },
          })
        "
        class="d-flex justify-content-center align-items-center"
      >
        <div
          class="icon-circle d-flex justify-content-center align-items-center"
        >
          <span class="material-icons-outlined"> person </span>
        </div>
      </div>

      <router-link to="/photo" class="list d-flex flex-column a-link">
        <div
          class="icon-circle d-flex justify-content-center align-items-center"
        >
          <span class="material-icons-outlined"> collections </span>
        </div>
      </router-link>
    </div>
  </nav>
</template>

<script setup>
import router from '@/router/index.js';
import { useUserStore } from '@/stores/user';

const userStore = useUserStore();
</script>

<style scoped>
@media (min-width: 1024px) {
  .footer-nav-wrap {
    min-width: 975px;
    max-width: 975px;
    margin: 0 auto;
  }
}

.footer-nav {
  position: fixed;
  bottom: 0px;
  z-index: 50;
  width: 100%;
  height: var(--size-h-footer);
  min-height: var(--size-h-footer);
  display: flex;
  justify-content: center;
  background-color: var(--color-main);
}
.footer-nav-wrap {
  height: var(--size-h-footer);
  width: 100%;
  justify-content: space-around;
  display: flex;
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
.a-link {
  text-decoration: none;
  color: var();
}
.icons {
  font-size: 32px;
}
</style>
