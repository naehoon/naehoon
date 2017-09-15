package store;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import deliveryCompany.CompanyDAO;

public class StorePaneOrder extends JPanel implements ActionListener { // 주문 tap //注文 tap

	private JPanel jp[] = new JPanel[4];		
	private JLabel jl[] = new JLabel[3];
	private JTextField tf[] = new JTextField[3];
	private JButton okb;
	private JButton rsb;
	private JComboBox jcb;
	private JTable deliverTable;
	private StorePaneDeliver deliverPane;
	
	String[] caption = {"店番号   :", "住             所    :", " 数量          :    " };

	public StorePaneOrder(){}
	public StorePaneOrder(int snum) {
		deliverPane = new StorePaneDeliver(snum);
		JTabbedPane tp = new JTabbedPane();
		setLayout(new GridLayout(6, 1));

		int size = caption.length;
		jp[0] = new JPanel();
		jl[0] = new JLabel(caption[0]);
		tf[0] = new JTextField(15);
		jp[0].add(jl[0]);
		jp[0].add(tf[0]);
		add(jp[0]);


		jp[1] = new JPanel();
		jl[1] = new JLabel(caption[1]);
		tf[1] = new JTextField(15);
		jp[1].add(jl[1]);
		jp[1].add(tf[1]);
		add(jp[1]);


		jp[2] = new JPanel();
		jl[2] = new JLabel(caption[2]);


		jcb = new JComboBox();
		for(int i=1; i<100; i++) // 콤보박스에 숫자 1~99 추가 // コンボボックスに数字1～99を入れる
			jcb.addItem(i);



		jp[2].add(jl[2]);
		jp[2].add(jcb);
		add(jp[2]);


		tf[0].setEditable(false);	
		tf[0].setText(snum+"");		
		jp[size] = new JPanel();
		okb = new JButton("要請");
		okb.addActionListener(this); 
		rsb = new JButton("書き直す");
		rsb.addActionListener(this);
		jp[size].add(okb);
		jp[size].add(rsb);
		add(jp[size]);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub	
		String e_type = e.getActionCommand();
		CompanyDAO cdao = null;
		if (e_type.equals(okb.getText())) { // okb버튼을 누르면　//　okbボタンを押すと
			try{
				if(!(tf[1].getText().equals(""))){ // tf[1]가 비어있지 않을경우　// tf[1]が空いていない場合
					cdao = new CompanyDAO();
					int snum;							// 매장번호를 받아오기위한 변수선언　　//　店番号が入るための変数
					snum = Integer.parseInt(tf[0].getText()); // 받아온 매장번호를 인트형으로 변경　//　もらった店番号をint形で變更

					cdao.getOrderRegiste(snum, tf[1].getText() ,Integer.parseInt(jcb.getSelectedItem().toString()),0); // DB로 자료를 보냄　//　データベースに資料傳送
					// 요청완료 출력　//　要請完了出力
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "要請完了。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");

					deliverTable = deliverPane.getTable();
					deliverTable.setModel(new StoreDeliveryListModel(snum)); // okb버튼을 누를경우 테이블 재생성　//　okbボタンを押す場合テ－ブル生成


				}else if(tf[1].getText().equals("")||tf[2].getText().equals("")){ // 텍스트가 비어있을경우　//　テキストが空いている場合
					
					// 내용을 입력해주세요 메세지 출력　//　”内容を入力してください”メッセージ出力
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "内容を入力してください。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				


				}

			}catch(Exception e1){

			}


		} else if (e_type.equals(rsb.getText())) { // rsb버튼을 누르면　// rsbボタンを押すと

			tf[1].setText(""); // 텍스트필드 초기화　//テキストフィールド初期化

		}

	}
	// StorePane에서 deliverPane을 사용하기 위한 메소드
	public StorePaneDeliver getPaneDeliver(){
		return deliverPane;
	}
}

