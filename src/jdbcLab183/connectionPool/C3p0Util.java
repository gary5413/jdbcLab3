package jdbcLab183.connectionPool;

import java.beans.PropertyVetoException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0Util {
	private ComboPooledDataSource cpds;
	public ComboPooledDataSource openDataSource() {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass( "com.mysql.cj.jdbc.Driver" );
			cpds.setJdbcUrl( "jdbc:mysql://localhost:3306/jdbcLabEEIT183" );
			cpds.setUser("root");                                  
			cpds.setPassword("5413gary"); 
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cpds;
	}
	
	public void closeDataSource() {
		if(cpds!=null) {
			cpds.close();
		}
	}
}
