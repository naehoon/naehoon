import java.util.*;

public class MyClass170425_04{

	public static void main(String[] arg){
		
		int [] num = new int[10];

		for(int i=0; i < num.length; i++){
			num[i] = (int)(Math.random()*100)+1;
			System.out.println("���� ���� ���� : " + num[i]);
		}

        	int max = num[0];
	        int min = num[0];

 	        for(int i = 0; i < num.length; i++) {
        	    if (num[i] > max)
                	max = num[i];
	            if (num[i] < min)
        	        min = num[i];
        	}
        	
		System.out.println("�ִ밪 : " + max + "\n" + "�ּҰ� : " + min);
		
	}
}