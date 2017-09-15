import java.util.*;

public class MyClass170425_02{

	public static void main(String[] arg){
		
		//String munja = "";

		char [] alpha = new char[26];


/*
		System.out.println("당신의 문자를 입력하세요!!!!!!");

		Scanner sc = new Scanner(System.in);

		String tmp = sc.nextLine();
		
		munja = tmp;

*/

		System.out.println("배열의 길이 : " + alpha.length);

		for(int i=0; i<alpha.length; i++){

			alpha[i] = (char)(65+i);

			System.out.println("알파벳 : " + alpha[i]);

		}
	}
}