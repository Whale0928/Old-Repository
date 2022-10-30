// inner Text 읽어오기
function getInnerText(){
    //html 문서 내에서 아이디가 "test1"인 요소를 얻어와
    //test1 변수에 대입
    const test1 = document.getElementById("test1");
    //test1 변수에 대입된 요소에서 내용을 얻어와 console에 출력.
    console.log(test1.innerText);
}

function setInnerText(){
    //id가 text1인 요소 얻어오기
    const test1 = document.getElementById("test1");

    //test1 변수에 대입된 요소에 새로운 내용을 작성
    test1.innerText= "innerText에 의해 변경된 내용입니다.";
}

//innerHTML로 읽어오기
function getInnerHTML1(){
    const test2 = document.getElementById("test2");

    //내부 요소의 모든 내용 출력
    console.log(test2.innerHTML);
}
//setInnerHTML으로 세팅하기
function setInnerHTML1(){
    const test2 = document.getElementById("test2");

    test2.innerHTML = "<b>inner HTML로 변경된 내용 </b> <br> 졸리다. ";
}


//inner HTML 응용
function add(){
    //1 ) 아이디가 area1인 요소 얻어오기
    const area1 = document.getElementById("area1");

/*     //2 ) area1 내부 내용(태그,속성,내용)을 모두 읽어오기
    const content = area1.innerHTML;

    //3 area1에 이전내용 + 새로운 요소(div.box2) 추가.
    area1.innerHTML = content + "<div class='box2'></div>"; */

    //2 + 3 번 과정 한번에 하기.

    area1.innerHTML += "<div class='box2'></div>";  
}

function fnAlert(){
    window.alert("alert 버튼 클릭됨");
   //window : 브라우저 자체를 나타내는 객체. 
   // -> JS 코드는 브라우저 (Window) 내부에서 실행되는 코드이다 보니 
   //  -> window를 생략 할 수 있다.
}

function fnConfirm(){
    if(confirm("버튼 배경 색을 초록 변경하시겠습니까?")){
        document.getElementById("confirmBtn").style.backgroundColor = "green";
        document.getElementById("confirmBtn").style.color = "white";
    }else{
        document.getElementById("confirmBtn").style.backgroundColor = "#EFEFEF"
        document.getElementById("confirmBtn").style.color = "black";
    }
}

/* 프롬포트 확인하기 */
function fnPrompt(){
    const input = prompt("이름을 입력해주세요.")
    const promptResult = document.getElementById("promptResult");

    
    if(input !=  null){ //이름이 입력되었을때
        promptResult.innerText = input + "님 환영합니다";
    }else{ //취소 버튼을 눌렀을 때.
        promptResult.innerText = "이름을 입력해주세요";
    }
}