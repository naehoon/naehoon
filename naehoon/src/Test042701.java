import java.util.*;

public class Test042701{

	public static void main(String[] arg){
		
		String name = "";
		int age = 0;

		System.out.println("당신의 이름과 나이를 입력하세요!!!!!!");

		Scanner sc = new Scanner(System.in);

		String tmp = sc.nextLine();
		String tmp2 = sc.nextLine();

		name = tmp;
		age = Integer.parseInt(tmp2);
		
		printInfo(name, age);		

	}

	public static void printInfo(String i, int j){

		System.out.println("입력받은 이름 : " + i);
		System.out.println("입력받은 나이 : " + j);

	}

}