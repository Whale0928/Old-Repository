/* 미리보기 관련 요소들 전부 얻어오기 */
// --> 동일한 개수의 요소가 존재함 == 인덱스가 일치함
const inputImage = document.getElementsByClassName("inputImage");
const preview = document.getElementsByClassName("preview");
const deleteImage = document.getElementsByClassName("delete-image");

/* 미리 보기 동작을 위한 코드 */
/* 배열 요소를 반복 접근하지만 그 요소 내부에서는 i를 잘 생각하고 써야한다. */
for(let i =0; i<inputImage.length; i++){
    /* 인풋 이미지 i번째 요소가 변했을 때. */
    /* ==  파일이 선택 되었을 때 동작*/
    inputImage[i].addEventListener("change",function(){
        if(this.files[0] != undefined){//파일이 선택된 경우
            const reader = new FileReader(); //선택된 파일을 읽을 객체를 생성
            reader.readAsDataURL(this.files[0]); //reader에 result(url 포함)에 저장됨 url을 이용해 이미지 확인 가능
            reader.onload=function(e){
                //e.targer = reader
                //e.target.result == 읽어들인 이미지의 URL
                //preview[i] == 파일이 선택된 input 태그와 인접한 preview 이미지 태그.
                preview[i].setAttribute("src",e.target.result);
            }
        }else{//파일이 선택되지 않았을 때.
            preview[i].removeAttribute("src");
        }
    });
    /* 미리보기 삭제 버튼이 클릭 되었을 때의 동작 */
    deleteImage[i].addEventListener("click",function(){
        //미리보기 삭제.
        preview[i].removeAttribute("src");
        //input 값 빈칸으로 지우기
        inputImage[i].value="";
    })
}

function writeValidate(){
    const boardTitle = document.getElementsByName("boardTitle")[0];
    const boardContent = document.getElementsByName("boardContent")[0];

    if(boardTitle.value.trim().length==0){
        alert("제목을 입력하세요");
        boardTitle.value ="";
        boardTitle.focus();
        return false
    };

    if(boardContent.value.trim().length==0){
        alert("내용을 입력하세요");
        boardContent.value ="";
        boardContent.focus();
        return false
    };

}
