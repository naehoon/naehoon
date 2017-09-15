/* 
 * =============================
 * 프로그램 설명 : 게시판내용테이블, 입력, 삭제, 수정, 상세보기, 메인으로돌아가기 기능 구현  
 * プログラムの説明 : 掲示板の内容のテーブル、入力、削除、修正、詳細表示、メインに移動する機能の具現
 * 작성자 : 오내훈, 박신영　　
 * 作成者 : オ・ネフン、パク・シンヨン
 * 최초 작성일자 : 2017-07-19
 * 最初の作成日付 :2017-07-19
 * 최종 수정일 : 
 * 最終の修正日 :
 * 수정 내용 : 
 * 修正の内容 :
 * =============================
 * */

package com.bs.board.control;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import com.bs.util.TableRender;
import com.bs.view.MainFrame;
import com.bs.vo.NoticeBoardVO;

public class Board extends JPanel implements ActionListener, MouseListener, MouseMotionListener, TableCellRenderer{

	private JLabel jl ;
	private JLabel name;
	private JButton jbtnMain = new JButton("メインに移動");//"메인으로"
	private JButton jbtnWrite = new JButton("作成する");//"작성하기"
	private JButton jbtnUdt = new JButton("修正する");//"수정하기"
	private JButton jbtnDel = new JButton("削除する");//"삭제하기"
	private JTable table;
	private int i;
	private BoardModel bb;
	private BoardModel bm = new BoardModel();
	private BoardDAO bdao = null;
	private ArrayList<NoticeBoardVO> list = null;
	private ImageIcon img, img2;
	public static int selectedNum = 0;
	public  String selectedWriter = null;
	
	private MainFrame win;
	private TableRender cellRenderer = new TableRender();
	private JLabel lb= null;

	public Board(MainFrame win) {
		setBackground(Color.WHITE);
		this.win = win; 

		//메인으로 가기 버튼 액션리스너　　
		//メインに移動ボタンのアクションリスナー
		jbtnMain.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == jbtnMain) {
						win.panelChange("LoginPage", MainFrame.id);
						selectedNum = 0;
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
		
		//작성하기 버튼 액션리스너　　
		//作成するボタンのアクションのリスナー
		jbtnWrite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == jbtnWrite) {
						win.panelChange("BoardInsert", MainFrame.id);
					}
				} catch (Exception ex) {
//					System.out.println(ex);
					ex.printStackTrace();
					
				}
			}
		});
		
		//삭제하기 버튼 액션리스너　　
		//削除するボタンのアクションのリスナー
		jbtnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (e.getSource() == jbtnDel) {
						System.out.println("삭제버튼 클릭됬다.");
						try{
							
							if(selectedNum == 0){								
								JOptionPane.showMessageDialog(null, "削除する掲示物を選択してください", "警告", 0);//"삭제하실 게시물을 선택해주세요.", "경고"
								return;
							}
							 																					//"선택한 게시글을 삭제하시겠습니까?", "선택"
							int dialogResult = JOptionPane.showConfirmDialog(null, "選択した掲示物を削除しますか", "選択", 0); // 컨펌　　
																			   // コンファーム
							
							System.out.println("selectedWriter : " + selectedWriter+ "@######### : " +MainFrame.id );
								
							if (dialogResult == JOptionPane.YES_OPTION) { //예 눌렀을때　　
												      //はいを押した時
//								System.out.println("@@@@@@@@@@@@@@@@@@" + selectedNum);
								if(!selectedWriter.equals(MainFrame.id)){ //작성자가 아닐경우 　　
													  //作成者がない場合
									JOptionPane.showMessageDialog(null, "作成者ではないです。", "警告", 0);
									return;
								}else{ //작성자가 맞으면　　
								       //作成者が合うと
									BoardDAO bdao = new BoardDAO();
									int i = bdao.delBoardContent(selectedNum,selectedWriter);  //게시글 번호로 조회해서 삭제 　　
																   //掲示文の番号で照会して削除
									
									if(i<=0){ //삭제 실패	
								                  //削除の失敗								
										JOptionPane.showMessageDialog(null, "削除失敗しました。", "警告", 0);//삭제를 실패했습니다", "경고"
										return;
										
									}else{														
										JOptionPane.showMessageDialog(null, "削除成功", "お知らせ", 0);//삭제성공", "알림"
										win.panelChange("Board", MainFrame.id);
										selectedNum = 0; //선택한 번호 초기화　　
												 //選択した番号を初期化
									}
								}
								
							}else{ //아니요 눌렀을때 　　
									//いいえを押した時
								return;
							}
							
						}catch(Exception ex){
							ex.printStackTrace();
						}
						
//						win.panelChange("Board", MainFrame.id);
					}
					
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
		
		//
		//수정 버튼 액션리스너　　
		//修正ボタンのアクションのリスナー
		jbtnUdt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					if (e.getSource() == jbtnUdt) {
						
						if(selectedNum == 0){								
							JOptionPane.showMessageDialog(null, "修正される掲示物を選択してください.", "警告", 0);//"수정하실 게시물을 선택해주세요", "경고"
							return;
							
						}else if(!selectedWriter.equals(MainFrame.id)){ //작성자가 아닐경우 　　
														//作成者がない場合
							JOptionPane.showMessageDialog(null, "作成者ではないです。", "警告", 0); //"작성자가 아닙니다.", "경고"
							return;

						}else{
							win.panelChange("BoardUpdate", MainFrame.id);
						}
					}
				} catch (Exception ex) {
					System.out.println(ex);
				}
			}
		});
	}
	
	//게시판을 새로고침한다.　　
	//掲示板を更新する。
	public void getBoardRefresh(String id){
		
		setBounds(new Rectangle(0, 0, 900, 600));

	
//				img2 = new ImageIcon(".\\src\\img\\gg.png");
//		img = new ImageIcon(".\\src\\img\\g.png");
		
		
		setLayout(null);
		
		
		lb = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("gt.png")));
		lb.setBounds(10,10,100,100);
		add(lb);
		
		jl = new JLabel("掲示板");//"게시판"
		jl.setBounds(120, 8, 400, 110);
		jl.setFont(new Font("MS PGothic", Font.PLAIN, 69));
		add(jl);
		

		jbtnMain.setBounds(187, 520, 120, 23);
		jbtnWrite.setBounds(319, 520, 120, 23);
		jbtnUdt.setBounds(451, 520, 120, 23);
		jbtnDel.setBounds(583, 520, 120, 23);

		add(jbtnMain);
		add(jbtnWrite);
		add(jbtnUdt);
		add(jbtnDel);
		
		//테이블  
		//テーブル
		table = new JTable(new BoardModel()); //게시판 재조회  
						      //掲示板の再照会
		table.getTableHeader().setResizingAllowed(false);
		table.getTableHeader().setReorderingAllowed(false);
		
		table.getColumnModel().getColumn(1).setPreferredWidth(402);
		table.getColumnModel().getColumn(2).setPreferredWidth(98);
		table.getColumnModel().getColumn(3).setPreferredWidth(118);
		
		table.setBounds(25, 110, 850, 400);
		table.setColumnSelectionAllowed(true);
		table.addMouseListener(this);
		table.addMouseMotionListener(this);
		
		for (int i = 0; i <= 3; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
		}
		
