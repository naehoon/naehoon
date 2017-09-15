package store;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;


public class StorePane extends JFrame { // 배달현황과 배달완료탭을 보여주는 Frame　//　配達現況と完了を見せるFrame
	private JTabbedPane taps;
	private StorePaneOrder orderPane;
	private StorePaneDeliver deliverPane;
	private StoreDeliveryComplete completePane;
	private JTable deliverTable, completeTable;
	private StoreDAO sDAO;
	public static void main(String[] args) {
		new StorePane(); // 매장관리창 실행　//　売場管理實行

	}
	public StorePane() {

	}
	public StorePane(int snum) {
		sDAO = new StoreDAO();
		taps = new JTabbedPane();
		orderPane = new StorePaneOrder(snum);
		deliverPane = orderPane.getPaneDeliver();
		completePane = new StoreDeliveryComplete(snum);


		taps.addTab("主文要請", orderPane);
		taps.addTab("主文リスト", deliverPane);
		taps.addTab("完了", completePane); // 탭추가및 이름설정　//　tap追加ど名前設定

		getContentPane().add(taps);
		setTitle("店舗管理");

		pack();
		setVisible(true);
		
		this.deliverTable = deliverPane.getTable();	// deliverTable을 가져옴
		this.completeTable = completePane.getTable();	// completeTable을 가져옴
		
		// 실행간격 지정(10초) // 実行間隔指定（10秒）
		int sleepSec = 10 ;
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm:ss");
		// 주기적인 작업을 위한 
		ScheduledThreadPoolExecutor exec = new ScheduledThreadPoolExecutor(1);

		exec.scheduleAtFixedRate(new Runnable(){

			public void run(){
				try {
					Calendar cal = Calendar.getInstance();
					System.out.println(fmt.format(cal.getTime()));
					deliverTable.setModel(new StoreDeliveryListModel(snum));
					completeTable.setModel(new StoreDeliveryCompleteModel(snum));
					int msgCheck = sDAO.getSmessageCheck(snum);
					if(msgCheck > 0){
						
					Object[] options = {"確認"};
					JOptionPane.showOptionDialog(null, "メッサ-ジが到着しました。", "メッセ-ジ", JOptionPane.CANCEL_OPTION, JOptionPane.WARNING_MESSAGE,null, options,"確認");
					}
					} catch (Exception e) {
					e.printStackTrace();
					// 에러 발생시 Executor를 중지시킨다 // errorが發生時Executorを中止させる
					exec.shutdown() ;
				}
			}
		}, 0, sleepSec, TimeUnit.SECONDS);
	}

}