package edu.kh.op.ex;

import java.util.Scanner;

public class OpExample { //예제 코드 작성용 클래스
	
	//ex1 이라고 명칭하는 메소드(method)
	// -> OpExample이 가지고 있는 기능 중 ex1()이라는 기능.
	
	public void ex1(){
		System.out.println("OpExample 클래스의 ex1 기능");
		System.out.println("클래스 분리 테스트.");
		System.out.println("sout");
		
	}
	
	public void ex2(){
		Scanner sc = new Scanner(System.in);
		
		System.out.print("정수 입력 1 : ");
		int input1 = sc.nextInt();
		
		System.out.print("정수 입력 2 : ");
		int input2 = sc.nextInt();
		
		System.out.printf("%d + %d = %d\n",input1,input2,input1+input2);
		System.out.printf("%d %% %d = %d\n",input1,input2,input1%input2);
		System.out.printf("%d * %d = %d\n",input1,input2,input1*input2);
		
	}

	public void ex3() {
		//증감(증가/감소) 연산자 : ++ , --
		// 피연산자(값) 3++  == 3이 피연산자.
		
		int iNum1 = 10;
		int iNum2 = 10;
		
		iNum1++; //1씩 증가
		iNum2--; //1씩 감소
		
//		System.out.println(iNum1);
//		System.out.println(iNum2);
		
		//전 처리 - 전위 연산
		// ++3; , --2 연산자가 앞쪽 배치
		int temp1 = 5;
		System.out.println(++temp1 + 5);
						//++5 +5
						// 6 + 5 == 11  전위 연산 처리후 플러스
		System.out.println(temp1);  // 프린트에서 이미 전위연산을 해서 6인 상태로 드러왔다.
		
		//후 처리 - 후위 연산
		// 3--; , 2-- 연산자가 뒤쪽 배치
		int temp2 = 3;
		System.out.println( temp2-- + 2); //
		System.out.println( temp2); //
							// 3-- +2 = 5;
							// 5-- =4
		
		
		int a = 3;
		int b = 5;
		int c = a++ + --b;
		// a=4 b=4 c=7
		
	 System.out.printf("%d, %d, %d",a,b,c);
	}
	
	public void ex4() {
		
		//비교 연산자(관계연산자 라고 표현하기도 함)
		//> _ < _ >= _ <= _ == _ != (비교 연산자의 결과값은 무조건 true or false
		// 등호(=)가 포함된 연산자에서 등호는 항상 오늘쪽
		
		
		 int a = 10;
		 int b = 20;
	
// if(a == b) {
//	 System.out.println("==");
// }else {
//	 System.out.println("!/");
// }
		 
		 System.out.println(a>b);
		 System.out.println(a<b);
		 System.out.println(a==b);
		 System.out.println((a==b)==true);
		 
		 System.out.println("===================================================");
		 System.out.println("===================================================");
		 
		 int c = 4;
		 int d = 5;
		 
		 System.out.println(c<d);
		 System.out.println(c+1 <= d);
		 System.out.println(c>=d-1);
		 //t
		 
		 
		 System.out.println((++c != d) == (--c !=d)); //f

		 System.out.println("===================================================");
		 System.out.println("===================================================");
		 
		 int temp = 123;
		 
		 
		 System.out.println("temp는 짝수인가?"+(temp%2 == 0));
//		 System.out.println("temp는 짝수인가?"+(temp%2 != 1));  바로 위의 코드와 같은 역활 
		 System.out.println("temp는 홀수인가?"+(temp%2 != 0));
		 System.out.println("temp는 3의 배수 인가?"+(temp%3 == 0));
		 System.out.println("temp는 4의 배수 인가?"+(temp%4 == 0));
		 System.out.println("temp는 5의 배수 인가?"+(temp%5 == 0));
		 

		 System.out.println("===================================================");
		 System.out.println("===================================================");
	}
	
	public void ex5() {
			//논리 연산자 : &&(AND) ,  ||(OR)
			//&& 둘다 true 일때만 true 나머진 false
			// ex)사과'와' 바나나,   사과 '그리고' 바나나, 사과'이면서' 바나나.
			
			//와,그리고(이고),~면서,~이면서,
			
			int a = 101;
			//a이는 100이상 이면서 짝수인가
			
			System.out.println(a>=100);
			System.out.println(a%2==0);
			System.out.println((a>=100)&&(a%2==0));

			System.out.println("-------------------");
			
			int b = 5;
			// b 는 1~10까지 숫자 범위에 포함되어 있는가?
			// b 는 1~10까지 사이의 숫자인가?
			// b는 1보다 크거나 같으면서 10보다 작거나 같은가?
			System.out.println((b<=10)&&(b>=1));
			System.out.println("-------------------");
			//|| 둘다 false 일때만 false 나머진 true(AND의 정반대)
			//혹은~ ,또는 ,~거나 ,~이거나
			
			int c = 9;
			// c는 10 초과 이거나 짝수인가?
			System.out.println((c>10)||(c%2==0));
			
			
			
			
	}

