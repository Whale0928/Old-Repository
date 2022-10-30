    const num1 = document.getElementById("num1");
    const num2 = document.getElementById("num2");
    const resultBox = document.getElementById("resultBox");
    console.log(num1);
    console.log(num2);

function plus(){
   /*  const num1 = document.getElementById("num1");
    const num2 = document.getElementById("num2");
    const resultBox = document.getElementById("resultBox"); */
    
    resultBox.innerHTML=Number(num1.value)+Number(num2.value);
}
function minus(){
    const num1 = document.getElementById("num1");
    const num2 = document.getElementById("num2");
    const resultBox = document.getElementById("resultBox");
    resultBox.innerHTML=Number(num1.value)-Number(num2.value);
}
function multip(){
    const num1 = document.getElementById("num1");
    const num2 = document.getElementById("num2");
    const resultBox = document.getElementById("resultBox");
    resultBox.innerHTML=Number(num1.value)*Number(num2.value);
}
function division(){
    const num1 = document.getElementById("num1");
    const num2 = document.getElementById("num2");
    const resultBox = document.getElementById("resultBox");
    resultBox.innerHTML=Number(num1.value)/Number(num2.value);
}
function mod(){
    const num1 = document.getElementById("num1");
    const num2 = document.getElementById("num2");
    const resultBox = document.getElementById("resultBox");
    resultBox.innerHTML=Number(num1.value)%Number(num2.value);
}




function sum(op){
    const num3 = Number(document.getElementById("num3").value);
    const num4 = Number(document.getElementById("num4").value);
    
    let res = 0; //결과 저장용 변수.

    console.log(num3);
    console.log(num4);
    switch(op){
        case '+' : res = num3 + num4; break;
        case '-' : res = num3 - num4; break;
        case '*' : res = num3 * num4; break;
        case '/' : res = num3 / num4; break;
        case '%' : res = num3 % num4; break;
    }
    console.log(res);

    document.getElementById("resultBox2").innerText = res;
}