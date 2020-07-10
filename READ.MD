# TODO List
## NBAMANIA 같은 관리자 - B2C 사이트 만들기.
## B2C 페이지 개발
=> B2C 페이지에서 CRUD 가능하도록 개발.
=> B2C 페이지는 Vue.js 또는 React.js 로 만들어보기.

## 채팅 모듈 만들기
=> 우선, Spring Boot Web Socket + STOMP 포로토콜 활용하여 개발. 이후 RabbitMQ 이용하여 활성화.
=> RabbitMQ + STOMP 프로토콜 활용하여, 서버 개발

## Entity 추가
=> Image 추가.
  => 연관관계 어떻게 설정할 것인지? 이미지가 필요한 엔티티 마다 엮어주는 방법?

## Image & File 캐시 처리
=> 캐시 처리

## (JPQL -> Querydsl) 로 변환 처리

## fetch join 으로 JPA 수정

## API 날짜 및 번호 리턴 포맷 맞춤 처리

## API 통신구간 암호화

## Kibana + elastic search + Spring boot + Sleuth 개발 관련.
=> “114_cloud_infra” + “114_basic_project” 프로젝트 참조.
=> 현재 마켓컬리에서 사용중.


# Database script
CREATE USER LOZM identified by oracle ;
GRANT CONNECT, RESOURCE TO LOZM
ALTER USER SYSTEM QUOTA 100M ON SYSTEM;
GRANT UNLIMITED TABLESPACE TO SYSTEM;

# API script
sudo docker run -d --name rabbitmq -p 5672:5672 -p 8085:15672 --restart=unless-stopped -e RABBITMQ_DEFAULT_USER=lozm -e RABBITMQ_DEFAULT_PASS=lozm rabbitmq:management

# Remember list
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
  => orders > 수정
    => modal 에서 status 만 수정가능하도록 우선 처리. 기타 수정 기능은 복잡하여 차후에 구현하도록 계획.
  => orders > 삭제
  
=> delivery
  => 현재, 엔티티 관계가 (delivery 1 : orders 1) 로 설정되어있는데, 이것이 맞는 것인지?
  
## coupon 관계 정의
=> 특정 user 가 소유하고 있는 coupon 을 특정 item 에만 사용가능하도록