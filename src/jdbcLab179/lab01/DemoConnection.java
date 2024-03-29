package jdbcLab179.lab01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DemoConnection {

	public static void main(String[] args) {
		Connection connection=null;
		try {
			// 加載driver驅動
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			/*
			 * jdbc連線字串url jdbc:協議:名稱
			 * jdbc:sqlserver://localhost:1433;DatabaseName=jdbcLabDB;encrypt=false
			 * 
			 * sql server: encrypt=false 表示不啟用加密
			 */
//			設計基本連線字串
			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=jdbcLabDB;encrypt=false";
			String user = "gary";
			String password = "123456";
//			取的連線物件
//			Connection connection = DriverManager.getConnection(url, user, password);
			connection = DriverManager.getConnection(url, user, password);
			/*
			 *  isClosed確認連線物件是否關閉 ture有關閉 反之無關閉 
			 */
			boolean status = !connection.isClosed();
			System.out.println("連線狀態:"+status);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
//				關閉連線
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
