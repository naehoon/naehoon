import java.util.*;

public class MyClass05{

	public static void main(String[] arg){

		int input;
		char gender;

		System.out.println("������ �Է��ϼ��� M/Y ����/����");

		Scanner scanner = new Scanner(System.in);
		String tmp = scanner.nextLine();

		//input = Integer.parseInt(tmp);

		gender = tmp.charAt(0);

		System.out.println(gender);

		if(gender == 'M'){

			System.out.println("����");

		}else if(gender == 'Y'){

			System.out.println("����");

		}


	}

}