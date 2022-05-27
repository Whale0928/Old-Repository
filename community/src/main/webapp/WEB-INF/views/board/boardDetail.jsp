<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 상세보기</title>
<link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
<link rel="stylesheet" href="${contextPath}/resources/css/boardDetail-style.css">
<link rel="stylesheet" href="${contextPath}/resources/css/reply-style.css">
<script src="https://kit.fontawesome.com/ea629e8085.js"	crossorigin="anonymous"></script>
</head>

<body>
<main>
    
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    <section class="board-detail">
    <h1 class="board-title">${detail.boardTitle} <span>- ${detail.boardName}</span></h1> 
    

    <!-- 프로필 + 닉네임 + 작성일 + 조회수 -->
    <div class="board-header">
        <div class="board-writer">
            <c:if test="${empty detail.profileImage}">
                <img src="${contextPath}/resources/images/user.png">
            </c:if>
            <c:if test="${!empty detail.profileImage}">
                <img src="${contextPath}${detail.profileImage}">
            </c:if>
            <span>${detail.memberNickname}</span>
        </div>
        <div class="board-info">
            <!--작성일 + 조회수 -->
            <p><span>작성일</span> ${detail.createDate}</p>

            <c:if test="${!empty detail.updateDate}">
                <p><span>마지막 수정일</span> ${detail.updateDate}</p>
            </c:if>

            <p><span>조회수</span> ${detail.readCount} </p>
        </div>
    </div>

   <!-- 이미지들이 있는 경우 -->
 <c:if test="${!empty detail.imageList}">
    <!-- 썸네일이 있을 경우에 변수 선언 -->
    <c:if test="${detail.imageList[0].imageLevel == 0}">
        <c:set var="thumbnail" value="${detail.imageList[0]}"></c:set>
    </c:if>
  
    <!-- 섬네일 -->
    <c:if test="${!empty thumbnail}">
        <h5>썸네일</h5>
        <div class="img-box">
            <div class="boardImg thumbnail">
                <img src="${contextPath}${thumbnail.imageReName}">
                <a href="${contextPath}${thumbnail.imageReName}" download="${thumbnail.imageOriginal}">다운로드</a>
            </div>
        </div>
    </c:if>



    

    <c:if test="${empty thumbnail}">   
        <c:set var="start" value="0"/>
    </c:if>

    <c:if test="${!empty thumbnail}">
        <c:set var="start" value="1"/>
    </c:if>

    <!-- 섬네일만 있고 이미지가 없는 경우-->
    <c:if test="${fn:length(detail.imageList)>start}">
    <!-- 업로드 이미지. -->
        <h5>업로드 이미지</h5>
        <div class="img-box">            

            <c:forEach var="i" begin="${start}" end="${fn:length(detail.imageList)-1}" step="1">
                    <div class="boardImg">
                        <img src="${contextPath}${detail.imageList[i].imageReName}" onclick="location.href='${contextPath}${detail.imageList[i].imageReName}'" >
                        <a href="${contextPath}${detail.imageList[i].imageReName}" download="${detail.imageList[i].imageOriginal}">다운로드</a>
                    </div>
            </c:forEach>
        </div>

    </c:if>

</c:if>
    <!-- 내용 -->
    <div class="board-content">
      ${detail.boardContent}
    </div>

    <!-- 버튼 -->
    <div class="board-btn-area">

        <c:if test="${loginMember.memberNo==detail.memberNo}">
            <button id="updateBtn">수정</button>
            <button id="deleteBtn">삭제</button>
        </c:if> 
        <button id="goToListBtn">목록으로</button>

        <!-- onclick="history.back()   은 뒤로가기를 의미한다." -->
        <!-- onclick="history.go( -2 )   뒤로 가기 2번을 의미한다." -->
        <!-- onclick="history.go( 2 )   앞으로 가기 2번을 의미한다. 없으면 최대 앞쪽까지만?" -->
    </div>
</section>



    <jsp:include page="/WEB-INF/views/board/reply.jsp"/>

</main>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="${contextPath}/resources/js/board/board.js"></script>

<script>
    // 댓글관련  js코드에 필요한 값을 전역변수로 선언.
    //jsp 파일 : html / css / js /el / jstl 사용 가능.
    //js 파일 : js 


    // 한줄에 코드 해석 순서 :   EL == JSTL  >  HTML  > JS

    //**JS코드에서 EL/JSTL을 작성하게 된다면 반드시 ""을 추가**
    //게시글 최상위 주소 저장
    const contextPath = "${contextPath}";
    //게시글 번호 저장
    const boardNo= "${detail.boardNo}";
    //로그인한 회원의 번호
    const loginMemberNo = "${loginMember.memberNo}";
    //로그인 되어 있으면 : 번호 저장;
    //로그인 되어 있지 않으면 : ""빈문자열 ;

</script>
<script src="${contextPath}/resources/js/board/reply.js"></script>

</body>
</html>