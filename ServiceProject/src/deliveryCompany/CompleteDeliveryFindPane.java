package deliveryCompany;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
//완료배달탭의 기본 클래스
public class CompleteDeliveryFindPane extends JPanel implements ActionListener, ItemListener{

	private JPanel jp[] = new JPanel[7];
	private JLabel jl;
	public JTextField tf;
	public JTextField tf1;
	private JButton bts[]=new JButton[3];
	private CompleteDeliveryListPane completePane;
	private JPanel base;
	private JComboBox choice =new JComboBox();
	private CompleteDeliveryModel cdm;

	String[] caption = { "注文番号", "出前番号", "店名"};
	String[] btnText={"検索","トータル"};
	public CompleteDeliveryFindPane() {


		setLayout(new GridLayout(1, 2));
		completePane = new CompleteDeliveryListPane();
		base=new JPanel();
		jp[3]=new JPanel();
		base.add(jp[3]);//첫번째 줄//一番目の行
		jp[0]=new JPanel();

		//콤보박스에 아이템 삽입//コンボボックスにアイテムを入れる
		try{
			for(int i=0; i<caption.length; i++)
				choice.addItem(caption[i]);
		} catch(Exception e){
			System.out.println("CompleteDeliveryFindPane1");
		}

		jp[0].add(choice);
		choice.addItemListener(this);

		base.setLayout(new GridLayout(8, 1));
		base.add(jp[0]);
		//두번째 줄//二番目の行

		EtchedBorder eb = new EtchedBorder();
		setBorder(eb);


		jl = new JLabel("店名 : ");
		tf=new JTextField(15);
		tf.addActionListener(this);

		jp[1]=new JPanel();
		jp[1].add(jl);
		jp[1].add(tf);
		bts[1]=new JButton("検索");
		bts[1].addActionListener(this);

		base.add(jp[1]);
		//네번째 줄//四番目の行
		jp[2]=new JPanel();
		jp[2].add(bts[1]);
		bts[2]=new JButton("トータル");
		bts[2].addActionListener(this);
		jp[2].add(bts[2]);
		base.add(jp[2]);

		jp[5]=new JPanel();
		base.add(jp[5]);

		jp[6]=new JPanel();
		base.add(jp[6]);

		JPanel countArea=new JPanel();

		JLabel l=new JLabel("検索されたの数");
		countArea.add(l,BorderLayout.WEST);

		tf1=new JTextField(20);
		countArea.add(tf1,BorderLayout.CENTER);

		base.add(countArea,BorderLayout.SOUTH);
		tf1.setEditable(false);

		add(completePane, BorderLayout.CENTER);
		add(base, BorderLayout.EAST);


	}


	public void actionPerformed(ActionEvent ae) {

		String as=tf.getText().trim();

		String ae_type = ae.getActionCommand();

		//검색버튼을 눌렀을때의 이벤트　//　検索ボタンを押す時のイベント
		if (ae_type.equals(bts[1].getText()) && tf.getText()!=null) {
			try{//매장명을 받아서 모델에 넘겨주어 해당 테이블을 불러온다　//　店名をもらってモデルに伝送してテーブルを呼び出す
				cdm=new CompleteDeliveryModel(as);
				completePane.setTable(tf.getText());
				tf1.setText(cdm.getRowCount()+"");
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("CompleteDeliveryFindPane-検索ボタン");
			}

			//모든 텍스트필드 초기화　//　テキストフィールド初期化
			int size = caption.length;
			for (int i = 0; i < size; i++) {
				tf.setText("");
			}
		}
		//전체보기버튼을 눌렀을 때의 이벤트　//トータルボタンを押す時のイベント
		else if(ae_type.equals(bts[2].getText())){

			//모든 자료가 있는 테이블을 가져온다　//　すべての資料がはいたテーブルを呼び出す
			try{
				cdm=new CompleteDeliveryModel();
				completePane.setTable();
			}catch(Exception e){
				System.out.println("CompleteDeliveryFindPane-トータル");
				e.printStackTrace();
			}

		}

	}

	//콤보박스의 아이템이 변할때의 이벤트　//　コンボボックスのアイテムが変わるのイベント
	public void itemStateChanged(ItemEvent ie) {

		String item=(String) ie.getItem();
		cdm=new CompleteDeliveryModel(item);


		if(choice.getSelectedItem() != null) {
			//선택된 아이템에 따른 이벤트　//　選択されたアイテムによってのイベント
			switch(item){
			case "注文番号":
				try{
					completePane.setTable(item);
					tf1.setText(cdm.getRowCount()+"");
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("CompleteDeliveryFindPane-コンボ注文番号");
				}break;
			case "出前番号":
				try{
					completePane.setTable(item);
					tf1.setText(cdm.getRowCount()+"");
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("CompleteDeliveryFindPane-コンボ配達番号");
				}break;
			case "店名":
				try{
					completePane.setTable(item);
					tf1.setText(cdm.getRowCount()+"");
				}catch(Exception e){
					e.printStackTrace();
					System.out.println("CompleteDeliveryFindPane-コンボ店名");
				}break;
			}
		}

	}

}