package deliveryCompany;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class DailyPane extends JPanel implements ActionListener, KeyListener{
	private JPanel base, right, date, datecom, storeSearch, menSearch, left, mcountp, counttp, moneyp;
	private JLabel savelist, yearl, monl, dayl, storeNamel, menl, countl, moneyl, summoneyl;
	private JTextField storeNamet, ment, countt, moneyt, sumt;
	private JButton searchb, moneyb;
	private JTable totalCompleteTable;
	private JScrollPane tableScroll;
	private JComboBox jyear, jmon, jday;
	private CompanyDAO cDAO;
	public DailyPane() {
		JPanel []deco=new JPanel[5];
		base=new JPanel();
		base.setLayout(new FlowLayout());
		add(base);

		totalCompleteTable=new JTable(new DailyModel());
		tableScroll=new JScrollPane(totalCompleteTable);
		setLayout(new FlowLayout());

		left=new JPanel();
		left.setLayout(new BorderLayout());
		base.add(left);
		savelist=new JLabel("貯藏されたリスト", JLabel.CENTER);
		left.add(tableScroll, BorderLayout.CENTER);
		left.add(savelist, BorderLayout.NORTH);
		try{
			for(int i=0; i<5; i++)
				deco[i]=new JPanel();
		}catch(Exception e){
			System.out.println("DailyPane1");
		}

		right=new JPanel(new GridLayout(10,1));
		base.add(right);

		moneyp=new JPanel();
		moneyp.setLayout(new BorderLayout());
		moneyl=new JLabel("適用される金額 ");
		moneyp.add(moneyl, BorderLayout.NORTH);
		moneyt=new JTextField(5);
		moneyt.addKeyListener(this);
		moneyp.add(moneyt, BorderLayout.CENTER);
		moneyb=new JButton("適用");
		moneyp.add(moneyb, BorderLayout.EAST);
		moneyb.addActionListener(this);
		right.add(moneyp);
		moneyt.setText("0");


		date=new JPanel(new GridLayout(1,3));
		yearl=new JLabel("年度");
		date.add(yearl);
		monl=new JLabel("月");
		date.add(monl);
		dayl=new JLabel("日");
		date.add(dayl);

		right.add(date);

		//콤보박스에 각 아이템들을 넣는 반복문들　//　コンボボックスに入れること
		datecom=new JPanel(new GridLayout(1,4));
		jyear=new JComboBox();
		jyear.addItem("");
		try{
			for(int i=2016; i<2050; i++)
				jyear.addItem(i);
		}catch(Exception e){
			System.out.println("DailyPane1");
		}
		datecom.add(jyear);

		jmon=new JComboBox();
		jmon.addItem("");
		try{
			for(int i=1; i<13; i++)
				jmon.addItem(i);

		}catch(Exception e){
			System.out.println("DailyPane2");
		}
		datecom.add(jmon);

		jday=new JComboBox();
		jday.addItem("");
		try{
			for(int i=1; i<32; i++){
				if(i<10)
					jday.addItem("0"+i);
				else jday.addItem(i);
			}
		}catch(Exception e){
			System.out.println("DailyPane3");
		}
		datecom.add(jday);
		right.add(datecom);

		right.add(deco[0]);
		right.add(deco[1]);

		storeSearch=new JPanel();
		storeNamel=new JLabel("店名");
		storeSearch.add(storeNamel);
		storeNamet=new JTextField(7);
		storeSearch.add(storeNamet);
		right.add(storeSearch);

		right.add(deco[2]);

		menSearch=new JPanel();
		menl=new JLabel("出前番号");
		menSearch.add(menl);
		ment=new JTextField(7);
		menSearch.add(ment);
		right.add(menSearch);

		right.add(deco[3]);

		searchb=new JButton("検索");
		right.add(searchb);
		setVisible(true);

		searchb.addActionListener(this);

		counttp=new JPanel();
		counttp.setLayout(new FlowLayout());
		countl=new JLabel("メニューの数");

		summoneyl=new JLabel("トタル");


		mcountp=new JPanel();
		mcountp.setLayout(new GridLayout(1,2));
		countt=new JTextField(7);
		countt.setEditable(false);
		countt.addActionListener(this);
		mcountp.add(countt, BorderLayout.CENTER);
		sumt=new JTextField(15);

		counttp.add(countl, BorderLayout.SOUTH);		
		counttp.add(mcountp);
		counttp.add(summoneyl);
		counttp.add(sumt);
		left.add(counttp, BorderLayout.SOUTH);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		cDAO=new CompanyDAO();
		String event=e.getActionCommand();

		//date에 각 콤보박스에서 골라진 아이템값을 합쳐 넣음　//dateにコンボボックスの情報を入れる
		String date=jyear.getSelectedItem()+"-"+
				jmon.getSelectedItem()+"-"+jday.getSelectedItem();

		//모든 콤보박스와 텍스트필드에 값이 채워져있을때의 모델세팅　//コンボボックスとテキストフィールドが空いていない時モデルセッティング.
		if(event.equals(searchb.getText())){
			try{
				if(!jyear.getSelectedItem().equals("") &&
						!jmon.getSelectedItem().equals("") &&
						!jday.getSelectedItem().equals("") &&
						!storeNamet.getText().equals("") &&
						!ment.getText().equals("")) {
					totalCompleteTable.setModel(new DailyModel(date, storeNamet.getText(), Integer.parseInt(ment.getText())));
					sumt.setText(Integer.parseInt(countt.getText())*Integer.parseInt(moneyt.getText())+"");
				}

				//매장명과 오늘날짜를 가지고 검색한 모델 세팅　//　店名と今日の日付で検索されたモデルセッティング.
				else if(!jyear.getSelectedItem().equals("") &&
						!jmon.getSelectedItem().equals("") &&
						!jday.getSelectedItem().equals("") &&
						!storeNamet.getText().equals("") &&
						ment.getText().equals("")){
					totalCompleteTable.setModel(new DailyModel(date, storeNamet.getText()));
					countt.setText(cDAO.getSumCount(date, storeNamet.getText(), 0));
					sumt.setText(Integer.parseInt(countt.getText())*Integer.parseInt(moneyt.getText())+"");
				}
				//배달번호와 날짜로 검색된 모델 세팅//出前番号と今日の日で検索されたモデルセッティング.
				else if(!jyear.getSelectedItem().equals("") &&
						!jmon.getSelectedItem().equals("") &&
						!jday.getSelectedItem().equals("") &&
						storeNamet.getText().equals("") &&
						!ment.getText().equals("")){
					totalCompleteTable.setModel(new DailyModel(date, Integer.parseInt(ment.getText())));
					countt.setText(cDAO.getSumCount(date, null, Integer.parseInt(ment.getText())));
					sumt.setText(Integer.parseInt(countt.getText())*Integer.parseInt(moneyt.getText())+"");
				}
				//모든 값이 비어있을 때 전체 목록 가져오기//すべてのdataが空いている時トタルリストを呼び出す
				else if(jyear.getSelectedItem().equals("") &&
						jmon.getSelectedItem().equals("") &&
						jday.getSelectedItem().equals("") &&
						storeNamet.getText().equals("") &&
						ment.getText().equals("")){
					totalCompleteTable.setModel(new DailyModel());
					countt.setText("");
				}
				//날짜만으로 가져오기//日付だけで呼び出す
				else if(!jyear.getSelectedItem().equals("") &&
						!jmon.getSelectedItem().equals("")&&
						storeNamet.getText().equals("") &&
						ment.getText().equals("")){
					totalCompleteTable.setModel(new DailyModel(date));
					countt.setText(cDAO.getSumCount(date, null, 0));
					sumt.setText(Integer.parseInt(countt.getText())*Integer.parseInt(moneyt.getText())+"");
				}
			}catch(NumberFormatException numberE){
				String[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "検索が失敗しました。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
			}
			jyear.setSelectedItem("");
			jmon.setSelectedItem("");
			jday.setSelectedItem("");
			storeNamet.setText("");
			ment.setText("");			
		}

		if(event.equals(moneyb.getText())){

			if(moneyb.getText()=="適用"){
				moneyb.setText("解除");
				moneyt.setEditable(false);
			}else{
				moneyb.setText("適用");
				moneyt.setEditable(true);
			}
		}
	}

	public void keyPressed(KeyEvent e) {}
	public void keyReleased(KeyEvent e) {}
	//숫자만 입력받을 수 있도록하는 이벤트
	public void keyTyped(KeyEvent e) {
		char type=e.getKeyChar();

		if(!Character.isDigit(type)){
			e.consume();
			return;
		}
	}

}
