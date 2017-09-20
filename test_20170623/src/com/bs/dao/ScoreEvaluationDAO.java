package com.bs.dao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import com.bs.vo.Student;

public class ScoreEvaluationDAO {
	
	Scanner s = new Scanner(System.in);
	ArrayList<Student> record = new ArrayList<Student>();

	//메뉴 출력 및 선택한 메뉴 값 리턴 메서드
	public int displayMenu() {
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
	//static void inputRecord() {
	public void inputRecord() {
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
	public void deleteRecord() {
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
//							if (input.equals(st.studentNo)) { 
							if (input.equals(st.getStudentNo())) {
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
	public void sortRecord() {
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
			Collections.sort(record, new Comparator<Object>(){ //익명 클래스 
				@Override //name 을 오름차순으로 정렬하기 위한 compare 메서드 구현
				public int compare(Object o1, Object o2) {  
					// TODO Auto-generated method stub
					if (o1 instanceof Student && o2 instanceof Student) {
						Student s1 = (Student) o1;
						Student s2 = (Student) o2;
						//(this.name).charAt(0)-(s1.name).charAt(0)
						return (s1.getName()).compareTo(s2.getName());
					}
					return -1;
				}
			});
			
			displayRecord();
		} else if (sort == 2) { //내림차순 정렬
			Collections.sort(record, new Comparator<Object>(){ //익명 클래스 
				@Override //total 을 내림차순으로 정렬하기 위한 compare 메서드 구현
				public int compare(Object o1, Object o2) {
					if (o1 instanceof Student && o2 instanceof Student) {
						Student s1 = (Student) o1;
						Student s2 = (Student) o2;
						return (s1.getTotal() < s2.getTotal()) ? 1 : (s1.getTotal() == s2.getTotal()? 0 : -1);
					}
					return -1;
				}
			});
			
			displayRecord();
		} else {
			return;
		}// while
	}// sortRecord()

	//정렬 결과 출력 메서드
	public void displayRecord() {
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
				koreanTotal += st.getKoreanScore();
				engTotal += st.getEngScore();
				mathTotal += st.getMathScore();
				total += st.getTotal();
			}

		} else {
			System.out.println();
			System.out.println("데이터가 없습니다.");
			System.out.println();
		}

		System.out.println("===================================");
		System.out.println("총점 : "
				+ Student.format(koreanTotal + "", 11, Student.RIGHT)
				+ Student.format(engTotal + "", 6, Student.RIGHT)
				+ Student.format(mathTotal + "", 6, Student.RIGHT)
				+ Student.format(total + "", 6, Student.RIGHT));
		System.out.println();
	}// displayRecord()

	//오름차순 정렬을 하기 위한 sort 매개변스로 사용할 클래스  
//	class NameAscending implements Comparator {
//		
//		@Override //name 을 오름차순으로 정렬하기 위한 compare 구현
//		public int compare(Object o1, Object o2) {  
//			// TODO Auto-generated method stub
//			if (o1 instanceof Student && o2 instanceof Student) {
//				Student s1 = (Student) o1;
//				Student s2 = (Student) o2;
//				//(this.name).charAt(0)-(s1.name).charAt(0)
//				return (s1.getName()).compareTo(s2.getName());
//			}
//			return -1;
//		}
//	}

	//내림차순 정렬을 하기 위한 sort 매개변수로 사용할 클래스  
//	class TotalDescending implements Comparator {
//
//		@Override //total 을 내림차순으로 정렬하기 위한 compare 구현
//		public int compare(Object o1, Object o2) {
//			
//			if (o1 instanceof Student && o2 instanceof Student) {
//				Student s1 = (Student) o1;
//				Student s2 = (Student) o2;
//				
//				return (s1.getTotal() < s2.getTotal()) ? 1 : (s1.getTotal() == s2.getTotal()? 0 : -1);
//			}
//			return -1;
//		}
//	}
	
}
