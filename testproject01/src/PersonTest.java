import java.util.Scanner;
//thred 프로그래밍을 할때 어떤 패키지에 들어있는것인지 고를때가 있다. (패키지를 보고 선택을 해야 한다.)
//이클립스 플러그인 폴더에 플러그인 기능을 추가 시킬수 있다. (플러그인 폴더에 저장된다.)
//.metadata 에 있는 내용을 이클립스가 자동으로 불러온다.(이클립스를 지우고 싶으면 metadata 를 지워야 한다.

public class PersonTest {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Person p = new Person();

		p.setAge(20);
		p.setName("오내훈");

		System.out.println(p.getAge());
		System.out.println(p.getName());
		System.out.println(p);
		
	}
}