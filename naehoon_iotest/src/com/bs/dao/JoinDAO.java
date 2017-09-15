package com.bs.dao;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import com.bs.model.Join;

public class JoinDAO {

	ArrayList<Join> record = new ArrayList<Join>();

	public int displayMenu() {

		System.out.println("choose menu >> 1.Join , 2.Login");
		int menu = 0;

		do {
			try {
				Scanner s = new Scanner(System.in);
				menu = Integer.parseInt(s.nextLine());

				if (menu >= 1 && menu <= 2) {
					break;
				} else {
					throw new Exception();
				}
			} catch (Exception e) {
				System.out.println("잘못 입력. 다시 입력!! ->> 메뉴 선택(1~2) : ");
				System.out.println();
			}
		} while (true);
		return menu;
	}

	// 아이디 입력 메서드
	public int inputInfo() {
		int result = 0;
		System.out.println("Regist new ID");
		// String uid = "";
		boolean YNflag = true;
		boolean idfllag = false;
		boolean pwFlag = false;
		String flag = "";
		String idPattern = "^[a-zA-Z]*$";
		String passPattern = "^[0-9]*$";
		Join join = new Join();
		String tmpId = "";
		String tmpPass = "";
		Scanner sc = new Scanner(System.in);

		do {
			try {
				
				System.out.print("ID : ");
				tmpId = sc.nextLine();
				ouputStream();
				Map<String, String> map = inputStream();

				if (!Pattern.matches(idPattern, tmpId)) {
					System.out.println("Error Alpahabet Only ");
					idfllag = false;
					
				}else if (tmpId.length() < 4) {
					System.out.println("Error Minimum length : 4");
					idfllag = false;
					
				}else if (tmpId.length() > 8) {
					System.out.println("Error Maximum length : 8");
					idfllag = false;

				}else if(!map.isEmpty()){ //아이디 중복 확인
//					System.out.println("22222222222" + map.isEmpty());
					for (String mapkey : map.keySet()) { // 키 갯수만큼 반복한다
						// 아이디 패스워드 비교해서 같다면
//						System.out.println(map.keySet() + "333333333333333");
						if (mapkey.equals(tmpId.toString())) { // 앞에
							System.out.println("this id is aleady exist.");
							idfllag = false;
							break;
						}else{
							idfllag = true;
							join.setId(tmpId); // 아이디 입력
						}
					}
				}else{
					idfllag = true;
					join.setId(tmpId); // 아이디 입력
				}
				
			}catch (Exception e) {
				// TODO: handle exception
			}
		}while(!idfllag);

		do{
			if(idfllag){
				System.out.print("PASS : ");
				tmpPass = sc.nextLine();

				if (!Pattern.matches(passPattern, tmpPass)) {
					System.out.println("Error number  Only ");
					pwFlag = false;

				} else if (tmpPass.length() < 6) {
					System.out.println("Error Minimum length : 6");
					pwFlag = false;

				} else if (tmpPass.length() > 8) {
					System.out.println("Error Maximum length : 8");
					pwFlag = false;
					
				}else{
					pwFlag = true;
					try { // 암호화
						MessageDigest md = MessageDigest.getInstance("SHA-1"); // 암호화하기
						md.update(tmpPass.getBytes()); // 입력받은 암호를 SHA-1으로 변환할 예정!
						byte byteData[] = md.digest();

						StringBuffer sb = new StringBuffer();
						for (int i = 0; i < byteData.length; i++) {
							sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
						}

						join.setPass(sb.toString()); // 패스워드 입력

					} catch (NoSuchAlgorithmException e) {
						e.printStackTrace();
					}

					record.add(join); // 어레이 리스트에 담기
				}
				
			}
		}while(!pwFlag);
		
		return result;

	}

