package com.bs.control;
import java.util.ArrayList;
import java.util.Scanner;
import com.bs.dao.ProductDAO;
public class ProductProcess {
	public static void main(String[] args) {
		ProductDAO pd = new ProductDAO();
		ArrayList item = new ArrayList();
		int menu;
		do{
			System.out.println("메뉴를 선택 하세요 1.입력, 2.출력, 3.수정, 4.삭제, 5.종료");
			Scanner sc = new Scanner(System.in);
			menu = (Integer.parseInt(sc.nextLine()));
			switch(menu){
			case 1:
				 pd.inputData(item);
				break;
			case 2:
				pd.printData(item);
				break;
			case 3:
				System.out.println("없는 메뉴 ");
				break;
			case 4:
				pd.deleteData(item);
				break;
			case 5:
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}while(menu !=5);
	}
}
