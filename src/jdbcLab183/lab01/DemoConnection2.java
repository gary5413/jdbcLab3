package jdbcLab183.lab01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

public class DemoConnection2 {

	public static void main(String[] args) {
		Connection connection=null;
		try {
			// 加載driver驅動
			/*
			 * 實體化driver
			 * 不推薦此方法
			 */
			SQLServerDriver driver = new SQLServerDriver();
			DriverManager.registerDriver(driver);
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
