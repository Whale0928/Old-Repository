//상세조회 - 목록으로 버튼 -- 게시글 작성.

//즉시 실행 함수
(function(){
    const goToListBtn = document.getElementById("goToListBtn");
    if(goToListBtn != null){//목록으로 버튼이 화면에 있을때만 이벤트 추가
        goToListBtn.addEventListener("click",function(){
    
            const pathname = location.pathname; //요청경로만 반환
            //community/board/detail
            //문자열. subString (시작 , 끝) : 시작 이상 끝 미만 까지 문자열 자르기

            //문자열.indexOf("검색문자열,시작인덱스")
            //문자열에서 검색할 문자열의 위치를 찾아서 반환 
            //이때 시작 인덱스 이후부터 검색 시작.
            let url=  pathname.substring(0,pathname.indexOf("/",1));
           
            url+= "/board/list?" 
            //community/board/list ?


            //URL 내장 객체: 주소관련 정보를 나타내는 객체
            //location.href : 현재 페이지 주소 + 쿼리스트링
            //URL.searchParams : 쿼리스트링만 별도 객체로 반환

            const params = new URL(location.href).searchParams;

            const type = "type="+params.get("type");//type=1
            
            let cp;
            if(params.get("cp")!=''){ // 쿼리 스트링에 cp가 있을 경우.
                cp = "cp="+params.get("cp");//type=1
            }else{
                cp= "cp=1";
            }
            ///community/board/list?type=1
            url+= type+"&"+cp;
            

            //검색 key, Query가 존재하는 경우.
            if(params.get("key")!=null){
                const key = "&key="+params.get("key");
                const query = "&query="+params.get("query");
                url+=key+query;
            }

            //location 객체 == 주소창을 나타내는 객체.
            location.href=url;
        })
    }
})();


// 즉시 실행 함수
(function(){
    const thumbnail = document.getElementsByClassName("list-thumbnail");

    if(thumbnail.length > 0){ // 목록에 썸네일 이미지가 있을 경우에만 이벤트 추가
        const modal = document.querySelector('.modal');
        const modalImage = document.getElementById("modal-image");
        const modalClose = document.getElementById("modal-close");

        for(let th of thumbnail){
            th.addEventListener("click", function(){
                modalImage.setAttribute("src", th.getAttribute("src") );
                // on . off 스위치
                //classList toggle ("클래스") : 클래스가 없으면 추가(add)
                //                            : 클래스가 있으면 제거(remove)
                modal.classList.toggle('show');
            });
        }

        modalClose.addEventListener("click", function(){ //0.45 뒤 수행.
            
            modal.classList.toggle('hide'); //hide 클래스 추가 하고

            setTimeout(function(){ //0.5초후에 클래스 두개 다 제거함.
                modal.classList.toggle('hide');
                modal.classList.toggle('show');
            },450);
        });
    }

})();

/* 즉시 실행 함수 : 성능 좋고 변수명 중복 문제 없고. */
(function deleteBoard(){
    const deleteBtn = document.getElementById("deleteBtn"); // if문으로 인해 없을경우 NULL 

    if(deleteBtn != null){ //삭제 버튼이 화면에 존재할 경우
        deleteBtn.addEventListener("click",function(){
            // /community/board/delete?no=1500&  type = 1
            //                 어느 게시글을 삭제할지 / 어느 게시판인지

            let url = "delete"; //상대경로

            // 1) 쿼리 스트링에 존재하는 모든 파라미터만 얻어오기
            const params = new URL(location.href).searchParams;

            // 2) url에 쿼리스트링 추가
            const no = "?no="+params.get("no");//게시글 얻어오기.
            const type = "&type="+params.get("type");//게시판 종류 얻어오기.
        
            // 3) url에 필요한 parameter들 전부 저장
            url +=no+type;
            
            if(confirm("정말로 삭제하시겠습니까?")){
                location.href=url; //Get방식 요청
            }
        })
    }
})();

/* 검색창에 이전 검색 기록 반영하기*/
(function(){
    const select = document.getElementById("search-key");
    const option = document.querySelectorAll("#search-key>option")

    const input = document.getElementById("search-query");

    if(select != null){ //검색창에 화면에 존재할 때에만 코드 적용
        //현재 주소에서 쿼리스트링 파라미터 얻어오기
        const params = new URL(location.href).searchParams;
        //얻어온 파라미터중 key , query만 변수에 저장
        const key = params.get("key");
        const query = params.get("query");

        //input에 쿼리값 대임
        input.value = query;
        
        //option을 반복 접은해서 value와 key가 같으면 selected 속성 추가
        for(let op of option){
            if(op.value==key){
               op.selected=true;
          }
        }
    }
}
)();

function searchValidate(){
    const input = document.getElementById("search-query");
    if(input.value.trim()==""){
        input.focus();
        input.value="";
        alert("검색어를 입력하세요.")
        return false;
    }

}
