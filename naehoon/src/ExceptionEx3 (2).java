class ExceptionEx3{
	public static void main(String[]args){
		int number = 100;
		int result = 0;

		System.out.println(1);
		System.out.println(2);

		try{
			System.out.println(3);
			System.out.println(0/0);
			System.out.println(4);
		}catch(ArithmeticException ae){
			ae.printStackTrace();
			System.out.println("예외메세지 " + ae.getMessage());
		}
		System.out.println(6);
/*
		for(int i=0; i<10; i++){
			try{
				result = number /(int)(Math.random());
				System.out.println(result);
			}catch(ArithmeticException e){
				System.out.println("0");
				System.out.println(e.getMessage());
				
			}
		}
*/
/*
		for(int i=0; i<10; i++){
				result = number /(int)(Math.random()*10);
				System.out.println(result);
		}
*/
/*
		try{
			//System.out.println("ABC");
			System.out.println(0/0);
			//System.out.println("123"); //예외 발생이후의 문장은 실행되지 않습니다.
		}catch(ArithmeticException e){
			//System.out.println("예외발생");
			e.getMessage();
			//e.printStackTrace();

		}
*/
/*
		try{
			//System.out.println("ABC");
			System.out.println(0/0);
			//System.out.println("123"); //예외 발생이후의 문장은 실행되지 않습니다.
		}catch(Exception e){
			System.out.println("예외발생1");
		}catch(ArithmeticException e){
			System.out.println("예외발생");
		}
*/
	}
}