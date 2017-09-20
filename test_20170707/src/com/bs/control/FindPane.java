package com.bs.control;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.bs.vo.EmployeeVO;

public class FindPane extends JPanel implements ActionListener{

	// Swing 관련 멤버
	private JPanel jp[] = new JPanel[6];
	private JLabel jl[] = new JLabel[5];
	private JTextField tf[] = new JTextField[5];
	private JButton okb;
	private JButton rsb;
	String[] caption = { "사 번 :", "이 름 :", "직 책 :", "부 서 :", "메 일 :" };

	//직원 검색
	public FindPane() {
		setLayout(new GridLayout(7, 1)); //그리드 그리기
		EtchedBorder eb = new EtchedBorder();
		setBorder(eb);
		int size = caption.length;
		
		for (int i = 0; i < size; i++) {
			jl[i] = new JLabel(caption[i]); //라벨 만들기
			tf[i] = new JTextField(15); //텍스트 필드
			jp[i] = new JPanel(); //패널만들기
			jp[i].add(jl[i]); //패널에 라벨넣기 
			jp[i].add(tf[i]); //패널에 텍스트 필드 넣기 
			add(jp[i]); //컴포넌트에 추가하기 
			
			tf[i].setEditable(false);
			
			if (i == 0 || i == 1){
				tf[i].setEditable(true);
			}
		}
		
		jp[size] = new JPanel();
		
		okb = new JButton("사원정보조회");
		okb.addActionListener((ActionListener) this);
		
		rsb = new JButton("다시쓰기");
		rsb.addActionListener((ActionListener) this);
		
		jp[size].add(okb);
		jp[size].add(rsb);
		add(jp[size]);
	}

	public void actionPerformed(ActionEvent ae) {
		String ae_type = ae.getActionCommand();
		EmployeeVO evo = null;
		EmployeeDAO edvo = null;
		
		System.out.println("okb.getText() "  + okb.getText());
		
		if (ae_type.equals(okb.getText())) {// 성적조회 버튼이 클릭되었을 경우
			try {
				edvo = new EmployeeDAO();
				String sno = tf[0].getText().trim();
				String sname = tf[1].getText().trim();
				if (!sno.equals("") && !sname.equals("")) {
					int no = Integer.parseInt(sno);
					
					evo = edvo.getEmployeeCheck(no, sname); //no , name 매개변수로 넘겨줌 
					
				} else if (!sno.equals("") && sname.equals("")) { //
					int no = Integer.parseInt(sno);
					
					evo = edvo.getEmployeeNo(no); //입력 받은 no 를 넘겨줌 
					
				} else if (sno.equals("") && !sname.equals(""))
					evo = edvo.getEmployeeName(sname);
			} catch (Exception e) {
				System.out.println("e=[" + e + "]");
			}
			
			if (evo != null) { //조회 성공시 
				tf[0].setText(evo.getNo() + "");
				tf[1].setText(evo.getName());
				tf[2].setText(evo.getJobGrade());
				tf[3].setText(evo.getDepartment() + "");
				tf[4].setText(evo.getEmail());
				
			}else{
				
				JOptionPane.showMessageDialog(okb, this, "검색 실패", 0);
				
			}
		} else if (ae_type.equals(rsb.getText())) {
			int size = caption.length;
			
			// 텍스트 필드를 초기화 한다.
			for (int i = 0; i < size; i++) {
				tf[i].setText("");
			}
		}
	}
}
