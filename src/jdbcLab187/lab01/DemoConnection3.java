package jdbcLab187.lab01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DemoConnection3 {

	public static void main(String[] args) {
		FileInputStream fileInputStream =null;
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties properties = new Properties();
			fileInputStream = new FileInputStream(new File("src/jdbc187.properties"));
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("passoword");
			connection = DriverManager.getConnection(url,user,password);
			boolean status = !connection.isClosed();
			System.out.println("連線狀態："+status);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				fileInputStream.close();
				connection.close();
			}catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

}
