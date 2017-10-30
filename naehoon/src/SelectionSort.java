public class SelectionSort {
  public static void main(String[] args) {
    int[] index = new int[10];
    int i, j, min;
    int temp = 0;

    for(int k=0; k<index.length; k++){
	index[k] = (int)(Math.random()*10);
    }


    for (i = 0; i < index.length; i++) {
      min = index[i];
      for (j = i; j < index.length - 1; j++) {
        if (min > index[j + 1]) {
          min = index[j + 1];
          temp = j + 1;
        }
      }
      index[temp] = index[i];
      index[i] = min;
    }
    for (i = 0; i < index.length; i++) {
      System.out.print(index[i] + " ");
    }
  }
}