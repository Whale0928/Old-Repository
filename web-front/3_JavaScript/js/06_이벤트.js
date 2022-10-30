//인라인 이벤트 모델 확인하기.
function test1(button){    
    button.style.backgroundColor="black";
    button.style.color="white";

}

//고전 이벤트 모델 확인하기 1


//** 주의사항**
// 고전 / 표준 이벤트 모델은 렌더링된 HTML요소에 
// 이벤트에 관련된 동작을 부여하는 코드.
// ->렌더링이 되지 않는 요소에는 이벤트 관련 동작을 추가할 수 없다.

// -> 문제 원인 : HTML의 코드 해석 순서(위 -> 아래 )
// -> 해결 방법 : HTML 요소를 먼저 랜더링하고 js 호출


document.getElementById("test2-1").onclick = function(){
    //익명 함수. 이벤트 핸들러에 많이 사용한다.
    alert("고전 이벤트 모델로 출력된 대화상자.");
}


//이벤트 제거.
document.getElementById("test2-2").onclick = function(){
    //test2-2 이벤트 제거하는 방법
    document.getElementById("test2-1").onclick = null;
    alert("test2-1 이벤트 제거 완료");
}

//고전 이벤트 단점
// -> 한 요소에 동일한 이벤트리스너(onclick)에 대한 
//    다수의 이벤트 핸들러(배경색,폰트사이즈 변경)를 수행 할 수 없다.
//      마지막으로 수행된 이벤트핸들러만 적용

//  해결 방법 : 표준 이벤트 모델.

document.getElementById("test2-3").onclick = function(evnet){
    //방법 .1 요소를 문서에서 찾아서 선택
    //document.getElementById("test2-3").style.background="lightblue";

    //방법 .2 매개변수 e 또는 evnet 활용하기.
    // -> 이벤트 핸들러에 e또는 evnet를 작성하는 경우
    //    해당 이벤트와 관련된 모든 정보가 담긴 이벤트 객체가 반환된다.
    // console.log(evnet);
    
    //evnet.target : 이벤트가 발생한 요소
    //evnet.target.style.background="lightblue";

    // 방법 .3 this 활용하기 ->이벤트가 발생한 요소.
    console.log(this)
    this.style.background="lightblue";
}
/* document.getElementById("test2-3").onclick= function(){ 
    document.getElementById("test2-3").style.fontSize ="30px";
} */


//표준 이벤트 모델
/*
    요소.addEventListener("클릭",function(){})
                        이벤트    이벤트핸들러
*/
document.getElementById("test3").addEventListener("click",function(){
    
 //this : 이벤트가 발생한 요소.
  console.log(this.clientWidth);   //현재 요소의 너비(테두리 제외)

  //현재요소 너비 변경
  //this.style.width = "300px";

  this.style.width = this.clientWidth +20+"px";
})


//~~~~~~~~~ 몇년이 지난후~~~~~~~~~~~~

//test3에 이미 클릭 이벤트가 있는데 
// 중복해서 적용. (고전 이벤트 모델의 문제점 해결 , 확인)
document.getElementById("test3").addEventListener("click",function(){
    
    this.style.height = this.clientHeight+20+"px";
    
})

document.getElementById("changeBtn1").addEventListener("click",function(){

  /*const bg = document.getElementById("input1").value
    const box = document.getElementById("div1") */

    document.getElementById("div1").style.backgroundColor 
                = document.getElementById("input1").value;

})

/* document.getElementById("input1").addEventListener("key",function(){
    if(window.event.key=="Enter"){ //눌려진 키가 Enter인 경우.
        readValue();  //함수를 호출
    }
}) */

//input 1에 값이 변경 되었을 때 입력된 값으로 배경색을 변경하도록.
document.getElementById("input1").addEventListener("keyup",function(){

    //changer evnet 
    // text 관련 input 태그
    // 입력된 값이 변할 때를 change라고 하지 않고 
    //  입력이 완료된 후 포커스를 잃거나 ,엔터를 입력하는 경우.
    //  입력된 값이 이전 값과 다를 경우를 change가 발생한걸로 인식한다.
    // 이전값 과 동일할 경우 change로 취급되지 않는다.


    //keyup : 키가 올라올때마다 변경
    //하지만 이때 색명 혹은 rgb 값이 존재해야지만 적용된다.


    console.log("change 이벤트 발생!");

    document.getElementById("div1").style.backgroundColor 
                = document.getElementById("input1").value;

})

//a태그 기본 이벤트 제거
document.getElementById("moveNaver").addEventListener("click",function(e){
    //매개변수 e 또는 evnet == 이벤트 발생 객체
                    // 이벤트와 관련된 정보가 담겨있는 객체.

    e.preventDefault();
    //prevent : 막다 , 방지하다
    //preventDefault : 기본적인 것을 막는다
    // e.preventDefault(); : 이벤트 발생 객체의 기본 이벤트를 막는다(제거)

})


//form 태그 기본 이벤트 제거
//방법 1 . submit 버튼을 사용 안하는 방법
document.getElementById("testBtn1").addEventListener("click",function(){

    //input 에 입력된 값 가져오기
    const in1 = document.getElementById("in1").value;

    //#in1의 작성된 값이 '제출'일 경우에  testForm1을 submit
    if(in1=="제출"){
        
        //**form 태그에 name속성이 있을 경우 직접 선택 가능.
        //document.fomr태그 name속성값

        //** form요소.submit()  : form 태그 제출 함수.
        console.log(in1);
        document.testForm1.submit();
    
    }
});



//방법 2. onsubmit를 이용해서 form 제출 막기
function checkIn2(){
    const in2 = document.getElementById("in2").value;
    
    console.log(in2);
    if(in2=="제출"){
        return true;
    }else{
        return false;
    }
}