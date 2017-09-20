package com.bs.control;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import com.bs.vo.EmployeeVO;

public class EmployModel extends AbstractTableModel{

	Object[][] data;
	Object[] columnName;
	EmployeeDAO emDao = new EmployeeDAO();
	EmployeeVO emVo;
	ArrayList<String> title;
	ArrayList<EmployeeVO> list;

	public EmployModel() {
		// 열의 개수와 행의 개수를 알아야 2차원 배열선언
		// 테이블에서 컬럼이름을 얻어와서 1차원 배열 선언
		title = emDao.getColumnName();
		columnName = title.toArray();
		int columnCount = title.size();
		list = emDao.getEmployeetotal();
		int rowCount = list.size();
		data = new Object[rowCount][columnCount];
		
		//배열에 데이터 입력 
		for (int index = 0; index < rowCount; index++) {
			emVo = list.get(index);
			
			data[index][0] = emVo.getNo();
			data[index][1] = emVo.getName();
			data[index][2] = emVo.getJobGrade();
			data[index][3] = emVo.getDepartment();
			data[index][4] = emVo.getEmail();
		}
		
	}

	//컬럼 카운트 반환 메서드 구현 
	public int getColumnCount() { 
		// TODO Auto-generated method stub
		if (columnName == null)
			return 0;
		else
			return columnName.length;
	}

	//로우 카운트 반환 메서드 구현 
	public int getRowCount() {
		// TODO Auto-generated method stub
		if (data == null)
			return 0;
		else
			return data.length;
	}

	//getValue 메서드 구현 
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		return data[rowIndex][columnIndex];
	}
	
	//컬럼네임 반환 메서드 구현 
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) columnName[column];
	}
}
