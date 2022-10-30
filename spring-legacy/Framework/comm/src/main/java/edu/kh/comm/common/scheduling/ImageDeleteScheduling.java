package edu.kh.comm.common.scheduling;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import edu.kh.comm.board.model.service.BoardService;

@Component
public class ImageDeleteScheduling {

	private Logger logger = LoggerFactory.getLogger(ImageDeleteScheduling.class);
	

	
	//BoardIMG 테이블에서는 삭제 되었으나 
	//서버 /resources/images/board 폴더에는 존재하는
	// 이미지 파일을 매 정시마다 삭제.
	
	//순서
	//1) BOARD_IMG 존재하는 모든 파일 목록을 조회
	//2) /resouces/images/board 폴데에 존재하는 모든 이미지 파일 목록 조회
	
	//3) 두 목록을 비교해 일치하지 않는 이미지 파일을 삭제
	
	@Autowired
	private BoardService service;
	@Autowired
	private ServletContext application;//application scope 객체 -> 서버 폴더 경로 얻어오기에 사용
	
	//스케줄링에 사용되는 모든 함수는 public  + void + 매개변수X 이여야만 한다.
	
	@Scheduled(cron = "0 0 * * * *") //원래는 매 정시 마다 하지만 테스트 때문에 분마다로 변경.
//	@Scheduled(fixedDelay=10000) 
	public void servcerImageDelete() {
		
		//1) BOARD_IMG 존재하는 모든 파일 목록을 조회
		List<String> dbList = service.selectDbList();
		
		
		//2) /resouces/images/board 폴데에 존재하는 모든 이미지 파일 목록 조회
		String folderPath = application.getRealPath("/resources/images/board");

		// /resouces/images/board 폴더를 참조하는 객체 생성
		File path = new File(folderPath);
		
		File[] arr = path.listFiles(); //path가 참조 하고 있는 모든 파일을 얻어와 File 배열로 반환
		List<File> serverList = Arrays.asList(arr);
		
		//3) 두 목록을 비교해 일치하지 않는 이미지 파일을 삭제
		if(!serverList.isEmpty()) { //서버에 저장되어 있는 파일들이 있을때만.
			
			for(File serverImage :serverList){
				//서버에 저장된 파일명만 얻어올 수 가 있다.
				String name = "/resources/images/board/"+serverImage.getName();
				
				/*
				 * for(String dbImage:dbList) { if(dbImage.equals(name)) { } }
				 */
				
				//indexOf(value) : List value와 같은 값이 있으면 인덱스를 반환 없으면 -1반환
				//contains :포함하면 true 아니면 false
				if(dbList.indexOf(name)==-1){
					logger.info(serverImage.getName()+"을 삭제하였습니다.");
					serverImage.delete();
				}
			}
			
			logger.info("------------서버 이미지들을 삭제하였습니다.----------------");
			
		}
		
	}
	
}
