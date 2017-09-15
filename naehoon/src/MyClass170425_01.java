import java.util.*;

public class MyClass170425_01{

	public static void main(String[] arg){
		
		String munja = "";
		//int i = 0;
		//int sum = 0;

		//char grade = ' ';

		System.out.println("당신의 문자를 입력하세요!!!!!!");

		Scanner sc = new Scanner(System.in);

		String tmp = sc.nextLine();
		
		munja = tmp;

		//System.out.println("문자" + munja.length());

		for(int i=0; i<munja.length(); i++){

			char ch = munja.charAt(i);

			System.out.println(munja.substring(i,i));
			
			//System.out.println(ch);			
		}
		//System.out.println("1부터 입력하신 " + score + " 까지의 합은 " + sum + "입니다.");
	}
}