package deliveryCompany;

public class OrderVO {		// 주문목록　//注文リスト
	private int onum;		// 주문번호 (auto_increment)　//注文番号
	private int menuCount; // 배달 상세내용　//出前詳細内容
	private String oaddr;	// 배달 도착주소　//出前到着住所
	private String codename; // 배달코드　//出前code
	private String sname; //매장이름　//店名
	private String saddr; //매장주소　//店住所
	private int delinum;
	private String smessage;

	public OrderVO() {
		super();
	}

	public OrderVO(int onum, int delinum, String oaddr,  int menuCount) {
		super();
		this.onum = onum;
		this.delinum = delinum;
		this.oaddr = oaddr;
		this.menuCount = menuCount;
	}
	
	public OrderVO(String smessage,int onum, String oaddr, int menuCount,  String codename) {
		super();
		this.smessage = smessage;
		this.onum = onum;
		this.menuCount = menuCount;
		this.oaddr = oaddr;
		this.codename = codename;
	}

	public OrderVO(int onum,  String sname, String oaddr, String saddr,
			int menuCount) {
		super();
		this.onum = onum;
		this.menuCount = menuCount;
		this.oaddr = oaddr;
		this.saddr = saddr;
		this.sname = sname;
	}
	
	public OrderVO(int onum, String sname, String saddr ,String oaddr, int menuCount,  
			String codename) {
		super();
		this.onum = onum;
		this.menuCount = menuCount;
		this.oaddr = oaddr;
		this.codename = codename;
		this.sname = sname;
		this.saddr = saddr;
	}
	public OrderVO(String smessage) {
		super();
		this.smessage = smessage;
	}
	
	public int getOnum() {
		return onum;
	}

	public void setOnum(int onum) {
		this.onum = onum;
	}

	public int getMenuCount() {
		return menuCount;
	}

	public void setMenuCount(int menuCount) {
		this.menuCount = menuCount;
	}

	public String getOaddr() {
		return oaddr;
	}

	public void setOaddr(String oaddr) {
		this.oaddr = oaddr;
	}

	public String getCodename() {
		return codename;
	}

	public void setCodename(String codename) {
		this.codename = codename;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getSaddr() {
		return saddr;
	}

	public void setSaddr(String saddr) {
		this.saddr = saddr;
	}
	
	public int getDelinum(){
		return delinum;
	}
	
	public void setDelinum(int delinum){
		this.delinum = delinum;
	}
	
	public String getSmessage() {
		return smessage;
	}

	public void setSmessage(String smessage) {
		this.smessage = smessage;
	}
	
}