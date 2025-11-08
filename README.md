## [우아한테크코스 8기 프리코스] 4주차 미션 백엔드 레포

### 개발 환경 세팅

#### 1. gradle 의존성 설치
``` ./gradlew build ```

### 2. Husky & Linit - staged 설정
#### Husky hook 활성화

``` npx husky install ```

#### lint-staged 의존성 설치

``` npm install ```

> 이 프로젝트는 코드 품질을 유지하기 위해 Husky를 사용하여 Git hooks를 관리합니다. <br>
staged된 파일만 lint 검사 수행합니다

#### 서버 실행 (기본 포트: 8080)
 ``` ./gradlew bootRun  ```


### 브렌치 컨벤션 
> Husky pre-push hook 으로 브렌치 이름 검증을 수행합니다
- 형식
    - 허용타입 : `feat` , `fix`,   `docs`, `refactor`, `perf`, `test`, `chore`
    - 예시
        - feat/login
        - fix/chat

### 커밋 컨벤션

```
#이슈번호 타입: 메시지
```

### 커밋 타입

- feat → 새로운 기능 추가  
- fix → 버그 수정  
- refactor → 코드 리팩토링  
- docs → 문서 수정  
- style → 코드 스타일 수정 (포맷, 세미콜론 등)  
- test → 테스트 코드 작성/수정  
- chore → 빌드, 설정, 패키지 관리 등 잡다한 수정  
- perf → 성능 개선  
- ci → CI/CD 관련 설정  
- revert → 이전 커밋 되돌리기  


### 예시
```
feat: 채팅 기능 구현
fix: 로그인 로직 버그 수정
```

### Tech Stack
<p dir="auto"><a target="_blank" rel="noopener noreferrer nofollow" href="https://camo.githubusercontent.com/4a2a76648ac74b1e794e66a65d6c39da2d76c090fdf16b1423a4c99fd9b7956a/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4672616d65776f726b2d3535353535353f7374796c653d666f722d7468652d6261646765"><img src="https://camo.githubusercontent.com/4a2a76648ac74b1e794e66a65d6c39da2d76c090fdf16b1423a4c99fd9b7956a/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4672616d65776f726b2d3535353535353f7374796c653d666f722d7468652d6261646765" data-canonical-src="https://img.shields.io/badge/Framework-555555?style=for-the-badge" style="max-width: 100%;"></a><a target="_blank"><img alt="SpringBoot" src="https://img.shields.io/badge/springboot-%236DB33F.svg?style=for-the-badge&amp;logo=springboot&amp;logoColor=white" style="max-width: 100%;">
<a target="_blank"><img alt="Kotlin" src="https://img.shields.io/badge/kotlin-7F52FF.svg?style=for-the-badge&amp;logo=kotlin&amp;logoColor=white" style="max-width: 100%;"></a>

<img src="https://img.shields.io/badge/build-555555?style=for-the-badge" style="max-width: 100%;"></a><a target="_blank"><img alt="Gradle" src="https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&amp;logo=Gradle&amp;logoColor=white" style="max-width: 100%;"></a></p>

<p dir="auto"><a target="_blank" ><img src="https://img.shields.io/badge/Database / Cache-555555?style=for-the-badge" style="max-width: 100%;"></a><a target="_blank"><img alt="MySQL" src="https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&amp;logo=mysql&amp;logoColor=white" style="max-width: 100%;"></a><a target="_blank"><img alt="Redis" src="https://img.shields.io/badge/redis-FF4438.svg?style=for-the-badge&amp;logo=redis&amp;logoColor=white" style="max-width: 100%;"></a>

<p dir="auto"><a target="_blank" ><img src="https://img.shields.io/badge/Message_Broker-555555?style=for-the-badge" style="max-width: 100%;"></a><a target="_blank">
<a target="_blank"><img alt="Kafka" src="https://img.shields.io/badge/Kafka-231F20.svg?style=for-the-badge&amp;logo=apachekafka&amp;logoColor=white" style="max-width: 100%;"></a>
</p>
