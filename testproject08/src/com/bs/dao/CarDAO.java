package com.bs.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import com.bs.model.Car;

public class CarDAO {
	// Car c = null; // iterator 데이터를 담을 Car type 참조 변수 선언

	// 입력 메서드
	public void inputData(ArrayList<Car> li) {
		// 입력 받은 데이터를 담을 Car type 객체 생성
		Car c = new Car();
		// Scanner 객체 생성
		Scanner sc = new Scanner(System.in);
		System.out.println("MODEL NAME 을 입력하세요 (문자열)");
		// 입력 받은 문자열을 set
		c.setModelName(sc.nextLine());
		System.out.println("DISPLAYMENT 를 입력하세요 (정수형)");
		// 입력 받은 문자열을 정수로파싱하여 set
		c.setDisplacement((Integer.parseInt(sc.nextLine())));
		System.out.println("TYPE 을 입력하세요 (문자열)");
		// 입력 받은 문자열을 set
		c.setType(sc.nextLine());
		System.out.println("MILEAGE 를 입력하세요 (실수형)");
		// 입력 받은 문자열을 실수로 파싱해서 set
		c.setMileage((Double.parseDouble(sc.nextLine())));
		li.add(c); // 매개 변수 li 에 Car 객체를 넣어준다.
	}

	// 출력 메서드
	public void printData(ArrayList<Car> li) {
		Car c = null; // iterator 데이터를 담을 Car type 참조 변수 선언
		// Car type 객체를 담을 Iterator 변수선언후 매개변수 li 를 참조
		Iterator<Car> it = li.iterator();
		// it 에 데이터가 들어있는갯수만큼 반복문 실행
		while (it.hasNext()) {
			c = it.next(); // Car type 참조 변수에 it.next(); 로 꺼내와서 하나씩 참조해줌
			System.out.println(c); // toString 으로 Car type 참조 변수 출력
		}
	}
}
