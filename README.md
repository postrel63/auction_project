# 중고 거래 경매 프로젝트

**프로젝트 소개**

중고 거래에 경매 시스템을 도입시켜 만든 중고 거래 서비스 

개발 환경
- 프로젝트 기간: 2023.8 ~ 2023.11
- java 11
- corretto -1.8
- Spring Boot(2.x)
- Maria DB & JPA


# 기술

프로그래밍 언어
<img src="https://img.shields.io/badge/java-F46D01?style=for-the-badge&logo=java&logoColor=white">

프레임워크
<img src="https://img.shields.io/badge/SpringBoot-6DA252?style=for-the-badge&logo=SpringBoot&logoColor=white">
<img src="https://img.shields.io/badge/Spring Data JPA-418813?style=for-the-badge&logo=Spring Data JPA&logoColor=white">
<img src="https://img.shields.io/badge/Spring Security-569A31?style=for-the-badge&logo=Spring Security&logoColor=white">

빌드 도구
<img src="https://img.shields.io/badge/Gradle-173B3F?style=for-the-badge&logo=Gradle&logoColor=white">

데이터베이스
<img src="https://img.shields.io/badge/MariaDB-1D2D35?style=for-the-badge&logo=MariaDB&logoColor=white">
<img src="https://img.shields.io/badge/Redis-E01F3D?style=for-the-badge&logo=Redis&logoColor=white">

기타
<img src="https://img.shields.io/badge/smtp-5BC4EE?style=for-the-badge&logo=smtp&logoColor=white">
<img src="https://img.shields.io/badge/json web token-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white">
<img src="https://img.shields.io/badge/swagger-85EA2D?style=for-the-badge&logo=swagger&logoColor=white">
![SSE](https://img.shields.io/badge/SSE-0000FF?style=for-the-badge&logoColor=white)

도구
<img src="https://img.shields.io/badge/GitHub-000000?style=for-the-badge&logo=GitHub&logoColor=white">
<img src="https://img.shields.io/badge/postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white">
<img src="https://img.shields.io/badge/slack-4A154B?style=for-the-badge&logo=slack&logoColor=white">
<img src="https://img.shields.io/badge/notion-000000?style=for-the-badge&logo=notion&logoColor=white">

# 기능

**[회원 가입]**

- 사용자 신원 인증 : 허위 정보 및 사기 방지

**[회원 프로필]**

- 구매 내역: 구매 리스트와 누적 구매 금액 등
- 판매 내역: 판매 리스트와 누적 판매 금액 등
- 평점 : 당근 마켓 온도와 비슷한 기능

**[상품 등록]**

- 상품 등록 ( 사진, 가격, 기간 등등)

**[경매]**

- 입찰: 등록된 상품에 입찰을 제시 , 입찰이 끝나기 전 입찰가를 수정할 수 있음
- 실시간 입찰 순서: 실시간으로 입찰 순서를 표시함으로써 입찰 상황을 파악
- 낙찰: 경매 기간이 종료되면 가장 높은 입찰가를 제시한 구매자에게 낙찰
- 결제 처리 및 기록: 구매자는 결제를 진행하고 판매자가 배송을 마치면 결제 대금 처리
- 알림: 더 높은 입찰가 알림, 경매 종료 및 낙찰 결과 알림 서비스
- 즉시 구매 : 판매자가 제시한 즉시 판매 가격에 즉시 구매
- 허위 입찰: 지나치게 비싼 입찰가 제시, 입찰 이후 결제를 하지 않는 등 여러 방법으로 서비스를 악용하는 사용자에게 입찰 제한
- 고의 경매 취소 패널티: 원하는 입찰가에 미치지 못해 배송을 하지 않는 경우가 반복되는  경우 상품 등록 제한

**[상품 목록]**

- 최근 등록된 상품
- 인기 상품

**[상품 검색]**

- 상품 검색


API 설계
