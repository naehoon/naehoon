/* 
 * =============================
 * 프로그램 설명 : 내옷장 화면 패널을 생성하기 위한 클래스   
 * プログラムの説明: 私のたんすの画面パネルを生成するためのクラス
 * 작성자 : 오내훈
 * 作成者 : オ・ネフン
 * 최초 작성일자 :   2017-07-18　
 * 最初の作成日付　:　 2017-07-18　
 * 최종 수정일 : 
 * 最終の修正日付　:
 * 수정 내용 : 	
 * 修正の内容 :
 * =============================
 * */

package com.bs.mydressroom.control;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bs.util.JTextFieldLimit;
import com.bs.view.MainFrame;
import com.bs.vo.ClosetVO;
import com.bs.vo.MyDressRoomVO;

public class MyDressRoom extends JPanel implements ActionListener , MouseListener, MouseMotionListener{
	
	private JButton btnMain = new JButton("メインに移動");//"메인으로"
	private JButton btnCharge = new JButton("充電");//충전
	private JComboBox comboBox_1 = new JComboBox();
	private JComboBox comboBox_2 = new JComboBox();
	private JComboBox comboBox_3 = new JComboBox();
	private JComboBox comboBox_4 = new JComboBox();
	private JPanel panel_man = new JPanel();
	private JPanel panel_cap = new JPanel();
	private JPanel panel_up = new JPanel();
	private JPanel panel_down = new JPanel();
	private JPanel panel_shoe = new JPanel();
	private int gender; //사용자 성별//使用者の性別
	private int balance; //사용자 잔고//使用者の残高
	private Container cPane;
	private ImageIcon img;
	private JLabel ImgBox = new JLabel();
	private JLabel imglb = new JLabel();
	private boolean isDragged1 = false;
	private boolean isDragged2 = false;
	private boolean isDragged3 = false;
	private boolean isDragged4 = false;
	private int panel_capX =0;
	private int panel_capY =0;
	private int panel_upX =0;
	private int panel_upY =0;
	private int panel_downX =0;
	private int panel_downY =0;
	private int panel_shoeX =0;
	private int panel_shoeY =0;
	private JTextField textField[] = new JTextField[17];
	private JTextField tfBalance = new JTextField(); //잔고//残高
	private JTextField tfCharge = new JTextField(); //충전금액//充填金額
	
