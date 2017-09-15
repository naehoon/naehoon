package store;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import deliveryCompany.OrderVO;

public class StoreDeliveryCompleteModel extends AbstractTableModel { // 배달완료테이블을 보여주는 모델　//　完了テーブルをみせるモデル
	Object[][] data; // 테이블을 가져오기위한 이차원배열 생성　//　テーブルの呼出のための配列生成
	Object[] columnName = {"注文番号","出前の人の番号","出前住所","メニューの数"};
	StoreDAO sdao = new StoreDAO();
	OrderVO ovo;
	ArrayList<OrderVO> list; // 테이블의 세로길이를 받기위한 ArrayList　// 見せるテーブルの縦をもらうためのArrayList

	public StoreDeliveryCompleteModel() {
		super();
	}


	public StoreDeliveryCompleteModel(int snum){
		int columnCount=columnName.length; // 테이블의 가로길이 설정　// 見せるテーブルの横設定
		list=sdao.getStoreDeliveryComplete(snum);
		int rowCount=list.size(); // 테이블의 세로길이 설정　// 見せるテーブルの縦設定
		data=new Object[rowCount][columnCount]; // data에 설정한값 저장　// dataに設定したことを貯藏
		
		for(int index=0; index<rowCount; index++){ // for문을 이용해  설정한값 대입　// 設定したことを貯藏
			ovo=list.get(index);
			data[index][0]=ovo.getOnum();
			data[index][1]=ovo.getDelinum();
			data[index][2]=ovo.getOaddr();
			data[index][3]=ovo.getMenuCount();
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
