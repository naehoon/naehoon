import java.util.*;

public class ArrayEx11{

	public static void main(String[] arg){
		/*
		Scanner scanner = new Scanner(System.in);

		System.out.print("��ճ� �л����� �Է��ϼ���");

		String number = scanner.nextLine(); // �л��� 

		*/

		int[] numArr = new int[10];
		int[] counter = new int[10];

		inputData(numArr);
		counterData(numArr, counter);
		printData(numArr, counter);

	}


	//���� �߻� �޼���
	static void inputData(int[] numArr){

		for(int i=0; i<numArr.length; i++){
			numArr[i] = (int)(Math.random()*10);
			System.out.print(numArr[i]);

		}

		System.out.println();

	}


	//ī���� �޼���
	static void counterData(int[] numArr, int[] counter){

		for(int i=0; i<numArr.length; i++){

			counter[numArr[i]]++;

		}
	}


	//��� �޼���
	static void printData(int[] numArr, int[] counter){

		for(int i=0; i<numArr.length; i++){
			System.out.println(i + "���� : " + counter[i]);
		}

	}

}