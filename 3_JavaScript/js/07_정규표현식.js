//정규 표현식 객체 생성 + 확인하기.
document.getElementById("check1").addEventListener("click",function(){
    const regExp1 = new RegExp("script");
                //script과 일치하는 문자열이 있는지 검사하는 정규 표현식
    const regExp2 = /java/;
                //java와 일치하는 문자열이 있는 지 검사하는 정규 표현식.
    
    //확인
    const str1 = "저희는 지금 javascript를 공부하고 있습니다.";
    const str2 = "servlet/JSP(java server page)도 조만간 다시 합니다.";
    const str3 = "java,java,java";


    console.log("regExp1.test(str1) : "+regExp1.test(str1));
    console.log(regExp1.exec(str1));

    console.log("regExp2.test(str2) : "+regExp2.test(str2));
    console.log(regExp2.exec(str2));
    
    
    //일치하는게 없는 경우
    console.log("regExp1.test(str2) : "+regExp1.test(str2));
    console.log(regExp1.exec(str2));
    
    
    //일치하는게 여러개 있을 경우
    console.log("regExp2.test(str3) : "+regExp2.test(str3));
    console.log(regExp2.exec(str3));
 })

//meta 문자 확인하기.
 document.getElementById("btn1").addEventListener("click",function(){
     const div1 = document.getElementById("div1");


     div1.innerHTML =""; //쓸때마다 내용 삭제.

     const regExp1 = /a/;

     //      a (일반문자열) : 문자열 내에 a라는 문자열이 존재하는지 검색.

     div1.innerHTML+="/a/,apple : "+regExp1.test("apple")+"<hr>";//true
     div1.innerHTML+="/a/,price : "+regExp1.test("price")+"<hr>";//false


     // [abcd] : 문자열 내 a,b,c,d 중 하나라도 일치하는 문자가 있는지 검색
    const regExp2 = /[abcd]/
    div1.innerHTML+="/[abcd]/ - apple : "+regExp2.test("apple")+"<hr>";//true
    div1.innerHTML+="/[abcd]/ - ssbss : "+regExp2.test("ssbss")+"<hr>";//true
    div1.innerHTML+="/[abcd]/ - qwerty : "+regExp2.test("qwerty")+"<hr>";//false

    
    // ^(캐럿) : 문자열의 시작
    const regExp3 = /^group/;
    div1.innerHTML+="/^group/ - group100 : "+regExp3.test("group100")+"<hr>";//true
    div1.innerHTML+="/^group/ - 100group : "+regExp3.test("100group")+"<hr>";//false
    
    
    //$(달러) : 문자열의 끝
    const regExp4 = /group$/; //문자열의 끝이 group으로 끈나는지 확인.
    div1.innerHTML+="/$group/ - group100 : "+regExp4.test("group100")+"<hr>";//true
    div1.innerHTML+="/$group/ - 100group : "+regExp4.test("100group")+"<hr>";//false

}) 
 


//이름 유효성 검사
document.getElementById("inputName").addEventListener("keyup",function(){
    
    //결과 출력용 span 태그
    const span = document.getElementById("inputNameResult");
    
    //한글 2~4 글자 정규 표현식(정규식)
    //[가-힝] : 한글(단일 자음, 모음제외)
    const regExp = /^[가-힣]{2,5}$/;

    if(this.value.length == 0){
        span.innerText="";
        return; //함수를 종료하고 호출한대로 돌아감.
    }

    if(regExp.test(this.value)){
        //#inputName 에 작성한 값이 유효한 경우.

        span.innerText="유효한 이름 형식입니다";
        span.style.color = "green"
        span.style.fontSize="18px"
        
    }else{
        span.innerText="유효하지 않은 이름 형식입니다";
        span.style.color = "red"
        span.style.fontSize="18px"
    }
})
 

//주민 등록 번호
document.getElementById("inputPno").addEventListener("keyup",function(){
    const span = document.getElementById("inputPnoResult");

    //주민등록번호 정규식
    //const regExp = /^\d{6}\-\d{7}$/;

    const regExp=/^\d{2}(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[01])\-[1-4]\d{6}$/;

    //월
    //01~09 0[0-9]
    //10~12 0[0-2]

    //() : 포획 괄호, 괄호 내부에 대응되는 부분을 찾아서 기억함.
    // | :버티컬바를 같이 사용한다.

    //   (  0[1-9] | 1[0-2] )  : 괄호 내 |를 기준으로 구분되며
    //    0이 먼저 입력된 경우 왼쪽의 조건(0[1-9]) 가
    //    1이 먼저 입력된 경우 오른쪽의 조건(1[0-2])가 적용된다.


    //일
    // 01 ~ 09  0[1-9] 
    // 11 ~ 19  1[1-9]  
    // 21 ~ 29  2[1-9] 
    // 30 ~ 31  3[01]





    //요소. clasList 요소가 가지고 있는 클래스를 배열형태로 반환
    //요소. classList. remove("클래스명") :  요소의 특정 클래스 제거
    //요소. classList. toggle("클래스명") : 크래스가 있으면 삭제 없으면 추가.
    

    if(this.value.length == 0){
        span.classList.remove(span.classList);
        //span.classList.remove("confirm","error");
        //span.classList.remove("confirm");

        
        return; //함수를 종료하고 호출한대로 돌아감.
    }

    if(regExp.test(this.value)){
        span.innerHTML= "유효한 주민등록번호 형식입니다."
        span.classList.remove("error");
        span.classList.add("confirm")
    }else{
        span.innerHTML= "유효하지 않은. 주민등록번호 형식입니다."
        span.classList.remove("confirm");
        span.classList.add("error")
    }
})