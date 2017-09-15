class ArrayEx3{

	public static void main(String[]args){

		int [] arr = new int[3];

		//배열 arr 에 1~5 를 저장한다.
		for(int i=0; i<arr.length; i++){
			arr[i] = i +1;
		}
		System.out.println("변경전 - arr.length : " +arr.length);

		for(int i=0; i<arr.length; i++){
			System.out.println("arr[" +i+"] : "+arr[i]);
		}

		int[] tmp = new int[arr.length*2];

		//배열 arr 에 저장된 값들을 배열 tmp 에 복사한다.
		for(int i=0; i<arr.length; i++){
			tmp[i] = arr[i];
		}

		arr = tmp;

		System.out.println("변경후 - arr.length : " + arr.length);

		for(int i=0; i<arr.length; i++){
			System.out.println("arr["+i+"] : "+arr[i]);
		}

	}

}
