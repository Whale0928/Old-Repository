package edu.kh.collection.model.vo;

public class Student {

	private String name; // 이름
	private int age; // 나이
	private String region; // 사는곳
	private char gender;// 성별

	private int score; // 점수

	public Student() {// 기본 생성자

	}

	public Student(String name, int age, String region, char gender, int score) {
		this.name = name;
		this.age = age;
		this.region = region;
		this.gender = gender;
		this.score = score;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public char getGender() {
		return gender;
	}

	public void setGender(char gender) {
		this.gender = gender;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	// toString() 오버라이딩
	// alt + shift +s
	@Override
	public String toString() {
		return "Student [name=" + name + ", age=" + age + ", region=" + region + ", gender=" + gender + ", score="
				+ score + "]";
	}

}
