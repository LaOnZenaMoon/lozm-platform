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
	"type": "OUTER"
}

{
	"name": "베이지 싱글코트",
	"price": 90000,
	"quantity": 5,
	"type": "OUTER"
}
### PUT