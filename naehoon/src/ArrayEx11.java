import java.util.*;

public class ArrayEx11{

	public static void main(String[] arg){
		/*
		Scanner scanner = new Scanner(System.in);

		System.out.print("평균낼 학생수를 입력하세요");

		String number = scanner.nextLine(); // 학생수 

		*/

		int[] numArr = new int[10];
		int[] counter = new int[10];

		inputData(numArr);
		counterData(numArr, counter);
		printData(numArr, counter);

	}


	//난수 발생 메서드
	static void inputData(int[] numArr){

		for(int i=0; i<numArr.length; i++){
			numArr[i] = (int)(Math.random()*10);
			System.out.print(numArr[i]);

		}

		System.out.println();

	}


	//카운터 메서드
	static void counterData(int[] numArr, int[] counter){

		for(int i=0; i<numArr.length; i++){

			counter[numArr[i]]++;

		}
	}


	//출력 메서드
	static void printData(int[] numArr, int[] counter){

		for(int i=0; i<numArr.length; i++){
			System.out.println(i + "개수 : " + counter[i]);
		}

	}

}