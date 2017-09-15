
package deliveryCompany;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;
//배달완료 테이블의 자료를 가져오는 모델
public class CompleteDeliveryModel extends AbstractTableModel {
	Object[][] data;
	Object[] columnName = {"注文番号","出前の人の番号","店名","店番号","出前住所","メニューの数"};
	CompanyDAO comDAO=new CompanyDAO();
	CompleteDeliveryVO comVO;
	ArrayList<CompleteDeliveryVO> list;

	public CompleteDeliveryModel(){
		int columnCount=columnName.length;
		list=comDAO.getCompleteDeliverytotal();//list에 CompanyDAO의 getCompleteDeliverytotal에서 가져오는 자료를 저장
		//listにCompanyDAOのgetCompleteDeliverytotalからもらった資料を貯藏
		int rowCount=list.size();
		data=new Object[rowCount][columnCount];

		//data에 자료를 삽입//dataに資料を貯藏
		try{
			for(int index=0; index<rowCount; index++){
				comVO=list.get(index);
				data[index][0]=comVO.getOnum();
				data[index][1]=comVO.getDelinum();
				data[index][2]=comVO.getSname();
			}
		}catch(Exception e){
			System.out.println("CompleteDeliveryModel1");
		}

	}

	//CompleteDeliveryFindPane의 콤보박스의 아이템에 따른 리스트 출력//CompleteDeliveryFindPaneのコンボボックスでりすと出力
	public CompleteDeliveryModel(String sname){
		if(sname.equals("注文番号")){
			list=comDAO.OrderListOnum();
		}
		else if(sname.equals("出前番号")){
			list=comDAO.OrderListSortDelinum();
		}else if(sname.equals("店名")){
			list=comDAO.OrderListSortSname();
		}
		else{
			list=comDAO.getCompleteDeliveryName(sname);
		}
		int columnCount=columnName.length;
		int rowCount=list.size();
		data=new Object[rowCount][columnCount];

		try{
			for(int index=0; index<rowCount; index++){
				comVO=list.get(index);
				data[index][0]=comVO.getOnum();
				data[index][1]=comVO.getDelinum();
				data[index][2]=comVO.getSname();
				data[index][3]=comVO.getSnum();
				data[index][4]=comVO.getOaddr();
				data[index][5]=comVO.getMenuCount();
				
			}
		}catch(Exception e){
			System.out.println("CompleteDeliveryModel2");
		}


	}




	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		if(columnName==null)
			return 0;
		else
			return columnName.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		if(data==null)
			return 0;
		else
			return data.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}

	public String getColumnName(int column){
		return (String)columnName[column];
	}

}