//		
//		table.add(new MouseMotionAdapter(){
//			   public void mouseMoved(MouseEvent e)
//			   {
//			      int row = table.rowAtPoint(e.getPoint());
//			      if (row > -1)
//			      {
//			         // easiest way:
//			         table.clearSelection();
//			         table.setRowSelectionInterval(row, row);
//			      }
//			      else
//			      {
//			         table.setSelectionBackground(Color.blue);
//			      }
//			   }
//			});
//		
//		
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setLocation(25, 110);
		scrollPane.setSize(850, 400);
		add(scrollPane);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
		//System.out.println("##############3" + arg0);
		int row = table.getSelectedRow();
		int column = 0;
		selectedNum = (int)table.getValueAt(row, column);
		selectedWriter = (String) table.getValueAt(row, 2);
//		System.out.println("@@@@@@@@@@@@@@@@" + table.getValueAt(row, column2) +"선택했습니다.");
		
		if(table.getSelectedColumn()==1){ //제목을 클릭했을때　　
						  //タイトルをクリックしたとき
			
//			System.out.println("@################" + table.getName());
//			System.out.println("table.getSelectedColumn()" + table.getSelectedColumn());
			try {
				win.panelChange("BoardDetail", MainFrame.id); //게시글 상세보기 페이지　　
									      //書き込みの詳細表示ページ
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void mouseMoved(MouseEvent evt) {
		
		int row = table.rowAtPoint(evt.getPoint()); //테이블 셀 색변경  
							    //テーブルセルの色を変更
		if (row > -1)
		 {
		    
			Color b=new Color(127,255,212);  
			cellRenderer.rowAtMouse = row;
//		    cellRenderer.color = Color.GREEN;
			cellRenderer.color = b;
		    table.repaint();
		 }
		
//		System.out.println("@#@@@@@@@@@@@@@@@@@@" + table.rowAtPoint(evt.getPoint()));
		
		if(table.columnAtPoint(evt.getPoint())==1){
			setCursor(new Cursor(Cursor.HAND_CURSOR));
		}else{
			setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}
		
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		return null;
	}

}
