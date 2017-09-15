package deliveryCompany;

public class DailyVO {//데일리 VO　//　DailyVO
	private String date;	//날짜　//　日付
	private int delinum;	//배달원번호　//　出前の人番号
	private String sname;	//매장명　//　店名
	private int menuCount;	//메뉴개수　//　メニューの数
	private int sumCount;	//메뉴개수 합산값　//　メニューの数の合計

	public DailyVO(String date, int delinum, String sname, int menuCount,
			int sumCount) {
		super();
		this.date = date;
		this.delinum = delinum;
		this.sname = sname;
		this.menuCount = menuCount;
		this.sumCount = sumCount;
	}
	public DailyVO(String date, int delinum, String sname, int menuCount) {
		super();
		this.date = date;
		this.delinum = delinum;
		this.sname = sname;
		this.menuCount = menuCount;
	}
	
	public DailyVO(String date, int delinum, int menuCount) {
		super();
		this.date = date;
		this.delinum = delinum;
		this.menuCount = menuCount;
	}
	public int getSumCount() {
		return sumCount;
	}
	public void setSumCount(int sumCount) {
		this.sumCount = sumCount;
	}
	public String getDate() {
		return date;
	}
	public int getDelinum() {
		return delinum;
	}
	public String getSname() {
		return sname;
	}
	public int getMenuCount() {
		return menuCount;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public void setDelinum(int delinum) {
		this.delinum = delinum;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public void setMenuCount(int menuCount) {
		this.menuCount = menuCount;
	}
}
