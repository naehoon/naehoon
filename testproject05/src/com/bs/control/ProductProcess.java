package com.bs.control;

import java.util.ArrayList;
import java.util.Scanner;

import com.bs.dao.ProductDAO;
import com.bs.vo.ProductVo;

public class ProductProcess {
	
	public static void main(String[]args){
		Scanner sc = new Scanner(System.in);
		ProductDAO pdao = new ProductDAO();
		ArrayList<ProductVo> li = new ArrayList<ProductVo>();
		int menu;
		
		do{
			System.out.println("1. input, 2.print, 3.delete , 4.exit");
			menu = Integer.parseInt(sc.nextLine());
			switch(menu){
			case 1:
				pdao.inputData(li);
				break;
				
			case 2:
				pdao.printData(li);
				break;
				
			case 3:
				pdao.deleteData(li);
				break;
				
			case 4:
				System.out.println("Program end");
				break;
				
			default:
				break;
			}
		}while(menu !=4);
	}
}
