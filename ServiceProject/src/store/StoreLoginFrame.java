package store;

import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class StoreLoginFrame extends JFrame implements ActionListener { // 로그인 창　//ログイン
	private JButton okb;
	private JButton adb;
	private JButton fdb;
	private JTextField tf1;
	private JTextField tf2;
	private JFrame frame;

	public StoreLoginFrame() {

		frame = new JFrame("Login");
		frame.setSize(310, 200);
		frame.setLayout(new FlowLayout());

		JLabel jl1 = new JLabel("店番号        :", Label.RIGHT);
		JLabel jl2 = new JLabel("パスワード:", Label.RIGHT);

		tf1 = new JTextField(15);
		tf2 = new JTextField();
		tf2 = new JPasswordField(15);

		frame.add(jl1);
		frame.add(tf1);
		frame.add(jl2);
		frame.add(tf2);

		okb = new JButton("ログイン");
		adb = new JButton("店登録");
		fdb = new JButton("検索");
		okb.setSize(90, 45);
		adb.setSize(90, 45);
		fdb.setSize(90, 45);

		frame.add(okb);
		okb.addActionListener(this);
		frame.add(adb);
		adb.addActionListener(this);
		frame.add(fdb);
		fdb.addActionListener(this);

		frame.setVisible(true);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String e_type = e.getActionCommand();
		StoreVO svo = null;
		StoreDAO sdao = null;

		if (e_type.equals(okb.getText())) { // okb버튼을 누르면　//　okbボタンを押すと

			try {

				sdao = new StoreDAO();
				int num = Integer.parseInt(tf1.getText()); // num에 입력받은 매장번호를 저장　//　numに入力した店番号を貯藏
				String pwd = tf2.getText(); // pwd 에 입력받은 비밀번호를 저장　//　pwdに入力したパスワードを貯藏
				svo = sdao.getStoreCheck(num, pwd); // 매장번호와 비밀번호를 체크　//　店番号とパスワードをチェック
				new StorePane(svo.getNo());
				frame.dispose(); 

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "ログイン失敗", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				
				

			}

		} else if (e_type.equals(adb.getText())) { // adb버튼을 누르면　//　adbボタンを押すと
			StoreAddPane storeAddPane = new StoreAddPane(); // 등록창 생성　//登錄

		} else if (e_type.equals(fdb.getText())) { // fdb버튼을 누르면　//　fdbボタンを押すと
			StoreFind storeFind = new StoreFind(); // 찾기창 생성　//　検索
		}
	}

	public static void main(String[] args) {
		new StoreLoginFrame();
	}
}
