package parkmanage.model;

import java.util.List;

public class ParkManageVO {
	
	private int parkno; // 
	private String carno;  // 
	private String phone;  //
	private String tno;  // 
	private String grade ; //
	private String tstat; //
	private String indate; //
	private String outdate; //
	private int total;
	private int currentPage;
	private int totalPages;
	private int startPage;
	private int endPage;
	private List item; //
	
	//생성자 
	public ParkManageVO(int total, int currentPage,int size, List item) {
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

	public ParkManageVO() {
	}
	
	public ParkManageVO(String carno, String grade, String indate, String outdate) {
		super();
		this.carno = carno;
		this.grade = grade;
		this.indate = indate;
		this.outdate = outdate;
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

	public ParkManageVO(String carno) {
		super();
		this.carno = carno;
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
	
	public boolean hasNoArticles(){
		return total ==0;
	}
	
	public boolean hasArticles(){
		return total  > 0;
	}

	@Override
	public String toString() {
		return this.getTstat();
	}
	
	

}
