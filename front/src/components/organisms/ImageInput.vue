<template>
  <div id="image-input">
    <div id="image-input-dash">
      <div>
        <i class="bi bi-plus" style="font-size: 100pt"></i>
      </div>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
#image-input {
  width: calc(100% - var(--grid));
  padding-top: 8%;
  padding-bottom: 8%;
  margin: 0 auto;
  background-color: var(--white);
  border-radius: 20px;
}
#image-input-dash {
  width: 80%;
  padding-bottom: 80%;
  margin: auto;
  border-radius: 20px;
  border: 1px dashed var(--black);
  text-align: center;
  position: relative;
}
#image-input-dash div {
  position: absolute;
  top: calc(50% - 12px);
  left: calc(50% - 12px);
}
</style>
