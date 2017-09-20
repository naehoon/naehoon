package test_20170703;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MouseEventTest extends Frame{
	
	Label location;
	
	MouseEventTest(String title){
		super(title);
		location = new Label("Mouse Pointer Location : ");
		location.setSize(195, 15);
		location.setLocation(5, 30);
		location.setBackground(Color.yellow);
		add(location);
		
		addMouseMotionListener(new EventHandler());
		
		setSize(300, 200);
		setLayout(null);
		setVisible(true);
		
	}
	
	public static void main(String[]args){
		MouseEventTest mainWin = new MouseEventTest("Mouse EventTest");
	}
	
	class EventHandler implements MouseMotionListener{

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub
			location.setText("Mouse Pointer Location  : (" + e.getX() + " , " + e.getY() + " )" );
		}
	}
}
