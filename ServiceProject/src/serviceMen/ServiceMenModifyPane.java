package serviceMen;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
//배달원 핸드폰번호 변경、出前の人の電話番号を変更
public class ServiceMenModifyPane extends JFrame implements ActionListener {
	private JPanel jnorth=new JPanel();
	private JPanel jcenter=new JPanel();
	private JPanel jsouth=new JPanel();
	private JPanel jp[]=new JPanel[3];
	private JLabel jl[]=new JLabel[3];
	private JTextField jtf[]=new JTextField[3];
	private JButton mdfok, mdfcc;
	String[] caption={"出前の人番号 :", "名前 :", "出前の人の電話番号   :"};

	public ServiceMenModifyPane(){}

	public ServiceMenModifyPane(String tel,String name,int dnum){
		jcenter.setLayout(new GridLayout(3,1));
		int size = caption.length;
		for (int i =0; i < size; i++) {
			jp[i] = new JPanel();
			jl[i] = new JLabel(caption[i]);
			jtf[i] = new JTextField(15);
			jp[i].add(jl[i]);
			jp[i].add(jtf[i]);
			jcenter.add(jp[i]);
		}
		jtf[0].setText(dnum+"");
		jtf[0].setEditable(false);
		jtf[1].setText(name);
		jtf[1].setEditable(false);
		jtf[2].setText(tel);
		jsouth.setLayout(new FlowLayout());
		mdfok = new JButton("変更");
		mdfok.addActionListener(this);
		mdfcc = new JButton("キャンセル");
		mdfcc.addActionListener(this);
		jsouth.add(mdfok);
		jsouth.add(mdfcc);
		add(jnorth,"North");
		add(jcenter,"Center");
		add(jsouth,"South");
		setVisible(true);
		setSize(200, 300);
		setLocation(500,500);
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		String e_type=ae.getActionCommand();
		ServiceMenDAO sdao=null;

		//변경버튼이 클릭되었을 경우。変更ボタンがクリックされたとき
		if(e_type.equals(mdfok.getText())){
			try{
				sdao=new ServiceMenDAO();
				String sno=jtf[0].getText().trim();
				String stel=new String(jtf[2].getText().trim());
				if(!stel.matches("^[0-9]*$")){ //숫자만 입력。数字のみを入力
					
				Object[] options = {"確認"};
				JOptionPane.showOptionDialog(null, "電話番号は数字で入力してください。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
				}
				
				else{
					int no=Integer.parseInt(sno);
					sdao.serviceMenUpdate(stel,no);
					
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "変更されました。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
					dispose();
				}
			}catch(NullPointerException ne){
				ne.printStackTrace();
			}catch(Exception e){
				System.out.println("e=["+e+"]");
			}
		}
		else if(e_type.equals(mdfcc.getText())){
			dispose();
		}
	}
}

