package deliveryCompany;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import serviceMen.ServiceMenDAO;
import serviceMen.ServiceMenVO;

public class ServiceMenFindPane extends JPanel implements ActionListener, MouseListener, KeyListener{

	private JTable serviceMenModelTable;
	private ServiceMenListPane smlp;
	private JPanel jp[] = new JPanel[6];
	private JLabel jl[] = new JLabel[3];
	private JTextField tf[] = new JTextField[3];
	private JButton bts[]=new JButton[4];
	String[] caption = { "出前の人番号 :", "出前の人の名前  :", "電話番号  :"};
	String[] btnText={"照會","登録","修正","削除"};
	public ServiceMenFindPane() {
		smlp=new ServiceMenListPane();
		JPanel base=new JPanel();
		base.setLayout(new GridLayout(1,2));
		base.add(smlp);
		add(base);
		serviceMenModelTable=smlp.getserviceTable();

		serviceMenModelTable.setPreferredScrollableViewportSize(new Dimension(330,500));


		JPanel right=new JPanel();
		right.setLayout(new GridLayout(6,1));
		base.add(right);
		EtchedBorder eb = new EtchedBorder();
		setBorder(eb);
		int size = caption.length;
		try{
		for (int i = 0; i < size; i++) {
			jl[i] = new JLabel(caption[i]);
			tf[i] = new JTextField(15);
			tf[i].addActionListener(this);
			jp[i] = new JPanel();
			jp[i].add(jl[i]);
			jp[i].add(tf[i]);
			right.add(jp[i]);
		}
		}catch(Exception e){
			System.out.println("ServiceMenFindPane1");
		}
		tf[0].setEditable(false);
		jp[4] = new JPanel();
		jp[5]=new JPanel();
try{
		for(int i=0; i<btnText.length; i++) {
			bts[i]=new JButton(btnText[i]);
			bts[i].addActionListener(this);
			if(i<2)
				jp[4].add(bts[i]);
			else jp[5].add(bts[i]);

		}
}catch(Exception e){
	System.out.println("ServiceMenFindPane2");
}
		right.add(jp[4]);
		right.add(jp[5]);

		serviceMenModelTable.addMouseListener(this);

		tf[0].addActionListener(this);
		
		//텍스트필드 클릭시 모든 텍스트필드 초기화　//テキストフィールドをクリックするとテキストフィールド初期化
		tf[1].addFocusListener(new FocusAdapter()
		{
			public void focusGained(FocusEvent fe)
			{	try{
				for(int i=0; i<tf.length; i++)
				tf[i].setText("");
			}catch(Exception e){
				System.out.println("ServiceMenFindPane3");
			}
			}
		});
		tf[2].addKeyListener(this);

	}
	public void mouseClicked(MouseEvent e) {

		serviceMenModelTable=smlp.getserviceTable();

		//테이블을 클릭할 때 텍스트필드에 자료를 가져오기//テーブルをクリックするとテキストフィールドの内容を呼び出す

		if(e.getClickCount()==1){
			int row= serviceMenModelTable.getSelectedRow();
			tf[0].setText(String.valueOf((new ServiceMenListModel()).getValueAt(row, 0)));
			tf[1].setText((String)(new ServiceMenListModel()).getValueAt(row, 1));
			tf[2].setText((String)(new ServiceMenListModel()).getValueAt(row, 2));
		}
	}

	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
	public void mousePressed(MouseEvent e) {}
	public void mouseReleased(MouseEvent e) {}

