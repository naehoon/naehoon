package test_20170630;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class UserInfo2 extends SuperUserInfo implements Serializable {
	int age;

	public UserInfo2() {
//		this("Unkown", "1111", 0);
		this(0,"Unkown", "1111");
	}

	public UserInfo2(String name, String password, int age) {
		super(name, password);
		this.age = age;
	}
	
	public UserInfo2(int age, String name, String password) {
		super(name, password);
		this.age = age;
	}
	
	public String toString() {
		return "(" + name + " , " + password + " , "  + age + ")";

	}
	
	//오버라이딩
	private void writeObject(ObjectOutputStream out) throws IOException{
		out.writeUTF(name);
		out.writeUTF(password);
		out.defaultWriteObject();
	}

	//오버라이딩
	private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
		name = in.readUTF();
		password = in.readUTF();
		in.defaultReadObject();
	}
}
