 package store;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class StoreFind extends JFrame implements ActionListener { // 매장의 번호와 비밀번호를 검색　//　店番号どパスワード検索
	private JPanel jp[] = new JPanel[5];
	private JLabel jl[] = new JLabel[4];
	private JTextField tf[] = new JTextField[4];
	private JButton okb;

	String[] caption = { "店番号         :", "パスワード:", "店名           :", "電話番号    :" };

	public StoreFind() {
		
		setLayout(new GridLayout(7, 1));			
		setTitle("Find");
		setSize(500, 500);		
		setVisible(true);
		
		
		tf[0] = new JTextField(15);
		tf[1] = new JTextField(15);
		tf[2] = new JTextField(15);
		tf[3] = new JTextField(15);
		int size = caption.length;
		for (int i = 0; i < size; i++) {
			jl[i] = new JLabel(caption[i]);

			jp[i] = new JPanel();
			jp[i].add(jl[i]);
			jp[i].add(tf[i]);
			add(jp[i]);
		
		}
		tf[2].setEditable(false);	

		jp[size] = new JPanel();
		okb = new JButton("照会");
		okb.addActionListener(this);
		jp[size].add(okb);
		add(jp[size]);

	}

	@Override
	public void actionPerformed(ActionEvent e) { 
		String e_type = e.getActionCommand();
		StoreVO svo = null;
		StoreDAO sdao = null;
		if (e_type.equals(okb.getText())) // okb버튼을 누르면　//　okbボタンを押すと
			;
		try {
			
			sdao = new StoreDAO();
			String pass = tf[1].getText().trim();
			String tel = tf[3].getText().trim();
			String no = tf[0].getText().trim(); // 각 변수에 텍스트필드로 받아온 값 저장　//　変数にテキストフィールドでもらったdataを貯藏

			if (!pass.equals("") && !tel.equals("")) { // 비밀번호와 전화번호가 공백이 아니면　//　パスワードど電話番後が空いていない場合
				svo = sdao.getStoreCheck1(pass, tel); // 비밀번호랑 전화번호로 매장번호찾기　//　パスワードど電話番後で店番号検索

			} else if (!no.equals("") && !tel.equals("")) { // 매장번호와 전화번호가 공백이아니면　//　店番号電話番後空いていない場合

				int sno = Integer.parseInt(no);

				svo = sdao.getStoreCheck2(no, tel); // 매장번호와 전화번호로 비밀번호 찾기　//　店番号と電話番後でパスワード検索

				

			}
		} catch (Exception e1) {
			System.out.println("e=[" + e1 + "]");

		}
		if (svo != null) {
			tf[0].setText(svo.getNo() + "");
			tf[1].setText(svo.getPass()+"");
			tf[2].setText(svo.getName()+"");
			tf[3].setText(svo.getTel() + ""); // 텍스트필드로 받은값이 있으면 각 매장번호, 비밀번호, 매장명, 전화번호 조회　//　テキストフィールドでdataをもらう時店番号、パスワードど、店名、電話番後を検索
			

		} else {
			
			Object[] options = {"確認"};
			JOptionPane.showOptionDialog(null, "検索失敗", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");

		}

	}

}