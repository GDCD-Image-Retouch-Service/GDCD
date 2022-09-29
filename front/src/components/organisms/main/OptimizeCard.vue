<template>
  <div
    class="optimize-card outer d-flex flex-column align-items-center justify-content-center"
  >
    <div
      id="carouselExampleCaptions"
      class="carousel slide"
      :class="[$root.theme ? '' : 'carousel-dark']"
      data-bs-ride="false"
      style="width: 100%"
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
      </div>

      <!-- carousel contents -->
      <div class="carousel-inner" style="height: 100%">
        <div class="carousel-item active" style="height: 100%">
          <div class="carousel-item-1 itemBox d-flex" style="height: 100%">
            <div
              class="d-flex flex-column align-items-center justify-content-center"
              style="height: 100%; width: 400px"
            >
              <div class="textBox">
                <img src="https://picsum.photos/200/300" alt="임시" />

                <h2>사진</h2>
                <div class="spacer"></div>
                <p>점수</p>
              </div>
              <img
                src="@/assets/grade/1.png"
                style="height: 100px; object-fit: cover"
                alt="logo"
              />
            </div>
            <div class="carousel-item-1-img flex-grow-1"></div>
          </div>
        </div>

        <div class="carousel-item" style="height: 100%">
          <div class="carousel-item-2 itemBox d-flex" style="height: 100%">
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
