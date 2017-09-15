package serviceMen;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import serviceMen.DeliverAddPane;
import serviceMen.ServiceMenDAO;
//배달원 등록과 로그인이 이루어짐、 　出前の人登録とログイン
public class DeliverAddPane extends JFrame implements ActionListener{

	private JPanel jp[] = new JPanel[5];
	private JLabel jl[] = new JLabel[5];
	private JTextField tf[] = new JTextField[5];
	private JButton okb,rsb;
	private ServiceShowDetailPane opendp;

	String[] caption = { "出前の人番号 :", "名前 :", "出前の人の電話番号   :",""};

	public DeliverAddPane() {

		setLayout(new GridLayout(5, 1));

		int size = caption.length;
		int i;

		for (i =1; i < size-1; i++) {
			jp[i] = new JPanel();
			jl[i] = new JLabel(caption[i]);
			tf[i] = new JTextField(15);
			jp[i].add(jl[i]);
			jp[i].add(tf[i]);
			add(jp[i]);
		}
		jp[i] = new JPanel();
		jl[i]=new JLabel(caption[i]);
		jp[i].add(jl[i]);
		add(jp[i]);

		jp[size] = new JPanel();
		okb = new JButton("登録");
		okb.addActionListener(this);
		rsb = new JButton("ログイン");
		rsb.addActionListener(this);
		jp[size].add(okb);
		jp[size].add(rsb);
		add(jp[size]);
		setVisible(true);
		setSize(200, 300);
		setLocation(500,500);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String e_type = e.getActionCommand();
		ServiceMenVO svo = null;
		ServiceMenDAO sdao = null;

		if (e_type.equals(okb.getText())) { //등록버튼  때、　登録のボタンをクリックしたとき
			try {
				svo = new ServiceMenVO(0,tf[1].getText(), tf[2].getText());
				String deliname = tf[1].getText().trim();
				String delitel=new String(tf[2].getText().trim());
				sdao = new ServiceMenDAO();
				if(deliname.equals("")){
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "名前を入力してください", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}else if(!delitel.matches("^[0-9]*$")){ //문자열 전체 숫자만 입력되도록、　文字列全体が数字のみを入力するように検査
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "電話番号は数字で入力してください", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");

				}else if(delitel.equals("")){
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "電話番号を入力してください", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}else if(sdao.getServiceManTel(tf[2].getText())==null){//등록 성공、　登録成功
					sdao.getDeliverRegiste(svo);
					svo=sdao.getDeliverCheck(deliname, delitel);
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "出前の人番号は"+svo.getNo()+"です。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}else{
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "もう登録されています。。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}

			} catch (Exception e1) {
				System.out.println("e1=[" + e1 + "]");	
			}
		} 
		else if (e_type.equals(rsb.getText())) {//로그인버튼을 눌렀을 때、ログインのボタンをクリックしたとき
			try {

				String deliname = tf[1].getText().trim();
				String delitel=new String(tf[2].getText().trim());
				svo=new ServiceMenVO();
				sdao=new ServiceMenDAO();
				svo=sdao.getDeliverCheck(deliname, delitel);

				if(deliname.equals("")){					
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "名前を入力してください", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}else if(!delitel.matches("^[0-9]*$")){ //숫자만 입력되도록.　数字のみを入力するように　検査
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "電話番号は数字で入力してください", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}else if(delitel.equals("")){
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "電話番号を入力してください", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}else if(deliname.equals(svo.getName()) && delitel.equals(svo.getTel())){
					JOptionPane.showMessageDialog(this, svo.getName()+"さん　ログインされました ");
					// ServiceShowDetailPane으로 로그인정보를 보냄.　ServiceShowDetailPaneにログインの情報を伝送
					opendp=new ServiceShowDetailPane(svo.getTel(),svo.getOnum(),svo.getName(),svo.getNo()); 
					opendp.setVisible(true);
					dispose();
				}else{
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "名前と電話番号を確認してください。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}
			}catch(NullPointerException ne){
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "名前と電話番号を確認してください。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
			}catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}
		}
	}
}

