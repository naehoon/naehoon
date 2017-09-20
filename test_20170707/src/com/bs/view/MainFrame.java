package com.bs.view;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.bs.control.AddPane;
import com.bs.control.FindPane;
import com.bs.control.TotalPane;

public class MainFrame extends JFrame{

	private JTabbedPane tp;
	private AddPane ap;
	private FindPane fp;
	private TotalPane tpa;

	public MainFrame() {
		
		// 프레잉에 추가될 컴포넌트 초기화
		tp = new JTabbedPane();
		ap = new AddPane();
		fp = new FindPane();
		tpa = new TotalPane();
		
		// 탭 추가
		tp.addTab("사원정보입력", ap);
		tp.addTab("사원정보조회", fp);
		tp.addTab("사원전체보기", tpa);
		
		// TabbedPane을 프레임에 추가
		getContentPane().add(tp);
		setTitle("사원 관리(JDBC 버전)");
		pack();
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new MainFrame();
	}

}
