class RepairableTest{
	public static void main(String[]args){
		Tank tank = new Tank();				//Tank		��ü ����
		Dropship dropship = new Dropship(); //Dropship	��ü ����
		Marine marine = new Marine();		//Marine	��ü ����
		SCV scv = new SCV();				//SCV		��ü ����

		scv.repair(tank); // Tank Ŭ������ Repairable �������̽��� implements �Ͽ����Ƿ� �������� tank �� Repairable Type �̴�.
		scv.repair(dropship); // Dropship Ŭ������ Repairable �������̽��� implements �Ͽ����Ƿ� �������� dropship�� Repairable Type �̴�.
		//scv.repair(marine);  //error //Repairable �� implements ���� �ʾ����Ƿ� marine �� Repairable Type �Ű������� ����Ҽ� ����.
	}
}

interface Repairable{ //�������̽��� ������

}

class Unit{ //unit Ŭ���� ����.
	int hitPoint;  //�ν��Ͻ� ���� ����
	final int MAX_HP; //final ���� ����

	Unit(int hp){ //������ ����
		MAX_HP = hp;
	}
}

class GroundUnit extends Unit{  //Unit Ŭ������ ��ӹ���
	GroundUnit(int hp){ // GroundUnit ������ 
		super(hp);		// Unit ������ ����
	}
}

class AirUnit extends Unit{  //Unit Ŭ������ ��ӹ���
	AirUnit(int hp){  //AirUnit ������ 
		super(hp);	  //Unit ������ ����
	}
}

class Tank extends GroundUnit implements Repairable{  //GroundUnit ��ӹް� Repairable implements ��Ŵ
	Tank(){ //Tank ������ 
		super(150); //GroundUnit ������ 
		hitPoint = MAX_HP;   //hitPoint = 150;
	}

	public String toString(){
		return "Tank"; //Tank ����
	}
}

class Dropship extends AirUnit implements Repairable{ //AirUnit ��� �ް� Repairable implements ��Ŵ.
	Dropship(){ //Dropship
		super(125); //AirUnit ������ 
		hitPoint = MAX_HP; // hitPoint = 125; 
	}

	public String toString(){
		return "Dropship"; // Dropship ���� 
	}
}

class Marine extends GroundUnit {  //GroundUnit ��� ����.
	Marine(){ // Marine������ 
		super(40); //GroundUnit ������ 
		hitPoint = MAX_HP; // hitPoint = 40;
	}
}

class SCV extends GroundUnit implements Repairable{ //GroundUnit ��� �ް� Repairable implements ��Ŵ.
	SCV(){ //SCV ������ 
		super(60);  //GroundUnit ������ 
		hitPoint = MAX_HP; //hitPoint = 60;
	}

	void repair(Repairable r){
		if(r instanceof Unit){  //���� r �� Unit Type �̶�� 
			Unit u = (Unit)r;   //Unit Type ���� ���� u = (Unit)r ->>Unit Type���� ĳ����.
			while(u.hitPoint != u.MAX_HP){ // u.hitPoint �� u.MAX_HP �� ���� �ʴٸ�
				u.hitPoint  ++; // u.hitPoint 1 ������Ŵ (������ ���������� ����)
			}
			System.out.println(u.toString() + "�� ������ �������ϴ� " ); // Unit �� ��ӹ޾Ƽ� toString �޼��� �����.
		}
	}
}