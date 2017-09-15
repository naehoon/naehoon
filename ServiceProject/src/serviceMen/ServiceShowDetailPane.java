package serviceMen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;




import deliveryCompany.CompleteDeliveryVO;
import deliveryCompany.OrderVO;

public class ServiceShowDetailPane extends JDialog implements MouseListener, ActionListener{
	/*//배달목록 table 생성 
	String colNames[]={"주문번호","매장이름","주문주소","매장주소","주문개수"};*/
	//배달목록 table 생성　//　配達リストtable生成
	String colNames[]={"注文番後","店名","注文住所","店住所","注文の数"};

	ServiceListModel model;
	DeliveringModel dmodel;
	private JTable table;
	private JPanel[] jp=new JPanel[6];//상세내용 패널　//　詳細内容
	private JTextField[] tf=new JTextField[5];
	private JLabel[] jl=new JLabel[5];
	public int sonum;
	private JButton jmine, jorn, jok,jcmpt,jcc,jmdf;
	static int size=0;
	private String delitel;
	private int delinum;
	private int i;// 배달중과 아님을 구별하기 위한 변수(배달중이면 1을 저장해 내가 배달한 목록을 봤다가 원래대로를 눌렀을 때 배달중인 목록을 표시)
				//配達中と配達中ではないことの区別のための変数（配達中ならば１を貯藏して自分が配達したリストを見てから戻るボタンを押す時配達中のリストを表示）
	Object[] row=new Object[5];

	public ServiceShowDetailPane() {
		super();
	}

	public ServiceShowDetailPane(String tel,int onum,String name,int dnum) throws Exception {
		if(onum!=0){ //배달중인목록표시//配達中のリスト
			dmodel=new DeliveringModel(tel);
			table=new JTable(dmodel);
		}else{ //배달대기목록표시//配達待機のリスト
			model=new ServiceListModel();
			table=new JTable(model);
		}
		JPanel pp=new JPanel();
		setSize(500,500);
		pp.setLayout(new BorderLayout());
		delitel=tel; delinum=dnum; 
		//~님, 환영합니다.//～さん、いらっしゃいませ。
		JPanel pname=new JPanel();
		Label lname=new Label(name+"さん、いらっしゃいませ。",Label.CENTER);
		jmdf=new JButton("電話番号修正");
		jmine=new JButton("出前完了リスト");
		jorn=new JButton("出前待機リスト");
		jmdf.setContentAreaFilled(false); jmdf.setBorderPainted(false);
		jmine.setContentAreaFilled(false); jmine.setBorderPainted(false);
		jorn.setContentAreaFilled(false); jorn.setBorderPainted(false);
		pname.add(lname);pname.add(jmdf); pname.add(jmine); pname.add(jorn);

		//배달목록보여주기//配達リストを見せる
		JPanel pdeli=new JPanel();
		pdeli.setLayout(new BorderLayout());		
		Label lb1=new Label("出前リスト",Label.CENTER);
		pdeli.add(lb1,"North");
		pdeli.add(new JScrollPane(table),"Center");

		//상세내용부분//詳細内容
		JPanel pcont=new JPanel();
		pcont.setLayout(new BorderLayout());
		Label lb2=new Label("詳細内容",Label.CENTER);

		Panel pdata=new Panel();
		pdata.setLayout(new GridLayout(6,2));

		size=colNames.length;
		for(int i=0; i<size; i++){
			jl[i]=new JLabel(colNames[i]);
			tf[i]=new JTextField(20);
			tf[i].setEditable(false);
			jp[i]=new JPanel();
			jp[i].add(jl[i]);
			jp[i].add(tf[i]);
			pdata.add(jp[i]);
		}
		jp[size]=new JPanel();
		pcont.add(lb2,BorderLayout.NORTH);
		pcont.add(pdata,BorderLayout.CENTER);

		//버튼//ボタン
		jok=new JButton("承諾");
		jcmpt=new JButton("出前完了");
		jcc=new JButton("キャンセル");
		jcc.setBackground(Color.red);
		jcc.setSize(5, 5);
		jp[size]=new JPanel();
		jp[size].setLayout(new FlowLayout());
		jp[size].add(jok);
		jp[size].add(jcmpt);
		if(onum!=0)
			jp[size].add(jcc).setVisible(true);
		else
			jp[size].add(jcc).setVisible(false);
		pcont.add(jp[size],BorderLayout.SOUTH);
		pp.add(pname,BorderLayout.NORTH);
		pp.add(pdeli,BorderLayout.WEST);
		pp.add(pcont,BorderLayout.CENTER);
		add(pp);
		jmdf.addActionListener(this);
		jmine.addActionListener(this);
		jorn.addActionListener(this);
		table.addMouseListener(this); 
		jok.addActionListener(this);
		jcmpt.addActionListener(this);
		jcc.addActionListener(this);
		pack();
	}


