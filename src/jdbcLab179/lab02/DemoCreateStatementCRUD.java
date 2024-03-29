package jdbcLab179.lab02;

import java.sql.Statement;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class DemoCreateStatementCRUD {
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
	/*
	 * CRUD
	 * 增刪改查
	 */
//	新增
	public void insert() {
		String sql="INSERT INTO users(name,password,email) VALUES('test','123456','test@mail.com')";
		try {
			statement= connection.createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	刪除
	public void delete(int id) {
		String sql="DELETE FROM users WHERE id="+id;
		try {
			statement = connection.createStatement();
			statement.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
//	更新
//	id為2 Name名字改成Banana
	public void update(int id,String name) {
		String sql="UPDATE users SET name='"+name+"' WHERE id ="+id;
		try {
			statement= connection.createStatement();
//			statement.execute(sql);
//			executeUpdate 回傳整數值 增刪改時也可使用
			int row = statement.executeUpdate(sql);
			System.out.println("更新"+row+"筆");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	查詢
	public void queryUsers() {
		String sql="SELECT * FROM users";
		try {
			statement= connection.createStatement();
//			executeQuery()方法來查詢 會回傳ResultSet物件
			ResultSet rs = statement.executeQuery(sql);
//			next()方法確認有無下一列資料回傳boolean值
			while (rs.next()) {
//				getXxx()讀取列中各欄位的值 可以用索引或是欄位名稱來當參數
				System.out.println(rs.getInt("id")+","+rs.getString("name"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
//	查詢單筆資料 參數為id
	public void queryUserById(int id) {
		String sql="SELECT name FROM users WHERE id="+id;
		try {
			statement=connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			rs.next();
			System.out.println(rs.getString("name")+","+rs.getString("password"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] args) {
//		實體化
		DemoCreateStatementCRUD demoCreateStatement2 = new DemoCreateStatementCRUD();
		try {
//			創建連線
			demoCreateStatement2.createConnection();
//			CRUD各個方法
//			demoCreateStatement2.insert();
//			demoCreateStatement2.delete(2);
//			demoCreateStatement2.update(5,"banana");
//			demoCreateStatement2.queryUsers();
			demoCreateStatement2.queryUserById(4);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			demoCreateStatement2.closeConnection();			
		}
	}

}
