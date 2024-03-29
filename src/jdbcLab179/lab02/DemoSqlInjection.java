package jdbcLab179.lab02;

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

import jdbcLab179.utils.JDBCutil;



public class DemoSqlInjection {
	
	Connection connection=null;
	FileInputStream fileInputStream=null;
	Statement statement=null;
	ResultSet rs=null;
	public void createConnection() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//			讀取properties
			Properties properties = new Properties();
			fileInputStream = new FileInputStream(new File("src/jdbc.properties"));
			properties.load(fileInputStream);
//			設計基本連線字串
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			connection= DriverManager.getConnection(url,user,password);
			boolean status = !connection.isClosed();
			System.out.println("連線狀態:"+status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		try {
			if(connection !=null) {
				connection.close();
			}
			if(fileInputStream !=null) {
				fileInputStream.close();
			}if(statement !=null) {
				statement.close();
			}
			if(rs !=null) {
				rs.close();
			}
		}catch (Exception e) {
		}
	}
	
	public Boolean login(String name,String password) {
		String sql="SELECT name,password FROM users WHERE name = '"+name+"' AND password ='"+password+"'";
		Boolean checkOk=null;
			try {
				statement= connection.createStatement();
				rs =statement.executeQuery(sql);
				checkOk =rs.next();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
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
			// TODO Auto-generated catch block
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
			String name=scanner.nextLine();
			System.out.println("請輸入密碼");
			String password=scanner.nextLine();
//			很重要要開連線a
//			demoSqlInjection.createConnection();
//			Boolean loginCheck = demoSqlInjection.login(name, password);
			Boolean loginCheck = demoSqlInjection.login2(name, password);
			if(loginCheck) {
				System.out.println("登入成功");
			}else {
				System.out.println("登入失敗");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		finally {
//			demoSqlInjection.closeConnection();
//		}
		
		
	}

}
