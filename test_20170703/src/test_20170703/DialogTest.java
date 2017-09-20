package test_20170703;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogTest {
	public static void main(String[]args){
		Frame f = new Frame("Parent");
		f.setSize(300,200);
		
		final Dialog info = new Dialog(f, "Infomation", true);
		info.setSize(140, 90);
		info.setLocation(50, 50);
		info.setLayout(new FlowLayout());
		
		Label msg = new Label("this is modal dialog ", Label.CENTER);
		Button ok = new Button("OK");
		
		ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				info.setVisible(true);
				info.dispose();
				
			}
		});
		
		info.add(msg);
		info.add(ok);
		f.setVisible(true);
		info.setVisible(true);
	}

}
