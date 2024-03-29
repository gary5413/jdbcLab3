package jdbcLab179.connectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;

public class DemoConenectionPool {
	
//	HikariCP hikariCP= new HikariCP();
	C3p0 c3p0= new C3p0();
	Connection connection=null;
	Statement statement=null;
	public void createConnection() {
		ComboPooledDataSource datasource = c3p0.openDataSource();
//		HikariDataSource datasource = hikariCP.openDataSource();
		try {
			connection = datasource.getConnection();
			boolean status = !connection.isClosed();
			System.out.println("連線狀態"+status);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeConnection() {
		if(connection !=null) {
			try {
//				歸還連線
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
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
	
	public static void main(String[] args) {
		DemoConenectionPool demoConenectionPool = new DemoConenectionPool();
		try {
			demoConenectionPool.createConnection();
			demoConenectionPool.insert();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			demoConenectionPool.closeConnection();
		}
		
	}

}
