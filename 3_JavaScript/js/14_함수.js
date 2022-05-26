function clickCount(btn){
    //이벤트  전달 객체
    console.log(btn.innerText);
    btn.innerText =Number(btn.innerText)+1;
    
}

//-------------------------------------------------------------

//즉시 실행 함수 확인하기.
function test1(){ //기본함수.
    console.log("비교를 위해 제작된 기본함수.");
}

//test1();//호출

(function(){
    console.log("즉시 실행된 함수");
    //호출하지 않아도 자동을 수행.
})();

//즉시 실행 함수의 변수명 중복 해결
const str = "전역 변수.";

(function(){
    const str = "즉시 실행 함수에서의 지역변수";
    console.log(str);
})();
console.log(str);

//-------------------------------------------------------------
// 1.기본형태 ([매개변수]) => { 함수 }
document.getElementById("btn2-1").addEventListener("click",() => {alert("화살표 함수 기본형태.")})

// 2.매개변수 1개  매개변수 => { 함수 }
document.getElementById("btn2-2").addEventListener("click",
        e => {e.target.style.backgroundColor="yellow"})

document.getElementById("btn2-3").addEventListener("click",() => {
    
    //함수 호출 (익명)
    printConsole( function(num){return num*10});

    //함수 호출(화살표 함수)
    printConsole( num => {return num*5} )

    printConsole( num =>  num*3 )


    //반환값이 Object 이면 {} , retunr 생략 불가
    //화살표 다음에 함수 정의 부분이 있어야 하는데 
    //객체(JS Object) 가 작성되서 생략이 불가능
    printConsole(  (num) => { return {price:num*1000 , n:num}})
 })

function printConsole(fn){//매개변수 함수가 전달됨.
    console.log( fn(2) );
}

//this 사용 불가

//익명함수는 this 사용 가능
document.getElementById("btn2-4").addEventListener("click",function(){
    this.style.backgroundColor="red";
})
//화살표 함수는 this 사용 불가능
document.getElementById("btn2-4").addEventListener("click",e=>{

    //화살표 함수에서 this는 window를 가르친다.
    //this.style.color="white";

    e.target.style.color="white";
})