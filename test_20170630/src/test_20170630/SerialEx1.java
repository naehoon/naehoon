package test_20170630;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class SerialEx1 {
	public static void main(String[]args)	{
		try{
			String fileName = "UserInfo.ser";
			FileOutputStream fos = new FileOutputStream(fileName);
			BufferedOutputStream bos = new BufferedOutputStream(fos);
			ObjectOutputStream out = new ObjectOutputStream(bos);
			
			UserInfo u1 = new UserInfo("Javaman", "1234", 30);
			UserInfo u2 = new UserInfo("JavaWoman", "4321", 26);

//			UserInfo2 u1 = new UserInfo2();
//			UserInfo2 u2 = new UserInfo2();
			ArrayList<UserInfo> list = new ArrayList<>();
			
			list.add(u1);
			list.add(u2);
			 
			out.writeObject(u1);
			out.writeObject(u2);
			out.writeObject(list);
			
			out.close();
			System.out.println("직렬화가 잘 끝났다.");
			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
