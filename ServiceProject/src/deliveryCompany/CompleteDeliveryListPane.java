package deliveryCompany;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
//완료배달 테이블을 보여주는 클래스
public class CompleteDeliveryListPane extends JPanel {
	private JTable completeTable;
	private JPanel base;
	private JPanel completeDeliveryPanel;

	public CompleteDeliveryListPane(){

		base=new JPanel();

		base.setLayout(new BorderLayout());

		completeTable=new JTable(new CompleteDeliveryModel());

		completeDeliveryPanel=new JPanel();
		JLabel completeDeliveryLabel=new JLabel("完了リスト", JLabel.CENTER);

		completeDeliveryPanel.setLayout(new BorderLayout());
		completeDeliveryPanel.add(completeDeliveryLabel,"North");
		completeDeliveryPanel.add(new JScrollPane(completeTable));
		completeTable.setPreferredScrollableViewportSize(new Dimension(330,530));//테이블의 크기조절//テーブルのサイズ

		EtchedBorder eb = new EtchedBorder();
		completeDeliveryPanel.setBorder(eb);
		add(base);
		base.add(completeDeliveryPanel, BorderLayout.CENTER);

	}

	//들어오는 변수에 따른 셋모델의 분류//変数によってsetModel分類
	public void setTable(){
		completeTable.setModel(new CompleteDeliveryModel());
	}

	public void setTable(String sname){
		completeTable.setModel(new CompleteDeliveryModel(sname));
	}
}