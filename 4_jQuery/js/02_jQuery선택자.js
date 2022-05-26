//jQuery 선택자

//JS 방식
document.getElementById("btn1").addEventListener("click",function(){
    //.test 클래스 JS/jQuery 방식으로 선택

    const list = document.getElementsByClassName("test") //JS 방식
    const list2 = $("test");

    //list 배열에 각 요소에 배경색 지정
/*     for(let i of list){
        i.style.backgroundColor="yellow";
    } */
    

    //배열 자체에 직접 스타일을 지정하는건 불가능!
    
    //console.log($(".test"));
    $(".test").css("fontSize","25px").css("background","green")
    //-> jQuery 는 요소가 묶여 있는 배열에 스타일이나 이벤트를 지정하는 경우 
    //      배열 내 모든 요소에 적용된다.
    
})

/* $("btn1").on("click",function(){
}) */