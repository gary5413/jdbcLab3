package jdbcLab3.lab02.statement;

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

public class DemoCreateStatement2 {
	
	public Connection getConnection() {
		
		try {
			// 加載driver
//			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 讀取properties
			Properties properties = new Properties();
			try (FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc.properties"))) {
				properties.load(fileInputStream);
				String url = properties.getProperty("url");
				String user = properties.getProperty("user");
				String password = properties.getProperty("password");
				try (Connection connection = DriverManager.getConnection(url, user, password)) {
					boolean status = !connection.isClosed();			
					// 取得連線
					if (status) {
						System.out.println("開啟連線");
					}
//					返回實際的連線
					return connection;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void createConnection() {
		try {
			// 加載driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// 讀取properties
			Properties properties = new Properties();
			try (FileInputStream fileInputStream = new FileInputStream(new File("src/jdbc.properties"))) {
				properties.load(fileInputStream);
				String url = properties.getProperty("url");
				String user = properties.getProperty("user");
				String password = properties.getProperty("password");
				try (Connection connection = DriverManager.getConnection(url, user, password)) {
					boolean status = !connection.isClosed();			
					// 取得連線
					if (status) {
						System.out.println("開啟連線");
					}
				}
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
//		try {
//			if (connection != null) {
//				connection.close();
//				System.out.println("關閉連線");
//			}
//			if (fileInputStream != null) {
//				fileInputStream.close();
//			}
//			if (statement != null) {
//				statement.close();
//			}
//			if(rs!=null) {
//				rs.close();
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
// 	新增

//	public void insert() {
////		創建statement
//		String sql = "INSERT INTO users(name,password,email)VALUES('test','123456','test@mail.com')";
//		try {
//			statement = connection.createStatement();
//			statement.execute(sql);
//			System.out.println("執行sql excute");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//	}
	
	public void insert(Connection connection) {
		String sql = "INSERT INTO users(name,password,email)VALUES('test','123456','test@mail.com')";
		try (Statement createStatement = connection.createStatement()) {
			createStatement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
//	
////	刪除
//	public void delete() {
//		String sql="DELETE FROM　users WHERE id =6";
//		try {
//			statement= connection.createStatement();
////			回傳int 代表已執行筆數
//			int row = statement.executeUpdate(sql);
//			System.out.println("刪除"+row+"筆");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
//	
////	更新
//	/*
//	 * 讓同學動手實作
//	 * 第一筆資料 apple 改為 banana 
//	 */
//	
//	public void update() {
//		String sql="UPDATE users SET　name='banana' WHERE id=1";
//		try {
//			statement=connection.createStatement();
//			int row = statement.executeUpdate(sql);
//			System.out.println("更新"+row+"筆");
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
//	
////	查 搜尋
//	public void queryUsers() {
////		關於* 有部分資深工程師會認為 如果你搜尋的資料不需要全部的話能不要用*就不要用 可能會影響效能
//		String sql="SELECT * FROM users";
//		try {
//			statement=connection.createStatement();
////			回傳ResultSet 結果集
//			rs = statement.executeQuery(sql);
////			next()方法 確認有無下一筆資料 回傳布林值
////			boolean result = rs.next();
////			System.out.println("result:"+result);
//			while(rs.next()) {
//				System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString("password")+","+rs.getString("email"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
////		finally {
////			try {
////				rs.close();
////			} catch (SQLException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////		}
//	}
//	
//	/*
//	 * 給同學操作
//	 * 搜尋id為1的單筆資料
//	 */
//	public void findUserById() {
//		String sql="SELECT name,password,email FROM users WHERE id =1";
//		try {
//			statement=connection.createStatement();
//			rs =statement.executeQuery(sql);
//			rs.next();
////			順便示範搜尋不同資料 索引不同
//			System.out.println(rs.getString(1)+","+rs.getString("password"));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}

	public static void main(String[] args) {
		/*
		 * 介紹實體化 甚麼是new 
		 * 实例化是将类的抽象概念转化为在内存中实际存在的对象的过程。
		 * 存入內存
		 */
		DemoCreateStatement2 demo2 = new DemoCreateStatement2();
//		demo2.createConnection();
		Connection connection = demo2.getConnection();
		demo2.insert(connection); 
//		demo2.delete();
//		demo2.update();
//		demo2.queryUsers();
//		demo2.findUserById();
//		demo2.closeConection();
	
	}

}
