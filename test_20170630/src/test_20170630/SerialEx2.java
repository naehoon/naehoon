package test_20170630;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class SerialEx2 {
	public static void main(String[]args)	{
		try{
			String fileName = "UserInfo.ser";
			FileInputStream fis = new FileInputStream(fileName);
			BufferedInputStream bis = new BufferedInputStream(fis);
			
			ObjectInputStream in = new ObjectInputStream(bis);
			
			UserInfo2 u1 = (UserInfo2)in.readObject();
			UserInfo2 u2 = (UserInfo2)in.readObject();
			ArrayList list = (ArrayList)in.readObject();
			
			System.out.println(u1);
			System.out.println(u2);
			System.out.println(list);
			in.close();
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
