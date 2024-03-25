package jdbcLab3.lab02.statementSqlInjection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.Scanner;

import jdbcLab3.util.JDBCutil;

public class DemoSqlInjection {
	
	FileInputStream fileInputStream = null;
	Connection connection = null;
	Statement statement = null;
	ResultSet rs=null;
	
	public void createConnection() {
		try {
			// 加載driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// 讀取properties
			Properties properties = new Properties();
			fileInputStream = new FileInputStream(new File("src/jdbc.properties"));
			properties.load(fileInputStream);
			String url = properties.getProperty("url");
			String user = properties.getProperty("user");
			String password = properties.getProperty("password");
			// 取得連線
			connection = DriverManager.getConnection(url, user, password);
			boolean status = !connection.isClosed();
			if (status) {
				System.out.println("開啟連線");
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
		try {
			if (connection != null) {
				connection.close();
				System.out.println("關閉連線");
			}
			if (fileInputStream != null) {
				fileInputStream.close();
			}
			if (statement != null) {
				statement.close();
			}
			if(rs!=null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean login(String name,String password) {
//		先在資料庫示範語句
//		SELECT name,password FROM users WHERE name='Ben' and password = '123456';
		String sql="SELECT name,password FROM users WHERE name='"+name+"' and password = '"+password+"'";
		Boolean checkOK=null;
		try {
			statement=connection.createStatement();
			rs= statement.executeQuery(sql);
			checkOK = rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return checkOK;
	}
	
	public Boolean login2(String name,String password) {
		String sql="SELECT name,password FROM users WHERE name=? and password = ?";
		Boolean checkOK=null;
		Connection connection2=null; 
		PreparedStatement prepareStatement=null;
		ResultSet rs=null;
		try {
			connection2 = JDBCutil.getConnection();
			prepareStatement = connection2.prepareStatement(sql);
			prepareStatement.setString(1,name);
			prepareStatement.setString(2,password);
			rs = prepareStatement.executeQuery();
			checkOK= rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(connection2, prepareStatement, rs);
		}
		return checkOK;
	}
	
	public static void main(String[] args) {
//		先示範這版本
		DemoSqlInjection demoSqlInjection = new DemoSqlInjection();;
		Scanner scanner = new Scanner(System.in);
		try {
			System.out.println("請輸入姓名");
			String name= scanner.nextLine();
			System.out.println("請輸入密碼");
			String password = scanner.nextLine();
			demoSqlInjection.createConnection();
			Boolean loginCheck = demoSqlInjection.login(name, password);
			if(loginCheck) {
				System.out.println("登入成功");
			}else {
				System.out.println("登入失敗");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			demoSqlInjection.closeConection();			
		}
		
//		加了迴圈
//		Scanner scanner = new Scanner(System.in);
//		DemoSqlInjection demoSqlInjection = new DemoSqlInjection();
//		boolean status=true;
//		while (status) {
//			System.out.println("請輸入姓名");
//			String name= scanner.nextLine();
//			System.out.println("請輸入密碼");
//			String password = scanner.nextLine();
//			demoSqlInjection.createConnection();
////			Boolean loginCheck = demoSqlInjection.login(name, password);
//			Boolean loginCheck = demoSqlInjection.login2(name, password);
//			if(loginCheck) {
//				System.out.println("登入成功");
//				status=false;
//			}else {
//				System.out.println("登入失敗");
//				status=true;
//			}
//			demoSqlInjection.closeConection();
//			System.out.println("===================================================a");
//		}
		
		
		/*
		 * 示範sqlinjection
		 * a' or 1=1 -- 1
		 */
		
	}

}
