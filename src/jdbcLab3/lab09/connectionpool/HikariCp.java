package jdbcLab3.lab09.connectionpool;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCp {
	private HikariDataSource ds;
	public HikariDataSource openDataSource() {
		HikariConfig config = new HikariConfig();
		config.setJdbcUrl("jdbc:sqlserver://localhost;encrypt=false;database=jdbcLabDB");
		config.setUsername("gary1");
		config.setPassword("5413gary");
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

		HikariDataSource ds = new HikariDataSource(config);
		return ds;
	}
	
	public void closeDataSource() {
		if(ds!=null) {
			ds.close();
		}
	}
}
