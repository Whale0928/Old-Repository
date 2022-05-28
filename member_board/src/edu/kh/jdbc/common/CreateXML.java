package edu.kh.jdbc.common;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class CreateXML {
	// XML (eXtensible MarkUp Language) : 단순화된 데이터 기술 형식

	// XML을 쓰는 이유
	// -DB연결 sql같이 수정이 빈번한 내용을
	// 코드에 직접 작성시 좋지 않음.
	// 왜 안좋은가 : JAVA == 컴파일 언어 == 코드가 조금만 수정되고 전체 컴파일을 다시함. (시간이 오래걸림)

	// howEver XML외부 파일을 이용해 xml 내용을 바꿔도
	// Java에서 XML 파일을 읽어오는 코드는 변하지 않음 -> 컴파일 x ->시간효율 상승

	public static void main(String[] args) {
		// XML 은 key : Value 형식 Map, XML은 문자열만 저장

		// Map<String,String> == Properties
		// Properties 컬렉션 객체
		// 1. String String으로 key , Value가 타입 제한된 Map
		// 2. xml 파일을 생성 / 읽는데 특화 되어 있다.

		Properties prop = new Properties();

		try {
			FileOutputStream fos = new FileOutputStream("board-sql.xml");
			// 내보낼 파일의 이름
			prop.storeToXML(fos, "board Service SQL"); // xml 파일 생성

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
}
