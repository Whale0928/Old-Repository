(() => {
	if (document.getElementById("openChatRoom") != null) {

		document.getElementById("openChatRoom").addEventListener("click", () => {
			const modal = document.querySelector('.modal');
			modal.classList.add('show');
			modal.classList.remove('hide');
		});

		document.getElementById("modal-close").addEventListener("click", () => {
			const modal = document.querySelector('.modal');
			modal.classList.add('hide');
			modal.classList.remove('show');
		});

		document.getElementById("open-form").addEventListener("submit", e => {
			const chatRoomTitle = document.getElementById("chatRoomTitle");
			if (chatRoomTitle.value.trim().length == 0) {
				alert("채팅방 제목을 입력해주세요.");
				e.preventDefault(); // form태그 기본 이벤트 제거
			}
		});
	}
})();


(function(){
	const ul = document.getElementById('chat-box');
	if(ul != null){
		ul.scrollTop = ul.scrollHeight;
	}
})();

$('#send').click(sendMessage);

function sendMessage() {
	const inputChatting = document.getElementById('inputChatting');

	if (inputChatting.value.trim().length == 0) {
		alert("채팅을 입력해주세요");
		inputChatting.value = "";
		inputChatting.focus();
	} else { //입력된 경우 소켓을 이용해 
		const chatMessage = {
			"memberNo": memberNo,
			"memberEmail": memberEmail,
			"memberNickname": memberNickname,
			"chatRoomNo": chatRoomNo,
			"message": inputChatting.value
		};

		/* 
	private int cmNo;
	private String message;
	private Date createDate;
	private int chatRoomNo;
	private int memberNo;
	private String memberEmail;
	private String memberNickname;
	 */

	/* 	console.log(chatMessage);
		console.log(JSON.stringify(chatMessage)); */

		//chattingsock을 이용해 메세지 보내기
		chattingSock.send(JSON.stringify(chatMessage));
		inputChatting.value="";
	}
} //전송 함수 종료

//chattingSock


//웹소켓 핸들러에서 
//s.sendMessage(new TextMessage(message.getPayload()));
//구문이 수행되어 메세지가 전달된 경우.
chattingSock.onmessage = function(e){
	//매개변수 e: 발생한 이벤트에 대한 정보를 답고 있는 객체
	//e.data : 전달된 메세지 (message.getPayload()) ==JSON 형태로

	const msg = JSON.parse(e.data);
	console.log(e.data);

	const ul = document.getElementById('chat-box');
	let li = document.createElement("li");
	let p = document.createElement("p");
	p.classList.add('chat');
	
	p.innerHTML = msg.message.replace(/\\n/gm,'<br>');

	let span = document.createElement("span");
	span.classList.add('chatDate');
	span.innerText = new Date(msg.createDate);


	//내가 쓴 채팅 : span -> p
	//남 쓴 채팅 : p -> span
	if(msg.memberNo == memberNo){
		li.append(span,p)
		li.classList.add("myChat");
	}else{
		li.innerHTML = "<b>"+msg.memberNickname+"<b><br>"
		li.append(p,span)
	}

	ul.append(li);

	//채팅창 제일 밑으로 내리기.
	//스크롤이동
	ul.scrollTop = ul.scrollHeight;
	
}



