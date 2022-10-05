import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useMainStore = defineStore('main', () => {
  // state
  const isDark = ref(
    localStorage.getItem('theme') != undefined &&
      localStorage.getItem('theme') == 'dark',
  );
  const isCamMode = ref(false);
  const tempImg = ref(null);
  const tempFile = ref(null);
  const tempId = ref(0);
  const tempEScore = ref(0);
  const tempQScore = ref(0);
  const tempScore = ref(0);
  const requestId = ref(0);
  const tempOptiList = ref([]);

  // action
  const setIsDarkToggle = () => {
    isDark.value = !isDark.value;
    localStorage.setItem('theme', isDark.value ? 'dark' : 'light');
  };
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
  const setTempFile = (f) => {
    tempFile.value = f;
  };
  const setTempEScore = (eScore) => {
    tempEScore.value = eScore;
  };
  const setTempQScore = (qScore) => {
    tempQScore.value = qScore;
  };
  const setTempScore = (score) => {
    tempScore.value = score;
  };
  const setScore = (item) => {
    console.log('점수 계산 :');
    if (item) {
      if (item.error) {
        console.log('서버 에러');
      } else {
        // console.log('E: ' + item.dict.aesthetic);
        // console.log('Q: ' + item.dict.quality);

        let eScore = Math.ceil((5.8 - item.dict.aesthetic) * 14 + 1);
        if (eScore > 9) eScore = 9;
        else if (eScore < 1) eScore = 1;

        setTempEScore(eScore);
        console.log('ES: ', getTempEScore.value);

        let qScore = Math.ceil((6.8 - item.dict.quality) * 14 + 1);
        if (qScore > 9) qScore = 9;
        else if (qScore < 1) qScore = 1;

        setTempQScore(qScore);
        console.log('QS: ', getTempQScore.value);

        let score = Math.ceil(
          (((item.dict.aesthetic - 5.5) * 10 + (item.dict.quality - 6.5) * 10) /
            2) *
            10 +
            50,
        );
        if (score > 100) score = 100;
        else if (score < 0) score = 0;

        setTempScore(score);
        console.log('점수 반환 S: ', getTempScore.value);

        return getTempScore.value;
      }
    } else {
      console.log('서버 에러');
    }
  };
  const setTempId = (id) => {
    tempId.value = id;
  };
  const setRequestId = (id) => {
    requestId.value = id;
  };
  const resetTempOptiList = () => {
    tempOptiList.value = [];
  };
  const setTempOptiList = (newOtiList) => {
    tempOptiList.value = newOtiList;
  };
  const pushTempOptiList = (opti) => {
    tempOptiList.value.push(opti);
  };
  const deleteTempOptiList = () => {
    tempOptiList.value = [];
  };

  // getter
  const getIsDark = computed(() => isDark.value);
  const getIsCamMode = computed(() => isCamMode.value);
  const getTempImg = computed(() => tempImg.value);
  // const getTempFile = computed(() => {
  //   var arr = tempImg.value.split(','),
  //     mime = arr[0].match(/:(.*?);/)[1],
  //     bstr = atob(arr[1]),
  //     n = bstr.length,
  //     u8arr = new Uint8Array(n),
  //     type = mime.split('/')[1];

  //   while (n--) {
  //     u8arr[n] = bstr.charCodeAt(n);
  //   }

  //   return new File([u8arr], 'tempFile.' + type, { type: mime });
  // });
  const getTempFile = computed(() => tempFile.value);
  const getTempEScore = computed(() => tempEScore.value);
  const getTempQScore = computed(() => tempQScore.value);
  const getTempScore = computed(() => tempScore.value);
  const getTempId = computed(() => tempId.value);
  const getRequestId = computed(() => requestId.value);
  const getTempOptiList = computed(() => tempOptiList.value);

  return {
    // state
    isDark,
    isCamMode,
    tempImg,
    tempScore,

    // action
    setIsDarkToggle,
    isCamModeOn,
    isCamModeOff,
    isCamModeToggle,
    setTempImg,
    setTempFile,
    setTempEScore,
    setTempQScore,
    setTempScore,
    setScore,
    setTempId,
    setRequestId,
    resetTempOptiList,
    setTempOptiList,
    pushTempOptiList,
    deleteTempOptiList,

    // getter
    getIsDark,
    getIsCamMode,
    getTempImg,
    getTempFile,
    getTempEScore,
    getTempQScore,
    getTempScore,
    getTempId,
    getRequestId,
    getTempOptiList,
  };
});
