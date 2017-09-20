package test_20170703;

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class JavaJTableEx01 extends JFrame{
	
	Object[][] data = {{"2017-10001", "park", "Vice Chief", 10, "park@naver.com"},
			 				{"2017-10002", "kim", "Manager", 20, "kim@naver.com"},
							{"2017-10003", "Hong", "director", 30, "hong@naver.com"}
							};
	
	String [] columnName = {"ID-No", "Name", "Title", "Major", "Email"};
	
	JTable table = new JTable(data, columnName);
	
	public JavaJTableEx01(){
		add(new JScrollPane(table), BorderLayout.CENTER);
		setSize(500, 200);
		setVisible(true);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
}
