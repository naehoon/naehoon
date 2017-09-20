package com.bs.dao;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

import com.bs.model.Car;

public class CarDAO {
	ArrayList<Car> record = new ArrayList<Car>();

	// 메뉴 출력 및 선택한 메뉴 값 리턴 메서드
	public int displayMenu() {

		System.out.println("메뉴를 선택하세요 >> 1.입력, 2.출력, 3,정렬, 4.수정, 5.삭제, 6.파일로 내보내기, 7.파일 가져오기 , 8.종료");
		int menu = 0;

		do {
			try {
				Scanner s = new Scanner(System.in);
				menu = Integer.parseInt(s.nextLine());

				if (menu >= 1 && menu <= 8) {
					break;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("잘못 입력. 다시 입력!! ->> 메뉴 선택(1~8) : ");
				System.out.println();
			}
		} while (true);
		return menu;
	}

	// 데이터 입력 메서드
	public void inputRecord() {

		do {
			try {
				Scanner s = new Scanner(System.in);
				Car c = new Car();
				System.out.println("모델명을 입력하세요(문자열)");
				c.setModelName(s.nextLine());
				System.out.println("displacement 을 입력하세요(정수형)");
				c.setDisplacement(Integer.parseInt(s.nextLine()));
				System.out.println("type 을 입력하세요(문자열)");
				c.setType(s.nextLine());
				System.out.println("mileage 을 입력하세요(실수형)");
				c.setMileage(Double.parseDouble(s.nextLine()));
				record.add(c);
				break;
			} catch (Exception e) {
				System.out.println("입력 오류. 다시 입력해 주세요.");
				break;
			}
		} while (true);
	}// inputRecord()

	// 출력 메서드
	public void printData() {
		Car c = null; // iterator 데이터를 담을 Car type 참조 변수 선언
		// Car type 객체를 담을 Iterator 변수선언후 매개변수 li 를 참조
		Iterator<Car> it = record.iterator();
		// it 에 데이터가 들어있는갯수만큼 반복문 실행
		while (it.hasNext()) {
			c = it.next(); // Car type 참조 변수에 it.next(); 로 꺼내와서 하나씩 참조해줌
			System.out.print(c); // toString 으로 Car type 참조 변수 출력
		}
	}

	// 데이터 삭제 메서드
	public void deleteRecord() {
		while (true) {
			printData();
			System.out.println("삭제하고자 하는 데이터의 모델을 입력 하세요 (q : 메인 화면)");

			do {
				try {
					Scanner s = new Scanner(System.in);
					String input = s.nextLine().trim();

					if (!input.equalsIgnoreCase("q")) {
						int length = record.size();
						boolean found = false;

						// 사용자에게 입력받은 모델명을 찾아서 같은 이름이 있을경우 삭제한다.
						for (int i = 0; i < length; i++) {
							Car c = record.get(i);
							// if (input.equals(st.studentNo)) {
							if (input.equals(c.getModelName())) {
								found = true;
								record.remove(i);
								break;
							}
						} // for

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
		} // while
	}// deleteRecord()

	// 데이터 수정 메서드
	public void updateRecord() {

		while (true) {
			printData();
			System.out.println("수정하고자 하는 데이터의 모델을 입력 하세요 (q : 메인 화면)");

			do {
				try {
					Scanner s = new Scanner(System.in);
					String input = s.nextLine().trim();

					if (!input.equalsIgnoreCase("q")) {
						int length = record.size();
						boolean found = false;

						// 사용자에게 입력받은 모델명을 찾아서 같은 이름이 있을경우 수정한다.
						for (int i = 0; i < length; i++) {
							Car c = record.get(i);
							// if (input.equals(st.studentNo)) {
							if (input.equals(c.getModelName())) {
								found = true;
								System.out.println("수정할 모델명을 입력하세요(문자열)");
								c.setModelName(s.nextLine());
								System.out.println("수정할 displacement 을 입력하세요(정수형)");
								c.setDisplacement(Integer.parseInt(s.nextLine()));
								System.out.println("수정할  type 을 입력하세요(문자열)");
								c.setType(s.nextLine());
								System.out.println("수정할 mileage 을 입력하세요(실수형)");
								c.setMileage(Double.parseDouble(s.nextLine()));
								record.set(i, c);
								break;
							}
						} // for
						if (found) {
							System.out.println("수정 성공!");
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

		} // while

	}// updateRecord()

	// 정렬 메서드
	public void sortRecord() {
		System.out.print("정렬 기준 선택(1. 모델명 , 2. 마일리지 , 3. 메인화면) : ");
		int sort = 0;

		do {
			try {
				Scanner s = new Scanner(System.in);
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
			Collections.sort(record, new Comparator<Object>() { // 익명 클래스
				@Override // name 을 오름차순으로 정렬하기 위한 compare 메서드 구현
				public int compare(Object o1, Object o2) {
					// TODO Auto-generated method stub
					if (o1 instanceof Car && o2 instanceof Car) {
						Car s1 = (Car) o1;
						Car s2 = (Car) o2;
						// (this.name).charAt(0)-(s1.name).charAt(0)
						return (s1.getModelName()).compareTo(s2.getModelName());
					}
					return -1;
				}
			});
			printData();
		} else if (sort == 2) { // 내림차순 정렬
			Collections.sort(record, new Comparator<Object>() { // 익명 클래스
				@Override // total 을 내림차순으로 정렬하기 위한 compare 메서드 구현
				public int compare(Object o1, Object o2) {
					if (o1 instanceof Car && o2 instanceof Car) {
						Car s1 = (Car) o1;
						Car s2 = (Car) o2;
						return (s1.getMileage() < s2.getMileage()) ? 1 : (s1.getMileage() == s2.getMileage() ? 0 : -1);
					}
					return -1;
				}
			});
			printData();
		} else {
			return;
		} // while
	}// sortRecord()

	//파일로 내보내기 메서드
	public void ouputStream() {
		try {
			String fileName = "Car.ser";

			FileOutputStream fos = new FileOutputStream(fileName);
//			BufferedOutputStream bos = new BufferedOutputStream(fos); //속도가 빨라짐
			ObjectOutputStream out = new ObjectOutputStream(fos);
			
			out.writeObject(record);
			out.close();

			System.out.println("직렬화가 잘 끝났습니다. (파일 내보내기 완료)");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//파일 가져오기 메서드	
	public void inputStream()	{
		try{
			String fileName = "Car.ser";
			FileInputStream fis = new FileInputStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			ObjectInputStream in = new ObjectInputStream(bis);
			System.out.println(in.readObject());
			
			in.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
