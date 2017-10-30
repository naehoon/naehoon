package com.bs.vo;

public class ProductVo {
	
	String pid ;
	String pName;
	int amount;
	int price;
	
	public ProductVo() {
		super();
	}
	
	public ProductVo(String pid, String pName, int amount, int price) {
		super();
		this.pid = pid;
		this.pName = pName;
		this.amount = amount;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "ProductVo [pid=" + pid + ", pName=" + pName + ", amount=" + amount + ", price=" + price + "]";
	}

	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	

}
