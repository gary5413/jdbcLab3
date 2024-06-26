package jdbcLab179.lab01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DemoConnection4 {

	public static void main(String[] args) {
//		使用try-with-resource方式 自動關閉資源
		try (FileInputStream fileInputStream=new FileInputStream(new File("src/jdbc.properties")))
		{
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Properties properties = new Properties();
			properties.load(fileInputStream);
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			try(Connection connection=DriverManager.getConnection(url,user,password)){
				boolean status = !connection.isClosed();
				System.out.println("連線狀態:"+status);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}

}
