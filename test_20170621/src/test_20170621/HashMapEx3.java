package test_20170621;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapEx3 {
	
	static HashMap phoneBook = new HashMap();
	
	public static void main(String[]args){
		addPhoneNo("친구", "이자바", "010-0000-0000");
		addPhoneNo("친구", "이자바1", "010-0001-0000");
		addPhoneNo("친구", "이자바2", "010-0002-0000");
		addPhoneNo("회사", "이자바3", "010-0003-0000");
		addPhoneNo("회사", "이자바4", "010-0004-0000");
		addPhoneNo("회사", "이자바5", "010-0005-0000");
		addPhoneNo("회사", "이자바6", "010-0006-0000");
		addPhoneNo("세탁",  "010-0000-0000");
		
		printList();
	}
	
	static void addGroup(String groupName){
		if(!phoneBook.containsKey(groupName)){
			phoneBook.put(groupName, new HashMap());
		}
	}
	
	//그룹에 전화번호를 추가하는 메서드 
	static void addPhoneNo(String groupName, String name, String tel){
		addGroup(groupName);
		
		HashMap group = (HashMap)phoneBook.get(groupName);
		group.put(tel, name); //이름은 중복될 수 있으니 전화번호를 키로 저장한다.
	}
	
	static void addPhoneNo(String name, String tel){
		addPhoneNo("기타", name, tel);
	}
	
	//전화번호부 전체를 출력하는 메서드 
	static void printList(){
		Set set = phoneBook.entrySet();
		Iterator it = set.iterator();
		
		while(it.hasNext()){
			Map.Entry e = (Map.Entry)it.next();
			
			Set subSet = ((HashMap)e.getValue()).entrySet();
			Iterator subIt = subSet.iterator();
			
			System.out.println("*" + e.getKey() + "[" + subSet.size()+"]");
			
			while(subIt.hasNext()){
				Map.Entry subE = (Map.Entry)subIt.next();
				String telNo = (String)subE.getKey();
				String name = (String)subE.getValue();
				System.out.println(name + " "+ telNo);
			}
			System.out.println();
		}
	}
}
