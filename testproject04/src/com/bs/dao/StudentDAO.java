package com.bs.dao;
import java.util.ArrayList;
import java.util.Scanner;

import com.bs.vo.StudentVO;
// 입력, 수정, 삭제, 출력를 하는 클래스이다.
// CRUD 작업을 실제로 하는 클래스를 말한다.
// for 문이나 메서드들을 모두 DAO 에 구현시켜준다.
//컨트롤러가 무슨 기능을 하느냐를 정하는것은.
//스프링을 배우느냐? 스트럿츠를 배우느냐에 따라 정해진것이 다르다...
//java 에서개발 할경우 main 함수가 컨트롤러의 역할을 하도록 한다.
//main -> view 메뉴 몇번 누름?-> DAO 함수로 이동 ->  
public class StudentDAO {
	ArrayList<StudentVO> item = new ArrayList<StudentVO>();
	
	public ArrayList inputData(){ //데이터 입력 메서드
		StudentVO sv = new StudentVO();
		// 키보드 입력
		Scanner sc = new Scanner(System.in);
		String select = "";
		System.out.println("이름을 입력해 주세요 ");
		String inputname = sc.nextLine(); 
		sv.setName(inputname);
		boolean flag = true;
		
		do{
			for (int i = 0; i < item.size(); i++) {
//				System.out.print("이름 : " + ((StudentVO)item.get(i)).getName());
				if(inputname.equals(((StudentVO)item.get(i)).getName())){
					System.out.println("중복된 주소록 이름이 있습니다. 덮어 씌워집니다.");
						sv.setName(inputname);
						updateData(item);
						break;
				}
			}
		}while(flag == false);

		if(item.isEmpty()){
			//숫자인지 체크 하기 
			String inscore;
			do{
				System.out.println("점수를 입력해 주세요 (1~100 숫자만 가능)");
				inscore = sc.nextLine();
			//점수값이 숫자가 아니거나 100보다 클경우
			}while(!isNumber(inscore) || Integer.parseInt(inscore) > 100);  
			//입력한 값이 숫자일 경우 
			sv.setScore(Integer.parseInt(inscore));
			
			// grade 계산을 한다.
			if(sv.getScore() >= 90){
				sv.setGrade("A");
			}else if(sv.getScore() >= 80){
				sv.setGrade("B");
			}else if(sv.getScore() >= 70){
				sv.setGrade("C");
			}else{
				sv.setGrade("F");
			}
			item.add(sv);//ArrayList 에 객체 입력
		}
		return item;
		
	}
	 
	//출력 메서드 
	public void printData(ArrayList<StudentVO> li){
		//ArrayList 에 있는 값을 꺼내어서 출력해준다.		
		for (int i = 0; i < li.size(); i++) {
			System.out.print("이름 : " + ((StudentVO)li.get(i)).getName());
			System.out.print(" 점수 : " + ((StudentVO)li.get(i)).getScore());
			System.out.println(" 등급 : " + ((StudentVO)li.get(i)).getGrade());
		}
	}

	//수정 메서드
	public void updateData(ArrayList li){
		Scanner sc = new Scanner(System.in);
		String tmpname = "";
		int score = 0;
		String oriname ="";
		
		if(li==null){
			System.out.println("수정하려는 사람의 이름을 입력해 주세요 ");		
			tmpname = sc.nextLine();
			System.out.println("수정하려는 점수를 입력해 주세요 ");		
			score = Integer.parseInt(sc.nextLine());
		}else{
			tmpname = ((StudentVO)li.get(0)).getName();
			System.out.println("수정하려는 점수를 입력해 주세요 ");		
			score = Integer.parseInt(sc.nextLine());
		}

		for (int i = 0; i < li.size(); i++) {
			oriname = ((StudentVO)li.get(i)).getName();
			if(oriname.equals(tmpname)){
				((StudentVO)li.get(i)).setScore(score);
				// grade 계산을 한다.
				if(((StudentVO)li.get(i)).getScore() >= 90){
					((StudentVO)li.get(i)).setGrade("A");
				}else if(((StudentVO)li.get(i)).getScore() >= 80){
					((StudentVO)li.get(i)).setGrade("B");
				}else if(((StudentVO)li.get(i)).getScore() >= 70){
					((StudentVO)li.get(i)).setGrade("C");
				}else{
					((StudentVO)li.get(i)).setGrade("F");
				}
			}
		}
	}
	
	//삭제 메서드 
	public void deleteData(ArrayList li){
		//이름으로 검색후 자료를 삭제 한다.
		Scanner sc = new Scanner(System.in);
		System.out.println("삭제 하려는 사람의 이름을 입력해 주세요 ");		
		String tmpname = sc.nextLine();

		for (int i = 0; i < li.size(); i++) {
			String oriname = ((StudentVO)li.get(i)).getName();
			if(oriname.equals(tmpname)){
				li.remove(i);
			}
		}
	}
	
	//숫자 입력 체크 메서드 
    public static boolean isNumber(String str){
        boolean result = false;
        try{
            Double.parseDouble(str) ;
            result = true ;
        }catch(Exception e){}
        return result ;
    }
}
