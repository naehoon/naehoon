package test_20170703;

import java.awt.FileDialog;
import java.awt.Frame;

public class FileDialogTest {
	
	public static void main(String[]args){
		Frame f = new Frame("Parent");
		f.setSize(300, 200);
		
		FileDialog fileOpen = new FileDialog(f, "파일열기", FileDialog.LOAD);
		
		f.setVisible(true);
		fileOpen.setDirectory("D:\\util");
		fileOpen.setVisible(true);
		
		System.out.println(fileOpen.getDirectory() + fileOpen.getFile());
	}
}
