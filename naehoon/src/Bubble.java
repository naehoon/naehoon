import java.util.*;

public class Bubble{

	public static void main(String[] arg){

		System.out.println("시작");

		int [] numArr = new int[10];

		inputData(numArr);
		bubbleSort(numArr);
		printData(numArr);

	}

	//랜덤 데이터 생성 메서드 
	static int[] inputData(int[] numArr){
	
		for(int i=0; i<numArr.length; i++){

			//System.out.print(numArr[i] = (int)(Math.random()*10)+1);
			numArr[i] = (int)(Math.random()*10+1);
		}

		return numArr;
	}
	
	//버블정렬 메서드 
	static int[] bubbleSort(int[] numArr){

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

			}System.out.println();


		}
		
		return numArr;
	}

	//출력 메서드 
	static void printData(int[] numArr){

		for(int k=0; k<numArr.length; k++){
			System.out.print(numArr[k]);
		}
	}	

}