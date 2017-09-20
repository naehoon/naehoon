package bs;

import java.util.ArrayList;
import java.util.Scanner;

public class AddressController {
	
	AddressVO av = new AddressVO();
	
	public void inputMethod(ArrayList item) { //메서드
		av.setUserInfo(item);
	}

	public ArrayList outputMethod(){
		ArrayList al = av.getUserInfo();
		return al;
	}
}
