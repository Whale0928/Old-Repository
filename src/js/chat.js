"use strict"

//서버 js에서 전달한 socket을 변수에 저장
const socket = io();

const nickname = document.getElementById("nickname");
const chatList = document.querySelector(".chatting-list");
const chatInput = document.querySelector(".chatting-input");
const sendBtn = document.querySelector(".sendBtn");
const displayContainer = document.querySelector(".display-container");

chatInput.addEventListener("keypress",send)
sendBtn.addEventListener("click",send)

function send(){

    if(nickname.value.trim()==''){
        alert("닉네임을 입력후 메세지를 전송해주세요")
        return false;
    }

    const param = {
        name : nickname.value,
        msg  : chatInput.value
    }
    socket.emit("chatting",param);
}



//메세지를 강제로 전송

//메세지를 전달받음
socket.on("chatting",(data)=>{
    const{name,msg,time} = data;
    const itme = new LiModel(name,msg,time);
    //li 모델을 인스턴스 화
    itme.makeLi();
    displayContainer.scrollTo(0,displayContainer.scrollHeight);
})



function LiModel(name,msg,time){
    this.name=name;
    this.msg = msg;
    this.time=time;

    this.makeLi = ()=>{
        const li = document.createElement("li");
        li.classList.add(nickname.value===this.name ? "sent" :"received" )
        const dom = `
        <span class="porfile">
            <span class="user">${name}</span>
            <img class="imgs" src="https://placeimg.com/50/50/and" alt="temp">
        </span>
        <span class="messge">${msg}</span>
        <span class="time">${time}</span>`;


        li.innerHTML=dom;
        chatList.appendChild(li);
    }
}