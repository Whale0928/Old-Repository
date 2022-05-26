//
document.getElementById("jsBtn").addEventListener("click",function(){
   
    //배경 검정 , 글자 노랑 ,폰트 사이즈 20

    this.style.background="black";
    this.style.color="yellow";
    this.style.fontSize="20px"
    
})

//----------------------------------------------------------
// jQuery 방식
// 이벤트리스너와 동일하다== on
$("#jQueryBtn").on("click",function(){
    $(this).css("backgroundColor","black").css("color","yellow").css("fontSize","20px");
})



//----------------------------------------------------------

//window.onload

window.onload=function(){
    console.log("1");
}
window.onload=function(){ //이전에 작성된 onload를 덮어씌운다.
    console.log("3");
}
console.log("2");



//ready() 함수.
//--문서가 준비되었을 때 (로딩완료) 실행
$(document).ready(function(){
    $("#readyTest").text("ready() 함수 확인중");
    $("#readyTest").on("click",function(){
        console.log("클릭중");
    })
})


//ready를 중복으로 작성함
$(document).ready(function(){
    document.getElementById("readyTest").style.color="pink";
})

//ready 함수 다른 형태.
$(function(){
    console.log("Ready 다른 형태");  
})

//ready + 화살표 ㅎㅁ수
$(()=>{
    console.log("ready함수에서 화살표함수 추가");
})

$(el=>{
    console.log("매개변수도 잇다.");
})