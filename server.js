const express = require('express');
const app = express();

app.listen(5000, function () {
    console.log("Run on Server 5000");
});


/* app.get(" 경로 " , function(){}); */

app.get("/pet", function (req, resp) {
    resp.send("펫용품 ");
});


//DB에서 리
MongoClient.connect('mongodb+srv://admin:qwer1234@cluster0.uztxq.mongodb.net/?retryWrites=true&w=majority', function (에러, client) {
    db = client.db("ChatDB");
    db.collection('Chat').insertOne({ 작성자: name, 내용: msg, 작성일: moment(new Date()).format("h:ss A") }, function (에러, 결과) {
        console.log('저장완료');
    });

    if (에러) { console.log(에러); }
    console.log('Connection on DB');

})