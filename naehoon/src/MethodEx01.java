public class MethodEx01{

	public static void main(String [] args){

		System.out.println("main �Լ� ����");

		printStr(10);
		printStr("������");
	

		System.out.println("main �Լ� ����");

	}

	public static void printStr(int i){

		System.out.println("����� �ۼ��Լ� pritnStr(int i) : " + i);
	
	}


	public static void printStr(String i){

		System.out.println("����� �ۼ��Լ� �����ε� pritnStr(String i) : " + i);
	
	}


}