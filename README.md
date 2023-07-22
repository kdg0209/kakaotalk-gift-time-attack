# kakaotalk-gift-time-attack
<hr>

- gift-api: 클라이언트가 접근하는 API 모듈
- gift-core: DB와 직접적으로 커넥션을 맺는 모듈
- gift-commons: 공통적으로 사용하는 모듈
- gift-redis: redis를 사용하는 모듈

<br>

### 상황
<hr>

1. 누군가 카카오톡 오픈 채팅방에서 선착순 선물하기 게임을 시작합니다.
2. 주어진 선물은 개인당 하나씩만 받을 수 있습니다.
3. 선물이 소진되면 꽝을 반환합니다.

<br>

### 기술 스택
<hr>

- JDK: 11 Version
- Redis: 7.0.11 Version
- QueryDSL
- MySQL: 8.0.23

<br>

### 시나리오
<hr>

<img width="890" alt="스크린샷 2023-07-22 오후 3 58 19" src="https://github.com/kdg0209/kakaotalk-gift-time-attack/assets/80187200/487e9cea-c420-4aa7-9d16-c9067ba2b3af">

<hr>

#### 게임 주최자의 입장 
##### [1] - 특정 오픈 채팅방에 선물하기 게임을 시작합니다.
##### [2] - 선물의 정보와 redis에 저장할 키를 발급하고 DB에 함께 저장합니다.
##### [3] - 발급받은 key를 사용하여 redis에 선물 갯수를 저장합니다.
##### [4] - 오픈 채팅방에 선물하기 게임이 시작됩니다.

<br>

#### 게임 참여자의 입장 
##### [1] - 게임 참여자의 요청을 받은 서버는 redis에 정보를 보냅니다.
##### [2] - 게임 참여자의 요청은 대기열에 순서대로 쌓이게 됩니다.
##### [3] - 스케줄러가 1초마다 대기열에서 10개씩 데이터를 가져와 서버로 데이터를 전달합니다.
##### [4 & 5] - redis로부터 전달받은 데이터를 DB에 저장하고 선물의 갯수를 차감합니다.
<br>

### DB 스키마 
<hr>

<img width="1003" alt="스크린샷 2023-07-22 오후 4 17 18" src="https://github.com/kdg0209/kakaotalk-gift-time-attack/assets/80187200/53cee948-e8a8-42fd-b403-d755192e9e5b">


