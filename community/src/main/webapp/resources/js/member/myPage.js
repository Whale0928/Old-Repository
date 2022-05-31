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


//회원 프로필 이미지 변경  (미리보기)
const inputImage = document.getElementById("input-image");
if(inputImage != null){
    //input type = "file" 요소는 파일이 선택 될 때 change 이벤트가 발생한다.

    inputImage.addEventListener("change",function(){

        // this : 이벤트가 발생한 요소 == <input=file 태그>
        // filse : input type ="file"만 사용 가능한 속성
        //          선택된 파일 목록(배열 형태)을 반환
       
         //파일 목록에서 첫번째 파일객체를 얻어옴

         if(this.files[0] !=undefined){//파일이 선택되었을 때.

            const reader = new FileReader();
            //자바 스크립트의 FIle Reader 
            // - 웹 애플리케이션이 비동기적으로 데이터를 읽기 위하여 사용하는 객체.

            reader.readAsDataURL(this.files[0]);
             //reader.readAsDataURL( 파일 )  
             // - 지정된 파일의 내용을 읽기 시작함.
             // - 읽어오는게 완료되면 result 속성 data:에 
             //   읽어온 파일의 위치를 나타내는 URL이 포함된다.
             //   이렇게 일어온 URL을 이용해 브라우저에 이미지를 볼 수 있다.

             //   data:url(파일경로);  

            //FileReader.onload = function(){}
            // - on + load : 다 읽어왔을 때
            // - FileReader가 파일을 다 읽어온 경우. 함수를 수행.

             reader.onload = function(e){ //고정 이벤트 모델
                // e: 이벤트 발생 객체
                // e.targer : 이벤트가 발생한 요소(객체) -> FileReader
                // e.target.result : FileReader가 읽어온 파일의 URL

                //프로필 이미지 scr 속성의 값을 FIleReader가 읽어온 파일의 URL로 변경
                const profileImage = document.getElementById("profile-image");
                profileImage.setAttribute("src",e.target.result);
                // setAttribute () 호출 시 중복되는 속성이 존재하면 덮어쓰기된다.
                document.getElementById("delete").value=0;
                //새로운 이미지가 선택되었기 때문에 1->0으로 x버튼을 안눌러진 상태로 변경
             };
         }
    })
}

/* 이미지 선택을 확인 */
function profileValidate(){

    /* 히든 타입 */
    const del = document.getElementById("delete");

    if(inputImage.value == "" && del.value==0){ //빈문자열 == 파일선택이 안된 상태
        //아무것도 안하고 / 프로필 삭제 버튼도 누르지 않은 경우.
        alert("이미지를 선택 후 변경해주세요");
        return false;
    }
    return true;
}

/* 프로필 이미지 옆 x 버튼 클릭시 */
document.getElementById("delete-image").addEventListener("click",function(){
    const del = document.getElementById("delete");

    //0: 안눌러짐
    //1: 눌러짐

    if(del.value==0){ //눌러지지 않은 경우 수행

    //1) 프로필 이미지를 기본 이미지로 변경
    document.getElementById("profile-image").setAttribute("src",contextPath+"/resources/images/user.png")
    
    //2) input type = " file " 에 저장된 value에 빈문자열을 대입
    inputImage.value="";
    
    del.value=1; //이제 눌려진걸로 인식.
    }
})