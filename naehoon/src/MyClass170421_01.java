import java.util.*;

public class MyClass170421_01{

	public static void main(String[] arg){
		
		char input;
		//int answer = 0;

		//answer = (int)(Math.random()*100) + 1;
		Scanner sc = new Scanner(System.in);
		
		do{
			System.out.print("다음의 문자를 입력해주세요 S저장, O열기, X종료");

			String tmp = sc.nextLine();

			input = tmp.charAt(0);			

			//input = Integer.parseInt(tmp);

			if(input == 'S'){

				System.out.println("저장되었습니다");
				//break;

			}else if(input == 'O'){
				System.out.println("파일을 엽니다.");				
				//break;

			}else if(input == 'X'){
				System.out.println("종료합니다");				
			}

		}while(input == 'S' || input == 'O');
	}

}