package jdbcLab3.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCutil {

	public static Connection getConnection()  {
		Connection connection=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Properties properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc.properties"));
			properties.load(fileInputStream);
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			connection = DriverManager.getConnection(url, user, password);
			boolean status = !connection.isClosed();
			if (status) {
				System.out.println("開啟連線");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeResource(Connection connection,Statement statement) {
		try {
			if(connection!=null) {
				connection.close();
			}
			if(statement !=null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
/*
 * 	多重定義 overloading
 *  同名方法 不同的參數
 */
	
	public static void closeResource(Connection connection,Statement statement,ResultSet rs) {
		try {
			if(connection!=null) {
				connection.close();
			}
			if(statement !=null) {
				statement.close();
			}
			if(rs !=null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
