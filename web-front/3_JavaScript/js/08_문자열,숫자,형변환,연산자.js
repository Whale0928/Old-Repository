
document.getElementById("btn1").addEventListener("click",function(){
  
    //문자열.indexOf("검색값"):
    //문자열에서 검색값과 일치하는 부분이 시작하는 인덱스를 반환 없으면 -1 반환

    const str1 = "Hello World";
    console.log(str1.indexOf("e")) //1
    console.log(str1.indexOf("l")) //2(가장 먼저 검색된 인덱스)
    console.log(str1.lastIndexOf("l")) //뒤에서부터 검색.
    console.log(str1.indexOf("h")) //1

    //문자열.substring(시작인덱스,종료인덱스(미만,미포함))
    //문자열 일부를 잘라내기 
    // 시작 이상 종료 미만
    const str2 ="abcdefg";
    console.log(str2.substring(0,3));
    console.log(str2.substring(2,6));


    //문자열.split("구분자") : 문자열을 구분자로 잘라서 배열로 반환
    const str3 ="햄버거,비빔밥,라면,파스타,샌드위치"
    const arr = str3.split(",");
    for(let i = 0 ; i<arr.length; i++){
        console.log(arr[i]);
    }
})

document.getElementById("btn2").addEventListener("click",function(){
    //숫자 관련 함수.

    //infinity 
    console.log(5/0);

    if(5/0==Infinity){
        console.log("무한");
    }
    
    //NaN 리터럴 표기
    console.log("ar"*5);
    
        //if(5/"01s"==NaN){  X

        // isNaN(값) : 값이 NaN이면 true 아니면 false
        if(isNaN(5/"01s")){
            console.log("NaN숫자아님");
        }

    //Math.random()  0.0 ~ 0.99
    //this.innerText=Math.floor(Math.random()*45+1);
    
    //소수점 관련 함수
    //round() , ceil() , floor() , trunc()
    //반올림    올림      내림      버림

    //** 소수점의 자리 지점 불가
    
    console.log(Math.round(10.5)); //11
    console.log(Math.ceil(10.5)); //11
    console.log(Math.floor(-10.5)); //10
    console.log(Math.trunc(-10.5)); //10     

    //버튼 배경색 랜덤으로 바꾸기.
    const r = Math.floor( Math.random()*255);
    const g = Math.floor( Math.random()*255);
    const b = Math.floor( Math.random()*255);

    this.style.backgroundColor = "rgb("+r+','+g+','+b+")"

    //숫자.toFixed(자리수) : 지정된 자리수까지 반올림해서 표현.
    //부동소수점(부정확) - > 고정소수점(정확한)으로 변경
    console.log((3.45).toFixed(1));
})


//형변환 확인.
document.getElementById("btn3").addEventListener("click",function(){
    console.log(parseInt(1.234));
    console.log(parseFloat(1.234));
    console.log(Number(1.234));
})