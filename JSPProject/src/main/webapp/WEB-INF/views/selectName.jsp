<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>회원 검색(이름)</title>
<style type="text/css">
* {
	font-family: d2coding
}
</style>
</head>
<body>
	<h1>회원 정보</h1>

	<c:choose>
		<c:when test="${list.isEmpty()}">
			<h1>대상이 없습니다</h1>
		</c:when>
		<c:otherwise>
			<c:forEach var="list" items="${list}">
	    	
<fieldset style="display: inline-block;">
    <legend>${list.memberName}</legend>
    
    아이디 : ${list.memberId} <br>
    
    비밀번호 : ${list.memberPw} <br>
    
    성별 :${list.memberGender}<br>
	    
    가입일 ${list.enrollDate}
</fieldset> 
 		    </c:forEach>

		</c:otherwise>


	</c:choose>


</body>
</html>