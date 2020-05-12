# TODO List
## Entity 추가
=> Image 추가.
  => 연관관계 어떻게 설정할 것인지?

## 파일 업로드 및 다운로드 API
=> 캐시 처리

## 엑셀 업로드 및 다운로드 API

## 관리 화면 개발
=> delivery 
=> orders

## API 인증 절차 정의 및 개발


## (JPQL -> Querydsl) 로 변환 처리

## API 와 Client side 모듈 분리

## fetch join 으로 JPA 수정

## B2C 페이지 개발

# Database script
CREATE USER LOZM identified by oracle ;
GRANT CONNECT, RESOURCE TO LOZM
ALTER USER SYSTEM QUOTA 100M ON SYSTEM;
GRANT UNLIMITED TABLESPACE TO SYSTEM;

# API script