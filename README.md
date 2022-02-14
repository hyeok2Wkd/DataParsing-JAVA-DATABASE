# DataParsing-JAVA-DATABASE

#### 아래와 같은 작업을 자바 프로그램으로 자동화

- txt, json, xml, csv 파일들에 저장된 데이터들 자바 내 객체 데이터로 파싱
- 자바와 DB 연결하기(여기서는 MariaDB사용) 
- 파싱한 데이터들을 연결한 DB에 삽입하기
- 저장된 DB를 JAVA프로그램을 통해 자유롭게 SQL 사용하기



**※ MariaDB 같은 DB, 자바 설치 및 환경설정이 되어 있다는 전제하에 설명하겠습니다.**



**1. 다음과 같은 csv파일의 데이터를 JAVA의 객체 데이터로 파싱하기**

   ![image](https://user-images.githubusercontent.com/99118233/153806840-38c9a17a-b962-4a00-af69-af0a36ea5802.png)
   - csv파일 내용

   ![image](https://user-images.githubusercontent.com/99118233/153806873-2192797c-9285-41b1-8b6c-eaf7e9c16fca.png)
   - 데이터를 담을 AccidentInfo 클래스 멤버변수

   ![image](https://user-images.githubusercontent.com/99118233/153806968-d3eb5530-36d0-44bb-a366-40b6d00b808a.png)
   - 데이터 파싱
   
   ![image](https://user-images.githubusercontent.com/99118233/153806925-fc845a12-1f98-4cb2-9179-b900cf845ade.png)
   - AccidentInfo객체에 저장된 데이터 출력

**2. 자바와 MariaDB 연결하기**

   ![image](https://user-images.githubusercontent.com/99118233/153807025-90a7d45a-6087-4b4c-ab7a-7fd6b30d56ee.png)
   - 자바와 MariaDB 연결
   
**3. MariaDB에 데이터베이스 생성, 테이블 생성하기**
   
   ![image](https://user-images.githubusercontent.com/99118233/153807053-9f32f9ed-7c73-4a51-8f4a-574f6faecba1.png)
   - MariaDB에 DB 및 테이블 생성
   
   ![image](https://user-images.githubusercontent.com/99118233/153807085-bd443442-83ea-46a2-abec-cd0ac5b6fba3.png)
   - "DESCRIBE accidentinfo;" 명령어 실행 결과
   
**4. 생성한 테이블에 파싱한 데이터 삽입하기**

   ![image](https://user-images.githubusercontent.com/99118233/153807127-b5d3c5fe-d1c9-404d-9e80-b967753513b7.png)
   - AccidentInfo 객체에 저장된 데이터들 테이블에 삽입하기
   
**5. 저장된 DB를 JAVA를 통해 SQL 사용 및 DB연결 종료**

   ![image](https://user-images.githubusercontent.com/99118233/153807171-ffe692c7-674b-4073-b354-d29e7abba80a.png)
   - 데이터 삽입이후 SQL문 적용
   
   ![image](https://user-images.githubusercontent.com/99118233/153807241-879997f6-e745-4ac2-bcac-b0942e13af61.png)
   - 결과