	public MyDressRoom(MainFrame win) {
		setBackground(Color.WHITE);

		setBounds(new Rectangle(0, 0, 900, 600));
		setLayout(null);
		panel_man.setBackground(new Color(240, 248, 255));
		panel_man.setBounds(12, 37, 372, 471);
		panel_man.setLayout(null); 
		
		panel_cap.setBackground(Color.WHITE);  //모자//帽子
		panel_cap.setOpaque(false);	//투명하게//透明に
		panel_up.setBackground(Color.WHITE); //상의//うわぎ
		panel_up.setOpaque(false);  //투명하게//透明に
		panel_down.setBackground(Color.WHITE); //하의//ズボン
		panel_down.setOpaque(false);  //투명하게//透明に
		panel_shoe.setBackground(Color.WHITE); //신발//靴
		panel_shoe.setOpaque(false);  //투명하게//透明に
		
		panel_man.add(panel_cap);
		panel_man.add(panel_up);
		panel_man.add(panel_down);
		panel_man.add(panel_shoe);
		add(panel_man);
		
		JPanel panel_comp = new JPanel();
		JLabel label_12 = new JLabel("私のたんす");//"내 옷장"
		JLabel labelArr[] = new JLabel[13]; // 라벨//ラベル
		
		String[] caption = { "総 :               個", "購買日 : ", "帽子 : ",  "上着 : ", "ズボン : " , "靴 : ", "残高 :                           WON       充電金額 :   "};
		int Y1 = 10;
		int Y2 = 30;
		int Y3 = 35;
		int Y4 = 15;
		
		for (int i = 0; i < labelArr.length; i++) {
			if(i<=3){ //ラベルの数
				labelArr[i] = new JLabel(caption[0]);
				labelArr[i].setBounds(225, Y1, 200, 20);
				
				textField[i] = new JTextField(); // 물품갯수 텍스트 필드//物品の数テキストフィールド
				textField[i].setBounds(248, Y1, 40, 20);
				textField[i].setEditable(false);	//글씨 수정 못하게 비활성화//字を修正できないように無効化
				panel_comp.add(textField[i]); //패널에 넣기 //パネルに入れる
				Y1+=90;
				
			}else if(i<=7){ //구매일자 라벨//ラベルの購買日
				labelArr[i] = new JLabel(caption[1]);
				labelArr[i].setBounds(0, 30, 200, Y2);

				textField[i] = new JTextField(); // 날짜 텍스트 필드//日付のテキストフィールド
				textField[i].setBounds(62, Y3, 130, 21);
				textField[i].setEditable(false);	//글씨 수정 못하게 비활성화//字を修正できないように無効化
				panel_comp.add(textField[i]); //패널에 넣기 //パネルに入れる
				
				Y2+=180;
				Y3+=90;
				
			}else if(i<=11){ //상품명 라벨//商品名のラベル
				labelArr[i] = new JLabel(caption[i-6]);
				labelArr[i].setBounds(0, 10, 200, Y4);
				Y4+=180;
				
			}else if(i<=12){ //잔고 & 충전 금액//残高&チャージ金額
				labelArr[i] = new JLabel(caption[i-6]);
				labelArr[i].setBounds(0, 10, 400, Y4);
			}
			
			tfBalance.setBounds(35, 367, 72, 20);
			tfBalance.setEditable(false);
			tfCharge.setBounds(225, 367, 74, 20);
			tfCharge.setDocument(new JTextFieldLimit(10)); //글자수 제한
			
			panel_comp.add(tfBalance);
			panel_comp.add(tfCharge);
			
			panel_comp.add(labelArr[i]);
			
		}

		panel_comp.setSize(new Dimension(100, 50));
		panel_comp.setBounds(420, 37, 372, 471);
		panel_comp.setBackground(Color.WHITE);
		add(panel_comp);
		panel_comp.setLayout(null);

		label_12.setBounds(12, 10, 120, 15);
		add(label_12);
		
		comboBox_1.setBounds(62, 5, 130, 21);
		panel_comp.add(comboBox_1);
		
		comboBox_2.setBounds(62, 95, 130, 21);
		panel_comp.add(comboBox_2);
		
		comboBox_3.setBounds(62, 185, 130, 21);
		panel_comp.add(comboBox_3);
		
		comboBox_4.setBounds(62, 275, 130, 21);
		panel_comp.add(comboBox_4);
		
		btnMain.setBounds(235, 447, 136, 23);
		btnCharge.setBounds(300, 367, 70, 20);
		
		//충전하기 버튼		
		panel_comp.add(btnCharge);
		//메인으로 가기 버튼
		panel_comp.add(btnMain);
		
		
		//이미지 깨져서 일단 주석
/*		JLabel imglb = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("mp.gif")));
		imglb.setBounds(63, 361, 120, 120);
		panel_comp.add(imglb);*/

		btnCharge.addActionListener(new ActionListener() {  //메인으로 가기 버튼 이벤트 //メインに行くボタンイベント
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == btnCharge) {
						
						System.out.println("충전하기 버튼 클릭했다");
						
						int dialogResult = JOptionPane.showConfirmDialog(null, tfCharge.getText() +"WONを充電しますか。", "お知らせ", 0); // "원을 충전하시겠습니까?", "Warning"
						if (dialogResult == JOptionPane.YES_OPTION) {

							MyDressRoomDAO mdrdao = new MyDressRoomDAO();
							System.out.println("@@@@@@@@main " + MainFrame.id + Integer.parseInt(tfCharge.getText())); 
							
							int result = mdrdao.setCashCharge(MainFrame.id, Integer.parseInt(tfCharge.getText()));
							
							if(result!=0){
								JOptionPane.showMessageDialog(null, "充電要請をしました。.", "警告", 0);// "충전요청을 하였습니다.", "경고"
								tfCharge.setText("");
							}else{
								JOptionPane.showMessageDialog(null, "充電失敗 .", "警告", 0);// "충전실패", "경고"
							}
						}
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});

		
		
		btnMain.addActionListener(new ActionListener() {  //메인으로 가기 버튼 이벤트 //メインに行くボタンイベント
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == btnMain) {
						win.panelChange("LoginPage", MainFrame.id);
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});

		// 모자 콤보박스 이벤트//帽子のコンボボックスイベント
		comboBox_1.addActionListener(ie -> {

			JComboBox c = (JComboBox) ie.getSource();
			ClosetVO item = (ClosetVO) c.getSelectedItem();
//			System.out.println(item.getProductCode() + " : " + item.getProductName());

			if(item!=null){
				
				try {

					JLabel ImgBoxCap;
					MyDressRoomDAO mdrdao = new MyDressRoomDAO();
					ArrayList<MyDressRoomVO> list = mdrdao.getImage(item.getProductCode());

					System.out.println(list.size());
					for (int i = 0; i < list.size(); i++) {

						if (item.getProductCode().contains("P_CAP")) {
							
							System.out.println("list.get(i).getImagePath()" + list.get(i).getImagePath());
							
							ImgBoxCap = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(list.get(i).getImagePath())));
							System.out.println("@@@@@@@ : " +  ImgBoxCap.getHeight() + " 1111111111111111" +  ImgBoxCap.getHeight());
							ImgBoxCap.setBounds(0, 0, ImgBoxCap.getWidth(), ImgBoxCap.getHeight());

//							panel_cap.setBounds(list.get(i).getxAixs(), list.get(i).getyAixs(), ImgBoxCap.getWidth(), ImgBoxCap.getHeight());
							panel_cap.setBounds(list.get(i).getxAixs(), list.get(i).getyAixs(), 157, 123);
							panel_cap.removeAll(); //이전에 있던 아이템을 지운다//以前にいたアイテムを消す
							panel_cap.setVisible(true);  //보이게한다//見えるようにする
							panel_cap.add(ImgBoxCap); //이미지를 넣는다//イメージを入れる
							System.out.println("@@@@@@@ : " +  ImgBoxCap.getWidth() + " 222" +  ImgBoxCap.getHeight());
							
							panel_cap.revalidate(); //그림을 바꾸기 위한 메서드  //絵を変えるためのメソッド
							panel_cap.repaint(); //그림을 바꾸기 위한 메서드//絵を変えるためのメソッド
							panel_cap.updateUI(); //그림을 바꾸기 위한 메서드//絵を変えるためのメソッド
							
							panel_cap.addMouseMotionListener(this);
							panel_cap.addMouseListener(new MouseListener() {

								@Override
								public void mouseReleased(MouseEvent e) {
									// TODO Auto-generated method stub
									isDragged1 = false;
								}
								
								@Override
								public void mousePressed(MouseEvent me) {
									// TODO Auto-generated method stub
									if(panel_cap.contains(new Point(me.getX(),me.getY()))){
//										System.out.println("cap -- me.getX() : " + me.getX() +"  me.getY() : " + me.getY());
										panel_capX = me.getX() - panel_cap.getX();
										panel_capY = me.getY() - panel_cap.getY();
										isDragged1 = true;
									}
								}
								
								@Override
								public void mouseExited(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								@Override
								public void mouseEntered(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								@Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
							});
							
							textField[4].setText(list.get(i).getBuyDate()); //구매일자 입력//購買の日付入力
							System.out.println("@@@@@@@@@@@@@" +item.getProductCode());
							
						}else if (item.getProductCode().contains("none")) {
							
							panel_cap.setVisible(false); // 아무것도 선택 안했을때//何も選択しなかった時
							textField[4].setText("");
							
						}
					}
				} catch (Exception e) {
					System.out.println("e=[" + e + "]");
					e.printStackTrace();
				}
			}

		});

		
		// 상의 콤보박스 이벤트//上着のコンボボックスイベント
		comboBox_2.addActionListener(ie -> {
			JComboBox c = (JComboBox) ie.getSource();
			ClosetVO item = (ClosetVO) c.getSelectedItem();
//			System.out.println(item.getProductCode() + " : " + item.getProductName());

			if(item!=null){
				try {
					JLabel ImgBoxUp;
					MyDressRoomDAO mdrdao = new MyDressRoomDAO();
					ArrayList<MyDressRoomVO> list = mdrdao.getImage(item.getProductCode());

					System.out.println(list.size());
					for (int i = 0; i < list.size(); i++) {

						if (item.getProductCode().contains("P_UP")) {

							ImgBoxUp = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(list.get(i).getImagePath())));
							ImgBoxUp.setBounds(0, 0, ImgBoxUp.getWidth(), ImgBoxUp.getHeight());

//							panel_up.setBounds(list.get(i).getxAixs(), list.get(i).getyAixs(), ImgBoxUp.getWidth(), ImgBoxUp.getHeight());
							panel_up.setBounds(list.get(i).getxAixs(), list.get(i).getyAixs(), 172, 210);
							panel_up.removeAll();
							panel_up.setVisible(true);
							panel_up.add(ImgBoxUp);
							panel_up.revalidate();
							panel_up.repaint();
							panel_up.updateUI();
							panel_up.addMouseMotionListener(this);
							panel_up.addMouseListener(new MouseListener() {

								@Override
								public void mouseReleased(MouseEvent e) {
									// TODO Auto-generated method stub
									isDragged2 = false;
								}
								
								@Override
								public void mousePressed(MouseEvent me) {
									// TODO Auto-generated method stub
									if(panel_up.contains(new Point(me.getX(),me.getY()))){
//										System.out.println("cap -- me.getX() : " + me.getX() +"  me.getY() : " + me.getY());
										panel_upX = me.getX() - panel_up.getX();
										panel_upY = me.getY() - panel_up.getY();
										isDragged2 = true;
									}
								}
								
								@Override
								public void mouseExited(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void mouseEntered(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
							});
							
							textField[5].setText(list.get(i).getBuyDate()); //구매일자 입력 //購買の日付入力
							
						} else if (item.getProductCode().contains("none")) {
							panel_up.setVisible(false); // 아무것도 선택 안했을때//何も選択しなかった時
							textField[5].setText("");
						}
					}
				} catch (Exception e) {
					System.out.println("e=[" + e + "]");
				}
			}
		});

		// 바지 콤보박스 이벤트//ズボンのコンボボックスイベント
		comboBox_3.addActionListener(ie -> { //람다//ラムダ
			JComboBox c = (JComboBox) ie.getSource();
			ClosetVO item = (ClosetVO) c.getSelectedItem();
//			System.out.println(item.getProductCode() + " : " + item.getProductName());

			if(item!=null){
				try {
					JLabel ImgBoxDown;
					MyDressRoomDAO mdrdao = new MyDressRoomDAO();
					ArrayList<MyDressRoomVO> list = mdrdao.getImage(item.getProductCode());

					System.out.println(list.size());
					for (int i = 0; i < list.size(); i++) {

						if (item.getProductCode().contains("P_DOWN")) {
							ImgBoxDown = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(list.get(i).getImagePath())));
							ImgBoxDown.setBounds(0, 0, ImgBoxDown.getWidth(), ImgBoxDown.getHeight());

//							panel_down.setBounds(list.get(i).getxAixs(), list.get(i).getyAixs(), ImgBoxDown.getWidth(), ImgBoxDown.getHeight());
							panel_down.setBounds(list.get(i).getxAixs(), list.get(i).getyAixs(), 172, 100);
							panel_down.removeAll();
							panel_down.setVisible(true);
							panel_down.add(ImgBoxDown);
							panel_down.revalidate();
							panel_down.repaint();
							panel_down.updateUI();
							panel_down.addMouseMotionListener(this);
							panel_down.addMouseListener(new MouseListener() {

								@Override
								public void mouseReleased(MouseEvent e) {
									isDragged3 = false;
								}
								
								@Override
								public void mousePressed(MouseEvent me) {
									if(panel_down.contains(new Point(me.getX(),me.getY()))){
										panel_downX = me.getX() - panel_down.getX();
										panel_downY = me.getY() - panel_down.getY();
										isDragged3 = true;
									}
								}
								
								@Override
								public void mouseExited(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void mouseEntered(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
							});

							
							textField[6].setText(list.get(i).getBuyDate()); //구매일자 입력//購買の日付入力
							
						} else if (item.getProductCode().contains("none")) {
							panel_down.setVisible(false); // 아무것도 선택 안했을때//何も選択しなかった時
							textField[6].setText("");
						}
					}

				} catch (Exception e) {
					System.out.println("e=[" + e + "]");
					e.printStackTrace();
				}
			}
		});


		// 신발 콤보박스 이벤트//靴のコンボボックスイベント
		comboBox_4.addActionListener(ie -> {
			JComboBox c = (JComboBox) ie.getSource();
			ClosetVO item = (ClosetVO) c.getSelectedItem();
			// System.out.println(item.getProductCode() + " : " +item.getProductName());
			System.out.println("靴の変更 ");//"신발 변경 "

			if(item!=null){
				try {
					JLabel ImgBoxShoe;
					MyDressRoomDAO mdrdao = new MyDressRoomDAO();
					ArrayList<MyDressRoomVO> list = mdrdao.getImage(item.getProductCode());

					System.out.println(list.size());
					for (int i = 0; i < list.size(); i++) {

						if (item.getProductCode().contains("P_SHOE")) {
							
							ImgBoxShoe = new JLabel(new ImageIcon  (getClass().getClassLoader().getResource(list.get(i).getImagePath())     )    );
							ImgBoxShoe.setBounds(0, 0, ImgBoxShoe.getWidth(), ImgBoxShoe.getHeight());

							panel_shoe.setBounds(list.get(i).getxAixs(), list.get(i).getyAixs(), 176, 200);
//							panel_shoe.setBounds(list.get(i).getxAixs(), list.get(i).getyAixs(), ImgBoxShoe.getWidth(), ImgBoxShoe.getHeight());
							panel_shoe.removeAll();
							panel_shoe.setVisible(true);
							panel_shoe.add(ImgBoxShoe);
							panel_shoe.revalidate();
							panel_shoe.repaint();
							panel_shoe.updateUI();
							panel_shoe.addMouseMotionListener(this);
							panel_shoe.addMouseListener(new MouseListener() {

								@Override
								public void mouseReleased(MouseEvent e) {
									isDragged4 = false; //클릭이 풀리면 false //クリックが解ければfalse 
								}
								
								@Override
								public void mousePressed(MouseEvent me) {
									if(panel_shoe.contains(new Point(me.getX(),me.getY()))){
										panel_shoeX = me.getX() - panel_shoe.getX();
										panel_shoeY = me.getY() - panel_shoe.getY();
										isDragged4 = true;  //클릭 플래그 //クリックのフラグ
									}
								}
								
								@Override
								public void mouseExited(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void mouseEntered(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
								
								@Override
								public void mouseClicked(MouseEvent e) {
									// TODO Auto-generated method stub
									
								}
							});
							
							textField[7].setText(list.get(i).getBuyDate()); //구매일자 입력//購買の日付入力
							
						} else if (item.getProductCode().contains("none")) { // 아무것도 선택 안했을때//何も選択しなかった時
							panel_shoe.setVisible(false);
							textField[7].setText("");
						}
					}

				} catch (Exception e) {
					System.out.println("e=[" + e + "]");
					e.printStackTrace();
				}
			}
		});
	}//end of const

	public void getMyCloth(String id) throws Exception{ //내 옷장에 있는 옷을 조회한다.//私のたんすにいる服を照会する

		
		try {
			
			MyDressRoomDAO mdrdao = null;
			ArrayList<MyDressRoomVO> list = null;
			ArrayList<MyDressRoomVO> listMember = null;
			
			mdrdao = new MyDressRoomDAO();
			System.out.println("@@@@@@@@@@id" + id);
			list = mdrdao.getClosetList(id);
			listMember = mdrdao.getMemberList(id);

			comboBox_1.removeAllItems(); //기존의 아이템은 제거한다.//既存のアイテムは除去する。
			comboBox_2.removeAllItems(); //기존의 아이템은 제거한다.//既存のアイテムは除去する。
			comboBox_3.removeAllItems(); //기존의 아이템은 제거한다.//既存のアイテムは除去する。
			comboBox_4.removeAllItems(); //기존의 아이템은 제거한다.//既存のアイテムは除去する。
			
			comboBox_1.addItem(new ClosetVO("none", "none"));  //물품이 없을경우//物品がない場合
			comboBox_2.addItem(new ClosetVO("none", "none"));  //물품이 없을경우//物品がない場合
			comboBox_3.addItem(new ClosetVO("none", "none"));  //물품이 없을경우//物品がない場合
			comboBox_4.addItem(new ClosetVO("none", "none"));  //물품이 없을경우//物品がない場合
			
			gender = listMember.get(0).getGender(); //성별 입력//性別入力
			balance = listMember.get(0).getBalance(); //잔고//残高
			
			panel_man.remove(ImgBox); //기존에 있던 사람그림은 지운다.//既存の人の絵は消す。

			//성별 선택//性別選択
			if(gender==2){ //여성일때//女性の時
				ImgBox = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("girl.png")));
				ImgBox.setBounds(40, 105, 282, 366); //이미지 위치 설정//イメージの位置を設定
				panel_man.add(ImgBox);
			}else{
				ImgBox = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("boy.png")));
				ImgBox.setBounds(40, 112, 282, 366); //이미지 위치 설정//イメージの位置を設定
				panel_man.add(ImgBox);
			}
			
