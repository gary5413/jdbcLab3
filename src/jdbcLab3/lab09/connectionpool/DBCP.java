package jdbcLab3.lab09.connectionpool;

import java.sql.SQLException;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCP {
	private BasicDataSource bds;
	public BasicDataSource openDataSource() {
		BasicDataSource bds = new BasicDataSource();
		try {
			bds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			bds.setUrl("jdbc:sqlserver://localhost;encrypt=false;database=jdbcLabDB");
			bds.setUsername("gary1");
			bds.setPassword("5413gary");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bds;
	}
	
	public void closeDataSource() {
		if(bds!=null) {
			try {
				bds.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
