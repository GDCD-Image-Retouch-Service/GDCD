<template>
  <div class="main-service">
    <input type="file" accept="image/*;capture=camera" />
    디자인 공사중 입니다
    <toggle-button class="width:100%" />
    <cam-container />
    공사중 이ㅣㅂ니다
    <image-input />
    <score-receive-button />
    <how-to-use />
  </div>
</template>

<script setup>
import CamContainer from '@/components/molecules/CamContainer.vue';
import ToggleButton from '@/components/molecules/ToggleButton.vue';
import ImageInput from '@/components/organisms/ImageInput.vue';
import ScoreReceiveButton from '@/components/atoms/button/ScoreReceiveButton.vue';
import HowToUse from '@/components/molecules/HowToUse.vue';

var video = document.querySelector('#videoElement');

if (navigator.mediaDevices.getUserMedia) {
  navigator.mediaDevices
    .getUserMedia({ video: true })
    .then(function (stream) {
      video.srcObject = stream;
    })
    .catch(function (e) {
      console.log('Something went wrong!');
    });
}

function stop(e) {
  var stream = video.srcObject;
  var tracks = stream.getTracks();

  for (var i = 0; i < tracks.length; i++) {
    var track = tracks[i];
    track.stop();
  }

  video.srcObject = null;
}
</script>

<style>
.main-service {
  width: 100%;
}
</style>
