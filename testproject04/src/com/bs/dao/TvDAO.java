package com.bs.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import com.bs.vo.ProductVO;
import com.bs.vo.StudentVO;

public class TvDAO extends ProductVO{
	
	public static void tvPrintData(ProductVO pv){
		System.out.println("Tv 클래스 : ");
		List list = new ArrayList();
		list.add(pv);
		Iterator it = list.iterator();
		
		while(it.hasNext()){
			ProductVO tmp = ((ProductVO)it.next());
			System.out.print("입력한 PID : " + tmp.getPid());
			System.out.print("  NAME : " + tmp.getpName());
			System.out.print("  PRICE : " + tmp.getPrice());
			System.out.println("  AMOUNT : " + tmp.getAmount());
		}
	}
}
