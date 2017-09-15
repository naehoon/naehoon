/* 
 * =============================
 * 프로그램 설명 : 로그인 페이지 패널을 생성하기 위한 클래스  
 * プログラムの説明: ログインページパネルを生成するためのクラス
 * 작성자 : 박신영  
 * 作成者 :  パク・シンヨン
 * 최초 작성일자 : 2017-07-25
 * 最初の作成日付　:　2017-07-25　
 * 최종 수정일 : 
 * 最終の修正日付　:
 * 수정 내용 : 	
 * 修正の内容 :
 * =============================
 * */

package com.bs.loginpage.control;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bs.memberjoin.control.MemberJoin;
import com.bs.mydressroom.control.MyDressRoomDAO;
import com.bs.view.MainFrame;
import com.bs.vo.ClothBasicVO;
import com.bs.vo.LoginPageVO;
import com.bs.vo.MemberVO;
import com.bs.vo.MyDressRoomVO;

public class LoginPage extends JPanel implements ActionListener , MouseMotionListener, MouseListener{
	
	private JPanel panel = new JPanel(); //
	private JPanel panel1 = new JPanel(); //
	private JPanel panel2 = new JPanel(); //
	private JPanel[] jpanelPdt; //상품정보를 담는 패널//商品情報が盛り込まれたパネル
	
	private JButton btnLogin = new JButton("ログイン");//"로그인"
	private JButton btnJoin = new JButton("会員加入");//"회원가입"
	private JButton btnLogout = new JButton("ログアウト");//"로그아웃"
	private JButton btnUserUpdate = new JButton("情報修正");//"회원정보수정"
	private JButton btnMyDressRoom = new JButton("私のたんす ");//"내 옷장"
	private JButton btnAdmin = new JButton("管理者");//"관리자 페이지//管理者ページ
	private JButton btnBoard = new JButton("掲示板");//"게시판"
	private JButton btnMyCart = new JButton("カート");//"장바구니보기 버튼//カート見るボタン
	private JButton btnCartInsert = new JButton(new ImageIcon(getClass().getClassLoader().getResource("cart.png")));//"카트담기"//カート盛り
	private JButton btnBuy = new JButton(new ImageIcon(getClass().getClassLoader().getResource("get.png")));	//"구매하기"//購買する
	
	private JLabel label_9 = null;
	private JLabel labelBal = null;
	private JLabel label_pCode[] = null; // 제품코드//製品コード
	private JLabel label_pName[] = null; // 제품명//製品名
	private JLabel label_pSize[] = null; // 제품사이즈//製品のサイズ
	private JLabel label_pBrand[] = null; // 제품브랜드//製品ブランド
	private JLabel label_pMaterial[] = null; // 제품소재//製品の素材
	private JLabel labelwm = new JLabel();	//움직이는수박이미지라벨//動くスイカイメージのラベル
	private JLabel lbId = new JLabel("ID");  //ID 라벨//IDのラベル
	private JLabel lbPass = new JLabel("Pass"); //password 라벨//passwordのラベル
	private JLabel lbLogin = new JLabel("ログイン");//로그인라벨//ログインのラベル
	private JLabel lbBuybtn = new JLabel("購買する");	//라벨//ラベル
	private JLabel lbCartbtn = new JLabel("カートに入れる");	//라벨//ラベル
	private JLabel labelpattern =null;
	
	private JPasswordField textFieldPass = new JPasswordField(); //password textField
	private JTextField textFieldId = new JTextField();  //ID textField
	
	public static int adminFlag; //관리자 여부//管理者かどうか
	public static String loginFlag = "N"; // 로그인 여부//ログインしたかどうか
	public int balance = 0; // 현재 잔고//現在の残高
	
	private int sum = 0;// 합계 저장 변수//合計保存の変数
	private LoginPageDAO lpdao = null;
	private List<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
	private JCheckBox chkButton[] = null;
	private ArrayList<ClothBasicVO> list = null;

	public LoginPage(MainFrame win) {
		
		btnUserUpdate.addActionListener(this);
		//관리자 버튼 클릭이벤트//管理者にボタンクリックイベント
		btnAdmin.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == btnAdmin && loginFlag.equals("N")) { // 로그인을안했을경우//ログインをしなかった場合
						JOptionPane.showMessageDialog(null, "ログイン後使に用可能です。.", "警告", 0);//"로그인후 사용가능합니다.", "경고"
						return;
					} else {
						win.panelChange("AdminPage", textFieldId.getText());
						for (int i = 0; i < checkBoxes.size(); i++) { //셀렉트 박스 체크 해제하기 //selectBoxesチェック解除する
							checkBoxes.get(i).setSelected(false);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});

