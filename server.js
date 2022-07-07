const express = require('express');
const app = express();
const path = require("path");// 서버 경로를 저장한 변수

const moment = require("moment");
time = moment();
time.format("HH:mm:ss");	// 2021.10.09 00:09:45

const http = require('http').createServer(app);
const { Server } = require("socket.io");
const io = new Server(http);

//  Request 객체 해석용 
const bodyParser = require('body-parser');
app.use(bodyParser.urlencoded({ extended: true }));


//EJS등록을 위한 코드
app.set('view engine', 'ejs');

//정적 파일들 처리용 
app.use('/resource/css/', express.static(__dirname + "/resource/css/"));
app.use('/resource/js/', express.static(__dirname + "/resource/js/"));

//환경변수 저장용  env 파일
require('dotenv').config();

//로그인용
const passport = require('passport');
const LocalStrategy = require('passport-local').Strategy;
const session = require('express-session');

//나는 '미들웨어'를 쓰겟습니다
//요청 <-> 미들웨어 <-> 
app.use(session({ secret: '비밀코드', resave: true, saveUninitialized: false }));
app.use(passport.initialize());
app.use(passport.session())




//Mongo DB 연결용 객체
const MongoClient = require('mongodb').MongoClient;

//어떤 DB에 저장할건지
let db;

