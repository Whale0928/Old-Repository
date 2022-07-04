const express = require('express');
const app = express();
const path = require("path");// 서버 경로를 저장한 변수

const http = require("http");  //서버의 정보
const server = http.createServer(app);
const socketIO = require("socket.io");
const io = socketIO(server);

const moment = require("moment");
time = moment();
time.format("HH:mm:ss");	// 2021.10.09 00:09:45

let url = require('url');

//  Request 객체 해석용 
const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));


//EJS등록을 위한 코드
app.set('view engine', 'ejs');

//정적 파일들 처리용 
app.use('/resource/css/', express.static(__dirname+"/resource/css/"));
app.use('/resource/js/', express.static(__dirname+"/resource/js/"));

//Mongo DB 연결용 객체
const MongoClient = require('mongodb').MongoClient;

//어떤 DB에 저장할건지
let db;

MongoClient.connect('mongodb+srv://admin:qwer1234@cluster0.uztxq.mongodb.net/?retryWrites=true&w=majority', function (에러, client) {
    if (에러) { return console.log(에러); }
    //todoapp에 접근하겟다라는 의미
    db = client.db('todoapp');
    // 서버 구동
    app.listen(5000, function () {
        console.log("Run on Server 5000");
        console.log("DB연결 완료");

            

    });
})

//어떤 사람이 add로 경로를 요청하면 데이터 2개를 보내주는데 (날짜 제목) , 
// 이때 'post'라는 이름을 가진 colleection에 두개의데이터 저장하기.

/* // 서버 구동
app.listen(5000, function () {
    console.log("Run on Server 5000");
}); */
/* app.get(" 경로 " , function(){}); */
//app.use(express.static(path.join(__dirname, "src")))

app.get("/", function (요청, 응답) {
    응답.sendFile(__dirname + '/index.html')
})
app.get("/write", function (요청, 응답) {
    응답.sendFile(__dirname + '/write.html')
})

//할일 작성 시 수행
app.post("/add", function (req, resp) {
    resp.send("전송 완료");
    let todo = req.body.title;
    let day = req.body.date;

    db.collection('counter').findOne({name:'게시물 갯수'},function(에러,결과){
        if(에러)return console.log(에러);
        console.log(결과.totalPost);
        let count = 결과.totalPost

        db.collection('post').insertOne({_id:count+1,제목: todo, 날짜: day }, function () {
            console.log('저장완료');
            db.collection('counter').insertOne({name:총개시물})
        });
    });

  
});

//글번호 달기 기능 추가



//DB 조회시 수행
app.get("/list", function (req, resp) {
    //데이터 가져오기
    db.collection('post').find().toArray(function(에러,결과){
        if(에러) return console.log(에러);
        console.log(결과);
        resp.render('list.ejs',{posts:결과});
    });
});


//채팅방 접속 URL
app.get("/chatList",function(req,resp){
    console.log('로그인한 회원의 번호 :'+req.query.loginMemberNo);
    /* console.log('채팅방 회원의 번호 :'+req.query.chatMemberNo); */

    resp.sendFile(__dirname+'/views/chatList.html');
    //응답.sendFile(__dirname + '/write.html')
});