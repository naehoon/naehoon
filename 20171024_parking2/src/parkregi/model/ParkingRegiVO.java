package parkregi.model;

import java.util.ArrayList;
import java.util.List;

public class ParkingRegiVO {

	private int tno;
	private int totalcount;
	private String carno ="";
	private String phone ="";
	private String grade ="";
	private String tstat ="";
	private String startdate ="";
	private String enddate ="";
	private int total;
	private int currentPage;
	private int totalPages;
	private int startPage;
	private int endPage;
	private List item ;

	//생성자 
	public ParkingRegiVO(int total, int currentPage,int size, List item) {
		this.total = total;
		this.currentPage = currentPage;
		this.item = item;
		
		if(total ==0 ){
			totalPages = 0;
			startPage = 0;
			endPage = 0;
		}else{
			totalPages = total / size;
			if(total % size  > 0){
				totalPages++;
			}
			int modVal = currentPage%5;
			startPage = currentPage / 5*5 + 1;
			if(modVal == 0){
				startPage -=5;
			}
			
			endPage = startPage  +4;
			if(endPage > totalPages){
				endPage = totalPages;
			}
		}
	}
	
	
	public ParkingRegiVO(int tno,  String carno, String phone, String grade, String tstat,
			String startdate, String enddate) {
		super();
		this.tno = tno;
		this.carno = carno;
		this.phone = phone;
		this.grade = grade;
		this.tstat = tstat;
		this.startdate = startdate;
		this.enddate = enddate;
	}

	public ParkingRegiVO(String carno) {
		super();
		this.carno = carno;
	}

	public ParkingRegiVO(int maxNum) {
		super();
		this.tno = maxNum;
	}

	public ParkingRegiVO() {
		
	}
	
	public ParkingRegiVO(List item) {
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

	public int getTotalcount() {
		return totalcount;
	}


	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public int getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}


	public int getTotalPages() {
		return totalPages;
	}


	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}


	public int getStartPage() {
		return startPage;
	}


	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}


	public int getEndPage() {
		return endPage;
	}


	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}


	public void setItem(List item) {
		this.item = item;
	}
	
	public boolean hasNoArticles(){
		return total ==0;
	}
	
	public boolean hasArticles(){
		return total  > 0;
	}

	@Override
	public String toString() {
		return grade + tstat;
	}

	

}
