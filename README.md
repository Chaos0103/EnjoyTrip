## 👩‍💻 Enjoy Trip

<table align="center">
    <tr align="center">
        <td style="min-width: 150px;">
            <a href="https://github.com/Chaos0103">
              <img src="https://avatars.githubusercontent.com/u/85945540?v=4?s=100" width="200">
              <br />
              <b>Chaos0103</b>
            </a>
        </td>
        <td style="min-width: 150px;">
            <a href="https://github.com/leeyr0412">
              <img src="https://avatars.githubusercontent.com/u/64480162?v=4?s=100" width="200">
              <br />
              <b>leeyr0412</b>
            </a> 
        </td>
    </tr>
    <tr align="center">
        <td>
            임우택
        </td>
        <td>
            이예리
        </td>
    </tr>
</table>

## ⚒️ 화면 구성

### 메인 화면
![main.png](display%2Fmain.png)

### 로그인 화면
![login.png](display%2Flogin.png)

### 회원가입 화면
![signup.png](display%2Fsignup.png)

### 관광지 조회 화면
![attraction.png](display%2Fattraction.png)

### 공지사항 목록 화면
![notion.png](display%2Fnotion.png)

### 공지사항 세부 화면
![view_notion.png](display%2Fview_notion.png)

### 페이지 준비중 화면
![ready.png](display%2Fready.png)

## ⚒️ 기능 목록

[기능 목록 명세서](docs%2FREADME.md)

## ⚒️ 데이터베이스

[SCHEMA](docs%2Fsql%2Fschema.sql)

[ERD](https://www.erdcloud.com/d/rjTmz95cMnPXerZpW)

## ✅ 커밋 컨벤션

| 제목          | 내용                                              |
|-------------|-------------------------------------------------|
| init        | 작업 세팅 커밋 (패키지 설치 등)                             |
| feat        | 새로운 기능을 추가할 경우                                  |
| style       | 기능에 영향을 주지 않는 커밋, 코드 순서, css등의 포맷에 관한 커밋        |
| fix         | 버그를 고친 경우                                       |
| refactor    | 프로덕션 코드 리팩토링                                    |
| test        | 테스트 코드 작성                                       |
| docs        | 문서를 수정한 경우, 파일 삭제, 파일명 수정 등 ex) README.md       |
| chore       | 빌드 테스트 업데이트, 패키지 매니저를 설정하는 경우, 주석 추가, 자잘한 문서 수정 |
| code review | 코드 리뷰 반영                                        |

---

## 📁 폴더 구조

