package deliveryCompany;

public class CompleteDeliveryVO {	// 배달완료 VO　//出前完了 VO
	private int onum;		// 주문번호　//　注文番号
	private int delinum;	// 배달원번호　//出前の人番号
	private String sname;	// 매장명　//店名
	private int snum; //매장번호　//店番号
	private String oaddr; //주문주소　//　注文住所
	private int menuCount; //메뉴개수　//メニューの数
	
	public CompleteDeliveryVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CompleteDeliveryVO(int onum, String sname) {	// 배달원으로 검색할때　//出前の人で検索
		super();
		this.onum = onum;
		this.sname = sname;
	}
	public CompleteDeliveryVO(int onum, int delinum, String sname,int snum, String oaddr, int menuCount) {
		super();
		this.onum = onum;
		this.delinum = delinum;
		this.sname = sname;
		this.snum=snum;
		this.oaddr=oaddr;
		this.menuCount=menuCount;
	}
	public int getOnum() {
		return onum;
	}
	public int getDelinum() {
		return delinum;
	}
	public String getSname() {
		return sname;
	}	
	public int getSnum() {
		return snum;
	}
	public String getOaddr() {
		return oaddr;
	}
	public int getMenuCount() {
		return menuCount;
	}
	public void setOnum(int onum) {
		this.onum = onum;
	}
	public void setSnum(int snum) {
		this.snum = snum;
	}
	public void setDelinum(int delinum) {
		this.delinum = delinum;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public void setOaddr(String oaddr) {
		this.oaddr = oaddr;
	}

	public void setMenuCount(int menuCount) {
		this.menuCount = menuCount;
	}
		
}
