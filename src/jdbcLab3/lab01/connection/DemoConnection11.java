package jdbcLab3.lab01.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DemoConnection11 {
	public static void main(String[] args) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			String url="jdbc:sqlserver://localhost;encrypt=false;database=jdbcLabDB;";
			String user="gary1";
			String password="5413gary";
			try(Connection connection = DriverManager.getConnection(url,user,password)) {
				boolean status = !connection.isClosed();
				System.out.println("連線狀態"+status);                                                                                                                   
				} catch (Exception e) {
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
