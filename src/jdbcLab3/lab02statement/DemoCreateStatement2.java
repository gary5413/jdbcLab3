package jdbcLab3.lab02statement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DemoCreateStatement2 {
	FileInputStream fileInputStream = null;
	Connection connection = null;
	Statement statement = null;

	public void createConnection() {
		try {
			// 加載driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// 讀取properties
			Properties properties = new Properties();
			fileInputStream = new FileInputStream(new File("src/jdbc.properties"));
			properties.load(fileInputStream);
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			// 取得連線
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
	}

	public void closeConection(){
		try {
			if (connection != null) {
				connection.close();
				System.out.println("關閉連線");
			}
			if (fileInputStream != null) {
				fileInputStream.close();
			}
			if (statement != null) {
				statement.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void insert() {
//		創建statement
		String sql = "INSERT INTO users(name,password,email)VALUES('test','123456','test@mail.com')";
		try {
			statement = connection.createStatement();
			statement.execute(sql);
			System.out.println("執行sql excute");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) {
		/*
		 * 介紹實體化 甚麼是new 
		 * 实例化是将类的抽象概念转化为在内存中实际存在的对象的过程。
		 * 存入內存
		 */
		DemoCreateStatement2 demo2 = new DemoCreateStatement2();
		demo2.createConnection();
		demo2.insert();
		demo2.closeConection();
	
	}

}