	//회원가입 반복 여부 메서드 
	public int replay(int result){
		boolean YNflag = false;
		if(result < 1){
			do{
				Scanner sc = new Scanner(System.in);
				System.out.println("Continue?[Y/N]");
				String flag = sc.nextLine();

				if (flag.equalsIgnoreCase("N")) {
					System.out.println("Good Bye");
					result++; // result 증가
					YNflag = false;
				}else if(!flag.equalsIgnoreCase("Y")){
					System.out.println("wrolng character");
					YNflag = true;
				}else{
					YNflag = true;
					break;
				}
				
			}while(YNflag);
		}
		return result ;
	}

	// 로그인 메서드
	public void login(Map<String, String> map) {
		int invalid = 0;
		boolean idFlag = false;
		boolean passFlag = false;
		String tmpId = "";
		Scanner sc = new Scanner(System.in);

		do {
			try {
				System.out.println("input id");
				tmpId = sc.nextLine();

				for (String mapkey : map.keySet()) {
					// System.out.println("key:" + mapkey + ",value:" +
					// map.get(mapkey));
					if (tmpId.equals(mapkey)) { // 사용자가 입력한 아이디가 있으면 true 처리 해준다
						idFlag = true;
					}
				}

				if (!idFlag) { // 입력한 아이디가 파일에 없다면
					System.out.println("Error invalid ID : " + tmpId);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		} while (!idFlag);

		do {
			try { // 암호화

				if (invalid >= 3) { // 3회이상 비번 틀렸으면
					System.out.println("password 3times invalid !!! - Program end ");
					break;
				}

				System.out.println("PASS : ");
				String tmpPass = sc.nextLine();

				MessageDigest md = MessageDigest.getInstance("SHA-1"); // 암호화하기
				md.update(tmpPass.getBytes()); // 입력받은 암호를 SHA-1으로 변환할 예정!
				byte byteData[] = md.digest();
				StringBuffer passSb = new StringBuffer();
				for (int i = 0; i < byteData.length; i++) {
					passSb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
				}
				// System.out.println("암호화 성공!!!" + passSb.toString());

				for (String mapkey : map.keySet()) { // 키 갯수만큼 반복한다
					// 아이디 패스워드 비교해서 같다면
					if (map.get(mapkey).equals(" " + passSb.toString()) && tmpId.equals(mapkey)) { // 앞에
																									// 공백맞춰줌
						System.out.println("Succsess.");
						System.out.println("Good bye.");
						passFlag = true;
					}
				}

				if (!passFlag) { // 비밀번호가 틀렸으면
					invalid++; // 비번 틀린 횟수
					System.out.println("Error invalid Password : " + invalid + "times");
				}

			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

		} while (!passFlag);

	}

	// 파일로 내보내기 메서드
	public void ouputStream() throws NoSuchAlgorithmException {
		try {
			String fileName = "password.txt";

			File file = new File(fileName);
			FileWriter fw = new FileWriter(file, true);
			for (int i = 0; i < record.size(); i++) {
				fw.write(record.get(i).getId() + ", " + record.get(i).getPass() + "\n");
				fw.flush();
			}
			fw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 파일 읽어오기 메서드
	public Map inputStream() throws NoSuchAlgorithmException {
		Map<String, String> map = new HashMap<String, String>();

		try {

			int i = 0;
			String userInfo = "";
			String fileName = "password.txt";

			File file = new File(fileName);
			FileReader fr = new FileReader(file);

			// 파일 읽어오기
			while ((i = fr.read()) != -1) {
				// System.out.print((char) i);
				userInfo += (char) i;
			}

			StringTokenizer st = new StringTokenizer(userInfo, "\n");

			while (st.hasMoreElements()) {

				String actualElement = st.nextToken();
				StringTokenizer et = new StringTokenizer(actualElement, ",");

				if (et.countTokens() != 2) {
					throw new RuntimeException("Unexpeced format");
				}
				String key = et.nextToken();
				String value = et.nextToken();
				map.put(key, value);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

}
