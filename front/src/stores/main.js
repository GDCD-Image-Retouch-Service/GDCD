import { ref, computed } from 'vue';
import { defineStore } from 'pinia';

export const useMainStore = defineStore('main', () => {
  // state
  const isCamMode = ref(false);
  const tempImg = ref(null);

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

  return {
    // state
    isCamMode,
    tempImg,

    // action
    isCamModeOn,
    isCamModeOff,
    isCamModeToggle,
    setTempImg,

    // getter
    getIsCamMode,
    getTempImg,
    getTempFile,
  };
});
