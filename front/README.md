# 개떡찰떡

### 프로젝트 참고 링크

- [Git](https://lab.ssafy.com/s07-ai-image-sub2/S07P22B301)

- [Jira](https://jira.ssafy.com/secure/RapidBoard.jspa?rapidView=13144&projectKey=S07P22B301)

- [Figma](https://www.figma.com/file/3ucdqCdz2EGjXKt7OhhH5h/%EA%B0%9C%EB%96%A1%EC%B0%B0%EB%96%A1?node-id=0%3A1)

- [Notion](https://www.notion.so/a58ba04e32814a97833e532872ca07cb)

- [Gantt & 명세서](https://docs.google.com/spreadsheets/d/1vI2nZP5mbR0at0AT3ZI8VDgFU5no_Bu5MFk8tqqZvac/edit?usp=sharing)

&nbsp;

## front

### API

- [Vue3](https://v3.ko.vuejs.org/guide/migration/introduction.html)

- [Vue Router](https://router.vuejs.org/)

- [Pinia](https://pinia.vuejs.org/)

- [eslint](https://eslint.org/)

- [prettier](https://prettier.io/)

- [Bootstrap](https://getbootstrap.com/)

- [Bootstrap-icon](https://icons.getbootstrap.com/)

- [BootstrapVue 3](https://cdmoro.github.io/bootstrap-vue-3/)

- [sweetalert2](https://sweetalert2.github.io/)

- [Vue 3 Google Login](https://www.npmjs.com/package/vue3-google-login)

- [Vue 3 Popper](https://valgeirb.github.io/vue3-popper/guide/getting-started.html#installation)

&nbsp;

### Convention Detail

- 디렉토리 구조

  - api

    - 추가적으로 사용되거나 만들어서 사용할 API 들은 해당 폴더에서 관리한다.
    - rest api, axios create 설정 등이 그러하다

  - assets

    - 사용될 이미지나 음악 등, 데이터들은 해당 폴더에서 관리한다.

  - components

    - 기본적으로 [Atomic Design Pattern](https://uxdev.org/entry/%EC%95%84%ED%86%A0%EB%AF%B9%EB%94%94%EC%9E%90%EC%9D%B8-Atomic-Design-%EC%9B%90%EC%9E%90%EB%8B%A8%EC%9C%84%EB%94%94%EC%9E%90%EC%9D%B8-%EB%B0%A9%EB%B2%95%EB%A1%A0-%EA%B0%84%EB%8B%A8%ED%95%98%EA%B2%8C-%EC%9D%B4%ED%95%B4%ED%95%98%EA%B3%A0-%EC%9D%91%EC%9A%A9%ED%95%98%EA%B8%B0)을 따른다
    - Router 주소에 걸리는 화면(Page)만 `src/view` 에서 관리
    - 해당 화면에 띄울 화면의 구조는 `src/components/pages/` 폴더 안에서 정의한다.
    - 각 디자인 배치는 `src/components/organisms/` 폴더 안에서 정의한다.
    - 카드 같은 디자인 형상은 `src/components/molecules/` 폴더 안에서 정의한다.
    - 아이콘이나 로고는 `src/components/atoms/` 폴더 안에서 정의한다.
    - 각 폴더 내에서 atomic design 에 적합한 전체에 자주 쓰일 요소는 `/common` 폴더 내부에 정리하여 자주 쓰이는 요소와 한정적으로 쓰이는 요소를 정리한다.

  - router

    - vue-router 주소
    - index.js 최상단에 각 view 임포트 항목정리

  - stores

  - views
    - router 에 맞춘다

&nbsp;

- Branch 명

  - front의 하위 Branch 를 생성할 경우 하이픈 이후, 지라나 Gantt 차트의 Epic 넘버( 또는, Story Task Fix 넘버 ) 를 적고 이유를 파스칼 네이밍으로 명시할 것
  - 예시) 지라 이슈 `8번 UX/UX` 에픽에 `라우터 세팅`에 관하여 충돌을 우려하여 브랜치를 생성하였다면 밑과 같은 이름으로 짓는다.
  - `front-8-RouterSettingFix`

&nbsp;

- Commit

  - `[이슈번호] 행동. 유저스토리: 설명`
  - ex) `[S07P22B301-0] Feat. 회원가입: 회원가입 from 생성`
  - 행동의 앞 글자는 대문자로 한다.
