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

<title>My Page</title>

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
		<jsp:include page="/WEB-INF/views/member/sideMenu.jsp"/>
            
        <section class="myPage-main">
                <h1 class="myPage-title">프로필</h1>
                <span class="myPage-explanation">프로필 이미지를 변경 할 수 있습니다.</span>

            <!-- 
                enctype : form 태그가 데이터를 서버로 제출할 때 
                            데이터의 인코딩 형식을 지정하는 속성.

                    1) application/x-www-form-urlencoded
                        모든 문자를 서버로 제출하기 전에 인코딩 (모든 데이터를 문자로 인코딩)
                        (form 태그 기본 값)

                    2) multpart/form-data : 제출할때 인코딩을 하지 않음 
                            -> 모든 데이터가 원본 형태를 유지한다.(파일이 파일형태로 서버로 제출);
                        **주의**  multipart/form-data로 설정시 method는 무조건 POST
             -->
            <form action="profile" method="POST" name="myPage-form"
                enctype="multipart/form-data" onsubmit="return profileValidate()">
        
                <div class="profile-image-area">
                    
                    <c:if test="${empty loginMember.profileImage}">
                        
                        <img src="${contextPath}/resources/images/user.png" id="profile-image">
                    </c:if>
                    <c:if test="${!empty loginMember.profileImage}">
                        <img src="${contextPath}${loginMember.profileImage}" id="profile-image">
                    </c:if>

                    <!-- 프로필 이미지 삭제 버튼 -->
                    <span id="delete-image">x</span>
                </div>
             
                <div class="profile-btn-area">
                    <label for="input-image">이미지 선택</label>
                    <input type="file" name="profileImage" id="input-image" accept="image/*">
                    <button type="submit">변경하기</button>
                </div>

                <div class="myPage-row">
                    <label for="">이메일</label>
                    <span>${loginMember.memberEmail}</span>
                </div>
                
                <div class="myPage-row">
                    <label for="">가입일</label>
                    <span for="">${loginMember.enrollDate}</span>
                </div>
                
                <!-- 삭제버튼이 눌린걸 기록하는 숨겨진 input 태그 -->
                <!-- 0:안눌려짐  1:눌려짐-->
                <input type="hidden" name="delete" id="delete" value="0">
            </form>
        </section>  


	    </section>
	</main>
	
	<jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    <script>
        const contextPath = "${contextPath}"; //최상위 경로를 JS전역변수로 선언.
    </script>
    <script src="${contextPath}/resources/js/member/myPage.js"></script>
    <!-- <script src="${contextPath}/resources/js/member/signUp.js"></script> -->
</body>

</html>