package edu.kh.control.loop;

import java.util.Scanner;

public class ForExample {
	/*
	 * for 문
	 * 	-끝이 정해져 있는 (반복의 횟수가 이미 정해져있는 반복문)
	 * 	
	 * [작성법]
	 * 
	 *  for(초기식 ; 조건식 ; 증감시){
	 *  	
	 *  	반복 수행할 코드.
	 * 
	 *  }
	 *  
	 *  -초기식 : for문을 제어하는 용도의 변수 선언.
	 *
	 *  -조건식 : for문의 반복 여부를 지정하는식 (다음반복 진행함?)
	 *  			'보통' 초기식에 사용된 변수를 사용하여 조건식을 작성한다.
	 *  			true or false만 사용가능.
	 *  	
	 *  -증감식 :  초기식에 사용된 변수를
	 *  			for문이 끈날 때 마다 증가 또는 감소 시켜
	 *  			조건식의 결과를 변하게 하는식.
	 *  
	 *  	 * */

	public void ex1() {
			//for문 기초 사용법 1 
			//1~10까지 출력하기.
		
			//1부터 10까지 1씩 증가하면서 출력
		
		
		// 반복문은 조건식이 true일 때만 반복.
		//1 2 3 4 5 수행후 5~7 한무 반복
		for(int i = 1; i<=3; i++) {
		//1)초기식 ; 2)5)조건식; 4)7)증감식
			
			//3)6) 수행할 코드
			System.out.println(i);
		}
	}
	public void ex2() {
		//for 기초  사용법 2
		//3에서 7까지 1씩 증가하면서 출력
					// i 값이 7 이하 일 때 true가 되게 하는 조건식
		//3,4,5,6,7
		
		for(int i =3; i<=7; i++) {
			System.out.println(i+"번 반복");
		}
	}
	public void ex3() {
		//2부터 15까지1씩 증가.
		for(int i=2; i<=15; i++) {
			System.out.println(i);
		}
	}
	public void ex4() {
		Scanner sc = new Scanner(System.in);
		System.out.print("max Number : ");
		int input = sc.nextInt();
		
		for(int i = 1 ; i<=input; i++ ) {
			System.out.println(i+ " - 출력");
		}
	}
	public void ex5() {
		//1부터 입력받은 수 까지 2씩 증가하며 출력
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Last Number : ");
		int input = sc.nextInt();
		
		for(int i =0; i<=input;i += 2) {
							//기존 i변수에 I+2를 대입한다.
			System.out.println(i+"_출력");
		}
		
	}	
	public void ex6() {
		//1부터 입력받은 수 까지 0.5
		
		Scanner sc =new Scanner(System.in);
		System.out.print("last number : ");
		double input = sc.nextDouble();
		
		for(double i = 1; i<=input; i+=0.5) {
			System.out.println(i+"_출력");
		}
		
	}
	public void ex7() {
		//Scanner sc =new Scanner(System.in);
		
		for(int i = 'A'; i<='Z'; i++) {
			
			System.out.print((char)i +"  " );
		}
		
		System.out.println("\n==================");
		
		for(char i = 'A'; i<='Z';i++) {

			System.out.print(i +"  " );
		}
	}
	public void ex8() {
		//10에서 1까지 1씩 감소하면서 출력.
		
		for(int i = 10; i >=0; i--) {
			System.out.println(i);
		}
	}
	public void ex9() {
		//for문 응용1번 : 반복문을 이용한 값의 누적
		int sum = 0;
		//반복되어 증가되는 i값의 합계를 저장할 변수
		//0으로 시작하는 이유 : 아무것도 더하지 않음으로 정확하게 결과를 도출 할 수 있기 떄문에.
		
		for(int i = 1; i <=10; i++) {
			sum+=i;
		}
		System.out.println("합계는:"+sum);
		
		
	}
	public void ex10() {
		//for문 응용 : 정수 5개를 입력 받아서 합계 구하기
		
		// ex) 
		// 입력 1: 10
		// 입력 2: 20
		// 입력 3: 30
		// 입력 4: 40
		// 입력 5: 50
		
		Scanner sc =new Scanner(System.in);
		
		int sum=0; // for문 밖에 존재하는 변수로
					// for문이 종료해도 사용가능하다.
		
		
		for(int i =1; i<=5;i++) {

			
			System.out.printf("입력"+i+" :");
			int input = sc.nextInt();   
			//{ 중괄호 } 내부선언은 내부에서만 사용가능하다.
			// {}가 끈나는 시점에 사라지기 때문에.
			// 변수의 생명주기
			
			sum += input; //sum에 입력받은 input값을 누적 시킨다.
			
			
			
		}
		System.out.println(sum);
	}
	public void ex11() {
		Scanner sc = new Scanner(System.in);
		
		//정수를 몇번 입력 받을지 먼저 입력 받고
		//입력된 정수의 합계를 출력해라
			
		System.out.print("입력받을 정수를 입력 : ");
		int input1 = sc.nextInt();
		int sum = 0;
		
		for(int i=1; i<=input1; i++) {
			System.out.print("입력"+i+":");
			int input2 = sc.nextInt();
			sum += input2;
		}
		
	System.out.println("합계 : " + sum);
	}
	public void ex12() {
		Scanner sc =new Scanner(System.in);
		
		// 1부터 20까지 1씩 증가하면서 출력
 		// 단 , 5의 배수에는 (괄호를) 붙여서 출력을 하겟다
		//1 2 3 4 (5) 6 7 8 9 (10)
		
		for(int i = 1; i <= 20; i++) {
			if(i%5==0) {
				System.out.printf("( %d )   ",i);
			}else {
				System.out.print(i+"   ");	}	
		}
	}
	public void ex13() {
		//1~20까지 출력
		//단 
		Scanner sc =new Scanner(System.in);
		System.out.print("괄호를 표시할 배수 : ");
		int num = sc.nextInt();
		
		
		for(int i=1;i<=20;i++ ) {
			if(i % num == 0) {
				System.out.printf("(%d)   ",i);
			}else {
				System.out.print(i+"   ");
			}
		
	}
	
	
	}
	public void ex14() {
		//2~9사이의 수를 입력 받아 
		//해당 단을 출력
		
		Scanner sc = new Scanner(System.in);
		System.out.print("2~9사이의 수 입력: ");
		int input=sc.nextInt();
		
		if(1>=input || input>=10){
			System.out.println("2 ~ 9사이의 수를 입력하세요 : ");
		}else{
			for(int i=1;i<=9;i++) {
				System.out.printf("%d * %d = %d\n",input,i,input*i);
			}
		}
	}
	public void ex15() {
		//19단 출력시
		
		Scanner sc = new Scanner(System.in);
		System.out.print("2부터 19사이의 수 입력 : ");
		int input = sc.nextInt();
		
		if(1>=input || input >=20) {
			 System.out.println("값을 제대로 입력하시오.");
			
		}else {
			for(int i=1; i <=19; i++) {
				System.out.printf("%2d * %2d = %3d \n",input,i,input*i);
			}
		}
	}
	public void ex16() {
			//중첩 반복문
			//모든 구구단 만들기
		for(int dan=2; dan <=9; dan++ ) {
			for(int num=1; num<=9; num++ ){
				 System.out.printf("%2d * %2d = %4d\n",dan,num,dan*num);
				 }
			 System.out.println("-------------");
		}
		
	}
	public void ex17() {
		//구구단 역순 출력
		//9단~2단 까지 출력
		//곱해지는 수는 1 > 9
		
		for(int dan=9; dan>=1; dan--) {
			for(int num=1; num<=9; num++) {
				System.out.printf("  %2d X%2d = %3d",num,dan,dan*num);
			}
			System.out.println("");
		}
	}
	public void ex18() {
		//2중 for문을 이용해서 다음 모양을 출력하시오
		
		// 12345
		// 12345
		// 12345
		// 12345
		// 12345
	for(int x=1;x<=5;x++) {	
		for(int i=1;i<=5;i++) {
			System.out.print(i);
		}
		System.out.println();
		}		
	

	System.out.println();
	System.out.println();
	
		for(int i1=1;i1<=3;i1++) {
			for(int x1 = 5; x1>=1; x1-- ) {
				System.out.print(x1);
			}
			System.out.println(); 
		}
	
}	
	public void ex19() {
		//2중 for문을 이용하여 다음 모양을 출력하시오.
		
		//1
		//12
		//123
		//1234
		
		for(int x=1;x<=4;x++) {  //줄반복 
			for(int i = 1; i <=x; i++) { //출력반복  //x를 조건문 넣어 매 반복마다 x번만큼 반복하게함.
				System.out.print(i);
			}
			System.out.println();
		}
		System.out.println("======================");
		
		for(int x = 4; x>=1;x--) {
			for(int i=x;i>=1;i--) {
				System.out.print(i);
			}
			System.out.println();
		}
	}
	public void ex20() {
		//count 숫자세기
		//1부터 20까지 1씩 증가하면서 
		//마지막에서 1~20사이 3배수의 총개수 출력
		int conut = 0; //3의 배수를 세기 위한 변수.
		int sum = 0;
		for(int i =1; i<=20; i++) {
			if(i%3 == 0) { //3의 배수
				System.out.print(i+"  ");
				conut ++; // 이 if문이 동작 할때마다 1씩 증가.
				sum +=i;
			}
		}
		System.out.println(": "+conut+"개");
		System.out.println("3의 배수의 합계: "+sum);
		
	}
	public void ex21() {
		//2중 for문과 count를 이용해서 아래 모양을 출력
		//1 2 3 4
		//5 6 7 8
		//9 10 11 12
		
		int count=1;
		
		for(int x=1;x<=3;x++) {
			for(int i = 1; i<=4; i++) {
				System.out.printf("%3d",count);  //모양잡기에는 printf가 가장 좋다.
				count++;
				
			}
			System.out.println();
		}
		
		
		
		
	}


}	