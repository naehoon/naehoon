import java.util.*;

public class MyClass170424_01{

	public static void main(String[] arg){

		int[] num = new int[100];
		int sum = 0;
		int zak = 0;
		int hol = 0;

		for(int i=0; i<num.length; i++){
			num[i] = (int)(Math.random()*100);

			if(num[i]%2 == 0){
				zak++;
			}else{
				hol++;
			}

		}

		for(int i=0; i<num.length; i++){

			sum += num[i];
			
			System.out.println((i+1) + " ��° �迭 : num = " + num[i]);
			
		}

		System.out.println("¦���� " + zak + "�� �Դϴ�");
		System.out.println("Ȧ���� " + hol + "�� �Դϴ�");
		System.out.println("�հ�� : " + sum + "�Դϴ�");
	}
}