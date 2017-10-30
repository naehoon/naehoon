/* 
 * =============================
 * 프로그램 설명 : 장바구니에서 구매,삭제기능, 메인화면으로 가기 기능 구현   
 * プログラムの説明: カ-トで購買、削除機能、メイン画面に移動機能の具現
 * 작성자 : 김덕현, 오내훈, 박신영  
 * 作成者 : キム・ドクヒョン、オ・ネフン、パク・シンヨン
 * 최초 작성일자 :     2017-07-18
 * 最初の作成日付 :  2017-07-18
 * 최종 수정일 :  
 * 最終の修正日付 :
 * 수정 내용 :   
 * 修正の内容 :
 * ============================= 
 * */

package com.bs.mycart.control;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.bs.loginpage.control.LoginPageDAO;
import com.bs.view.MainFrame;
import com.bs.vo.CartVO;
import com.bs.vo.ClothBasicVO;
import com.bs.vo.LoginPageVO;

public class MyCart extends JPanel implements ActionListener, MouseListener {

   private JButton button_Buy, button_Del, button_Main;// 구매, 삭제, 메인으로　가기 버튼　　
						       // 購買、削除、メインにいくボタン
   private JLabel label_pic1, label_pic2, label_pic3;// 이미지 출력 라벨 　
						     //	イメージ出力ラベル
   private JLabel label_num1, label_num2, label_num3;// 장바구니에서 목록 순서(1,2,3...)　
						     // カートでリストの順番(1、2、3...)
   private JLabel label_picimage;// "총금액이미지라벨 "　
				 // "総金額のイメージラベル"
   private JLabel label_sumCh = new JLabel("総額 ");// "총금액 : "　
						    // "総金額：　"
   private JPanel panelTop = new JPanel();// 4번 패널　
					  // 4番のパネル
   private JLabel label_sumNum = new JLabel("0won ");
   // 서버에서 데이터 가져오기 위해 초기화한 변수 2개　
   // サーバーからデータをもらうための初期化したの変数2つ
   private ArrayList<CartVO> list = null;
   private MyCartDAO mcdao = null;
   private List<JCheckBox> checkBoxes = new ArrayList<JCheckBox>();
   static JCheckBox chkButton[] = null;
   private MainFrame win;
   private String cartCallBack = "N";// getCartImage() 재호출여부확인　// getCartImage() 再お呼び確認
   private JLabel button_DelLabel, button_BuyLabel, button_MainLabel = null;            //삭제,구매하기,메인가기 알려주는 라벨　//削除、購買する、メイン移動を教えるラベル   
   public MyCart(MainFrame win) {
      this.getMyCartImage(MainFrame.id);// 메인화면에서 장바구니 버튼 눌렀을때 실행　
					// メイン画面でカ-トボタンを押したとき実行
      this.win = win;
      setBounds(new Rectangle(50, 50, 900, 600));
      setLayout(null);
   }

