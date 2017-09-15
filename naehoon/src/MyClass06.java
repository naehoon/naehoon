import java.util.*;

public class MyClass06{

	public static void main(String[] arg){

		System.out.println("아이디 패스워드를 입력하세요");

		Scanner scanner = new Scanner(System.in);

		String id = scanner.nextLine();
		String pw = scanner.nextLine();

		//input = Integer.parseInt(tmp);

		if(id.equals("abcd") && pw.equals("1234")){

			System.out.println("로그인");

		}else{

			System.out.println("입력한 정보가 틀립니다.");

		}


	}

}