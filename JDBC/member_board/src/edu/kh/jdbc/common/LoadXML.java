package edu.kh.jdbc.common;

import java.io.FileInputStream;
import java.util.Properties;

public class LoadXML {

	public static void main(String[] args) {
		
		//외부 XML 읽어오기 ( Properties 사용 ) 
		
		Properties prop = new Properties(); 
		//key , value가 String으로 제한된 Map
		
		try {			
			prop.loadFromXML(new FileInputStream("driver.xml"));
			
			//Property : 속성
			//prop.getProperty("driver") : xml에서 얻어온 값중 key가 "Driver"인 entry 의 value를 얻어옴
			System.out.println("driver  : "+prop.getProperty("driver"));
			
			
			//Java는 코드가 한 글자라도 변환되면
			//다시 처음부터 전체 내용 컴파일(이진 코드 번역)한다 -> 비효율적
			
			//그런데 , Java에 외부파일을 읽어오는 변하지않는 코드를 작성 시
			//컴파일을 다시 하지 않는다 - > 효율적
			
			//컴파일 하지 않아도 외부파일의 내용이 변하면 자동으로 반영된다.
			
			//DB 연결정보 , SQL내용은 빈번히 변화할 예정
			// 1)java 코드에 직접 작성 시 -> 다시 컴파일- > 실행(비효율적)
			// 2)xml에 작성시 -> 바로 실행 (효율적)
			//		+추가 효과 db정보랑 sql을 한곳에 모아 관리(가시성 향상 , 관리편의성 향상)
			
		}catch(Exception e) {
			e.printStackTrace();
		}
			
		System.out.println(JDBCTemplate.getConnection());

	}

}
