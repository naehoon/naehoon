package serviceMen;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import deliveryCompany.OrderVO;
//배달전목록모델、　出前のリスト
public class ServiceListModel extends AbstractTableModel{
	Object[][] data;
	Object[] columnName = {"注文番号","店名","店住所","出前住所","メニューの数","出前狀態"};
	ServiceMenDAO deDao=new ServiceMenDAO();
	OrderVO oVo;
	ArrayList<OrderVO> list;

	public ServiceListModel(){
		int columnCount=columnName.length;
		list=deDao.getOrdertotal();
		int rowCount=list.size();
		data=new Object[rowCount][columnCount];

		try{
			for(int index=0; index<rowCount; index++){
				oVo=list.get(index);
				data[index][0]=oVo.getOnum();
				data[index][1]=oVo.getSname();
				data[index][2]=oVo.getSaddr();
				data[index][3]=oVo.getOaddr();
				data[index][4]=oVo.getMenuCount();
				data[index][5]=oVo.getCodename();
			}
		}catch(Exception e){
			e.printStackTrace();
			
		}
	}

	@Override
	public int getColumnCount() {
		if(columnName == null)
			return 0;
		else
			return columnName.length;
	}

	@Override
	public int getRowCount() {
		if(data == null)
			return 0;
		else
			return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		return data[rowIndex][columnIndex];
	}

	public String getColumnName(int column) {
		return (String)columnName[column];
	}

}
