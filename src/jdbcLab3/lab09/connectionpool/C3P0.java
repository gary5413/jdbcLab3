package jdbcLab3.lab09.connectionpool;

import java.beans.PropertyVetoException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3P0 {
	private ComboPooledDataSource cpds;
	public ComboPooledDataSource openDataSource() {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		try {
			cpds.setDriverClass( "com.microsoft.sqlserver.jdbc.SQLServerDriver" ); //loads the jdbc driver            
			cpds.setJdbcUrl( "jdbc:sqlserver://localhost;encrypt=false;database=jdbcLabDB" );
			cpds.setUser("gary1");                                  
			cpds.setPassword("5413gary");
			cpds.setMinPoolSize(5);                                     
			cpds.setAcquireIncrement(5);
			cpds.setMaxPoolSize(20);
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
