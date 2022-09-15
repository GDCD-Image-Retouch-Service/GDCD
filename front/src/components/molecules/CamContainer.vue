<template>
  <div id="cam-container">
    <video autoplay="true" id="videoElement"></video>
  </div>
</template>

<script>
export default {};
</script>

<style>
#cam-container {
  margin: 0px auto;
  width: 500px;
  height: 375px;
  border: 10px #333 solid;
}
#videoElement {
  width: 500px;
  height: 375px;
  background-color: #666;
}
</style>
