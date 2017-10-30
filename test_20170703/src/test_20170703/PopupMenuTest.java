package test_20170703;
import java.awt.Frame;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PopupMenuTest {
	
	public static void main(String[]args){
		final Frame f = new Frame("PopupMenu Test");
		f.setSize(300, 200);
		
		final PopupMenu pMenu  = new PopupMenu("Edit");
		MenuItem miCut = new MenuItem("Cut");
		MenuItem miCopy = new MenuItem("Copy");
		MenuItem miPaste = new MenuItem("Paste");
		
		pMenu.add(miCut);
		pMenu.add(miCopy);
		pMenu.add(miPaste);
		
		f.add(pMenu);
		f.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent me){
				if(me.getModifiers()==me.BUTTON3_MASK)
					pMenu.show(f, me.getX(), me.getY());
			}
		});
		f.setVisible(true);
	}
}
