import java.text.MessageFormat;

public class MessageFormatEx2 {
	
	public static void main(String[]args){
		String tableName = "CUST_INFO";
		String msg = "INSERT INTO " + tableName + " VALUES (''{0}'',''{1}'',{2},''{3}'');";
		Object[][] arguments = {
												{"이자바", "02-111-1111", "27", "07-09"}
												,{"김프로", "02-111-1111", "27", "07-09"}
												,{"오내훈", "02-111-1111", "27", "07-09"}
												,{"사내훈", "02-111-1111", "27", "07-09"}
												,{"삼내훈", "02-111-1111", "27", "07-09"}
										};
		
		for (int i = 0; i < arguments.length; i++) {
			String result = MessageFormat.format(msg, arguments[i][0]);
			System.out.println(result);
		}
	}
}
