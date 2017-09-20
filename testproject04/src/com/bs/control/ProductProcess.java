package com.bs.control;

import java.util.ArrayList;
import java.util.Scanner;

import com.bs.dao.AudioDAO;
import com.bs.dao.TvDAO;
import com.bs.vo.ProductVO;

public class ProductProcess {

	public static void main(String[] args) {
		TvDAO td = new TvDAO();
		Scanner sc = new Scanner(System.in);
		
		String tmpid;
		String regex;
		
		do{
			System.out.println("pid 를 입력하세요(T로 시작하면 TV , A로 시작하면 Audio 입니다) ");
			regex = "^[a-zA-Z0-9]{1,8}$";
			tmpid = sc.nextLine();
			
			if(tmpid.matches(regex)){
				if(tmpid.startsWith("A") || tmpid.startsWith("a")){
					System.out.println("입력한 제품은 Audio 입니다.");
					AudioDAO.audioPrintData(inputData(tmpid));
				}else if(tmpid.startsWith("T") || tmpid.startsWith("t")){
					System.out.println("입력한 제품은 TV 입니다.");
					TvDAO.tvPrintData(inputData(tmpid));
				}
			}else if(!tmpid.matches(regex)){
				System.out.println("1자 이상~8자 이하로 입력해주세요");
			}
		}while(!tmpid.matches(regex));
	}
	
	//데이터 입력 메서드 
	public static ProductVO inputData(String tmpid){
		ProductVO pv = new ProductVO();
		Scanner sc = new Scanner(System.in);
		
		pv.setPid(tmpid);
		System.out.println("Name 을 입력하세요");
		String name = sc.nextLine();
		pv.setpName(name);
		System.out.println("price 를 입력하세요");
		String price = sc.nextLine();
		pv.setPrice(Integer.parseInt(price));
		System.out.println("amount 를 입력하세요");
		String amount = sc.nextLine();
		pv.setAmount(Integer.parseInt(amount));
		
		return pv;
	}
}
