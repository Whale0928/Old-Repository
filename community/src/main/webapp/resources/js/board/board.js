//상세조회 - 목록으로 버튼

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
            const cp = "cp="+params.get("cp");//type=1

            ///community/board/list?type=1
            url+= type+"&"+cp;

            //location 객체 == 주소창을 나타내는 객체.
            location.href=url;
        })
    }
})()


