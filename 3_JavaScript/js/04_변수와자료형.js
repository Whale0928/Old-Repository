var str="JS 코드를 함수 내부가 아닌 JS파일 또는 script태그 밑에 바로 작성하면"
            +"HTML 랜더링시 바로 실행된다.";
console.log(str);


//변수선언 위치에 따른 구문
var num1 = 1;
num2 = 2;

console.log("[js 파일 내부]")    
console.log("num1:"+num1);
console.log("num2:"+num2);


function test(){
    var num3 = 3;
    num4 = 4;

    if(true){
        var num5 = 5;
        num6 = 6;
    }
    console.log("num5:"+num5);
}
test(); //함수를 호출해야지 변수가 생성됨.

//console.log("num3:"+num3);
console.log("num4:"+num4); //function 종료 후에도 사용 가능.
console.log("num6:"+num6); // function 내부의 if문에서 선언된 변수여도 전역변수.



/* 자료형 확인하는 변수 */
function typeTest(){
    
    const typeBox = document.getElementById("typeBox");

    let temp; //선언 후 값을 초기화 하지 않음 == undifined
    typeBox.innerHTML="temp:"+temp;

    //string
    const name = "홍길동";

    //typeof 변수명 : 해당 변수의 자료형의 검사하는 연산자.
    typeBox.innerHTML +="<br>name:"+name+"/"+typeof name;
    
    
    const gender = "M";
    typeBox.innerHTML +="<br>Gender:"+gender+"/"+typeof gender;
    //자바 스크립트는 char 자료형이 존재하지 않는다.
    //" ",' ' 모두 String 표기법이다.


    //number
    const age = 25;
    const height = 178.9;

    typeBox.innerHTML +="<br>age:"+age+"/"+typeof age;
    typeBox.innerHTML +="<br>height:"+height+"/"+typeof height;
    
    
    
    //boolean
    const isTrue = true;
    
    
    //object
    //js 에서 배열 ( 대괄호 사용 필요)
    const arr = [10,30,70,50];
    typeBox.innerHTML +="<br>arr:"+arr+"/"+typeof arr;
    
    for(let i=0; i<arr.length;i++){
        //typeBox.innerText += ", "+arr[i];    
        typeBox.innerHTML +="<br>arr["+i+"] : "+arr[i];
    }
    
    //자바스크립트 객채 K:V (map형식)으로 작성
    const user = {"id":"user01","pw":"pass01"};
    typeBox.innerHTML += "<br>user : "+user+"/"+typeof user;
    //js 객체는 toString 오버라이딩이 안된거처럼 전부 조회가 안된다.

    //1번 방법 하나씩 조회
    typeBox.innerHTML += "<br>user.id : "+user.id+"/"+typeof user.id;
    typeBox.innerHTML += "<br>user.pw : "+user.pw+"/"+typeof user.pw;

    //2번 방법 (객체 전용 for문 (for...in ) )
    for(let key in user){
        //user 객체의 key (id,pw)를 반복 할 때 마다 하나씩 얻어와 key 변수에 저장ㅅ
        typeBox.innerHTML += "<br>user["+key+"] : " + user[key];
    }

    //가장 쉬운건 콘솔로 그냥 보는거
    console.log(user);




    //function (함수도 자료형)
    // 변수명 == 함수명

        const sumFn = function(n1,n2){//이름이 없는 함수== 익명함수.
        return n1+n2;
        }

        //함수명만 작성한 경우 -> 함수에 작성된 코드가 출력됨.
        typeBox.innerHTML += "<br>sumFn : "+sumFn+"/"+typeof sumFn;
        //함수명 () 괄호 포함해 작성시 호출후 동작.
        typeBox.innerHTML += "<br>sumFn : "+sumFn(20,35)+"/"+typeof sumFn;

        //2번 함수를 매개변수로 전달
        typeBox.innerHTML += "<br>tempFn(30,sumFn) : "+tempFn(30,sumFn)
        }

        function tempFn(n3,fn){
        return n3 +fn(10,20);
        }