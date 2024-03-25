package jdbcLab3.util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class JDBCutilWithHikariCp {
	/*
	 *	使用hikariCp 
	 */
	private static HikariDataSource ds;
	static {
		HikariConfig config = new HikariConfig("src/hikariCp.properties");
		ds = new HikariDataSource(config);
		
	}
	public static Connection getConnection() {
		Connection connection=null;
		try {
			connection = ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public static void closeResource(HikariDataSource ds) {
		if(ds!=null) {
			ds.close();
		}
	}
	
	
	public static void closeResource(Connection conn,Statement pstmt) {
		try {
			if(pstmt !=null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn !=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void closeResuore(Connection conn,Statement pstmt,ResultSet rs) {
		try {
			if(pstmt !=null)
				pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(conn !=null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(rs !=null)
				rs.close();
		} catch (SQLException e) {
		}
	}
}
