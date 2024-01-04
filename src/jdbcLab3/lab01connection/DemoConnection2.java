package jdbcLab3.lab01connection;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;


public class DemoConnection2 {
	public static void main(String[] args) {
		try {
//		加載Driver 實體化driver
//			不推薦此方法
			SQLServerDriver driver = new SQLServerDriver();
			DriverManager.registerDriver(driver);
			String url="jdbc:sqlserver://localhost;encrypt=false;database=jdbcLabDB;";
			String user="gary1";
			String password="5413gary";
			Connection connection = DriverManager.getConnection(url,user,password);
			boolean status = !connection.isClosed();
			System.out.println("連線狀態"+status);
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
