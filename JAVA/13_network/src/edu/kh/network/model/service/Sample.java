package edu.kh.network.model.service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Sample{
	
	//서버를 시작하는 메소드
	public void serverStart() {
		int port = 8500; //포트 번호를 8500으로 하나 잡는대
		
		//서버와 클라이언트의 소켓을 설정 
		//클라이언트 소켓은 Socket
		ServerSocket serverSocket= null;
		Socket clientSocket = null;
		
		//클라이언트한태 값을 가져오는 변수
		BufferedReader br = null;
		InputStream is = null;
		
		//클라이언트한태 값을 보내는 변수
		PrintWriter pw =null;
		OutputStream os =null;
		
		try {//입출력 IO는 checked이기 떄문에 무조건 예외처리를 해야한다.
			
			//서버소켓에 포트번호를 생성후 결합시킨다.
			serverSocket = new ServerSocket(port);
			
			//클라이언트가 접속요청 전 까지  대기
			System.out.println("서버대기중....");
			
			//접속 요청이 허락되면 클라이언트에 대한 소켓 객체가 얻어와져 clientSocket에 저장된다.
			clientSocket = serverSocket.accept();
			//접속한 Client Ip주소 얻어온후 문자열에 저장해 출력한다.
			String clienIP = clientSocket.getInetAddress().getHostAddress();
			System.out.println("IP 주소 : "+clientSocket);
			
			//입력스트림을 만든다.
			is = clientSocket.getInputStream();
			//출력스트림을 만든다.
			os = clientSocket.getOutputStream();
			 
			br = new BufferedReader(new InputStreamReader(is));
			pw = new PrintWriter(os);
			
			Date now = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("mm-dd HH:mm:ss"); 
			
			String time = sdf.format(now);
			pw.println("접속 성공 시간 : "+time);
			pw.flush(); //메시지 출력후 밀어내기.
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
}