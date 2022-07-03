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

//  Request 객체 해석용 
const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));


//EJS등록을 위한 코드
app.set('view engine', 'ejs');


//Mongo DB 연결용 객체
const MongoClient = require('mongodb').MongoClient;

//어떤 DB에 저장할건지
let db;

MongoClient.connect('mongodb+srv://admin:qwer1234@cluster0.uztxq.mongodb.net/?retryWrites=true&w=majority', function (에러, client) {
    
    if(에러){return console.log(에러);}

    //todoapp에 접근하겟다라는 의미
    db = client.db('todoapp');    

    //        
/*     db.collection('post').insertOne({_id:10,이름:'유저1',나이:22},function(){
        console.log('저장완료');
    }); */
    
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

    db.collection('post').insertOne({_id:time,제목:todo,날짜:day},function(){
        console.log('저장완료');
    });
});


//DB 조회시 수행
app.get("/list",function(req,resp){
    resp.render('list.ejs')
});
