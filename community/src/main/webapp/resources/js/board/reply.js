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
            if(reply.profileImage != null){
                profilImage.setAttribute("src",contextPath+ reply.profileImage);
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

            replyRow.append(replyWriter,replyContent); //순서를 맞추기 위해 아래 if수행전에 append한다.

            /* 로그인한 회원 번호와 댓글 작성자의 회원번호가 같을 때만 버튼 추가 */
            if(loginMemberNo==reply.memberNo){ 
                //버튼 영역
                const replyBtnArea = document.createElement("div");
                replyBtnArea.classList.add("reply-btn-area");
                //버튼생성
                const updateBtn =document.createElement("button");
                updateBtn.innerText="수정";
                updateBtn.setAttribute("onclick","showUpdateReply("+reply.replyNo+",this)");
                const deleteBtn =document.createElement("button");
                deleteBtn.innerText="삭제";
                deleteBtn.setAttribute("onclick","deleteReply("+reply.replyNo+")")

                //버튼영역 마지막 자식으로 수정 삭제 버튼 추가
                replyBtnArea.append(updateBtn,deleteBtn); 
                replyRow.append(replyBtnArea);
            }

        
         replyList.append(replyRow);

        }
        },
        error:function(req,status,error){
        console.log(req.responseText); 
        }
    })
}
/* -------------------------------------------------- */

//댓글 등록
const addReply = document.getElementById("addReply");
const replyContent = document.getElementById("replyContent");

addReply.addEventListener("click",function(){ //버튼이 클릭되었을 때

    //전역변수 회원번호를 이용해 로그인 여부 확인
    if(loginMemberNo == "" ){
        alert("로그인 후 이용해주세요")
        return;
    }
    
    //댓글 내용이 작성되어 있나 확인
    if(replyContent.value.trim().length==0){ //미작성
        alert("댓글을 입력해주세요")
        replyContent.value="";
        replyContent.focus();
        return;
    }

    //ajax를 통해 댓글 내용을 DB에 저장(insert)
    $.ajax({
        url:contextPath+"/reply/insert",
        data:{
            "replyContent":replyContent.value,
            "memberNo":loginMemberNo,
            "boardNo":boardNo},
        type:"post",
        success:function(result){
            if(result>0){//등록 성공
                alert("댓글이 등록되었습니다.")
                replyContent.value="";
                selectReplyList();
            }else{ //등록 실패
                alert("댓글 등록이 실패했습니다..")
            }
        },
        error:function(req,status,error){
            console.log("댓글 등록 실패");
            console.log(req.responseText);
        }
    })  
})

/* --------------------------댓글 삭제------------------------------------- */
function deleteReply(replyNo){
    if(confirm("정말로 삭제 하시겠습니까?")){
        $.ajax({
            url:contextPath+"/reply/delete",
            data:{"replyNo":replyNo},
            type:"get",
            success:function(result){
                if(result>0){
                    alert("삭제되었습니다.")
                    selectReplyList();
                }else{
                    alert("삭제 실패했습니다..")
                }
            },
            error:function(req,status,error){
                console.log("댓글 삭제 실패");
                console.log(req.responseText);
            }
        })
    }
}










/* ----------------댓글 수정 화면 전환-------------------------------- */
let beforeReplyRow; //수정전 원래 행의 상태를 저장할 변수
function showUpdateReply(replyNo,btn){
    //  댓글번호 ,이벤트 발생요소(수정버튼)

    //**  댓글수정이 한개만 열릴 수 있도록 만들기 */
    const temp = document.querySelectorAll(".update-textarea");

    if(temp.length>0){ //이미 열려있는 textarea가 여러개인 경우
        if(confirm("다른 댓글이 수정 중입니다. 현재 댓글을 수정하시겠습니까?")){ //확인 
            temp[0].parentElement.innerHTML = beforeReplyRow;
            //이전 내용에   수정 취소와 같은 효과를 내게 한다.
        }else{
            return;
        }
    }

    //1) 단계 댓글 수정을 누르면 그 행이 선택
    const replyRow = btn.parentElement.parentElement
    
    //2) 단계 행 내용을 지우기 전 원래 상태를 저장(백업)

    //전역변수를 이용 , 
    beforeReplyRow = replyRow.innerHTML;

    //취소 버튼 동작 코드
    //replyRow.innerHTML = beforeReplyRow;


    //3) 단계 댓글에 작성되어있던 내용만 얻어오기

    //br 태그 유지를 위해 innerHTML을 사용
    //console.log(replyRow.children[1].innerHTML);
    let beforeContent = replyRow.children[1].innerHTML;
    //btn 기준
    //et beforeContent = btn.parentElement.previuoseElementSibling.innerHTML;

    //4번)  댓글 행 내부 내용을 모두 삭제.
    replyRow.innerHTML="";

    
    //5번) textarea 요소 생성 + 클래스 추가 + **내용 추가**
    const textarea = document.createElement("textarea");
    textarea.classList.add("update-textarea");

    //*************************** */
    // XSS 방지처리를 해제
    beforeContent = beforeContent.replaceAll("&amp;","&");
    beforeContent = beforeContent.replaceAll("&lt;","<");
    beforeContent = beforeContent.replaceAll("&gt;",">");
    beforeContent = beforeContent.replaceAll("&quot;","\""); //이스케이프 수행 시켜줘야됨.
    
    //&lt;script&gt;&lt;/script&gt;
    
    // 개행문자 처리도 해제
    beforeContent = beforeContent.replaceAll("<br>","\n");
    
    //*************************** */
    textarea.value = beforeContent;
    
    //6번) replyRow에 생성된 textarea 추가
    replyRow.append(textarea);


    //7번) 버튼 영역 + 수정 / 취소버튼 생성
    const replyBtnArea = document.createElement("div");
    replyBtnArea.classList.add("reply-btn-area");
    //버튼생성
    const updateBtn = document.createElement("button");
    updateBtn.innerText="수정";
    updateBtn.setAttribute("onclick","updateReply("+replyNo+",this)");
   
    const cancelBtn =document.createElement("button");
    cancelBtn.innerText="취소";
    cancelBtn.setAttribute("onclick","updateCancel(this)")
    //replyRow.innerHTML = beforeReplyRow;

    //버튼영역에 버튼 추가 후 replyRow에 버튼 추가
    replyBtnArea.append(updateBtn,cancelBtn); 
    replyRow.append(replyBtnArea);
}

//-----------------------댓글 수정을 취소 ----------------------------
function updateCancel(btn){
    //매개변수 btn : 클릭된 취소 버튼
    //beforeReplyRow : 수정 전의 원래 행의 댓글을 저장한 변수.

    if(confirm("댓글 수정을 취소하시겠습니까?")){
        btn.parentElement.parentElement.innerHTML = beforeReplyRow;
    }
}

//-----------------------댓글 수정(ajax)----------------------------
function updateReply(replyNo,btn){
    //새로 작성된 댓글 내용 얻어오기
    const replyContent = btn.parentElement.previousElementSibling.value;
    $.ajax({
        url:contextPath+"/reply/update",
        data:{"replyNo":replyNo,"replyContent":replyContent},
        type:"post",
        success:function(result){
            if(result>0){//등록 성공
                alert("댓글이 수정되었습니다.")
                selectReplyList();
            }else{ //등록 실패
                alert("댓글 수정이 실패했습니다..")
            }
        },
        error:function(req,status,error){
            console.log("댓글 수정 실패");
            console.log(req.responseText);
        }
    })
}