   @Override
   public void actionPerformed(ActionEvent ae) {
      
      String ae_type = ae.getActionCommand();
      
      System.out.println("@@@@@@@@@@@@@@@@@ae_type" + ae);
      
      
      if (ae_type.equals(button_Del.getText())) {// 삭제하기 버튼이 클릭되었을 경우　// 削除するボタンがクリックされた場合
         System.out.println("삭제 하기 버튼 클릭 !!!");
         System.out.println("削除するボタンクリック!!!");	
 
         try {
            ArrayList<CartVO> list = null;
            ArrayList<CartVO> listDel = null;

            list = mcdao.getCartAndImage(MainFrame.id);// 로그인한 유저 cart 안에 있는 정보만을 가져옴　
						       // ログインしたユーザのcartの中にある情報だけをもってくる
            String code = null;
            String delComplete = "N";// 삭제가 잘 됐는지 체크!!!!!!  
				     // 削除がうまくなったのかチェック!!!!!!
            if (list != null) {
               for (int i = 0; i < list.size(); i++) {
                  JLabel c_id[] = new JLabel[list.size()];
                  c_id[i] = new JLabel();
                  c_id[i].setText(list.get(i).getId());
                  JLabel label_pCode[] = new JLabel[list.size()]; // 제품코드 
								  // 製品のコード
                  label_pCode[i] = new JLabel();
                  label_pCode[i].setText(list.get(i).getProductCode());// 제품명을 가져와서 글자를 화면에 뿌려줌　　
								       // 製品名を持ってきて文字を画面に現わせてくれる

                  System.out.println(checkBoxes.get(i).isSelected());
                  // 삭제 되는 부분　　
		  // 削除される部分
                  if (checkBoxes.get(i).isSelected()) {// 체크박스가 눌렸을 경우에만 삭제　　
						       // チェックボックスが押された場合に削除
                     System.out.println("$$$$$$$$$$$$" + (i + 1) + "번째 "+ checkBoxes.get(i).getText() + " 선택됨");
                     code = label_pCode[i].getText();
                     listDel = mcdao.getDelete(MainFrame.id, code);
                     delComplete = "Y";
                     System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$"+ label_pCode[i].getText());
                  }//if end 
		   //if文の終わり
               }// for문 끝  
		// for文の終わり
                  // 체크박스 선택 안하고 삭제버튼 눌렀을때　
		  // チェックボックスを選択しないで削除ボタンを押す時
               if (delComplete.equals("N")) {
                  JOptionPane.showMessageDialog(null, "商品を選択してください。","お知らせ", 0);//상품을 선택해 주세요.(매개변수 맨 끝에 '0'은 경고(X)표시, '1'은 i(정보)표시)                  
               }// 체크박스 선택 하고 삭제버튼 눌렀을 때
		// チェックボックスを選択して、削除ボタンを押す時
               else {
                  JOptionPane.showMessageDialog(null, "選択した商品を削除しました。","お知らせ", 1);// "선택한 상품을 삭제하였습니다.", "알림"
               }
               repaintScreen();// 화면다시그리기　
			       // 画面を再び描く
            }

         } catch (Exception e) {
            System.out.println("e=[" + e + "]");
            e.printStackTrace();
         }

      }
      //구매하기 버튼 클릭했을 때　　
      //購買するボタンをクリックしたとき
      else if (ae_type.equals(button_Buy.getText())) {
         System.out.println("구매버튼 클릭!!!");

         try {
            LoginPageDAO lpdao = new LoginPageDAO();// 옷장추가용 변수　
						    // たんす追加用の変数
            ClothBasicVO cbvo = null;// 옷장추가용 변수　
				     // たんす追加用の変数
            ArrayList<ClothBasicVO> buyList = new ArrayList();// 옷장추가용 변수　
							      // たんす追加用の変数
            String productCode = "";// 옷장추가용 변수　
				    // たんす追加用の変数

            ArrayList<CartVO> list = null;
            ArrayList<CartVO> listDel = null;

            list = mcdao.getCartAndImage(MainFrame.id);// 로그인한 유저 cart 안에 있는 정보만을 가져옴　　
						       // ログインしたユーザのcartの中にある情報だけをもらう
            String code = null;
            String addComplete = "N";//구매했는지 체크　//購入したかどうかチェック
            if (list != null) {
               
               int productPrice = 0; //선택한 상품의 총가격　　
				     //選択した商品の銃価格
               ArrayList<LoginPageVO> balancelist = lpdao.getBalance(MainFrame.id); //현재 잔고 조회　　
										    //現在の残高を照会
               System.out.println("!!!!!!!!!!현재 당신의 잔고 !!!!!!!!! : " + balancelist.get(0).getBalance());
               
               for (int j = 0; j < checkBoxes.size(); j++) {
                  if(checkBoxes.get(j).isSelected()){ //체크되어있는 상품만　　//チェックされている商品だけ
                     productCode = checkBoxes.get(j).getText(); //선택한 상품의 코드를 가져온다.　　
							        //選択した商品のコードをもってくる。
                     System.out.println("내가 선택한 상품들  " + productCode);
                     ArrayList<LoginPageVO> priceList = lpdao.getClothPrice(productCode); //선택한 상품의 가격을 가져온다.　　
											  //選択した商品の価格をもってくる。
                     
                     for (int k = 0; k < priceList.size(); k++) { // 선택한 물품들의 가격들을 합산한다.　　
								  // 選択した物品の価格を合算する。
                        productPrice += priceList.get(k).getPrice();
                        System.out.println("물품들의 가격  : " + productPrice);
                     }
                  }
               }
               System.out.println("@@@@@@@@물품들의 가격@@@@ : " + productPrice);
               
               if(productPrice > Integer.parseInt(balancelist.get(0).getBalance())){ //선택한 상품가격보다 잔고가 적으면 　　
										     //選択した商品の価格より残高が少なければ
                  JOptionPane.showMessageDialog(null, "잔고가 부족합니다. 현재잔고 : " + balancelist.get(0).getBalance() + "원", "お知らせ", 0);//잔고가 부족합니다.　　
																		//残高が足りないです。
                  return;
               }
               
               
               for (int i = 0; i < list.size(); i++) {
                  JLabel c_id[] = new JLabel[list.size()];
                  c_id[i] = new JLabel();//유저ID　　
					 //ユーザのID
                  c_id[i].setText(list.get(i).getId());
                  JLabel label_pCode[] = new JLabel[list.size()]; // 제품코드　　
								  // 製品のコード
                  label_pCode[i] = new JLabel();
                  label_pCode[i].setText(list.get(i).getProductCode());// 제품코드 지정　
								       // 製品のコードを指定
                  

                  if (checkBoxes.get(i).isSelected()) {
                     System.out.println("checkBoxes.is select " + i+ "번째 checkBox");
                     productCode = label_pCode[i].getText();
                     buyList = lpdao.getClothInfo(productCode);//제품정보조회　　
							       //製品情報の照会

                     for (int j = 0; j < buyList.size(); j++) {

                        int result  = lpdao.setClosetInfo(MainFrame.id, productCode);//제품구매　　
										     //製品の購買
                        result += lpdao.setBalanceMinus(MainFrame.id, productCode); //잔고 감소시켜줌.　　
										    //残高を減少させる。
                        
                        if (result == 0) { // DB에 입력이 실패　　
					   // DBに入力を失敗
                           JOptionPane.showMessageDialog(null, "製品の購入失敗.", "警告", 0);// "제품구매 실패.", "경고"
                           return;
                        }
                        
                        System.out.println("//////////////"+ productCode+ buyList.get(0).getProductName());
                        addComplete = "Y";
                        code = label_pCode[i].getText() + "";
                        listDel = mcdao.getDelete(MainFrame.id, code);
                        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$"+ label_pCode[i].getText());
                     }
                  }// 체크박스 선택됐나 if end　　　
		   // チェックボックスが選択されたのか　if文の終わり
               }// for문 끝　　
		// for文の終わり
               
               
               
               if (addComplete.equals("N")) {//상품을 선택하지 않고 구매하기 버튼 누름　　
					     // 商品を選択せず、購買ボタンを押す
                  JOptionPane.showMessageDialog(null, "商品を選択してください。","お知らせ", 0);//상품을 선택해 주세요.
               } else {
                  JOptionPane.showMessageDialog(null, "選択した商品を購入しました。","お知らせ", 1);// "선택한 상품을 구매하였습니다.", "알림"
               }
               repaintScreen();// 화면다시그리기　　
			       // 画面を再びを描く
            }

         } catch (Exception ex) {
            ex.printStackTrace();
         }

      }else if(ae_type.equals(button_Main.getText())) {  //메인으로 가기 클릭　
							 //メインに移動クリック
         
         System.out.println("@@@@@@@@@@@@@@@@@@@@@  : " + ae_type);
         System.out.println("button_Main.getText()  : " + button_Main.getText());
         try {
            win.panelChange("LoginPage", MainFrame.id);
         } catch (Exception ex) {
            System.out.println(ex);
         }
      }
   }

