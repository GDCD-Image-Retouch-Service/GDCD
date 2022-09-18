<template>
  <div class="main-upload d-flex align-items-center justify-content-center">
    <div
      class="main-upload-form outer d-flex flex-column justify-content-center"
    >
      <div class="btn-toggle-mode">토글버튼</div>
      <div class="box-upload-image">
        <video autoplay="true" id="videoElement"></video>
      </div>
      <div class="btn-send-image">점수버튼</div>
    </div>
  </div>
</template>

<script setup>
// var video = document.querySelector('#videoElement');

const start = async () => {
  console.log('테스트중');
  // const stream = await navigator.mediaDevices.getUserMedia({ video: true });
  // videoRef.current.srcObject = stream;
};

start();

// const useEffect(() => {
//   const getUserMedia = async () => {
//     try {
//       const stream = await navigator.mediaDevices.getUserMedia({ video: true });
//       videoRef.current.srcObject = stream;
//     } catch (err) {
//       console.log(err);
//     }
//   };
//   getUserMedia();
//   }, []
// );

// if (navigator.mediaDevices.getUserMedia) {
//   navigator.mediaDevices
//     .getUserMedia({ video: true })
//     .then(function (stream) {
//       console.log(stream);
//       video.srcObject = stream;
//     })
//     .catch(function (e) {
//       console.log(e);
//     });
// }
</script>

<style>
.main-upload {
  min-height: calc(100vh - var(--size-h-header));
  width: 100vw;
}

.main-upload-form {
  border-radius: 20px;
  padding: 8px;
  width: 300px;
}

/* box */
.box-upload-image {
  margin: 0;
  width: 100%;
  height: 400px;
  border: 10px #333 solid;
}

#videoElement {
  width: 100%;
  height: 370px;
  background: #666;
}
/*  */
</style>
