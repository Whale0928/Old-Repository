

function sum(op){
    const num1 = Number(document.getElementById("num1").value);
    const num2 = Number(document.getElementById("num2").value);
    
    //eval("코드 형식 문자열")
    // 작성된 문자열을 js 코드로 해석되는 함수.
    //문제가 많음

    //해결 방법 : new Function () 사용
    //document.getElementById("resultBox").innerText = eval(num1+ op +num2);

    oper = op.innerText;
    
    document.getElementById("resultBox").innerText 
          = new Function("return "+num1+ oper +num2)(); // 이 한줄이 함수
}