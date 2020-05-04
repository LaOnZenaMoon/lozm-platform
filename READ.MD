# TODO List
## Entity 추가
=> Image 추가.
  => 연관관계 어떻게 설정할 것인지?

## 파일 업로드 및 다운로드 API
=> 캐시 처리.

## 관리 화면 만들기
=> delivery. 
=> orders. 

## API 인증 절차 정의 및 개발

## B2C 페이지 만들기


# Database script
CREATE USER LOZM identified by oracle ;
GRANT CONNECT, RESOURCE TO LOZM
ALTER USER SYSTEM QUOTA 100M ON SYSTEM;
GRANT UNLIMITED TABLESPACE TO SYSTEM;

# API script
## 1. User
### POST
{
	"name": "이준",
	"identifier": "junlee",
	"password": "asdfasdf1234",
	"type": "USER"
}

{
	"name": "이동산",
	"identifier": "dongsanlee",
	"password": "asdfasdf1234",
	"type": "USER"
}

### PUT

## 2. ITEM
### POST
{
	"name": "검정 더블코트",
	"price": 140000,
	"quantity": 10,
	"type": "OUTER",
    "contents": "겨울코트",
    "size": "M"
}

{
	"name": "베이지 싱글코트",
	"price": 90000,
	"quantity": 5,
	"type": "OUTER",
	"contents": "겨울코트",
	"size": "L"
}
### PUT

## 3. Delivery
### POST
{
	"country": "South Korea",
	"zipCode": "144250",
	"city": "Seoul",
	"street": "Jangmunro 45 gil",
	"etc": "303",
	"status": ""
}

{
	"country": "USA",
	"zipCode": "552982",
	"city": "LA",
	"street": "South Hamton",
	"etc": "",
	"status": ""
}

### PUT

## 4. Coupon
### POST
### PUT