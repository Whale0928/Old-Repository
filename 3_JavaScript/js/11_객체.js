document.getElementById("btn1").addEventListener("click",function(){
    const div1 = document.getElementById("div1");
    
    // {}객체 리터럴 표기법으로 객체 생성
    
    //중요!!!!!!!!!!!!!!!!!!
    //자바스크립트 객체의 Key 역할은 무조건 String (묵시적)
    //따옴표 없어도  String으로 인식함.

    const brand = "coffebean";

    const product = {
        "pName" : "텀블러",
        'brand': "스타벅스",
        color : ["white","black","silver"],
        price : 22000,

        //기능 ( 메서드 추가 )
        mix:function(){
            console.log("흔들어주세요 쉐킷쉐킷 베이베 쒜킷 베이베");
        },

        information : function (){
            //같은 객체 내부에 다른 속성을 호출하고 싶은 경우
            //현재 객체를 뜻하는 this를 앞에 붙여야 한다.
            
            //이벤트 안에서 this : 이벤트 대상 요소
            //객체 안에서 this : 함수 내부의 요소

            //this 미작성시 객체 외부 변수 호출
            console.log(this.pName);
            console.log(this.brand);
            console.log(this.color);
            console.log(this.price);

            //this 미작성시 전역변수 brand 를 호출한다.
            console.log("--------------------");
            console.log(brand);
        }
    };

    div1.innerHTML= "" //div1 의 내용 모두 지우기
    div1.innerHTML += "product.pName:"+"<br>";
    div1.innerHTML += "product.brand:"+product.brand+"<br>";
    div1.innerHTML += "product.color:"+product.color+"<br>";
    div1.innerHTML += "product.price:"+product.price+"<br>";


    div1.innerHTML += "<hr>";
    //javascript 객체용 향상된 for문
    //for -- in 
    //객체 내부에 작성된 Key를 순서대로 하나씩 꺼내옴
    for(let key in product){    
        div1.innerHTML+=product[key]+"<br>"
                    //  배열의 인덱스 선택하듯이 반복하면된다.
    }


    div1.innerHTML += "<hr>";

    //객체의 메서드를 호출
    product.mix();

    product.information();
})

// ----------------------------------------------------------------------

//생성자 함수 (자바의 생성자를 함수로 작성하는 모양)

//1.생성자 함수 정의 (생성사 함수명은 대문자로 시작!)
function Student(name,grade,ban){

    //속성
    //this == 생성되는 객체 (앞으로 생성될 객체)
    this.name = name; //생성되는 객체 name에 매개변수 name을 대입해
    this.grade = grade; //생성되는 객체 grade 매개변수 grade 대입해
    this.ban = ban; //생성되는 객체 ban 매개변수 ban 대입해

    //기능(메서드)

    this.intro = function(){
        console.log(grade+"학년 "+ban+"반 "+name+"입니다.");
    }
}

//2. 생성자 함수 호출 ( new 연산자 )

document.getElementById("btn2").addEventListener("click",function(){
    const std1 = new Student("홍길동",3,2);
    const std2 = new Student("홍길동",3,2);
    const std3 = new Student("홍길동",3,2);

    // 생성자 함수 사용 이유 : 같은 형태의 객체가 다수 필요한 경우에 사용한다
    console.log(std1);
    console.log(std1.name);
    std1.intro()
    console.log(std2);
    console.log(std3);
})