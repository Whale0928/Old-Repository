package edu.kh.community.member.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.kh.community.member.model.service.MemberService;

@WebServlet("/member/sendEmail")
public class SendEmailServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	  String inputEmail = req.getParameter("inputEmail"); //입력받은 이메일
		
      String subject = "[Commnity 프로젝트] 회원 가입 이메일 인증번호"; // 제목
      String fromEmail = "admin@HotelLala.com"; // 보내는 사람으로 표시될 이메일
      String fromUsername = "관리자"; // 보내는 사람 이름
      String toEmail = inputEmail; // 받는사람, 콤마(,)로 여러개 나열 가능
	
      // 구글 이메일을 이용한 메일 보내기 (SMTP)
      // 1. 구글 계정 생성(기존 이메일 사용해도됨)
      // 2. 계정 -> 보안 설정 진행
      //  1) 2단계 인증 추가
      //  2) 앱 비밀번호 생성(메일, 서버컴퓨터 OS) -> 저장해두기 (dlzhplgtkqrmibho)
      
      final String smtpEmail = "rlagusrl928@gmail.com"; // 이메일
      final String password = "dlzhplgtkqrmibho"; // 발급 받은 비밀번호


      // 메일 옵션 설정
      Properties props = new Properties();
      
      // 중요
      props.put("mail.transport.protocol", "smtp");
      props.put("mail.smtp.host", "smtp.gmail.com");
      props.put("mail.smtp.port", "587"); //465, 587
      props.put("mail.smtp.auth", "true");

      // 추가 옵션
      props.put("mail.smtp.quitwait", "false");
      props.put("mail.smtp.socketFactory.port", "587");
      props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
      props.put("mail.smtp.socketFactory.fallback", "true");
      props.put("mail.smtp.starttls.enable", "true");

      
      try {
         // 메일 세션 생성
         Session session = Session.getDefaultInstance(props);

         // 메일 송/수신 옵션 설정(1명 보내기)
         Message message = new MimeMessage(session);
      
         message.setFrom(new InternetAddress(fromEmail, fromUsername));    // 송신자(보내는 사람) 지정
         
         message.addRecipient(RecipientType.TO, new InternetAddress(toEmail)); // 수신자(받는사람) 지정
         
         message.setSubject(subject); // 이메일 제목 지정
         
         
         
         // 메일 콘텐츠 설정
         Multipart mParts = new MimeMultipart();
         MimeBodyPart mTextPart = new MimeBodyPart();

         //인증번호 6자리 코드 
         String cNumber = "";
         for(int i=0 ; i< 6 ; i++) {
            
            int sel1 = (int)(Math.random() * 3); // 0:숫자 / 1:영어
            
            if(sel1 == 0) {
               
               int num = (int)(Math.random() * 10); // 0~9
               cNumber += num;
               
            }else {
               
               char ch = (char)(Math.random() * 26 + 65); // A~Z
               
               int sel2 = (int)(Math.random() * 2); // 0:소문자 / 1:대문자
               
               if(sel2 == 0) {
                  ch = (char)(ch + ('a' - 'A')); // 대문자로 변경
               }
               
               cNumber += ch;
            }
            
         }
         
         
         // 메일에 출력할 텍스트
         StringBuffer sb = new StringBuffer(); // 가변성 문자열 저장 객체
         sb.append("<h3>[Community 프로젝트] 회원 가입 인증 번호입니다.</h3>\n");
         sb.append("<h3>인증 번호 : <span style='color:red'>"+cNumber+"</span></h3>\n");
         sb.append("<img src='https://thumbs.gfycat.com/DearDeadlyLeonberger-size_restricted.gif'>");
         

         String mailContent = sb.toString(); // 문자열로 반환
         
         // 메일 콘텐츠 - 내용 , 메일인코딩, "html" 추가 시 HTML 태그가 해석됨
         mTextPart.setText(mailContent, "UTF-8", "html");
         mParts.addBodyPart(mTextPart);

         
         // 메일 콘텐츠 지정
         message.setContent(mParts);

         
         
         // MIME 타입 설정 (이메일 내용이 깨질 때 사용)
         /*MailcapCommandMap MailcapCmdMap = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
         MailcapCmdMap.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html");
         MailcapCmdMap.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml");
         MailcapCmdMap.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain");
         MailcapCmdMap.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed");
         MailcapCmdMap.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822");
         CommandMap.setDefaultCommandMap(MailcapCmdMap);*/

         // 메일 발송
         Transport t = session.getTransport("smtp");
         t.connect(smtpEmail, password);
         t.sendMessage(message, message.getAllRecipients());
         t.close();

         //인증번호를 받는 이메일, 인증번호가 뭔지 , 언제 발급했는지 DB에 저장
         int result = new MemberService().insertCertification(inputEmail,cNumber);
         
         resp.getWriter().print(result);

      } catch (Exception e) {
         e.printStackTrace();
         
         // ajax error 속성 활용을 위한 500에러를 응답으로 전달.
         resp.setStatus(500);
         resp.getWriter().print(e.getMessage());
      }
	}
}
