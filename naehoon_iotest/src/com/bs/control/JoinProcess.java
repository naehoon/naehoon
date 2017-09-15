package com.bs.control;

import java.security.NoSuchAlgorithmException;
import java.util.Map;

import com.bs.dao.JoinDAO;

public class JoinProcess {

	public static void main(String[] args) throws NoSuchAlgorithmException {
		// TODO Auto-generated method stub
		JoinDAO jdao = new JoinDAO();
		int result = 0;
		
		while (true) {
			switch (jdao.displayMenu()) {
			case 1:
				while(result < 1){
					result = jdao.inputInfo(); //회원정보 입력 메서드 
					result = jdao.replay(result);
				}
				jdao.ouputStream(); //파일로 내보내기
				result = 0;
				break;
			case 2:
				Map<String, String> map = jdao.inputStream();
				jdao.login(map);
				break;
			}
		}
	}
}
