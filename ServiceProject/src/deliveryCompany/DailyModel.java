package deliveryCompany;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class DailyModel extends AbstractTableModel{
	Object data[][];
	Object columnName[] = {"日付","出前の人の番号","店名","メニューの数"};
	CompanyDAO cDAO;
	DailyVO daVO;
	ArrayList<DailyVO> list;
	
	public DailyModel() {
		cDAO=new CompanyDAO();

		int columnCount = columnName.length;
		list = cDAO.getDailytotal();
		int rowCount = list.size();
		data = new Object[rowCount][columnCount];
		try{
		for (int index = 0; index < rowCount; index++) {
			daVO = list.get(index);
			data[index][0] = daVO.getDate();
			data[index][1] = daVO.getDelinum();
			data[index][2] = daVO.getSname();
			data[index][3] = daVO.getMenuCount();
		}
		}catch(Exception e){
			System.out.println("DailyModel1");
		}
	}
	
	public DailyModel(String date, String sname, int delinum) {
		cDAO=new CompanyDAO();
		int columnCount = columnName.length;
		list = cDAO.dailySearchAll(date, sname, delinum);
		int rowCount = list.size();
		data = new Object[rowCount][columnCount];
		try{
		for (int index = 0; index < rowCount; index++) {
			daVO = list.get(index);
			data[index][0] = daVO.getDate();
			data[index][1] = daVO.getDelinum();
			data[index][2] = daVO.getSname();
			data[index][3] = daVO.getMenuCount();
		}
		}catch(Exception e){
			System.out.println("DailyModel2");
		}
	}
	
	public DailyModel(String date, String sname) {
		cDAO=new CompanyDAO();
		int columnCount = columnName.length;
		list = cDAO.dailySearchName(date, sname);
		int rowCount = list.size();
		data = new Object[rowCount][columnCount];
		try{
		for (int index = 0; index < rowCount; index++) {
			daVO = list.get(index);
			data[index][0] = daVO.getDate();
			data[index][1] = daVO.getDelinum();
			data[index][2] = daVO.getSname();
			data[index][3] = daVO.getMenuCount();
		}
		}catch(Exception e){
			System.out.println("DailyModel3");
		}
	}
	
	public DailyModel(String date, int delinum) {
		cDAO=new CompanyDAO();
		int columnCount = columnName.length;
		list = cDAO.dailySearchDelinum(date, delinum);
		int rowCount = list.size();
		data = new Object[rowCount][columnCount];
		try{
		for (int index = 0; index < rowCount; index++) {
			daVO = list.get(index);
			data[index][0] = daVO.getDate();
			data[index][1] = daVO.getDelinum();
			data[index][2] = daVO.getSname();
			data[index][3] = daVO.getMenuCount();
		}
		}catch(Exception e){
			System.out.println("DailyModel4");
		}
	}
	
	public DailyModel(String date) {
		cDAO=new CompanyDAO();
		int columnCount = columnName.length;
		list = cDAO.dailySearchDate(date);
		int rowCount = list.size();
		data = new Object[rowCount][columnCount];
		try{
		for (int index = 0; index < rowCount; index++) {
			daVO = list.get(index);
			data[index][0] = daVO.getDate();
			data[index][1] = daVO.getDelinum();
			data[index][2] = daVO.getSname();
			data[index][3] = daVO.getMenuCount();
		}
		}catch(Exception e){
			System.out.println("DailyModel5");
		}
	}

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
