package jdbcLab3.lab01.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DemoConnection1 {
//	快捷main
	public static void main(String[] args) {
		Connection connection=null;
		try {
//			註冊 加載Driver
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			/*
			 * jdbc連線字串url
			 * jdbc:協議:名稱
			 * jdbc:jdbc:sqlserver://localhost;database=jdbcLabDB;
			 * 
			 * mssql encrypt=false;
			 * 表示不启用加密
			 * 如果将 encrypt 设置为 true，则连接将使用 SSL/TLS 进行加密，以确保连接的安全性。
			 * 使用加密的连接通常在需要对数据库进行加密传输的敏感信息时使用，以防止信息在传输过程中被窃听。
			 */
//			String url="jdbc:sqlserver://localhost;encrypt=false;database=jdbcLabDB;";
			String url="jdbc:mysql://localhost:3306/mytestdb";
//			String user="gary";
			String user="root";
			String password="5413gary";
//			取得連線
//			Connection connection = DriverManager.getConnection(url,user,password);
			connection = DriverManager.getConnection(url,user,password);
//			檢視連線
			boolean status = !connection.isClosed();
			System.out.println("連線狀態"+status);
//			關閉連線
//			connection.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}			
		}
	}
}
