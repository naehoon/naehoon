package serviceMen;

public class ServiceMenVO {		// 배달원정보。出前の人の情報
	private int no; 		// 배달원 고유번호。出前の人の固有の番号
	private String name;	// 배달원이름。出前の人の名前。
	private String tel; 	// 배달원 휴대폰번호。出前の人の電話番号
	private int onum;		// 주문번호。注文番号
	
	public ServiceMenVO(int no, String name, String tel, int onum) {
		super();
		this.no = no;
		this.name = name;
		this.tel = tel;
		this.onum = onum;
	}
	public ServiceMenVO(int no, String name, String tel) {	// 배달원 등록。出前の人を　登録。
		this.no = no;
		this.name = name;
		this.tel = tel;
	}
	
	public ServiceMenVO(String name, String tel){ // 배달원로그인。出前の人ログイン。
		this.name=name;
		this.tel=tel;
	}
	
	public ServiceMenVO() {
		// TODO Auto-generated constructor stub
	}
	public int getNo() {
		return no;
	}	
	public String getName() {
		return name;
	}
	public String getTel() {
		return tel;
	}
	public int getOnum() {
		return onum;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setOnum(int onum) {
		this.onum = onum;
	}
}
