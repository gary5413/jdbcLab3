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

public class DemoCreateStatement1 {

	public static void main(String[] args)  {
			FileInputStream fileInputStream=null;
			Connection connection=null;
			Statement statement=null;
			try {
//				加載driver
				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//				讀取properties
				Properties properties = new Properties();
				fileInputStream = new FileInputStream(new File("src/jdbc.properties"));
				properties.load(fileInputStream);
				String url=properties.getProperty("url");
				String user=properties.getProperty("user");
				String password=properties.getProperty("password");
//				取得連線
				connection = DriverManager.getConnection(url,user,password);
				boolean status = !connection.isClosed();
				if(status) {
					System.out.println("開啟連線");
				}
//				創建statement
				String sql="INSERT INTO users(name,password,email)VALUES('test','123456','test@mail.com')";
				statement = connection.createStatement();
//				execute() 回傳布林值 有無ResultSet 有true 反之false
				statement.execute(sql);
				System.out.println("執行sql excute");
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
