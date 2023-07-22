## API 문서

<br>

#### 멤버 생성 API
<hr>

URL: http://localhost:8080/members (POST)

request
```
{
    "id": "test 11"
}
```

<br>

#### 오픈 채팅방 생성 API
<hr>

URL: http://localhost:8080/open-rooms (POST)

request
```
{
    "memberIdx": 4
}
```

<br>

#### 오픈 채팅방 참가 API
<hr>

URL: http://localhost:8080/open-rooms/join (POST)

request
```
{
    "memberIdx": 4,
    "participationCode": "2307172237D26M4VDI"
}
```

<br>

#### 오픈 채팅방 선물발송 API
<hr>

URL: http://localhost:8080/send-gift-box (POST)

request
```
{
    "memberIdx": 3,
    "giftName": "아이스 아메리카노",
    "openRoomCode": "2307172237D26M4VDI",
    "giftQuantity": 10
}
```

<br>

#### 오픈 채팅방 선물발송 API
<hr>

URL: http://localhost:8080/received-gift-box (POST)

request
```
{
    "memberId": "test 11",
    "participationCode": "2307172237D26M4VDI",
    "giftSerialCode": "2307220042E9HW8USZ:2307220042ZWRAX7YS"
}
```






















