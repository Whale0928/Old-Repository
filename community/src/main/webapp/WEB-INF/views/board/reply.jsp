<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 


<div id="reply-area">
    <!-- 댓글 목록 -->
    <div class="reply-list-area">
        <ul id="reply-list">
            <li class="reply-row">
                    <p class="reply-writer">
                        <img src="${contextPath}/resources/images/user.png">
                        <span>댓글 작성자 닉네임</span>
                        <span class="reply-date">(2022.05.27 11:07:20)</span>
                    </p>
                    
                    <p class="reply-content">
                        댓글 내용입니다<br>
                        이런식으로 출력 예정입니다
                    </p>

                    <div class="reply-btn-area">
                        <button>수정</button>
                        <button>삭제</button>
                    </div>
            </li>
            <li class="reply-row">
                    <p class="reply-writer">
                        <img src="${contextPath}/resources/images/user.png">
                        <span>댓글 작성자 닉네임</span>
                        <span class="reply-date">(2022.05.27 11:07:20)</span>
                    </p>
                    
                    <p class="reply-content">
                        댓글 내용입니다<br>
                        이런식으로 출력 예정입니다
                    </p>

                    <div class="reply-btn-area">
                        <button>수정</button>
                        <button>삭제</button>
                    </div>
            </li>
            <li class="reply-row">
                    <p class="reply-writer">
                        <img src="${contextPath}/resources/images/user.png">
                        <span>댓글 작성자 닉네임</span>
                        <span class="reply-date">(2022.05.27 11:07:20)</span>
                    </p>
                    
                    <p class="reply-content">
                        댓글 내용입니다<br>
                        이런식으로 출력 예정입니다
                    </p>

                    <div class="reply-btn-area">
                        <button>수정</button>
                        <button>삭제</button>
                    </div>
            </li>
        </ul>
    </div>

    <!-- 댓글 작성 부분 -->
    <div class="reply-write-area">
        <textarea  id="replyContent" ></textarea>
        <button id="addReply">댓글<br>등록</button>
    </div>
</d>