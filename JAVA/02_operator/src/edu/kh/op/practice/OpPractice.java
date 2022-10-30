package edu.kh.op.practice;
import java.util.Scanner;

public class OpPractice {
	Scanner sc = new Scanner(System.in);
		
		public void practice1() {
			//모든 사람이 사탕을 골고루 나눠가지려고 한다. 인원 수와 사탕 개수를 키보드로 입력 받고
			//1인당 동일하게 나눠가진 사탕 개수와 나눠주고 남은 사탕의 개수를 출력하세요.
			System.out.print("인원 수 입력 : ");
			int pers = sc.nextInt(); //인원(Personnel)
			
			System.out.print("사탕의 개수 입력 : ");
			int cn = sc.nextInt(); //candy
			
			System.out.println("1인당 사탕 개수 : " + cn/pers);
			System.out.println("남은 사탕 개수 : "+cn%pers);
			
			
		
		}
		public void practice2() {
			//키보드로 입력 받은 값들을 변수에 기록하고 저장된 변수 값을 화면에 출력하여 확인하세요.
			System.out.print("이름 : ");
			String name = sc.next(); //이름
			
			System.out.print("학년 : ");
			int sy = sc.nextInt(); //학년 School year
			
			System.out.print("반 : ");
			int cl = sc.nextInt(); //반 class
			
			System.out.print("번호 : ");
			int num1 = sc.nextInt(); //번호 number
			
			System.out.print("성별(남학생/여학생) : ");
			String gen = sc.next(); //성별 gender
			
			System.out.print("점수 : ");
			double scr = sc.nextDouble(); //점수 score 
			
			System.out.printf("%d 학년 %d반 %d번 %s %s 의 성적은 %.2f입니다",sy,cl,num1,name,gen,scr);
		}
		public void practice3() {
			//정수를 하나 입력 받아 짝수/홀수를 구분하세요.
			//(0은 짝수로 취급합니다.)
			
			System.out.print("정수 입력 : ");
			int input = sc.nextInt();
			
			String result = (input%2)== 0 ? "짝수" : "홀수";  
			System.out.println(result + "입니다.");  
			
			
			
		}
		public void practice4() {
			//국어, 영어, 수학에 대한 점수를 키보드를 이용해 정수로 입력 받고,
			//세 과목에 대한 합계(국어+영어+수학)와 평균(합계/3.0)을 구하세요.
			//세 과목의 점수와 평균을 가지고 합격 여부를 처리하는데
			//세 과목 점수가 각각 40점 이상이면서 평균이 60점 이상일 때 합격, 아니라면 불합격을 출력하세요.
			
			System.out.print("국어 : ");  
			int input1 = sc.nextInt();
			System.out.print("영어 : ");
			int input2 = sc.nextInt();
			System.out.print("수학 : ");
			int input3 = sc.nextInt();
			//3 과목들의 점수를 각각 변수에 저장시켰다.
			
			
			int result = input1+input2+input3;
			double sum = result/3;
			//합계 값은 result에 저장
			//평균의 값은 실수형 sum에 저장.
			
			
			System.out.printf("합계: %d \n 평균: %f",result,sum);
		//	System.out.println("합계 :" + result));
		//	System.out.println("평균 : " + sum);
		//	합계와 평균을 출력 각각 호출이 아닌 printf으로 실행하여 Refectoring 할 수 있다.
			
			boolean bool = ((input1>=40)&&(input2>=40)&&(input3>=40)&&(sum >= 60));
			
			System.out.println((bool ? "합격" : "불합격") + "입니다.");
			//합계의 값이 60보타 크면서 평균값의 변수도 true경우 true
		}
}
