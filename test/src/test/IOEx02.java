package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.util.Arrays;

public class IOEx02 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] inScr = {0,1,2,3,4,5,6,7,8,9};
		byte[] outScr =null;
		
		byte[] temp = new byte[10];
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inScr);
		output = new ByteArrayOutputStream();
		
		input.read(temp, 0, temp.length);
		output.write(temp, 5, 5);
		outScr = output.toByteArray();
		
		System.out.println("input source : " +Arrays.toString(inScr));
		System.out.println("temp : "  + Arrays.toString(temp));
		System.out.println("output   : " + Arrays.toString(outScr));
	}

}
