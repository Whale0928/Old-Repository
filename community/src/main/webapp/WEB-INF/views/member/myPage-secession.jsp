<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<%-- 문자열 관련 함수 (메서드 ) 제공 JSTL (el 형식으로 작성) --%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>회원탈퇴</title>

<link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
<link rel="stylesheet" href="${contextPath}/resources/css/myPage-style.css">


</head>
<body>
	<main class="container">
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <!-- 마이페지이지 - 내정보-->
    <section class="myPage-content">

		<!-- side menu include하는 과정 -->
		<!-- jsp 액션 태그  -->
		<jsp:include page="/WEB-INF/views/member/sideMenu.jsp"></jsp:include>
            
                  <!-- 오른쪽 myPage 주요 내용 부분 -->
		<section class="myPage-main">
			<h1 class="myPage-title">탈퇴</h1>
			<span class="myPage-explanation">현재 비밀번호가 일치하는 경우 탈퇴할 수 있습니다..</span>

			<form action="secession" method="POST" name="myPage-form" onsubmit="return SecessionValidate()">

				<div class="myPage-row">
				<label for="">현재 비밀번호</label>
				<input type="password" name="memberPw" maxlength="30">
				</div>
				
				<div class="myPage-row info-title">
					<label for="">회원 탈퇴 약관</label>
				</div>
				<pre id="secession-terms">
제1조
이 약관은 샘플 약관입니다.

① 약관 내용 1

② 약관 내용 2

③ 약관 내용 3

④ 약관 내용 4


제2조
이 약관은 샘플 약관입니다.

① 약관 내용 1

② 약관 내용 2

③ 약관 내용 3

④ 약관 내용 4
				</pre>

				<div>
					<input type="checkbox" name="agree" id="agree">
					<label for="agree">위 약관에 동의합니다.</label>
				</div>

				<button id="info-update-btn">회원 탈퇴</button>
			</form>
		</section>
	</section>
	</main>
	
		<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

		<script src="${contextPath}/resources/js/member/myPage-secession.js"></script>
		<script src="${contextPath}/resources/js/member/myPage.js"></script>
</body>

</html>