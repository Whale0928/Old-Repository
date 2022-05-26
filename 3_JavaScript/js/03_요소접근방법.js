//ID로 접근하기. 
function accessId(){
    const div1 = document.getElementById("div1");

    //접근한 요소의 배경색 얻어오기.
    const bgColor = div1.style.background;

    /* js는 문자열 비교시에서 비교 연산자 사용한다. */
    if(bgColor == "red"){
        div1.style.background = "yellow" // 배경색 yellow로 변경
    }else{
        div1.style.background = "red" // 배경색 red로 변경
    }
    
}

/* 클래스로 접근하기 */
function accessClass(){
    //요소를 여러개 얻어오는 경우  [배열] 형태로 반환됨.
    const arr = document.getElementsByClassName("div2");

    //인덱스를 이용해서
    arr[0].style.background = "pink";
    arr[0].innerText="1번요소";
    arr[1].style.background = "tomato";
    arr[1].innerText="2번요소";
    arr[2].style.background = "green";
    arr[2].innerText="3번요소";
}

/* 태그명으로 접근하기 */
function accessTagName(){
    
    //문서내의 모든 li 아이템 태그를 접근해서 배열 반환
    const arr =document.getElementsByTagName("li");
 
    //반복문(Java랑 비슷)
    for(let i=0; i<arr.length; i++){
        const num = arr[i].innerText; //요소에 작성된 내용.

        arr[i].style.background="rgb(130,220,"+(50*num)+")";
    }
}

//input 태그에 값(value) 얻어오기/변경하기.
function inputTest(){

    //input 요소 접근하기.
    const input = document.getElementById("input-test");

    //input에 작성된 값을 얻어와 콘솔에 출력
    
    //**inner Text / HTMl 은 
    //요소의 내용 (시작태그 , 종료태그 사이에 작성된 내용)을
    //얻어오거나 , 변경할 때만 사용 가능.

    //**input은 [value] 를 이용해 값을 얻어오거나 변경할 수 있음.
    console.log(input.value);

    // input에 작성된 값 변경하기
    input.value="";//빈문자열로 덮어버려서 value를 초기화 시킨다.

    //input에 초점 맞추기.(채팅 이어쓰기 같은 느낌?)
    input.focus();
}

//name 으로 접근하기.
function accessName(){
    const hobbyList = document.getElementsByName("hobby");

    let str = "";
    let count = 0;
    for(let i=0; i<hobbyList.length; i++){

        //** input 중에서 radio / checkbox 전용 속성**
        //checked : 해당 요소가 체크 되어 있으면 True 아니면 false 반환
        if(hobbyList[i].checked){ //현재 요소가 체크되어 있는 경우.
            //빈문자열 str에 계속 누적.
            str += hobbyList[i].value+"  ";
            count++;
        }
    }
    //div중에서 #name-div에 출력
    document.getElementById("name-div").innerText=str;
    
    document.getElementById("name-div").innerHTML +="<br><br><br> 선택된 개수 :"+ count;

}


//CSS선택자로 접근하기
function accessCss(){
    //querySelector() : 요소 하나 선택할때 사용
    document.querySelector("#css-div").style.border ="3px solid red";

    //여러개 있는 요소 선택 (첫 번째 요소 선택)
    document.querySelector("#css-div > div").style.fontSize="30px";

    //여러개 있는 요소 다 선택하고 싶을때
    //querySelectorAll() : 모든 요소 선택시 사용(배열 반환)
    const arr = document.querySelector("#css-div > div");

    for(let i=0; i<arr.length; i++){
        arr[i].style.background="brown";
    }
}


/* 카카오톡 채팅 만들기. */
function readValue(){
    //채팅 입력에 사용되는 요소 모두 얻어오기.
    const bg = document.getElementById("chatting-bg");
    const input =document.querySelector("#chatting-Input");       

    //input에 입력된 값이 있을 경우
    if(input.value.trim().length > 0){
        
        //문자열.trim() : 문자열 양 끝에 공백을 모두 제거.

        //input에 입력된 값을 얻어와 bg에 추가(누적)
        bg.innerHTML += "<p><span>"+input.value+"</span></p>";

        //bg의 스크롤을 제일 밑으로 내리기

        //요소.scrollTop      :요소의 현재 스크롤의 위치를 반환
        //요소.scrollTop=위치 : 스크롤를 특정 위치로 이동.
        
        //요소.scrollHeight   : 스크롤이 있는 요소의 내용 높이.

        bg.scrollTop=bg.scrollHeight;


    }else{
        alert("채팅 내용을 입력 해주세요.");
    }
    //입력된 input의 value 지우기.
    input.value="";
    //입력후에 다시 포커스 맞추기
    input.focus();
}

//input 태그에 키가 눌러졌을 때 엔터인 경우를 검사하는 함수.
function inputEnter(){
    if(window.event.key=="Enter"){ //눌려진 키가 Enter인 경우.
        readValue();  //함수를 호출
    }
}
