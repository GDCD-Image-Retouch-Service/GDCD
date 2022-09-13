<template>
  <div class="header-spacer" />
  <div class="header-nav outer d-flex flex-column">
    <router-link to="/">
      <img
        src="@/assets/logo.png"
        class="align-bottom"
        style="margin-left: 16px; height: calc(var(--size-h-header) / 2)"
        alt="logo"
      />
    </router-link>
    <router-link to="/community"> 커뮤니티 </router-link>
  </div>
</template>

<style scoped>
.header-spacer {
  top: 0;
  width: 100vw;
  height: var(--size-h-header);
}

.header-nav {
  position: fixed;
  top: 0;

  width: 100vw;
  height: var(--size-h-header);
  background: white;

  line-height: var(--size-h-header);
}
</style>
