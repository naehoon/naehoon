package com.bs.control;

import java.util.ArrayList;
import java.util.Scanner;
import com.bs.dao.CarDAO;
import com.bs.model.Car;

public class CarProcess {

	public static void main(String[] args) { // main 클래스
		CarDAO cd = new CarDAO(); // DAO 객체 생성
		ArrayList<Car> item = new ArrayList<Car>(); // Car 타입 객체를 담을 ArrayList
													// 선언
		int menu; // 메뉴 선택 번호 변수 선언

		do { // do start
			System.out.println("메뉴를 선택하세요 >> 1.입력, 2.출력, 3.종료");
			Scanner sc = new Scanner(System.in);
			menu = (Integer.parseInt(sc.nextLine()));

			switch (menu) { // switch start
			case 1: // 입력 선택시
				cd.inputData(item); // CarDAO 의 입력 메서드 호출 (매개변수 item)
				break;
			case 2: // 출력 선택시
				cd.printData(item); // CarDAO 의 출력 메서드 호출 (매개변수 item)
				break;
			case 3: // 종료 선택시
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		} while (menu != 3); // menu 가 3이 아니면 빠져나온다.
	}// end of main
}
