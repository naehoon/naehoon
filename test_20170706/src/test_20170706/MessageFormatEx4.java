package test_20170706;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.MessageFormat;
public class MessageFormatEx4 {

	public static void main(String[] args) throws Exception {
		
		MessageFormatEx4 mf = new MessageFormatEx4();
		String tableName = "GOODSINFO";
		String msg = "INSERT INTO " + tableName + " VALUES({0},{1},{2},{3});";
		String result = "";
		
		for (int i = 0; i < 3000; i++) {
			Object[][] arguments = {{
				String.format("%06d", i+1)
				,"'"+Character.toString((char)((int)(Math.random()*26+65))) + "NAME'"
				,Integer.toString((int)(Math.ceil((Math.random()*100))*100))
				,"'"+Character.toString((char)((int)(Math.random()*26+65))) + "MAKER'" 
			  }};
			result += MessageFormat.format(msg, arguments[0])+"\n";
		}
		System.out.println(result);
		mf.ouputStream(result); 
	}

	// 파일로 내보내기 메서드
	public void ouputStream(String result) {
		try {
			String fileName = "goodsinfo.txt";
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(result);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
