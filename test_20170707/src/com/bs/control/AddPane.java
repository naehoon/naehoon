package com.bs.control;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import com.bs.vo.EmployeeVO;

public class AddPane extends JPanel implements ActionListener, ItemListener {

	// Swing 관련 멤버
	private JPanel jp[] = new JPanel[6];
	private JLabel jl[] = new JLabel[5];
	private JTextField tf[] = new JTextField[5];
	private JButton okb;
	private JButton rsb;
	private int department = 10;
	private JComboBox combo = new JComboBox();

	String[] caption = { "이 름 :", "직 책 :", "메 일 :", "부 서 :" };

	public AddPane() { // 사원 정보 입력
		setLayout(new GridLayout(6, 1));
		EtchedBorder eb = new EtchedBorder();
		setBorder(eb);
		int size = caption.length; //사이즈
		System.out.println("size :" + size);
		
		int i;

		for (i = 0; i < size - 1; i++) {
			jp[i] = new JPanel();
			jl[i] = new JLabel(caption[i]);
			tf[i] = new JTextField(15); //
			jp[i].add(jl[i]); //라벨 추가 
			jp[i].add(tf[i]); //텍스트 필드 추가 
			add(jp[i]); //컴포넌트 추가
		}
		
		jp[i] = new JPanel();
		jl[i] = new JLabel(caption[i]);  
		jp[i].add(jl[i]);  //라벨 추가 
		add(jp[i]); //컴포넌트 추가
		
		combo.addItem("부서번호를 선택하세요.");
		for (int c = 1; c <= 5; c++) {
			combo.addItem(c * 10);
		}
		
		jp[i].add(combo); //패널에 콤보박스 추가 
		
		combo.addItemListener(this); //콤보박스 이벤트 리스너 
		
		jp[size] = new JPanel(); //패널 생성 

		okb = new JButton("저장하기"); 

		okb.addActionListener(this);

		rsb = new JButton("다시쓰기");

		rsb.addActionListener(this);

		jp[size].add(okb); //패널에 버튼 생성 
		jp[size].add(rsb); //패널에 버튼 생성 
		
		add(jp[size]); //컴포넌트에 패널 추가 
	}

	public void actionPerformed(ActionEvent ae) {
		String ae_type = ae.getActionCommand();
		EmployeeVO evo = null;
		EmployeeDAO edvo = null;

		if (ae_type.equals(okb.getText())) {// 저장하기 버튼이 클릭되었을 경우
			try {
				evo = new EmployeeVO(0, tf[0].getText(), tf[1].getText(), department, tf[2].getText());
				edvo = new EmployeeDAO();
				edvo.getEmployeeregiste(evo);
			} catch (Exception e) { 
				System.out.println("e=[" + e + "]");
			}
			if (edvo != null)
				JOptionPane.showMessageDialog(this, tf[0].getText() + "님이 성공적으로 추가됨");
		
		} else if (ae_type.equals(rsb.getText())) {
				
			int size = caption.length;
			
			// 텍스트 필드를 초기화 한다.
			for (int i = 0; i < size - 1; i++) {
				tf[i].setText("");
			}
		}
	}

	//콤보박스 리스너 메서드 구현 
	public void itemStateChanged(ItemEvent ie) {
		if (ie.getStateChange() == ItemEvent.SELECTED) {
			System.out.println("test " + department);
			department = Integer.parseInt(ie.getItem().toString());
		}
	}
}
