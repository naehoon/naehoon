package deliveryCompany;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;

public class OrderListPane extends JPanel{
	private JTable companyOrderListTable;
	public OrderListPane() {
		if(companyOrderListTable != null)
			companyOrderListTable.clearSelection();	// 새로고침시 오류방지
		
		companyOrderListTable = new JTable(new OrderListModel());

		companyOrderListTable.getTableHeader().setReorderingAllowed(false);//테이블의 열을 못움직이게함//テーブルの列の固定
		companyOrderListTable.getTableHeader().setResizingAllowed(false);//테이블의 열의 길이조절을 못하게 함//テーブルの列の長さの固定

		setLayout(new BorderLayout());

		JPanel orderListPanel = new JPanel();
		orderListPanel.setLayout(new BorderLayout());
		JLabel orderListLabel = new JLabel("注文現況", JLabel.CENTER);
		orderListPanel.add(orderListLabel, "North");
		orderListPanel.add(new JScrollPane(companyOrderListTable));

		add(orderListPanel, BorderLayout.WEST);

		EtchedBorder eb = new EtchedBorder();
		setBorder(eb);

		setVisible(true);

		// 실행간격 지정(3초)　//　実行間隔指定（5秒）
		int sleepSec = 3 ;
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
		// 주기적인 작업을 위한 　//　
		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

		exec.scheduleAtFixedRate(new Runnable(){

			public void run(){
				try {
					Calendar cal = Calendar.getInstance();
					System.out.println(fmt.format(cal.getTime()));
					companyOrderListTable.setModel(new OrderListModel());
				} catch (Exception e) {
					System.out.println("OrderListPane");
					e.printStackTrace();
					// 에러 발생시 Executor를 중지시킨다　//errorが發生時Executorを中止させる
					exec.shutdown() ;
				}
			}
		}, 0, sleepSec, TimeUnit.SECONDS);

	}

	public JTable model(){
		return companyOrderListTable;
	}

}





