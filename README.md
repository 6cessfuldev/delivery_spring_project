# 먹어요 Delivery: 배달서비스 플랫폼 웹사이트

🚴 Motive : 배달서비스 플랫폼 [요기요](https://www.yogiyo.co.kr/) 웹페이지의 기능들을 최대한 구현해보고 싶었다!🔥

🥅 Goal : 전부 다 구현하긴 어렵고 배달서비스 사업자를 위한 기능은 배제하고 배달서비스 사용자를 위한 기능 위주로 구현해보자!💪

  
## 사이트 링크 : http://yukssungmin.cafe24.com

## 주요 기능
- **음식점 검색 기능** : 태그에 따라 거리순/별점순/리뷰개수순으로 주소에서 10km이내의 음식점 검색 기능
- **음식 주문/결제 기능** : [아임포트 API](https://www.iamport.kr)를 사용하여 장바구니의 담긴 음식을 주문 및 결제 기능
- **리뷰 작성 기능** : 음식점 상세페이지에 비동기 통신을 통한 리뷰 등록, 수정, 삭제하는 기능
- **사장님 댓글 기능** : 음식점 리뷰에 사장님 댓글 등록하는 기능
- **장바구니 기능** : 음식점의 메뉴를 장바구니에 등록, 수정, 삭제하는 기능
- **로그인 기능** : 스프링 시큐리티 기반의 Form 로그인과 OAuth 로그인 기능
- **회원가입/정보수정 기능** : JavaMail API를 사용하여 회원 인증 Email을 전송하는 기능/ 회원 정보를 수정하는 기능 
- **찜 기능** : 원하는 음식점을 찜하고 목록을 조회하는 기능
- **관리자 기능** : 도메인별로 목록을 추가/조회/수정/삭제하는 기능

## 기술 스택
- Front
  - Javascript, JQuery, Bootstrap
- Back
  - Java, - version 1.8, Spring, Mybatis, Spring Security 5, Maven, Junit4, MySQL

## 메인 로직

## 개발 기간
- 2022.12.29 ~ 1.25 (4주)

## 기획 & 설계
[기능 명세서](https://speckle-energy-fe9.notion.site/300be396353f4ad79b7ebd1b5f8e643d)  
  
[API 문서](https://speckle-energy-fe9.notion.site/API-880af2429f3942b9aae1b2fd92b038a7)  
  
[DB 명세서](https://speckle-energy-fe9.notion.site/DB-599b7a08e4c941238b8833ac7c2f282a)  
  
![DB](https://github.com/6cessfuldev/delivery_spring_project/blob/main/erd.jpg?raw=true)  
  

## README 참고 자료
https://github.com/sungeun101/cafemate
