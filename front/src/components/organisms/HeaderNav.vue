<template>
  <div class="header-spacer" />
  <div class="header-nav outer d-flex align-items-center">
    <div class="nav-logo-wrap">
      <img src="@/assets/logo.png" class="logo" alt="logo" />
      <div style="font-weight: 700">
        <span style="color: var(--theme-color)">개떡</span><span>찰떡</span>
      </div>
    </div>

    <div class="flex-grow-1"></div>

    <router-link to="/community" style="margin-right: 10px">
      <img src="@/assets/sdprofile.png" alt="" class="profile-image" />
    </router-link>
  </div>
</template>

<script setup></script>

<style scoped>
.nav-logo-wrap {
  display: flex;
  gap: 10px;
  align-items: center;
  margin-left: 10px;
}
.logo {
  height: 20px;
}
.profile-image {
  width: 25px;
  height: 25px;
  object-fit: cover;
  border-radius: 25px;
}
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
}
</style>
