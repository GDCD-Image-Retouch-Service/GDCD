<template>
  <div
    class="optimize-card outer d-flex flex-column align-items-center justify-content-center"
  >
    <b-carousel controls indicators>
      <b-carousel-slide
        active
        img-src="https://picsum.photos/1024/480/?image=30"
      >
        <h1>First</h1>
        <p>Some</p>
      </b-carousel-slide>
    </b-carousel>
  </div>
</template>

<script setup></script>

<style scoped>
.optimize-card {
  border-radius: 20px;
  width: 90%;
  max-width: 380px;
  overflow: hidden;
}

.btn-set-button {
  height: 48px;
  width: 48px;
  border-radius: 100%;
  border: solid 4px var(--theme-color);
  font-size: 14pt;
  color: #000000;
  text-decoration: none;
}
</style>