	public void mouseClicked(MouseEvent e)
	{ 
		// TODO Auto-generated method stub
		// 이벤트가 일어난 객체 얻기 //イベントが起きた客體持つ
		JTable j = (JTable)e.getComponent();
		OrderVO ovo=new OrderVO();
		ServiceMenDAO sdao=new ServiceMenDAO();

		// 선택된 행의 데이터 출력//選択されたの行のdata出力
		try {
			int no=(int) j.getValueAt(j.getSelectedRow(), 0);//선택된 열 값을 no에 저장//選択されたの行のdataをnoに貯藏
			ovo=sdao.getServiceShow(no); 
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if(ovo!=null){
			tf[0].setText(ovo.getOnum()+"");
			tf[1].setText(ovo.getSname());
			tf[2].setText(ovo.getSaddr());
			tf[3].setText(ovo.getOaddr());
			tf[4].setText(ovo.getMenuCount()+"");
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String e_type = e.getActionCommand();
		ServiceMenVO svo;
		ArrayList<OrderVO> ovo = null;
		CompleteDeliveryVO cdvo=null;
		ServiceMenDAO sdao = null;
		
		//내정보수정//自分の情報修正
		try{
			if(e_type.equals(jmdf.getText())){
				sdao=new ServiceMenDAO();
				svo=sdao.getServiceManNum(delinum);
				new ServiceMenModifyPane(svo.getTel(),svo.getName(),svo.getNo());
			}
		}catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		//승인버튼 눌렀을 때//承諾ボタンを押す時
		if (e_type.equals(jok.getText())) {
			i=1;
			try {	
				int no=Integer.parseInt(tf[0].getText()); //주문번호//注文番号
				if(no==0){
					
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "リストを選択してください。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}
				sdao=new ServiceMenDAO();
				svo=sdao.getServiceManTel(delitel); //배달원전화번호로 배달원 검색//出前の人電話番号で配達員検索
				ovo=sdao.getServiceOk(no); //배달상태바뀜//出前狀態変更
				sdao.getServiceOkdman(no,svo.getNo());//dman테이블의 onum update///dmanテーブルの onum update
				table.setModel(new DeliveringModel(delitel)); //자신이 승인한 것만 보임//自分が承諾したことだけ見える
				jcc.setVisible(true);
			}catch(NumberFormatException nfe){
				System.out.println(nfe);
			} catch (Exception e1) {
				System.out.println("e1=[" + e1 + "]");
			}
		}
		
		//배달완료버튼을 눌렀을 때//出前完了ボタンを押す時
		else if (e_type.equals(jcmpt.getText())) {
			i=0;
			try {
				int no=Integer.parseInt(tf[0].getText());
				sdao=new ServiceMenDAO();
				cdvo=sdao.getNum(no);
				if(cdvo!=null){
					sdao.getCmptOK(cdvo);
					sdao=new ServiceMenDAO();
					ovo=sdao.getServiceComplete(no); //주문현황에서 삭제//注文現況で削除
					tf[0].setText("");
					tf[1].setText("");
					tf[2].setText("");
					tf[3].setText("");
					tf[4].setText("");
					setTable();
					jcc.setVisible(false);
				}else{
					
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "まだ 出前の前です!!", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}
			}catch(NullPointerException ne){
				System.out.print("NullPointerException = ");
				ne.printStackTrace();
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				
			}
		}
		
		//배달취소버튼을 눌렀을 때//出前キャンセルボタンを押す時
		else if (e_type.equals(jcc.getText())) {
			i=0;
			try {
				int no=Integer.parseInt(tf[0].getText());
				sdao=new ServiceMenDAO();
				ovo=sdao.getOrderCancel(no); //주문테이블에서 취소//注文テーブルでキャンセル
				ovo=sdao.getDmanCancel(delinum); //배달원테이블에서 취소//出前の人テーブルでャンセル
				tf[0].setText(""); //상세내용 텍스트필드 초기화//詳細内容テキストフィールドを初期化
				tf[1].setText("");
				tf[2].setText("");
				tf[3].setText("");
				tf[4].setText("");//
				setTable();
				jcc.setVisible(false);
			}catch(NullPointerException ne){
				System.out.print("NullPointerException = ");
				ne.printStackTrace();
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		//내가 배달완료한 것만 보기//自分の出前完了のリスト
		if(e_type.equals(jmine.getText())){
			jok.removeActionListener(this);
			jcmpt.removeActionListener(this);
			try{
				sdao=new ServiceMenDAO();
				table.setModel(new DeliverCmptModel(delinum));
			}catch(Exception be){
				be.printStackTrace();
			}
		}
		
		//원래 테이블 보이기//前のテーブル
		if(e_type.equals(jorn.getText())){
			jok.addActionListener(this);
			jcmpt.addActionListener(this);
			try{
				if(i==1)
					table.setModel(new DeliveringModel(delitel));			
				else
					setTable();
			}catch(Exception oe){
				oe.printStackTrace();
			}
		}
	}
	
	//배달전테이블 불러오기//出前の前のテーブルを呼び出す
	public void setTable(){
		table.setModel(new ServiceListModel());
	}

}
