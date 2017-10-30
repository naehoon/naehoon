class InnerEx3{
	private int outerIv = 0;
	static int outerCv = 0;

	class IsntanceInner{
		int iiv = outerIv;
		int iiv2 = outerCv;
	}

	static class StaticInner{
		static int scv = outerCv;
	}

	void meMethod(){
		int lv = 0;
		final int LV = 0;

		class LocalInner{
			int liv = outerIv;
			int liv2 = outerCv;

			//int liv3 = lv;
			int liv4 = LV;
		}
	}
}