## front

### 프로젝트 참고 링크

- [Git](https://lab.ssafy.com/s07-ai-image-sub2/S07P22B301)

- [Jira](https://jira.ssafy.com/secure/RapidBoard.jspa?rapidView=13144&projectKey=S07P22B301)

- [Figma](https://www.figma.com/file/3ucdqCdz2EGjXKt7OhhH5h/%EA%B0%9C%EB%96%A1%EC%B0%B0%EB%96%A1?node-id=0%3A1)

- [Notion](https://www.notion.so/a58ba04e32814a97833e532872ca07cb)

- [Gantt & 명세서](https://docs.google.com/spreadsheets/d/1vI2nZP5mbR0at0AT3ZI8VDgFU5no_Bu5MFk8tqqZvac/edit?usp=sharing)

### API

- [Vue3](https://v3.ko.vuejs.org/guide/migration/introduction.html)
- [Pinia](https://pinia.vuejs.org/)
- [BootstrapVue](https://cdmoro.github.io/bootstrap-vue-3/)
- [BootstrapVue 3](https://cdmoro.github.io/bootstrap-vue-3/)

### Convention Detail

- Branch 명

  - front의 하위 Branch 를 생성할 경우 하이픈 이후, 지라나 Gantt 차트의 Epic 넘버( 또는, Story Task Fix 넘버 ) 를 적고 이유를 파스칼 네이밍으로 명시할 것
  - 예시) 지라 이슈 8번 UX/UX 에픽에 라우터 세팅에 관하여 충돌을 우려하여 브랜치를 생성하였다면 밑과 같은 이름으로 짓는다.
  - `front-23-IssueFix`

- Commit
  - `[이슈번호] 행동. 유저스토리: 설명`
  - ex) `[S07P12B102-0] Feat. 회원가입: 회원가입 from 생성`
  - 행동의 앞 글자는 대문자로 한다.
