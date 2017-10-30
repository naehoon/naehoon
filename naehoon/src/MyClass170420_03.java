import java.util.*;

public class MyClass170420_03{

	public static void main(String[] arg){

		int sum = 0;
		//int sum2 = 0;

		for(int i=1; i<=100; i++){

			if(i%3 ==0){
				sum = sum + i;
				System.out.println("3의 배수의 합" + sum + "  " + i);

			}

		}

				//System.out.print("짝수의합" + sum2 + " " + i);
	}
}