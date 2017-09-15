class InnerEx1{
	class InstanceInner{
		int iv =100;
		//static int cv =100; //error
		final static int CONST = 100; //final static �� ����̹Ƿ� ����Ѵ�.
	}

	static class StaticInner{
		int iv = 200;
		static int cv = 200; //static class �� ����� ���� �� �� �ִ�.
	}

	void MyMethod(){
		class LocalInner{
			int iv = 300;
			//static int cv = 300;  //error static ������ ������ �� ����.
			final static int CONST = 300; //final static �� ����̹Ƿ� ����Ѵ�.
		}
	}

	public static void main(String[]args){
		System.out.println(InstanceInner.CONST);
		System.out.println(StaticInner.cv);
	}
}