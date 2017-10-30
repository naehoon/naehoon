package com.bs.dao;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import com.bs.vo.Audio;
import com.bs.vo.Product;
import com.bs.vo.Tv;

public class ProductDAO {
	
	Product p = null;
	public void inputData(ArrayList li){ //.입력 메서드
		
		Scanner sc = new Scanner(System.in);
		String pid;
		String regex = "^[0-1a-zA-Z]{8}$";
		boolean flag = false;
			
		do{
			System.out.println("PID 를 입력해주세요");
			pid = sc.nextLine();
			if(!pid.matches(regex)){ //8자리가 아니면
				System.out.println("PID 는 8자리 만 입력 가능합니다.");
			}else if(!(pid.startsWith("A") || pid.startsWith("a") || pid.startsWith("T") || pid.startsWith("t"))){
				System.out.println("PID 는 A 또는 T 로 시작해야 합니다.");
				flag = false;
			}else{
				flag = true;
			}
		}while(!pid.matches(regex) || !flag);
		
		if(pid.startsWith("A") || pid.startsWith("a")){
			System.out.println("Audio 객체 생성");
			p = new Audio();
		}else if(pid.startsWith("T") || pid.startsWith("t")){
			System.out.println("TV 객체 생성");
			p = new Tv();
		}
		
		p.setPid(pid);
		System.out.println("NAME 를 입력해주세요");
		p.setName(sc.nextLine());
		System.out.println("PRICE 를 입력해주세요");
		p.setPrice(Integer.parseInt(sc.nextLine()));
		System.out.println("AMOUNT 를 입력해주세요");
		p.setAmount(Integer.parseInt(sc.nextLine()));
		li.add(p);
	}
	
	public void printData(ArrayList li){ //.출력메서드
		Iterator it = li.iterator();
		while(it.hasNext()){
			p = (Product)it.next();
			System.out.println(p);
		}
	}
	
	public void deleteData(ArrayList li) {
		Iterator it = li.iterator();
		//이름으로 검색후 자료를 삭제 한다.
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제 하려는 제품의 pid 를 입력해 주세요 ");		
		String tmppid = sc.nextLine();
		
		while(it.hasNext()){
			p = (Product) it.next();
//			System.out.println("@@@@@@@@@@@@@@@" + p);
			if(tmppid.equals(p.getPid())){
				it.remove();
				System.out.println("삭제 완료");
			}
		}
	}
}

//	public void deleteData(ArrayList li){ //.삭제 메서드 
//		Iterator<Product> it = li.iterator();
//		Scanner sc = new Scanner(System.in);
//		System.out.println("삭제하실 PID 를 입력하세요");
//		String tmppid = sc.nextLine();
////		p = it.next();
////		for (int i = 0; i < li.size(); i++) {
////			System.out.println(((Product)li.get(i)).getPid());
////			if(((Product)li.get(i)).getPid().equals(tmppid)){
////			if(p.getPid().equals(tmppid)){
////				
////			}
////		}
//		
//		while(it.hasNext()){
//			if(tmppid.equals(p.getPid())){
//				it.remove();
//				System.out.println("삭제 완료 ");
//				break;
//			}else{
//				System.out.println("찾는  PID 가 없습니다.");
//				break;
//			}
//		}
//		
////		for (int i = 0; i < li.size(); i++) {
////			System.out.println(((Product)li.get(i)).getPid());
////			if(((Product)li.get(i)).getPid().equals(tmppid)){
////				while(it.hasNext()){
////					it.next();
////					it.remove();
////				}
////			}else{
////				System.out.println("입력하신 PID 가 없습니다.");
////				break;
////			}
////		}
//		
////		while(it.hasNext()){
////			System.out.println("@#################" + p.getPid());
////			if(p.getPid().equals(tmppid)){
////				System.out.println("@@@@@@@@@@@@@@@@@@");
////				li.iterator();
////				it.remove();
////			}else{
////				System.out.println("입력하신 PID 가 없습니다.");
////				break;
////			}
////		}
//	}
//}
