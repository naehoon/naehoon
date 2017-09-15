public class MethodEx01{

	public static void main(String [] args){

		System.out.println("main 함수 시작");

		printStr(10);
		printStr("오내훈");
	

		System.out.println("main 함수 종료");

	}

	public static void printStr(int i){

		System.out.println("사용자 작성함수 pritnStr(int i) : " + i);
	
	}


	public static void printStr(String i){

		System.out.println("사용자 작성함수 오버로딩 pritnStr(String i) : " + i);
	
	}


}