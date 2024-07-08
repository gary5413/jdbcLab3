package jdbcLab183.connectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;





public class DemoConnectionPool {
	C3p0Util c3p0= new C3p0Util();
	HikariUtil hikariCp= new HikariUtil();
	Connection connection=null;
	Statement statement=null;
	
	
	public void createConnection() throws SQLException {
//		ComboPooledDataSource datasource = c3p0.openDataSource();
		HikariDataSource datasource = hikariCp.openDataSource();
			connection = datasource.getConnection();
			boolean status = !connection.isClosed();
			System.out.println("連線狀態"+status);
		
	}
	
	public void closeConnection() throws SQLException {
		if(connection !=null) {
//				關閉連線
				connection.close();
//				關閉datasource
				c3p0.closeDataSource();
			
		}
	}
	
	public void insert() throws SQLException {
		String sql="INSERT INTO users(name,password,email) VALUES('pool','123456','test@mail.com')";
		
			statement= connection.createStatement();
			statement.execute(sql);
	}
	
	public static void main(String[] args) {
		DemoConnectionPool demoConnectionPool = new DemoConnectionPool();
		try {
			demoConnectionPool.createConnection();
			demoConnectionPool.insert();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				demoConnectionPool.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
