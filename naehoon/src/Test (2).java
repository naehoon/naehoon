interface Testinter{
}

class TestParent{

	public String testMethod(String s){

		return s;

	}
}

class Test extends TestParent implements Testinter{
	public static void main(String[]args){

		//��ü ���� �Ұ���!!! (�ڽ�, �θ�)
		//Test t = new TestParent();

		//��ü ���� �Ұ���!!! (�ڽ�, �������̽�)
		//Test t = new Testinter();
		
		//��ü ���� �Ұ���!!! (�θ�, �������̽�)
		//TestParent tp = new Testinter();
		
		//��ü ���� ����	  (�θ�, �ڽ�)
		//TestParent tp = new Test(); 

		//��ü ���� �Ұ���!!! (�������̽�, �θ�)
		//Testinter ti = new TestParent();

		//��ü ���� ����	  (�������̽�, �ڽ�)
		//Testinter ti = new Test();
		
		//����ȯ ���� (�ڼ�Ÿ�� <-����Ÿ�� (���� ���� ������ null �ϰ�츸))
		//TestParent tp = null;
		//Test t = (Test)tp; //�ڼ�Ÿ�� <-����Ÿ�� (�ٿ�ĳ����)

		//����ȯ ���� (����Ÿ�� <- �ڼ�Ÿ��)
		//Test t = new Test(); //
		//TestParent tp = t;   //ĳ���� �� ���� ������.(����Ÿ�� <- �ڼ�Ÿ��) (��ĳ����)
		
		//����ȯ ���� (�θ�Ÿ�� <-�������̽�)
		//Testinter ti = new Test();      //�������̽� �������� = Ŭ���� ��ü ���� 
		//TestParent tp = (TestParent)ti; //�θ�Ÿ�� <-�������̽�

		//Test t = new Testinter();  // Test Type t ���� �������� �ν��Ͻ� ��ü ���� �Ұ���.
		//TestParent tp = new Test();
		//Test t1 = new TestParent(); // error
		//Test t2 = new Testinter(); //error
		//Test t = new Test();
		//TestParent tp = new TestParent();
		
		//TestParent tp = t; //����Ÿ�� <-�ڼ�Ÿ�� 
		//TestParent tp = (Test)ti;  // test Ŭ���� Ÿ������ �������̽��� ĳ���� ����.
		//Test t2 = (Test)ti;  // test Ŭ���� Ÿ������ �������̽��� ĳ���� ����.
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