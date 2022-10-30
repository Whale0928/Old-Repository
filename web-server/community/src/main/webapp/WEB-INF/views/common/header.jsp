<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<header>
	<section>
		<a href="${contextPath}"> 						
			<img src="${contextPath}/resources/images/logo.jpg" alt="logo"
			id="home-logo">
		</a>
	</section>

	<section>
		<article class="search-area">
			<form action="#" name="search-form">
				<fieldset>
					<input type="search" id="query" name="query" autocomplete="off"
						placeholder="검색어를 입력해주세요">
					<button type="submit" id="search-bth"
						class="fa-solid fa-magnifying-glass"></button>
				</fieldset>
			</form>
		</article>
	</section>
	<section></section>
</header>

<!-- 네비게이터 -->

<!-- 주소에 담겨져서 전달되는 파라미터
	주소?name속성=값 & name속성=값 형태로 반복된다. 
	?뒤에 서술되는 파라미터들을 나타내는 문자열.
	/member/login/?memberEmail=user1&memberPw=1234-->
<nav>
	<ul>
		<li><a href="${contextPath}/board/list?type=1">공지사항</a></li>
		<li><a href="${contextPath}/board/list?type=2">자유 게시판</a></li>
		<li><a href="${contextPath}/board/list?type=3">질문 게시판</a></li>
		<li><a href="#">FAQ</a></li>
		<li><a href="#">1:1 문의</a></li>
	</ul>
</nav>