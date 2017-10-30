package com.bs.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.bs.vo.Audio;
import com.bs.vo.ProductVo;
import com.bs.vo.Tv;

public class ProductDAO {
	
	ProductVo p = null;

	public void inputData(ArrayList<ProductVo> li) {
		Scanner sc =  new Scanner(System.in);
		System.out.println("PID 를 입력하세요 : ");
		String pid;
		String regex;
		do{
			pid = sc.nextLine();
			regex = "^[a-zA-Z0-9]{8}$";
			if(pid.matches(regex)){
				if(pid.charAt(0)=='T' || pid.charAt(0)=='t'){
					p = new Tv();
					p.setPid(pid);
					System.out.println("PNAME 를 입력하세요 : ");
					String pName = sc.nextLine();
					System.out.println("PRICE 를 입력하세요 : ");
					int price = Integer.parseInt(sc.nextLine());
					System.out.println("AMOUNT 를 입력하세요 : ");
					int amount = Integer.parseInt(sc.nextLine());
					p.setpName(pName);
					p.setAmount(amount);
					p.setPrice(price);
					li.add(p);
				}else if(pid.charAt(0)=='A' || pid.charAt(0)=='a'){
					p = new Audio();
					p.setPid(pid);
					System.out.println("PNAME 를 입력하세요 : ");
					String pName = sc.nextLine();
					System.out.println("PRICE 를 입력하세요 : ");
					int price = Integer.parseInt(sc.nextLine());
					System.out.println("AMOUNT 를 입력하세요 : ");
					int amount = Integer.parseInt(sc.nextLine());
					p.setpName(pName);
					p.setAmount(amount);
					p.setPrice(price);
					li.add(p);			
				}else{
					System.out.println("wrong input!!!");
					return;
				}
			}else if(!pid.matches(regex)){
				System.out.println("PID 는 8글자의 숫자 또는 영문으로 입력해주세요");
			}
		}while(!pid.matches(regex));
	}
	
	//출력 메서드 
	public void printData(ArrayList<ProductVo> li) {
		Iterator<ProductVo> it = li.iterator();
		System.out.println();
		System.out.println("<<list>>");
		
		while(it.hasNext()){
			p = it.next();
			System.out.println(p);
		}
	}
	
	//삭제 메서드 
	public void deleteData(ArrayList<ProductVo> li) {
		Iterator<ProductVo> it = li.iterator();
		//이름으로 검색후 자료를 삭제 한다.
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제 하려는 제품의 pid 를 입력해 주세요 ");		
		String tmppid = sc.nextLine();
		
		while(it.hasNext()){
			p = it.next();
//			if(p.getPid().equals(tmppid)){
//				it.remove();
//			}
			if(tmppid.equals(p.getPid())){
				it.remove();
			}
		}
	}
}
