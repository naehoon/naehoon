import java.util.*;

public class MyClass06{

	public static void main(String[] arg){

		System.out.println("���̵� �н����带 �Է��ϼ���");

		Scanner scanner = new Scanner(System.in);

		String id = scanner.nextLine();
		String pw = scanner.nextLine();

		//input = Integer.parseInt(tmp);

		if(id.equals("abcd") && pw.equals("1234")){

			System.out.println("�α���");

		}else{

			System.out.println("�Է��� ������ Ʋ���ϴ�.");

		}


	}

}