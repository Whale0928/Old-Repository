package edu.kh.io.model.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOService {

	// IO

	// input(입력) : 외부에서 내부로 데이터를 들어오는 것.
	// Output(출력) : 내부에서 외부로 데이터를 내보내는 것.

	// Stream : 입/출력 통로 역활 (데이터가 흘러 들어가는 통로)
	// 기본적으로 Stream은 단방향이다.

	// 1) 파일 출력(내부 == 프로그램, 외부 == 파일)
	public void output1() {
		FileOutputStream fos = null;
		// FileOutputStream 객체 생성 시
		// FileNotFoundException

		try {

			fos = new FileOutputStream("test1.txt");
			// 현재 프로그램에서
			// test1.txt파일로 출력하는 통로객체를 생성한다.

			// file에 "Hello" 내보내기
			String str = "헬로";

			for (int i = 0; i < str.length(); i++) {
//				System.out.println(str.charAt(i));

				// "Hello" 한문자씩 끊어서 파일로 출력하기
				fos.write(str.charAt(i));
//				write는 IOException을 발생 시킬 가능성이 있다.
			}

		} catch (IOException e) {
			System.out.println("예외 발생");
			e.printStackTrace(); // 예외추적.
		} finally {
			// 예외가 발생하든 말든 무조건적으로 수행

			// 사용한 스트림 자원 반환 (통로 없앰)
			// 프로그램 메모리 관리 차원에서 항상 다쓰면 끊어줌
			// - > 작성 안하면 문제점으로 꼽을 수 있다.

			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// 문자기반 스트림
	public void output2() {
		FileWriter fw = null; // 프로그램에서 파일로 쓰는 문자 기반 스트림
	

		try {

			fw = new FileWriter("test1.txt"); // 외부 파일과 연결하는 스트림 객체 생성.
			String str = "안녕하세요 .  Hello , 12345 !";
			
//			fw.write(int c);  : 한 문자씩
//			fw.write(String s);  : 한 줄씩
			
			fw.write(str);
			//실행 했는데 안 내보내진다.
			//한 줄을 통째로 보내기 위해 "버퍼"를 사용한다.
			//아직 버퍼에 담겨있다. //강제로 밀어 넣어서 버퍼를 비워야한다.
			//close() 구문을 실행하면 스트림에 남아있는 모든 내용을 내보내고 통로를 없앤다.
			
		} catch (IOException e) {
			e.printStackTrace(); //예외 추적
		} finally {
			 try {
				fw.close();
			} catch (IOException e) {
					e.printStackTrace();
			}
		}

	}

	//3) 파일 입력 : 외부(파일)에서 내부(프로그램)로 읽어오기
	public void input1() {
		FileInputStream fis = null; //file to program  at byte basic Stream
		
		try {
			fis = new FileInputStream("test1.txt");
			
			// File Input Stream  1바이트 씩만 읽어올 수 있다.
			while(true) {
				int data = fis.read(); //다음 1byte를 읽어오는 정수형임.
									   //다음 내용이 없으면 -1 반환.
				if(data == -1) { //다음 내용 없음 => 종료
					break;
				}
				System.out.print((char)data);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public void input2() {
		
		FileReader fr = null;

		try {

			fr = new FileReader("text1.txt");
			
			while(true) {
				int data = fr.read(); //다음 한 문자를 읽어오고 없으면 -1반환
				
				if(data== -1) {
					break;
				}else {
					System.out.print((char)data);
				}
			
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			try {
				fr.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