		//	게시판 버튼 클릭 이벤트 //掲示板にボタンクリックイベント
		btnBoard.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == btnBoard && loginFlag.equals("N")) { // 로그인을안했을경우//ログインをしなかった場合
						JOptionPane.showMessageDialog(null, "ログイン後使に用可能です。.", "警告", 0);//"로그인후 사용가능합니다.", "경고"
						return;
					} else {
						win.panelChange("Board", textFieldId.getText());
						for (int i = 0; i < checkBoxes.size(); i++) { //셀렉트 박스 체크 해제하기 //selectBoxesチェック解除する
							checkBoxes.get(i).setSelected(false);
						}
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
		
		//내 장바구니 확인 버튼 클릭 이벤트(카트)//私のカートの確認ボタンクリックイベント(カート)
		btnMyCart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == btnMyCart && loginFlag.equals("N")) { // 로그인을안했을경우//ログインをしなかった場合
						JOptionPane.showMessageDialog(null, "ログイン後に使用可能です。.", "警告", 0);//"로그인 후 사용가능합니다.", "경고"
						return;
					} else {
						win.panelChange("MyCart", textFieldId.getText());
						for (int i = 0; i < checkBoxes.size(); i++) { //selectBoxesチェック解除する
							checkBoxes.get(i).setSelected(false);
						}
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});

		// 내옷장으로 가기 버튼 클릭 이벤트//私のたんすに行くボタンクリックイベント
		btnMyDressRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					MyDressRoomDAO mdrdao = null;
					ArrayList<MyDressRoomVO> list = null;
					mdrdao = new MyDressRoomDAO();
					list = mdrdao.getClosetList(MainFrame.id);
					
					if (e.getSource() == btnMyDressRoom && loginFlag.equals("N")) { // 로그인을안했을경우//ログインをしなかった場合
						JOptionPane.showMessageDialog(null, "ログイン後に使用可能です。.", "警告", 0);//"로그인 후 사용가능합니다.", "경고"
						return;
//					}else if(list.size() ==0){ //구매한 상품이 없을경우
//						JOptionPane.showMessageDialog(null, "購入した商品がありません。", "警告", 1);// "구매한 상품이 없습니다.", "경고"
					}else {
						win.panelChange("MyDressRoom", textFieldId.getText()); //내 옷장으로 패널 변경하기 //私のたんすにパネル変更する
						
						
						for (int i = 0; i < checkBoxes.size(); i++) { //셀렉트 박스 체크 해제하기 //selectBoxesチェック解除する
							checkBoxes.get(i).setSelected(false);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		btnLogin.addActionListener(new ActionListener() { //로그인 버튼 눌렀을때//ログインボタン押した時
			
			public void actionPerformed(ActionEvent e) {
				try {
					panel.setVisible(true);

					if (textFieldId.getText().equals("")) { // 아이디를 입력하지 않았을때//IDを入力しなかったとき
						JOptionPane.showMessageDialog(null, "IDを入力してください.", "警告", 0);//"아이디를 입력해주세요", "경고"
						System.out.println("아이디를 입력해주세요. ");//
						return;
					} else if (textFieldPass.getText().equals("")) { // 패스워드를 입력안했을때//パスワードを入力しなかった時
						JOptionPane.showMessageDialog(null, "パスワードを入力してください.", "警告", 0);//"패스워드를 입력해주세요", "경고"
						System.out.println("パスワードを入力してください.");//"패스워드를 입력해주세요. "
						return;
					} else if (textFieldId.getText() != null && textFieldPass.getText() != null) { // 둘다//二つとも
																									// 입력했으면//入力したら
						LoginPageDAO lpdao = new LoginPageDAO();
						ArrayList<MemberVO> list = lpdao.getMemberList(textFieldId.getText()); // DB조회//DBの照会
							
						if (list.size() == 0) { // 조회를 못해왔으면//照会ができず、来たら
							
							JOptionPane.showMessageDialog(null, "該当する使用者がいないです.", "警告", 0);//"해당하는 사용자가 없습니다.", "경고"
							System.out.println("해당하는 사용자가 없습니다.");//"해당하는 사용자가 없습니다."
							return;

						}else if (!list.get(0).getId().equals(textFieldId.getText())){
							JOptionPane.showMessageDialog(null, "아이디 대소문자를 확인해주세요.", "警告", 0);//"해당하는 사용자가 없습니다.", "경고"
						
						}else if (!list.get(0).getPassword().equals(textFieldPass.getText())) { // 패스워드
																									// 틀리면//間違ったら
							JOptionPane.showMessageDialog(null, "パスワードが間違っています。", "警告", 0);//"패스워드가 틀렸습니다.", "경고"
							System.out.println("패스워드가 틀렸습니다.");//
							return;
							
						}else if(list.get(0).getAdmin_flag()==0){ // 아이디 패스워드가 맞으면 (일반사용자)//ID、パスワードが合うと(一般利用者)
							balance = list.get(0).getBalance(); //잔고 입력 //残高入力
							adminFlag = list.get(0).getAdmin_flag();		
							userLogin();
							MainFrame.autoSelect = false; //데몬스레드 끄기//デモンスレッドを消す
							
						}else if(list.get(0).getAdmin_flag()==1){ //관리자로 로그인했을때//管理者でログインしたとき
							balance = list.get(0).getBalance(); //잔고 입력//残高入力
							adminFlag = list.get(0).getAdmin_flag();
							adminLogin();
							MainFrame.autoSelect = true; //데몬 스레드 작동시킨다//デーモンのスレッドを作動させる
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		btnJoin.addActionListener(new ActionListener() { //회원가입 버튼 이벤트//会員加入ボタンイベント
			public void actionPerformed(ActionEvent e) {
				try {
					
					MemberJoin join = new MemberJoin();
					join.setVisible(true);
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		btnCartInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == btnCartInsert && loginFlag.equals("Y")) { // 카트에담기버튼클릭했을때(로그인상태일때)//CartInsertボタンをクリックしたとき(ログイン状態の時)
						
						System.out.println("카트에 담기 버튼 클릭함");//"카트에 담기 버튼 클릭함"
						
						int selectCnt = 0; // 선택된 콤보박스 개수//選択されたコンボボックスの数
						for (int i = 0; i < checkBoxes.size(); i++) {
							if (checkBoxes.get(i).isSelected()) {
								selectCnt++;
							}
						}

						if (selectCnt == 0) {
							JOptionPane.showMessageDialog(null, "商品が選択されていません。", "お知らせ ", 0);//"선택한 상품이 없습니다.", "알림"
							return;
						}
						
						try {
							LoginPageDAO lpdao = new LoginPageDAO();
							ArrayList<ClothBasicVO> cartList = new ArrayList();
							String productCode = "";

							for (int i = 0; i < checkBoxes.size(); i++) {
								if (checkBoxes.get(i).isSelected()) {
									System.out.println("checkBoxes.is select " + checkBoxes.get(i).getText());
									productCode = checkBoxes.get(i).getText();
									int result = lpdao.setCartInfo(productCode, MainFrame.id); 
									if (result == 0) {
										JOptionPane.showMessageDialog(btnLogin, "カート盛り失敗.", "警告", 0);// "카트담기 실패.", "경고"
									}
								}
							}
							JOptionPane.showMessageDialog(null, "選択した商品をカートに入れました。", "お知らせ", 1);//"선택한 상품을 카트에 담았습니다.", "알림"
							
							for (int i = 0; i < checkBoxes.size(); i++) { //셀렉트 박스 체크 해제하기// selectboxチェック解除すること
								checkBoxes.get(i).setSelected(false);
							}
							

						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}else if (e.getSource() == btnCartInsert && loginFlag.equals("N")) { // 로그인안됬을때//ログインできなかった時
						JOptionPane.showMessageDialog(null, "ログイン後使に用可能です。", "警告", 0);// "로그인후 사용가능합니다.", "경고"
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		
		
		btnBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (e.getSource() == btnBuy && loginFlag.equals("Y")) { // 구매하기버튼클릭했을때(로그인상태일때)

						System.out.println("구매버튼 클릭함");//

						int selectCnt = 0; // 선택된 콤보박스 개수//選択されたコンボボックスの数
						for (int i = 0; i < checkBoxes.size(); i++) {
							if (checkBoxes.get(i).isSelected()) {
								selectCnt++;
								// System.out.println("!checkBoxes.get(i).isSelected()"
								// + !checkBoxes.get(i).isSelected());
							}
						}

						if (selectCnt == 0) {
							JOptionPane.showMessageDialog(null, "選択した商品がありません.", "お知らせ", 0);//"선택한 상품이 없습니다.", "알림"
							return;
						}

						int dialogResult = JOptionPane.showConfirmDialog(null, "選択した商品を購買しますか。", "お知らせ", 0); // "선택한 상품을 구매하시겠습니까?", "Warning"
						if (dialogResult == JOptionPane.YES_OPTION) {

							try {
								LoginPageDAO lpdao = new LoginPageDAO();
								String productCode = "";
								int productPrice = 0; //선택한 상품의 총가격//選択した商品の銃価格
								
								ArrayList<LoginPageVO> list = lpdao.getBalance(MainFrame.id); //현재 잔고 조회//現在の残高を照会
								System.out.println("!!!!!!!!!!!!!!!!!!!! : " + list.get(0));
								
								for (int i = 0; i < checkBoxes.size(); i++) {
									if(checkBoxes.get(i).isSelected()){ //체크되어있는 상품만//チェックされている商品だけ
										productCode = checkBoxes.get(i).getText(); //선택한 상품의 코드를 가져온다.//選択した商品のコードをもたらす
										ArrayList<LoginPageVO> priceList = lpdao.getClothPrice(productCode); //선택한 상품의 가격을 가져온다.//選択した商品の価格をもたらす
										
										for (int j = 0; j < priceList.size(); j++) { //가격들을 합산한다.//価格を合算する
											productPrice += priceList.get(j).getPrice();
											System.out.println("2222222222222222 : " + productPrice);
										}
									}
								}
								System.out.println("@@@@@@@@@@@@ : " + productPrice);
								
								if(productPrice > Integer.parseInt(list.get(0).getBalance())){ //선택한 상품가격보다 잔고가 적으면 //
									JOptionPane.showMessageDialog(null, "残高が不足します。 現在の残高 : " + list.get(0).getBalance() + "WON", "お知らせ", 0);//"잔고가 부족합니다.
									return;
								}

								for (int i = 0; i < checkBoxes.size(); i++) {
									if (checkBoxes.get(i).isSelected()) {
										System.out.println("checkBoxes.is select " + checkBoxes.get(i).getText());
										productCode = checkBoxes.get(i).getText(); //선택한 상품의 코드//選択した商品のコード
										
										int result = lpdao.setClosetInfo(MainFrame.id, productCode);
										result += lpdao.setBalanceMinus(MainFrame.id, productCode); //잔고 감소시켜줌.//残高ﾐの減少させる
										
										if (result == 0) { // DB에 입력이 실패// DBに入力が失敗
											JOptionPane.showMessageDialog(null, "製品の購入失敗.", "警告", 0);// "제품구매 실패.", "경고"
											return;
										}
									}
								}
								JOptionPane.showMessageDialog(null, "選択した商品を購入しました。", "お知らせ ", 1);// "선택한 상품을 구매하였습니다.", "알림"
								
								for (int i = 0; i < checkBoxes.size(); i++) { //셀렉트 박스 체크 해제하기 //selectBoxチェック解除する
										checkBoxes.get(i).setSelected(false);
								}
								
							} catch (Exception ex) {
								ex.printStackTrace();
							}

						} else { // 아니오 버튼 선택했을때//いいえボタンを選択したとき
							return;
						}

					} else if (e.getSource() == btnBuy && loginFlag.equals("N")) {
						JOptionPane.showMessageDialog(null, "ログイン後に使用可能です。", "警告", 0);// "로그인후 사용가능합니다.", "경고"

					}
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (e.getSource() == btnLogout && loginFlag.equals("Y")) {
						System.out.println("로그아웃 버튼 클릭");//
						
						loginFlag = "N"; // 로그인 플래그 N//ログインのフラグN
						textFieldId.setText(""); // 텍스트 필드 초기화//テキストフィールドの初期化
						textFieldPass.setText(""); // 텍스트 필드 초기화//テキストフィールド初期化
						
						panel2.removeAll();  //로그아웃창 내용을 모두 지운다.//ログアウッ画面の内容を全て消す
						panel2.setVisible(false); // 로그아웃창 안보이게//ログアウッ画面を見えなくする
						panel.setVisible(true); // 로그인창 보이게//ログアウッ画面を見えるように
						btnAdmin.setVisible(false);
						MainFrame.autoSelect = false; //데몬스레드 끄기//デモンスレッドを消す
						
					}
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		
	}
	
	public int refreshList(){ //상품 패널 재조회 하기//商品パネル再照会する
		
		btnAdmin.setVisible(false); //관리자 페이지 버튼안보이게 하기//管理者ページボタン見えないようにする
		
		labelwm = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("wm.gif")));	//"수박이미지"//スイカイメージ
		labelpattern = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("pattern2.png")));	//"수박이미지"//スイカイメージ
		labelpattern.setBounds(0,0,900,5);
		add(labelpattern);
//		
		setBackground(Color.white);
		setBounds(new Rectangle(50, 50, 900, 600)); //패널 사이즈 //パネルサイズ
		setLayout(null); //레이아웃 없애기//レイアウトなくす

		panel.setBackground(Color.WHITE);
		panel.setBounds(12, 26, 220, 205);
		add(panel);
		panel.setLayout(null);

		// 로그인 버튼//ログインボタン
		btnLogin.setBounds(121, 159, 87, 23);
		panel.add(btnLogin);
		
		lbId.setBounds(18, 58, 29, 15);//ID 라벨 //IDのラベル
		panel.add(lbId);

		lbPass.setBounds(18, 89, 29, 15);//password 라벨//passwordのラベル
		panel.add(lbPass);

		lbLogin.setBounds(18, 9, 69, 15);//로그인라벨//ログインのラベル
		panel.add(lbLogin);

		textFieldPass.setBounds(58, 86, 116, 21); //password 텍스트 필드//passwordのテキストフィールド
		panel.add(textFieldPass);
		textFieldPass.setColumns(10);
		
		btnJoin.setBounds(18, 159, 91, 23);
		panel.add(btnJoin);

		textFieldId.setBounds(58, 55, 116, 21);
		panel.add(textFieldId);
		textFieldId.setColumns(10);
		
		
		//"カートに入れる"
//		btnCartInsert = new JButton(new ImageIcon(getClass().getClassLoader().getResource("cart.png")));//"카트담기"
		btnCartInsert.setBorder(null);
		btnCartInsert.setBounds(669, 5, 50, 50);
		
		lbCartbtn.setBounds(656,55,104,15);
		add(lbCartbtn);
		add(btnCartInsert);
		
		//購買する
//		btnBuy = new JButton(new ImageIcon(getClass().getClassLoader().getResource("get.png")));	//"구매하기"
		btnBuy.setBackground(new Color(240, 240, 240));
		btnBuy.setBorder(null);
		btnBuy.setBounds(765, 5, 50, 50);
		
		add(btnBuy);
		
		lbBuybtn.setBounds(765,55,53,15);//		jl2 = new JLabel("購買する");	//라벨//ラベル
		add(lbBuybtn);
		
		btnMyDressRoom.setBounds(44, 260, 158, 40);  //내옷장으로 가기 버튼//私のたんすに行くボタン
		add(btnMyDressRoom);
		
		btnBoard.setBounds(44, 340, 158, 40); //게시판 페이지 버튼//掲示板ページボタン
		add(btnBoard);
		
		btnMyCart.setBounds(44, 420, 158, 40); //내 장바구니 보기 버튼//私のカ-ト見るボタン
		add(btnMyCart);
		
		btnAdmin.setBounds(44, 500, 158, 40); //관리자 페이지 버튼 //管理者ページボタン
		add(btnAdmin);
		
		panel1.setBackground(Color.WHITE);
		panel1.setBounds(12, 244, 220, 308);
		add(panel1);
		
		if(loginFlag.equals("Y") && adminFlag==0){ // 일반 유저 로그인//一般ユーザーログイン
			btnAdmin.setVisible(false); //관리자 페이지 버튼 안보이게 //管理者ページボタン見えないように
			userLogin();
			MainFrame.autoSelect = false; //데몬스레드 끄기//デモンスレッドを消す
			
		}else if(loginFlag.equals("Y") && adminFlag ==1){ //관리자로 로그인했을때//管理者でログインしたとき
			btnAdmin.setVisible(true);  //관리자 페이지 버튼 보이게//管理者ページボタン見えるように
			adminLogin();
			MainFrame.autoSelect = true; //데몬 스레드 작동시킨다//デーモンのスレッドの作動させる
		}

		setVisible(true);
		
		int compCnt = 0; //상품 개수//商品数

		try { // 상품 화면 조회하기//商品画面を照会する
			// ArrayList<ClosetBasicVO> list = null;
			lpdao = new LoginPageDAO();// 서버에서 가져온 값을 쓰기위해 DAO 객체화//サーバーから持ってきた値を書くためにDAOオブジェクト化
			list = lpdao.getProductInfo();
			
			// list에 내용이 있을경우 실행//listに内容がある場合、実行
			if (list != null) {
				
				compCnt = list.size();

				int jpanelAxisLeft = 70; // 패널 위치 변수//パネルの位置変数
				int jpanelAxisRight = 70; // 패널 위치 변수//パネルの位置変数

				for (int i = 0; i < list.size(); i++) {
					
					System.out.println("jpanelAxis :" + jpanelAxisLeft);
					
					jpanelPdt = new JPanel[list.size()];
					jpanelPdt[i] = new JPanel();
					jpanelPdt[i].setBackground(Color.WHITE);
					
					if (i % 2 == 0) {  //짝수번째 상품일경우 왼쪽//偶数番目の商品の場合左
						jpanelPdt[i].setBounds(250, jpanelAxisLeft, 290, 153);
						jpanelAxisLeft += 165;  //X 축 증가//X軸の増加
					} else {  //홀수번째 상품일경우 오른쪽//奇数番目の商品の場合左
						jpanelPdt[i].setBounds(550, jpanelAxisRight, 290, 153);
						jpanelAxisRight += 165; //X 축 증가//X軸の増加
					}

					add(jpanelPdt[i]);
					jpanelPdt[i].setLayout(null);

					JLabel label_num[] = new JLabel[list.size()];
					label_num[i] = new JLabel(Integer.toString(i + 1)); // 상품 순번//商品の順番
					label_num[i].setBounds(12, 10, 15, 15);
					jpanelPdt[i].add(label_num[i]);

					JLabel label_pName[] = new JLabel[list.size()]; // 제품명//製品名
					label_pName[i] = new JLabel();
					label_pName[i].setBounds(193, 10, 70, 15);
					jpanelPdt[i].add(label_pName[i]);

					JLabel label_pSize[] = new JLabel[list.size()]; // 제품사이즈//製品のサイズ
					label_pSize[i] = new JLabel(); // 사이즈//サイズ
					label_pSize[i].setBounds(193, 30, 70, 15);
					jpanelPdt[i].add(label_pSize[i]);

					JLabel label_pBrand[] = new JLabel[list.size()]; // 제품브랜드//製品のブランド
					label_pBrand[i] = new JLabel(); // 사이즈//サイズ
					label_pBrand[i].setBounds(193, 50, 70, 15);
					jpanelPdt[i].add(label_pBrand[i]);

					JLabel label_pMaterial[] = new JLabel[list.size()]; // 제품소재//製品の素材
					label_pMaterial[i] = new JLabel(); // 사이즈//サイズ
					label_pMaterial[i].setBounds(193, 70, 70, 15);
					jpanelPdt[i].add(label_pMaterial[i]);

					JLabel label_pCode[] = new JLabel[list.size()]; // 제품코드//製品のコード
					label_pCode[i] = new JLabel();
					// label_pCode[i].setBounds(193, 80, 70, 15);
					jpanelPdt[i].add(label_pCode[i]);
					// label_pCode[i].setVisible(false);// 코드 안보이게 하기//コード見えないようにする

					JLabel label_price[] = new JLabel[list.size()];

					label_price[i] = new JLabel(); // 가격표//値札
					label_price[i].setBounds(193, 90, 70, 15);
					jpanelPdt[i].add(label_price[i]);

					chkButton = new JCheckBox[list.size()]; // 체크박스//ェックボックス
					chkButton[i] = (JCheckBox) new JCheckBox(list.get(i).getProductCode()); // 제품//製品
																							// 모델명을넣어준다.//モデル名を入れてあげる。
					chkButton[i].setBounds(185, 110, 150, 23);
					chkButton[i].setBackground(Color.WHITE);
					chkButton[i].setOpaque(false);
					
					this.checkBoxes.add(chkButton[i]);
					jpanelPdt[i].add(chkButton[i]);

					JPanel panel_pic[] = new JPanel[list.size()]; // 그림 패널//絵のパネル
					panel_pic[i] = new JPanel();
					panel_pic[i].setBounds(39, 12, 142, 130);
					jpanelPdt[i].add(panel_pic[i]);
					jpanelPdt[i].addMouseListener(this);
						
					label_pName[i].setText("- " + list.get(i).getProductName()); // 제품명//製品名
					label_price[i].setText("- "+ list.get(i).getPrice() + " won");// 가격//価格

					label_pCode[i].setText(list.get(i).getProductCode());// 제품코드//製品のコード
					// chkButton[i].add(label_pCode[i]); //체크 박스에 제품코드를 추가한다.

					label_pSize[i].setText("- " + list.get(i).getSize());// 제품사이즈製品のコード
					label_pBrand[i].setText("- " + list.get(i).getBrand());// 제품//製品
																			// 브랜드//ブランド
					label_pMaterial[i].setText("- " + list.get(i).getMaterial());// 제품//製品
																					// 소재//素材
					sum += list.get(i).getPrice();// 값을 모두더하기//値を全部加える　　

					ImageIcon imgProd[] = new ImageIcon[list.size()];
					imgProd[i] = new ImageIcon(getClass().getClassLoader().getResource(list.get(i).getImagePath())); //이미지 경로 가져오기//イメージ経路もたらす
//					imgProd[i] = new ImageIcon("D:\\ecsy\\project_team2\\src\\img\\" + list.get(i).getImagePath()); //이미지 경로 가져오기
					
					
					System.out.println("!!!!!!!!!!!!!!" + list.get(i));

					JLabel label_pic[] = new JLabel[list.size()];
					label_pic[i] = new JLabel();
					label_pic[i] = new JLabel(imgProd[i]);
					label_pic[i].setBounds(50, 0, imgProd[i].getIconWidth(), imgProd[i].getIconHeight());
					panel_pic[i].add(label_pic[i]);
					
				} // for end

			} // end if

		} catch (Exception e) {
			System.out.println("e=[" + e + "]");
			e.printStackTrace();
		}
		
		return compCnt;
	}
	
	public void userLogin(){
		LoginPanel();
	}
	
	public void adminLogin(){
		btnAdmin.setVisible(true);
		LoginPanel();
	}
	
	public void LoginPanel(){
		
		loginFlag = "Y"; // 로그인 여부 Y 로 변경//ログインしたかどうかYに変更
		MainFrame.id = textFieldId.getText(); // id 넘겨주기//idを渡せる
		panel.setVisible(false);
		panel2.setBackground(Color.WHITE);
		panel2.setBounds(12, 26, 220, 205);
		panel2.setLayout(null);
		panel2.setVisible(true);
		
		labelwm = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("wm.gif")));	//"수박이미지"//スイカイメージ
		labelwm.setBounds(50,15,100,100);
		label_9 = new JLabel(textFieldId.getText() + "   様 ようこそ。"); // 환영메시지출력//" 님 환영합니다"
		label_9.setBounds(50, 80, 200, 50);
		
		String tmp = String.format("%,d", balance); //잔고//残高
		labelBal = new JLabel(" 残高 : " + tmp + " won"); //잔고 //残高
		labelBal.setBounds(50, 105, 200, 50);
		
		btnLogout.setBounds(115, 159, 100, 23);
		btnUserUpdate.setBounds(18,159,90,23);//회원정보수정//会員情報修正
		
		panel2.add(labelwm);
		panel2.add(btnLogout); //로그아웃 버튼 넣어준다.//ログアウトボタン入れてあげる
		panel2.add(btnUserUpdate);//회원정보수정//会員情報修正

		panel2.add(labelBal);
		panel2.add(label_9);
		add(panel2);
		
	}
	

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		//System.out.println("@@@@@@@@@@@@@@@@@" + e);
	}


	@Override
	public void mouseClicked(MouseEvent e) {
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
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("@@@@@mouseEntered@@@@@@@@@");
//		Color b=new Color(000,250,154);
		Color b=new Color(127,255,212);  
//		e.getComponent().setBackground(Color.lightGray);
		e.getComponent().setBackground(b);
	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
//		System.out.println("@@@@@mouseExited@@@@@@@@@");
		e.getComponent().setBackground(Color.white);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnUserUpdate){
			System.out.println("회원수정버튼눌림");
			MemberJoin update = new MemberJoin();
			update.setVisible(true);
			update.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //종료시 프로세스제거 //終了の時プロセスの除去
		}
		
	}

}
