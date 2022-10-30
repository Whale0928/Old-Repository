

//로그인시 이메일(아이디)/비밀번호 입력확인

// -> 미작성시 alert()를 이용해서 메세지를 출력하고
//    로그인 form 태그의 제출을 막는 기본 이벤트 제거 진행

function loginValidate(){ //로그인 유효성 검사 함수.
    //Validate : 유효하다.
    //inValidate : 무효하다.
    
    //이메일
    const inputEmail = document.getElementsByName("inputEmail")[0];
    //비밀번호
    const inputPw = document.getElementsByName("inputPw")[0];

    //이메일이 입력되지 않는 경우 false를 반환.
    if(inputEmail.value.trim().length == 0){
        //문자열.trim() : 문자열 양쪽 공백을 제거.
        //문자열.length : 문자열의 길이.
        alert("이메일을 입력해주세요.");
        inputEmail.value = "";  //띄어 쓰기 있을 경우 다 없애버림.
        inputEmail.focus(); //알림후 바로 이메일칸에 집중.
        return false;  //form 태그 전송 제한.
    }

    if(inputPw.value.trim().length == 0){

        alert("비밀번호를 입력해주세요.");
        inputPw.value="";
        inputPw.focus();
        return false;
    }
    return true;//form 태그 기본 이벤트 정상적으로 수행.
}


if(loginMember == ""){ //로그인이 되어있지 않을때만 이벤트 실행.
//아이디 저장 체크박스가 체크 되었을 때 이벤트 처리
document.getElementById("saveId").addEventListener("change",function(){
    //radio, checkBox에 체크시 change 이벤트 발생
    //console.log(this.checked);
    // this  : change 이벤트가 발생한 요소(체크박스
    // this.checked : 체크 여부 반환 (T / F)
    // this.checked  = true;  : 체크박스 체크
    // this.checked  = false;  : 체크박스 해제
    if(this.checked){ // t / f값만 반환됨으로 비교 필요 X
        
        const str = "개인 정보 보호를 위해 개인 PC에서의 사용을 권장합니다. 개인 PC가 아닌 경우 취소를 눌러주세요.";
        //confirm(str) // 확인(true) ,취소(false);

        if(!confirm(str)){ //취소를 눌렀을 때.
            this.checked = false; //checked 값에 체크박스 false 상태 대입.
        }
    }
})
}


//회원정보 이메일로 조회하는 비동기 통신(AJAX)
document.getElementById("select1").addEventListener("click",function(){
    const input = document.getElementById("in1");
    const div =document.getElementById("result1");

    //ajax 코드 작성
    $.ajax({
        url:"member/selectOne",
        data:{"memberEmail":input.value},
        type:"POST",

        //json data 타입 지정
        dataType:"JSON", //dataType : 응답 데이터 형식을 지정
        
        success:function(member){
            console.log(member);  //js 객체 형태 모양인 문자열
                                  // dataType: "JSON" 추가 후 -> JS객체


            //JSON.parse(문자열) : 문자열->JS 객체로 변환
            //console.log(JSON.parse(member));  
            /* console.log(JSON.parse(member).memberNickName);   */
            
            
            div.innerHTML="";

            if(member != null){ //회원정보가 있을 경우
                // 1) div에 작성된 내용 모두 삭제
                
                // 2) ul 요소 생성
                const ul = document.createElement("ul");
                
                // 3) li 요소 생성  * 5개 + 내용 추가
                const li1 = document.createElement("li");
                li1.innerText = "이메일 : "+member.memberEmail;
                
                const li2 = document.createElement("li");
                li2.innerText = "닉네임 : "+member.memberNickName;
                
                const li3 = document.createElement("li");
                li3.innerText = "전화번호 : "+member.memberTell;
                
                const li4 = document.createElement("li");
                li4.innerText = "주소 : "+member.memberAddress;
                
                const li5 = document.createElement("li");
                li5.innerText = "가입일 : "+member.enrollDate;

                // 4) ul에 li들 순서대로 추가.  
                ul.append(li1,li2,li3,li4,li5);

                // 5) div에 ul을 추가
                div.append(ul);

            }else{ //회원 정보가 존재하지 않을때 
                 
                const h4 = document.createElement("h4");
                
                h4.innerText="일치하는 회원이 없다";
                
                h4.style.color= "red";

                div.append(h4);
                
            }
        },
        error:function(request,status,error){
            console.log("ajax 에러 발생");
            console.log("상태코드 : "+request.status); //에러 번호 404,405,500
        /*     console.log(request.responseText);//에러 메세지
            console.log(error); //에러 객체 출력 */
        }
    })

    
})

//일정 시간마다 회원 목록을 조회.

function selectAll(){
    //Ajax 코드 작성
    
    $.ajax({
        url:"member/selectAll",
        dataType:"JSON", //응답 데이터 형식을 지정해 자동으로 JS 객체로 변환
        success:function(list){
            //list ==JS 객체 배열
            
            //#1 mberList 내용 전부 삭제
            const memberList = document.getElementById("memberList")
            memberList.innerHTML="";

            //#2 list를  for문으로 이용해 반복 접근
            for(let i of list){
                // i 는 회원 한명의 정보가 담긴 JS 객체
                //#3 tr 요소 생성
                const tr = document.createElement("tr");
                //#4 td생성
                const td1 = document.createElement("td");
                td1.innerText=i.memberNo;
                const td2 = document.createElement("td");
                td2.innerText=i.memberEmail;
                const td3 = document.createElement("td");
                td3.innerText=i.memberNickName;
                
                //#5 tr에 추가
                tr.append(td1,td2,td3);

                memberList.append(tr);
            }

        },
        erroe:function(){
            console.log("에러발생");
        }
    })
}
//즉시 실행 함수 
//속도 빠름 , 변수명 중복 해결등의 문제 개꿀
(function(){

    selectAll();

    setInterval(selectAll,10000); //10초 == 10000 . 1초가 1000

    //setInterval()은 지연 - > 수행 -> 지연 - > 수행의 반복이다 지연 부터 시작한다는게 중요
})();
