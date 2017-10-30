/* 
 * =============================
 * 프로그램 설명 : 관리자 페이지 페널 생성을 위한 클래스  
 * プログラムの説明: 管理者ページフェンネルの生成のためのクラス
 * 작성자 : 김덕현, 오내훈 
 * 作成者 : キム・ドクヒョン、オ・ネフン
 * 최초 작성일자 :  2017-07-24
 * 最初の作成日付　: 2017-07-24
 * 최종 수정일 : 
 * 最終の修正日付　:
 * 수정 내용 : 	
 * 修正の内容 :
 * =============================
 * */

package com.bs.adminpage.control;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import com.bs.util.LabelAccessory;
import com.bs.view.MainFrame;
import com.bs.vo.AdminPageVO;
import com.bs.vo.MyDressRoomVO;

public class AdminPage extends JPanel implements ActionListener, MouseListener {
	private JButton btnProdIns, btnProdDel, btnConfirm;
	private JRadioButton rbutton1, rbutton2, rbutton3, rbutton4;
	private JTable adminTable;
	private JTable walletTable;
	private String selectedPdt = null;// 선택한 테이블 위치의 제품명 저장//選択したテーブルの位置の製品名保存
	private String selectedId= null;// 선택한 테이블 위치의 아이디 저장//選択したテーブルの位置の id保存
	private MainFrame win = null;

	private JLabel imglb = new JLabel();

	int xAxis = 0; // 상품이미지 X 축 좌표//商品イメージX軸の座標
	int yAxis = 0; // 상품이미지 Y 축 좌표//商品イメージY軸の座標

	public AdminPage(MainFrame win) {
		this.win = win;
		setLayout(null);
	}

