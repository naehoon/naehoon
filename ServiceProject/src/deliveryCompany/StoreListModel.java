package deliveryCompany;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import store.StoreDAO;
import store.StoreVO;

public class StoreListModel extends AbstractTableModel {
	Object[][] data;
	Object[] columnName = {"店番号","店名","店住所","電話番号"," パスワード"};
	StoreDAO stDao = new StoreDAO();
	StoreVO smVo;
	CompanyDAO cDao=new CompanyDAO();
	ArrayList<StoreVO> list;

	public StoreListModel() {
		int columnCount = columnName.length;
		list = stDao.getStoretotal();
		int rowCount = list.size();
		data = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			smVo = list.get(index);
			data[index][0] = smVo.getNo();
			data[index][1] = smVo.getName();
			data[index][2] = smVo.getAddress();
			data[index][3] = smVo.getTel();	
			data[index][4] = smVo.getPass();
		}
	}


	public StoreListModel(StoreVO num) {
		int columnCount = columnName.length;
		
		cDao.storeUpdate(num);
		int rowCount = list.size();
		data = new Object[rowCount][columnCount];

		for (int index = 0; index < rowCount; index++) {
			smVo = list.get(index);
			data[index][0] = smVo.getNo();
			data[index][1] = smVo.getName();
			data[index][2] = smVo.getAddress();
			data[index][3] = smVo.getTel();
			data[index][4] = smVo.getPass();
		}
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		if (columnName == null)
			return 0;
		else
			return columnName.length;

	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if (data == null)
			return 0;
		else
			return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}

	public String getColumnName(int column) {
		return (String) columnName[column];
	}

}