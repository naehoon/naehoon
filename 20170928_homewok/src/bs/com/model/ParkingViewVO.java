package bs.com.model;

import java.util.List;

public class ParkingViewVO {
	
	private List<ParkingTicketVO> list;
	private int count;
	
	public ParkingViewVO() {
		super();
	}
	
	public ParkingViewVO(List<ParkingTicketVO> list , int count) {
		super();
		this.list = list;
		this.count = count;
	}

	public List<ParkingTicketVO> getList() {
		return list;
	}

	public void setList(List<ParkingTicketVO> list) {
		this.list = list;
	}
	
	public boolean isEmpty(){
		return count == 0;
	}
}
