/* 서버를 구동해주는 JS 파일 */

//require를 사용하게 되면 자동으로 node 파일 내부를 
//조회 하기 때문에 키워드만 잘 적으면 된다

const express = require("express");  //express를 가져와 변수에 저장
const http = require("http");  //서버의 정보
const app = express();  //express를 실행한 값을 app에 저장
const path = require("path");// 서버 경로를 저장한 변수
const server = http.createServer(app);

const socketIO = require("socket.io");
const io = socketIO(server);

const moment = require("moment");

//console.log(__dirname);
app.use(express.static(path.join(__dirname,"src")))

io.on("connection",(socket)=>{
    socket.on("chatting",(data)=>{
        const {name,msg,} = data;

        io.emit("chatting",{
            name,
            msg:msg, /* es6부터는 아름같으면 생략 가능 */
            time:moment(new Date()).format("h:ss A")
        })
    })
});

//프로세스에 설정된 기본 포드 혹은 5000번
const PORT = process.env.PORT||5000;
server.listen(PORT,()=> console.log(`Run on server${PORT}`));

