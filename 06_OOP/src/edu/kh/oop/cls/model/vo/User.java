package edu.kh.oop.cls.model.vo;

public class User {
	//클래스가 생성될 때 필요한
	//속성  (==필드)
	
	//아이디,비밀번호,이름,나이,성별(추상화 진행)
	
	//캡슐화 원칙에 의해서 필드는 기본적으로 모두 private
	private String userId ;
	private String userPw ;
	private String userName ;
	private int userAge;
	private char userGender;
	
	
	
	//기능  (==생성자 + 메소드)

	//생성자 :new 연사자를 통해 객체가  생성될 때 
	//		생성된 객체의 필드값 초기화 + 기능 수행.
	
	//기본 생성자
	public User() {
		System.out.println("기본 생성자로 User객체 생성");  

		//필드 초기화
		userId = "user01";
		userPw = "pass01";
		userName = "홍길동";
		userAge = 20;
		userGender = '남';
		
	}
	
	//매개 변수 생성자
	//**사용되는 기술, 변수 : 매개변수(paramiter), ovreloding(오버로딩) , this, 	
	
	//## 매개변수 :  생성자나 메소드 호출 시 ( ) 괄호 안에 작성 되어 
	//						전달 되어 지는 값을 저장하는 변수 .
	//##전달 받은 값을 저장하고 있는 매개체(지니고 있는 역활) 역활의 변수.
	
	public User(String userId,String userPw){
		//user의 괄호안에 있는 것들이 매개변수.
		//매개변수 생성자를 통해 들어온 값이 그대로 대입된다.	
		//System.out.println("매개변수 생성자를 이용해 User객체 생성");
		//System.out.println(userId +"/"+userPw);
		
		//전달 받은 값을 필드에 초기화 해주는 작업 (this 참조변수)
		
		//###this 참조 변수###
		// - 객체가 자기 자신을 참조할 수 있도록 하는 변수
		// - 모든 객체 내부에 숨겨져 있다.
		
		//@@@  필드명과 매개변수명이 같을 경우 이를 구분하기 위해서 사용한다. @@@
		this.userId = userId;  //전달받은 매개변수를 필드의 변수에 저장한다.
	//	필드userId = 매개변수
		this.userPw = userPw;
		
	}
	
	//필드를 전부 초기화 하는 목적의 매개변수 생성자
	
	public User(String userId ,String userPw ,String userName , int userAge, char userGender ){
					
		//매개변수로 전달 받은 값으로 필드 초기화
//		this.userId = userId;
//		this.userPw = userPw;
		
		this(userId,userPw);  //이 클래스 안에서 String 두개 있는 생성자를 이용한다.
		//같은 클래스의 다른 생성자를 호출할 때 사용
		//생성자 내에서 반드시 첫번째 줄에 작성 되어야한다.
		//왜 사용하는가 ? 중복 코드제거 , 코드 길이 감소, 재사용성 증가
		
		this.userName = userName;
		this.userAge = userAge;
		this.userGender = userGender;
	}
	
	
	
	//오버 로딩(Over Loading)
	// - 클래스 내에 동일한 이름의 메소드(생성자도 포함)를 
	// 	 여러 개 작성하는 기법.
	
	//-- > 하나의 이름으로 여러 기능을 수행 할 수 있도록 하는 것.
	// [오버로딩 조건]
	//1)메서드(생성자도 포함)의 이름이 동일
	//2)매개변수 개수 , 타입이 다른거나  , 순서중 1개라도 달라야 함.
	
	
//	public User() {//기본생성자가 이미 작성되어 있어서 중복.}
		
	public User(String UserId) {}//매개변수의 개수가 같은 생성자가 없다 //오버로딩 성립

	public User(int UserAge) {}//매개변수의 개수는 같지만 자료형 타입이 다르다 //오버로딩 성립.
	
	public User(String UserId,int UserAge) {}//매개변수의 개수가 다름.
	
	public User(int UserAge,String UserId) {}//매개변수의 개수가 같지만 순서가 다르다.
	
	//변수명에 상관 없이 개수 , 타입 , 순서만 따진다.
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//캡슐화로 인한 간접 접근 기능 (getter / setter )
	public String getUserId() {  //userId의 getter
		return userId;    
	}
	public void setUserId(String userId){ //userId의 setter
		this.userId = userId;
	}
	//getter , setter 자동완성
	//alt + Shift + s 
	//getter setter 자동 생성
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getUserAge() {
		return userAge;
	}
	public void setUserAge(int userAge) {
		this.userAge = userAge;
	}
	public char getUserGender() {
		return userGender;
	}
	public void setUserGender(char gender) {
		this.userGender = gender;
	}
	
	
	}
