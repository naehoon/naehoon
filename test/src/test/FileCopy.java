package test;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class FileCopy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try{
			FileInputStream fis = new FileInputStream(args[0]);
			FileOutputStream fos = new FileOutputStream(args[1]);
			
			int data = 0;
			
			while((data=fis.read())!=-1){
				fos.write(data);
			}
			
			fis.close();
			fos.close();
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
