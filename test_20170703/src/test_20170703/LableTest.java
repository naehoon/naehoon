package test_20170703;

import java.awt.Frame;
import java.awt.Label;

public class LableTest {
	public static void main(String[]args){
		Frame f = new Frame("Login");
		f.setSize(300, 200);
		f.setLayout(null);
		
		Label id = new Label("ID : ");
		id.setBounds(50, 50, 30, 10);
		
		Label pwd = new Label("PASSWORD : ");
		pwd.setBounds(50, 65, 100, 10);
		
		f.add(id);
		f.add(pwd);
		f.setVisible(true);
		
	}

}
