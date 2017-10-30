class Triangle{

	Point[] p = new Point[3];

	Triangle(){
	}

	Triangle(Point[]p){
		this.p = p;
	}

	Triangle(Point p1, Point p2, Point p3){
		p[0] = p1;
		p[1] = p2;
		p[2] = p3;
	}
/*
	void draw(){ //자식클래스의 메서드가 우선순위이다.(오버라이드 됨)
		System.out.printf("[p1=%s, p2=%s, p3=%s, color=%s]%n", p[0].getXY(), p[1].getXY(), p[2].getXY(), color);
	}
*/

}