interface Parseable{
	//���� �м��۾��� �����Ѵ�.
	//abstract = �߻� �޼��� (�̸��� ������� �޼���)
	public abstract void parse(String fileName);
		//�ؾ� �� ������ �־�� �ɰ� ����.
		//�޼��� ������ ����.
}


class ParserManager{
	//����Ÿ���� Parseable �� �������̽� �̴�.
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
		//Parseable parser = new XMLParser(); //������ �ᱹ�� �̶�.
		parser.parse("document.xml");

		parser = ParserManager.getParser("HTML");
		//Parseable parser = new HTMLParser(); //������ �ᱹ�� �̶�.
		parser.parse("document2.html");
	}
}

