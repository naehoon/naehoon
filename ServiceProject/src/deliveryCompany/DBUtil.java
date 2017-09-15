package deliveryCompany;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	static final String driver = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://192.168.1.18:3306/DeliveryDB?useUnicode=true&characterEncoding=utf8";
		
	public static Connection getConnection( ) throws Exception{
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, "jo", "1111");
		return con;
	}
} 