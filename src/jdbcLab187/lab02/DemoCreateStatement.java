package jdbcLab187.lab02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties properties = new Properties();
			fileInputStream = new FileInputStream(new File("src/jdbc187.properties"));
			properties.load(fileInputStream);
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			connection = DriverManager.getConnection(url,user,password);
			boolean status = !connection.isClosed();
			System.out.println("連線狀態"+status);
			statement = connection.createStatement();
			statement.execute("INSERT INTO users(name,password,email) VALUES('test1','123456','test@mail.com')");
			System.out.println("執行execute");
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
		}finally {
			try {
				fileInputStream.close();
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
