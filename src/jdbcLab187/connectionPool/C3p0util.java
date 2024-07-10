package jdbcLab187.connectionpool;



import com.mchange.v2.c3p0.ComboPooledDataSource;

public class C3p0util {
	private ComboPooledDataSource cpds;
	public ComboPooledDataSource openDataSource() {
		ComboPooledDataSource cpds = new ComboPooledDataSource();
		/**
		 * #user=gary1
#password=5413gary
#url=jdbc:sqlserver://localhost;encrypt=false;database=jdbcLabDB
com.microsoft.sqlserver.jdbc.SQLServerDriver
		 */
		try {
			cpds.setDriverClass("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			cpds.setJdbcUrl("jdbc:sqlserver://localhost;encrypt=false;database=jdbcLabEEIT183");
			cpds.setUser("gary1");
			cpds.setPassword("gary1");
		} catch (Exception e) {
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
