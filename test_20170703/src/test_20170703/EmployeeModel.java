package test_20170703;

import javax.swing.table.AbstractTableModel;

//�𵨸� Ŭ���� (�����͸� ��������)

public class EmployeeModel extends AbstractTableModel {
	Object[][] data = { { "2017-10001", "park", "Vice Chief", 10, "park@naver.com" },
			{ "2017-10002", "kim", "Manager", 20, "kim@naver.com" },
			{ "2017-10003", "Hong", "director", 30, "hong@naver.com" } };

	String[] columnName = { "ID-No", "Name", "Title", "Major", "Email" };
	
	//���� ���� 
	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return data.length;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return data[0].length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}
	
	@Override
    public String getColumnName(int column) {
        return columnName[column];
    }
}
