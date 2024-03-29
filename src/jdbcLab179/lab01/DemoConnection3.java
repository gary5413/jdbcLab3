package jdbcLab179.lab01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DemoConnection3 {

	public static void main(String[] args) {
		Connection connection=null;
		FileInputStream fileInputStream=null;
		/*
		 * 資料庫連線資訊另外放在文件中 透過io讀取文件
		 * 降低程式的和資料的耦合度
		 */
		try {
			// 加載driver驅動
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			讀取properties
			Properties properties = new Properties();
//			FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc.properties"));
			fileInputStream = new FileInputStream(new File("src/jdbc.properties"));
			properties.load(fileInputStream);
//			設計基本連線字串
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
//			取的連線物件
//			Connection connection = DriverManager.getConnection(url, user, password);
			connection = DriverManager.getConnection(url, user, password);
			/*
			 *  isClosed確認連線物件是否關閉 ture有關閉 反之無關閉 
			 */
			boolean status = !connection.isClosed();
			System.out.println("連線狀態:"+status);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
//				關閉連線
				connection.close();
				fileInputStream.close();
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
