package bs.com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
	static final String driver = "com.mysql.jdbc.Driver";
	static final String url = "jdbc:mysql://localhost:3306/naehoon?useUnicode=true&characterEncoding=utf8";
		
	public static Connection getConnection( ) throws Exception{
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, "root", "");
		return con;
	}
} 