//정보변경
function changeInfoValidate(){
   const memberNickname = document.getElementById("memberNickname");
   const memberTel = document.getElementById("memberTel");
    //아이디 검증식
   const regExpNickName = /^[a-zA-Z0-9가-힣]{2,10}$/; 
   //전화번호 검증식
   const regExpTel =  /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;

   //아이디 체크
   if(memberNickname.value.length=='0'){
        alert("닉네임을 입력해주세요.")
        memberNickname.focus();
        return false;
    }else if(!regExpNickName.test(memberNickname.value) ){
        alert("닉네임은 영어/숫자/한글 2~10 글자 사이로 작성해주세요..")
        memberNickname.focus();
        return false;
    }
    

    //전화번호 입력.
    if(memberTel.value.length=='0'){
        alert("전화번호를 입력하세요 (-제외).")
        memberTel.focus();
        return false;
    }else if(!regExpTel.test(memberTel.value)){
        alert("전화번호 형식이 올바르지 않습니다.")
        memberTel.focus();
        return false;
    }
    return true;
}
//비밀번호 변경 체크
function changePwValidate(){
    
    const currntPw = document.querySelector("[name=currentPw]");
    const newPw = document.querySelector("[name=newPw]");
    const newPwConfirm = document.querySelector("[name=newPwConfirm]");

    const regExpPw = /^[\w!@#_-]{6,30}$/;

    if(currntPw.value==""){
        alert("현재 비밀번호를 입력해주세요")
        currntPw.focus();
        return false;
    }

    if(newPw.value==""){
        alert("새 비밀번호를 입력해주세요")
        newPw.focus();
        return false;
    }else if(!regExpPw.test(newPw.value)){
        alert("비밀번호 형식에 맞지 않습니다")
        newPw.focus();
        return false;
    }

    if(newPwConfirm.value==""){
        alert("새 비밀번호 확인을 입력해주세요")
        newPwConfirm.focus();
        return false;
    }else if(newPw.value!=newPwConfirm.value){
        alert("새 비밀번호가 일치하지 않습니다.")
        newPwConfirm.focus();
        return false;
    }

    return true;
}
//회원탈퇴
function SecessionValidate(){
    
    const memberPw = document.querySelector("[name=memberPw]");
    const agree =  document.getElementById("agree")

    if(memberPw.value==""){
        alert("비밀번호를 입력해주세요.");
        memberPw.focus();
        return false;
    }
    
    if(!agree.checked){
        alert("약관 동의 후 탈퇴 버튼을 입력해주세요.");
        return false;    
    }

    if(confirm("정말 탈퇴 할 거? ")){
        return true;
    }else{
        alert("탈퇴 취소")
        return false;
    }
}


//매개변수로 받아 처리하고 반환의 반환하는 함수.