   // 메인화면에서 장바구니 버튼 눌렀을때 실행　　
   // メイン画面でカ-トボタンを押すときに実行
   public void getMyCartImage(String id) {

      // 서버에서 가져온 데이터로 글자 지정 부분　　
      // サーバーから持ってきたデータで文字の指定部分
      try {
         panelTop.setBounds(12, 10, 800, 56);
         add(panelTop);
         panelTop.setLayout(null);
         
         this.btnMaker(); //버튼 생성 메서드　　
			  //ボタン生成メソッド

         int sum = 0;// 합계 저장 변수　　
		     // 合計保存変数
         label_sumNum.setText(sum + "won");
         
         ArrayList<CartVO> list = null;
         mcdao = new MyCartDAO();// 서버에서 가져온 값을 쓰기위해 DAO 객체화　　
				 // サーバーから持ってきた戻り値を使えるためにDAOオブジェクト化
         list = mcdao.getCartAndImage(id);// 로그인한 유저 cart 안에 있는 정보만을 가져옴  
					  // ログインしたユーザのcartの中にある情報だけをもたらす
         ArrayList<CartVO> listDel = null;
         String code = null;
         // System.out.println(list.isEmpty());
         
         // list에 내용이 없을경우 대화상자 실행　　
	 // listに内容がない場合、ダイアログ実行
//         if (list.isEmpty()) {
//            JOptionPane.showMessageDialog(null, "カ－トが　空いて　います.", "お知らせ ", 1);// 장바구니가 비어있습니다. 알림
//         }
         // list에 내용이 있을 경우 실행　　
	 // listに内容がある場合に実行
         if (!list.isEmpty()) {

            // System.out.println(list.get(0));
            // System.out.println("size " + list.size());
            int jpanelAxis = 70;
            int yAxis = 10;
            if (cartCallBack.equals("Y")) {// getMyCartImage()가 재호출 될 경우　　
					   // getMyCartImage()が再お呼びするとき
               checkBoxes.clear();
            } else if (cartCallBack.equals("N")) {// getMyCartImage()가 재호출 되지 않았을경우　
						  // getMyCartImage()が再お呼びしないとき
               cartCallBack = "Y";
            }
            for (int i = 0; i < list.size(); i++) {

               System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@jpanelAxis :"+ jpanelAxis);

               JPanel jpanel[] = new JPanel[list.size()];
               jpanel[i] = new JPanel();
               jpanel[i].setBackground(Color.WHITE);

               jpanel[i].setBounds(10, jpanelAxis, 789, 153);
               add(jpanel[i]);
               jpanel[i].setLayout(null);
               jpanel[i].addMouseListener(this);

               JLabel label_num[] = new JLabel[list.size()];

               label_num[i] = new JLabel(Integer.toString(i + 1)); // 카트 순번　　
								   // カートの順番
               label_num[i].setBounds(12, 10, 15, 15);
               jpanel[i].add(label_num[i]);
               jpanel[i].add(label_num[i]);

               JLabel label_pName[] = new JLabel[list.size()]; // 제품명
							       // 製品名
               label_pName[i] = new JLabel();
               label_pName[i].setBounds(193, 10, 70, 15);
               jpanel[i].add(label_pName[i]);

               JLabel label_pCode[] = new JLabel[list.size()]; // 제품코드　　
							       // 製品コード
               label_pCode[i] = new JLabel();
               label_pCode[i].setBounds(266, 10, 61, 15);
               jpanel[i].add(label_pCode[i]);
               label_pCode[i].setVisible(false);// 제품코드 안보이게 하기　　製品コード見えないようにする

               JLabel label_price[] = new JLabel[list.size()];// 가격표　
							      // 値札
               label_price[i] = new JLabel(); 
               label_price[i].setBounds(378, 10, 56, 15);
               jpanel[i].add(label_price[i]);

               chkButton = new JCheckBox[list.size()]; // 체크박스　　
						       //チェックボックス
               chkButton[i] = (JCheckBox) new JCheckBox(list.get(i).getProductCode()); // 제품모델코드　　
										       // 製品モデルコード
               chkButton[i].setBounds(533, 10, 100, 23);
               chkButton[i].setBackground(Color.WHITE);
               chkButton[i].setSelected(false);// 체크되어있는 체크박스 focus 끄기　　
					       // チェックされているチェックボックスのfocusを消す
               chkButton[i].setOpaque(false);
               this.checkBoxes.add(chkButton[i]);
               jpanel[i].add(chkButton[i]);

               JPanel panel_pic[] = new JPanel[list.size()]; // 그림 패널　絵のパネル
               panel_pic[i] = new JPanel();
               panel_pic[i].setBounds(39, 12, 142, 130);
               jpanel[i].add(panel_pic[i]);
               jpanel[i].add(panel_pic[i]);

               label_pName[i].setText(list.get(i).getProductName());// 제품명을 가져와서 글자를 화면에 뿌려줌　　
								    // 製品名を持ってきて文字を画面にかける
               label_price[i].setText(list.get(i).getPrice() + "won");// 가격을　가져와서 글자를 화면에 뿌려줌　　
								      // 価格を　持ってきて文字を画面にかける                      
               label_pCode[i].setText(list.get(i).getProductCode());// 제품코드를 가져와서 글자를 화면에 뿌려줌　　
								    // 製品コードを持ってきて文字を画面にかける
               sum += Integer.parseInt(list.get(i).getPrice());// 값을 모두　더해서 sum에 저장　　
							       // 値をすべて加えてsumに保存

               ImageIcon imgProd[] = new ImageIcon[list.size()];
//               imgProd[i] = new ImageIcon(list.get(i).getImagePath());// 이미지 경로 받아옴　　
									// イメージの経路を受ける
               imgProd[i] = new ImageIcon(getClass().getClassLoader().getResource(list.get(i).getImagePath()));// 이미지 경로 받아옴　　
													       // イメージの経路を受ける

               System.out.println("!!!!!!!!!!!!!!" + list.get(i));

               JLabel label_pic[] = new JLabel[list.size()];//그림 라벨　　
							    //絵のラベル
               label_pic[i] = new JLabel();

               label_pic[i] = new JLabel(imgProd[i]);
               label_pic[i].setBounds(50, 0, imgProd[i].getIconWidth(),imgProd[i].getIconHeight());//그림 크기 지정　　
												   //絵の大きさを指定
               panel_pic[i].add(label_pic[i]);

               jpanelAxis += 165;

            }// for end　　
	     // for文のあわり
            System.out.println("버튼 누르기 전@@@@@@   체크어레이 개수"   + checkBoxes.size());
            // System.out.println("버튼 누르기 전@@@@@@   생성된 체크박스 개수"+chkButton.length);
            label_sumNum.setText(sum + "won");// 총액 화면에 출력　　
					      // 総額の画面に出力

         } // end if　　
	   // if文のあわり

      } catch (Exception e) {
         System.out.println("e=[" + e + "]");
         e.printStackTrace();
      }

   }

