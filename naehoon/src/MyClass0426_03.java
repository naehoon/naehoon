import java.util.*;

public class MyClass0426_03{

	public static void main(String[] arg){

		int [] numArr = new int[10];

		for(int i=0; i<numArr.length; i++){

			System.out.print(numArr[i] = (int)(Math.random()*10));
		}

		System.out.println();


		for(int i=0; i<numArr.length-1; i++){
			boolean changed = false;

			for(int j=0; j<numArr.length-1-i; j++){

				if(numArr[j] > numArr[j+1]){//옆의 값이 적으면 서로 바꾼다.

					int temp = numArr[j];
					numArr[j] = numArr[j+1];
					numArr[j+1] = temp;
					changed = true;
				}
			}


			if(!changed) break;

			for(int k=0; k<numArr.length; k++){

				System.out.print(numArr[k]);

			}
			System.out.println();
		}
	}
}