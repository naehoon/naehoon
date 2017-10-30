interface Testinter{
}

class TestParent{

	public String testMethod(String s){

		return s;

	}
}

class Test extends TestParent implements Testinter{
	public static void main(String[]args){

		//객체 생성 불가능!!! (자식, 부모)
		//Test t = new TestParent();

		//객체 생성 불가능!!! (자식, 인터페이스)
		//Test t = new Testinter();
		
		//객체 생성 불가능!!! (부모, 인터페이스)
		//TestParent tp = new Testinter();
		
		//객체 생성 가능	  (부모, 자식)
		//TestParent tp = new Test(); 

		//객체 생성 불가능!!! (인터페이스, 부모)
		//Testinter ti = new TestParent();

		//객체 생성 가능	  (인터페이스, 자식)
		//Testinter ti = new Test();
		
		//형변환 가능 (자손타입 <-조상타입 (조상 참조 변수가 null 일경우만))
		//TestParent tp = null;
		//Test t = (Test)tp; //자손타입 <-조상타입 (다운캐스팅)

		//형변환 가능 (조상타입 <- 자손타입)
		//Test t = new Test(); //
		//TestParent tp = t;   //캐스팅 형 생략 가능함.(조상타입 <- 자손타입) (업캐스팅)
		
		//형변환 가능 (부모타입 <-인터페이스)
		//Testinter ti = new Test();      //인터페이스 참조변수 = 클래스 객체 가능 
		//TestParent tp = (TestParent)ti; //부모타입 <-인터페이스

		//Test t = new Testinter();  // Test Type t 참조 변수에는 인스턴스 객체 참조 불가능.
		//TestParent tp = new Test();
		//Test t1 = new TestParent(); // error
		//Test t2 = new Testinter(); //error
		//Test t = new Test();
		//TestParent tp = new TestParent();
		
		//TestParent tp = t; //조상타입 <-자손타입 
		//TestParent tp = (Test)ti;  // test 클래스 타입으로 인터페이스를 캐스팅 가능.
		//Test t2 = (Test)ti;  // test 클래스 타입으로 인터페이스를 캐스팅 가능.
		//(Test)TestParent
		//System.out.println(tp.testMethod("test"));
		//System.out.println(t2.testMethod2("test2"));

/*
		if(tp instanceof Test){
			System.out.println("this is Test isntance");
		}

		if(tp instanceof TestParent){
			System.out.println("this is TestParent isntance");
		}

		if(tp instanceof Testinter){
			System.out.println("this is Testinter isntance");
		}
*/
		//TestParent tp = (TestParent)ti;
		
	}

	public String testMethod2(String s){
		return s;
	}
}