   // 화면 다시그리기　　
   // 画面を再び描く
   public void repaintScreen() {

      repaint();
      revalidate();
      removeAll();
      try {
         System.out.println("다시그리기!!!");
         ArrayList<CartVO> list = null;
         mcdao = new MyCartDAO();// 서버에서 가져온 값을 쓰기위해 DAO 객체화　　
				 // サーバーから持ってきた戻り値を使うためにDAOオブジェクト化
         list = mcdao.getCartAndImage(MainFrame.id);// 로그인한 유저 cart 안에 있는 정보만을 가져옴　　
						    // ログインしたユーザのcartの中にある情報だけをもたらす
         ArrayList<CartVO> listDel = null;
         String code = null;

         panelTop.setBounds(12, 10, 789, 56);
         add(panelTop);
         panelTop.setLayout(null);

         this.btnMaker(); //버튼 생성 메서드　　
			  //ボタン生成メソッド
         
         int sum = 0;// 합계 저장 변수　　
		     // 合計保存変数
         label_sumNum.setText(sum + "won");

         

         // list에 내용이 없을경우 대화상자 실행　　
	 // listに内容がない場合、ダイアログ実行
         if (list.isEmpty()) {
            JOptionPane.showMessageDialog(null, "カ－トが　空いて　います", "お知らせ ", 1);// 장바구니가 비어있습니다. 알림
         }
         // list에 내용이 있을 경우 실행　　
	 // listに内容がある場合、ダイアログ実行
         else {
            System.out
                  .println("===========================================================================================");
            // System.out.println(list.get(0));
            // System.out.println("size " + list.size());

            int jpanelAxis = 70;
            int yAxis = 10;
            checkBoxes.clear();//체크박스 어레이리스트에 들어있는 정보 삭제　　
			       //チェックボックスのArraylistに入っている情報を削除
            for (int i = 0; i < list.size(); i++) {
               JPanel jpanel[] = new JPanel[list.size()];
               jpanel[i] = new JPanel();
               jpanel[i].setBackground(Color.WHITE);

               jpanel[i].setBounds(10, jpanelAxis, 789, 153);
               add(jpanel[i]);
               jpanel[i].setLayout(null);

               JLabel label_num[] = new JLabel[list.size()];

               label_num[i] = new JLabel(Integer.toString(i + 1)); // 카트 순번　　
								   // カートの順番
               label_num[i].setBounds(12, 10, 15, 15);
               jpanel[i].add(label_num[i]);

               JLabel label_pName[] = new JLabel[list.size()]; // 제품명　　
							       // 製品名
               label_pName[i] = new JLabel();
               label_pName[i].setBounds(193, 10, 70, 15);
               jpanel[i].add(label_pName[i]);

               JLabel label_pCode[] = new JLabel[list.size()]; // 제품코드　　
							       // 製品コード
               label_pCode[i] = new JLabel();
               label_pCode[i].setBounds(266, 10, 61, 15);
               jpanel[i].add(label_pCode[i]);
               label_pCode[i].setVisible(false);// 코드 안보이게 하기　　
						//コードを見えなくする

               JLabel label_price[] = new JLabel[list.size()];

               label_price[i] = new JLabel(); // 가격표　　
					      // 値札
               label_price[i].setBounds(378, 10, 56, 15);
               jpanel[i].add(label_price[i]);

               chkButton = new JCheckBox[list.size()]; // 체크박스　　
						       // チェックボックス
               chkButton[i] = (JCheckBox) new JCheckBox(list.get(i).getProductCode()); // 제품모델명　
										       // 製品のモデル名
               chkButton[i].setBounds(533, 10, 100, 23);
               chkButton[i].setBackground(Color.WHITE);
               chkButton[i].setSelected(false);// 체크되어있는 체크박스 focus 끄기　　
					       // チェックされているチェックボックスのfocusを消す
               this.checkBoxes.add(chkButton[i]);
               jpanel[i].add(chkButton[i]);

               JPanel panel_pic[] = new JPanel[list.size()]; // 그림 패널　　
							     // 絵のパネル
               panel_pic[i] = new JPanel();
               panel_pic[i].setBounds(39, 12, 142, 130);
               jpanel[i].add(panel_pic[i]);

               label_pName[i].setText(list.get(i).getProductName());
               label_price[i].setText(list.get(i).getPrice() + "won");// 가격을 가져와서 글자를 화면에 뿌려줌　　
								      // 価格を持ってきて文字を画面にかける
               label_pCode[i].setText(list.get(i).getProductCode());// 제품명을 가져와서 글자를 화면에 뿌려줌　　
								    //製品名を持ってきて文字を画面にかける
               sum += Integer.parseInt(list.get(i).getPrice());// 값을 모두　더해서 sum에 저장　　
							       // 値をすべて　加えてsumに保存

               ImageIcon imgProd[] = new ImageIcon[list.size()];
               imgProd[i] = new ImageIcon(getClass().getClassLoader().getResource(list.get(i).getImagePath()));// 이미지 경로 받아옴　　
													       // イメージの経路を受ける

               JLabel label_pic[] = new JLabel[list.size()];
               label_pic[i] = new JLabel();

               label_pic[i] = new JLabel(imgProd[i]);
               label_pic[i].setBounds(50, 0, imgProd[i].getIconWidth(),imgProd[i].getIconHeight());//이미지 크기 지정　　
												   //イメージの大きさを指定
               panel_pic[i].add(label_pic[i]);

               jpanelAxis += 165;

            }// for end
	     // for文のあわり	
            System.out.println("버튼 누른후 @@@@@@   체크어레이 개수"+ checkBoxes.size());
            // System.out.println("버튼 누른후@@@@@@   생성된 체크박스 개수"+chkButton.length);
            label_sumNum.setText(sum + "won"); // 합계가 0이 아닐때의 총액 화면에 출력　　//　合計が0でないとき、総額画面に出力
         } // end if// if文のあわり
      } catch (Exception e) {
         System.out.println("e=[" + e + "]");
         e.printStackTrace();
      }
   }
   
