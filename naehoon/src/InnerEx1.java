class InnerEx1{
	class InstanceInner{
		int iv =100;
		//static int cv =100; //error
		final static int CONST = 100; //final static 은 상수이므로 허용한다.
	}

	static class StaticInner{
		int iv = 200;
		static int cv = 200; //static class 만 멤버를 정의 할 수 있다.
	}

	void MyMethod(){
		class LocalInner{
			int iv = 300;
			//static int cv = 300;  //error static 변수를 선언할 수 없다.
			final static int CONST = 300; //final static 은 상수이므로 허용한다.
		}
	}

	public static void main(String[]args){
		System.out.println(InstanceInner.CONST);
		System.out.println(StaticInner.cv);
	}
}