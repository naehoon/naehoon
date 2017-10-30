package test_20170703;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Frame;

public class CanvasTest {
	
	public static void main(String[]args){
		Frame f = new Frame("canvasTest");
		f.setSize(300, 200);
		f.setLayout(null);
		
		Canvas c = new Canvas();
		c.setBackground(Color.pink);
		c.setBounds(50, 50, 150, 100);
		
		f.add(c);
		f.setVisible(true);
	}
	

}
