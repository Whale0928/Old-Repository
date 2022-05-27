package edu.kh.network.model.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPServer {

	// Server : 서비스를 제공하는 프로그램 또는 컴퓨터.
	// Client : 서버에 서비스를 요청하여 사용하는 프로그램 또는 컴퓨터

	// TCP Socket Programming

	// TCP : 데이터 전달의 신뢰성을 최대한 보장하기 위한 방식.(연결 지향형 통신)
	// 순차적으로 데이터를 전달하고. 확인 및 오류시 재전송.

	// Socket : 프로세스(Process , 프로그램과 비슷한거.)의 양끝단
	// 서버와 클라이언트가 통신을 하기 위한 매개체

	// ServerSocket : 서버용 소켓

	public void serverStart() {
//		1. 서버의 포트번호 정함
		int port = 8500; // port는 0 ~ 65535사이 지정 가능 **1023이하는 사용중인 경우가 많다.**

		// **사용할 변수 미리 선언**
		ServerSocket serverSocket = null; // 서버 소켓 생성
		Socket clientSocket = null; // 클라이언트 소켓

		InputStream is = null; // 클라이언트 - > 서버 입력용 스트림 변수
		BufferedReader br = null; // 입력용 보조 스트림

		OutputStream os = null; // 서버에서 -> 클라이언트로 출력용
		PrintWriter pw = null; // 출력용 보조 스트림 변수

		try { // 2~7번까지 try로 감쌈
//		2. 서버용 소켓 객체 생성
			serverSocket = new ServerSocket(port);
			// 서버용 소켓을 생성하여 결합

//		3. 클라이언트 쪽에서 접속 요청이 오길 기다림
//			서버용 소켓은 생성되면 클라이언트 오기 전까지
//			다음코드를 수행하지 않고 대기하고 있다.
			System.out.println("[Server]");
			System.out.println("클라이언트의 요청을 대기중입니다.");

//		4. 접속 요청이 오면 요청 수락 후 해당 클라이언트에 대한 소켓 객체가 얻어와진다.
			clientSocket = serverSocket.accept();
//			 접속한 클라이언트 IP를 얻어와 출력.
			String clientIP = clientSocket.getInetAddress().getHostAddress();
			System.out.println(clientIP + "에서 연결을 요청합니다...");

//		5. 연결된 클라이언트와 입출력 스트림 생성
			is = clientSocket.getInputStream(); // 클라이언트로부터 읽어오는 스트림
			os = clientSocket.getOutputStream(); // 클라이언트로 보내는 스트림

//		6. 보조 스트림을 통해 성능 개선
			br = new BufferedReader(new InputStreamReader(is));
//		 문자 기반 스트림과 바이트 기반 스트림 연결에 사용되는 스트림

			pw = new PrintWriter(os);

//		7. 스트림을 통해 읽고 쓰기
//		 7-1) 서버 to 클라이언트에게 출력(메세지 전송)
			Date now = new Date();  //생성된 시간을 기록하고 있는 시간 관련 객체.
//										날짜 출력 형식 변경이 필요함
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-= dd HH:mm:ss");
			String time = sdf.format(now);
			
			pw.println(time+" 서버 접속 성공.");
			pw.flush(); // Stream에 내용을 모두 밀어낸다.

//		 7-2) 클라이언트 to 서버에게 입력(메세지 전송받기)
			String message = br.readLine();
			System.out.println(clientIP + "가 보낸 메세지입니다 : " + message);
		} catch (IOException e) {
			e.printStackTrace();// 예외 추적용
		} finally {
//		8. 통신 종료 (File.close)을 쓸 부분
//			사용한 스트림, 소켓 자원을 모두 반환(Close)
			try {
				//보조 스트림 close시 연결된 기반 스트림 (is ,os)도 같이 close된다.
				if (pw != null)	pw.close();
				if (br != null)br.close();

				if (serverSocket != null)	serverSocket.close();
				if (clientSocket != null)	clientSocket.close();
				// if문에 중괄호{}을 사용안하면 다음 한줄(세미콜론 기준) 대해서만 if내부 코드가 된다.

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
