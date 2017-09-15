package deliveryCompany;

import java.awt.BorderLayout;
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
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import store.StoreDAO;
import store.StoreVO;

public class StoreFindPane extends JPanel implements ActionListener,
		MouseListener, KeyListener {

	private JPanel jp[] = new JPanel[7];
	private JLabel jl[] = new JLabel[5];
	private JTextField tf[] = new JTextField[5];
	private JPasswordField pf;
	private JButton bts[] = new JButton[4];
	private StoreListPane storeListPane;
	private JTable storeListModel;
	String[] caption = { "店番号 :", "店名　 :", "住		　所:", "電話番号 :", "暗　　号  :" };
	String[] btnText = { "店照會", "登録", "修正", "削除" };

	public StoreFindPane() {
		JPanel deco = new JPanel();
		setLayout(new BorderLayout());
		JPanel base = new JPanel();
		storeListPane = new StoreListPane();
		base.setLayout(new GridLayout(8, 1));
		EtchedBorder eb = new EtchedBorder();
		setBorder(eb);
		int size = caption.length;
		base.add(deco);

		base.setSize(new Dimension(500, 400));
		for (int i = 0; i < size; i++) {
			jl[i] = new JLabel(caption[i]);
			tf[i] = new JTextField(15);
			tf[i].addActionListener(this);
			jp[i] = new JPanel();
			jp[i].add(jl[i]);
			jp[i].add(tf[i]);
			base.add(jp[i]);

		}

		tf[0].setEditable(false);

		jp[5] = new JPanel();
		jp[6] = new JPanel();
		for (int i = 0; i < btnText.length; i++) {
			bts[i] = new JButton(btnText[i]);
			bts[i].addActionListener(this);
			if (i < 2)
				jp[5].add(bts[i]);
			else
				jp[6].add(bts[i]);

		}
		base.add(jp[5]);
		base.add(jp[6]);

		add(storeListPane, BorderLayout.WEST);
		add(base, BorderLayout.CENTER);

		tf[1].addActionListener(this);
		tf[1].addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent fe) {
				for (int i = 0; i < tf.length; i++)
					tf[i].setText("");
			}
		});

		base.add(jp[size]);
		storeListModel = storeListPane.getModelTable();
		storeListModel.addMouseListener(this);
		tf[3].addKeyListener(this);

	}

	// 매장명의 텍스트필드에 클릭을 하면 모든 텍스트필드 초기화　//　店名のテキストフィールドをクリックするとテキストフィールド初期化
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1) {
			int row = storeListModel.getSelectedRow();
			tf[0].setText(String.valueOf((new StoreListModel()).getValueAt(row,
					0)));
			tf[1].setText((String) (new StoreListModel()).getValueAt(row, 1));
			tf[2].setText((String) (new StoreListModel()).getValueAt(row, 2));
			tf[3].setText((String) (new StoreListModel()).getValueAt(row, 3));
			tf[4].setText((String) (new StoreListModel()).getValueAt(row, 4));
		}
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void actionPerformed(ActionEvent ae) {

		String ae_type = ae.getActionCommand();
		StoreVO stvo = null;
		CompanyDAO comDAO = null;
		StoreDAO sDAO = null;

		if (ae.getSource().equals(tf[1].getText())) {
			if (!tf[1].getText().equals("")) {
				for (int i = 0; i < tf.length; i++)
					tf[i].setText("");
			}
		}

		// 매장 조회의 버튼을 눌렀을 때 발생하는 이벤트//　店の照會ボタンを押す時イベント
		try {
			sDAO = new StoreDAO();
			comDAO = new CompanyDAO();
		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("StoreFindPane1");
		}
		// 매장명의 내용으로 자료 불러오기//店名で資料を呼び出す
		if (ae_type.equals(bts[0].getText())) {
			try {
				String sname = tf[1].getText().trim();
				if (!sname.equals(""))
					stvo = sDAO.getStoreName(sname);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("StoreFindPane2");
			}
			// 찾아서 데이터가 나왔다면 텍스트필드에 채워넣기//dataをテキストフィールドに入れる
			if (stvo != null) {
				tf[0].setText(stvo.getNo() + "");
				tf[1].setText(stvo.getName());
				tf[2].setText(stvo.getAddress());
				tf[3].setText(stvo.getTel());
				tf[4].setText(stvo.getPass());
			}
			// 찾지 못했다면 메세지 띄움//検索の失敗時
			else {

				Object[] options = { "確認" };
				JOptionPane.showOptionDialog(null, "検索失敗", "メッセ-ジ",
						JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, "確認");
			}

			// 등록하기 버튼을 눌렀을 때 발생하는 이벤트//　登録ボタンを押す時イベント
		} else if (ae_type.equals(bts[1].getText())) {
			try {
				// 텍스트 필드가 모두 채워져있을 때 등록//　テキストフィールドが空いていないときだけ登録
				stvo = sDAO.getStoreName(tf[1].getText());
				if (stvo == null) {
					if (tf[0].getText().trim().equals("")
							&& !(tf[1].getText().trim().equals("")
									|| tf[2].getText().trim().equals("")
									|| tf[3].getText().trim().equals("") || tf[4]
									.getText().trim().equals(""))) {
						stvo = new StoreVO(0, tf[1].getText(), tf[2].getText(),
								tf[3].getText(), tf[4].getText());
						sDAO.getStoreRegiste(stvo);
					} else { // 텍스트가 하나라도 비어있다면 메세지//　テキストフィールドが空いている時

						Object[] options = { "確認" };
						JOptionPane.showOptionDialog(null, "内容を入力してください。",
								"メッセ-ジ", JOptionPane.CANCEL_OPTION,
								JOptionPane.WARNING_MESSAGE, null, options,
								"確認");
					}
				} else {

					Object[] options = { "確認" };
					JOptionPane.showOptionDialog(null, "同じ店が登録されています。",
							"メッセ-ジ", JOptionPane.CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options, "確認");
				}
			} catch (Exception e) {
				System.out.println("StoreFindPane3");
				e.printStackTrace();
			}
			int size = caption.length;
			for (int i = 0; i < size; i++) {
				tf[i].setText("");
			}
		} else if (ae_type.equals(tf[1].getText())) { // 매장명 텍스트에서 엔터를 눌렀을 때 검색이
														// 되는//　店名テキストフィールドでエンターを押す時検索されること
			try {
				String sname = tf[1].getText().trim();
				if (!sname.equals("")) {
					stvo = sDAO.getStoreName(sname);
				}
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("StoreFindPane4");

			}
			if (stvo != null) {
				tf[0].setText(stvo.getNo() + "");
				tf[1].setText(stvo.getName());
				tf[2].setText(stvo.getAddress());
				tf[3].setText(stvo.getTel());
				tf[4].setText(stvo.getPass());
			} else {

				Object[] options = { "確認" };
				JOptionPane.showOptionDialog(null, "検索失敗", "メッセ-ジ",
						JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, "確認");
			}

		} else if (ae_type.equals(bts[2].getText())) {// 수정하기 버튼이 클릭되었을 때,
														// //修正ボタンを押すと

			try {

				stvo = new StoreVO(Integer.parseInt(tf[0].getText()),
						tf[1].getText(), tf[2].getText(), tf[3].getText(),
						tf[4].getText());

				comDAO.storeUpdate(stvo);

				if (comDAO != null) {

					Object[] options = { "確認" };
					JOptionPane.showOptionDialog(null, "さんが成功的に修正されました。",
							"メッセ-ジ", JOptionPane.CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options, "確認");
				}
				int size = caption.length;
				for (int i = 0; i < size; i++) {
					tf[i].setText("");
				}

			} catch (Exception e) {

				Object[] options = { "確認" };
				JOptionPane.showOptionDialog(null, "情報がないです。", "メッセ-ジ",
						JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, "確認");

			}

		} else if (ae_type.equals(bts[3].getText())) {

			try {
				int no = Integer.parseInt(tf[0].getText());
				Object[] options1 = { "はい", "いいえ" };
				int result = JOptionPane.showOptionDialog(null, "削除していただけますか。",
						"削除", JOptionPane.YES_NO_OPTION,
						JOptionPane.WARNING_MESSAGE, null, options1, null);
				if (result == JOptionPane.YES_OPTION) {
					sDAO.storeDelete(no);
					Object[] options = { "確認" };
					JOptionPane.showOptionDialog(null, "店番号" + no
							+ "が　 削除されました。", "メッセ-ジ",
							JOptionPane.CANCEL_OPTION,
							JOptionPane.WARNING_MESSAGE, null, options, "確認");
				}
			} catch (Exception e) {
				Object[] options = { "確認" };
				JOptionPane.showOptionDialog(null, "情報を入力してください。", "メッセ-ジ",
						JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,
						null, options, "確認");

			}
			int size = caption.length;
			for (int i = 0; i < size; i++) {
				tf[i].setText("");
			}
		}
		if (!ae_type.equals(bts[0].getText())) {
			storeListPane.storeListset();
		}
	}

	public void keyPressed(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	// 숫자만 입력받을 수 있도록하는 이벤트
	public void keyTyped(KeyEvent e) {
		char type = e.getKeyChar();

		if (!Character.isDigit(type)) {
			e.consume();
			return;
		}
	}

}