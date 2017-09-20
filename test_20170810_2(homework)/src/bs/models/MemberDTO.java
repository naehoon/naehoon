package bs.models;

import java.io.Serializable;

//data transfer object ( this is java beans ) VO 와 다른점은 Serializable 을 구현한다는것이다.
public class MemberDTO implements Serializable{ 
	
	//file Stream 직렬화 할때 사용한다.
	//클래스에 직렬화가 붙으면 
	
	private int num;
	private String id;
	private String pass;
	private String name;
	private String gender;
	private String hobby;
	private String tel;
	private String addr;
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHobby() {
		return hobby;
	}
	public void setHobby(String hobby) {
		this.hobby = hobby;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	
	
	
}
