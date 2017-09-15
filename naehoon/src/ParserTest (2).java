interface Parseable{
	//구문 분석작업을 수행한다.
	//abstract = 추상 메서드 (이름만 지어놓은 메서드)
	public abstract void parse(String fileName);
		//해야 할 내용이 있어야 될게 없다.
		//메서드 내용이 없다.
}


class ParserManager{
	//리턴타입이 Parseable 의 인터페이스 이다.
	public static Parseable getParser(String type){
		if(type.equals("XML")){
			return new XMLParser();
		}else{
			Parseable p = new HTMLParser();
			return p;
			//return new HTMLParser();
		}
	}
}


class XMLParser implements Parseable{

	public void parse(String fileName){
		System.out.println(fileName + "- XML parsing completed");
	}
}
 

class HTMLParser implements Parseable{
	public void parse(String fileName){
		System.out.println(fileName + " - HTML parsing completed");
	}
}



class ParserTest{
	public static void main(String[]args){
		Parseable parser = ParserManager.getParser("XML");
		//Parseable parser = new XMLParser(); //위에는 결국에 이뜻.
		parser.parse("document.xml");

		parser = ParserManager.getParser("HTML");
		//Parseable parser = new HTMLParser(); //위에는 결국에 이뜻.
		parser.parse("document2.html");
	}
}

