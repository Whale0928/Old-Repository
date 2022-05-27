<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!--  
	jstl / core 라는 태그를 사용할 것이고
	사용시 접두사로 "c"를 붙이겟다
-->

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSTL_2</title>
    
     <style>
    	*{font-family: d2coding}
    </style>
    
</head>
<body>
    <h1>c:forEach 태그 </h1>
   	<pre>
      - Java의 for문 + 추가 기능을 가지고 있는 태그
   
      - 속성
      
      var : 현재 반복 횟수에 해당하는 변수 (== int i)
      begin : 반복 시작 값
      end :   반복 종료 값
      step :  반복 시 마다 증가할 값 (증감식), 미작성 시 기본값 1
      
      items : 반복 접근할 객체 명(Collection 객체) // 향상된 for문
      
      varStatus : 현재 반복에 해당되는 상태 정보
      
      - 제공되는 값
        1) current : 현재 반복 횟수 또는 현재 접근 중인 객체
        2) index : 현재 객체가 몇 번째 인덱스인지 반환 (0부터 시작)
        3) count : 현재 반복문이 몇바퀴 반복 중인지 반환 (1부터 시작)
        4) first : 첫 번째 반복일 경우 true 반환
        5) last  : 마지막 반복일 경우 true 반환
   </pre>
   	
   	<h2>일반 for문 형식으로 사용</h2>
   	<c:forEach var="i" begin="1" end="6" step="1">
   		<h${i}>${i}번째 반복</h${i}>
   		<hr>
   	</c:forEach>
   	
 	
	<table border="1">
		<c:forEach var="i" begin="1" end="10" step="1"> 
		<tr>
			<th>${i}</th>
			<td>${i}번재 게시글입니다.</td>
		</tr>
		</c:forEach>
	</table>	
   	
   	<hr>
   	
   	
   	<h2>향샹된 for문 형식으로 사용하기</h2>
   	
   	<form action="forResult.jsp" method="get">
		<input type="checkbox" name="lang" value="java"> java <br>
		<input type="checkbox" name="lang" value="sql"> sql <br>
		<input type="checkbox" name="lang" value="jdbc"> jdbc <br>
		<input type="checkbox" name="lang" value="html"> html <br>
		<input type="checkbox" name="lang" value="css"> css <br>
		<input type="checkbox" name="lang" value="javascript"> javascript <br>
		<input type="checkbox" name="lang" value="jQuery"> jQuery <br>
		<input type="checkbox" name="lang" value="servlet"> servlet <br>
		 <input type="checkbox" name="lang" value="jsp"> jsp <br>
  		
   		<button>제출하기 </button>	
   	</form>
   	
   	
</body>
</html>