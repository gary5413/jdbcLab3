package jdbcLab187.utils;

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
	public static Connection getConnection() {
		Connection connection=null;
		FileInputStream fileInputStream=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties properties = new Properties();
			fileInputStream = new FileInputStream(new File("src/jdbc183.properties"));
			properties.load(fileInputStream);
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			connection = DriverManager.getConnection(url, user, password);
			boolean status = !connection.isClosed();
			System.out.println("連線狀態:" + status);
		
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
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return connection;
	}
	
	/*
	
	 * 知識點
	 * 定義同名方法
	 * 多重定義 Overloading
	 * 定義參數個數或型別不同的同名方法
	 */
	
	public static void closeResource(Connection conn)  {
		try {
			if(conn !=null) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void closeResource(Connection conn,Statement st) {
		try {
			if(conn !=null) {
				conn.close();
			}if(st !=null) {
				st.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void closeResource(Connection conn,Statement st,ResultSet rs) {
		try {
			if(conn !=null) {
				conn.close();
			}if(st !=null) {
				st.close();
			}if(rs !=null) {
				rs.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
