import java.util.*;

public class Test042701{

	public static void main(String[] arg){
		
		String name = "";
		int age = 0;

		System.out.println("����� �̸��� ���̸� �Է��ϼ���!!!!!!");

		Scanner sc = new Scanner(System.in);

		String tmp = sc.nextLine();
		String tmp2 = sc.nextLine();

		name = tmp;
		age = Integer.parseInt(tmp2);
		
		printInfo(name, age);		

	}

	public static void printInfo(String i, int j){

		System.out.println("�Է¹��� �̸� : " + i);
		System.out.println("�Է¹��� ���� : " + j);

	}

}