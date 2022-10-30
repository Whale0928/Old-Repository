package edu.kh.variable.ex2;

public class PrintExample {
	public static void main(String[] args) {
		
		
		//System.out.println();  -- 한 줄 출력
		//ln은 line을 의미함. 한줄 출력하겠다는 표시.
		//(출력후 줄 바꿈을 수행하겟다.)
		
		//System.out.print();  -- 단순 출력.
		//
		
		System.out.println("ln1");
		System.out.println("ln2");
		

		System.out.print("nonLn1");
		System.out.println();//내용이 없어도 단순 줄바꿈의 역활로 사용된다.
		System.out.print("nonLn2");
		
		//System.out.printf(); : 출력될 문자열 형식을 패턴으로 지정하는 출력구문. 
		//f = format(구성방식)
		
		System.out.println();
		
		int iNum1= 10;
		int iNum2= 5;
		
		//10+5 
		System.out.println((iNum1)+" + "+(iNum2)+" = "+(iNum1+iNum2));
		
		System.out.printf("%d + %d = %d\n"  ,iNum1,iNum2,iNum1+iNum2); // '\n'으로 줄넘김 시켜줘야한다.
		
		
		//10 + 10 * 5 /2 =35
		System.out.printf("%d + %d * %d / 2 = %d\n",iNum1,iNum1,iNum2,iNum1*iNum2/2+iNum1);
		
		
		int iNum3 = 3;
		
		System.out.printf("%d\n", iNum3);
		System.out.printf("%5d\n", iNum3); //오른쪽으로 5칸 확보.
		System.out.printf("%-5d\n", iNum3); // 왼쪽으로 5칸 확보.
		
		
		//실수
		System.out.printf("%f\n", 10/4.0);
		System.out.printf("%.2f\n", 10/4.0); //% '.'2f 
		System.out.printf("%.0f\n", 10/4.0); //,f는 반올림한다.
		
		//문자,문자열,boolean
		boolean isTrue =false;
		char ch ='백';
		String str = "점심 뭐 먹지"; //14byte  
		//String == 4byte  
		//String == 참조형
		
		//'' : char iretar 표기법.
		//"" : String ireat 표기법.
		
		System.out.printf("%b / %c / %s\n", isTrue, ch, str); //포멧 위치가 정확 해야한다.
		
		System.out.println("a\tb\tc\td\tef");
		System.out.println("안\n녕");

		System.out.println("\u0041"); //유니코드 (16진수) 번호로 출력
	}
}
