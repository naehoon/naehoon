package bs.com.model;

import java.util.List;

public class ParkManageVO {
	
	private int parkno; //����� ��ȣ 
	private String carno;  //������ȣ 
	private String phone;  //���� ��ȭ
	private String tno;  //����� ��ȣ 
	private String grade ; //���
	private String tstat; //����
	private String indate; //�԰�����
	private String outdate; //�������
	private List item; //����Ʈ ��ȸ��

	public ParkManageVO() {
	}
	
	public ParkManageVO(List item) {
		super();
		this.item = item;
	}
	
	public String getTno() {
		return tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getParkno() {
		return parkno;
	}
	public void setParkno(int parkno) {
		this.parkno = parkno;
	}
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getTstat() {
		return tstat;
	}
	public void setTstat(String tstat) {
		this.tstat = tstat;
	}
	public String getIndate() {
		return indate;
	}
	public void setIndate(String indate) {
		this.indate = indate;
	}
	public String getOutdate() {
		return outdate;
	}
	public void setOutdate(String outdate) {
		this.outdate = outdate;
	}
	public List getItem() {
		return item;
	}
	public void setItem(List item) {
		this.item = item;
	}

	@Override
	public String toString() {
		return this.getTstat();
	}
	
	

}
