//sign Up .js

//유효성 검사 여부를 기록할 객체

const checkObj = {
    "memberEmail":false,
    "memberPw":false,
    "memberPwConfirm":false,
    "memberNickname":false,
    "memberTel":false
};



const memberTel = document.getElementById("memberTel");
const telMessage = document.getElementById("telMessage");

//  input 이벤트
//  ->입력과 관련된 모든 동작(keyup/down/press, mouse관련 , 붙여넣기.)
memberTel.addEventListener("input",function(){
    //입력이 되지 않은 경우
    if(this.value.length==0){
        telMessage.innerText="전화번호를 입력해주세요(-제외)";
        telMessage.classList.remove("confirm","error");

        
        checkObj.memberTel=false; //유효하지 않은 상태임을 기록

        return;
    }
    //전화번호 정규식
    const regExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;

    if(regExp.test(this.value)){//유효한 경우.
        telMessage.innerText="유효한 전화번호 형식입니다"
        telMessage.classList.remove("error")
        telMessage.classList.add("confirm")

        checkObj.memberTel=true;

    }else{ //유효하지 않은 경우.
        telMessage.innerText="전화번호 형식이 올바르지 않습니다."
        telMessage.classList.remove("confirm")
        telMessage.classList.add("error")

        checkObj.memberTel=false;
    }
})


//--------------------------------------------------------
//이메일 유효성 검사
const memberEmail = document.getElementById("memberEmail");
const emailMessage = document.querySelector("#emailMessage")
memberEmail.addEventListener("input",function(){
    if(this.value==""){

        emailMessage.innerText="메일을 받을 수 있는 이메일을 입력해주세요."
        emailMessage.classList.remove("error","confirm")
        checkObj.memberEmail=false;
        return;
    }

    const regExp = /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;
                //  /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/
    // + 1개 이상  (아무단어 _ - 포함 한글자 이상)

    // khg @ imi .or .kr
    // user @ naver . com
    // test11 @  ko. or .kr

    if(regExp.test(this.value)){
        //이메일 주복 검사 (ajax) 추가 예졍 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        //$.ajax( {k:v, k:v}) -- jQuery ajax 기본 형태
        $.ajax({
            url : "emailDupCheck", 
            // 필수 속성인 url 
            //현재 주소 : community/member/shgnUp
            //상대 주소 : community/member/emailDupCheck
            
            data : {"memberEmail":memberEmail.value} ,
            // data 속성 :  비동기 통신 시 서버로 전달할 값 작성 ( JS 객체 형식)
            //비동기 통신시 input에 입력된 값을 
            // memberEmail 이라는 key값(parameter)으로 전달한다.

            type : "GET",  //데이터 전달 방식.
            
            success : function(result){
                // 비동기 통신(ajax)가 오류없이 요청/응답 성공한 경우

                    //매개변수 result에는 servlet에서 출력된 result값이 담겨있다.
                if(result>=1){//중복일 때.
                    emailMessage.innerText="이미 사용중인 이메일입니다."
                    emailMessage.classList.remove("confirm")
                    emailMessage.classList.add("error")
                    checkObj.memberEmail=false;
                }else{ //중복이 아닐때.
                    emailMessage.innerText="사용 가능한 이메일입니다."
                    emailMessage.classList.remove("error")
                    emailMessage.classList.add("confirm")
                    checkObj.memberEmail=true;
                }
            },

            error:function(){
                //비동기 통신중 오류가 발생한 경우.
                console.log("에러 발생");
            }
        });


    }else{
        emailMessage.innerText="이메일 형식이 올바르지 않습니다."
        emailMessage.classList.remove("confirm")
        emailMessage.classList.add("error")
        checkObj.memberEmail=false;
    }
})



//닉네임 유효성 검사
const memberNickname = document.getElementById("memberNickname");
const nicknameMessage = document.getElementById("nicknameMessage");

