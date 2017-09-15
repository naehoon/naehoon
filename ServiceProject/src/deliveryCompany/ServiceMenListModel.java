package deliveryCompany;
import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import serviceMen.ServiceMenDAO;
import serviceMen.ServiceMenVO;

public class ServiceMenListModel extends AbstractTableModel{
	Object[][] data;
	Object[] columnName = {"出前の人の番号","出前の人の名前","電話番号","注文番号"};
	ServiceMenDAO sDao=new ServiceMenDAO();
	ServiceMenVO smVo;
	ArrayList<ServiceMenVO> list;

	public ServiceMenListModel(){
		int columnCount=columnName.length;
		list=sDao.getServiceMentotal();
		int rowCount=list.size();
		data=new Object[rowCount][columnCount];

		try{
		for(int index=0; index<rowCount; index++){
			smVo=list.get(index);
			data[index][0]=smVo.getNo();
			data[index][1]=smVo.getName();
			data[index][2]=smVo.getTel();
			data[index][3]=smVo.getOnum();
		}
		}catch(Exception e){
			System.out.println("ServiceMenListModel1");
		}
	}
	public int getColumnCount() {
		if(columnName==null)
			return 0;
		else
			return columnName.length;

	}
	public int getRowCount() {
		if(data==null)
			return 0;
		else
			return data.length;
	}
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}
	
	public String getColumnName(int column) {
		return (String)columnName[column];
	}



}
