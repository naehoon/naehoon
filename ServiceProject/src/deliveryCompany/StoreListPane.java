package deliveryCompany;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;



public class StoreListPane extends JPanel{

	JTable storeListModelTable;
	private StoreFindPane storeFindPane;

	public StoreListPane() {
		storeListModelTable=new JTable(new StoreListModel());


		JPanel storeListPane=new JPanel();

		storeListPane.setLayout(new BorderLayout());
		storeListPane.add(new JScrollPane(storeListModelTable), BorderLayout.CENTER);
		JLabel storeListLabel=new JLabel("店のリスト",JLabel.CENTER);
		storeListPane.add(storeListLabel,"North");

		storeListModelTable.setPreferredScrollableViewportSize(new Dimension(400,400));

		add(storeListPane);




	}

	public void storeListset(){
		storeListModelTable.setModel(new StoreListModel());
	}

	public JTable getModelTable(){
		return storeListModelTable;
	}


}
