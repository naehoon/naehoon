package store;

public class StoreVO { // 매장정보　//　店の情報
	private int no; // 매장 고유번호　//　店番号
	private String pass; // 매장 비밀번호 (로그인 비밀번호)　//店のパスワード　（ログインパスワード）
	private String name; // 매장명　//　店名
	private String address; // 매장주소　　//　店の住所
	private String tel; // 매장 전화번호　　//　店の電話番後

	public StoreVO() {
		super();
	}

	public StoreVO(int no, String name, String address, String tel, String pass) { // 매장등록　//　店登録

		this.no = no;
		this.name = name;
		this.address = address;
		this.tel = tel;
		this.pass = pass;
	}

	public StoreVO(int no, String name, String address) { // 비밀번호와 전화번호로 매장번호　　//　パスワードと電話番号で店の番号
															// 검색할때　　//　検索する時
		this.no = no;
		this.name = name;
		this.address = address;
	}

	public StoreVO(int no, String pass, String name, String tel) { // 매장번호와　　//　店の番号と
																	// 전화번호로　　//　電話番号で
																	// 비밀번호 검색할때　　//　パスワードを検索する時
		this.no = no;
		this.pass = pass;
		this.name = name;
		this.tel = tel;
	}

	public int getNo() {
		return no;
	}

	public String getPass() {
		return pass;
	}

	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getTel() {
		return tel;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public void setStatus(String string) {
		// TODO Auto-generated method stub

	}

}
