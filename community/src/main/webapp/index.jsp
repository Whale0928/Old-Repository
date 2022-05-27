<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>KH Community</title>
<link rel="stylesheet" href="resources/css/main-style.css">

<!-- 아이콘 추가를 위한 링크 -->
<script src="https://kit.fontawesome.com/ea629e8085.js"
	crossorigin="anonymous"></script>



</head>
<body>
	<main class="container">
		<!-- 
			다른 jsp 파일의 내용을 해당 위치에 포함. 
			-> 경로 작성 시 
				외부 요청 주소가X(인터넷 주소) : 최상위 : /community (프로젝트 폴더)
				내부 접근 경로 O (파일경로) :  최상위 : /webapp
		-->
		<!-- 내부 접근 절대 경로 -->
		<jsp:include page="/WEB-INF/views/common/header.jsp"/>

		<section class="content">

			<section class="content-1">
				<h3>회원 정보 조회(AJAX)</h3>
				<p>이메일을 입력받아 일치하는 회원 정보 출력</p>
				이메일 : <input type="text" name="email" id="in1">
				<button id="select1">조회</button>
				
				<div id="result1" style="height:150px;"></div>

				<hr>

				<h3>회원 목록 조회</h3>
				<p>일정 시간 마다 비동기로 회원목록(회원번호,이메일,닉네임) 조회 </p>

				<table border="1">
					<thead>
						<tr>
							<th>회원 번호</th>
							<th>회원 이메일</th>
							<th>회원 닉네임</th>
						</tr>
					</thead>

					<tbody id="memberList">
						
					</tbody>
				</table>

				
			</section>





			<section class="content-2">


				<c:choose>


					<%-- 로그인이 되어 있지 않는경우 --%>
					<c:when test="${ empty sessionScope.loginMember}">
						<!-- 절대경로 : /community/member/login -->

						<!-- 상대경로(현재 페이지(index.jsp 기준) -->
						<form action="member/login" name="login-form" method="post" onsubmit="return loginValidate()">
							<!--아이디/ 비밀번호 / 로그인 버튼 영역 -->

							<fieldset id="id-pw-area">

								<section>

									<input type="text" name="inputEmail" placeholder="아이디(이메일)"	value="${cookie.saveId.value}">
									<input type="password" name="inputPw" placeholder="비밀번호">
								</section>

								<!-- button의 기본값은 submit이라 생략 가능 -->
								<section>
									<button>로그인</button>
								</section>

							</fieldset>

							<!-- 쿠키에 saveId가 있는 경우 -->
							<c:if test="${!empty cookie.saveId.value}">
								<c:set var="chk" value="checked" />
							</c:if>

							<label for="saveId"> <!-- checked 속성 : radio나 checkBox를 체크하는 속성 -->
								<input type="checkbox" id="saveId" name="saveId" ${chk}> 아이디 저장
							</label>

							<!-- 회원가입/ ID,PW 찾기 영역. -->
							<article id="signup-find-area">
                                <!-- WEB-INF 폴더는 외부로 부터 직접적으로 요청할 수 없는 폴더이다
                                    왜? 중요한 코드(자바,sql,설정관련등)가 위치하는 폴더이기 땜누에
                                        외부로 부터 접근을 차단하기 위하여
                                    
                                   ->대신 서블릿을 이용한 내부 접근(forward)는 접근 가능

                                    -->
								<!-- <a href="/community/WEB-INF/view/member/signUp.jsp">회원 가입</a> <span>|</span>  -->
								<a href="/community/member/signUp">회원 가입</a> <span>|</span> 
                                <a href="#">ID/PW찾기</a>
							</article>
						</form>
					</c:when>



					<%-- 로그인이 되어 있는 경우 --%>
					<c:otherwise>
						<article class="login-area">
							<!-- 회원 프로필 이미지 -->
							<a href=""> 
								<img src="/community/resources/images/user.png"	id="member-profile">
							</a>

							<!-- 회원 정보 + 로그아웃 버튼 -->
							<div class="my-info">
								<div>
									<a href="${contextPath}/member/myPage/info" id="nickname">${loginMember.memberNickName}</a> 
									<a href="/community/member/logout" id="logout-btn">로그아웃</a>
								</div>
								<p>${loginMember.memberEmail}</p>
							</div>
						</article>
					</c:otherwise>

				</c:choose>

			</section>
		</section>
	</main>

	<!-- footer include -->		
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
	

	<!-- jQuery 라이브러리 추가. -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	
	<!-- main . js파일을 연결 -->
	<script src="${contextPath}/resources/js/main.js"></script>
</body>

</html>