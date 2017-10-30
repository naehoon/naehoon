package com.bs.vo;

//Student 클래스 
public class Student implements Comparable{
	public final static int LEFT = 0;
	public final static int CENTER = 1;
	public final static int RIGHT = 2;

	String name;  //학생이름
	String studentNo; //학번
	int koreanScore; //한국어 점수
	int engScore;  //영어 점수
	int mathScore; //수학 점수
	int total; //합계
	
	public Student(){
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public int getKoreanScore() {
		return koreanScore;
	}

	public void setKoreanScore(int koreanScore) {
		this.koreanScore = koreanScore;
	}

	public int getEngScore() {
		return engScore;
	}

	public void setEngScore(int engScore) {
		this.engScore = engScore;
	}

	public int getMathScore() {
		return mathScore;
	}

	public void setMathScore(int mathScore) {
		this.mathScore = mathScore;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	//생성자 
	public Student(String name, String studentNo, int koreanScore,int engScore, int mathScore) {
		this.name = name;
		this.studentNo = studentNo;
		this.koreanScore = koreanScore;
		this.engScore = engScore;
		this.mathScore = mathScore;
		this.total = koreanScore + engScore + mathScore;
	}

	@Override
	public String toString() {
		return format(name, 4, LEFT) 
				+ format(studentNo, 4, RIGHT)
				+ format(koreanScore+"", 6, RIGHT) 
				+ format(engScore+"", 6, RIGHT)
				+ format(mathScore+"", 6, RIGHT)
				+ format(total+"", 8, RIGHT);
	}

	//출력되는 포멧 설정 메서드
	//+ Student.format(koreanTotal + "", 11, Student.RIGHT)
	public static String format(String str, int length, int alignment) {
		//str = 국어점수 100 , length  = 11,. alignment = 2
		//11 -3 = 8
		int diff = length - str.length(); //diff = 8 
		
		//사용자의 입력값이 설정한 값보다 길면 설정된 값까지만 자른다. 
		if (diff < 0){
			return str.substring(0, length);
		}

		char[] source = str.toCharArray(); //100
		//설정된 length 값크기의 char 배열 선언 
		char[] result = new char[length]; //11
		
		for (int i = 0; i < result.length; i++) {
			result[i] = ' ';
		}

		switch (alignment) { //2
		case CENTER: //1 중앙 출력
			//Object src : 복사하고자 하는 소스입니다. 원본이라고 생각하면 됩니다.
			//int srcPos : 위의 소스에서 어느 부분부터 읽어올지 위치를 정해줍니다.
			//Object dest : 원본이 있다면 복사본이 있어야겠지요. 복사하려는 대상입니다.
			//int destPos : 위의 dest에서 자료를 받을 때, 어느 부분부터 쓸지 시작 위치를 정해줍니다.
			//int length : 원본에서 복사본까지 얼마큼 읽어 올지 입력하는 것 입니다.
			System.arraycopy(source, 0, result, diff / 2, source.length);
			break;
		case RIGHT: //2 오른쪽 줄맞춤
			//100, 0, result[0], 2 
			System.arraycopy(source, 0, result, diff, source.length);
			break;
		case LEFT: //0 왼쪽 줄맞춤
		default:
			System.arraycopy(source, 0, result, 0, source.length);
			break;
		}//
		return new String(result);
	}
	
	//이름을 오름차순 기준으로 정렬하기 위해 compareTo 구현  
	public int compareTo(Object obj) {
		int result = -1;
		if(obj instanceof Student) {
			Student s1 = (Student)obj;
			//(this.name).charAt(0)-(s1.name).charAt(0)
//			for (int i = 0; i < 10; i++) {
//				System.out.println("############" + (this.name).charAt(i));
//			}
			result = (this.name).compareTo(s1.name);
		}
		return result;
	}
}