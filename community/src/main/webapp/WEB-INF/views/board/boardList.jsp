<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!-- map에 저장된 값을 각각 변수로 저장 -->
<c:set var="boardName" value="${map.boardName}"/>
<c:set var="pagination" value="${map.pagination}"/>
<c:set var="boardList" value="${map.boardList}"/>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>${boardName} 게시판</title>
<link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
<link rel="stylesheet" href="${contextPath}/resources/css/boardList-style.css">
<script src="https://kit.fontawesome.com/ea629e8085.js"	crossorigin="anonymous"></script>
</head>
<body>
<main>
<jsp:include page="/WEB-INF/views/common/header.jsp"/>
<%--검색을 진행한 경우 key와 query를 쿼리스트링 형태로 저장한 변수 생성--%>
<c:if test="${!empty param.key}">
    <c:set var="sURL" value="&key=${param.key}&query=${param.query}"/>
</c:if>

<section class="board-list">
<h1 class="board-name">${boardName} 게시판</h1>

<c:if test="${!empty param.key}">
    <h3 style="margin-left:30px;">"${param.query}" 검색 결과</h3>
</c:if>


<div class="list-wrapper">
    <table class="list-table">
        <thead>
            <tr>
                <th>글번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <th>조회수</th>
            </tr>
        </thead>

        <tbody>
            <c:choose>
                
                <c:when test="${empty boardList}">
                    <!-- 게시글 목록 조회 결과가 비어있다면 -->
                    <tr>
                        <th colspan="5">게시글이 존재하지 않습니다.</th>
                    </tr>
                </c:when>

                <c:otherwise>
                    <!-- 게시글 목록 조회 결과가 비어있지 않다면 -->
                    
                    <!-- 향상된 for문 처럼 사용 -->
                    <c:forEach var="board" items="${boardList}">
                        <tr>
                            <td>${board.boardNo}</td>
                            <td>
                                <c:if test="${!empty board.thumbnail}">
                                    <img src="${contextPath}${board.thumbnail}" class="list-thumbnail">
                                </c:if>
                                <a href="detail?no=${board.boardNo}&cp=${pagination.currentPage}&type=${param.type}${sURL}">${board.boardTitle}</a></td>
                            <td>${board.memberNickname}</td>
                            <td>${board.createDate}</td>
                            <td>${board.readCount}</td>
                        </tr>
                    </c:forEach>
                </c:otherwise>

            </c:choose>
    

            
        </tbody>
    </table>
</div>

<div class="btn-area">
    <c:if test="${!empty loginMember}">
        <!-- /community / board / list / => list -->
        <button id="insertBtn" 
        onclick="location.href='write?mode=insert&type=${param.type}&cp=${param.cp}'">작성하기</button>
    </c:if>
</div>

<div class="pagination-area">

    <!-- 페이지네이션 a태그에 사용될 공통 주소 변수 선언 -->
    
    <c:set var="url" value="list?type=${param.type}&cp="/>
    <ul class="pagination">
        <li><a href="${url}1${sURL}">&lt;&lt;</a></li>
        <li><a href="${url}${pagination.prevPage}${sURL}">&lt;</a></li>

        <!-- 범위가 정해진 일반 for문 -->
        <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
            <c:choose>
                <c:when test="${i == pagination.currentPage}">
                    <li><a class="current">${i}</a></li>
                </c:when>

                <c:otherwise>
                    <li><a href="${url}${i}${sURL}">${i}</a></li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

                        <!-- 다음목록 시작 번호 -->
        <li><a href="${url}${pagination.nextPage}${sURL}">&gt;</a></li>
        <li><a href="${url}${pagination.maxPage}${sURL}">&gt;&gt;</a></li>
    </ul>
</div>

    <!-- http://localhost:8080/community/board/list?type=2&cp=3 -->
        <form action="list" method="get" id="boardSearch" onsubmit="return searchValidate()">
            <input type="hidden" name="type" value="${param.type}">
            <select name="key" id="search-key">
                <option value="t">제목</option>
                <option value="c">내용</option>
                <option value="tc">제목+내용</option>
                <option value="w">작성자</option>
            </select>
            <input type="text" name="query" id="search-query" placeholder="검색어를 입력해주세요" value="${param.query}"">
            <button>검색</button>
        </form>
    </section>
</main>
    <div class="modal">
        <span id="modal-close" class="fa-solid fa-circle-xmark"></span>
        <img id="modal-image" src="${contextPath}/resources/images/user.png">
    </div>

<jsp:include page="/WEB-INF/views/common/footer.jsp"/>

<script src="${contextPath}/resources/js/member/myPage.js"></script>
<script src="${contextPath}/resources/js/board/board.js"></script>
</body>
</html>