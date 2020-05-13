# TODO List
## Entity 추가
=> Image 추가.
  => 연관관계 어떻게 설정할 것인지?

## 파일 업로드 및 다운로드 API
=> 캐시 처리

## 엑셀 업로드 및 다운로드 API

## 관리 화면 개발
=> orders
  => orders > 신규(modal)
    => user 선택
      => n 명의 user 에게 item 을 order 할 수 있도록? 그러면 n 명의 user 에게 delivery 는 어떻게 할당? 각 user 에게 delivery 가 종속되도록?
      => 우선 n 명의 user 에게 1 개의 item 이 order 가능하도록 개발
      => ** user 1 - item 1 - coupon 1 - delivery 1 로 order 가능하도록 개발
    => store 선택 
    => ** 해당 store 에 대한 item 선택(store detail 에 있는 것처럼 탭으로 분리할 것인지?)
    => coupon 선택
    => delivery 선택  
  => orders > 수정(detail page)
  => orders > 삭제
  
=> delivery
  => 현재, 엔티티 관계가 (delivery 1 : orders 1) 로 설정되어있는데, 이것이 맞는 것인지?

## API 인증 절차 정의 및 개발

## coupon 관계 정의
=> 특정 user 가 소유하고 있는 coupon 을 특정 item 에만 사용가능하도록

## (JPQL -> Querydsl) 로 변환 처리

## API 와 Client side 모듈 분리

## fetch join 으로 JPA 수정

## B2C 페이지 개발

## API 날짜 및 번호 리턴 포맷 맞춤 처리

## 등록일. 수정일. 등록자. 수정자. 공통 처리

## API 통신구간 암호화

## user password 암호화

# Database script
CREATE USER LOZM identified by oracle ;
GRANT CONNECT, RESOURCE TO LOZM
ALTER USER SYSTEM QUOTA 100M ON SYSTEM;
GRANT UNLIMITED TABLESPACE TO SYSTEM;

# API script