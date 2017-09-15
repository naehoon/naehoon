package store;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class StorePaneDeliver extends JPanel implements MouseListener,
ActionListener { // 배달현황 tap　//　配達現況tap

	private JPanel jp[] = new JPanel[3];
	private JLabel jl[] = new JLabel[2];
	private JTextArea ta = new JTextArea();
	private JButton okb;
	private JTable deliveryTable;
	private int snum; // 매장번호를 저장하기위한 변수 //　店番号をもらうための変数
	private int onum; // 주문번호를 저장하기위한 변수　//　注文番号をもらうための変数

	public StorePaneDeliver() {
		super();
	}

	public StorePaneDeliver(int snum) {

		this.snum = snum;
		new StoreDeliveryListModel();
		deliveryTable = new JTable(new StoreDeliveryListModel(snum)); // snum을 이용해 매장의
		// 주문목록테이블을
		// 불러온다　//　snumを利用して店の注文テーブルを呼出

		setLayout(new GridLayout(2, 1));
		jp[0] = new JPanel();
		jp[0].add(new JScrollPane(deliveryTable));
		add(jp[0]);

		deliveryTable.setPreferredScrollableViewportSize(new Dimension(400, 200));
		jp[1] = new JPanel();
		jl[0] = new JLabel("メッセージ   :");
		ta = new JTextArea(5, 32);

		jp[1].add(jl[0]);
		jp[1].add(ta);
		okb = new JButton("確認");
		okb.addActionListener(this);
		jp[1].add(okb);
		add(jp[1]);

		deliveryTable.addMouseListener(this);

	}

	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = deliveryTable.getSelectedRow();

		if (e.getClickCount() == 1) { // 마우스를 한번 클릭하면　// クリックした時
			ta.setText((String) (new StoreDeliveryListModel(snum)).getValueAt(row, 4)); // 텍스트에어리어에 메세지를 받아온다　//　テキストエリアにメッセージを呼出
			onum = (int) (new StoreDeliveryListModel(snum).getValueAt(row, 0)); 

		}

		
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String e_type = e.getActionCommand();
		if (e_type.equals(okb.getText())) { // 확인버튼을 누르면　//　確認ボタンを押すど
			StoreDAO sdao = new StoreDAO();
			try {
				sdao.getMessageDelete(onum); // 클릭한 주문번호를 dao로
				// 보낸다.　//クリックした注文番号をdaoに傳送
				deliveryTable.setModel(new StoreDeliveryListModel(snum)); // 테이블
				// 재생성　//　テーブル生成
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	// 다른클래스에서 테이블을 새로고침하기 위한 메소드
	public JTable getTable() {
		return deliveryTable;
	}
}