```
├── java
│     ├── article
│     │     ├── Article.java
│     │     ├── controller
│     │     │     └── ArticleController.java
│     │     ├── dto
│     │     │     ├── ArticleDto.java
│     │     │     └── ArticleSearch.java
│     │     ├── repository
│     │     │     ├── ArticleJdbcRepository.java
│     │     │     └── ArticleRepository.java
│     │     └── service
│     │         ├── ArticleService.java
│     │         └── ArticleServiceImpl.java
│     ├── attraction
│     │     ├── AttractionDescription.java
│     │     ├── AttractionDetail.java
│     │     ├── AttractionInfo.java
│     │     ├── Gugun.java
│     │     ├── Sido.java
│     │     ├── controller
│     │     │     ├── AttractionController.java
│     │     │     └── api
│     │     │         └── AttractionApiController.java
│     │     ├── dto
│     │     │     ├── AttractionDto.java
│     │     │     ├── AttractionSearch.java
│     │     │     ├── GugunDto.java
│     │     │     └── SidoDto.java
│     │     ├── repository
│     │     │     ├── AttractionJdbcRepository.java
│     │     │     ├── AttractionRepository.java
│     │     │     ├── GugunJdbcRepository.java
│     │     │     ├── GugunRepository.java
│     │     │     ├── SidoJdbcRepository.java
│     │     │     └── SidoRepository.java
│     │     └── service
│     │         ├── AttractionService.java
│     │         ├── AttractionServiceImpl.java
│     │         ├── GugunService.java
│     │         ├── GugunServiceImpl.java
│     │         ├── SidoService.java
│     │         └── SidoServiceImpl.java
│     ├── common
│     │     ├── Page.java
│     │     ├── exception
│     │     │     ├── AccountException.java
│     │     │     ├── ArticleException.java
│     │     │     ├── ExceptionMessage.java
│     │     │     ├── InformationChangeException.java
│     │     │     ├── LoginException.java
│     │     │     ├── NotionException.java
│     │     │     ├── SignUpException.java
│     │     │     └── WithdrawalException.java
│     │     └── validation
│     │         ├── ArticleValidation.java
│     │         ├── MemberUpdateValidation.java
│     │         ├── NotionValidation.java
│     │         ├── SignUpValidation.java
│     │         ├── ValidationMessage.java
│     │         ├── dto
│     │         │     ├── ArticleRequest.java
│     │         │     ├── InvalidResponse.java
│     │         │     ├── MemberRequest.java
│     │         │     └── NotionRequest.java
│     │         └── validator
│     │             ├── ArticleValidator.java
│     │             ├── MemberValidator.java
│     │             ├── NotionValidator.java
│     │             ├── article
│     │             │     ├── ContentValidator.java
│     │             │     └── TitleValidator.java
│     │             ├── member
│     │             │     ├── BirthValidator.java
│     │             │     ├── EmailValidator.java
│     │             │     ├── GenderValidator.java
│     │             │     ├── LoginIdValidator.java
│     │             │     ├── LoginPwValidator.java
│     │             │     ├── NicknameValidator.java
│     │             │     ├── PhoneValidator.java
│     │             │     └── UsernameValidator.java
│     │             └── notion
│     │                 ├── ContentValidator.java
│     │                 └── TitleValidator.java
│     ├── hotplace
│     ├── member
│     │     ├── Authority.java
│     │     ├── Member.java
│     │     ├── controller
│     │     │     ├── AccountController.java
│     │     │     └── MemberController.java
│     │     ├── dto
│     │     │     ├── LoginMember.java
│     │     │     ├── MemberAddDto.java
│     │     │     └── MemberDto.java
│     │     ├── repository
│     │     │     ├── MemberJdbcRepository.java
│     │     │     └── MemberRepository.java
│     │     └── service
│     │         ├── AccountService.java
│     │         ├── AccountServiceImpl.java
│     │         ├── MemberService.java
│     │         └── MemberServiceImpl.java
│     ├── notion
│     │     ├── Notion.java
│     │     ├── controller
│     │     │     └── NotionController.java
│     │     ├── dto
│     │     │     └── NotionDto.java
│     │     ├── repository
│     │     │     ├── NotionJdbcRepository.java
│     │     │     └── NotionRepository.java
│     │     └── service
│     │         ├── NotionService.java
│     │         └── NotionServiceImpl.java
│     └── util
│         ├── ConnectionConst.java
│         └── DBConnectionUtil.java
└── webapp
    ├── WEB-INF
    ├── account
    │     └── login.jsp
    ├── assets
    │     ├── css
    │     │     ├── common.member.css
    │     │     ├── login.css
    │     │     └── mypage.css
    │     ├── img
    │     │     ├── logo.png
    │     │     ├── no-img.jpg
    │     │     ├── panel_01.png
    │     │     ├── panel_02.png
    │     │     ├── panel_03.png
    │     │     └── ready.jpeg
    │     └── js
    │         └── requestApi.js
    ├── attraction
    │     └── attractionList.jsp
    ├── common
    │     ├── footer.jsp
    │     ├── head.jsp
    │     └── header.jsp
    ├── error
    │     └── ready.jsp
    ├── index.jsp
    ├── member
    │     ├── addMember.jsp
    │     ├── mypage
    │     │     ├── myInfo.jsp
    │     │     └── mypageNav.jsp
    │     └── mypage.jsp
    └── notion
        ├── addNotion.jsp
        ├── editNotion.jsp
        ├── notionList.jsp
        └── viewNotion.jsp
```