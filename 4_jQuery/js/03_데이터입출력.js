//1단계

//[jQuery]
$("#btn1").on("click",function(){
    //this : 이벤트가 발생한 요소.
    
    //$(this) : 이벤트가 발생한 요소를 jQuery 방식으로 선택
    
    //버튼의 전전 요소의 '값'얻어오기.
    const color = $(this).prev().prev().val();
    
    //console.log( $(this).prev().val()); : br 이 선택됨.
    //console.log(color);  : 색 입력 확인
    
    //버튼의 전전전 요소 고르기
    
    $(this).prev().prev().prev().css("background-color",color);
})


//2단계 .
$("#btn2").on("click",function(){
    const arr = $(".box")
    
    for(let i=0; i<arr.length; i++){
        let color = $($(".input2")[i]).val();
        $(  $(".div2")[i]  ).css("background-color",color);
        console.log(i+":"+color);
    }
})




//3단계


//jQuery
$(".input3").on("input",(el)=>{
    $($(el.target).prev()).css("background-color",$(el.target).val()).css("transition","1s");
    $(el.target).css("outline","2px solid "+$(el.target).val());

    //$(this) 로 선택해도 된다. 
    //지금 나는 화살표 함수 써서 안된다. 왜 안되나 했네.
    //console.log($(this).val());
}) 

//-------------------------------------------------

//js
const temp = document.querySelectorAll(".input3");
//js로 클래스에 이벤트 추가하려면 하나하나 수작업으로 넣어줘야한다.
for(let i of temp){  //for - of 배열용 향상된 for문
    i.addEventListener("input",addColor);
}
function addColor(){
    this.style.backgroundColor=this.value;
}