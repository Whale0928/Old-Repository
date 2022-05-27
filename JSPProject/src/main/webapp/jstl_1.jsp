<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!--  
	jstl / core 라는 태그를 사용할 것이고
	사용시 접두사로 "c"를 붙이겟다
-->

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSTL_1</title>
    <style>
    	*{font-family: d2coding}
    </style>
</head>
<body>
    
	<h1>JSTL(Jsp Standard Tag Library)</h1>
	<pre style="font-family: d2coding">
		
		JSP에서 자주 혹은 공통적으로 사용되는 JAVA코드를 
		쉽고, 표기법을 간단히 할 수 있도록 "태그화"(Tag Library) 하여 표준(Standard)으로 제공함.
		 
		 (if , for , Scope 변수 선언 , 데이터파싱(데이터의 형변환))		
	</pre>
	
	<h3>JSTL 라이브러리 등록 방법.</h3>
	
	<ol>
		<li> https://tomcat.apache.org/download-taglibs.cgi 접속 </li>
        <li> jar files -> impl, EL, Spec 다운로드 </li>
        <li> WEB-INF/lib 폴더에 추가</li>
		
		<!-- webapp 폴더는 서버 구동 시 인터넷에 배포되는 폴더.
			 -> 인터넷 상에서 수행되어야 되는 코드,파일 등을
			 	모두 webapp폴더 내부에 저장한다.
		 -->
	</ol>
	<hr>
	<h3> JSTP을 사용을 하기 위한 선언 방법.</h3>
	<pre>
      JSTL을 사용하고자 하는 JSP가 있을 경우
      해당 JSP 최상단에 JSTL 라이브러리를 추가하는 지시자 taglib를 작성해야 한다.
      
      prefix : 접두사. 다른 태그와 구별할 수 있는 namespace
            (태그 앞에 붙는 태그명)
            
      url(Uniform Resource Locator) : 인터넷에서 특정 자원 위치 지정(주소)
      uri(Uniform Resource Identifier) : 네트워크 상에서 자원을 구별하는 식별자
                                 (자원을 구분하는 유일한 주소)
      
      -> uri에 작성하는 주소는 네트워크 상의 주소가 아닌
         다운로드 받은 라이브러리 내부 구분 주소
   </pre>
   
	<h3>1.변수 선언 (c:Set)</h3>
	<pre>
		-변수를 선언하고 값을 초기화 하는 태그(초기화는 무조건 수행)
		-c:set 태그로 선언된 변수는 EL을 이용해서 출력할 수 있다.
		
		특징 1 : 변수 타입을 지정하지 않음
		특징 2 : 변수의 범위(scope) 지정할 수 있음
			-> c:set은 내장 객체에 속성을 추가하는 태그
				(page,request,session,application)			
				
				
		-c:set 태그속성
		1) var  : 변수명
		2) value: 대입되는 값
		3) scope: 변수의 범위 (page(기본 값),request,session,application)
	</pre>
	
	<!-- <태그 /> : 태그가 시작되자마자 종료된다. == 내용이 없는 요소 -->
	<c:set var="num" value="100"/>
	<c:set var="num" value="200" scope="request" />
	
	${ num }+${requestScope.num }=${ num + requestScope.num }
	
	<hr>
	
	<h2>2.변수 삭제(c:remove)</h2>
	<pre>
		- 지정한 변수(c:set / setAttribute() 추가된 변수) 삭제
		- scope 선택 가능. 
		- scope 미작성 시 모든 범위에서 변수명이 일치하는 경우 제거
		
		-c:remove 속성
		var: 삭제할 변수명
		scope : 삭제할 범위 (기본값 : 모든 범위)
	</pre>
   
   <!-- session scope 변수 선언 -->
   <c:set var="num" value="300" scope="session"/>
   
   <ul>
	   <li>page : ${num}</li>
	   <li>page : ${requestScope.num}</li>
	   <li>page : ${sessionScope.num}</li>
   </ul>
   
   request 범위의 num 변수 삭제
   <c:remove var="num" scope="request"/>
   
   
   <ul>
	   <li>page : ${num}</li>
	   <li>page : ${requestScope.num}</li>
	   <li>page : ${sessionScope.num}</li>
   </ul>
   
   모든 범위의 num 변수 삭제
   <c:remove var="num"/>
   
   
   <ul>
	   <li>page : ${num}</li>
	   <li>page : ${requestScope.num}</li>
	   <li>page : ${sessionScope.num}</li>
   </ul>
   
   
   <hr>
   <h2>3. 조건문 -if ( c:if ) </h2>
   <pre>
   	- 조건문을 작성할 수 있는 태그.
   	
   	- if문만 가능하고 else는 불가능(else가 존재하지 않음)
   	
   	
   	c:if 속성
   	test : 조건을 작성하는 속성. 
   			단, EL로만 작성 할 수 있다
   </pre>
   
   <c:set var="temp" value="30"/>
   
   	<c:if test="${temp>20 }">
   		<!-- test에 작성된 조건이 참일때만 화면에 출력  -->
   		temp는 20보다 크다
    </c:if>
    <!-- else구문이 없어 반대되는 조건을 별도로 작성해야 한다. -->
    <c:if test="${temp<=20}">
    	temp는 20보다 작거나 같다.
    </c:if>
    
    
    <hr>
    <h2>4. 조건문 - if ~ else if ~ else ( c:choose , c:when , c:otherwise  )</h2>
    
    <pre>
    	c:choose 내부에
    	c:when (if / else if)
    	c:otherwise (else )태그를 작성하는 형태.
    </pre>
    
    <c:set var="temp2" value="9"/>
    
    <c:choose> <%-- --%>
    	<c:when test="${temp2>10}">
    		temp2가 10보다 큼
    	</c:when>

    	<c:when test="${temp2<10}">
    		temp2가 10보다 작다
    	</c:when>
    
    	<c:otherwise >
    		temp2는 10이다
    	</c:otherwise>
    </c:choose>
   
</body>
</html>