package serviceMen;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import deliveryCompany.CompleteDeliveryVO;

//배달완료목록모델。出前完了のリスト
public class DeliverCmptModel extends DefaultTableModel{
	Object[][] data;
	Object[] columnName = {"注文番号","出前の人の番号","店名","店番号","出前住所","メニューの数"};
	ServiceMenDAO deDao=new ServiceMenDAO();
	CompleteDeliveryVO cdvo;
	ArrayList<CompleteDeliveryVO> list;

	public DeliverCmptModel(int delinum) throws Exception{
		int columnCount=columnName.length;
		list=deDao.getCmptShow(delinum);
		int rowCount=list.size();
		data=new Object[rowCount][columnCount];

		try{
			for(int index=0; index<rowCount; index++){
				cdvo=list.get(index);
				data[index][0]=cdvo.getOnum();
				data[index][1]=cdvo.getDelinum();
				data[index][2]=cdvo.getSname();
				data[index][3]=cdvo.getSnum();
				data[index][4]=cdvo.getOaddr();
				data[index][5]=cdvo.getMenuCount();
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
