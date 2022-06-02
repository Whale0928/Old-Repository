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
            if(params.get("cp")!=null){ // 쿼리 스트링에 cp가 있을 경우.
                cp = "cp="+params.get("cp");//type=1
            }else{
                cp= "cp=1";
            }

            ///community/board/list?type=1
            url+= type+"&"+cp;

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