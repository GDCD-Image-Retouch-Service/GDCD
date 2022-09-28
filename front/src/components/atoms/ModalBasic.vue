<!-- 
  :modal-color="색상"
  modal-color 프롭스에 따라 색상이 변합니다.
  modal-color가 primary이면 프라이머리컬러 모달이 생성됩니다.
  modal-color가 white이면 하얀색 모달이 생성됩니다. 
  modal-color가 danger이면 빨간색 모달이 생성됩니다.

  @offModal이벤트를 emit하여 모달을 off하는 함수를 연결하면, 모달 외부를 클릭하면 모달이 꺼진다. 
  ex)부모에서 @offModal="toggleLogoutModal(false)"
-->

<template>
  <teleport to="body">
    <div class="modal-container" @click.self="offModal">
      <div class="modal-basic" :class="modalStyle">
        <slot></slot>
      </div>
    </div>
  </teleport>
</template>

<script setup>
import { defineEmits } from 'vue';

const emit = defineEmits({
  offModal: Boolean,
});
const offModal = () => {
  emit('offModal');
};
</script>

<style scoped>
.modal-container {
  position: fixed;
  top: 0;
  height: 100vh;
  width: 100vw;
  background-color: rgba(0, 0, 0, 0.7);
  z-index: 20;
}

.modal-basic {
  overflow-y: scroll;
  max-height: 60vh;
}

.primary-modal {
  color: white;
  background-color: blue;
}

.white-modal {
  color: black;
  background-color: white;
}
</style>
