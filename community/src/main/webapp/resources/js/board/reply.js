function selectReplyList(){

    $.ajax({
        url:contextPath+"/reply/selectReplyList",
        data:{"boardNo":boardNo},
        type:"get",
        dataType:"JSON", //json 형태의 문자열 응답 데이터를 JS객체로 자동 변환한다.
        success:function(rList){
            
        
        //이미 화면에 출력되 있는 댓글 목록 삭제
        const replyList = document.getElementById("reply-list");
        replyList.innerHTML="";

        // rList에 저장된 요소 하나씩 접근
        for(let reply of rList){
            //한 행
            const replyRow = document.createElement("li");
            replyRow.classList.add("reply-row");
            
            //작성자
            const replyWriter = document.createElement("p");
            replyWriter.classList.add("reply-writer");

            const profilImage = document.createElement("img");
            if(reply.profilImage != null){
                profilImage.setAttribute("src",contextPath+ reply.profilImage);
            }else{
                profilImage.setAttribute("src",contextPath+"/resources/images/user.png");
            }

            const memberNickname = document.createElement("span");
            memberNickname.innerText = reply.memberNickname;

            const replyDate = document.createElement("span");
            replyDate.classList.add("reply-date");
            replyDate.innerText = "("+reply.createDate+")";
            
            //작성자 영역 (p)에 프로필,닉네임,작성일,마지막 자식으로 추가

            replyWriter.append(profilImage,memberNickname,replyDate);
            

            //댓글 내용
            const replyContent = document.createElement("p")
            replyContent.classList.add("reply-content");
            replyContent.innerHTML = reply.replyContent;


            //버튼 영역
            const replyBtnArea = document.createElement("div");
            replyBtnArea.classList.add("reply-btn-area");
            //버튼생성
            const updateBtn =document.createElement("button");
            updateBtn.innerText="수정";
            const deleteBtn =document.createElement("button");
            deleteBtn.innerText="삭제";
            //버튼영역 마지막 자식으로 수정 삭제 버튼 추가
            replyBtnArea.append(updateBtn,deleteBtn); 

            replyRow.append(replyWriter,replyContent,replyBtnArea);
            replyList.append(replyRow);
        }
        },
        error:function(req,status,error){
        console.log(req.responseText); 
        }
    })
}