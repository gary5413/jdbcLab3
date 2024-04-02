package jdbcLab183.lab02;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import jdbcLab183.utils.JDBCutil;



public class DemoSqlInjection {
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
//	先在資料庫示範語句
//	SELECT name,password FROM users WHERE name='Ben' and password = '123456';
//	SELECT name,password FROM users WHERE name=' a' or '1'='1 ' and password = 'a' or '1'='1 ';
//	a' or '1'='1
	public Boolean login(String name, String password) throws SQLException {
		String sql = "SELECT name,password FROM users WHERE name = '" + name + "' AND password ='" + password + "'";
		statement = connection.createStatement();
		rs = statement.executeQuery(sql);
		Boolean checkOk = rs.next();
		return checkOk;
	}
	
//	改寫login成preparedStatement寫法
	public Boolean login2(String name,String password) {
		Boolean checkOk=null;
		String sql="SELECT name,password FROM users WHERE name = ? AND password =?";
		Connection connection2 = JDBCutil.getConnection();
		PreparedStatement preparedStatement =null;
		ResultSet rs= null;
		try {
			preparedStatement = connection2.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			rs = preparedStatement.executeQuery();
			checkOk= rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection2, preparedStatement, rs);
		}
		
		return checkOk;
	}

	public static void main(String[] args) {
		DemoSqlInjection demoSqlInjection = new DemoSqlInjection();
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("請輸入姓名");
			String name= scanner.nextLine();
			System.out.println("請輸入密碼");
			String password = scanner.nextLine();
//			demoSqlInjection.createConnection();
//			Boolean loginCheck = demoSqlInjection.login(name, password);
			
			Boolean loginCheck = demoSqlInjection.login2(name, password);
			
			if(loginCheck) {
				System.out.println("登入成功");
			}else {
				System.out.println("登入失敗");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
//		finally {
//			try {
//				demoSqlInjection.closeConnection();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		}
	}

}
