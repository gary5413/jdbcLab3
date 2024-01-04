package jdbcLab3.lab02statement;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DemoCreateStatement {

	public static void main(String[] args) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Properties properties = new Properties();
			FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc.properties"));
			properties.load(fileInputStream);
			String url=properties.getProperty("url");
			String user=properties.getProperty("user");
			String password=properties.getProperty("password");
			Connection connection = DriverManager.getConnection(url,user,password);
			boolean status = !connection.isClosed();
			if(status) {
				System.out.println("開啟連線");
			}
			String sql="INSERT INTO users(name,password,email)VALUES('test','123456','test@mail.com')";
			Statement statement = connection.createStatement();
//			execute() 回傳布林值 有無ResultSet 有true 反之false
			statement.execute(sql);
			System.out.println("執行sql excute");
			statement.close();
			connection.close();	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
