import java.util.*;

public class MyClass170425_03{

	public static void main(String[] arg){
		
		String munja = "";

		char [] alpha = new char[26];
		//char [] count2 = new char[26];
		int [] count = new int[26];
 
		System.out.println("����� ���ĺ��� �Է��ϼ���!!!!!!");

		Scanner sc = new Scanner(System.in);

		String tmp = sc.nextLine();
		munja = tmp;

		//System.out.println("�迭�� ���� : " + alpha.length);
		//System.out.println("�Է¹��� ���� : " + munja);
		//System.out.println("�Է¹��� ���� ����: " + munja.length());
		
		//System.out.println("char: " + ch);

		for(int i=0; i<alpha.length; i++){

			//char ch = munja.charAt(i);
			//System.out.print(ch);
			
			alpha[i] = (char)(65+i);
			//System.out.print(alpha[i]);

			for(int j=0; j<munja.length(); j++){
				String big = munja.toUpperCase();

				if(big.charAt(j)==alpha[i]){
					count[i]++;
					
					//System.out.print("count : " + count[i]);
					//System.out.print(count[i]);
				}
				//System.out.print(count[i]);
				
			}
			//char[] alpha = new char[i];
			//System.out.print(count[i]);

		}

		System.out.println(alpha);

		for(int k=0; k<count.length; k++){
			System.out.print(count[k]);
		}
	}
}