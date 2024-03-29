package jdbcLab179.lab02;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DemoCreateStatement {

	public static void main(String[] args) {
		FileInputStream fileInputStream=null;
		Connection connection=null;
		Statement statement=null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Properties properties = new Properties();
			fileInputStream = new FileInputStream(new File("src/jdbc.properties"));
			properties.load(fileInputStream);
			String url=properties.getProperty("url");
			String user=properties.getProperty("user");
			String password=properties.getProperty("password");
			connection = DriverManager.getConnection(url,user,password);
			boolean status = !connection.isClosed();
			System.out.println("連線狀態"+status);
//			創建statment 使用createStatement()方法取得statement物件
//			Statement statement = connection.createStatement();
			statement = connection.createStatement();
			String sql="INSERT INTO users(name,password,email) VALUES('test','123456','test@mail.com')";
			statement.execute(sql);
			System.out.println("執行execute");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fileInputStream.close();
//				關閉statement
				statement.close();
				connection.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
