<template>
  <div
    class="main-optimize d-flex flex-column align-items-center justify-content-center"
  >
    <div
      id="carouselExampleCaptions"
      class="carousel slide"
      :class="[$root.theme ? '' : 'carousel-dark']"
      data-bs-ride="false"
      style="width: 100vw; min-width: 100vw; height: 100%"
    >
      <!-- carousel bottom button -->
      <div class="carousel-indicators">
        <button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="0"
          class="active"
          aria-current="true"
          aria-label="Slide 1"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="1"
          aria-label="Slide 2"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="2"
          aria-label="Slide 3"
        ></button>
      </div>

      <!-- carousel contents -->
      <div class="carousel-inner" style="height: 100%">
        <div class="carousel-item active" style="height: 100%">
          <div
            class="carousel-item-1 itemBox outer d-flex"
            style="height: 100%"
          >
            <div
              class="d-flex flex-column align-items-center justify-content-center"
              style="height: 100%; width: 400px"
            >
              <div class="textBox">
                <h2>사진</h2>
                <div class="spacer"></div>
                <p>점수</p>
              </div>
              <img
                src="@/assets/grade/1.png"
                style="height: 100px; object-fit: cover"
                alt="logo"
              />
              <router-link
                to="/account/login"
                style="text-decoration: none; z-index: 2"
              >
                <div class="btn-login">커뮤니티 글쓰기</div>
              </router-link>
            </div>
            <div class="carousel-item-1-img flex-grow-1"></div>
          </div>
        </div>

        <div class="carousel-item" style="height: 100%">
          <div
            class="carousel-item-2 outer itemBox d-flex"
            style="height: 100%"
          >
            <div class="container-fluid" style="height: 100%; width: 100%">
              <div class="row" style="height: 100%; width: 100%">
                <div
                  class="textBox col-12 col-sm-4"
                  style="padding-top: 24vh; padding-left: 8%"
                >
                  <h2>
                    <span>사진</span>
                  </h2>
                  <div class="spacer"></div>
                  <p>optimize 점수 내역:</p>
                  <p>tag</p>
                </div>

                <div
                  class="carousel-item-2-img col-12 col-sm-8"
                  :style="{
                    'min-height': '50%',
                    'background-image':
                      'url(' + require('@/assets/grade/1.png') + ')',
                    'background-size': 'contain',
                    'background-repeat': 'no-repeat',
                    'background-position': 'center',
                  }"
                ></div>
              </div>
            </div>
          </div>
        </div>

        <div class="carousel-item" style="height: 100%">
          <div
            class="carousel-item-3 outer itemBox d-flex"
            style="height: 100%"
          >
            <div class="container-fluid" style="height: 100%; width: 100%">
              <div class="row" style="height: 100%; width: 100%">
                <div
                  class="textBox col-12 col-sm-4"
                  style="padding-top: 24vh; padding-left: 8%"
                >
                  <h2>
                    <span>사진</span>
                  </h2>
                  <div class="spacer"></div>
                  <p>
                    <span>어떤</span> 화면에서도 자연스러운 <span>화면</span>
                  </p>
                  <p>
                    <span>어떤</span> 장소라도 버튼 한 번이면 <br />공부방이
                    됩니다
                  </p>
                </div>

                <div
                  class="carousel-item-3-img col-12 col-sm-8"
                  :style="{
                    'min-height': '50%',
                    'margin-top': '10vh',
                    'margin-bottom': '10vh',
                    'background-image':
                      'url(' + require('@/assets/grade/1.png') + ')',
                    'background-size': 'contain',
                    'background-repeat': 'no-repeat',
                    'background-position': 'center',
                  }"
                ></div>
              </div>
            </div>
          </div>
        </div>
      </div>

      <!-- carousel side button -->
      <button
        class="carousel-control-prev"
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide="prev"
        style="width: 4%"
      >
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Previous</span>
      </button>
      <button
        class="carousel-control-next"
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide="next"
        style="width: 4%"
      >
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="visually-hidden">Next</span>
      </button>
    </div>
  </div>
</template>

<script setup>
// import OptimizeCard from '@/components/organisms/main/OptimizeCard.vue';
</script>

<style scoped>
.main-score {
  width: 100%;
  padding: 4px;
  overflow-y: auto;
}
</style>
