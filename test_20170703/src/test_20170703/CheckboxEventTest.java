package test_20170703;

import java.awt.Button;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CheckboxEventTest extends Frame{
	Label q1;
	Label q2;
	Label score;
	Checkbox q1cb1,q1cb2,q1cb3,q1cb4;
	Checkbox q2cb1,q2cb2,q2cb3,q2cb4;
	CheckboxGroup group;
	Button end;
	
	public CheckboxEventTest(String title) {
		// TODO Auto-generated constructor stub
		super(title);
		setSize(500, 300);
		setLayout(new GridLayout(13, 1));
		
		q1= new Label("1, ������ ActionEvent �� ActionPerformd �޼��尡 ȣ��Ǵ� ����?(��� ������)");
		q1cb1 = new Checkbox("Button �� ��������");
		q1cb2 = new Checkbox("Text Filed ���� Enter key �� ��������");
		q1cb3 = new Checkbox("MenuItem �� ��������");
		q1cb4 = new Checkbox("List ���� ���� Ŭ������  item �� ��������");
		
		q2 = new Label("2, Frame �� �⺻ LayoutManager ��? (�ϳ���)");
		group = new CheckboxGroup();
		q2cb1 = new Checkbox("FlowLayout", group, false);
		q2cb2 = new Checkbox("GridLayout	", group, false);
		q2cb3 = new Checkbox("BorderLayout", group, false);
		q2cb4 = new Checkbox("CardLayout", group, false);
		
		score = new Label(" ��� �Դϴ�."	);
		end = new Button("�� ��ư�� ������ ����� �˼� �ֽ��ϴ�.");
		end.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				float totalScore = 0;
				if(q1cb1.getState()){
					totalScore += 12.5;
				}
				if(q1cb2.getState()){
					totalScore += 12.5;
				}
				if(q1cb3.getState()){
					totalScore += 12.5;
				}
				if(q1cb4.getState()){
					totalScore += 12.5;
				}
				if(q1cb3.getState()){
					totalScore += 50;
				}
				score.setText("��� :  ����� ������ "  + totalScore +"���Դϴ�.");
				
			}
		});
		
		add(q1);
		add(q1cb1);
		add(q1cb2);
		add(q1cb3);
		add(q1cb4);
		add(new Label(""));
		add(q2);
		add(q2cb1);
		add(q2cb2);
		add(q2cb3);
		add(q2cb4);
		add(end);
		add(score);
		
		setVisible(true);
	}
	
	public static void main(String[]args){
		CheckboxEventTest mainWin = new CheckboxEventTest("CheckboxEventTest = Quiz");
	}
}
