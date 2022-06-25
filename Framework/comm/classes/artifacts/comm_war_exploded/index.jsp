<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%--
    * JSP는 컴파일시 Servlet으로 변환된다.

    * webapp 폴더 바로 하위에 존재하는 index.jsp파일은 자동으로 Welcome-file로 인식된다
        ->최상위 주소로 요청이 index.jsp 파일이 보여지게된다.

    지금까진 Servlet -> JSP로 forward (JSP 경로 작성)  
    
    이번에는 JSP에서 ->Servlet으로 forward (Servlet의 요청 주소 작성)
    
    * http://localhost:8080/comm/main 으로 forward
    * 단 , forward 이기 때문에 출력되는 주소는  http://localhost:8080/comm/ 으로 유지된다.
--%>

<jsp:forward page="main"/>