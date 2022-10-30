<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% pageContext.setAttribute("pageValue", "페이지 scope");    	%>
    <% pageContext.setAttribute("message", "Page scope에 저장된 메세지");    	%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>내장 객체 범위 결과</title>
</head>
<body>

		<pre style="font-family: D2Coding">
		
		page 범위 message : ${pageValue}
		
		 Request 범위의 message: ${requestScope.message}
		 
		 session 범위의 sesseionValue :${sessionValue}
		 
		 application 범위 appValue : ${appValue}

	
		** scope의 우선 순위 **		 
		
		Request > session > application
		
		${pageScope.message}
		${requestScope.message}
		${sessionScope.message}
		${applicationScope.message}
		</pre>

</body>
</html>