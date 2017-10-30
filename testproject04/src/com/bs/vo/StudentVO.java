package com.bs.vo;

// Student VO class
// VO 는 하나의 방법이다.
// 인터넷 상에서 사용하는 VO 들을 DTO 라고 한다. (모양은 같지만 명칭만 달라진다.)
//  

public class StudentVO {
	
	String name;
	int score;
	String grade;
	
	public StudentVO() {
	}
	
	public StudentVO(String name, int score, String grade) {
		super();
		this.name = name;
		this.score = score;
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}
	
//	@Override
//	public String toString() {
//		return "StudentVO [name=" + name + ", score=" + score + ", grade=" + grade + "]";
//	}
//	
}
