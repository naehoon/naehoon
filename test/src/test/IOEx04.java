package test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Arrays;

public class IOEx04 {
	
	public static void main(String[]args){
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outScr =null;
		
		byte[] temp = new byte[4];
		
		ByteArrayInputStream input = null;
		ByteArrayOutputStream output = null;
		
		input = new ByteArrayInputStream(inSrc);
		output = new ByteArrayOutputStream();
		
		try{
			while(input.available() > 0){
				int len = input.read(temp);
				output.write(temp, 0, len);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		outScr = output.toByteArray();
		
		System.out.println("input source :" + Arrays.toString(inSrc));
		System.out.println("temp :"  +Arrays.toString(temp));
		System.out.println("output source : " + Arrays.toString(outScr));
	}

}