			if (list.size() != 0){ //구매한 물품이 있으면//購買した物品があれば
				
				for (int i = 0; i < list.size(); i++) { //콤보박스에 요소를 추가시켜준다.//コンボボックスに、要素を追加してくれる
					
					if (list.get(i).getProductCode().contains("P_CAP")) {
						comboBox_1.addItem(new ClosetVO(list.get(i).getProductCode(), list.get(i).getProductName()));
						
					} else if (list.get(i).getProductCode().contains("P_UP")) {
						comboBox_2.addItem(new ClosetVO(list.get(i).getProductCode(), list.get(i).getProductName()));

					} else if (list.get(i).getProductCode().contains("P_DOWN")) {
						comboBox_3.addItem(new ClosetVO(list.get(i).getProductCode(), list.get(i).getProductName()));

					} else if (list.get(i).getProductCode().contains("P_SHOE")) {
						comboBox_4.addItem(new ClosetVO(list.get(i).getProductCode(), list.get(i).getProductName()));
					}
				}
			}else{
				JOptionPane.showMessageDialog(null, "購入した商品がありません。", "警告", 0);// "구매한 상품이 없습니다."
			}
			
			// 물품 갯수 표시//物品の数を表示
			textField[0].setText(Integer.toString(comboBox_1.getItemCount() - 1));
			textField[1].setText(Integer.toString(comboBox_2.getItemCount() - 1));
			textField[2].setText(Integer.toString(comboBox_3.getItemCount() - 1));
			textField[3].setText(Integer.toString(comboBox_4.getItemCount() - 1));
//			tfBalance.setText(Integer.toString(list.get(0).getBalance()));
			System.out.println("################## : " + balance);
			