	public void ex6() {
			//논리 부정 연산자 : !
			// -> 논리 값을 반대로 바꾸는 연산자.
		
		boolean bool1 = true;
		boolean bool2 = !bool1;
		
		System.out.println("bool1 : "+bool1);
		System.out.println("bool2 : "+bool2);
		
			//논리 부정 연산자를 어디로 쓰는가
			//
		
		System.out.println("---------------------------------------");
		
		Scanner sc =  new  Scanner(System.in);
		
		//정수를 하나 입력 받았을 때 
		//1) 해당 정수가 1부터 100사이의 값이 맞는지 확인 (1이상 100이하)
		//2) (반대) 1부터 100사이의 값이 '아닌지'를 확인

	
		System.out.print("정수 입력: ");
		int input = sc.nextInt();
		boolean result1 =(1 <= input)&&(input <= 100); 
		//둘 모두 true일 경우 AND연산자를 이용해서 true값을 확인한다.
		//괄호가 없어도 연산에 문제가 없다.
		
		System.out.printf("%d은/는 1이상 100이하의 정수인가 ? %b\n",input,result1);

		

		boolean result2 =	1<input ||	100<input; //둘다 범위에 들어가야지 false이다 
		boolean result3 =	!((1 <= input) && (input <= 100));  // 
		System.out.printf("%d은/는 1이상 100이하의 정수가 아닌가 ? %b // %b\n",input,result2,result3);
		}
	
	public void ex7() {
		//복합 대입 연산자 :  +=  ,  -+  , *=  , /= , %=
		//항상 연산식중 '='등호는 오른쪽에 위치한다.
		//->피연산자가 자신과 연산 후 결과를 다시 자신에게 대입한다.
		//복합연산자가 조금더 빠르다, 대용량의 정보작업에서는 중요하다.
		int a = 10;
		a++;
		
		System.out.println("a 1 증가" + a );
		
		//a를 4 증가
		a +=4;
		System.out.println("a 4 증가" + a );
		
		//a를 10 감소
		a -=10;
		System.out.println("a 10감소 "+ a);
		
		//a를 3곱
		a *=3;
		System.out.println("a 3곱 "+a);
		
		//나누기
		a /=6;
		System.out.println("a 3나누기 몫 " + a);

		//나머지
		a %=2;
		System.out.println("a 3나눈 후 나머지 " + a);
	}

	public void ex8() {
		//삼항 연산자 : 조건식 ? 식1 : 식2
		//조건식 [ 비교 , 논리 ,논리부정 ] 최종적으로 셋중 하나가 필요하다.
		//조건식 결과가 true이면 식1 false이면 식2
		//조건식 : 연산의 결과가 T / F인 식 (비교, 논리, 논리 부정)
		

		//	num이 30보다 크면(초과) : "num은 30보다 큰 수 이다"
		//	num이 30보다 작으면(이하) : "num은 30보다 작은 수 이다"
		
		int num = 30;
					
		
		String str1 = "num은 30을 초과한 수 이다";
		String str2 = "num은 30 이하의 수 이다";
		
		String result = num >30 ? str1 : str2;
		//num 값이 30을 초과하면 str1
		// 초과하지 못하면 str2를
		//result 변수에 저장한다.
		
		System.out.println(result);
		System.out.println("====================================");
		
		//입력 받은 정수가 음수인지 양수인지 구분
		//0은 양수로 처리
		 

		System.out.print("임의의 수 입력 : ");
		Scanner sc = new Scanner(System.in);
		//int input = sc.nextInt();
		
		//String result2 = input >= 0 ? "양수" : "음수";  //양수일 경우 양수 출력
		System.out.println((sc.nextInt() >= 0 ? "양수" : "음수") + "입니다.");  //괄호를 선 처리 후 "입니다"를 붙인다.
		
		//코드를 최대한 줄이는 것 : 리팩토링 (Refectoring)
			
	}
}