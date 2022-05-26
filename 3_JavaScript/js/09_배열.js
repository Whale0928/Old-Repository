
document.getElementById("btn1").addEventListener("click",function(){
    //배열 선언 방법.
    const arr1=new Array(3); //3칸짜리 배열 생성
    const arr2=new Array(); //0칸 짜리 배열(비어있는 배열) 생성
  
    /* console.log(arr1);
    console.log(arr2); */

    arr1[0] = "깁밥";
    arr1[1] = 123;
    arr1[2] = true;
    arr1[3] = true;
    console.log(arr1);

    //0칸 배열에 대입 -> 자동으로 길이가 증가함
    arr2[0] = "깁밥";
    arr2[1] = 123;
    arr2[2] = true;
    console.log(arr2);

    // 배열 선언 방법
    const arr3 = []; //비어있는 배열 [0칸]
    const arr4 = ["사과","딸기","망고","용과"] //선언 +초기화
     
    //for문 배열 요소 접근.
    // 1. 일반 for문
    // 2. 향상된 for문 (for .... of) : 배열용 향상된 for문
     
    for(let item of arr4){
    //arr4 배열에서 요소를 하나씩 얻어와 item에 대입
        console.log(item);
    }
})

document.getElementById("btn2").addEventListener("click",function(){
    
    //비어 있는 배열
    const arr = [];

    arr.push("삽겹살");
    arr.push("곱창");
    arr.push("양고기");
    arr.push("돼지고기");

    console.log(arr);

    //pop() : 배열 요소 꺼내오기
    console.log(arr.pop());
    console.log(arr);
    /* console.log(arr.pop());
    console.log(arr); */


    console.log("소고기 인덱스:"+arr.indexOf("소고기"));
    console.log("없는거 인덱스:"+arr.indexOf("회"));

 
    //배열.sort([정렬기준 함수])
        const arr2 = [5,2,10,1,8,3,7];
        console.log(arr.sort()); //문자
        console.log(arr2.sort()); //숫자->정상적으로 정렬 X
                                 // 왜 ?  문자열로 변환되서 정렬되기 때문에.
                                 // (문자열 정렬 기준이 기본값)
  
        //숫자 오름차순 정렬.
        console.log(arr2.sort( function(a,b){return a-b} ));


        console.log(arr.toString());
        console.log(arr.join("/"));
})