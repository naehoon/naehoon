package test_20170630;

public class SuperUserInfo {
	
	String name;
	String password;
	
	SuperUserInfo() {
		this("Unkown", "1111");
	}
	
	SuperUserInfo(String name, String password){
		this.name = name;
		this.password = password;
	}
}

