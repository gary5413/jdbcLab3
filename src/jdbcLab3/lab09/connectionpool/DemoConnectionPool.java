package jdbcLab3.lab09.connectionpool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.zaxxer.hikari.HikariDataSource;

import jdbcLab3.util.JDBCutil;
import jdbcLab3.util.JDBCutilWithHikariCp;

public class DemoConnectionPool {
	private Connection connection;
	C3P0 c3p0 = new C3P0();
	DBCP dbcp = new DBCP();
	HikariCp hikariCp=new HikariCp();
	public void createConnection() {
//		ComboPooledDataSource datasource = c3p0.openDataSource();
//		BasicDataSource datasource = dbcp.openDataSource();
		HikariDataSource datasource = hikariCp.openDataSource();
		try {
			connection = datasource.getConnection();
			boolean status = !connection.isClosed();
			System.out.println("連線狀態:"+status);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void closeConnection() {
		if(connection!=null) {
			try {
				connection.close();
//				c3p0.closeDataSource();
//				dbcp.closeDataSource();
				hikariCp.closeDataSource();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void queryUesrs() {
		String sql="SELECT * FROM users";
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(null, prepareStatement);
		}
	}
	
	public void queryUesrs(Connection connection) {
		String sql="SELECT * FROM users";
		PreparedStatement prepareStatement=null;
		try {
			prepareStatement = connection.prepareStatement(sql);
			ResultSet rs = prepareStatement.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JDBCutil.closeResource(null, prepareStatement);
		}
	}
	
	public static void main(String[] args) {
		DemoConnectionPool demoConnectionPool = new DemoConnectionPool();
		Connection connection = JDBCutilWithHikariCp.getConnection();
		try {
//			demoConnectionPool.createConnection();
			
			demoConnectionPool.queryUesrs(connection);
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
//			demoConnectionPool.closeConnection();	
			JDBCutilWithHikariCp.closeResource(connection, null);
		}
	}
	
}
