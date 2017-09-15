package deliveryCompany;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;
//메인 클래스
public class CompanyMain extends JFrame implements WindowListener{
	private JTabbedPane taps;
	private OrderListFindPane orderListFindPane;
	private CompleteDeliveryFindPane completeFindPane; 
	private StoreFindPane storeFindPane;
	private ServiceMenFindPane svmlp;
	private DailyPane daily;

	public CompanyMain(){


		taps = new JTabbedPane();

		orderListFindPane = new OrderListFindPane();

		completeFindPane = new CompleteDeliveryFindPane();

		storeFindPane = new StoreFindPane();

		svmlp=new ServiceMenFindPane();

		daily=new DailyPane();

		taps.addTab("注文現況", orderListFindPane);
		taps.addTab("出前完了現況", completeFindPane);
		taps.addTab("店管理", storeFindPane);
		taps.addTab("出前の人管理", svmlp);
		taps.addTab("貯藏された 出前完了", daily);
		getContentPane().add(taps);
		setTitle("出前 管理プログラム");

		setSize(800, 600);

		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);	
		this.addWindowListener(this);//프로그램 종료시 이벤트를 주기위한 리스너　//プログラム終了のイベントのためのListener
	}

		public static void main(String[] args) {
			new CompanyMain();

		}
		public void windowActivated(WindowEvent e) {}@Override
		public void windowClosed(WindowEvent e) {}
		//프로그램이 종료가 되면서 배달완료테이블의 자료들이 데일리테이블로 옮겨지면서 삭제됨　//プログラムが終了されながら出前完了テーブルの資料がdailyテーブルに伝送されながら削除
		public void windowClosing(WindowEvent e) {
			CompanyDAO cDAO=new CompanyDAO();
			try{
			cDAO.getDailyRegiste();
			} catch(Exception ec){
				System.out.println("CompanyMain1");
			}
			
			
			String dml = " truncate dok;";
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
				con = DBUtil.getConnection();
				pstmt = con.prepareStatement(dml);
				pstmt.executeUpdate();
			}catch (Exception ec) {
				System.out.println("CompanyMain2");
			} 
		}
		public void windowDeactivated(WindowEvent e) {}
		public void windowDeiconified(WindowEvent e) {}
		public void windowIconified(WindowEvent e) {}
		public void windowOpened(WindowEvent e) {}
	}
