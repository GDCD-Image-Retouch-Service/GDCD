<template>
  <div class="header-spacer" />
  <div class="header-nav outer d-flex align-items-center">
    <img
      src="@/assets/logo.png"
      class="align-bottom"
      style="margin-left: 8px; height: calc(var(--size-h-header) / 1.5)"
      alt="logo"
    />
    <div>개떡찰떡</div>

    <div class="flex-grow-1"></div>

    <router-link to="/community" style="margin-right: 8px">
      <b-avatar
        variant="info"
        size="2.4rem"
        src="https://placekitten.com/300/300"
      ></b-avatar>
    </router-link>
  </div>
</template>

<style scoped>
.header-spacer {
  top: 0;
  width: 100vw;
  height: var(--size-h-header);
}

.header-nav {
  z-index: 100;
  position: fixed;
  top: 0;

  width: 100vw;
  height: var(--size-h-header);
  background: white;

  line-height: var(--size-h-header);
}
</style>
