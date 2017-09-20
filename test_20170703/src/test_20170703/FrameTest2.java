package test_20170703;

import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Toolkit;
import java.io.UnsupportedEncodingException;

public class FrameTest2 {
	
	public static void main(String[]args) throws UnsupportedEncodingException{
		Frame f = new Frame("Login");
		f.setSize(300, 200);
		f.setLayout(null); 
		
		Toolkit tk  = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		
//		String name = new String("ï¿½Ñ±ï¿½".getBytes("EUC-KR"),"EUC-KR");
//		String name = new String("ï¿½Ñ±ï¿½".getBytes("UTF-8"),"UTF-8");
//		String name = new String("ÇÑ±Û".getBytes("EUC-KR"),"EUC-KR");
//		String name = newString("ÇÑ±Û".getBytes("MS949"),"MS949");
		String name ="ÇÑ±Û";
		
//		name.getBytes("MS949");
		
		Button b = new Button(name);
		b.setSize(100, 50);
		b.setLocation(100, 75);
		f.add(b);
		
		f.setLocation(screenSize.width/2-150, screenSize.height/2-100);
		f.setVisible(true);
	}

}
