package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class IOEx03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte[] inScr = {0,1,2,3,4,5,6,7,8,9};
		byte[] outScr =null;
		
		byte[] temp = new byte[4];
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inScr);
		output = new ByteArrayOutputStream();
		
		System.out.println("input source : " + Arrays.toString(inScr));
		
		try{
			while(input.available() > 0){
				input.read(temp);
				output.write(temp);
				//System.out.println("temp : " + Arrays.toString(temp));
				
				outScr = output.toByteArray();
				printArrays(temp, outScr);
			}
		}catch(Exception e){
			e.printStackTrace();
		}

	}
	static void printArrays(byte[] temp, byte[] outSrc){
		System.out.println("temp :" + Arrays.toString(temp));
		System.out.println("output source : "  + Arrays.toString(outSrc));
	}
}