	// 화면 새로 고침 메서드//画面更新のメソッド
	public void getRefresh(String id) throws Exception {
		
		selectedPdt = null; //화면 새로 고침 하면 초기화//画面更新のメソッド
		selectedId = null; //화면 새로 고침 하면 초기화//画面更新のメソッド
		
		JPanel panelBtn = new JPanel();
		panelBtn.setBounds(0, 530, 600, 40);
		panelBtn.setBackground(Color.WHITE);
		add(panelBtn);
		panelBtn.setLayout(null);

		JButton btn1, btn2;
		DefaultTableModel tablemodel; //addRow를 호출하기 위한 선언 　//addRowを呼び出すための宣言
		setBackground(Color.WHITE);

		ButtonGroup ct;
		JButton btnMain = new JButton("メインに移動");//"메인으로"
		btnProdIns = new JButton("物品登録");//"물품등록"
		btnProdDel = new JButton("物品削除");//"물품삭제"
		btnConfirm = new JButton("充電承認");//충전승인
		
		JButton btnUdt = new JButton("修正事項セーブ");//"수정사항저장"
		adminTable = new JTable(new AdminModel(null)); // 테이블 생성//テーブルを生成
		adminTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		adminTable.addMouseListener(this);
		JScrollPane scrollPane = new JScrollPane(adminTable);
		scrollPane.setLocation(0, 0);
		System.out.println("AdminModel.data.length : " + AdminModel.data.length);
		scrollPane.setSize(600, 520);
		
		walletTable = new JTable(new WalletModel(null)); // 지갑 테이블 생성//財布テーブル生成
		walletTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		walletTable.addMouseListener(this);
		JScrollPane scrollPane2 = new JScrollPane(walletTable);
		scrollPane2.setLocation(10, 150);
		scrollPane2.setSize(250, 340);		
		
		add(scrollPane);

		JPanel panel_comp = new JPanel();
		panel_comp.add(scrollPane2);
		
		/* DefaultTableModel을 구현하여 JTable을 생성한다. */
		/*DefaultTableModelを具現化してJTableを生成する。 */
		setLayout(null);

		panel_comp.setBounds(600, 30, 300, 530);
		panel_comp.setBackground(Color.WHITE);
		add(panel_comp);
		panel_comp.setLayout(null);

		setLayout(null);

		Panel panel = new Panel(new FlowLayout());
		panel.setBounds(471, 0, 313, 512);
		panel.setBackground(Color.WHITE);

		
		rbutton1 = new JRadioButton("帽　　子");// "모 자"
		rbutton1.setBounds(0, 0, 100, 20);
		rbutton1.setBackground(Color.WHITE);
		panel_comp.add(rbutton1);
		
		rbutton2 = new JRadioButton("上　　着");//"상 의"
		rbutton2.setBounds(0, 30, 100, 20);
		rbutton2.setBackground(Color.WHITE);
		panel_comp.add(rbutton2);
		
		rbutton3 = new JRadioButton("ズ　ボ　ン");//"하 의"
		rbutton3.setBounds(0, 60, 100, 20);
		rbutton3.setBackground(Color.WHITE);
		panel_comp.add(rbutton3);
		
		rbutton4 = new JRadioButton("　　靴　　");//"신 발"
		rbutton4.setBounds(0, 90, 100, 20);
		rbutton4.setBackground(Color.WHITE);
		panel_comp.add(rbutton4);

		ct = new ButtonGroup();
		ct.add(rbutton1);
		ct.add(rbutton2);
		ct.add(rbutton3);
		ct.add(rbutton4);

		btnProdIns.setBounds(130, 0, 112, 23); //물품등록//物品登録
		
		btnProdDel.setBounds(350, 0, 112, 23); //물품 삭제 //物品削除
		btnUdt.setBounds(467, 0, 130, 23); //물품 수정사항 저장//物品の修正事項保存
		
		btnMain.setBounds(150, 500, 112, 23); //메인으로 가기 //メインに移動
		btnConfirm.setBounds(20, 500, 112, 23); //충전 승인하기 //充電承認する

		btnProdIns.addActionListener(this); 
		btnProdDel.addActionListener(this);
		btnConfirm.addActionListener(this);
		
		panelBtn.add(btnProdDel);
		panelBtn.add(btnUdt);

		panel_comp.add(btnProdIns);
		panel_comp.add(btnConfirm);		
		panel_comp.add(btnMain);

//		JLabel imglb = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("mp.gif")));
//		imglb.setBounds(50, 220, 120, 120);
//		panel_comp.add(imglb);

		/* 화면의 레이아웃을 구성함 */
		/*画面のレイアウトを構成する*/

		add(panel);
		add(adminTable.getTableHeader(), BorderLayout.NORTH);
		setSize(900, 600);
		setVisible(true);

		btnUdt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ArrayList list = new ArrayList();
				AdminPageDAO apdao = new AdminPageDAO();
				int result = 0;

				try {

					if (e.getSource() == btnUdt && !AdminModel.udtList.isEmpty()) { // 수정사항이있다면 수정된 행번호를 리스트로 가져온다//修正事項があれば修正された行の番号をリストに持ち帰る
																				
						System.out.println("AdminModel.udtList.get(0)" + AdminModel.udtList.get(0));

						int dialogResult = JOptionPane.showConfirmDialog(null, "修正事項を保存しますか。", "お知らせ", 0); // 수정사항을 저장하시겠습니까?
																												
						if (dialogResult == JOptionPane.YES_OPTION) {

							for (int i = 0; i < AdminModel.udtList.size(); i++) { // 데이터row만큼 update한다.//データrowだけ、updateする。
																					
								for (int j = 0; j < 6; j++) { // 물품명, 사이즈, 브랜드 ,가격, 소재//物品名、サイズ、ブランド、価格、素材
									// System.out.print(AdminModel.data[i][j]);
									list.add(AdminModel.data[Integer.parseInt(AdminModel.udtList.get(i).toString())][j]);// data[row][column]
								}

								result += apdao.updateAdminPage(list);
								// list.removeAll(list);
								list.clear();

							}

							if (result != AdminModel.udtList.size()) { // 모든행을 업데이트 하지 않았을경우//全ての行をアップデートしなかった場合																		
								JOptionPane.showMessageDialog(null, "データ変更を失敗しました。", "警告", 0);// "데이터 변경을 실패했습니다.","경고"
								return;
							} else {
								JOptionPane.showMessageDialog(null, "データ変更を成功しました。", "お知らせ", 1);// "데이터 변경을 성공했습니다.","경고"
							}
							AdminModel.udtList.clear();
						}
					} else if (e.getSource() == btnUdt && AdminModel.udtList.isEmpty()) {
						JOptionPane.showMessageDialog(null, "データ修正事項がありません。", "お知らせ", 0);//"데이터 수정사항이 없습니다.", "경고"
					}

				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "データ変更を失敗しました。", "警告", 0);//"데이터 변경을 실패했습니다.", "경고"
					ex.printStackTrace();
				}
			}
		});
		//메인으로 이동//メインに移動
		btnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == btnMain) {
						win.panelChange("LoginPage", MainFrame.id);
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
		});
	}

	public void actionPerformed(ActionEvent e) {

		/* 추가 버튼이 눌린 경우 */
		/*追加ボタンが押された場合*/
		try {
			if (e.getSource() == btnProdIns) {
				if (rbutton1.isSelected()) {//모자 //帽子
					System.out.println(rbutton1.getText() + "버튼이 눌렸음");
					xAxis = 100;
					yAxis = 110;
					productUpload();

				} else if (rbutton2.isSelected()) {//상의 //うわぎ

					System.out.println(rbutton2.getText() + "버튼이 눌렸음");
					xAxis = 97;
					yAxis = 235;
					productUpload();
				} else if (rbutton3.isSelected()) {//바지 //ズボン

					System.out.println(rbutton3.getText() + "버튼이 눌렸음");
					xAxis = 97;
					yAxis = 309;
					productUpload();
				} else if (rbutton4.isSelected()) {//신발 //くつ

					System.out.println(rbutton4.getText() + "버튼이 눌렸음");
					xAxis = 95;
					yAxis = 385;
					productUpload();
				} else {// 라디오버튼 선택 안됐을 경우//ラジオボタンが選択されなかった場合
					JOptionPane.showMessageDialog(null, "ラジオボタンを選択してください。", "警告", 0);// "라디오 버튼을 선택해주세요.","경고"
				}
			} else if (e.getSource() == btnProdDel) {
				System.out.println("삭제버튼이 눌렸음");
				AdminPageDAO apdao = new AdminPageDAO();
				// AdminPageVO avo = new AdminPageVO();

				int dialogResult = JOptionPane.showConfirmDialog(null, "選択した物品を削除しますか", "選択", 0); // 선택하신 물품을 삭제하시겠습니까?, "선택
				if (dialogResult == JOptionPane.YES_OPTION) { // 예 눌렀을때//はいを押した時
					int result = apdao.getDeleteCloth(selectedPdt);// 옷정보테이블에서 삭제//服の情報テーブルから削除

					if (result == 0) {
						JOptionPane.showMessageDialog(null, "削除失敗しました。", "警告", 0);//"삭제실패했습니다.", "경고"
						return;

					} else if (result == 1) { 
						JOptionPane.showMessageDialog(null, "削除成功", "お知らせ", 1);// 삭제성공", "알림"
						win.panelChange("AdminPage", MainFrame.id);
						selectedPdt = null; // 모델명 초기화//モデル名初期化
					}
				} else { // 아니요 눌렀을때//いいえを押した時
					return;
				}

			}else if(e.getSource() == btnConfirm){
				
				System.out.println("충전 승인버튼 눌렸다");
				
				if(selectedId==null){ //아무것도 선택을 안했을때//何も選択をしなかった時
					JOptionPane.showMessageDialog(null, "掲示物を選択してください", "警告", 0);
					return;
				}
				
				int dialogResult = JOptionPane.showConfirmDialog(null, "充電を承認しますか。", "お知らせ", 0); // 충전을 승인하시겠습니까? "선택
				if (dialogResult == JOptionPane.YES_OPTION) { // 예 눌렀을때//はいを押した時
					AdminPageDAO apdao = new AdminPageDAO();
					int result = apdao.setCashConfirm(selectedId);// 선택한 유저의 지갑 테이블업데이트  //選択したユーザの財布テーブルをアップデート
					
					if(result!=0){
						JOptionPane.showMessageDialog(null, "承認成功", "警告", 0);//승인성공//承認に成功
						win.panelChange("AdminPage", MainFrame.id); //페이지 새로고침//ページ更新
					}else{
						JOptionPane.showMessageDialog(null, "承認失敗", "警告", 0);//승인실패//承認失敗
						return;
					}
					
				}
				
			}

		} catch (Exception ee) {
			ee.printStackTrace();
		}

	}

	public void productUpload() throws Exception {

		AdminPageDAO apdao = new AdminPageDAO();
		// MyDressRoomVO mdvo = new MyDressRoomVO();
		AdminPageVO avo = new AdminPageVO();// 이미지 테이블에 업데이트 하기 위한 변수//イメージテーブルにアップデートするための変数
		ArrayList<MyDressRoomVO> list = null;

		list = apdao.getImgList();
		String pCode = null;// 원래 코드//もとのコード
		String plusCode = null;// +1한 코드//+1したコード

		int cp = 0;// 맨마지막값을 가져오기위해 사용하는 변수//一番後ろの値を読み込むために使用する変数
		int plusNum = 0;// +1해주기 위한 변수//+1してやるための変数

		JFileChooser fs = new JFileChooser(new File("c:\\"));// 파일 오픈 경로//ファイルのオープン経路
		fs.setDialogTitle("Open a File");// 파일 오픈 시 다이얼로그 제목//ファイルオープンのときダイアログのタイトル
		fs.setFileFilter(new FileNameExtensionFilter("jpg", "png"));// 오픈할수 있는 파일 종류//オープンできるファイルの種類
		// 파일 선택시 프리뷰기능//ファイルを選択の時にプレビュー機能
		LabelAccessory accessory = new LabelAccessory(fs);
		fs.setAccessory(accessory);
		fs.addPropertyChangeListener(JFileChooser.SELECTED_FILE_CHANGED_PROPERTY, accessory);
		int result = fs.showSaveDialog(this);
		// 저장 버튼을 눌렀을 때
		if (result == JFileChooser.APPROVE_OPTION) {
			try {
				System.out.println("당신의 선택은" + fs.getSelectedFile().getName());// 파일이름//ファイル名
				File fi = fs.getSelectedFile();
				System.out.println("이미지 경로는" + fi.getPath());// 파일경로//ファイルの経路
				if (list != null) {
					// 파일명을 변경해줌//ファイル名を変更する
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getImageName().contains("P_CAP") && rbutton1.isSelected()) {//모자 선택시//帽子を選択する時
							if (i > cp) {//제품의 맨마지막 코드명을 가져옴//製品の一番うしるのコ-ド名をもたらすことになる
								cp = i;
								pCode = list.get(i).getImageName();
							}
						} else if (list.get(i).getImageName().contains("P_UP") && rbutton2.isSelected()) {//상의 선택시//うわぎを選択する時
							if (i > cp) {
								cp = i;
								pCode = list.get(i).getImageName();
							}
						} else if (list.get(i).getImageName().contains("P_DOWN") && rbutton3.isSelected()) {//바지 선택시//ズボンを選択する時
							if (i > cp) {
								cp = i;
								pCode = list.get(i).getImageName();
							}
						} else if (list.get(i).getImageName().contains("P_SHOE") && rbutton4.isSelected()) {//신발 선택시//靴を選択する時
							if (i > cp) {
								cp = i;
								pCode = list.get(i).getImageName();
							}
						}
					}
					System.out.println("pCode 저장!!" + pCode);
					String lastNum = pCode.substring(pCode.length() - 2, pCode.length());
					plusNum = Integer.parseInt(lastNum) + 1;
					plusCode = String.format(pCode.substring(0, pCode.length() - 2) + "%02d", plusNum);// 바뀐 파일명저장//変わったファイル名の保存
																										
					System.out.println("change2 값   : " + plusCode);
					apdao.setAdminList(plusCode, "H");// +1한 값과 'H'를 제품코드와 제품명으로 db에 저장//+1した値と'H'を製品コードと製品名でdbに保存
														

					byte[] buf = new byte[1024];
					FileInputStream fin = null;
					FileOutputStream fout = null;

					File imgPath = new File("img"); // img 폴더 경로를 가져온다//imgフォルダのパスをもってくる

					File oldFile = new File(fi.getPath());// 원본 파일 위치//原本ファイルの位置
					File newFile = new File(imgPath + "\\" + plusCode + ".png");// 업로드할 폴더와 저장할 파일명//アップロードするフォルダと保存するファイル名
																				
					// buf = new byte[1024];
					fin = new FileInputStream(oldFile);
					fout = new FileOutputStream(newFile);
					int read = 0;
					// 파일 이동//ファイルを移動
					while ((read = fin.read(buf, 0, buf.length)) != -1) {
						fout.write(buf, 0, read);
					}

					JOptionPane.showMessageDialog(null, "ファイルのアップロードの成功", "お知らせ", 1);// "파일 업로드 성공.","알림"
					fin.close();
					fout.close();

					avo.setProductCode(plusCode);
					avo.setxAxis(xAxis);
					avo.setyAxis(yAxis);
					apdao.setImageList(avo);// DB에 업로드한 정보 등록//DBにアップロードした情報を登録
					win.panelChange("AdminPage", MainFrame.id);// 변경사항을 테이블에 표시//変更事項をテーブルに表示
				}

			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, e2.getMessage());
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// 마우스로 선택한 테이블 위치를 행, 열값으로 저장//マウスで選択したテーブルの位置を行、列の値に保存
		if(e.getSource() == adminTable){
			int row = adminTable.getSelectedRow();
			selectedPdt = (String) adminTable.getValueAt(row, 0);// 선택한 행의 모델명 저장//選択した行のモデル名保存
			System.out.println("@@@@@@@@@@@@@@@@" + adminTable.getValueAt(row, 0) + "선택했습니다.");
		}else{
			System.out.println("다른 지갑 테이블 클릭");
			int row = walletTable.getSelectedRow();
			selectedId= (String) walletTable.getValueAt(row, 0);// 선택한 행의 모델명 저장//選択した行のモデル名保存
			System.out.println("@@@@@@@@@@@@@@@@" + walletTable.getValueAt(row, 0) + "선택했습니다.");
			
		}
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

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
