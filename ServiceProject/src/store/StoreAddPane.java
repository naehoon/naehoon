package store;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class StoreAddPane extends JFrame implements ActionListener { // 매장등록을 하기위한 pane　//　店の登錄のためのpane

	private JPanel jp[] = new JPanel[5];
	private JLabel jl[] = new JLabel[4];
	private JTextField tf[] = new JTextField[4];
	private JButton okb;
	private JButton csb;

	String[] caption = { "店舗名       :", "パスワード :", "店舗住所   :", "電話番号   :" };

	public StoreAddPane() {
		
		setLayout(new GridLayout(6, 1));
		setTitle("店頭登録");
		setVisible(true);
		setSize(350, 500);

		int size = caption.length;
		tf[0] = new JTextField(15);
		tf[1] = new JPasswordField(15);
		tf[2] = new JTextField(15);
		tf[3] = new JTextField(15);

		int i;
		for (i = 0; i < size; i++) {
			jp[i] = new JPanel();
			jl[i] = new JLabel(caption[i]);
		
			jp[i].add(jl[i]);
			jp[i].add(tf[i]);
			add(jp[i]);
		}
		jp[i] = new JPanel();

		jp[size] = new JPanel();
		okb = new JButton("登録");
		okb.addActionListener(this);
		csb = new JButton("キャンセル");
		csb.addActionListener(this);
		jp[size].add(okb);
		jp[size].add(csb);
		add(jp[size]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String e_type = e.getActionCommand();
		StoreVO svo = null;
		StoreDAO sdao = null;
		if (e_type.equals(okb.getText())) { // okb버튼을 누르면　//　okbボタンを押すと

			
			try {

				if(!(tf[0].getText().equals(""))&&!(tf[1].getText().equals(""))&&!(tf[3].getText().equals(""))){ // 텍스트필드가 공백이아니면　//テキストフィールドが空いていない場合
					
				
				svo = new StoreVO(0, tf[0].getText(), tf[2].getText(), 
						tf[3].getText(), tf[1].getText());  // 텍스트필드로 입력받은 값을 테이블에 저장 // テキストフィールドで入力したことを	テーブルに貯藏
			int data = Integer.parseInt(tf[3].getText());   // 전화번호는 숫자만 받음　//　電話番号は数字だけ
				sdao = new StoreDAO();
				sdao.getStoreRegiste(svo);
				}else if(tf[0].getText().equals("")||tf[1].getText().equals("")||tf[3].getText().equals("")){ // 텍스트필드가 공백일경우　//テキストフィールドが空いている場合
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "内容を入力してください。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}
			} catch(NumberFormatException e2){
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "數字で電話番号を入力してください", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
			
			} catch (Exception e1) {
				System.out.println("e1=[" + e1 + "]");
			

			}

			if (svo != null){}
				
		} else if (e_type.equals(csb.getText())) {
			setVisible(false); 

		}
	}

}