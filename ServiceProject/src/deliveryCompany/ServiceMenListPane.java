package deliveryCompany;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ServiceMenListPane extends JPanel{

	private JTable serviceMenListModelTable;

	public ServiceMenListPane() {
		setLayout(new BorderLayout());

		serviceMenListModelTable=new JTable(new ServiceMenListModel());

		JPanel storeListPane=new JPanel();
		storeListPane.setLayout(new BorderLayout());
		storeListPane.add(new JScrollPane(serviceMenListModelTable));
		add(storeListPane,BorderLayout.WEST);
		JLabel storeListLabel=new JLabel("出前の人リスト",JLabel.CENTER);
		storeListPane.add(storeListLabel,"North");

		serviceMenListModelTable.setPreferredScrollableViewportSize(new Dimension(400,700));

	}
	
	public void serviceListset(){
		serviceMenListModelTable.setModel(new ServiceMenListModel());
	}

	public JTable getserviceTable(){
		return serviceMenListModelTable;
	}


	
}
