
document.getElementById("btn1").addEventListener("click",function(){
  setTimeout(function(){alert("3초과 경과된 것")},2000)
})

let interval; //setInterval을 저장하기 위한 전역변수.


function clockFn(){
    const clock = document.getElementById("clock");
    clock.innerText = currentTime();
    
    //지연시간 마다 반복(첫 반복도 지연시간 후에 시작)
    // -> 페이지 로딩후 1초후 부터 반복된다. (지연->함수->지연->함수)
    interval =  setInterval(function(){
        clock.innerText = currentTime();
    },1000)

}
function currentTime(){
    const now = new Date();
    return now.getHours()+":"+now.getMinutes()+":"+now.getSeconds();

}
clockFn(); //로딩시 자동으로 함수 호출

document.getElementById("stop").addEventListener("click",function(){
    clearInterval(interval);
})
