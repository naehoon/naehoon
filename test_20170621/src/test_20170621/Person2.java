package test_20170621;

public class Person2 {

	String name; // david //key
	int age; // 10
	int test = 50;

	Person2(String name, int age) {
		this.name = name;
		this.age = age;
	}
//	
//	public boolean equals(String abc) {
//		System.out.println("@@@@@@@" + abc);
//		return true;
//	}

	public boolean equals(Object obj) {
		
		System.out.println("start equals");
		
		if(obj != this){
			System.out.println("obj 와 this 는 같은것이아니다.!!!");
			System.out.println(obj);
			System.out.println("$$$$$$$$$$$$$$$$$$$" + this.test);
		}else{
			System.out.println("obj 와 this 는 같다!!!.!!!");
		}
		
		if (obj instanceof Person2) {
			Person2 tmp = (Person2) obj;
//			return name.equals(tmp.name) && age == tmp.age; // 결과 = true
			 return true; //중복 제거
		}
		return false; // 중복 제거 안함
	}

	public int hashCode() {
		
		System.out.println(name.hashCode());
		System.out.println((name+age).hashCode());
		
		return (name + age).hashCode();
	}

	@Override
	public String toString() {
		return name + " : " + age;
	}

}
