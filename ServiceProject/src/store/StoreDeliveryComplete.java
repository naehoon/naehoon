package store;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class StoreDeliveryComplete extends JPanel { // 배달완료테이블을 보여주는 tap　//　出前完了テーブルをみせるtap
	private JTable completeTable;
	public StoreDeliveryComplete(){}
	public StoreDeliveryComplete(int snum) {
		completeTable = new JTable(new StoreDeliveryCompleteModel(snum)); // 받아온 매장번호와 일치하는 배달완료테이블 생성　//　もらった店番号と同じ出前完了テーブルを生成
		add(new JScrollPane(completeTable)); // 스크롤펜 추가　//　スクロルペン追加
	}

	// 다른클래스에서 테이블을 새로고침하기 위한 메소드
	public JTable getTable(){
		return completeTable;
	}
}
