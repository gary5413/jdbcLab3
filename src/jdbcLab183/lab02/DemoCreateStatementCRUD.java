package jdbcLab183.lab02;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DemoCreateStatementCRUD {
	Connection connection = null;
	FileInputStream fileInputStream = null;
	Statement statement = null;
	ResultSet rs = null;

	public void createConnection() throws Exception {
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

	}

	public void closeConnection() throws Exception {

		if (fileInputStream != null) {
			fileInputStream.close();
		}
		if (statement != null) {
			statement.close();
		}
		if (rs != null) {
			rs.close();
		}
		if (connection != null) {
			connection.close();
		}

	}

	public void insertUser() throws Exception {
		String sql = "INSERT INTO users(name,password,email) VALUES('test','123456','test@mail.com')";
		statement = connection.createStatement();
		statement.execute(sql);
		System.out.println("執行insert方法");
	}
	
	public void deleteUserById(int userId) throws Exception {
		String sql="DELETE FROM users WHERE id="+userId;
		statement = connection.createStatement();
//		statement.execute(sql);
		System.out.println("執行刪除法方,刪除userId為"+userId);
	}
	
	public void deleteUserByName(String userName) throws Exception {
		String sql="DELETE FROM users WHERE name="+userName;
		statement = connection.createStatement();
		int rows = statement.executeUpdate(sql);
		System.out.println("刪除"+rows+"筆");
	}
	
	
	public void UpdateUserNameById(int userId,String userName) throws Exception {
		String sql="UPDATE users SET name='"+userName+"' WHERE id ="+userId;
		statement= connection.createStatement();
//		statement.execute(sql);
//		System.out.println("執行更新方法");
//		executeUpdate 回傳整數值 增刪改時也可使用
		int row = statement.executeUpdate(sql);
		System.out.println("更新"+row+"筆");
	}
	
	public void UpdateUserName(String userName,String newName) throws Exception {
		String sql="UPDATE users SET name='"+newName+"' WHERE name ="+userName;
		statement= connection.createStatement();
//		statement.execute(sql);
//		System.out.println("執行更新方法");
//		executeUpdate 回傳整數值 增刪改時也可使用
		int row = statement.executeUpdate(sql);
		System.out.println("更新"+row+"筆");
	}
	
	public void findAllUsers() throws SQLException {
		String sql="SELECT * FROM users";
		statement= connection.createStatement();
//		executeQuery()方法來查詢 會回傳ResultSet物件
		ResultSet rs = statement.executeQuery(sql);
//		next()方法確認有無下一列資料回傳boolean值
		while (rs.next()) {
//			getXxx()讀取列中各欄位的值 可以用索引或是欄位名稱來當參數
			System.out.println(rs.getInt("id")+","+rs.getString("name"));
		}
	}
//	注意
//	如果你sql語句查詢沒有該欄位是會查不到資料
	public void findUserById(int userId) throws SQLException {
//		先示範第一個
//		String sql="SELECT name FROM users WHERE id="+userId;
		String sql="SELECT name ,password FROM users WHERE id="+userId;
		statement=connection.createStatement();
		ResultSet rs = statement.executeQuery(sql);
		rs.next();
//		System.out.println(rs.getString("name")+","+rs.getString("id"));
		System.out.println(rs.getString(1));
	}
	
	public static void main(String[] args) {
		DemoCreateStatementCRUD demoCRUD = new DemoCreateStatementCRUD();
		try {
			demoCRUD.createConnection();
//			demoCRUD.insertUser();
//			demoCRUD.deleteUserById(1);
//			demoCRUD.UpdateUserNameById(2, "banana");
//			demoCRUD.findAllUsers();
			demoCRUD.findUserById(2);
//			demoCRUD.UpdateUserName("Ben","Ben1");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				demoCRUD.closeConnection();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
