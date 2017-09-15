package test_20170621;

import java.util.HashSet;

public class HashSetEx3 {

	public static void main(String[] args) {
		
		HashSet set = new HashSet();
		set.add("abc");
		set.add("abc");
		set.add(new Person("David", 10));
		set.add(new Person("David", 10));
		System.out.println(set);

	}

}