MongoClient.connect(process.env.COMMON_DB_URL, function (에러, client) {
    if (에러) { return console.log(에러); }
    //todoapp에 접근하겟다라는 의미
    //db = client.db('todoapp');
    db = client.db('ChatDB');
    // 서버 구동
    http.listen(process.env.PORT, function () {
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

//글번호 달기 기능 추가



//DB 조회시 수행
app.get("/list", function (req, resp) {
    //데이터 가져오기
    db.collection('post').find().toArray(function (에러, 결과) {
        if (에러) return console.log(에러);
        console.log(결과);
        resp.render('list.ejs', { posts: 결과 });
    });
});

app.get('/detail/:id', function (요청, 응답) {
    db.collection('post').findOne({ _id: parseInt(요청.params.id) }, function (에러, 결과) {
        console.log(결과);
        응답.render('detail.ejs', { data: 결과 });
    })

});

app.get('/login', function (req, resp) {
    resp.render('login.ejs');
})


//로그인 기능
app.post('/login', passport.authenticate('local', {
    failureRedirect: 'fail'//로그인 실패하면 fail으로 리다이렉트 시킨다.
}), function (req, resp) {


    resp.redirect('/');
})

//로그인 인증
passport.use(new LocalStrategy({
    usernameField: 'id',
    passwordField: 'pw',
    session: true,
    passReqToCallback: false,
}, function (입력한아이디, 입력한비번, done) {
    //console.log(입력한아이디, 입력한비번);
    db.collection('login').findOne({ id: 입력한아이디 }, function (에러, 결과) {
        if (에러) return done(에러)


        //done ( 서버에러 ,결과를보내기 위해 , 에러 메세지.   )   
        if (!결과) return done(null, false, { message: '존재하지않는 아이디요' })
        if (입력한비번 == 결과.pw) {

            return done(null, 결과)

        } else {

            return done(null, false, { message: '비번틀렸어요' })
        }
    })
}));

//세션에 저장시키는 방법
passport.serializeUser(function (user, done) {
    done(null, user.id)
});
//세션에서 찾을때 사용되는 함수.
passport.deserializeUser(function (아이디, done) {
    db.collection('login').findOne({ id: 아이디 }, function (에러, 결과) {

        //디비에서 user.id (test) 로 유저를 찾은 뒤 유저 정보를 
        done(null, 결과)//여기 중괄호 안에 넣음
    })

});




//마이페이지 접속
app.get('/myPage', 로그인했니, function (요청, 응답) {
    console.log(요청.user);
    응답.render('myPage.ejs', { 사용자: 요청.user });
})

function 로그인했니(요청, 응답, next) {
    if (요청.user) {
        console.log(요청.user);
        next()
    } else {
        응답.send("로그인하세요");
    }
}


app.post('/register', function (요청, 응답) {
    //아이디 중복 검사
    db.collection('login').insertOne({ id: 요청.body.id, pw: 요청.body.pw }, function (에러, 결과) {
        응답.redirect("/");
    });
})




//할일 작성 시 수행
app.post("/add", function (req, resp) {
    resp.send("전송 완료");
    let todo = req.body.title;
    let day = req.body.date;

    db.collection('counter').findOne({ name: '게시물 갯수' }, function (에러, 결과) {


        if (에러) return console.log(에러);
        console.log(결과.totalPost);
        let 총게시물갯수 = 결과.totalPost

        let data = { _id: 총게시물갯수, 제목: todo, 날짜: day, 작성자: req.user._id };

        db.collection('post').insertOne(data, function () {
            console.log('저장완료');
            db.collection('counter').insertOne({ name: '게시물 갯수' })

            //db.collection("counter").updateOne({어떤 데이터를 수정할 지},{수정 할 값},function(){});
            db.collection("counter").updateOne({ name: '게시물 갯수' }, { $inc: { totalPost: 1 } }, function (에러, 결과) {
                //연산자 {$set:{totalPost: 바꿀 값}}
                //연산자 {$inc:{totalPost: 기존 값에 누적해줄 값}}
                //데이터 증가 후 수행
                if (에러) { return console.log(에러); }
            });
            //게시글을 작성 하고 카운트를 증가 시킨다.
        });
    });


});



//삭제
app.delete('/delete', function (요청, 응답) {
    요청.body._id = parseInt(요청.body._id);
    //요청.body에 담겨온 게시물번호를 가진 글을 db에서 찾아서 삭제해주세요
    //let 삭제할데이터 = {_id:요청.body._id,작성자:요청.user._id}

    db.collection('post').deleteOne({ _id: 요청.body._id, 작성자: 요청.user._id }, function (에러, 결과) {
        console.log('삭제완료');
        console.log('에러', 에러)
        응답.status(200).send({ message: '성공했습니다' });
    })
});




//채팅 연결
const { ObjectId } = require('mongodb');

app.post('/chatroom', 로그인했니, function (요청, 응답) {

    console.log(요청.body);
    var 저장할거 = {
        title: '무슨무슨채팅방',
        member: [ObjectId(요청.body.당한사람id), 요청.user._id],
        date: new Date()
    }

    db.collection('chatroom').insertOne(저장할거).then(function (결과) {
        응답.send('저장완료')
    });
});

//채팅 페이지로 이동
app.get('/chat', 로그인했니, function (요청, 응답) {
    // 배열로 저장되어 있어도 하나만 찾아도 됨
    console.log("채팅방 접속 : " + 요청.user._id);
    db.collection('chatroom').find({ member: 요청.user._id }).toArray().then((결과) => {
        console.log(결과);
        응답.render('chat.ejs', { data: 결과, loginUserID: 요청.user._id });
    })
});
//${JSON.stringify(요청.user)}

app.post('/message', 로그인했니, function (요청, 응답) {
    let 저장할거 = {
        parent: 요청.body.parent,
        userid: 요청.user._id,
        content: 요청.body.content,
        date: new Date(),
    }

    db.collection('message').insertOne(저장할거).then((결과) => {
        console.log('DB 저장 성공');
        응답.send(결과);

    })
})




//-------------------------------------------------------------------------------------------------------------------------
app.get("/message/:id", 로그인했니, function (요청, 응답) {
    응답.writeHead(200, {
        "Connection": "keep-alive",
        "Content-Type": "text/event-stream",
        "Cache-Control": "no-cache",
    });

    db.collection('message').find({ parent: 요청.params.id }).toArray().
        then((결과) => {

            응답.write('event: test\n');
            응답.write(`data:${JSON.stringify(결과)}\n\n`);
        })


    const 찾을문서 = [
        { $match: { 'fullDocument.parent': 요청.params.id } }
    ];


    const changeStream = db.collection('message').watch(찾을문서);
    changeStream.on('change', result => {
        console.log(result.fullDocument);
        var 추가된문서 = [result.fullDocument];
        console.log("추가된 채팅들\n" + 추가된문서);
        응답.write('event: test\n');
        응답.write(`data: ${JSON.stringify(추가된문서)}\n\n`);
    });


});






app.get('/socket', function (요청, 응답) {
    응답.render('socket.ejs');
})

io.on('connection', function (socket) {
    console.log('소켓 접속');

    /* 어떤 유저가 user-send라는 이벤트를 보내면 함수 발생 */
    socket.on('user-send', function (data) {
        console.log(" 소켓 아이디  " + socket.id);
        console.log(data);
        io.emit('broadcast', data);
    })
    socket.on('send', function (data) {
        console.log(" 소켓 아이디  " + socket.id);
        console.log(data);
        io.to().emit('msg', data);
    })
})





//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------
//-------------------------------------------------------------------------------------------------------------------------


//채팅방 접속 URL
app.get("/weChat", function (req, resp) {
    console.log("Get요청입니다.");
    return false;
});
app.post("/weChat/list", function (req, resp) {
    MongoClient.connect(process.env.COMMON_DB_URL, function (에러, client) {
        if (에러) { return console.log(에러); }
        db = client.db('ChatDB');
        const member = JSON.parse(req.body.member);
        console.log(member.memberNo);
        console.log(member.memberEmail);
        //나중에 프로필 이미지 연결할거. 
        //console.log('http://localhost:8080/comm'+member.profileImage); 

        if (member != null) {//{ memberNo: member.memberNo }
            db.collection('chatroom').find({ memberEmail: member.memberEmail }).toArray().then((결과) => {
                console.log(결과);
                resp.render('weChat.ejs', { data: 결과, member: member });
            })
        }
    }) //DB 연결
});

app.post("/weChat/add", function (req, resp) {
    MongoClient.connect(process.env.COMMON_DB_URL, function (에러, client) {
        if (에러) { return console.log(에러); }


        const member = JSON.parse(req.body.member);
        const targetMember = JSON.parse(req.body.targetMember);


        const findEmails = [
            member.memberEmail,
            targetMember.memberEmail
        ]
        console.log(member);
        console.log(targetMember);

        //채팅방에 대상과의 채팅이 있는지 먼저 확인        
        db.collection('chatroom').findOne({ memberEmail: findEmails }, function (에러, 결과) {
            console.log(결과);
            
            if (결과 == null) {
                console.log('채팅방 생성');
                //DB에 삽입



                const 집어넣을값 = {
                    memberEmail: [member.memberEmail, targetMember.memberEmail],
                    memberNickname: [member.memberNickname, targetMember.memberNickname],
                    creatRoomDate:new Date()
                }
                //채팅방 없으면 채팅방 개설해줌
            db.collection('chatroom').insertOne(집어넣을값, function (에러, 결과) {
                console.log('DB저장 완료');
                console.log(결과);
            });

            } else {
               
            }


            db.collection('chatroom').find({ memberEmail: member.memberEmail }).toArray().then((결과) => {
                console.log(결과);
                resp.render('weChat.ejs', { data: 결과, member: member });
            })


        })



    }) //DB 연결
});