memberNickname.addEventListener("input",function(){
    if(this.value==""){
        nicknameMessage.innerText="영어/숫자/한글 2~10글자 사이로 작성해주세요."
        nicknameMessage.classList.remove("error","confirm")
        checkObj.memberNickname=false;
        return;
    }
    const regExp = /^[a-zA-Z0-9가-힣]{2,10}$/;

    if(regExp.test(this.value)){
    
        // /community/member/signUp
        $.ajax({
            url:"nicknameDupCheck", //필수 속성 (어디로 비동기 요청을 보낼건지)
            data:{"memberNickname":this.value},//서버로 전달할 parameter
            type:"GET",
            success:function(res){ //비동기 통신 성공시 수행할 함수
                //매개변수 res == servlet에서 응답으로 전달한 값 (result)
                
                if(res == 0){ //닉네임 중복이 없는 경우
                    nicknameMessage.innerText="사용가능한 닉네임입니다.."
                    nicknameMessage.classList.remove("error")
                    nicknameMessage.classList.add("confirm")
                    checkObj.memberNickname=true;
            
                }else{
                    nicknameMessage.innerText="이미 사용중인 닉네임입니다.."
                    nicknameMessage.classList.remove("confirm")
                    nicknameMessage.classList.add("error")
                    checkObj.memberNickname=false;
                }
            },
            error:function(){ //에러가 발생한 경우 수행되는 함수.
                console.log("에러 발생");
            }
        })


    }else{
        nicknameMessage.innerText="닉네임 형식이 올바르지 않습니다."
        nicknameMessage.classList.remove("confirm")
        nicknameMessage.classList.add("error")
        checkObj.memberNickname=false;
    }
})


//비밀번호 유효성 검사

// 함수명 () : 함수를 호출해서 수행
// 함수명 : 실행되는게 아니라 함수의 코드 자체가 반환되어진다.
const memberPw = document.getElementById("memberPw");
const memberPwConfirm = document.getElementById("memberPwConfirm");
const PwMessage = document.getElementById("PwMessage");

memberPw.addEventListener("input",function(){

    if(this.value.length==0){
        PwMessage.innerText="영어,숫자,특수문자(!,@,#,-,_)로 구성된 6~30글자 사이로 작성해주세요."
        PwMessage.classList.remove("error","confirm")

        checkObj.memberPw=false; //유효하지 않음.
        
        return;
    }
    const regExp = /^[\w!@#_-]{6,30}$/;

    if(regExp.test(this.value)){
        
        checkObj.memberPw=true;
        
        if(memberPwConfirm.value.length==0){ 
            //비밀번호가 유효하고 확인이 작성되지 않았을때만
            PwMessage.innerText="유효한 비밀번호입니다.."
            PwMessage.classList.add("confirm")
            PwMessage.classList.remove("error")
        }else{
            //유효하지만 비밀번호확인이 작성되 잇을때.
            checkPw(); //두 입력값 일치 확인
        }
        
    }else{
        PwMessage.innerText="비밀번호 형식이 올바르지 않습니다."
        PwMessage.classList.remove("confirm")
        PwMessage.classList.add("error")
        checkObj.memberPw=false;
    }
})

//비밀번호 확인 유효성 검사
memberPwConfirm.addEventListener("input",checkPw)

function checkPw(){//비밀번호 확인 일치 검사.
    if(memberPw.value == memberPwConfirm.value){
        PwMessage.innerText="비밀번호가 일치합니다."
        PwMessage.classList.remove("error")
        PwMessage.classList.add("confirm")
        
        checkObj.memberPwConfirm=true;
    }else{
        PwMessage.innerText="비밀번호가 일치하지 않습니다."
        PwMessage.classList.remove("confirm")
        PwMessage.classList.add("error")
        
        checkObj.memberPwConfirm=false;
    }
}


//여담 표준이벤트 방식에서 (이벤트 조건 , 함수는 정의하는 곳이고 호출하는곳이 아니다. )


//회원 가입 버튼 클릭시 유효성 검사가 완료되었는지 확인하는 함수
function signUpValidate(){
    //checkObj에 있는 모든 속성을 반복 접근하여 
    //false 가 하나라도 있는 경우에는 false 반환

    let str;
    for(let k in checkObj){
        if(!checkObj[k]){ //현재 접근 중인 value가 false인 경우.
                switch(k){
                    case "memberEmail":     str="이메일이"; break;
                    case "memberPw":        str="비밀번호가"; break;    
                    case "memberPwConfirm": str="비밀번호 확인이"; break;
                    case "memberNickname":  str="닉네임이"; break;
                    case "memberTel":       str="전화번호가"; break;
                    }
            
            alert(str+" 유효하지 않습니다.");    
            document.getElementById(k).focus();

            return false;}
    }
    return true
}