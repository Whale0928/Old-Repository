/* 서버를 구동해주는 JS 파일 */

//require를 사용하게 되면 자동으로 node 파일 내부를 
//조회 하기 때문에 키워드만 잘 적으면 된다

const express = require("express");  //express를 가져와 변수에 저장
const app = express();  //express를 실행한 값을 app에 저장
const http = require("http");  //서버의 정보
const path = require("path");// 서버 경로를 저장한 변수
const server = http.createServer(app);

const socketIO = require("socket.io");
const io = socketIO(server);

const moment = require("moment");

//DB 연결
let db;
const MongoClient = require('mongodb').MongoClient;
function send(data) {
    MongoClient.connect('mongodb+srv://admin:qwer1234@cluster0.uztxq.mongodb.net/?retryWrites=true&w=majority', function (에러, client) {
        if (에러) return console.log(에러);

        console.log(data);
        //db = client.db("ChatDB");
        // db.collection('Chat').insertOne({이름:'성대',나이:25},function(에러,결과){
        //     console.log('저장완료');  });
        const { name, msg, } = data;
        db = client.db("ChatDB");
        db.collection('Chat').insertOne({ 작성자: name, 내용: msg, 작성일: moment(new Date()).format("h:ss A") }, function (에러, 결과) {
            console.log('저장완료');
        });

        if (에러) { console.log(에러); }
        console.log('Connection on DB');
    })
}


io.on("connection", (socket) => {
    socket.on("chatting", (data) => {
        const { name, msg, } = data;
        send(data)
        io.emit("chatting", {
            name,
            msg: msg, /* es6부터는 아름같으면 생략 가능 */
            time: moment(new Date()).format("h:ss A")
        })
    })
});

//프로세스에 설정된 기본 포드 혹은 5000번
const PORT = process.env.PORT || 5000;
server.listen(PORT, () => console.log(`Run on server${PORT}`));
//console.log(__dirname);
//app.use(express.static(path.join(__dirname, "src")))