			String tmp = String.format("%,d", balance);
//			tfBalance.setText(Integer.toString(balance));
			tfBalance.setText(tmp);
		
		} catch (IndexOutOfBoundsException io) {
//			System.out.println(io);
			io.printStackTrace();
		}
		
	}
	//마우스 리스너 메서드 구현//マウスリスナーのメソッド具現

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent me) {
		// TODO Auto-generated method stub
		//frame.setLocation(e.getLocationOnScreen().x - frame.mouseClickedLocation.x, e.getLocationOnScreen().y - frame.mouseClickedLocation.y);
		
		if(isDragged1 && me.getSource() == panel_cap){ //모자 패널 누르면//帽子パネル押すと
			panel_cap.setLocation(me.getX()-panel_capX, me.getY()-panel_capY);
			//panel_cap.setLocation(me.getLocationOnScreen().x - panel_cap.mouseClickedLocation.x, me.getLocationOnScreen().y - panel_cap.mouseClickedLocation.y);
			
		}
		
		if(isDragged2 && me.getSource() == panel_up){ //상의 패널 누르면//うわぎパネル押すと
			System.out.println("panel_upX : " + panel_upX + " panel_upY : "  +panel_upY);
//			panel_up.setLocation(me.getX()-panel_upX, me.getY()-panel_upY);
			panel_up.setLocation(me.getX()-panel_upX, me.getY()-panel_upY);
			
		}
		
		if(isDragged3 && me.getSource() == panel_down){ //바지  패널 누르면//ズボンパネル押すと
			panel_down.setLocation(me.getX()-panel_downX, me.getY()-panel_downY);
			
		}
		
		if(isDragged4 && me.getSource() == panel_shoe){ //신발 패널 누르면//靴パネル押すと
			panel_shoe.setLocation(me.getX()-panel_shoeX, me.getY()-panel_shoeY);
		}
		
		repaint();
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
}
