package bs.com.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingViewVO {
	
	private int tno;
	private int totalcount;
	private String carno ="";
	private String phone ="";
	private String grade ="";
	private String tstat ="";
	private String startdate ="";
	private String enddate ="";
	private List item ;
	
	public ParkingViewVO() {
	}
	
	public ParkingViewVO(List item) {
		super();
		this.item = item;
	}
	
	public List getItem() {
		return item;
	}
	public void setItem(ArrayList item) {
		this.item = item;
	}
	public int getTno() {
		return tno;
	}
	public void setTno(int tno) {
		this.tno = tno;
	}
	public String getCarno() {
		return carno;
	}
	public void setCarno(String carno) {
		this.carno = carno;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
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
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	
	public boolean isEmpty(){
		return totalcount == 0; 
	}
	
	
	

}
