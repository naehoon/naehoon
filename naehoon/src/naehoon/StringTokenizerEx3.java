package naehoon;

import java.util.StringTokenizer;

public class StringTokenizerEx3 {
	public static void main(String[]args){
		String source = "1,김천재, 100, 100, 100 | 2, 박수재, 20, 20, 20 | 3, 이자바, 30, 30, 30";
		StringTokenizer st = new StringTokenizer(source, "|");
		
		while(st.hasMoreTokens()){
			String token = st.nextToken();
			
			StringTokenizer st2 = new StringTokenizer(token, ",");
			while(st2.hasMoreTokens()){
				System.out.println(st2.nextToken());
			}
			System.out.println("-------");
		}
	}
}
