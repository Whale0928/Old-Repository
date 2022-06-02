<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>


<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시글 작성</title>
<link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
<link rel="stylesheet" href="${contextPath}/resources/css/boardWriteForm-style.css">
<script src="https://kit.fontawesome.com/ea629e8085.js"	crossorigin="anonymous"></script>
</head>

<body>
<main>
    <jsp:include page="/WEB-INF/views/common/header.jsp"/>


        <form action="write" enctype="multipart/form-data" 
        method="post" class="board-write" onsubmit="return writeValidate()">
            <!-- 제목!!!!!!!!!!!!!!!!! -->
            <h1 class="board-title">
                <input type="text" name="boardTitle" placeholder="제목을 입력해주세요." autocomplete="off">
            </h1>
            <!-- 썸네일 등록 -->
            <h5>썸네일</h5>
            <div class="img-box">
                <div class="boardImg thumbnail">
                    <label for="img0">
                        <img src="" class="preview">
                    </label> 
                    <input type="file" class="inputImage" id="img0" name="0" accept="image/*">
                    <span class="delete-image fa-solid fa-circle-xmark"></span>
                        <!-- &times; == x모양의 문자. -->
                </div>
            </div>

            <!-- 업로드 이미지 -->
            <h5>업로드 이미지</h5>
            <div class="img-box">
                <!-- 1번 사진 -->
                <div class="boardImg">
                    <label for="img1">
                        <img src="" class="preview">
                    </label> 
                    <input type="file" class="inputImage" id="img1" name="1" accept="image/*">
                    <span class="delete-image ">&times;</span>
                </div>
                <!-- 2번 사진 -->
                <div class="boardImg">
                    <label for="img2">
                        <img src="" class="preview">
                    </label> 
                    <input type="file" class="inputImage" id="img2" name="2" accept="image/*">
                    <span class="delete-image">&times;</span>
                </div>
                <!-- 3번 사진 -->
                <div class="boardImg">
                    <label for="img3">
                        <img src="" class="preview">
                    </label> 
                    <input type="file" class="inputImage" id="img3" name="3" accept="image/*">
                    <span class="delete-image">&times;</span>
                </div>
                <!-- 4번 사진 -->
                <div class="boardImg">
                    <label for="img4">
                        <img src="" class="preview">
                    </label> 
                    <input type="file" class="inputImage" id="img4" name="4" accept="image/*">
                    <span class="delete-image">&times;</span>
                </div>
            </div>

            <div class="board-content">
                <textarea name="boardContent" id=""></textarea>
            </div>

            <div class="board-btn-area">
                <button type="submit" id="writeBtn">등록</button>
                <button type="button" id="goToListBtn">목록으로</button>
            </div>

            <!-- 숨겨진 값(hidden) -->
            <!-- 동작을 구분 (수정/새로 작성같은) -->
            <input type="hidden" name="mode" value="${param.mode}">
            <!-- 어디 게시판인지 구분. -->
            <input type="hidden" name="type" value="${param.type}">
        </form>


</main>
<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

<script src="${contextPath}/resources/js/board/board.js"></script>
<script src="${contextPath}/resources/js/board/boardWriteForm.js"></script>

</body>
</html>