package com.bs.control;

import java.util.ArrayList;
import java.util.Scanner;

import com.bs.dao.StudentDAO;
import com.bs.vo.StudentVO;

public class StudentProcess {
	
	public static void main(String[]	args){
		StudentDAO sd = new StudentDAO(); 
		ArrayList<StudentVO> inputItems = new ArrayList<StudentVO>(); //리턴 받은 입력값.
		Scanner sc = new Scanner(System.in);
		int menu; //메뉴 선택 번호 
		
		do{
			String tmp; 
			do{ //숫자인지 체크 하기
				System.out.println("메뉴를 선택해 주세요 (1~5 숫자만 입력 가능 합니다)>> ");
				System.out.println("1.입력, 2.출력, 3.수정, 4.삭제, 5.종료");
				tmp = sc.nextLine(); //입력 받은 메뉴 번호 
			}while(!StudentDAO.isNumber(tmp)); //입력받은값이 숫자가 아닐경우 반복 
			menu = (Integer.parseInt(tmp)); //입력받은 값을 Integer 로 변환			
			
			switch(menu){ 
			case 1:
				inputItems = sd.inputData(); //입력
				break;
			case 2:
				sd.printData(inputItems);//출력
				break;
			case 3:
				sd.updateData(inputItems); //수정
				break;
			case 4:	
				sd.deleteData(inputItems); //삭제
				break;
			}
		}while(menu != 5); //5가 아니면 반복
		System.out.println("프로그램을 종료 합니다.");
	}
}