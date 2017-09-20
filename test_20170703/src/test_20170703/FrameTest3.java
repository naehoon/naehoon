package test_20170703;

import java.awt.Frame;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class FrameTest3 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Frame f = new Frame("Login");
		f.setSize(300, 200);
		f.addWindowListener(new EventHandler());
		f.setVisible(true);
	}
}

class EventHandler implements WindowListener {

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		e.getWindow().setVisible(false);
		e.getWindow().dispose();
		System.exit(0);

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

}
