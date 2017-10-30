package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class IOEx01 {
	
	public static void main(String[]args){
		byte[] inScr = {0,1,2,3,4,5,6,7,8,9};
		byte[] outScr =null;
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inScr);
		output = new ByteArrayOutputStream();
		
		int data = 0;
		
		while((data = input.read())!=-1){
			output.write(data);
		}
		
		outScr = output.toByteArray();
		
		System.out.println("input source : " + Arrays.toString(inScr));
		System.out.println("output source : " + Arrays.toString(outScr));
	}

}
