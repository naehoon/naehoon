package bs;

import java.util.ArrayList;

public class AddressVO {
	
	private int menu;  //입력받은값
	private ArrayList<String> userInfo; //사용자 정보
	
	public int getMenu() {
		return menu;
	}
	public void setMenu(int menu) {
		this.menu = menu;
	}
	public ArrayList<String> getUserInfo() {
		return userInfo;
	}
	public void setUserInfo(ArrayList item) {
		this.userInfo = item;
	}
	
}
