package test_20170621;

import java.util.HashSet;

public class HashSetEx4 {

	public static void main(String[] args) {
		
		HashSet<Object> set = new HashSet<Object>();
		
		set.add(new String("abc"));
		set.add(new String("abc"));
		
		set.add(new Person2("David", 10));
		set.add(new Person2("David", 10));
		
		System.out.println(set);
	}
}
