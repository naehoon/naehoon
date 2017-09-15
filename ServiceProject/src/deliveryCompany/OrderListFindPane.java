package deliveryCompany;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class OrderListFindPane extends JPanel implements MouseListener, ActionListener{
	private OrderListPane olp;
	private JPanel base, mebp, onump, oaddrp, omessagep, snump, codep, sendmessagp, snamep;
	private JLabel findLabel, onuml, oaddrl, omessagel, snuml, codel, snamel, messagel;
	private JTextField onumt, oaddrt, omessaget, snumt, codet, snamet;
	private JTable orderList;
	private JTextArea sendmessagt;
	private JButton mebt;
	private CompanyDAO cDAO;
	public OrderListFindPane(){
		setLayout(new BorderLayout());
		base=new JPanel();
		base.setLayout(new GridLayout(10,1));
		add(base, BorderLayout.EAST);
		
		olp=new OrderListPane();
		add(olp, BorderLayout.CENTER);
		olp.model().setPreferredScrollableViewportSize(new Dimension(400, 50));
		
		findLabel=new JLabel("情報", JLabel.CENTER);
		base.add(findLabel, "North");
		
		onump=new JPanel(new FlowLayout());
		onuml=new JLabel("注文番号");
		onumt=new JTextField(10);
		onump.add(onuml);
		onump.add(onumt);
		base.add(onump);
		onumt.setEditable(false);
		
		snamep=new JPanel(new FlowLayout());
		snamel=new JLabel("店名");
		snamet=new JTextField(10);
		snamep.add(snamel);
		snamep.add(snamet);
		base.add(snamep);
		snamet.setEditable(false);
		

		oaddrp=new JPanel(new FlowLayout());
		oaddrl=new JLabel("店の住所");
		oaddrt=new JTextField(10);
		oaddrp.add(oaddrl);
		oaddrp.add(oaddrt);
		base.add(oaddrp);
		oaddrt.setEditable(false);
		
		omessagep=new JPanel(new FlowLayout());
		omessagel=new JLabel("目的地の住所");
		omessaget=new JTextField(10);
		omessagep.add(omessagel);
		omessagep.add(omessaget);
		base.add(omessagep);
		omessaget.setEditable(false);

		snump=new JPanel(new FlowLayout());
		snuml=new JLabel("詳細内容");
		snumt=new JTextField(10);
		snump.add(snuml);
		snump.add(snumt);
		base.add(snump);
		snumt.setEditable(false);

		codep=new JPanel(new FlowLayout());
		codel=new JLabel("出前狀態");
		codet=new JTextField(10);
		codep.add(codel);
		codep.add(codet);
		base.add(codep);
		codet.setEditable(false);
		
		onumt.setEditable(false);
		sendmessagt=new JTextArea(20,20);
		messagel=new JLabel("message");
		sendmessagp=new JPanel();
		sendmessagp.setLayout(new BorderLayout());
		sendmessagp.add(messagel, BorderLayout.NORTH);
		sendmessagp.add(sendmessagt, BorderLayout.CENTER);
		base.add(sendmessagp);
		sendmessagt.setEnabled(false);
		
		mebp=new JPanel();
		base.add(mebp);
		
		mebt=new JButton("伝送");
		mebp.add(mebt);
		mebt.addActionListener(this);
		
		orderList=olp.model();
		orderList.addMouseListener(this);
		
		setVisible(true);

	}

	//테이블의 자료 클릭시 발생하는 이벤트　//テーブルの資料をクリックときのイベント
	public void mouseClicked(MouseEvent e) {
		orderList=olp.model();
		int row = orderList.getSelectedRow();
		//한번 클릭하면 모든 텍스트 필드에 정보가 나타난다　//　クリックときテキストフィールドで情報が見える
		if(e.getClickCount()==1){
			onumt.setText(String.valueOf((new OrderListModel()).getValueAt(row, 0)));
			snamet.setText((String)(new OrderListModel()).getValueAt(row, 1));
			oaddrt.setText((String)(new OrderListModel()).getValueAt(row, 2));
			omessaget.setText((String)(new OrderListModel()).getValueAt(row, 3));
			snumt.setText(String.valueOf((new OrderListModel()).getValueAt(row, 4)));
			codet.setText(String.valueOf((new OrderListModel()).getValueAt(row, 5)));
			sendmessagt.setEnabled(true);
			}
		//더블클릭시 텍스트필드가 초기화된다　//　 ダブルクリック時テキストフィールドが初期化
			else if(e.getClickCount()==2){
			onumt.setText("");	snamet.setText("");	oaddrt.setText("");	omessaget.setText("");	snumt.setText("");	codet.setText("");
		}
	}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	@Override
	//보내기 버튼을 눌렀을시 메세지를 테이블에 보내는 이벤트　//伝送ボタンを押す時メッセージをテーブルに伝送
	public void actionPerformed(ActionEvent e) {
		cDAO=new CompanyDAO();
		String event=e.getActionCommand();
		String send=sendmessagt.getText();
		int onum=Integer.parseInt(onumt.getText());
		
		if(event.equals(mebt.getText())){
			cDAO.orderListUpdate(send, onum);
			
			Object[] options = {"確認"};
			JOptionPane.showOptionDialog(null, "伝送しました。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
			sendmessagt.setText("");
			sendmessagt.setEnabled(false);
		}
			
	}

}
