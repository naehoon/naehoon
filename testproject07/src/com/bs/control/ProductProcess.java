package com.bs.control;

import java.util.Scanner;

public class ProductProcess {
	
	public static void main(String[]args){
		
		int menu ;
		System.out.println("메뉴를 선택해주세요 1.입력, 2.출력, 3.삭제, 4.종료");
		Scanner sc = new Scanner(System.in);
		menu = (Integer.parseInt(sc.nextLine()));
		
		do{
			System.out.println("test");
			
			switch(menu){
			
			case 1:
					
			break;

			case 2:
				
			break;
			case 3:
				
			break;
			case 4:
				System.out.println("프로그램을 종료 합니다.");
			break;
			
			}
			
		}while(menu!=4);
		
		
		
		
	}
}
