package com.bs.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class ScoreEvaluation {
	
	static ArrayList<Student> record = new ArrayList<Student>();
	static Scanner s = new Scanner(System.in);

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		while (true) {
			switch (displayMenu()) {
			case 1: //데이터 입력
				inputRecord();
				break;
			case 2: //데이터 삭제
				deleteRecord();
				break;
			case 3: //데이터 정렬
				sortRecord();
				break;
			case 4: //프로그램 종료
				System.out.println("프로그램을 종료합니다.");
				System.exit(0);
			}
		}// while
	}

	//메뉴 출력 및 선택한 메뉴 값 리턴 메서드
	static int displayMenu() {
		System.out.println("*****************************");
		System.out.println("*     성적 관리 프로그램     ");
		System.out.println("*         version 1.0        ");
		System.out.println("*  except from Backsaninfo   ");
		System.out.println("*****************************");
		System.out.println();
		System.out.println();
		System.out.println("1. 학생성적 입력.");
		System.out.println();
		System.out.println("2. 학생성적 삭제.");
		System.out.println();
		System.out.println("3. 학생성적 정렬(이름, 성적)");
		System.out.println();
		System.out.println("4. 프로그램 종료.");
		System.out.println();
		System.out.println();
		System.out.print("메뉴 선택(1~4) : ");

		int menu = 0;

		do {
			try {
				menu = Integer.parseInt(s.nextLine());

				if (menu >= 1 && menu <= 4) {
					break;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("잘못 입력. 다시 입력!!");
				System.out.print("메뉴 선택(1~4) : ");
			}
		} while (true);
		
		return menu;
	}//

	//데이터 입력 메서드 
	static void inputRecord() {
		System.out.println("1. 학생성적 입력");
		System.out.println("이름,학번,국어성적,수학성적,영어성적의 순서로 공백없이 입력");
		System.out.println("입력을 종료하려면 q 입력. 메인화면으로 이동.");

		while (true) {
			System.out.print(">> ");

			do {
				try {
					String input = s.nextLine().trim(); //입력받은 값 공백제거

					if (!input.equalsIgnoreCase("q")) {
						Scanner s2 = new Scanner(input).useDelimiter(","); //구분자 ","
							
						//ArrayList record 에 사용자에게 입력 받은 값을 Student 객체화 하여 담는다
						record.add(new Student(s2.next(), s2.next(), s2.nextInt(), s2.nextInt(), s2.nextInt()));
						System.out.println("입력되었습니다. 마치려면 q 입력.");
						break;
					} else {
						return;
					}
				} catch (Exception e) {
					System.out.println("입력 오류. 다시 입력해 주세요.");
					break;
				}
			} while (true);
		}// while
	}// inputRecord()
	
	//데이터 삭제 메서드
	static void deleteRecord() {
		while (true) {
			displayRecord();
			System.out.println("삭제하고자 하는 데이터의 학번 입력(q : 메인 화면)");
			System.out.print(">> ");

			do {
				try {
					String input = s.nextLine().trim();

					if (!input.equalsIgnoreCase("q")) {
						int length = record.size();
						boolean found = false;
						
						//사용자에게 입력받은 학생이름을 찾아서 같은 이름이 있을경우 삭제한다.
						for (int i = 0; i < length; i++) {
							Student st = record.get(i);
							if (input.equals(st.studentNo)) { 
								found = true;
								record.remove(i);
								break;
							}
						}// for

						if (found) {
							System.out.println("삭제 성공!");
						} else {
							System.out.println("일치하는 데이터 없음.");
						}
						break;
					} else {
						return;
					}
				} catch (Exception e) {
					System.out.println("입력 오류. 다시 입력.");
					break;
				}
			} while (true);
		}// while
	}// deleteRecord()
	
	//정렬 메서드 
	static void sortRecord() {
		System.out.print("정렬 기준 선택(1. 이름, 2. 성적, 3. 메인화면) : ");

		int sort = 0;

		do {
			try {
				sort = Integer.parseInt(s.nextLine());

				if (sort >= 1 && sort <= 3) {
					break;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("유효하지 않은 입력. 다시 입력.");
			}
		} while (true);

		if (sort == 1) { // 오름차순 정렬
			Collections.sort(record, new NameAscending());
			displayRecord();
		} else if (sort == 2) { //내림차순 정렬
			Collections.sort(record, new TotalDescending());
			displayRecord();
		} else {
			return;
		}// while
	}// sortRecord()

	//정렬 결과 출력 메서드
	static void displayRecord() {
		int koreanTotal = 0;
		int engTotal = 0;
		int mathTotal = 0;
		int total = 0;

		System.out.println();
		System.out.println("이름  번호  국어  영어  수학  총점");
		System.out.println("**********************************");

		int length = record.size();

		if (length > 0) { //점수 합계 계산
			for (int i = 0; i < length; i++) {
				Student st = record.get(i);
				System.out.println(st);
				koreanTotal += st.koreanScore;
				engTotal += st.engScore;
				mathTotal += st.mathScore;
				total += st.total;
			}

		} else {
			System.out.println();
			System.out.println("데이터가 없습니다.");
			System.out.println();
		}

		System.out.println("===================================");
		System.out.println("총점 : "
				+ Student.format(koreanTotal + "", 11, Student.CENTER)
				+ Student.format(engTotal + "", 6, Student.RIGHT)
				+ Student.format(mathTotal + "", 6, Student.RIGHT)
				+ Student.format(total + "", 6, Student.RIGHT));
		System.out.println();
	}// displayRecord()
}// class

//오름차순 정렬 메서드 
class NameAscending implements Comparator {
	
	@Override //name 을 오름차순으로 정렬하기 위한 compare 구현
	public int compare(Object o1, Object o2) {  
		// TODO Auto-generated method stub
		if (o1 instanceof Student && o2 instanceof Student) {
			Student s1 = (Student) o1;
			Student s2 = (Student) o2;
			//(this.name).charAt(0)-(s1.name).charAt(0)
			return (s1.name).compareTo(s2.name);
		}
		return -1;
	}
}

//내림차순 정렬 메서드 
class TotalDescending implements Comparator {

	@Override //total 을 내림차순으로 정렬하기 위한 compare 구현
	public int compare(Object o1, Object o2) {
		
		if (o1 instanceof Student && o2 instanceof Student) {
			Student s1 = (Student) o1;
			Student s2 = (Student) o2;
			
			return (s1.total < s2.total) ? 1 : (s1.total == s2.total ? 0 : -1);
		}
		return -1;
	}
}

//Student 클래스 
class Student implements Comparable {
	final static int LEFT = 0;
	final static int CENTER = 1;
	final static int RIGHT = 2;

	String name;  //학생이름
	String studentNo; //학번
	int koreanScore; //한국어 점수
	int engScore;  //영어 점수
	int mathScore; //수학 점수
	int total; //합계

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
	static String format(String str, int length, int alignment) {
		//str = 국어점수 10000000000 000000 , length  = 11,. alignment = 2
		//str = 국어점수 100 , length  = 1,. alignment = 2
		//11 -3 = 8
		//1 -3 = -2
		int diff = length - str.length(); //diff = 8 
		
		//사용자의 입력값이 설정한 값보다 길면 설정된 값까지만 자른다. 
		if (diff < 0){
			return str.substring(0, length);
//			return str.substring(0, 2);
		}

		char[] source = str.toCharArray(); //100
		//설정된 length 값크기의 char 배열 선언 
		char[] result = new char[length]; //11
		
		for (int i = 0; i < result.length; i++) {
			result[i] = ' ';
			//
//			result[0]; = ' ';
//			result[1]; = ' ';
//			result[2]; = ' ';
//			result[3]; = ' ';
//			result[4]; = ' ';
//			result[5]; = ' ';
//			result[6]; = ' ';
//			result[7]; = ' ';
//			result[8]; = ' ';
//			result[9]; = ' ';
//			result[10]; = ' ';
//			result[11]; = ' ';
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
}//