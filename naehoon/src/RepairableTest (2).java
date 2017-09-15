class RepairableTest{
	public static void main(String[]args){
		Tank tank = new Tank();				//Tank		객체 생성
		Dropship dropship = new Dropship(); //Dropship	객체 생성
		Marine marine = new Marine();		//Marine	객체 생성
		SCV scv = new SCV();				//SCV		객체 생성

		scv.repair(tank); // Tank 클래스는 Repairable 인터페이스를 implements 하였으므로 참조변수 tank 는 Repairable Type 이다.
		scv.repair(dropship); // Dropship 클래스는 Repairable 인터페이스를 implements 하였으므로 참조변수 dropship은 Repairable Type 이다.
		//scv.repair(marine);  //error //Repairable 를 implements 하지 않았으므로 marine 을 Repairable Type 매개변수로 사용할수 없음.
	}
}

interface Repairable{ //인터페이스를 정의함

}

class Unit{ //unit 클래스 생성.
	int hitPoint;  //인스턴스 변수 선언
	final int MAX_HP; //final 변수 선언

	Unit(int hp){ //생성자 선언
		MAX_HP = hp;
	}
}

class GroundUnit extends Unit{  //Unit 클래스를 상속받음
	GroundUnit(int hp){ // GroundUnit 생성자 
		super(hp);		// Unit 생성자 실행
	}
}

class AirUnit extends Unit{  //Unit 클래스를 상속받음
	AirUnit(int hp){  //AirUnit 생성자 
		super(hp);	  //Unit 생성자 실행
	}
}

class Tank extends GroundUnit implements Repairable{  //GroundUnit 상속받고 Repairable implements 시킴
	Tank(){ //Tank 생성자 
		super(150); //GroundUnit 생성자 
		hitPoint = MAX_HP;   //hitPoint = 150;
	}

	public String toString(){
		return "Tank"; //Tank 리턴
	}
}

class Dropship extends AirUnit implements Repairable{ //AirUnit 상속 받고 Repairable implements 시킴.
	Dropship(){ //Dropship
		super(125); //AirUnit 생성자 
		hitPoint = MAX_HP; // hitPoint = 125; 
	}

	public String toString(){
		return "Dropship"; // Dropship 리턴 
	}
}

class Marine extends GroundUnit {  //GroundUnit 상속 받음.
	Marine(){ // Marine생성자 
		super(40); //GroundUnit 생성자 
		hitPoint = MAX_HP; // hitPoint = 40;
	}
}

class SCV extends GroundUnit implements Repairable{ //GroundUnit 상속 받고 Repairable implements 시킴.
	SCV(){ //SCV 생성자 
		super(60);  //GroundUnit 생성자 
		hitPoint = MAX_HP; //hitPoint = 60;
	}

	void repair(Repairable r){
		if(r instanceof Unit){  //만약 r 이 Unit Type 이라면 
			Unit u = (Unit)r;   //Unit Type 참조 변수 u = (Unit)r ->>Unit Type으로 캐스팅.
			while(u.hitPoint != u.MAX_HP){ // u.hitPoint 가 u.MAX_HP 와 같지 않다면
				u.hitPoint  ++; // u.hitPoint 1 증가시킴 (수리가 끝날때까지 루프)
			}
			System.out.println(u.toString() + "의 수리가 끝났습니다 " ); // Unit 을 상속받아서 toString 메서드 실행됨.
		}
	}
}