   //버튼 생성 메서드　　
   //ボタン生成メソッド
   public void btnMaker(){
      
      //구매 확정버튼 
      //購買確定ボタン
      button_Buy = new JButton("button_Buy", new ImageIcon(getClass().getClassLoader().getResource("cart4.png")));// "구매확정"　　
														  // "購買確定"
      button_Buy.setBorder(null);
      button_Buy.setBounds(480, 5, 64, 40);
      button_BuyLabel = new JLabel("購買確定 ");   //라벨　　
						   //ラベル
      button_Buy.setBorderPainted(false);
      button_Buy.setContentAreaFilled(false);
      button_Buy.setFocusPainted(false);
      button_Buy.addActionListener(this);
      button_BuyLabel.setBounds(485,43,104,15);
      panelTop.add(button_BuyLabel);
      panelTop.add(button_Buy);

      //삭제 버튼　　
      //削除のボタン
      button_Del = new JButton("button_Del", new ImageIcon(getClass().getClassLoader().getResource("cart3.png")));// "삭제"　　
														  // "削除"
      button_Del.setBorder(null); 
      button_Del.setBounds(555, 5,  64, 40);
      button_DelLabel = new JLabel("削除");   //라벨　　
					      //ラベル
      button_Del.setBorderPainted(false);
      button_Del.setContentAreaFilled(false);
      button_Del.setFocusPainted(false);
      button_Del.addActionListener(this);
      button_DelLabel.setBounds(570,43,104,15);
      panelTop.add(button_DelLabel);
      panelTop.add(button_Del);
      
      // 메인으로 가기 버튼　　
      // メインに移動ボタン
      button_Main = new JButton("button_Main", new ImageIcon(getClass().getClassLoader().getResource("main.png")));
      button_Main.setBorder(null);
      button_Main.setBounds(630, 5, 64, 40);
      button_MainLabel = new JLabel("メイン画面");   //라벨　　
						     //ラベル
      button_Main.setBorderPainted(false);
      button_Main.setContentAreaFilled(false);
      button_Main.setFocusPainted(false);
      button_Main.addActionListener(this);
      button_MainLabel.setBounds(620,43,104,15);
      panelTop.add(button_MainLabel);
      panelTop.add(button_Main);

      label_picimage = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("money.png")));   //합계이미지　　
													  //合計のイメージ
      label_picimage.setBorder(null);
      label_picimage.setBounds(700,5,60,40);
      panelTop.add(label_picimage);
      label_sumNum.setBounds(700, 43, 100, 15);
      panelTop.add(label_sumNum);
      
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
		Color b=new Color(127,255,212);  
		e.getComponent().setBackground(b);
		
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		e.getComponent().setBackground(Color.white);
		
	}
   
}
