import java.util.*;

public class Bubble{

	public static void main(String[] arg){

		System.out.println("����");

		int [] numArr = new int[10];

		inputData(numArr);
		bubbleSort(numArr);
		printData(numArr);

	}

	//���� ������ ���� �޼��� 
	static int[] inputData(int[] numArr){
	
		for(int i=0; i<numArr.length; i++){

			//System.out.print(numArr[i] = (int)(Math.random()*10)+1);
			numArr[i] = (int)(Math.random()*10+1);
		}

		return numArr;
	}
	
	//�������� �޼��� 
	static int[] bubbleSort(int[] numArr){

		for(int i=0; i<numArr.length-1; i++){
			boolean changed = false;

			for(int j=0; j<numArr.length-1-i; j++){

				if(numArr[j] > numArr[j+1]){//���� ���� ������ ���� �ٲ۴�.

					int temp = numArr[j];
					numArr[j] = numArr[j+1];
					numArr[j+1] = temp;
					changed = true;
				}
			}


			if(!changed) break;

			for(int k=0; k<numArr.length; k++){

				System.out.print(numArr[k]);

			}System.out.println();


		}
		
		return numArr;
	}

	//��� �޼��� 
	static void printData(int[] numArr){

		for(int k=0; k<numArr.length; k++){
			System.out.print(numArr[k]);
		}
	}	

}