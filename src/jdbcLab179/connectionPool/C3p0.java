package jdbcLab179.connectionPool;

import java.beans.PropertyVetoException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0 {
	public ComboPooledDataSource openDataSource() {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass( "com.microsoft.sqlserver.jdbc.SQLServerDriver" );
			cpds.setJdbcUrl( "jdbc:sqlserver://localhost:1433;DatabaseName=jdbcLabDB;encrypt=false" );
			cpds.setUser("gary");                                  
			cpds.setPassword("123456"); 
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //loads the jdbc driver            
		          
		return cpds;
	}
}
