package jdbcLab3.lab01connection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DemoConnection3 {
	public static void main(String[] args) {
		/*
		 * 將資料庫連線資料 放在文件中 通過讀取文件來讀取
		 * 降低程式和資料的耦合度 此方式更改資訊較為容易維護
		 */
		try {
//			加載Driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			讀取jdbc.properties
			Properties properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc.properties"));
			properties.load(fileInputStream);
			String url=properties.getProperty("url");
			String user=properties.getProperty("user");
			String password=properties.getProperty("password");
			Connection connection = DriverManager.getConnection(url,user,password);
			boolean status = !connection.isClosed();
			System.out.println("連線狀態"+status);
			connection.close();
			
		} catch (ClassNotFoundException | SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
