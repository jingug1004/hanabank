# Two-Online Play
## from  @hanaph
### Author: Emiya Mulzomdao
#### since 171129
##### Fighting
###### Hidden?
####### Hidden Fighting!
######## Hidden Fighting 2nd!

#### 171129 (수)
- 설치, 개발환경 세팅
- loginUserController.java까지

#### 171203 (일)
- loginUserController.java부터 끝
- pom.xml 디펜던시 추가
  - org.apache.commons.dbcp.basicdatasource 가 없다고 할 경우 
    출처: http://yangyag.tistory.com/165 [Hello Brother!]
  - [JSONObject] Maven Dependency 설정
    출처: http://jjeong.tistory.com/836 [jjeong]    
  - java: incompatible types: java.util.List<java.lang.Object> cannot be converted to java.util.List
    DaoImpl 전부 캐스트 데이터 타입 제외 (List<WhatVO>) 제거
- 웹 컨텐트 내용 전부 옮김
- properties 파일의 config_local.properties와 database_local.properties 수정함