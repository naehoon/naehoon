import java.util.*;

public class MyClass07{

	public static void main(String[] arg){

		System.out.println("������� �Ϻ�������, IT ������ �Է��ϼ���");

		Scanner scanner = new Scanner(System.in);
		String tmpjapan = scanner.nextLine();
		String tmpit = scanner.nextLine();

		int japan = Integer.parseInt(tmpjapan);
		int it = Integer.parseInt(tmpit);

		if(japan >= 60 || it >= 60){

			System.out.println("�հ�");

		}else{

			System.out.println("���հ�");

		}

	}

}