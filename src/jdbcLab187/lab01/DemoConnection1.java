package jdbcLab187.lab01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DemoConnection1 {

	public static void main(String[] args) {
		Connection connection=null;
		try {
//			com.microsoft.sqlserver.jdbc.SQLServerDriver
//			加載driver驅動
			Class.forName("com.mysql.cj.jdbc.Driver");
//			jdbc 連線字串url 
//			jdbc:協議:名稱
//			jdbc:sqlserver://localhost:1433;DatabaseName=jdbcLabDB;encrypt=false
//			sql server: encrypt=false 表示不啟用加密
//			設定連線字串
			String url="jdbc:mysql://localhost:3306/jdbcLabEEIT183";
//			String user = "gary";
//			String password = "123456";
			String user="root";
			String password="5413gary";
//			取得連線物件
			/*
			 *  isClosed確認連線物件是否關閉 ture有關閉 反之無關閉 
			 */
			connection = DriverManager.getConnection(url,user,password);
			boolean status = !connection.isClosed();
//			關閉連線
//			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
