# DataParsing-JAVA-DATABASE

#### 아래와 같은 작업을 자바 프로그램으로 자동화

- txt, json, xml, csv 파일들에 저장된 데이터들 자바 내 객체 데이터로 파싱
- 자바와 DB 연결하기(여기서는 MariaDB사용) 
- 파싱한 데이터들을 연결한 DB에 삽입하기
- 저장된 DB를 JAVA프로그램을 통해 자유롭게 SQL 사용하기



**※ MariaDB 같은 DB, 자바 설치 및 환경설정이 되어 있다는 전제하에 설명하겠습니다.**



1. 다음과 같은 csv파일의 데이터를 JAVA의 객체 데이터로 파싱하기

   - csv파일 내용

   - 데이터를 담을 AccidentInfo 클래스 멤버변수
- AccidentInfo객체에 저장된 데이터 출력



2. 자바와 MariaDB 연결하기
   - 자바와 MariaDB 연결
3. MariaDB에 데이터베이스 생성, 테이블 생성하기

   - MariaDB에 DB 및 테이블 생성
   - "DESCRIBE accidentinfo;" 명령어 실행 결과
4. 생성한 테이블에 파싱한 데이터 삽입하기
   - AccidentInfo 객체에 저장된 데이터들 테이블에 삽입하기
5. 저장된 DB를 JAVA를 통해 SQL 사용하기
   - 데이터 삽입이후 SELECT문으로 확인하기
   - SELECT문에 WHERE문을 적용해 원하는 부분만 출력하기
