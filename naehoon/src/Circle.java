class Circle{
/*
	int x = 250;
	int y = 200;
*/

	Point center;
	int r ; 

	Circle(){
		this(new Point(0, 0), 100);
	}

	Circle(Point center, int r){
		this.center = center;
		this.r = r;
	}

/*

	void draw(){
		System.out.printf("[center=(%d,%d), color=%s]%n",center.x, center.y, color);
	}


	Point center;
	int r; 

	Circle(){
		this(new Point(0,0),100);
	}

	Circle(Point center, int r){
		this.center = center;
		this.r =r;
	}

	void draw(){
		System.out.printf("[center=(%d,%d), r=%d, color=%s]%n",center.x, center.y, r, color);
	}
	*/
}