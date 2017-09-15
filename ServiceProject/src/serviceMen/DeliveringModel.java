package serviceMen;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

import deliveryCompany.CompanyDAO;
import deliveryCompany.OrderVO;
//배달중목록모델。出前中のリスト。
public class DeliveringModel extends DefaultTableModel{
		 Object[][] data;
	Object[] columnName = {"注文番号","店名","店住所","出前住所","メニューの数","出前狀態"};
	ServiceMenDAO deDao=new ServiceMenDAO();
	OrderVO oVo;
	ArrayList<OrderVO> list;

	public DeliveringModel(String delitel) throws Exception{
		int columnCount=columnName.length;
		list=deDao.getDelivering(delitel);
		data=new Object[1][columnCount];

		try{
			for(int index=0; index<1; index++){
				oVo=list.get(index);
				data[index][0]=oVo.getOnum();
				data[index][1]=oVo.getSname();
				data[index][2]=oVo.getSaddr();
				data[index][3]=oVo.getOaddr();
				data[index][4]=oVo.getMenuCount();
				data[index][5]=oVo.getCodename();
			}
		}catch(Exception e){
			System.out.println(e);
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
