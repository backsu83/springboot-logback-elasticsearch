## springboot-logback-elasticsearch

### 개발 환경
* Springboot 2.1.3
* Jdk 8.x
* Elasticsearch 6.4.3
* Swagger 2.9.2


### 목적
* Springboot에서 구현된 Rest API로 검색엔진 데이터 CRUD 기능
* Logback에서 Info Level 로그는 검색엔진으로 전송

### 설치방법
* docker-compose.yml 이 있는 경로에서 docker-compose up 명령어를 실행
* localhost:9200 에서 elasticsearch 설치 됬는지 확인 
* 어플리케이션을 실행하면 Info level이 로컬환경에 검색엔진으로 데이터가 전송됨
* 검색엔진 크롬 플러그인으로 데이터 확인이 가능 - https://chrome.google.com/webstore/search/elasticsearch-head


