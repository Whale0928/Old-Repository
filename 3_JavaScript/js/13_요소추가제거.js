const con = document.getElementById("container");

document.getElementById("add").addEventListener("click",function(){
    
    //div 요소 생성
    const div = document.createElement("div");
    //div에 row추가
    div.classList.add("row");
    
    //input 요소 생성
    const input = document.createElement("input");
    input.classList.add("in");
    //input 에 "type"속성 , "number" 속성값 추가
    //요소.setAttribute("속성","속성값") : 요소에 속성 / 속성값
    input.setAttribute("type","number");

    //span 요소 생성
    const span = document.createElement("span");
    //span에 클래스 추가
    span.classList.add("remove");
    span.innerText="X";

    //span이 클릭되었을 떄에 대한 이벤트 동작
    //방금 만들어진 span에 부여된 이벤트
    span.addEventListener("click",function(){
        //요소.parentElement : 부모요소
        //요소.remove () : 요소 제거

        //부모(.row) 
        this.parentElement.remove();
    })

    //div에 input , span  순서로 초가
    div.append(input,span);/* 
    div.append(span); */

    con.append(div);
})

document.getElementById("calc").addEventListener("click",function(){
    //합계 저장용 변수

    let sum = 0;

    //in 클래스 요소들 모두 얻어오기
    const list = document.getElementsByClassName("in");
    
    //배열용 향상된 for문 (for of) 사용
    for(let input of list){
        sum += (Number)(input.value);
        //빈칸은 0으로 변환되기때문에 신경 쓰지 않아도 ㄱㅊ다.
    }
    alert("계산결과 : "+sum);
})

