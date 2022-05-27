<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>향상된 for문 처럼 사용하기.</title>
</head>
<body>
    
    <h3>EL로 Parameter로 얻어오는 방법</h3>
    1.\%{param.name속성값}<br>
    	-> 단일 파라미터가 얻어오는 용도<br>
     	-> 복수 파라미터에 사용하는 경우 맨 앞(0번째 인덱스)의 값만 얻어온다 <br> <br>
    ${param.lang}
    
    <br> <br>
    
    2. \${ paramValues.name속성값} <br> <br>
    
    ${paramValues.lang[0]} <br>
    ${paramValues.lang[1]} <br>
    ${paramValues.lang[2]} <br>

	<hr>
	
	<h2>향상된 for문 사용</h2>
<ul>
	<c:forEach var="str" items="${paramValues.lang }" varStatus="vs">
		<li>
		${str} :[${vs.index}]   :${vs.count} : ${vs.first}  /${vs.current} <br>
		
			<c:if test="${vs.first}">
				<!-- 첫번째 반복인 경우 -->
				<span style ="color:red"> 첫번째 </span>			
			</c:if>
			<c:if test="${vs.last}">
				<!-- 마지막 반복인 경우 -->
				<span style ="color:blue"> 마지막 </span>			
			</c:if>
		</li>
	</c:forEach>
</ul>

    
    
	
    
    
</body>
</html>