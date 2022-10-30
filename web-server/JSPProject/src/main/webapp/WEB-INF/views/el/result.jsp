<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <!-- Person 클래스 import -->
<%@page import="java.util.List"%>
<%@page import="edu.kh.jsp.model.vo.Person"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>EL 확인하기</title>
</head>
<body>
	<!-- 
		EL의 특징
		
		1. get이라는 단어를 사용하지 않음
		 why : 표현 언어 == 출력용언어 == 출력은 얻어와서 밖에 못함.(세팅 같은 건 불가능)
		
		2. EL은 null을 빈칸으로 출력한다.(null 과 관련된 모든것을 허용하지 않는다)
	 -->
		<pre style="font-family: D2Coding">
		 session 범위의 sesseionValue :${sessionValue}
		 
		 application 범위 appValue : ${appValue}
		</pre>
			
	<h3>request에서 parameter얻어오기</h3>
	
	<pre>
		EL에서 Parameter 얻어와서 출력하는 방법.
		$ { param.name속성값 } 
	</pre>
	
	1) JSP 표현식: 
		<%= request.getParameter("inputName") %> /
		<%= request.getParameter("inputAge") %> /
		<%= request.getParameter("inputAddress") %>
		<%= request.getParameter("inputAddress2") %>

	2) EL(표현 언어):
		${param.inputName} /
		${param.inputAge} / 
		${param.inputAddress}
		${param.inputAddress2}
	
	<hr>
	
	<h3>Request에서 Attribute 얻어오기</h3>
	
	<pre>
		Servlet 에서 추가된 속성을 표현(출력) 할려는 경우
		Request에 세팅된 Attribute의 key만 입력해도 인식한다.
		(import , getAttribute(), 다운캐스팅, 변수 저장 모두 생략)
				
		\${ 속성Key }
		
		\${ 속성Key.필드명 }
		(단, getter 작성되어 있어야지만 사용 가능)
	</pre>
	
	<%
		String menu = (String)request.getAttribute("menu");
		Person person = (Person)request.getAttribute("person");
	%>
	1) JSP 표현식 : <%= menu %>	
	<!-- Person 클래스에 getter을 이용해 얻어와 출력 -->
	<br><%=person %>
	<br><%=person.getName() %>
	<br><%=person.getAge() %>
	<br><%=person.getAddress() %>
	
	2) EL(표현 언어) : ${ menu }
	<br>	${person}
	<br>	${person.name}
	<br>	${person.age}
	<br>	${person.address}
	
	<hr>
	<h3>null 처리 방법</h3>
	<pre>
		EL에서 null을 출력해야 되는 경우 ""(빈 문자열)을 출력한다.
		
		+ nullPointerException이 발생하는 코드에서도 ""(빈 문자열)을 출력한다.
		
		+EL은 null인 경우를 확인할 때 empty를 통해 확인할 수 있다. 
	</pre>


	<%List<String> list = null;	%>
	1) JSP 표현식 : <%= list %>
	<br> <%= list==null %>
	<br> <br>
	2) EL (표현 언어) : ${list}
	<br> ${ empty list  }  <!-- EL == null == ""이지만 ? // empty: ""  -->
	
	
	<h3 style="color:red;">EL의 empty는 null과 비어있는 컬렉션을 같은것으로 취급한다.</h3>
	
	
	<%
		list = new ArrayList<String>();
		// list가 ArrayList객체를 참조 하니깐 null은 아님
		// but 참조하고 있는 Array List에 값은없음 == 비어있다.
	%>
	${empty list}
	
	<!--  
		  EL을 이용해 컬렉션 요소를 다룰 때 
		  null인지 비어있는지 확인하는 방법이 동일하기 때문에 
		  코드 작성 시 이를 잘 구분할 수 있도록 해야한다
	-->
	
</body>
</html>