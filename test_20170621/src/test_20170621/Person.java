package test_20170621;

public class Person {
	
	String name;
	int age;
	
	Person(String name, int age){
		this.name = name;
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}

}
