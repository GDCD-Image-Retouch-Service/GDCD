import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useMainStore = defineStore('main', () => {
  // state
  const isCamMode = ref(false);
  const tempImg = ref(null);
  const tempEScore = ref(0);
  const tempQScore = ref(0);

  // action
  const isCamModeOn = () => {
    isCamMode.value = true;
  };
  const isCamModeOff = () => {
    isCamMode.value = false;
  };
  const isCamModeToggle = () => {
    isCamMode.value = !isCamMode.value;
  };
  const setTempImg = (img) => {
    tempImg.value = img;
  };
  const setTempEScore = (eScore) => {
    tempEScore.value = eScore;
  };
  const setTempQScore = (qScore) => {
    tempQScore.value = qScore;
  };

  // getter
  const getIsCamMode = computed(() => isCamMode.value);
  const getTempImg = computed(() => tempImg.value);
  const getTempFile = computed(() => {
    var arr = tempImg.value.split(','),
      mime = arr[0].match(/:(.*?);/)[1],
      bstr = atob(arr[1]),
      n = bstr.length,
      u8arr = new Uint8Array(n),
      type = mime.split('/')[1];

    while (n--) {
      u8arr[n] = bstr.charCodeAt(n);
    }

    return new File([u8arr], 'tempFile.' + type, { type: mime });
  });
  const getTempEScore = computed(() => tempEScore.value);
  const getTempQScore = computed(() => tempQScore.value);
  const getScore = (item) => {
    if (item) {
      if (item.error) {
        console.log('서버 에러');
      } else {
        setTempEScore(item.dict[0].aesthetic);
        setTempQScore(item.dict[0].quality);
        console.log('E: ' + tempEScore.value);
        console.log('Q: ' + tempQScore.value);
        const score = Math.ceil(
          (((item.dict[0].aesthetic - 5) * 10 +
            (item.dict[0].quality - 7) * 10) /
            2) *
            10 +
            50,
        );
        console.log('score: ' + score);
        if (score > 100) return 100;
        if (score < 0) return 0;
        return score;
      }
    }
  };

  return {
    // state
    isCamMode,
    tempImg,

    // action
    isCamModeOn,
    isCamModeOff,
    isCamModeToggle,
    setTempImg,
    setTempEScore,
    setTempQScore,

    // getter
    getIsCamMode,
    getTempImg,
    getTempFile,
    getTempEScore,
    getTempQScore,
    getScore,
  };
});