	public void actionPerformed(ActionEvent ae) {

		String ae_type = ae.getActionCommand();
		ServiceMenVO smVO = null;
		CompanyDAO comDAO = null;
		ServiceMenDAO smDAO=null;

		try {
			smDAO = new ServiceMenDAO();
			comDAO = new CompanyDAO();
		} catch (NullPointerException e) {
			// TODO: handle exception
			e.printStackTrace();
			System.out.println("ServiceMenFindPane2");
		}
		//조회 버튼을 눌렀을 때 발생하는 이벤트//照會ボタンを押す時のイベント
		if (ae_type.equals(bts[0].getText())){
			//배달원 이름텍스트의 내용을 받아서 조회//出前の人の名前で照會
			if(tf[1].getText()!=null) {
				try {					
					String dname = tf[1].getText().trim();	
						smVO = smDAO.getServiceManName(dname);
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("ServiceMenFindPane3");
				}
				//찾아서 데이터가 나왔다면 텍스트필드에 채워넣기　//dataをテキストフィールドに入れる
				if (smVO != null) {
					tf[0].setText(smVO.getNo() + "");
					tf[1].setText(smVO.getName());
					tf[2].setText(smVO.getTel());
				} 
				//찾지 못했다면 메세지 띄움　//　検索が失敗する時
				else {
					
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "検索失敗", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}
			} 
		}
		//등록버튼을 눌렀을 때 발생하는 이벤트　//　登録ボタンを押す時のイベント
		else if (ae_type.equals(bts[1].getText())) {
			try{
				smVO=smDAO.getServiceManTel(tf[2].getText());
				
				//텍스트 필드가 모두 채워져있을 때 등록　//　テキストフィールドが全部空いていない時登録
				if(!(tf[1].getText().trim().equals("") || 
						tf[2].getText().trim().equals(""))){
					if(smVO==null){
					smVO=new ServiceMenVO(0,tf[1].getText()
							,tf[2].getText());
					smDAO.getDeliverRegiste(smVO);
			} else	{
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "同じ出前の人がいます。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
			}	
			} else{ //텍스트가 하나라도 비어있다면 메세지　//　テキストフィールドが空いている時のメッセージ
					
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "内容を入力してください。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
			}
			}catch(Exception e){
				e.printStackTrace();
				System.out.println("ServiceMenFindPane3");
			}
			int size = caption.length;
			try{
			for (int i = 0; i < size; i++) {
				tf[i].setText("");
			}
			}catch(Exception e){
				System.out.println("ServiceMenFindPane4");
			}
		}
		//배달원이름 텍스트에서 엔터를 눌렀을 때 검색　//出前の人名前のテキストフィールドでエンターをおすとき検索
		else if(ae_type.equals(tf[1].getText())) {	
			try{
				String sname = tf[1].getText().trim();
				if (!sname.equals("")) {
					smVO = smDAO.getServiceManName(sname);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("ServiceMenFindPane4");
			}
			if (smVO != null) {
				tf[0].setText(smVO.getNo() + "");
				tf[1].setText(smVO.getName());
				tf[2].setText(smVO.getTel());
			}  else {
				
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "検索失敗", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
			}

		}
		//수정하기 버튼이 클릭되었을 때의 이벤트　//修正ボタンを押す時のイベント
		else if(ae_type.equals(bts[2].getText())){ 

			try {
				smVO=new ServiceMenVO(Integer.parseInt(tf[0].getText()),tf[1].getText()	,tf[2].getText());
				comDAO=new CompanyDAO();

				comDAO.serviceMenUpdate(smVO);
				
				if(comDAO != null ){
	
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, tf[0].getText() + "さんが成功的に修正されました。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}
				int size = caption.length;
				try{
				for (int i = 0; i < size; i++) {
					tf[i].setText("");
				}
				}catch(Exception e){
					System.out.println("ServiceMenFindPane5");
				}

			}catch (Exception e){
				
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "情報がないです。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,null);
			}
			//내용이 잘 넘어갔을 때　//　dataが入る時
			
			//삭제하기 버튼을 눌렀을 때　//　削除ボタンを押す時
		}else if(ae_type.equals(bts[3].getText())) {

			

			try {
				comDAO=new CompanyDAO();
				int no=Integer.parseInt(tf[0].getText());
				Object[] options1 = {"はい", "いいえ"};
				int result=JOptionPane.showOptionDialog(null, "削除していただけますか。", "削除", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE,null, options1,null);
				//Dialog창에서 yes를 선택했을 때　//Dialogでyesを選ぶ時
				if(result==JOptionPane.YES_OPTION){					
					comDAO.serviceMenDelete(no);
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "店番号"+no+"が削除されました。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}
			} catch (Exception e) {
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "内容を入力してください。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
			}
			int size = caption.length;
			try{
			for (int i = 0; i < size; i++) {
				tf[i].setText("");
			}
			}catch(Exception e){
				System.out.println("ServiceMenFindPane6");
			}
		}
		if(!ae_type.equals(bts[0].getText())){
			smlp.serviceListset();
		}
	}
	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	public void keyTyped(KeyEvent e) {
		char type=e.getKeyChar();
		
		if(!Character.isDigit(type)){
			e.consume();
		}
	}

}