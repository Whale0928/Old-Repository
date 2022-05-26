document.getElementById("btn1").addEventListener("click",function(){
    
    // #test 자식노드 모두 얻어오기
    //요소. childNodes : 요소의 자식 노드를 모두 반환.
    const nodeList = document.getElementById("test").childNodes;
    console.log(nodeList);
    //console.log(nodeList[1].nodeValue);  

    //노드 탐색

    //1) 부모 노드 탐색 : PraentNode 
    const li1 = document.getElementById("li1");
    console.log(li1.parentNode); // ul#test

    li1.parentNode.style.background = "red";
    //부모 노드 마지막에 새로운 노드를 추가 
    //( append : (마지막에,제일뒤에)덧붙이다.)
    li1.parentNode.append("ABCD");



    //2) 첫번째 자식노드 탐색 : firstChild
    console.log(document.getElementById("test").firstChild);
    //3) 마지막 자식노드 탐색 : LastChild
    console.log(document.getElementById("test").lastChild);
    
    //3) 중간에 위치한 자식노드 탐색 : 부모요소.childNodes[0]
    console.log(nodeList[11]);


    //5) 이전 형제 노드 탐색 : previous Sibling 
    //   다음 형제 노드 탐색 :  next Sibling 
    console.log(nodeList[8]);
    console.log(nodeList[8].previousSibling);
    
    console.log(nodeList[8].previousSibling.previousSibling.previousSibling);

})


//node Element 차이 
document.getElementById("btn2").addEventListener("click",function(){
    //#test의 모든 자식 요소들을 배열형태로 list에 저장
    const list = document.getElementById("test").children;
    const test = document.getElementById("test");
    console.log(list);

    //#test 첫 자식 요소
    document.getElementById("test").firstElementChild.style.background="green";
    //#test 마지막 자식 요소
    document.getElementById("test").lastElementChild.style.background="brown";

    //#test의 자식중 2번 인덱스의 이전/다음 형재 요소
    list[2].previousElementSibling.addEventListener("click",function(){alert("2번째 요소의 이전 요소")})
    list[2].nextElementSibling.addEventListener("click",function(){alert("2번째 요소의 다음 요소")})

    console.log(prevEl(list[2]));
    console.log(nextEl(list[2]));
})

//이전 형제요소 선택 함수.
function prevEl(el){
    return el.previousElementSibling;
    //해당 요소의 이전 형제 요소 반화니
}
//다음 형제요소 선택 함수.
function nextEl(el){
    return el.nextElementSibling;
}

let count =1;
document.getElementById("btn3-1").addEventListener("click",function(){
    const div = document.getElementById("div3-1");
    if(count<=10){
        div.innerHTML+="<div>"+count+"</div>";
        count++;
    }
})
//createElement 버튼 클릭시.
let count2 =1;
document.getElementById("btn3-2").addEventListener("click",function(){
    const div = document.getElementById("div3-2");

    //create Element를 이용해서 div요소 생성.
    //document.createElement("태그명");
    const child = document.createElement("div"); //div 생성은 O 배치는 X

    if(count2<=10){
        
    //만들어진 div에 내용 추가
    child.innerText = count2;
    count2++;
    //#div3-2의 마지막 자식 요소로 추가하기(append)
    div.append(child);
    }
})