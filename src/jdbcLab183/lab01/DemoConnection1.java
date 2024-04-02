package jdbcLab183.lab01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DemoConnection1 {

	public static void main(String[] args) {
		try {
//			加載driver驅動
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
//			jdbc 連線字串url 
//			jdbc:協議:名稱
//			jdbc:sqlserver://localhost:1433;DatabaseName=jdbcLabDB;encrypt=false
//			sql server: encrypt=false 表示不啟用加密
//			設定連線字串
//			String url = "jdbc:sqlserver://localhost:1433;DatabaseName=jdbcLabEEIT183;encrypt=false";
			String url="jdbc:mysql://localhost:3306/jdbcLabEEIT183";
//			String user = "gary";
//			String password = "123456";
			String user="root";
			String password="5413gary";
//			取得連線物件
			Connection connection = DriverManager.getConnection(url,user,password);
//			確認連線
			boolean status = !connection.isClosed();
			System.out.println("連線狀態:"+status);
//			關閉連線
//			connection.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
		}
	}

}
