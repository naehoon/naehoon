package jdbc.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//Connection 을 제공해주는 클래스
public class ConnectionProvider {

	public static Connection getConnection() throws SQLException{
		
		return DriverManager.getConnection("jdbc:apache:commons:dbcp:naehoon");
		
	}
	
}