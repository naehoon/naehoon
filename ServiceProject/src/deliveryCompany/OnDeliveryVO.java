package deliveryCompany;

public class OnDeliveryVO {		// 배달중 목록　//　出前中リスト
	private int onum;			// 주문번호　//　注文番号
	private int delinum;		// 배달원번호　//出前の人番号
	private String snum;		// 매장번호　//店番号
	public OnDeliveryVO(int delinum, String snum, String oaddr) {	// 매장번호로 검색했을때　//　店番号で検索

		this.delinum = delinum;
		this.snum = snum;
		this.oaddr = oaddr;
	}
	public OnDeliveryVO(int onum, int delinum, String snum, String oaddr) {

		this.onum = onum;
		this.delinum = delinum;
		this.snum = snum;
		this.oaddr = oaddr;
	}
	public int getOnum() {
		return onum;
	}
	public int getDelinum() {
		return delinum;
	}
	public String getSnum() {
		return snum;
	}
	public String getOaddr() {
		return oaddr;
	}
	public void setOnum(int onum) {
		this.onum = onum;
	}
	public void setDelinum(int delinum) {
		this.delinum = delinum;
	}
	public void setSnum(String snum) {
		this.snum = snum;
	}
	public void setOaddr(String oaddr) {
		this.oaddr = oaddr;
	}
	private	String oaddr;		// 배달 도착주소　//　配達到着住所
}
