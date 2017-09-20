package test_20170703;

import java.awt.Frame;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextComponentEventTest extends Frame{
	
	TextField tf;
	TextArea ta;
	
	TextComponentEventTest(String title){
		super(title);
		
		tf = new TextField();
		ta = new TextArea();
		
		add(ta, "Center");
		add(tf, "South");
		
		tf.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ta.append(tf.getText() + " \n");
				tf.setText("");
				tf.requestFocus();
			}
		});
		
		ta.setEditable(false);
		setSize(300, 200);
		setVisible(true);
		tf.requestFocus();
	}
	
	public static void main(String[]args){
		TextComponentEventTest mainWin= new TextComponentEventTest("TextComponentEventTest");
	}
}
