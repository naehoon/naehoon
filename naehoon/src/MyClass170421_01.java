import java.util.*;

public class MyClass170421_01{

	public static void main(String[] arg){
		
		char input;
		//int answer = 0;

		//answer = (int)(Math.random()*100) + 1;
		Scanner sc = new Scanner(System.in);
		
		do{
			System.out.print("������ ���ڸ� �Է����ּ��� S����, O����, X����");

			String tmp = sc.nextLine();

			input = tmp.charAt(0);			

			//input = Integer.parseInt(tmp);

			if(input == 'S'){

				System.out.println("����Ǿ����ϴ�");
				//break;

			}else if(input == 'O'){
				System.out.println("������ ���ϴ�.");				
				//break;

			}else if(input == 'X'){
				System.out.println("�����մϴ�");				
			}

		}while(input == 'S' || input == 'O');
	}

}